import java.util.Scanner;
/**
 * This is a child class of Thing, which will be used to hold the information of a Dock and what ship is anchored in it. 
 * 
 * Date: 9/2/2017
 * @author Nabeel Hussain
 */
public class Dock extends Thing {
	Ship ship;
	
	/**
	 * Constructor that initializes the Dock class.
	 * 
	 * @param sc a Scanner to take advantage of super constructors, and any particular constructor focusing only on the additional elements of interest to that particular class.
	 */	
	public Dock(Scanner sc) {
		super(sc);
	}
	
	/**
	 * @return the ship at the dock
	 */
	public Ship getShip() {
        return ship;
    }
    
	/**
	 * @return the formatted string representation of the class
	 */
    public String toString()
    {
        return "Dock: "+ super.toString() + "\n  Ship: " + ship.toString();
    }
}
