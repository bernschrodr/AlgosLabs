import java.io.*;

public class Task_B {
    public static void main(String[] args) {

        try (BufferedReader fin = new BufferedReader(new FileReader("matching.in"));
                BufferedWriter fout = new BufferedWriter(new FileWriter("matching.out"))) {

            int E = 0, E_left = 0, E_right = 0;
            String inp = fin.readLine();
            String[] str = inp.split(" ");
            int Intstr[] = new int[3];
            for (int i = 0; i < 3; i++)
                Intstr[i] = Integer.valueOf(str[i]);
            E_left = Intstr[0];
            E_right = Intstr[1];
            E = Intstr[2];

            int Emax = Math.max(E_left, E_right);
            boolean matchings[][] = new boolean[Emax][Emax];

            for (int i = 0; i < E; i++) {
                inp = fin.readLine();
                str = inp.split(" ");
                Intstr = new int[2];
                for (int j = 0; j < 2; j++)
                    Intstr[j] = Integer.valueOf(str[j]);
                int out = Intstr[0] - 1;
                int in = Intstr[1] - 1;
                matchings[out][in] = true;

            }

            GFG m = new GFG(E_left, E_right);

            fout.write(String.valueOf(m.maxBPM(matchings)));
        }

        catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

    static class GFG {
        int E_left;
        int E_right;

        GFG(int E_left, int E_right) {
            this.E_left = E_left;
            this.E_right = E_right;
        }

        boolean bpm(boolean bpGraph[][], int u, boolean seen[], int matchR[]) {

            for (int v = 0; v < E_right; v++) {

                if (bpGraph[u][v] && !seen[v]) {

                    seen[v] = true;

                    if (matchR[v] < 0 || bpm(bpGraph, matchR[v], seen, matchR)) {
                        matchR[v] = u;
                        return true;
                    }
                }
            }
            return false;
        }

        int maxBPM(boolean bpGraph[][]) {
            int matchR[] = new int[E_right];

            for (int i = 0; i < E_right; ++i)
                matchR[i] = -1;

            int result = 0;
            for (int u = 0; u < E_left; u++) {
                boolean seen[] = new boolean[E_right];
                for (int i = 0; i < E_right; ++i)
                    seen[i] = false;

                if (bpm(bpGraph, u, seen, matchR))
                    result++;
            }
            return result;
        }

    }
}
