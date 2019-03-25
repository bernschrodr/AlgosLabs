import java.io.*;
import java.util.*;

public class Task_B2 {

    public static boolean isCorrect(Tree[] tree, int min, int max, int i){
        Tree buffer = tree[i];
        if(buffer.Visited)
            return true;

        buffer.Visited = true;

        if(!(buffer.key > min && buffer.key < max))
            return false;

        if(buffer.left != -1){
            Tree left = tree[buffer.left];
            if(!isCorrect(tree, min, buffer.key, left.id))
                return false;
        }

        if(buffer.right != -1){
            Tree right = tree[buffer.right];
            if(!isCorrect(tree, buffer.key, max, right.id))
                return false;
        }

        return true;
    }

    public static class Tree {
        int left, right;
        int key;
        int id;
        int parentId;

        boolean Visited = false;
        int height;

        public Tree(int id, int key, int left, int right) {
            this.id = id;
            this.left = left;
            this.right = right;
            this.key = key;
        }
    }



public static void main(String[] args) {
   
    try (BufferedReader fin = new BufferedReader(new FileReader("check.in"));
    BufferedWriter fout = new BufferedWriter(new FileWriter("check.out"))) {
        String str;
        int size = 0;
        int count = 0;
        str = fin.readLine();
        size = Integer.valueOf(str);
        
        Tree tree[] = new Tree[size];

        while ((str = fin.readLine()) != null) {
            String[] inp = str.split(" ");
            int Intstr[] = new int[3];
            for(int i = 0; i < 3; i++)
                Intstr[i] = Integer.valueOf(inp[i]);

            tree[count] = new Tree(count++,Intstr[0], Intstr[1]-1, Intstr[2]-1);
            
    }
    fin.close();

    if(size <= 0 || isCorrect(tree, -Integer.MAX_VALUE, Integer.MAX_VALUE, 0))
            fout.write("YES");
        else
            fout.write("NO");

        fout.close();
        
}

    catch (IOException ex) {
        System.out.println(ex.getMessage());
    }
}}