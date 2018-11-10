using System;
using System.IO;

namespace FirstApp
{
    class Stack{

        public static int size;
        public int count = 0;
        int[] stack;
        public Stack(int n)
        {
            size=n;
            stack = new int[n];
        }
        
        public void push(int a)
        {
            stack[count] = a;
            if(count != size-1)
                ++count;
                
        }

        public int pop()
        {
            return stack[--count];
        }
    
    }

    class Program
    {
        static void Main(string[] args)
        {
            StreamReader sr = new StreamReader(@"stack.in");
            StreamWriter sw = new StreamWriter(@"stack.out");
            const int size = 1000001;
            int n = int.Parse(sr.ReadLine());
            Stack Inp = new Stack(size);

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
