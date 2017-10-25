import java.util.Scanner;
/**
 * This is a child class of Thing, which will be used to hold the information of a Person and what skill they have
 * 
 * Date: 9/2/2017
 * @author Nabeel Hussain
 */
public class Person extends Thing {
	
	String skill;
	
	/**
	 * Constructor that initializes the Person class.
	 * 
	 * @param sc a Scanner to take advantage of super constructors, and any particular constructor focusing only on the additional elements of interest to that particular class.
	 */
	public Person(Scanner sc) {
		super(sc);
		
		if (sc.hasNext())
		{
			skill = sc.next();
		}

	}
	
	/**
	 * @return the skill of the person
	 */
    public String getSkills() {
        return skill;
    }

    
	/**
	 * @return the formatted string representation of the class
	 */
    @Override
    public String toString()
    {
        return "Person: " + super.toString() + " " + skill;
    }
}
