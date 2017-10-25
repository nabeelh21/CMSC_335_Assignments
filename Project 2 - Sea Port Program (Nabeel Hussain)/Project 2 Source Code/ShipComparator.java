import java.util.Comparator;
/**
 * This is a comparator class for sorting the ship attributes by weight, length, width, and draft.  
 * 
 * @author Nabeel Hussain
 */
public class ShipComparator implements Comparator<Ship>{
	
	private String sortType = "";
	
	/***
	 * Constructor
	 * @param sortType the attribute being used to sort the ships by
	 */
	public ShipComparator (String sortType) {
		this.sortType = sortType;
	}
	
	/***
	 * Compare method
	 * 
	 * @param s1 the first ship to be compared
	 * @param s2 the second ship to be compared
	 * @return an int that determines whether the first ships attribute is greater, less than, or equal to the second ships attribute. 
	 */
    @Override
    public int compare(Ship s1, Ship s2)
    {
    	switch(sortType)
    	{
    		case "Weight":
    			//compare ship weight
    	        if(s1.getWeight() > s2.getWeight())
    	            return 1;
    	        else if(s1.getWeight() < s2.getWeight())
    	            return -1;
    	        else
    	            return 0;
    		case "Length":
    		       //compare ship length
    	        if(s1.getLength() > s2.getLength())
    	            return 1;
    	        else if(s1.getLength() < s2.getLength())
    	            return -1;
    	        else
    	            return 0;
    		case "Width":
    		       //compare ship width
    	        if(s1.getWidth() > s2.getWidth())
    	            return 1;
    	        else if(s1.getWidth() < s2.getWidth())
    	            return -1;
    	        else
    	            return 0;
    		default:
    			//compare ship draft
    	        if(s1.getDraft() > s2.getDraft())
    	            return 1;
    	        else if(s1.getDraft() < s2.getDraft())
    	            return -1;
    	        else
    	            return 0;
    	} 
    }   
}

