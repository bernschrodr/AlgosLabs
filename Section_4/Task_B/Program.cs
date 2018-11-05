using System;
using System.IO;

namespace Task_B
{
    class Queue{

        public static int size;
        public int head;
        public int tail;
        int[] queue;
        public Queue(int n)
        {
            size=n;
            queue = new int[n];
            head = n;
            tail = head - 1;
           
        }
        
        
        public void push(int a)
        {
            queue[tail] = a;
            if(tail != 0)
                --tail;
                
        }

        public int pop()
        {
            if(head != tail)
            return queue[--head];
            else
            return -1;
        }
    
    }

    class Program
    {
        static void Main(string[] args)
        {
            StreamReader sr = new StreamReader(@"queue.in");
            StreamWriter sw = new StreamWriter(@"queue.out");
            const int size = 1000001;
            int n = int.Parse(sr.ReadLine());
            Queue Inp = new Queue(size);

            char operations;

            string str;
            while((str = sr.ReadLine()) != null)
            { 
                string[] keys = str.Split(new char[] { ' ' }, StringSplitOptions.RemoveEmptyEntries);
                operations = char.Parse(keys[0]);
               
                if(operations == '+')
                {
                    Inp.push(int.Parse(keys[1]));
                }

                if(operations == '-')
                {
                    sw.WriteLine(Inp.pop());
                }
            }
            
            sw.Close();
            sr.Close();
            
            
        }
    }
}

