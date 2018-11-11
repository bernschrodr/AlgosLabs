using System;
using System.IO;

namespace Task_E
{
    class Queue
    {

        public struct Element
        {
            public int indexStr, el;
            public Element(int el, int indexStr)
            {
                this.el = el;
                this.indexStr = indexStr;
            }
        }

        public static int size;
        public int head;
        public int tail;
        Element[] queue;
        public Queue(int n)
        {
            size = n;
            queue = new Element[n];
            head = n - 1;
            tail = head;

        }


        public void Push(int InpElem, int ind)
        {
            queue[tail] = new Element(InpElem, ind);

            if (tail != 0)
            {
                --tail;
            }

        }

        public string ExtractMin()
        {

            int min = 2147483647;
            int minIndex = 0;

            for (int i = head; i > tail; --i)
            {
                int val = queue[i].el;
                if (val < min)
                {
                    min = val;
                    minIndex = i;
                }
            }

            queue[minIndex].el = 2147483647;
            size--;

            if (min == 2147483647)
                return "*";
            else
                return min.ToString();

        }

        public void DecreaseKey(int x, int y)
        {
            for (int i = head; i >= tail; i--)
            {
                if (queue[i].indexStr == x)
                    queue[i].el = y;
            }
        }

    }
    class Program
    {
        static void Main()
        {
            StreamReader sr = new StreamReader(@"priorityqueue.in");
            StreamWriter sw = new StreamWriter(@"priorityqueue.out");
            const int size = 1000001;
            Queue queue = new Queue(size);
            string str, str2, str3;
            int indexstr = 0, pos = 0;

            while ((str = sr.ReadLine()) != null)
            {
                ++indexstr;
                if (str.IndexOf("push") != -1)
                {
                    pos = str.IndexOf(" ");
                    str2 = str.Substring(pos);
                    queue.Push(int.Parse(str2), indexstr);
                }

                if (str.IndexOf("extract-min") != -1)
                {
                    sw.WriteLine(queue.ExtractMin());
                }

                if ((pos = str.IndexOf("decrease-key")) != -1)
                {

                    str2 = str.Substring(str.IndexOf(" ")+1);
                    str3 = str2.Substring(str2.IndexOf(" ") + 1);
                    str2 = str2.Remove(str2.IndexOf(" "));
                    queue.DecreaseKey(int.Parse(str2), int.Parse(str3));
                }

            }
            sw.Close();
            sr.Close();
        }
    }
}
