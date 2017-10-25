import java.util.ArrayList;
import java.util.Scanner;
/**
 * This is a child class of Thing, which will be used to hold all the docks, ships, ships in que, and persons in a sea port. 
 * 
 * Date: 9/2/2017
 * @author Nabeel Hussain
 */
public class SeaPort extends Thing {
	
	ArrayList<Dock> docks = new ArrayList<Dock>();
	ArrayList<Ship> que = new ArrayList<Ship>();
	ArrayList<Ship> ships = new ArrayList<Ship>();
	ArrayList<Person> persons = new ArrayList<Person>();
	
	/**
	 * Constructor that initializes the SeaPort class.
	 * 
	 * @param sc a Scanner to take advantage of super constructors, and any particular constructor focusing only on the additional elements of interest to that particular class.
	 */	
    public SeaPort(Scanner sc) {
        super(sc);

    }
    
	/**
	 * @return the ArrayList of the docks
	 */
	public ArrayList<Dock> getDocks() {
        return this.docks;
    }
	
	/**
	 * @return the ArrayList of ships that are in que
	 */
	public ArrayList<Ship> getQue() {
        return this.que;
    }
	
	/**
	 * @return the ArrayList of the ships
	 */
	public ArrayList<Ship> getShips() {
        return this.ships;
    }
	
	/**
	 * @return the ArrayList of persons
	 */
	public ArrayList<Person> getPersons() {
        return this.persons;
    }
    
	/**
	 * @return the formatted string representation of the class
	 */
    public String toString () {
    	
        String st = "\n\nSeaPort: " + super.toString() + "\n";
        
        for (Dock md: docks){
        	st += "\n" + md;
        }
        
        st += "\n\n --- List of all ships in que:";       
        for (Ship ms: que ){
        	st += "\n   > " + ms;
        }
        
        st += "\n\n --- List of all ships:";
        for (Ship ms: ships){
        	st += "\n   > " + ms;
        }
        
        st += "\n\n --- List of all persons:";
        for (Person mp: persons){
        	st += "\n   > " + mp;
        }
 
        return st;
     } // end method toString
}
