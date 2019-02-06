import java.io.*;
import java.util.*;

public class Task_D {

    public static class Graph {
        public LinkedList<Integer> Component;
        public LinkedList<Integer>[] ConnectedPoints; // ConnectedPoints[i] содержит список вершин, в которые есть рёбр
                                                      //  из вершины i.

        private int ammount = 0;
        private int numPoints;
        private int numLines;

        Graph(int[][] Lines) {
            if (Lines[0][0] > 0 & Lines[0][1] > 0) {
                numPoints = Lines[0][0];
                numLines = Lines[0][1];
            } else
                return;

            ConnectedPoints = new LinkedList[numPoints];
            Component = new LinkedList<Integer>();
            used = new boolean[numPoints];

            for (int i = 0; i < numPoints; ++i)
                ConnectedPoints[i] = new LinkedList<Integer>();
            for (int i = 1; i <= numLines; ++i) {
                int a = Lines[i][0];
                int b = Lines[i][1];
                ConnectedPoints[a - 1].add(b - 1);
                ConnectedPoints[b - 1].add(a - 1);

            }

            find_comps();
        }

        private boolean used[];

        private void dfs(int Point) {
            used[Point] = true;
            Component.add(Point);
            for (int i = 0; i < ConnectedPoints[Point].size(); ++i) {
                int to = ConnectedPoints[Point].get(i);
                if (!used[to])
                    dfs(to);
            }
        }

        private void find_comps() {
            ammount = 0;
            for (int i = 0; i < ConnectedPoints.length; ++i)
                used[i] = false;
            for (int i = 0; i < ConnectedPoints.length; ++i) {
                if (!used[i]) {
                    Component.clear();
                    dfs(i);

                    ammount++;
                }

            }

        }

        public int getAmmount() {

            return ammount;
        }

        public int getNumLines() {
            return numLines;
        }

        public int getNumPoints() {
            return numPoints;
        }
    }

    public static void main(String[] args) {

        try (BufferedReader fin = new BufferedReader(new FileReader("input.txt"));
                BufferedWriter fout = new BufferedWriter(new FileWriter("output.txt"))) {
            int NumberOfPoint = 0;

            String inp = fin.readLine();
            String[] str = inp.split(" ");
            int Intstr[] = new int[2];
            for (int i = 0; i < 2; i++)
                Intstr[i] = Integer.valueOf(str[i]);
            NumberOfPoint = Intstr[0];
            int NumberOfLine = Intstr[1];
            int inputArray[][] = new int[NumberOfLine + 1][2];
            inputArray[0][0] = NumberOfPoint;
            inputArray[0][1] = NumberOfLine;

            for (int i = 1; i < NumberOfLine + 1; ++i) {
                inp = fin.readLine();
                str = inp.split(" ");
                Intstr = new int[2];
                for (int j = 0; j < 2; j++)
                    Intstr[j] = Integer.valueOf(str[j]);
                inputArray[i][0] = Intstr[0];
                inputArray[i][1] = Intstr[1];

            }

            Graph graph = new Graph(inputArray);
            fout.write( String.valueOf(graph.getAmmount()) );

            fout.close();
            fin.close();
        }

        catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }
}
