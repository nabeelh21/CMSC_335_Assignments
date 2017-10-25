import java.util.ArrayList;
import java.util.Scanner;
/**
 * This is a child class of Thing, which will be used to hold Job data.
 * 
 * Date: 9/2/2017
 * @author Nabeel Hussain
 */
public class Job extends Thing {
	
	double duration;
	ArrayList<String> requirements = new ArrayList<String>();
	
	/**
	 * Constructor that initializes the Thing class.
	 * 
	 * @param sc a Scanner to take advantage of super constructors, and any particular constructor focusing only on the additional elements of interest to that particular class.
	 */	
	public Job(Scanner sc) {
        super(sc);
    }
 
	/**
	 * @return the duration of the job
	 */	
    public double getDuration() {
        return duration;
    }


	/**
	 * @return the list of requirements of the job
	 */	
    public ArrayList<String> getRequirements() {
        return requirements;
    }

}
