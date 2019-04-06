import java.io.*;
import java.util.Arrays;

public class Task_A {

    public static void main(String[] args) {

        try (BufferedReader fin = new BufferedReader(new FileReader("pathmgep.in"));
                BufferedWriter fout = new BufferedWriter(new FileWriter("pathmgep.out"))) {

            int V = 0;
            int start = 0;
            int end = 0;
            String inp = fin.readLine();
            String[] str = inp.split(" ");
            int Intstr[] = new int[3];
            for (int i = 0; i < 3; i++)
                Intstr[i] = Integer.valueOf(str[i]);
            V = Intstr[0];
            start = --Intstr[1];
            end = --Intstr[2];

            // Init
            int[][] graph = new int[V][V];
            boolean[] label = new boolean[V];
            long[] distance = new long[V];
            Arrays.fill(label, false);
            Arrays.fill(distance, Long.MAX_VALUE);

            // Fill
            for (int i = 0; i < V; i++) {
                inp = fin.readLine();
                str = inp.split(" ");
                Intstr = new int[V];
                for (int j = 0; j < V; j++) {
                    Intstr[j] = Integer.valueOf(str[j]);
                    int weight = Intstr[j];
                    graph[i][j] = weight;
                }
            }

            // Dijkstra
            distance[start] = 0;
            for (int i = 0; i < V; ++i) {
                int v = -1;

                for (int j = 0; j < V; ++j)
                    if (!label[j] && (v == -1 || distance[j] < distance[v]))
                        v = j;

                if (distance[v] == Long.MAX_VALUE)
                    break;

                label[v] = true;
                for (int j = 0; j < V; j++) {
                    if (v != j && graph[v][j] != -1 && distance[j] > distance[v] + graph[v][j]) {
                        distance[j] = distance[v] + graph[v][j];
                    }
                }
            }

            // Out
            long result = -1;
            if (distance[end] != Long.MAX_VALUE)
                result = distance[end];
            fout.write(String.valueOf(result));

            fout.close();
            fin.close();

        }

        catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

}