# Electric Vehicle Charging Station Optimization
A Java simulation that determines the minimum number of chargers needed to serve a fleet of electric vehicles within a given maximum waiting time. Uses a max-oriented binary heap priority queue.

# Working Principles
Each vehicle(shuttle) has four attributes:  
 - ID  
 - Priority — higher priority vehicles are assigned to chargers first  
 - Arrival Time — the minute the vehicle arrives and enters the queue  
 - Service Time — how many minutes the vehicle needs on a charger  

The simulator increments time minute by minute. As vehicles arrive, they are inserted into a max priority queue ordered by priority. 
Whenever a charger becomes free, the highest-priority vehicle in the queue is assigned to it. 
If any vehicle waits longer than the specified limit before being assigned, the simulation fails and retries with one additional charger.

# Input File Format
The first line is the number of vehicles. Each following line defines one vehicle, setting its ID, priority, arrival time and waiting time.  
  
Example (also uploaded in the files):  
5  
1 8 0 10  
2 6 3 15  
3 9 5 12  
4 7 7 8  
5 5 10 20  

# Notes:
 - The program will ask for the name of the input file and the desired maximum waiting time.
 - time is measured in mins (not important, may be any measurement unit)
 - the max waiting time applies to the time a vehicle spends waiting in the queue, not the time it charges
