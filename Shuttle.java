package shuttlemaxpq;

//-----------------------------------------------------
//Title: Shuttle class
//Author: Barış Tuncer
//Description: This class represents the shuttle object that will be used in the program with its constructor and get methods
//-----------------------------------------------------

public class Shuttle
{
	private int id, prio, arrival, service;


	//--------------------------------------------------------
	// Summary: Constructor for Shuttle objects. Splits the input string by spaces and assigns each token to id, prio, arrival, and service respectively.
	// Precondition: s is a non-null String with 4 space separated integers
	// Postcondition: a Shuttle object with the parsed values is created
	//--------------------------------------------------------
	public Shuttle(String s)
	{
		String[] arr = s.trim().split("\\s+");
		this.id      = Integer.parseInt(arr[0]);
		this.prio    = Integer.parseInt(arr[1]);
		this.arrival = Integer.parseInt(arr[2]);
		this.service = Integer.parseInt(arr[3]);
	}


	//--------------------------------------------------------
	// Summary: Returns the ID of the shuttle
	// Precondition: none
	// Postcondition: returns the id
	//--------------------------------------------------------
	public int getID()
	{
		return id;
	}


	//--------------------------------------------------------
	// Summary: Returns the priority of the shuttle
	// Precondition: none
	// Postcondition: returns the priority
	//--------------------------------------------------------
	public int getPrio()
	{
		return prio;
	}


	//--------------------------------------------------------
	// Summary: Returns the arrival time of the shuttle
	// Precondition: none
	// Postcondition: returns the arrival time
	//--------------------------------------------------------
	public int getArrival()
	{
		return arrival;
	}


	//--------------------------------------------------------
	// Summary: Returns the service time of the shuttle
	// Precondition: none
	// Postcondition: returns the service time
	//--------------------------------------------------------
	public int getService()
	{
		return service;
	}
	
	
	
	
}


