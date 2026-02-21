package shuttlemaxpq;

//-----------------------------------------------------
//Title: Simulator class
//Author: Barış Tuncer
//Description: This class contains the main method and the simulation logic. It reads shuttle data from a file, then finds the minimum number
//of chargers required so that no shuttle waits longer than the specified maximum waiting time.
//-----------------------------------------------------


import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;

public class Simulator
{

	//--------------------------------------------------------
	// Summary: Prints all of the values of a Shuttle object
	// Precondition: a is a non-null Shuttle object
	// Postcondition: the shuttle's id, priority, arrival time and service time are printed
	//--------------------------------------------------------
	public static void printShuttle(Shuttle a)
	{
		System.out.println("  Shuttle " + a.getID() + " | Priority: " + a.getPrio() + " | Arrival: "  + a.getArrival() + " | Service: "  + a.getService());
				
	}


	//--------------------------------------------------------
	// Summary: Simulates the shuttle-charger assignment process.
	//      At each time step N, all shuttles whose arrival time <= N are
	//      inserted into the max priority queue. The highest-priority shuttle
	//      in the queue is assigned to the first available charger. If any
	//      shuttle waits longer than maxWaitingTime before being assigned,
	//      the simulation fails and returns false.
	// Precondition: maxWaitingTime >= 0, numChargers >= 1, arr is a non-empty array of Shuttle objects.        
	// Postcondition: returns true if all shuttles can be served within the waiting time limit using numChargers chargers; false otherwise.
	//--------------------------------------------------------
	public static boolean simulate(int maxWaitingTime, int numChargers, Shuttle[] arr)
	{
		int totalShuttles = arr.length;
		boolean[] inserted   = new boolean[totalShuttles];  // tracks the shuttles that are in the queue
		int shuttlesAssigned = 0;

		// chargerFreeAt[x] = the earliest time step at which charger x becomes available
		int[] chargerFreeAt = new int[numChargers];

		PriorityQueue pQueue = new PriorityQueue(totalShuttles);

		// timeOfEntry[i] = the simulation time when shuttle i entered the queue, used to calculate how long it waited before being assigned
		int[] timeOfEntry = new int[totalShuttles];

		int N = 0; // current simulation time (minutes past 12:00)

		while (shuttlesAssigned < totalShuttles)
		{
			// Step 1: enqueue every shuttle that has arrived by time N
			for (int j = 0; j < totalShuttles; j++)
			{
				if (!inserted[j] && arr[j].getArrival() <= N)
				{
					pQueue.insert(arr[j]);
					timeOfEntry[arr[j].getID() - 1] = N; // store entry time (IDs are 1-based)
					inserted[j] = true;
				}
			}

			// Step 2: assign waiting shuttles to free chargers
			for (int c = 0; c < numChargers; c++)
			{
				if (chargerFreeAt[c] <= N && !pQueue.isEmpty())
				{
					Shuttle s        = pQueue.delMax();
					int waitTime     = N - timeOfEntry[s.getID() - 1];

					// If this shuttle waited too long, the configuration fails
					if (waitTime > maxWaitingTime)
					{
						System.out.println("  [FAIL] Shuttle " + s.getID()
								+ " waited " + waitTime + " min (limit: " + maxWaitingTime + " min)"
								+ " with " + numChargers + " charger(s).");
						return false;
					}

					System.out.println("  [t=" + N + "] Shuttle " + s.getID()
							+ " (prio=" + s.getPrio() + ") assigned to charger " + (c + 1)
							+ " | waited " + waitTime + " min"
							+ " | finishes at t=" + (N + s.getService()));

					chargerFreeAt[c] = N + s.getService();
					shuttlesAssigned++;
				}
			}

			N++;

			// prevents infinite loop if there is any error
			if (N > 100000)
			{
				System.out.println("  [ERROR] Simulation exceeded time limit. Aborting.");
				return false;
			}
		}

		return true;
	}


	//--------------------------------------------------------
	// Summary: Main method. Reads shuttle data from a user-specified file,
	//          asks for a maximum waiting time, then increments the number of
	//          chargers from 1 upward until simulate() returns true. Prints
	//          the minimum number of chargers required.
	// Precondition: the input file must have an integer on the first line (number of shuttles) followed by one shuttle per line 
	//               in the format: id priority arrival service
	// Postcondition: the minimum charger count satisfying the constraint is printed.
	//--------------------------------------------------------
	public static void main(String[] args) throws FileNotFoundException
	{
		Scanner sc = new Scanner(System.in);

		// Reads file name
		System.out.print("Enter the name of the file: ");
		String fileNameInput = sc.nextLine().trim();
		File file  = new File(fileNameInput);
		Scanner fileIn = new Scanner(file);

		// Reads number of shuttles
		int numOfShuttles = fileIn.nextInt();
		fileIn.nextLine(); // consume the rest of the first line

		// Reads each shuttle line and creates Shuttle array
		Shuttle[] shuttleArray = new Shuttle[numOfShuttles];
		for (int i = 0; i < numOfShuttles; i++)
		{
			String line      = fileIn.nextLine();
			shuttleArray[i]  = new Shuttle(line);
		}

		// Prints the loaded shuttles
		System.out.println("\nLoaded " + numOfShuttles + " shuttle(s):");
		for (Shuttle s : shuttleArray)
			printShuttle(s);

		// Reads the maximum waiting time
		System.out.print("\nEnter the maximum waiting time (minutes): ");
		int maxWaitingTime = Integer.parseInt(sc.nextLine().trim());
		sc.close();

		// Finds the minimum number of chargers needed
		System.out.println("\nSearching for minimum number of chargers...\n");

		int chargers = 1;
		while (true)
		{
			System.out.println("--- Trying " + chargers + " charger(s) ---");
			if (simulate(maxWaitingTime, chargers, shuttleArray))
			{
				System.out.println("\n=== Result ===");
				System.out.println("Minimum number of chargers needed: " + chargers);
				System.out.println("All shuttles served within the " + maxWaitingTime
						+ "-minute waiting limit.");
				break;
			}
			chargers++;
		}
		
		
		
	}
	
	
	
}




