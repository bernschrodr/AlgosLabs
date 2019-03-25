import java.util.*;
import java.io.*;

public class Task_D {
    public static void main(String[] args) {
        try (BufferedReader fin = new BufferedReader(new FileReader("chinese.in"));
                BufferedWriter fout = new BufferedWriter(new FileWriter("chinese.out"))) {

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
            String[] inputtemp = new String[E];
            for(int i = 0; i < E; i++)
                    inputtemp[i] = "";

            for (int i = 0; i < E; ++i) {
                inp = fin.readLine();
                str = inp.split(" ");
                inputtemp[i] += str[0] + " " + str[1];
                Intstr = new int[2];
                for (int j = 0; j < 2; j++)
                    Intstr[j] = Integer.valueOf(str[j]);
                int a = Intstr[0];
                int b = Intstr[1];
                long w = Long.parseLong(str[2]);
                graph.addEdge(i, a, b, w);
                graph.addPoint(a-1,b-1);
                
                if(a == b){
                    fout.write("NO");
                    return;
                }
                else
                {   
                    Iterator<Graph.Vertex> iter = graph.vert[a-1].adjList.iterator();
                    Iterator<Graph.Vertex> iter2 = graph.vert[b-1].adjList.iterator();
                    while(iter.hasNext()){
                    Graph.Vertex tmp1 = iter.next();
                        if(tmp1.point == b){
                            while(iter2.hasNext()){
                            Graph.Vertex tmp2 = iter2.next();
                            if(tmp2.point == a){
                                fout.write("NO");
                                return;
                            }
                        }
                            
                        }

                    }


            }}

            Arrays.sort(inputtemp);
            for(int i = 0; i < E; i++)
                for(int j = i; j < E; j++)
                if(inputtemp[i] == inputtemp[j])
                    {
                        fout.write("NO");
                            return;
                    }

                

            if(graph.CheckAdj(0))
                fout.write("YES\n");
            else{
                fout.write("NO\n");
                return;}

            graph.set.KruskalMST(graph);
            Vector<Graph.Edge> MST = graph.set.getMST();

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
        Vertex[] vert;
        boolean used[];
        private Sets set;
        static int visited = 0;

        public static class Vertex{
            int point;
            Vector<Vertex> adjList = new Vector<Vertex>();
            Vertex(int i){
                this.point = i;
            }
        }

        public class Edge implements Comparable<Edge> {
            int vertex1, vertex2;
            long weight;
            Sets set;

            Edge(int v1, int v2, long w) {
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
            vert = new Vertex[V];
            used = new boolean[V];
            set = new Sets();
            for(int i = 0; i < V; i++){
                vert[i] = new Vertex(i);
                used[i] = false;
            }

        }

        void addEdge(int index, int v1, int v2, long w) {
            edges[index] = new Edge(v1, v2, w);
        }

        void addPoint(int Point, int adjPoint){
            vert[Point].adjList.add(vert[adjPoint]);
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

        boolean CheckAdj(int root){
            dfs(root);
            if(visited == V)
                return true;
            else
                return false;
        }
    
        private void dfs(int Point) {
            visited++;
            used[Point] = true;
            Iterator<Vertex> iter = vert[Point].adjList.iterator();
            while(iter.hasNext()){
                int to = iter.next().point;
                if (!used[to])
                    dfs(to);
            }
        }

        class Sets {
            int parent[];
            int rank[];
            Vector<Edge> MST;

            Sets() {
                parent = new int[V];
                rank = new int[V];
                MST = new Vector<Edge>();
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

            Vector<Edge> getMST() {
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
