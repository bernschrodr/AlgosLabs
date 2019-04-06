import java.io.*;
import java.util.*;

public class Task_A1 {

    public static void main(String[] args) {

        try (BufferedReader fin = new BufferedReader(new FileReader("pathmgep.in"));
                BufferedWriter fout = new BufferedWriter(new FileWriter("pathmgep.out"))) {

            int V = 0;
            int E = 0;
            int start = 0;
            int end = 0;

            String inp = fin.readLine();
            String[] str = inp.split(" ");
            int Intstr[] = new int[3];
            for (int i = 0; i < 3; i++)
                Intstr[i] = Integer.valueOf(str[i]);
            V = Intstr[0];
            start = Intstr[1];
            end = Intstr[2];

            Graph graph = new Graph(12);

            for(int i = 0; i < V; i++){
                inp = fin.readLine();
                str = inp.split(" ");
                Intstr = new int[V];
                for (int j = 0; j < V; j++)
                    Intstr[j] = Integer.valueOf(str[j]);



            }


            fout.close();
            fin.close();

        }

        catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

    static class Graph {

        int V, E;
        Vector<Vector<Pair<Integer, Integer>>> graph;
        Vector<Integer> distance, parrent;
        Vector<Character> label;

        Graph(int V) {
            this.V = V;
            distance = new Vector<Integer>(V);
            parrent = new Vector<Integer>(V);
            label = new Vector<Character>(V);
            graph = new Vector<Vector<Pair<Integer, Integer>>>(V);

            for (int i = 0; i < V; i++) {
                graph.set(i, new Vector<Pair<Integer, Integer>>(V));
                graph.get(i).set(i, new Pair<Integer, Integer>(i, Integer.MAX_VALUE));
            }
        }

        void addEdge(int index ,int vert, int weight){
            graph.get(index).set(vert, new Pair<Integer,Integer>(vert, weight));


        }

        void Dijkstra(int start) {
            distance.set(start, 0);

            for (int i = 0; i < V; ++i) {
                int v = -1;
                for (int j = 0; j < V; ++j)
                    if ((label.get(j) != 0) && (v == -1 || distance.get(j) < distance.get(v)))
                        v = j;
                if (distance.get(v) == Integer.MAX_VALUE)
                    break;
                label.set(v, Character.forDigit(1, 10));

                for (int j = 0; j < graph.get(v).size(); ++j) {
                    int to = graph.get(v).get(j).vert, len = graph.get(v).get(j).weight;
                    if (distance.get(v) + len < distance.get(to)) {
                        distance.set(to, distance.get(v) + len);
                        parrent.set(to, v);
                    }
                }
            }

        }

    }

    static class Pair<A, B> {
        A vert;
        B weight;

        public Pair(A vert, B weight) {
            this.vert = vert;
            this.weight = weight;
        }
    }

}