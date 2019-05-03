import java.io.*;
import java.util.Vector;

public class Task_C {

    public static void main(String[] args) throws IOException {
        try (BufferedReader fin = new BufferedReader(new FileReader("prefix.in"));
                BufferedWriter fout = new BufferedWriter(new FileWriter("prefix.out"))) {

            String txt = fin.readLine();
            txt = txt.trim();

            int[] prefix = prefix(txt);

            for (int i : prefix)
                fout.write(i + " ");

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

    private static int[] prefix(String str) {
        int[] p = new int[str.length()];
        for (int i = 1; i < str.length(); i++) {
            int k = p[i - 1];
            while (k > 0 && str.charAt(i) != str.charAt(k))
                k = p[k - 1];
            if (str.charAt(i) == str.charAt(k))
                k++;
            p[i] = k;
        }
        return p;
    }
}