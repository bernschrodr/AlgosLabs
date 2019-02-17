import java.io.*;
import java.util.*;

public class Task_D {
    static long startTime = System.currentTimeMillis();
    public static void main(String[] args) {
        
        try (BufferedReader fin = new BufferedReader(new FileReader("Components.in"));
                BufferedWriter fout = new BufferedWriter(new FileWriter("Components.out"))) {
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


            graph.find_comps();
            fout.write(String.valueOf(graph.getNumComponents()) + "\n");
            

            for (int i = 0; i < graph.matchMatrix.length; ++i) {
                fout.write(String.valueOf(graph.matchMatrix[i]));
                fout.write(" ");
            }
            fout.close();
            fin.close();

            long timeSpent = System.currentTimeMillis() - startTime;
            System.out.println("runtime all " + timeSpent + " ms\n");
        }

        catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public static class Graph {
        public Vector<Integer> Component;
        public Vector<Integer>[] ConnectedPoints; // ConnectedPoints[i] содержит список вершин, в которые есть рёбра
                                                      // 
                                                      //
                                                      // из вершины i.

        public int[] matchMatrix;
        private int ammount = 0;
        public int numPoints;
        public int numLines;
        private boolean used[];

        Graph(int numPoints, int numLines) {
            this.numPoints = numPoints;
            this.numLines = numLines;
            ConnectedPoints = new Vector[numPoints];
            Component = new Vector<Integer>(numPoints);
            used = new boolean[numPoints];
            matchMatrix = new int[numPoints];

            for (int i = 0; i < numPoints; i++) {
                ConnectedPoints[i] = new Vector<Integer>(40);
                used[i] = false;
            }

        }

        private void dfs(int Point) {

            used[Point] = true;
            Component.add(Point);
            
            for (int i = 0; i < ConnectedPoints[Point].size(); i++) {
                int to = ConnectedPoints[Point].get(i);

                if (!used[to])
                    dfs(to);
            }
        }

        private void find_comps() {
            ammount = 0;
            for (int i = 0; i < ConnectedPoints.length; i++) {
                if (!used[i]) {
                    ammount++;
                    dfs(i);
                        try {
                            Iterator<Integer> iter = Component.iterator();
                            while(iter.hasNext()){
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
