using System;
using System.IO;

namespace Task_C
{
    class Stack
    {

        public static int size;
        public int count = 0;
        char[] stack;
        public Stack(int n)
        {
            size = n;
            stack = new char[n];
        }

        public void push(char a)
        {
            stack[count] = a;
            if (count != size - 1)
                ++count;

        }

        public int pop()
        {
            return stack[--count];
        }

        private char LastElement;
        public char last()
        {
            if (count > 0)
            {
                LastElement = stack[count - 1];
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
            const int size = 10001;
            Stack St = new Stack(size);
            int count=0;
            string bracket;

            while ((bracket = sr.ReadLine()) != null)
            {
                count++;
                for (int i = 0; i < bracket.Length; i++)
                {

                    if (i == 0)
                    {   
                        if (bracket[i] == ')' || bracket[i] == ']')
                        {
                            St.push(bracket[i]);
                            //sw.WriteLine("NO");
                            break;
                        }
                    }

                    else
                        if ((bracket[i] == ']') && (St.last() == '['))
                    {
                        St.pop();
                        continue;
                    }
                    else
                            if ((bracket[i] == ')') && (St.last() == '('))
                    {
                        St.pop();
                        continue;

                    }

                    St.push(bracket[i]);
                }

                if (St.last() == '0')
                    sw.WriteLine("YES");
                else
                    {
                        sw.WriteLine("NO");
                        St.count = 0;
                    }

            }

            sw.Close();
            sr.Close();


        }
    }
}
