/**
 * @author     Erik Moore <erikmoore93@yahoo.com>
 * @purpose    1.0   priorityQ used by HuffmanTree program
 * @date        2013-02-5          
 * @usage		to run this program: $>java HuffmanApp main
 */
////////////////////////////////////////////////////////////////
class PriorityQ
{
	private int maxSize;//max  size of array
	private Tree[] queArray;//The priority queue
	private int nItems;//number of items
	//-------------------------------------------------------------
	public PriorityQ(int s) // constructor
	{
		maxSize = s;
		queArray = new Tree[maxSize];
		nItems = 0;
	}
	//-------------------------------------------------------------
	public void insert(Tree item) // insert pre-existing Tree
	{
		int j;
		if(nItems==0) // if no items,
			queArray[nItems++] = item; // insert at 0
		else // if items,
		{
			for(j=nItems-1; j>=0; j--) // start at end,
			{
				if( item.getfreq(item.getroot()) > queArray[j].getfreq(queArray[j].getroot()) ) // if new item frequency larger,
					queArray[j+1] = queArray[j]; // shift upward
				else // if smaller,
					break; // done shifting
			} // end for
			queArray[j+1] = item; // insert it
			nItems++;
		} // end else (nItems > 0)
	} // end insert()
	//-------------------------------------------------------------
		public void insert(Node n) // insert a pre-existing Node
		{
			Tree item = new Tree(); //Set up a new Letter filled from above
			item.insert(n);
			int j;
			if(nItems==0) // if no items,
				queArray[nItems++] = item; // insert at 0
			else // if items,
			{
				for(j=nItems-1; j>=0; j--) // start at end,
				{
					if( item.getfreq(item.getroot()) > queArray[j].getfreq(queArray[j].getroot()) ) // if new item frequency larger,
						queArray[j+1] = queArray[j]; // shift upward
					else // if smaller,
						break; // done shifting
				} // end for
				queArray[j+1] = item; // insert it
				nItems++;
			} // end else (nItems > 0)
		} // end insert()
	//-------------------------------------------------------------
	public Tree remove() // remove minimum item
	{ return queArray[--nItems]; }
	//-------------------------------------------------------------
	public Tree peekMin() // peek at minimum item
	{ return queArray[nItems-1]; }
	//-------------------------------------------------------------
	public boolean isEmpty() // true if queue is empty
	{ return (nItems==0); }
	//-------------------------------------------------------------
	public int size() // true if queue is empty
	{ return nItems; }
	//-------------------------------------------------------------
	public boolean isFull() // true if queue is full
	{ return (nItems == maxSize); }
	public void display()//display all trees in the priority queue
	{
		for(int times = 0;times<queArray.length;times++)//Goes through Priority Queue
		{
		    queArray[times].displayTree();
		}
	}
	//-------------------------------------------------------------
} // end class PriorityQ