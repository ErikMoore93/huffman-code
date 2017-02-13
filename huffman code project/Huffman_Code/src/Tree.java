/**
 * @author     Erik Moore <erikmoore93@yahoo.com>
 * @purpose    1.0   Tree used by HuffmanTree program
 * @date        2013-02-5          
 * @usage		to run this program: $>java HuffmanApp main
 */
import java.util.*; // for Stack class
////////////////////////////////////////////////////////////////
class Node
{
	public char value; // data item (key)
	public int frequency; // data item
	public Node leftChild; // this node's left child
	public Node rightChild; // this node's right child
	public void displayNode() // display ourself
	{
		System.out.print('{');
		System.out.print(value);
		System.out.print(", ");
		System.out.print(frequency);
		System.out.print("} ");
	}
} // end class Node
////////////////////////////////////////////////////////////////
class Tree
{
	private Node root; // first node of tree
	// -------------------------------------------------------------
	public Tree() // constructor
	{ root = null; } // no nodes in tree yet
	//-------------------------------------------------------------
	public void insert(Node newNode)
	{
		if(root==null) // no node in root
			root = newNode;
		else // root occupied
		{
			Node current = root; // start at root
			Node parent = null;
			while(true) // (exits internally)
			{
				if (parent == null)//if parent was empty fill it
				{
					parent = current;
				}
				if (parent.leftChild==null)//if no left go left
				{
					parent = current;
					if(newNode.frequency < current.frequency) // go left?
					{
						current = current.leftChild;
						if(current == null) // if end of the line,
						{ // insert on left
							parent.leftChild = newNode;
							return;
						}
					} // end if go left
					else // or go right?
					{
						current = current.rightChild;
						if(current == null) // if end of the line
						{ // insert on right
							parent.rightChild = newNode;
							return;
						}
					} // end else go right
				}
				else//Otherwise go right
				{
					if(parent.rightChild == null) // if end of the line
					{ // insert on right
						parent.rightChild = newNode;
						return;
					}
					parent = current;
					return;
				}
			} // end while
		} // end else not root
	} // end insert()
	// -------------------------------------------------------------
	

	// -------------------------------------------------------------
	public void displayTree()//display the Tree
	{
		Stack globalStack = new Stack();
		globalStack.push(root);
		int nBlanks = 32;
		boolean isRowEmpty = false;
		System.out.println(
				"......................................................");
		while(isRowEmpty==false)
		{
			Stack localStack = new Stack();
			isRowEmpty = true;
			for(int j=0; j<nBlanks; j++)
				System.out.print(' ');
			while(globalStack.isEmpty()==false)
			{
				Node temp = (Node)globalStack.pop();
				if(temp != null)
				{
					System.out.print(temp.value+""+temp.frequency);
					localStack.push(temp.leftChild);
					localStack.push(temp.rightChild);
					if(temp.leftChild != null ||
							temp.rightChild != null)
						isRowEmpty = false;
				}
				else
				{
					System.out.print("--");
					localStack.push(null);
					localStack.push(null);
				}
				for(int j=0; j<nBlanks*2-2; j++)
					System.out.print(' ');
			} // end while globalStack not empty
			System.out.println();
			nBlanks /= 2;
			while(localStack.isEmpty()==false)
				globalStack.push( localStack.pop() );
		} // end while isRowEmpty is false
		System.out.println(
				"......................................................");
	} // end displayTree()
	// -------------------------------------------------------------
	public int getfreq(Node current)//return the 
	{
		return current.frequency;
	}
	public int getfreqr()//returns root frequency
	{
		return root.frequency;
	}
	public int getvaluer()//returns roots value
	{
		return root.value;
	}
	public Node getroot()//returns root
	{
		return root;
	}
} // end class Tree
////////////////////////////////////////////////////////////////