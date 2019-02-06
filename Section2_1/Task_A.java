import java.io.*;

public class Task_A {
    public static void main(String[] args) {

        try (BufferedReader fin = new BufferedReader(new FileReader("input.txt"));
                BufferedWriter fout = new BufferedWriter(new FileWriter("output.txt"))) {
            int NumberOfPoint = 0;
            String inp = fin.readLine();
            String[] str = inp.split(" ");
            int Intstr[] = new int[2];
            for (int i = 0; i < 2; i++) 
                Intstr[i] = Integer.valueOf(str[i]);
            NumberOfPoint = Intstr[0];
            int NumberOfLine = Intstr[1];
            
            int[][] matrix;
            matrix = new int[NumberOfPoint + 1][NumberOfPoint + 1];
            
        
            for (int i = 0; i < NumberOfPoint; ++i) {
                for (int j = 0; j < NumberOfPoint; ++j) {
                    matrix[i][j] = 0;
                }
            }

            for (int i = 1; i <= NumberOfLine; ++i) {
                inp = fin.readLine();
                str = inp.split(" ");
                Intstr = new int[2];
                for (int j = 0; j < 2; j++) 
                    Intstr[j] = Integer.valueOf(str[j]);
                int PointA = Intstr[0];
                int PointB = Intstr[1];
                matrix[PointA][PointB] = 1;

            }
            for (int i = 1; i <= NumberOfPoint; ++i) {
                for (int j = 1; j <= NumberOfPoint; ++j) {
                    fout.write(matrix[i][j] + " ");
                }
            fout.write("\n");
            
            }
            fout.close();
            fin.close();

        }

        catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }
}
