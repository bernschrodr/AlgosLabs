import java.io.*;

private class Task_B {
    public static class HashTable {
        public int size = 0;
        public ListOfKeys[] Table;

        public HashTable(int size) {
            this.size = size;
            Table = new ListOfKeys[size];
        }

        public static class ListOfKeys {
            public ListOfKeys prev, next;
            public String value;
            public String key;

            public ListOfKeys(String key, String value) {
                this.key = key;
                this.value = value;
            }
        }

        public String get(String x) {
            if (size <= 0)
                return null;

            ListOfKeys temp = Table[GetHash(x)];

            while (temp != null) {
                if (temp.key.equals(x))
                    return temp.value;
                temp = temp.next;
            }
            return null;
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

        public void put(String x, String value) {
            int hash = GetHash(x);
            ListOfKeys temp = Table[hash];

            if (temp == null) {
                size++;
                Table[hash] = new ListOfKeys(x, value);
                return;
            }
            while (true) {
                if (temp.key.equals(x)) {
                    size++;
                    temp.value = value;
                    break;
                } else if (temp.next == null) {
                    size++;
                    temp.next = new ListOfKeys(x, value);
                    temp.next.prev = temp;
                    break;
                } else {
                    temp = temp.next;
                }
            }
        }

        public int GetHash(String x) {
            int h = 0;
            for (int i = 0; i < x.length(); i++)
                h = 31 * h + x.charAt(i);
            return Math.abs(h) % Table.length;
        }
    }

    public static void main(String[] args) {

        HashTable htable = new HashTable(100000);

        try (BufferedReader fin = new BufferedReader(new FileReader("map.in"));
                BufferedWriter fout = new BufferedWriter(new FileWriter("map.out"))) {

            String str;

            while ((str = fin.readLine()) != null) {
                String[] inp = str.split(" ");

                switch (inp[0]) {
                case "put":
                    htable.put(inp[1], inp[2]);
                    continue;
                case "get":
                    String out = htable.get(inp[1]);
                    if(out == null)
                    fout.write("none" + "\n");
                    else
                    fout.write(out + "\n");
                    continue;
                case "delete":
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