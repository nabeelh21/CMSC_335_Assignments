import java.util.Scanner;
/**
 * This is a child class of Ship, which will be used to hold the information of a Passenger Ship
 * 
 * Date: 9/2/2017
 * @author Nabeel Hussain
 */
public class PassengerShip extends Ship {
	
	int numberOfPassengers;
	int numberOfRooms;
	int numberOfOccupiedRooms;
	
	/**
	 * Constructor that initializes the PassengerShip class.
	 * 
	 * @param sc a Scanner to take advantage of super constructors, and any particular constructor focusing only on the additional elements of interest to that particular class.
	 */
	public PassengerShip (Scanner sc){
		super(sc);
		
		if (sc.hasNextInt())
		{
			numberOfPassengers = sc.nextInt();
		}
		if (sc.hasNextInt())
		{
			numberOfRooms = sc.nextInt();
		}
		if (sc.hasNextInt())
		{
			numberOfOccupiedRooms = sc.nextInt();
		}
		
	} // end end Scanner constructor
	
	
	/**
	 * @return the number of occupied rooms in the passenger ship
	 */
    public int getNumberOfOccupiedRooms() {
        return numberOfOccupiedRooms;
    }

	/**
	 * @return the number of passengers in the passenger ship
	 */
    public int getNumberOfPassengers() {
        return numberOfPassengers;
    }

	/**
	 * @return the number of rooms in the passenger ship
	 */
    public int getNumberOfRooms() {
        return numberOfRooms;
    }

	/**
	 * @return the formatted string representation of the class
	 */
	public String toString ()
	{
		String st = "Passenger ship: " + super.toString();
		
		if (jobs.size() == 0)
		{
			return st;
		}
		
		for (Job mj: jobs){
			st += "\n - " + mj;
		}
		
		return st;
	} // end method toString
}
