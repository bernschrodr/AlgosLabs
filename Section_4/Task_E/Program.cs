using System;
using System.IO;

namespace Task_E
{
    class Queue
    {
        public struct Element
        {
            public Int32 indexStr, el;
            public Element(Int32 el, Int32 indexStr)
            {
                this.el = el;
                this.indexStr = indexStr;
            }
        }

        public static Int32 size;
        public Int32 head;
        public Int32 tail;
        Element[] queue;
        public Queue(Int32 n)
        {
            size = n;
            queue = new Element[n];
            head = n - 1;
            tail = head;

        }


        public void Push(Int32 InpElem, Int32 ind)
        {
            queue[tail] = new Element(InpElem, ind);

            if (tail != 0)
            {
                --tail;
            }

        }

        public string ExtractMin()
        {

            Int32 min = 2147483646;
            Int32 minIndex = 0;

            for (Int32 i = head; i > tail; --i)
            {
                Int32 val = queue[i].el;
                if (val < min)
                {
                    min = val;
                    minIndex = i;
                }
            }

            queue[minIndex].el = 2147483646;
            size--;

            if (min == 2147483646)
                return "*";
            else
                return min.ToString();

        }

        public void DecreaseKey(Int32 x, Int32 y)
        {
            for (Int32 i = head; i > tail; i--)
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
            StreamReader sr = new StreamReader("priorityqueue.in");
            StreamWriter sw = new StreamWriter("priorityqueue.out");
            const Int32 size = 1000001;
            Queue queue = new Queue(size);
            string str, str2, str3;
            Int32 indexstr = 0, pos = 0;

            while ((str = sr.ReadLine()) != null)
            {
                ++indexstr;
                if (str.IndexOf("push") != -1)
                {
                    pos = str.IndexOf(" ");
                    str2 = str.Substring(pos);
                    queue.Push(Int32.Parse(str2), indexstr);
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
                    queue.DecreaseKey(Int32.Parse(str2), Int32.Parse(str3));
                }

            }
            sw.Close();
            sr.Close();
        }
    }
}
