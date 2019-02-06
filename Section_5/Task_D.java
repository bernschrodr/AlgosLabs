import java.io.*;

public class Task_D {

    public static int getStringHash(String x){
        int h = 0;
        for(int i = 0; i < x.length(); i++)
            h = 31 * h + x.charAt(i);
        return Math.abs(h);
    }

    public static class ListOfKeys {

        public ListOfKeys prev, next;
        public String value;

        public ListOfKeys(String value) {
            this.value = value;
        }
    }

    public static class HashTable {
        public ListOfKeys[] Table;
        public int size = 0;

        public HashTable(int loadFactor){
            Table = new ListOfKeys[loadFactor];
        }

        public int getHash(String value){
            return getStringHash(value) % Table.length;
        }

        public boolean contains(String value){
            if(size <= 0)
                return false;
            ListOfKeys temp = Table[getHash(value)];
            while (temp != null){
                if(temp.value.equals(value))
                    return true;
                temp = temp.next;
            }
            return false;
        }

        public void insert(String value){
            if(contains(value))
                return;

            int hash = getHash(value);
            ListOfKeys temp = Table[hash];
            if(temp == null){
                Table[hash] = new ListOfKeys(value);
            } else {
                while (true){
                    if(temp.next == null) {
                        temp.next = new ListOfKeys(value);
                        temp.next.prev = temp;
                        break;
                    } else {
                        temp = temp.next;
                    }
                }
            }
            size++;
        }

        public void delete(String value){
            if(size <= 0)
                return;

            int hash = getHash(value);
            ListOfKeys temp = Table[hash];
            while (temp != null){
                if(temp.value.equals(value)){
                    if(temp.prev == null && temp.next == null){
                        Table[hash] = null;
                    } else if(temp.prev == null){
                        Table[hash] = temp.next;
                        temp.next.prev = null;
                    } else if(temp.next == null){
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
    }
    
    public static class ListsOfLinkedMap {

        public ListsOfLinkedMap prev, next;
        public String key;
        public HashTable set;

        public ListsOfLinkedMap(String key) {
            this.key = key;
            this.set = new HashTable(1 << 8);
        }
    }
    
    public static class MultiMap {
        public ListsOfLinkedMap[] Table;
        public int size = 0;

        public MultiMap(int loadFactor){
            Table = new ListsOfLinkedMap[loadFactor];
        }

        public int getHash(String x){
            return getStringHash(x) % Table.length;
        }

        public void put(String key, String value){
            int hash = getHash(key);
            ListsOfLinkedMap temp = Table[hash];
            if(temp == null) {
                Table[hash] = new ListsOfLinkedMap(key);
                Table[hash].set.insert(value);
                size++;
                return;
            }
            while (true){
                if(temp.key.equals(key)){
                    temp.set.insert(value);
                    break;
                } else if(temp.next == null) {
                    temp.next = new ListsOfLinkedMap(key);
                    temp.next.set.insert(value);
                    temp.next.prev = temp;
                    size++;
                    break;
                } else {
                    temp = temp.next;
                }
            }
        }

        public HashTable get(String key){
            if(size <= 0)
                return null;

            ListsOfLinkedMap temp = Table[getHash(key)];
            while (temp != null){
                if(temp.key.equals(key))
                    return temp.set;
                temp = temp.next;
            }
            return null;
        }

        public void delete(String key){
            if(size <= 0)
                return;
            int hash = getHash(key);
            ListsOfLinkedMap temp = Table[hash];
            while (temp != null){
                if(temp.key.equals(key)){
                    if(temp.prev == null && temp.next == null){
                        Table[hash] = null;
                    } else if(temp.prev == null){
                        Table[hash] = temp.next;
                        temp.next.prev = null;
                    } else if(temp.next == null){
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

        public void deleteValue(String key, String value){
            if(size <= 0)
                return;
            int hash = getHash(key);
            ListsOfLinkedMap temp = Table[hash];
            while (temp != null){
                if(temp.key.equals(key)){
                    Table[hash].set.delete(value);
                    return;
                } else {
                    temp = temp.next;
                }
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader fin = new BufferedReader(new FileReader(new File("multimap.in")));
        MultiMap htable = new MultiMap(1 << 20);

        PrintWriter fout = new PrintWriter("multimap.out");
        String str;
        while ((str = fin.readLine()) != null){
            String[] inp = str.split(" ");
            switch(inp[0]){
                case "put":
                htable.put(inp[1], inp[2]);
                continue;
                
                case "get" :
                HashTable temp1 = htable.get(inp[1]);
                if(temp1 == null)
                fout.write(0 + "\n");
                else {
                fout.write(temp1.size + " ");
                for (int i = 0; i < temp1.Table.length; i++) {
                    ListOfKeys temp2 = temp1.Table[i];
                    while (temp2 != null){
                        fout.write(temp2.value + " ");
                        temp2 = temp2.next;
                    }
                } fout.write('\n');}
                continue;

                case "delete" :
                htable.deleteValue(inp[1], inp[2]);
                continue;

                case "deleteall":
                htable.delete(inp[1]);
                continue;
        }
    }
        fout.close();
        fin.close();
    }


}
