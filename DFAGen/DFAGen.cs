using System;

/**
 * This class will check program arguments for the DFAGen program
 * 
 * @author Andrew Sinn
 */

namespace DFAGen
{
    public class DFAGen
    {

        public static void Main(String[] args)
        {
            if (args.Length != 2 && args.Length != 3)
            {
                Console.Error.WriteLine("Usage: DFAGen [-d] DFAProgramFile C#ClassName");
                return;
            }

            DFAParser parser;      //The DFA Parser.
            if (args.Length == 2)
            {
                parser = new DFAParser(args[0], args[1], false);
            }
            else
                parser = new DFAParser(args[1], args[2], true);
            parser.parseDFA();
        }
    }
}

