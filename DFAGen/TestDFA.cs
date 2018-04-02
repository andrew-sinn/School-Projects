namespace TestDFA
{
    class TestDFA
    {
        static void Main(string[]args)
        {
            NumberDFA dfa = new NumberDFA();
            Console.WriteLine(dfa.scan("1234"));
            Console.WriteLine(dfa.scan("1234.56"));
            Console.WriteLine(dfa.scan("123-­‐45"));
            dfa.setDebug(true);
            Console.WriteLine(dfa.scan("-­‐123"));
        }
    }
}