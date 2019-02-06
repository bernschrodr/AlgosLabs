import java.io.*;

private class Task_A {
    public static class HashTable {
        public int size = 0;
        public ListOfKeys[] Table;

        public HashTable(int size) {
            this.size = size;
            Table = new ListOfKeys[size];
        }

        public static class ListOfKeys {
            public ListOfKeys prev, next;
            public int key;

            public ListOfKeys(int key) {
                this.key = key;
            }
        }

        public boolean exists(int x) {
            if (size <= 0)
                return false;
            ListOfKeys temp = Table[GetHash(x)];
            if (temp == null)
                return false;
            if (temp.key == x)
                return true;
            else
                while (temp.next != null) {
                    temp = temp.next;
                    if (temp.key == x)
                        return true;

                }
            return false;
        }

        public void delete(int x) {
            if (size <= 0)
                return;

            if (exists(x) == false)
                return;

            int hash = GetHash(x);
            ListOfKeys temp = Table[hash];
            while (temp != null) {
                if (temp.key == x) {
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

        public void insert(int x) {
            if (exists(x) == false) {

                int hash = GetHash(x);
                ListOfKeys temp = Table[hash];
                if (temp == null) {
                    Table[hash] = new ListOfKeys(x);
                    return;
                }
                while (true) {
                    if (temp.next == null) {
                        temp.next = new ListOfKeys(x);
                        temp.next.prev = temp;
                        break;
                    } else
                        temp = temp.next;
                }

                size++;
            } else
                return;

        }

        public int GetHash(int key) {
            key = ((key >> 16) ^ key) * 0x2BCCBEA5;
            key = ((key >> 16) ^ key) * 0x2BCCBEA5;
            key = (key >> 16) ^ key;
            return Math.abs(key) % Table.length;
        }
    }

    public static void main(String[] args) {

        HashTable htable = new HashTable(1000001);

        try (BufferedReader fin = new BufferedReader(new FileReader("set.in"));
                BufferedWriter fout = new BufferedWriter(new FileWriter("set.out"))) {
            String str;

            while ((str = fin.readLine()) != null) {
                String[] inp = str.split(" ");
                int val = Integer.valueOf(inp[1]);

                switch (inp[0]) {
                case "insert":
                    htable.insert(val);
                    continue;
                case "exists":
                    if (htable.exists(val) == true)
                        fout.write("true\n");
                    else
                        fout.write("false\n");
                    continue;
                case "delete":
                    htable.delete(val);
                    continue;
                }
            }
        }

        catch (IOException ex) {

            System.out.println(ex.getMessage());
        }
    }
}