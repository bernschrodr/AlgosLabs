import java.io.*;
import java.util.*;

public class Task_B {

    public static void main(String[] args) {

        Scanner fin = new Scanner("pathsg.in");

            int V = fin.nextInt();
            int E = fin.nextInt();

            // Init
            Long graph[][] = new Long[V][V];
            for (int i = 0; i < V; i++)
                for (int j = 0; j < V; j++) {
                    if (i == j)
                        graph[i][j] = (long) 0;
                    else
                        graph[i][j] = INF;

                }

            // Fill
            for (int i = 0; i < E; i++) {
                int row = fin.nextInt();
                int column = fin.nextInt();
                graph[row][column] = fin.nextLong();
            }

            AllPairShortestPath grph = new AllPairShortestPath(V);
            grph.floydWarshall(graph);

            fin.close();

        

    }

    final static long INF = Long.MAX_VALUE;

    static class AllPairShortestPath {
        int V;

        AllPairShortestPath(int V) {
            this.V = V;
        }

        void floydWarshall(Long graph[][]) {
            Long dist[][] = new Long[V][V];
            int i, j, k;

            for (i = 0; i < V; i++)
                for (j = 0; j < V; j++)
                    dist[i][j] = graph[i][j];

            for (k = 0; k < V; k++) {
                for (i = 0; i < V; i++) {
                    for (j = 0; j < V; j++) {
                        if (dist[i][k] + dist[k][j] < dist[i][j])
                            dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
            try{
            printSolution(dist);
            }
            catch(Exception exception){

            }
            
        }

        void printSolution(Long dist[][]) throws FileNotFoundException {
            PrintWriter fout = new PrintWriter("pathsg.out");
            for (int i = 0; i < V; ++i) {
                for (int j = 0; j < V; ++j) {
                    fout.print(dist[i][j] + " ");
                }
                fout.println();
            }
            fout.close();
        }

    }
}