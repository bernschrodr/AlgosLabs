import java.io.*;

public class Task_D3 {

    public static class ListOfKeys {
        public ListOfKeys prev, next;
        public String set;

        public ListOfKeys(String key) {
            this.key = key;
            this.set = new HashTable(100000);
        }
    }

    public static class HashTable {
        public int size = 0;
        public ListOfKeys[] Table;

        public HashTable(int size) {
            this.size = size;
            Table = new ListOfKeys[size];
        }

        public HashTable get(String x){
            if(size <= 0)
                return null;

            LinkedMapNode temp = Table[getHash(key)];
            while (temp != null){
                if(temp.x.equals(x))
                    return temp.set;
                temp = temp.next;
            }
            return null;
        }

        public ListOfKeys GetList(String x){
            if(size <= 0)
                return null;
//просматриваем всю цепочку и возвращаем элемент со значением равным аргументу функции
            ListOfKeys temp = Table[GetHash(x)];
            while (temp != null){
                if(temp.key.equals(x))
                    return temp;
                temp = temp.next;
            }
            return null;
        }

        public boolean contains(String x){
            if(size <= 0)
                return false;
            ListOfKeys temp = Table[getHash(x)];
            while (temp != null){
                if(temp.x.equals(x))
                    return true;
                temp = temp.next;
            }
            return false;
        }

        public void insert(String x){
            if(contains(x))
                return;

            int hash = getHash(x);
            ListOfKeys temp = Table[hash];
            if(temp == null){
                Table[hash] = new ListOfKeys(x);
            } else {
                while (true){
                    if(temp.next == null) {
                        temp.next = new ListOfKeys(x);
                        temp.next.prev = temp;
                        break;
                    } else {
                        temp = temp.next;
                    }
                }
            }
            size++;
        }

        public void delete(String x) {
            if (size <= 0)
                return;

            int hash = GetHash(x);
            ListOfKeys temp = Table[hash];
            while (temp != null) {
                if (temp.key.equals(x)) {
                    if (temp.prev == null && temp.next == null) {
                        Table[hash] = null;
                    } else if (temp.prev == null) {
                        Table[hash] = temp.next;
                        temp.next.prev = null;
                    } else if (temp.next == null) {
                        temp.prev.next = null;
                    } else {
                        temp.prev.next = temp.next;
                        temp.next.prev = temp.prev;
                    }
                    size--;
                    return;
                } else {
                    temp = temp.next;
                }
            }
        }

        public void deleteValue(String x, String value){
            if(size <= 0)
                return;
            int hash = getHash(x);
            LinkedMapNode temp = Table[hash];
            while (temp != null){
                if(temp.x.equals(x)){
                    Table[hash].set.delete(value);
                    return;
                } else {
                    temp = temp.next;
                }
            }
        }

        public void put(String x, String value) {
            int hash = GetHash(x);
            ListOfKeys temp = Table[hash];

            if (temp == null) {
                size++;
                Table[hash] = new ListOfKeys(x, value);
                Table[hash].set.insert(value);
                return;
            }
            while (true) {
                if (temp.key.equals(x)) {
                    temp.insert(value);
                    break;
                } else if (temp.next == null) {
                    temp.next = new ListOfKeys(value);
                    temp.next.set.insert(value);
                    size++;
                    temp.next.prev = temp;
                    break;
                } else {
                    temp = temp.next;
                }
            }
        }

        public int GetStringHash(String x) {
            return GetHash(x) % Table.length;
        }

        public int GetHash(String x) {
            int h = 0;
            for (int i = 0; i < x.length(); i++)
                h = 33 * h + x.charAt(i);
            return Math.abs(h) % Table.length;
        }

    }

    public static void main(String[] args){
        HashTable htable = new HashTable(100000);

        try (BufferedReader fin = new BufferedReader(new FileReader("multimap.in"));
                BufferedWriter fout = new BufferedWriter(new FileWriter("multimap.out"))) {

            String str;

            while ((str = fin.readLine()) != null) {
                String[] inp = str.split(" ");

                switch (inp[0]) {
                case "put":
                    htable.put(inp[1], inp[2]);
                    continue;
                case "get":
                    HashTable out = htable.get(inp[1]);
                    if(out == null){
                        fout.write(0);
                        return;
                    }
                    fout.write(out.size + " ");
                    for (int i = 0; i < out.Table.length; i++) {
                        ListOfKeys temp = out.Table[i];
                        while (temp != null){
                            fout.write(temp.value + " ");
                            temp = temp.next;
                        }
                    }
                    fout.write("\n");
                    continue;
                case "delete":
                    htable.deleteValue(inp[1], inp[2]);
                    continue;
                case "deleteall":
                htable.delete(inp[1]);
                continue;
                }
            }
        }

        catch (IOException ex) {

            System.out.println(ex.getMessage());
        }
    }
}