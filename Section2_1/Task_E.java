import java.io.*;
import java.util.*;

public class Task_E {

    public static void main(String[] args) {

        // INPUT
        try (BufferedReader fin = new BufferedReader(new FileReader("pathbge1.in"));
                BufferedWriter fout = new BufferedWriter(new FileWriter("pathbge1.out"))) {
            int NumberOfPoint = 0;

            String inp = fin.readLine();
            String[] str = inp.split(" ");
            int Intstr[] = new int[2];
            for (int i = 0; i < 2; i++)
                Intstr[i] = Integer.valueOf(str[i]);
            NumberOfPoint = Intstr[0];
            int NumberOfLine = Intstr[1];

            Graph graph = new Graph(NumberOfPoint, NumberOfLine);

            for (int i = 1; i < NumberOfLine + 1; ++i) {
                inp = fin.readLine();
                str = inp.split(" ");
                Intstr = new int[2];
                for (int j = 0; j < 2; j++)
                    Intstr[j] = Integer.valueOf(str[j]);
                int a = Intstr[0];
                int b = Intstr[1];
                graph.ConnectedPoints[a - 1].add(b - 1);
                graph.ConnectedPoints[b - 1].add(a - 1);

            }

            // CALCULATE
            graph.find_length(0);
            int[] lengthForPoints = graph.getLengthMatrix();

            // OUTPUT
            for (int i = 0; i < NumberOfPoint; i++)
                fout.write(String.valueOf(lengthForPoints[i]) + " ");

            fout.close();
            fin.close();

        }

        catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public static class Graph {
        private Vector<Integer> Component;
        public Vector<Integer>[] ConnectedPoints; /*
                                                   * ConnectedPoints[i] содержит список вершин, в которые есть рёбра и
                                                   *  вершины i. */
                                                   

        public int[] matchMatrix;
        private int ammount = 0;
        private int numPoints;
        private int numLines;
        private boolean used[];
        private int length[];
        private int previous[];

        Graph(int numPoints, int numLines) {
            this.numPoints = numPoints;
            this.numLines = numLines;
            length = new int[numPoints];
            previous = new int[numPoints];
            ConnectedPoints = new Vector[numPoints];
            Component = new Vector<Integer>(numPoints);
            used = new boolean[numPoints];
            matchMatrix = new int[numPoints];

            for (int i = 0; i < numPoints; i++) {
                ConnectedPoints[i] = new Vector<Integer>();
                used[i] = false;
            }

        }

        private void dfs(int Point) {

            used[Point] = true;
            for (int i = 0; i < ConnectedPoints[Point].size(); i++) {
                int to = ConnectedPoints[Point].get(i);
                if (!used[to])
                    dfs(to);
            }
            // Принадлежит 1 компоненту
            if (matchMatrix[Point] == 1)
                Component.add(Point);
            else
                Component.add(0);
        }

        private void dfs2(int Point) {
            Component.add(Point);
            used[Point] = true;
            for (int i = 0; i < ConnectedPoints[Point].size(); i++) {
                int to = ConnectedPoints[Point].get(i);

                if (!used[to])
                    dfs2(to);
            }

        }

        void find_length(int Point) {
            if (!Component.isEmpty())
                Component.clear();
            Component.add(Point);
            used[Point] = true;
            previous[Point] = -1;

            while (!Component.isEmpty()) {
                int firstElement = Component.firstElement();
                Component.remove(0);
                Iterator<Integer> iter = ConnectedPoints[firstElement].iterator();
                while (iter.hasNext()) {
                    int to = iter.next();

                    if (!used[to]) {
                        used[to] = true;
                        Component.add(to);
                        length[to] = length[firstElement] + 1;
                        previous[to] = firstElement;
                    }
                }
            }

            /*
             * String str = ""; for (int i = 0; i < numPoints; i++) { if (!used[i]) str +=
             * "No path!\n"; else { Vector<Integer> path = new Vector<Integer>(numPoints);
             * for (int firstElement = i; firstElement != -1; firstElement =
             * previous[firstElement]) path.add(firstElement); Collections.reverse(path);
             * str += "Path " + String.valueOf(i) + " : "; for (int j = 0; j < path.size();
             * ++j) str += String.valueOf(path.get(j) + 1) + " "; str += "\n"; }
             * 
             * }
             */

        }

        void topological_sort() {
            for (int i = 0; i < numPoints; ++i)
                used[i] = false;
            Component.clear();
            for (int i = 0; i < numPoints; ++i)
                if (!used[i])
                    dfs(i);
            Collections.reverse(Component);

        }

        private void find_comps() {
            ammount = 0;
            for (int i = 0; i < ConnectedPoints.length; i++) {
                if (!used[i]) {
                    ammount++;
                    dfs2(i);
                    try {
                        Iterator<Integer> iter = Component.iterator();
                        while (iter.hasNext()) {
                            matchMatrix[iter.next()] = ammount;
                        }

                    } catch (Exception e) {

                    }

                    Component.clear();
                }

            }

        }

        public int getNumComponents() {

            return ammount;
        }

        public int[] getLengthMatrix() {
            return length;
        }

        public int getNumLines() {
            return numLines;
        }

        public int getNumPoints() {
            return numPoints;
        }

        public int[] getMatchMatrix() {
            return matchMatrix;

        }
    }

}
