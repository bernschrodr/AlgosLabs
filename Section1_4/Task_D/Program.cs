using System;
using System.IO;

namespace Task_D
{
    class Stack
    {

        public static int size;
        public int count = 0;
        int[] stack;
        public Stack(int n)
        {
            size = n;
            stack = new int[n];
        }

        public void push(int a)
        {
            stack[count] = a;
            if (count != size - 1)
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
            StreamReader sr = new StreamReader(@"postfix.in");
            StreamWriter sw = new StreamWriter(@"postfix.out");
            int tmp1 = 0, tmp2 = 0;
            char inp;
            Stack St = new Stack(150);
            string str = "";

            str = sr.ReadLine();

            for (int i = 0; i < str.Length; i++)
            {

                if (str[i] == ' ')
                    continue;

                if (str[i] == '+' || str[i] == '-' || str[i] == '*')
                {
                    switch (str[i])
                    {
                        case '+':
                            tmp2 = St.pop();
                            tmp1 = St.pop();
                            St.push(tmp1 + tmp2);
                            continue;
                        case '-':
                            tmp2 = St.pop();
                            tmp1 = St.pop();
                            St.push(tmp1 - tmp2);
                            continue;
                        case '*':
                            tmp2 = St.pop();
                            tmp1 = St.pop();
                            St.push(tmp1 * tmp2);
                            continue;
                    }
                    break;
                }

                inp = str[i];
                string tmp = "";
                tmp += inp;

                while ((str[i + 1] != ' ') && (i < str.Length))
                {
                    inp = str[++i];
                    tmp += inp;
                }

                St.push(int.Parse(tmp));

            }

            sw.WriteLine(St.pop());

            sr.Close();
            sw.Close();
        }
    }
}
