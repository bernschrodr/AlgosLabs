import java.io.*;

public class Task_B {
    public static void main(String[] args) {

        try (BufferedReader fin = new BufferedReader(new FileReader("input.txt"));
                BufferedWriter fout = new BufferedWriter(new FileWriter("output.txt"))) {
            int NumberOfPoint = 0;
            String inp = fin.readLine();
            NumberOfPoint = Integer.valueOf(inp);
            boolean Valid = true;

            int[][] Matrix;
            Matrix = new int[NumberOfPoint + 1][NumberOfPoint + 1];

            for (int i = 1; i <= NumberOfPoint; ++i) {
                inp = fin.readLine();
                String[] str = inp.split(" ");
                for (int j = 1; j <= NumberOfPoint; ++j) {
                    Matrix[i][j] = Integer.valueOf(str[j - 1]);
                    if (i == j && Matrix[i][j] == 1) {
                        Valid = false;
                        break;
                    }
                }
            }

            for (int i = 1; i <= NumberOfPoint; ++i) {
                for (int j = i + 1; j <= NumberOfPoint; ++j) {
                    if (Matrix[i][j] != Matrix[j][i]) {
                        Valid = false;
                        break;
                    }
                }

            }
            if (Valid == true)
                fout.write("YES");
            else
                fout.write("NO");
            fout.close();
            fin.close();

        }

        catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }
}
