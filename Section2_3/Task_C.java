import java.util.*;
import java.io.*;

public class Task_C {
    public static void main(String[] args) {
        try (BufferedReader fin = new BufferedReader(new FileReader("spantree3.in"));
                BufferedWriter fout = new BufferedWriter(new FileWriter("spantree3.out"))) {

            int V, E;

            String inp = fin.readLine();
            String[] str = inp.split(" ");
            int Intstr[] = new int[2];
            for (int i = 0; i < 2; i++)
                Intstr[i] = Integer.valueOf(str[i]);
            V = Intstr[0];
            E = Intstr[1];

            Graph graph;
            graph = new Graph(V, E);

            for (int i = 0; i < E; ++i) {
                inp = fin.readLine();
                str = inp.split(" ");
                Intstr = new int[3];
                for (int j = 0; j < 3; j++)
                    Intstr[j] = Integer.valueOf(str[j]);
                int a = Intstr[0];
                int b = Intstr[1];
                int w = Intstr[2];
                graph.addEdge(i, a, b, w);
            }

            graph.set.KruskalMST(graph);
            LinkedList<Graph.Edge> MST = graph.set.getMST();

            Iterator<Graph.Edge> iter = MST.iterator();
            long weight = 0;

            while (iter.hasNext()) {
                weight += iter.next().weight;
            }
            fout.write(Long.toString(weight));

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static class Graph {

        private int V, E;
        Edge[] edges;
        private Sets set;

        public class Edge implements Comparable<Edge> {
            int vertex1, vertex2;
            long weight;
            Sets set;

            Edge(int v1, int v2, int w) {
                vertex1 = v1;
                vertex2 = v2;
                weight = w;
            }

            Edge() {
                vertex1 = 0;
                vertex2 = 0;
                weight = 0;
            }

            @Override
            public int compareTo(Edge edge) {
                if (weight < edge.weight)
                    return -1;
                else if (weight == edge.weight)
                    return 0;
                else
                    return 1;
            }

        }

        Graph(int V, int E) {
            this.V = V;
            this.E = E;
            edges = new Edge[E];
            set = new Sets();

        }

        void addEdge(int index, int v1, int v2, int w) {
            edges[index] = new Edge(v1, v2, w);
        }

        void sort() {
            Arrays.sort(edges);
        }

        int getV() {
            return V;
        }

        int getE() {
            return E;
        }

        class Sets {
            int parent[];
            int rank[];
            LinkedList<Edge> MST;

            Sets() {
                parent = new int[V];
                rank = new int[V];
                MST = new LinkedList<Edge>();
            };

            private void make_set(int v) {
                parent[v] = v;
                rank[v] = 0;
            }

            private int find_set(int v) {
                if (v == parent[v])
                    return v;
                return parent[v] = find_set(parent[v]);
            }

            private void union_sets(int a, int b) {
                a = find_set(a);
                b = find_set(b);
                if (a != b) {
                    if (rank[a] < rank[b])
                        swap(a, b);
                    parent[b] = a;
                    if (rank[a] == rank[b])
                        ++rank[a];
                }
            }

            private void initial() {
                for (int i = 0; i < V; i++) {
                    parent[i] = 0;
                    rank[i] = 0;
                    make_set(i);
                }
                MST.clear();
            }

            public void KruskalMST(Graph graph) {
                initial();
                graph.sort();
                for (Edge i : edges) {
                    if (find_set(i.vertex1 - 1) != find_set(i.vertex2 - 1)) {
                        MST.add(i);
                        union_sets(i.vertex1 - 1, i.vertex2 - 1);
                    }
                }

            }

            LinkedList<Edge> getMST() {
                return MST;
            }

        }

    }

    static void swap(int a, int b) {
        int c = a;
        a = b;
        b = c;
    }
}
