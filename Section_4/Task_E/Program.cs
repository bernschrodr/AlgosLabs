using System;
using System.IO;

namespace Task_E
{

    class Queue
    {

        public struct Element
        {
            public int indexStr,el,prev,next,indexElem;
            public Element(int el, int indexElem, int indexStr)
            {
                this.el = el;
                this.indexElem = indexElem;
                this.prev = indexElem-1;
                this.next = indexElem+1;
                this.indexStr = indexStr;
            }
        }

        public static int size;
        public int head;
        public int tail;
        public int count = 0;
        public Element min = new Element(2147483646, 0, 0);
        Element[] queue;
        public Queue(int n)
        {
            size = n;
            queue = new Element[n];
            head = n-1;
            tail = head - 1;

        }


        public void Push(int InpElem, int i)
        {   
            queue[tail] = new Element(InpElem,count++,i);
            if (queue[tail].el < min.el)
                    min = queue[tail];

            if (tail != 0)
            {
                --tail;
            }
                
            

        }

        public string ExtractMin()
        {   
            if(min.prev == -1 && min.next == -1)
            return "*";

            if(min.prev == min.next && min.next == min.prev)
            {
                min.prev = -1; 
                min.next = -1;
            }
            else
            {
            queue[min.next].prev = min.prev;
            queue[min.prev].next = min.next;
            }
            return this.min.el.ToString();
        }

        public void DecreaseKey(int x, int y)
        {
            for(int i = head; i > tail; i--)
            {
                if (queue[i].indexStr == x)
                    queue[i].el = y;
            }
        }

    }
    class Program
    {
        static void Main(string[] args)
        {
            StreamReader sr = new StreamReader(@"priorityqueue.in");
            StreamWriter sw = new StreamWriter(@"priorityqueue.out");
            const int size = 1000001;
            Queue queue = new Queue(size);
            string str,str2,str3;
            int indexstr=0,pos=0;

            while((str = sr.ReadLine()) != null)
            {   
                ++indexstr;
                if(str.IndexOf("push") != -1)
                {
                    pos = str.IndexOf(" ");
                    str2 = str.Substring(pos+1);
                    queue.Push(int.Parse(str2),indexstr);
                }

                if(str.IndexOf("extract-min") != -1)
                {
                    sw.WriteLine(queue.ExtractMin());
                }

                if((pos = str.IndexOf("decrease-key")) != -1)
                {
                    
                    str2 = str.Substring(str.IndexOf(" ")+1);
                    str3 = str2.Substring(str2.IndexOf(" ")+1);
                    str2 = str2.Remove(1);
                    queue.DecreaseKey(int.Parse(str2),int.Parse(str3));
                }

            }
            sw.Close();
            sr.Close();
        }
    }
}
