import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Task_C {

    public static void main(String[] args) throws IOException {
        BufferedReader scan = new BufferedReader(new FileReader("bstsimple.in"));
        Tree tree = new Tree();

        PrintWriter out = new PrintWriter("bstsimple.out");
        String line;
        while((line = scan.readLine()) != null){
            String[] parts = line.split(" ");
            String action = parts[0];
            int val = Integer.parseInt(parts[1]);

            if("insert".equals(action)){
                tree.insert(val);
            } else if("delete".equals(action)){
                tree.delete(val);
            } else if("exists".equals(action)){
                out.println(tree.contains(val));
            } else if("next".equals(action)){
                Element res = new Element(val);
                int m = tree.next(tree.root, val, res);
                if(m != val)
                    out.println(m);
                else
                    out.println("none");
            } else if("prev".equals(action)){
                Element prev = new Element(val);
                int m = tree.prev(tree.root, val, prev);
                if(m != val)
                    out.println(m);
                else
                    out.println("none");
            }
        }
        out.close();

    }


    private static class Element {

        Element left, right;
        int value;

        public Element(int value) {
            this.value = value;
        }

    }

    private static class Tree {
        private Element root;

        public void insert(int x){
            if(root == null){
                root = new Element(x);
                return;
            }
            Element elem = root;
            while (true){
                if(elem.value == x)
                    return;

                if(x < elem.value){
                    if(elem.left == null)
                        elem.left = new Element(x);
                    else
                        elem = elem.left;
                } else {
                    if(elem.right == null)
                        elem.right = new Element(x);
                    else
                        elem = elem.right;
                }
            }
        }

        public int next(Element root, int value, Element result) {
            if (root == null)
                return result.value;

            if (root.value <= value){
                next(root.right, value, result);
            } else {
                result.value = root.value;
                next(root.left, value, result);
            }

            return result.value;
        }

        public int prev(Element root, int value, Element result){
            if(root == null)
                return result.value;

            if(root.value >= value){
                prev(root.left, value, result);
            } else{
                result.value = root.value;
                prev(root.right, value, result);
            }

            return result.value;
        }

        public boolean contains(int x){
            if(root == null)
                return false;

            Element elem = root;
            while (elem != null){
                if(elem.value == x)
                    return true;
                if(x < elem.value)
                    elem = elem.left;
                else
                    elem = elem.right;
            }
            return false;
        }

        public void delete(int x){
            if(root == null)
                return;
            Element elem = root, parent = null;
            while (elem != null){
                if(elem.value == x)
                    break;
                parent = elem;
                if(x < elem.value)
                    elem = elem.left;
                else
                    elem = elem.right;
            }
            if(elem == null)
                return;

            if(elem.right == null){
                if(parent == null)
                    root = elem.left;
                else
                    if(elem == parent.left)
                        parent.left = elem.left;
                    else
                        parent.right = elem.left;
            } else {
                Element mostLeft = elem.right;
                parent = null;
                while (mostLeft.left != null) {
                    parent = mostLeft;
                    mostLeft = mostLeft.left;
                }

                if(parent != null)
                    parent.left = mostLeft.right;
                else
                    elem.right = mostLeft.right;

                elem.value = mostLeft.value;
            }
        }
    }
}
