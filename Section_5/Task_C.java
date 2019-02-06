import java.io.*;

public class Task_C {
    public static class ListOfKeys {
        public ListOfKeys prev, next;
        public String value;
        public String key;
        public ListOfKeys inPrev,inNext;

        public ListOfKeys(String key, String value) {
            this.key = key;
            this.value = value;
        }
    }

    public static class HashTable {
        public int size = 0;
        public ListOfKeys[] Table;
        public ListOfKeys Tail;

        public HashTable(int size) {
            this.size = size;
            Table = new ListOfKeys[size];
        }


        private void TryTail(ListOfKeys x) {
            if(Tail != null)
                Tail.inNext = x;
            x.inPrev = Tail;
            Tail = x;
        }

        public String get(String x) {
            if (size <= 0)
                return null;

            ListOfKeys temp = Table[GetHash(x)];
//просматриваем всю цепочку и возвращаем значение равное аргументу функции
            while (temp != null) {
                if (temp.key.equals(x))
                    return temp.value;
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
                    if(temp.inPrev == null && temp.inNext == null){
                        Tail = null;
                    } else if(temp.inPrev == null){
                        temp.inNext.inPrev = null;
                    } else if(temp.inNext == null){
                        temp.inPrev.inNext = null;
                        Tail = temp.inPrev;
                    } else {
                        temp.inPrev.inNext = temp.inNext;
                        temp.inNext.inPrev = temp.inPrev;
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
                TryTail(Table[hash]);
                return;
            }
            while (true) {
                if (temp.key.equals(x)) {
                    size++;
                    temp.value = value;
                    break;
                } else if (temp.next == null) {
                    ListOfKeys temp2 = new ListOfKeys(x, value);
                    size++;
                    temp.next = temp2;
                    temp.next.prev = temp;
                    TryTail(temp2);
                    break;
                } else {
                    temp = temp.next;
                }
            }
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

        try (BufferedReader fin = new BufferedReader(new FileReader("linkedmap.in"));
                BufferedWriter fout = new BufferedWriter(new FileWriter("linkedmap.out"))) {

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
                case "prev":
                ListOfKeys temp = htable.GetList(inp[1]);
                if (temp == null || temp.inPrev == null)
                    fout.write("none\n");
                else
                    fout.write(temp.inPrev.value + "\n");
                continue;
                case "next":
                ListOfKeys temp2 = htable.GetList(inp[1]);
                if (temp2 == null || temp2.inNext == null)
                    fout.write("none\n");
                else
                    fout.write(temp2.inNext.value + "\n");
                continue;
                }
            }
        }

        catch (IOException ex) {

            System.out.println(ex.getMessage());
        }
    }
}