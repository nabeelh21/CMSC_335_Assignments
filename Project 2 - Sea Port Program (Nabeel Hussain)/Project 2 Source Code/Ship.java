import java.util.ArrayList;
import java.util.Scanner;
/**
 * This is a child class of Thing, which will be used to hold the information of a Ship, including its weight, length, width, and draft, among other things. 
 * 
 * Date: 9/2/2017
 * @author Nabeel Hussain
 */
public class Ship extends Thing {
	
	PortTime arrivalTime, dockTime;
	double weight, length, width, draft;
	ArrayList<Job> jobs = new ArrayList<>();
	
	/**
	 * Constructor that initializes the Ship class.
	 * 
	 * @param sc a Scanner to take advantage of super constructors, and any particular constructor focusing only on the additional elements of interest to that particular class.
	 */
	public Ship(Scanner sc)
	{
		super(sc);
		if (sc.hasNextDouble())
		{
			weight = sc.nextDouble();
		}
		if (sc.hasNextDouble())
		{
			length = sc.nextDouble();
		}
		if (sc.hasNextDouble())
		{
			width = sc.nextDouble();
		}
		if (sc.hasNextDouble())
		{
			draft = sc.nextDouble();
		}
	}
	
	/**
	 * @return the arrival time of the ship
	 */
	public PortTime getArrivalTime() {
        return arrivalTime;
    }

	/**
	 * @return the dock time of the ship
	 */
    public PortTime getDockTime() {
        return dockTime;
    }

	/**
	 * @return the draft of the ship
	 */
    public double getDraft() {
        return draft;
    }

	/**
	 * @return the length of the ship
	 */
    public double getLength() {
        return length;
    }

	/**
	 * @return the weight of the ship
	 */
    public double getWeight() {
        return weight;
    }

	/**
	 * @return the width of the ship
	 */
    public double getWidth() {
        return width;
    }

	/**
	 * @return the list of jobs on the ship
	 */
    public ArrayList<Job> getJobs() {
        return jobs;
    }

}
