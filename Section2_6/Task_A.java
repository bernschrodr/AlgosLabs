import java.io.*;
import java.util.Vector;

public class Task_A {

    public static void main(String[] args) throws IOException {
        try (BufferedReader fin = new BufferedReader(new FileReader("search1.in"));
                BufferedWriter fout = new BufferedWriter(new FileWriter("search1.out"))) {

            String pat = fin.readLine();
            String txt = fin.readLine();
            pat = pat.trim();
            txt = txt.trim();

            Vector<Integer> index = new Vector<Integer>();

            int txt_leng = txt.length();
            int pat_leng = pat.length();
    
            for(int i = 0; i <= txt_leng - pat_leng; i++){
                boolean detected = true;
                for(int j = 0; j < pat_leng; j++) {
                    if (txt.charAt(i + j) != pat.charAt(j)) {
                        detected = false;
                        break;
                    }
                }
    
                if(detected)
                    index.add(i);
            }

            fout.write(index.size() + "\n");
            for(int i: index)
                fout.write((i+1) + " ");

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }
}