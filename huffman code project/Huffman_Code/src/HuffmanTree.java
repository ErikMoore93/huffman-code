/**
 * @author     Erik Moore <erikmoore93@yahoo.com>
 * @purpose    1.0   runs sets up and runs a HuffmanTree
 * @date        2013-02-5          
 * @usage		to run this program: $>java HuffmanApp main
 */
public class HuffmanTree {
	PriorityQ PQ;//a priority queue
	Tree Huffman_tree;//The huffman tree that generates the code
	String[] huffcode;//The code for each character
	public HuffmanTree(int[] f,char[] c)//HuffmanTree constructor
	{
		PQ = new PriorityQ(f.length);//set up PriorityQ
		huffcode = new String[f.length];//set up huffcode
		for (int times = 0;times <f.length;times++)
		{
			Node newNode = new Node();//sets up a new Node
			newNode.frequency = f[times];//adds frequency to new node
			newNode.value = c[times];//adds value to new node
			huffcode[times]="";//initializes huffcode
			if(f[times]!=0)//If character is in frequency queue
			{
				PQ.insert(newNode);//insert into Priority Queue
				
			}
		}
	}
	public void makeHuffmanTree()//makes the huffman tree
	{
		while((PQ.size())>1)//while more than one tree continue
		{
			Tree dum = new Tree();//set up a dummy tree
			dum = PQ.remove();//fetch first item in priority queue
			Tree dum2 = new Tree();//set up a second dummy tree
			dum2 = PQ.remove();//fetch second item in priority queue
			Node finNode = new Node();//get the new node to add to tree
			finNode.frequency = dum.getfreqr()+dum2.getfreqr();//finNodes frequency is the sum of the first 2 elements in the priority queue
			finNode.value = '+';
			Tree fin = new Tree();//make tree to insert back into priority queue
			fin.insert(finNode);//insert fin into the priority queue
			if((dum.getvaluer()=='+')&&(dum2.getvaluer()!='+'))//If value 1 is + and value 2 is not,insert 2nd and then 1st
			{
				fin.insert(dum2.getroot());
				fin.insert(dum.getroot());
			}
			else if((dum2.getvaluer()=='+')&&(dum.getvaluer()!='+'))//If value 2 is + and value 1 is not,insert 1st and then 2nd
			{
				fin.insert(dum.getroot());
				fin.insert(dum2.getroot());
			}
			else if(((dum.getvaluer()=='+')&&(dum2.getvaluer()=='+'))||((dum.getvaluer()!='+')&&(dum2.getvaluer()!='+')))
			{//If both are either + or not +, then
				if (dum.getfreqr()>dum2.getfreqr())// If 1st > 2nd, insert 2, then 1
				{
					fin.insert(dum2.getroot());
					fin.insert(dum.getroot());
				}
				else//If 2nd>1st insert 1, then 2
				{
					fin.insert(dum.getroot());
					fin.insert(dum2.getroot());
				}
			}
			PQ.insert(fin);//insert the tree into the priority queue
		}
		Huffman_tree = PQ.remove();//remove the only element in priority queue
		Huffman_tree.displayTree();//Show the final tree
	}
	public String encode(String original)//encode set-up
	{
		String code = "";//initial code is nothing
		encode(code,Huffman_tree.getroot());//Calls second encode statement (note this is overloaded)
		String coded ="";//initial coded is nothing
		for (int pos = 0;pos<original.length();pos++)//setup coded from original
		{
			char toint = original.charAt(pos);
			coded+=huffcode[((int)toint)-65];
		}
		for (int times = 0;times<huffcode.length;times++)//shows the character and it's code
		{
			if (huffcode[times]!="")
			{
				int c = times +65;
				System.out.println(((char)c)+" "+huffcode[times]);
			}
		}
		return coded;//returns coded
	}
	public void encode(String code,Node current)//recursive encode
	{
		if (current.leftChild!=null)//if there is a left
		{
			encode(code+"0",current.leftChild);//call encode with code+0, and go to left
		}
		if (current.rightChild!=null)//if there is a right
		{
			encode(code+"1",current.rightChild);//call encode with code+1, and go to right
		}
		else//otherwise it is a leaf
		{
			char toint = current.value;
			huffcode[((int)toint)-65] = code;//add current code to the huffcode table
		}
		return;
	}
	public String decode(String coded)//decode the string
	{
		Node current = Huffman_tree.getroot();//get the top root
		Node root = current;
		String decoded="";//set up decoded to nothing
		int times = 0;//counter variable
		while (times < coded.length())//while there are still new bits to decode
		{
			
			int dum = ((int)coded.charAt(times))-48;//sets dum to bit at times
			if((dum)==(0))//if char = 0
			{
				if(current.leftChild!=null)//if left exists
				{
					current = current.leftChild;//go left
					times++;//increment count
				}
				else//if left doesn't exist, restarts at root
				{
					decoded+= current.value;//add value to decoded
					current = root;
				}
			}
			else//if char = 1
			{
				if(current.rightChild!=null)//if right exists
				{	
					current = current.rightChild;//go right
					times++;//increment count
				}
				else//if right doesn't exist, restarts at root
				{
					decoded+= current.value;//add current value
					current = root;
				}
			}
		}
		decoded+= current.value;//add last value
		return decoded;//return decoded message
	}
	public void display()//display 
	{
			PQ.display();//displays priority queue
	}
}
