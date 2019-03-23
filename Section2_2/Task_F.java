import java.io.*;
import java.util.*;

public class Task_A {

    public static void main(String[] args) {

        // INPUT
        try (BufferedReader fin = new BufferedReader(new FileReader("hamiltonion.in"));
                BufferedWriter fout = new BufferedWriter(new FileWriter("hamiltonion.out"))) {
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
            for (int i = 0; i < NumberOfPoint; ++i)
                if (graph.cycleCheck(i))
                    break;

            // OUTPUT
            if (graph.getCycle_st() == -1)
                {
                    Vector<Integer> Component = graph.topological_sort();
                    Iterator<Integer> iter = Component.iterator();
                    while(iter.hasNext())
                        fout.write((iter.next()+1) + " ");
                    }
            else
                    fout.write("-1");
                

                /*Vector<Integer> cycle = new Vector<Integer>(NumberOfPoint);
                
                cycle.add(cycle_st);
                for (int v = cycle_end; v != cycle_st; v = Component.get(v))
                cycle.add(v);
                cycle.add(cycle_st);
                Collections.reverse(cycle);
                for (int i = 0; i < cycle.size(); ++i)
                    fout.write(String.valueOf(cycle.get(i) + 1) + " ");*/
            

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
                                               * вершины i.
                                               */

    public int[] matchMatrix;
    private int ammount = 0;
    private int numPoints;
    private int numLines;
    private boolean used[];
    private int length[];
    private int previous[];
    private Vector<Integer> cl;

    Graph(int numPoints, int numLines) {
        this.numPoints = numPoints;
        this.numLines = numLines;
        length = new int[numPoints];
        previous = new int[numPoints];
        ConnectedPoints = new Vector[numPoints];
        Component = new Vector<Integer>(numPoints);
        used = new boolean[numPoints];
        matchMatrix = new int[numPoints];
        cl = new Vector<Integer>(numPoints);

        for (int i = 0; i < numPoints; i++) {
            ConnectedPoints[i] = new Vector<Integer>();
            used[i] = false;
            Component.add(-1);
            cl.add(i);
        }

    }

    public static int cycle_st = -1, cycle_end = 0;

    boolean cycleCheck(int Point) {
        cl.set(Point, 1);
        for (int i = 0; i < ConnectedPoints[Point].size(); i++) {
            int to = ConnectedPoints[Point].get(i);
            if (cl.get(to) == 0) {
                Component.set(to, Point);
                if (cycleCheck(to))
                    return true;
            } else if (cl.get(to) == 1) {
                cycle_end = Point;
                cycle_st = to;
                return true;
            }
        }
        cl.set(Point, 2);
        return false;
    }

    private void dfs(int Point) {

        used[Point] = true;
        for (int i = 0; i < ConnectedPoints[Point].size(); i++) {
            int to = ConnectedPoints[Point].get(i);
            if (!used[to])
                dfs(to);
        }
            Component.add(Point);
        
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

    Vector<Integer> topological_sort() {
        for (int i = 0; i < numPoints; ++i)
            used[i] = false;
        Component.clear();
        for (int i = 0; i < numPoints; ++i)
            if (!used[i])
                dfs(i);
        Collections.reverse(Component);
        return Component;

    }

    private void find_comps() {
        ammount = 0;
        for (int i = 0; i < ConnectedPoints.length; i++) {
            if (!used[i]) {
                ammount++;
                dfs2(i);

                Iterator<Integer> iter = Component.iterator();
                while (iter.hasNext()) {
                    matchMatrix[iter.next()] = ammount;

                    Component.clear();
                }

            }

        }
    }

    

    public int getCycle_end() {
        return cycle_end;
    }

    public int getCycle_st() {
        return cycle_st;
    }

    public Vector<Integer> getComponent() {
        return Component;
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
