using System;
using System.IO;

namespace Task_C
{
    class Stack{

        public static int size;
        public int count = 0;
        char[] stack;
        public Stack(int n)
        {
            size=n;
            stack = new char[n];
        }
        
        public void push(char a)
        {
            stack[count] = a;
            if(count != size-1)
                ++count;
                
        }

        public int pop()
        {
            return stack[--count];
        }

        private char LastElement;
        public char last()
        {
            if(count>0)
            {
                LastElement = stack[count-1];
                return LastElement;
            }
            else
                return '0';
        }
    
    }

    class Program
    {
        static void Main(string[] args)
        {
            StreamReader sr = new StreamReader(@"brackets.in");
            StreamWriter sw = new StreamWriter(@"brackets.out");
            const int size = 1000001;
            
            Stack Inp = new Stack(size);

            string bracket;

    
            while((bracket = sr.ReadLine()) != null)
            {
                
                for(int i = 0; bracket[i] != (char)13; i++)
                {
                    Inp.push(bracket[i]);

                    if(i == 0)
                    {
                        if (bracket[i] == ')' || bracket[i] == ']')
                        {
                            sw.WriteLine("NO");
                            break;
                        }
                    }
                    else
                        if((bracket[i] == ']') && (Inp.last() == '['))
                        {
                            Inp.pop();
                            Inp.pop();
                        }
                        else
                            if((bracket[i] == ')') && (Inp.last() == '('))
                            {
                                Inp.pop();
                                Inp.pop();
                            }
                                else
                                {
                                  sw.WriteLine("NO");
                                  break;
                                }
                }

                if(Inp.last() == '0')
                    sw.WriteLine("YES");
            
            }
            
            sw.Close();
            sr.Close();
            
            
        }
    }
}
