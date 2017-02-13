/**
 * @author     Erik Moore <erikmoore93@yahoo.com>
 * @purpose    1.0   runs HuffmanTree program
 * @date        2013-02-5          
 * @usage		to run this program: $>java HuffmanApp main
 * 			Note:This program checks if the entered String is of valid characters
 */
import java.io.*;
public class HuffmanApp 
{
// -------------------------------------------------------------
	public static void main(String[] args) throws IOException 
	{
		String original="";    //the original inputed string
		String coded="";       //the coded string
		HuffmanTree Hufftree;  //Huffmantree
		char ret = 92;   
		int[] frequency= new int[29];  //Frequency array
		char[] letters = new char[29]; //Array of letters corresponding to frequency array
		Hufftree = new HuffmanTree(frequency,letters);  //instantiates Hufftree
		boolean entered = false;   //check to see if String is entered before following operations
		boolean treebuilt = false;  //check to see if Tree is built before following operations
		boolean msgcoded = false;  //check to see if msg is coded before following operations
		while(true)   //repeated runs switch statement for menu
		{
			System.out.print("Enter first letter of Enter, ");  //Ask user for input
			System.out.print("show, code, or decode: ");
			int choice = getChar();   //Get user input
			
			switch(choice)//switch statement for input
			{
			case 'e':   //Enter string to be coded, also sets up frequency queue
				boolean valid = false;
				while(!valid)
				{
					String dum="";      //dummy variable for getting String
					for (int times = 0;times<frequency.length;times++) //Initializes loop for frequency and letters
					{
						int c = times +65;
						letters[times] = (char)c;
						frequency[times] = 0;
					}
					System.out.print("Enter character string for tree: (Use $ to end)");    //Get the String to code
					do                                            //repeats until finding a $ character
					{
						dum += getString();
						dum += ret;//adds the endline character
					}while(!(dum.contains("$")));
					//String formatting
					original = dum.substring(0,dum.indexOf("$"));  //Sets the string to just the String before the $.
					original = original.toUpperCase();   //Forces String to be uppercase
					original = original.replace(' ', '[');
					valid = true;
					for(int times = 0;times<original.length();times++)
					{
						if(!((64<original.charAt(times))&&(original.charAt(times)<94)))
						{
							valid = false;
							System.out.println("invalid character at position "+times+ " only characters A-Z,space,return, and linefeeds are valid");
						}
					}
				}
				for (int pos = 0;pos<original.length();pos++)   //Sorts the String into the frequency array
				{
					char toint = original.charAt(pos);
					frequency[((int)toint)-65]++;
				}
				System.out.println(original);   //Outputs formatted string
				for (int pos = 0;pos<frequency.length;pos++) //Shows Char array for the frequency array
				{
					System.out.print(letters[pos]+" ");
				}
				System.out.println();// carriage return from style
				for (int pos = 0;pos<frequency.length;pos++) //Shows the frequencies in the the frequency array
				{
					System.out.print(frequency[pos]+" ");
				}
				System.out.println();  //carriage return from style
				entered = true;//toggles entered
				treebuilt = false;//resets treebuilt
				msgcoded = false;//resets msgcoded
				break;
			case 's':   //Shows the Huffman Tree
				if(entered)//checks if String entered
				{
					Hufftree = new HuffmanTree(frequency,letters); //makes a PQ in the HuffmanTree given the frequency array and sets up HuffmanTree
					Hufftree.makeHuffmanTree(); //Makes the Huffman Tree
					treebuilt = true;//toggles treebuilt
				}
				else  //If not tells user
				{
					System.out.println("No String yet entered cannot build tree");
				}
				break;
			case 'c':   //Encodes the String that was entered, shows code table and coded String
				if(treebuilt)//Checks if Huffman tree was built
				{
					coded = Hufftree.encode(original);//gets coded message from HuffmanTree
					System.out.println("Coded message:\n"+coded); //Displays coded message
					msgcoded = true; //Toggles msgcoded
				}
				else  //If not tells user
				{
					System.out.println("Tree not yet built cannot encode");
				}
				break;
			case 'd':  //Decodes a previously coded message
				if(msgcoded) //Checks if String was encoded
				{
					System.out.println("Decoded msg:\n"+Hufftree.decode(coded));//displays decoded String returned from HuffmanTree
				}
				else //If not tells user
				{
					System.out.println("String not yet encoded, cannot decode");
				}
				break;  //Decodes the coded String, and shows decoded String
			default:    //Not a valid entry for the switch
				System.out.print("Invalid entry\n");
			}
		}

	}
// -------------------------------------------------------------
// -------------------------------------------------------------
	public static String getString() throws IOException//getString method
    {
		InputStreamReader isr = new InputStreamReader(System.in);
	    BufferedReader br = new BufferedReader(isr);
	    String s = br.readLine();
	    return s;
	}
// -------------------------------------------------------------	
	public static char getChar() throws IOException//getChar method
	{
		String s = getString();
		return s.charAt(0);
	}
// -------------------------------------------------------------	
}
