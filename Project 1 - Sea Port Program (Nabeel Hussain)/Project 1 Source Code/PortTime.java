/**
 * This class is used to hold the time information of when a ship has come into port and docked.
 * 
 * Date: 9/2/2017
 * @author Nabeel Hussain
 */
public class PortTime {
	
	int time;
	
	
	/**
	 * Constructor that initializes the time.
	 * 
	 * @param time the time a ship has ported. 
	 */
    public PortTime(int time) {
        this.time = time;
    }

	/**
	 * @return the port time
	 */
    public int getTime() {
        return time;
    }

}
