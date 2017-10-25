import java.util.Comparator;
/**
 * This is a comparator class for sorting the items in the ArrayList's by name. 
 * 
 * @author Nabeel Hussain
 */
public class NameComparator implements Comparator<Thing>
{
	/***
	 * Compare method
	 * 
	 * @param n1 the first name to be compared
	 * @param n2 the second name to be compared
	 * @return an int that determine whether the first name being compared is greater, less than, or equal to the second name. 
	 */
    @Override
    public int compare(Thing n1, Thing n2)
    {
    	return n1.getName().compareToIgnoreCase(n2.getName());
    		
    }   
}

