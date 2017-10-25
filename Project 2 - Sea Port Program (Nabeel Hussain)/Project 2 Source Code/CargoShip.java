import java.util.Scanner;
/**
 * This is a child class of Ship, which will be used to hold the information of a Cargo Ship
 * 
 * Date: 9/2/2017
 * @author Nabeel Hussain
 */
public class CargoShip extends Ship{
	
	double cargoWeight;
	double cargoVolume;
	double cargoValue;
	
	
	/**
	 * Constructor that initializes the CargoShip class.
	 * 
	 * @param sc a Scanner to take advantage of super constructors, and any particular constructor focusing only on the additional elements of interest to that particular class.
	 */
	public CargoShip(Scanner sc) {
		super(sc);
		
		if (sc.hasNextDouble())
		{
			cargoWeight = sc.nextDouble();
		}
		if (sc.hasNextDouble())
		{
			cargoVolume = sc.nextDouble();
		}
		if (sc.hasNextDouble())
		{
			cargoValue = sc.nextDouble();
		}
	}
	
	/**
	 * @return the cargo value in the cargo ship
	 */
    public double getCargoValue() {
        return cargoValue;
    }

	/**
	 * @return the cargo volume in the cargo ship
	 */
    public double getCargoVolume() {
        return cargoVolume;
    }

	/**
	 * @return the cargo weight in the cargo ship
	 */
    public double getCargoWeight() {
        return cargoWeight;
    }

    
	/**
	 * @return the formatted string representation of the class
	 */
	public String toString ()
	{
		String st = "Cargo Ship: " + super.toString();
			
		return st;
	} // end method toString
		

}
