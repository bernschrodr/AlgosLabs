import java.io.*;
import java.util.Vector;

public class Task_B {

    public static void main(String[] args) throws IOException {
        try (BufferedReader fin = new BufferedReader(new FileReader("search2.in"));
                BufferedWriter fout = new BufferedWriter(new FileWriter("search2.out"))) {

            String pat = fin.readLine();
            String txt = fin.readLine();
            pat = pat.trim();
            txt = txt.trim();
            int pat_leng = pat.length();
            Vector<Integer> index = new Vector<Integer>();

            int[] prefix = prefix(pat + " " + txt);

            for (int i = 0; i < prefix.length; i++)
                if (prefix[i] == pat_leng)
                    index.add(i - 2 * pat_leng);

            fout.write(index.size() + "\n");
            for (int i : index)
                fout.write((i + 1) + " ");

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