package shuttlemaxpq;

//-----------------------------------------------------
//Title: Priority Queue class
//Author: Barış Tuncer
//Description: This class represents the max oriented priority queue that will be used throughout the program along with its basic methods
//-----------------------------------------------------


public class PriorityQueue<Key extends Comparable<Key>>
{
	private Shuttle[] pq;
	private int N;


	//--------------------------------------------------------
	// Summary: Constructor for the priority queue
	// Precondition: capacity is an int
	// Postcondition: creates a priority queue that uses the Shuttle object
	//--------------------------------------------------------
	public PriorityQueue(int capacity)
	{
		pq =  new Shuttle[capacity + 1];
	}


	//--------------------------------------------------------
	// Summary: Checks if the priority queue is empty
	// Precondition: none
	// Postcondition: returns true if empty, false otherwise
	//--------------------------------------------------------
	public boolean isEmpty()
	{
		return N == 0;
	}


	//--------------------------------------------------------
	// Summary: Returns the current size of the priority queue
	// Precondition: none
	// Postcondition: returns the number of elements in the queue
	//--------------------------------------------------------
	public int size()
	{
		return N;
	}


	//--------------------------------------------------------
	// Summary: Inserts a shuttle object into the priority queue and swims it up to its correct position
	// Precondition: key is a Shuttle object
	// Postcondition: shuttle is inserted and heap order is restored
	//--------------------------------------------------------
	public void insert(Shuttle key)
	{
		pq[++N] = key;
		swim(N);
	}


	//--------------------------------------------------------
	// Summary: Removes and returns the maximum priority shuttle
	// Precondition: priority queue is not empty
	// Postcondition: max shuttle is removed, heap order is restored
	//--------------------------------------------------------
	public Shuttle delMax()
	{
		Shuttle max = pq[1];
		exch(1, N--);
		sink(1);
		pq[N + 1] = null;
		return max;
	}


	//--------------------------------------------------------
	// Summary: Swims up the element at index k to its correct position
	// Precondition: k is an index in the heap
	// Postcondition: heap order is restored
	//--------------------------------------------------------
	private void swim(int k)
	{
		while (k > 1 && less(k / 2, k))
		{
			exch(k, k / 2);
			k = k / 2;
		}
	}


	//--------------------------------------------------------
	// Summary: Sinks down the element at index k to its correct position
	// Precondition: k is an index in the heap
	// Postcondition: heap order is restored
	//--------------------------------------------------------
	private void sink(int k)
	{
		while (2 * k <= N)
		{
			int j = 2 * k;
			if (j < N && less(j, j + 1)) j++;
			if (!less(k, j)) break;
			exch(k, j);
			k = j;
		}
	}


	//--------------------------------------------------------
	// Summary: Compares the priority values of two shuttles in the heap
	// Precondition: i and j are valid indexes
	// Postcondition: returns true if pq[i] has lower priority than pq[j]
	//--------------------------------------------------------
	private boolean less(int i, int j)
	{
		return pq[i].getPrio() < pq[j].getPrio();
	}


	//--------------------------------------------------------
	// Summary: Exchanges two elements in the heap array
	// Precondition: i and j are valid indexes
	// Postcondition: the two elements swap positions
	//--------------------------------------------------------
	private void exch(int i, int j)
	{
		Shuttle t = pq[i];
		pq[i] = pq[j];
		pq[j] = t;
	}
	
	
	
	
}



