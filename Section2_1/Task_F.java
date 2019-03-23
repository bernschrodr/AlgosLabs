import java.io.*;
import java.util.*;

public class Task_F {

    public static void main(String[] args) {

        // INPUT
        try (BufferedReader fin = new BufferedReader(new FileReader("input.txt"));
                BufferedWriter fout = new BufferedWriter(new FileWriter("output.txt"))) {
            int NumberOfElements = 0;

            String inp = fin.readLine();
            String[] str = inp.split(" ");
            int Intstr[] = new int[2];
            for (int i = 0; i < 2; i++)
                Intstr[i] = Integer.valueOf(str[i]);
            NumberOfElements = Intstr[1];
            int NumberOfLineElements = Intstr[0];
            int NumberOfPoint = 0;
            

            int startpoint = 0;
            int endpoint = 0;

            char[][] charMatrix = new char[NumberOfLineElements][NumberOfElements];
            int [][] intMatrix = new int[NumberOfLineElements][NumberOfElements];
            for (int i = 0; i < NumberOfLineElements; ++i) {
                inp = fin.readLine();
                for (int j = 0; j < NumberOfElements; j++) {
                    charMatrix[i][j] = inp.charAt(j);
                    NumberOfPoint++;
                    intMatrix[i][j] = NumberOfPoint;

                    if(charMatrix[i][j] == '.' || charMatrix[i][j] == 'S'|| charMatrix[i][j] == 'T' ){
                        
                }
                    else
                        if(charMatrix[i][j] == '#')
                            intMatrix[i][j] = 0;
                    
                    if (charMatrix[i][j] == 'S')
                        startpoint = NumberOfElements * i  + j + 1;
                    else if (charMatrix[i][j] == 'T')
                        endpoint = NumberOfElements * i + j + 1;
                    

                    
                }
            }
            int NumberOfLine = NumberOfPoint * NumberOfPoint;

            Graph graph = new Graph(NumberOfPoint, NumberOfLine);

            for(int j = 0; j<NumberOfElements; j++){
            if (intMatrix[0][j] > 0 && intMatrix[1][j] > 0) {
                int Point1 = intMatrix[0][j];
                int Point2 = intMatrix[1][j];
                graph.ConnectedPoints[Point1 - 1].add(Point2 - 1);
                graph.ConnectedPoints[Point2 - 1].add(Point1 - 1);
            }}

            for (int j = 0; j < NumberOfElements - 1; ++j){
                if (intMatrix[0][j] > 0 && intMatrix[0][j+1] > 0) {
                    int Point1 = intMatrix[0][j];
                    int Point2 = intMatrix[0][j+1];
                    graph.ConnectedPoints[Point1 - 1].add(Point2 - 1);
                    graph.ConnectedPoints[Point2 - 1].add(Point1 - 1);
            }
            
            
        }

            for (int i = 1; i < NumberOfLineElements; ++i) {
                for (int j = 0; j < NumberOfElements; j++) {
                    try {
                        if (intMatrix[i][j] > 0 && intMatrix[i-1][j] > 0) {
                            int Point1 = intMatrix[i][j];
                            int Point2 = intMatrix[i-1][j];
                            graph.ConnectedPoints[Point1 - 1].add(Point2 - 1);
                            graph.ConnectedPoints[Point2 - 1].add(Point1 - 1);
                        }
                        if(j <= NumberOfElements-1)
                        if (intMatrix[i][j] > 0 && intMatrix[i][j+1] > 0) {
                            int Point1 = intMatrix[i][j];
                            int Point2 = intMatrix[i][j+1];
                            graph.ConnectedPoints[Point1 - 1].add(Point2 - 1);
                            graph.ConnectedPoints[Point2 - 1].add(Point1 - 1);
                        }
                    } catch (Exception e) {
                    }

                }

            }

            // CALCULATE
            int[] out = graph.find_length(startpoint,endpoint);
            out[0]=startpoint;
            int k = 0;

            String outstr ="";
            while(out[k] != 0 && k < NumberOfPoint-1){
                if(out[k+1]==0)
                    break;

                if(out[k]<out[k+1])
                    if((out[k]-1)/NumberOfElements == (out[k+1]-1)/NumberOfElements)
                        outstr+='R';
                    else
                    outstr+='D';

                if(out[k]>out[k+1])
                    if((out[k]-1)/NumberOfElements == (out[k+1]-1)/NumberOfElements)
                    outstr+='L';
                    else
                    outstr+='U';
                k++;
            }
            fout.write(outstr.length() + "\n" + outstr);

            

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
                                                   * ConnectedPoints[i] содержит список вершин, в которые есть рёбра вершины i. */                                                   
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

        int[] find_length(int Point, int endpoint) {
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

            
            int[] str = new int[numPoints];
            
            if (!used[Point]) 
            str[0] = 0; 
            else 
            { 
            Vector<Integer> path = new Vector<Integer>(numPoints);
            for (int firstElement = endpoint; firstElement != -1; firstElement = previous[firstElement])
            path.add(firstElement);
            Collections.reverse(path);

            for (int j = 1; j < path.size()+1; ++j)
                str[j] = path.get(j-1) + 1;
            str[path.size()+1] = endpoint;
        }

              
            return str;

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
