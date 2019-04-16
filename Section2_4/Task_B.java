
import java.io.*;
import java.util.*;

public class Task_B {

    public static final int INF = 1000000000;
    public static void main(String[] args) throws FileNotFoundException {
        FastScanner scan = new FastScanner(new File("pathsg.in"));
        int n = scan.nextInt();
        int m = scan.nextInt();

        int[][] graph = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(graph[i], INF);
            graph[i][i] = 0;
        }

        for (int i = 0; i < m; i++) {
            int row = scan.nextInt() - 1;
            int column = scan.nextInt() - 1;
            int value = scan.nextInt();
            graph[row][column] = value;
        }

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if(graph[i][k] + graph[k][j] < graph[i][j])
                        graph[i][j] = graph[i][k] + graph[k][j];
                }
            }
        }

        PrintWriter out = new PrintWriter("pathsg.out");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                out.print(graph[i][j] + " ");
            }
            out.println();
        }
        out.close();
    }

    static class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        FastScanner(File f) {
            try {
                br = new BufferedReader(new FileReader(f));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}