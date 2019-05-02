import java.io.*;

public class Task_A {

    public final static int alphabet = 256;

    /*
     * pat -> pattern txt -> text q -> A prime number
     */
    static String search(String pat, String txt, int q) {
        int pat_leng = pat.length();
        int txt_leng = txt.length();
        int pat_hash = 0; // hash value for pattern
        int txt_hash = 0; // hash value for txt
        int h = 1;
        int count = 0;
        String output = "";

        // The value of h would be "pow(alphabet, pat_leng-1)%q"
        for (int i = 0; i < pat_leng - 1; i++)
            h = (h * alphabet) % q;

        // Calculate the hash value of pattern and first
        // window of text
        for (int i = 0; i < pat_leng; i++) {
            pat_hash = (alphabet * pat_hash + pat.charAt(i)) % q;
            txt_hash = (alphabet * txt_hash + txt.charAt(i)) % q;
        }

        // Slide the pattern over text one by one
        for (int i = 0; i <= txt_leng - pat_leng; i++) {

            // Check the hash values of current window of text
            // and pattern. If the hash values match then only
            // check for characters on by one
            if (pat_hash == txt_hash) {
                /* Check for characters one by one */
                for (int j = 0; j < pat_leng; j++) {
                    if (txt.charAt(i + j) != pat.charAt(j))
                        break;
                }

                // if pat_hash == txt_hash and pat[0...pat_leng-1] = txt[i, i+1,
                // ...i+pat_leng-1]
                if (j == pat_leng) {
                    output += Integer.toString(i + 1) + " ";
                    count++;
                }
            }

            // Calculate hash value for next window of text: Remove
            // leading digit, add trailing digit
            if (i < txt_leng - pat_leng) {
                txt_hash = (alphabet * (txt_hash - txt.charAt(i) * h) + txt.charAt(i + pat_leng)) % q;

                // We might get negative value of txt_hash, converting it
                // to positive
                if (txt_hash < 0)
                    txt_hash = (txt_hash + q);
            }
        }
        String finalOutput = "";
        finalOutput += Integer.toString(count) + "\n" + output;
        return finalOutput;

    }

    /* Driver program to test above function */
    public static void main(String[] args) throws IOException {
        try (BufferedReader fin = new BufferedReader(new FileReader("search1.in"));
                BufferedWriter fout = new BufferedWriter(new FileWriter("search1.out"))) {

            String pat = fin.readLine();
            String txt = fin.readLine();
            pat = pat.trim();
            txt = txt.trim();

            int q = 101; // A prime number
            fout.write(search(pat, txt, q));

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }
}