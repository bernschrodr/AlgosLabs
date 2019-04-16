import java.io.*;
import java.util.*;

public class Task_A {
    public static void main(String[] args) {

        try (BufferedReader fin = new BufferedReader(new FileReader("maxflow.in"));
                BufferedWriter fout = new BufferedWriter(new FileWriter("maxflow.out"))) {

            int V = 0;
            int E = 0;
            String inp = fin.readLine();
            String[] str = inp.split(" ");
            int Intstr[] = new int[2];
            for (int i = 0; i < 2; i++)
                Intstr[i] = Integer.valueOf(str[i]);
            V = Intstr[0];
            E = Intstr[1];

            int graph[][] = new int[V][V];

            for (int i = 0; i < E; i++) {
                inp = fin.readLine();
                str = inp.split(" ");
                Intstr = new int[3];
                for (int j = 0; j < 3; j++)
                    Intstr[j] = Integer.valueOf(str[j]);
                int out = Intstr[0] - 1;
                int in = Intstr[1] - 1;
                int flow = Intstr[2];
                graph[out][in] = flow;

            }
            MaxFlow m = new MaxFlow(V);
            fout.write(String.valueOf(m.fordFulkerson(graph, 0, V - 1)));
        }

        catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public static class MaxFlow {

        MaxFlow(int V) {
            this.V = V;
        }

        int V;

        boolean bfs(int rGraph[][], int s, int t, int parent[]) {

            boolean visited[] = new boolean[V];
            for (int i = 0; i < V; ++i)
                visited[i] = false;

            LinkedList<Integer> queue = new LinkedList<Integer>();
            queue.add(s);
            visited[s] = true;
            parent[s] = -1;

            while (queue.size() != 0) {
                int u = queue.poll();

                for (int v = 0; v < V; v++) {
                    if (visited[v] == false && rGraph[u][v] > 0) {
                        queue.add(v);
                        parent[v] = u;
                        visited[v] = true;
                    }
                }
            }

            return (visited[t] == true);
        }

        int fordFulkerson(int graph[][], int s, int t) {
            int u, v;

            int rGraph[][] = new int[V][V];

            for (u = 0; u < V; u++)
                for (v = 0; v < V; v++)
                    rGraph[u][v] = graph[u][v];

            int parent[] = new int[V];

            int max_flow = 0;

            while (bfs(rGraph, s, t, parent)) {
                int path_flow = Integer.MAX_VALUE;
                for (v = t; v != s; v = parent[v]) {
                    u = parent[v];
                    path_flow = Math.min(path_flow, rGraph[u][v]);
                }

                for (v = t; v != s; v = parent[v]) {
                    u = parent[v];
                    rGraph[u][v] -= path_flow;
                    rGraph[v][u] += path_flow;
                }

                max_flow += path_flow;
            }

            return max_flow;
        }

    }
}
