import java.util.Scanner;
/**
 * This is the Parent class, which contains the name, index, and parent information of all the items in the text file that will be read in.
 * All the items will contain these fields, and then have their own additional fields in their own sub classes. 
 * 
 * Date: 9/2/2017
 * @author Nabeel Hussain
 * 
 */
public class Thing implements Comparable<Thing>{
	String name;
	int index;
	int parent;

	/**
	 * Constructor that initializes the Thing class.
	 * 
	 * @param sc a Scanner to take advantage of super constructors, and any particular constructor focusing only on the additional elements of interest to that particular class.
	 */	
	public Thing(Scanner sc)
    {   
		// Each valid line should always contain the name first, then the index number, followed by the parent number, no matter what item/thing is being processed.
        if(sc != null)
        {
    		if (sc.hasNext())
    		{
    			name = sc.next();
    		}
    		if (sc.hasNextInt())
    		{
    			index = sc.nextInt();
    		}
    		if (sc.hasNextInt())
    		{
    			parent = sc.nextInt();
    		}
        }
    }
	
	/**
	 * Will return the index value of the item
	 * 
	 * @return the item's index number
	 */
	public int getIndex() {
        return index;
    }

	/**
	 * Will return the name of the item
	 * 
	 * @return the items name
	 */
    public String getName() {
        return name;
    }

	/**
	 * Will return the value of the index field
	 * 
	 * @return index number
	 */
    public int getParent() {
        return parent;
    }
	
	/**
	 * A comparator method, which I believe will be used in later projects. 
	 */
	@Override
	public int compareTo(Thing o) {
		return 0;
	}
	
	/**
	 * @return the formatted string representation of the class
	 */
	@Override
    public String toString()
    {
        return name + " " + index;
    }

}
