import java.io.*;

public class Task_A {

    private static void CalculateHeight(Tree[] tree, int i, int h){

        Tree temp = tree[i];
        temp.height = h + 1;
        if(temp.left != -1)
            CalculateHeight(tree, temp.left, temp.height);
        if(temp.right != -1)
            CalculateHeight(tree, temp.right, temp.height);
    }

    private static class Tree {
        int left, right;
        int key;

        int height;

        public Tree(int key, int left, int right) {
            this.left = left;
            this.right = right;
            this.key = key;
        }
    }



public static void main(String[] args) {
   
    try (BufferedReader fin = new BufferedReader(new FileReader("height.in"));
    BufferedWriter fout = new BufferedWriter(new FileWriter("height.out"))) {
        String str;
        int size,max = 0;
        int count = 0;
        str = fin.readLine();
        size = Integer.valueOf(str);

        if(size < 3){
            fout.write(String.valueOf(size));
            return;
        }
        
        Tree tree[] = new Tree[size];

        while ((str = fin.readLine()) != null) {
            String[] inp = str.split(" ");
            int Intstr[] = new int[3];
            for(int i = 0; i < 3; i++)
                Intstr[i] = Integer.valueOf(inp[i]);

            tree[count++] = new Tree(Intstr[0], Intstr[1]-1, Intstr[2]-1);
        
    }
        CalculateHeight(tree,0, 0);
        for(int i = 0; i< tree.length; i++)
        if(tree[i].height > max)
        max = tree[i].height;
        fout.write(String.valueOf(max));

        fout.close();
        fin.close();
}

    catch (IOException ex) {
        System.out.println(ex.getMessage());
    }
}}