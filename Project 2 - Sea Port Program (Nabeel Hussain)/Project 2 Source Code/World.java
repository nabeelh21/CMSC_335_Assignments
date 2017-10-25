import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * This is a child class of Thing, which will be used to properly process and create the internal data structure from the text data file.
 * For project 2,  It will use instances of the Hash Map class to more efficiently search for items by there index's.
 * 
 * @author Nabeel Hussain
 */
public class World extends Thing {
	
	ArrayList<SeaPort> ports = new ArrayList<SeaPort>();
	PortTime time;
	
	// Will use Hash Maps to link and store ships, docks, seaports, and people with their index, so that looking them up in a search will be done more efficiently.
	// These instances should be passed as explicit parameters to other methods used when reading/processing the data file.
	HashMap<Integer, Ship> hms = new HashMap<Integer, Ship>();
	HashMap<Integer, Dock> hmd = new HashMap<Integer, Dock>();
	HashMap<Integer, SeaPort> hmsp = new HashMap<Integer, SeaPort>();
	HashMap<Integer, Person> hmp = new HashMap<Integer, Person>();

	
	//An ArrayList to contain all the found items from a search
	ArrayList<String> foundItems = new ArrayList<String>();

	
	/**
	 * Constructor that initializes the World class.
	 * 
	 * @param sc a Scanner to take advantage of super constructors, and any particular constructor focusing only on the additional elements of interest to that particular class.
	 */	
	public World(Scanner sc) {
		super(sc);
	}
	
	/**
	 * @return an ArrayList of sea ports from the data file that was read in
	 */
	public ArrayList<SeaPort> getPorts() {
        return ports;
    }

    
	/**
	 * Method to handle a line from the file. 
	 * 
	 * @param st a line from the file to be processed into the internal data structure.  
	 */
    @SuppressWarnings("resource")
	void process (String st) {
    	// System.out.println ("Processing >" + st + "<");
    	Scanner sc = new Scanner (st);
    	
    	if (!sc.hasNext()){
    		return;
    	}
    	
    	switch (sc.next())
    	{
    		case "port":
    			SeaPort sp = new SeaPort(sc);
    			assignPort(sp);
    			break;
    		case "dock":
    			Dock d = new Dock(sc);
    			assignDock(d);
    			break;
    		case "ship":
    			Ship s = new Ship(sc);
    			assignShip(s);
    			break;
    		case "pship":
    			Ship ps = new PassengerShip(sc);
    			assignShip(ps);
    			break;
    		case "cship":
    			Ship cs = new CargoShip(sc);
    			assignShip(cs);
    			break;
    		case "person":
    			Person p = new Person(sc);
    			assignPerson(p);
    			break;
    	}
    }
    
    
    public static boolean isInteger(String s) {
        try { 
            Integer.parseInt(s); 
        } catch(NumberFormatException e) { 
            return false; 
        } catch(NullPointerException e) {
            return false;
        }
        // only got here if we didn't return false
        return true;
    }
    
	/**
	 * Method to handle a search by the user. 
	 * 
	 * @param searchTerm the search item that the user is trying to find and have displayed.
	 * @return an ArrayList containing all the found items containing the search term    
	 */
    ArrayList<String> searchResult(String searchTerm)
    {
    	// Each time the searchResult method is called, make sure to clear the foundItems list, because a new search will be performed with the new search term. 
		foundItems = new ArrayList<String>();
		
    	// If the search term is an integer number, then search for the items with matching index values. 
		// I have eliminated the getDockByIndex, getShipByIndex, getSeaPortByIndex, and getPersonByIndex methods used to search for the items by their index value.
		// Instead, the call to these methods have been replaced by the get method of the HashMap associated with each type of thing.
		// The purpose of this is to change the operation of searching for an item with a particular index from an O(N) operation, ie searching through the entire
		// data structure to see if the code can find the parent index parameter, to an O(1) operation, a hash map lookup.
    	if(isInteger(searchTerm))
    	{
    		int st = Integer.parseInt(searchTerm);
    		        	
        	if(hms.get(st) != null)
    		{
        		foundItems.add(hms.get(st).toString());
    		}
        	if(hmd.get(st) != null)
    		{
        		foundItems.add(hmd.get(st).toString());
    		}
        	if(hmsp.get(st) != null)
    		{
        		foundItems.add(hmsp.get(st).toString());
    		}
        	if(hmp.get(st) != null)
    		{
        		foundItems.add(hmp.get(st).toString());
    		}	
    	}
    	//Otherwise if it's not an integer, then it must be a String, so then we can search for name or skill.
    	else{
    		
    		//If the search term matches a skill, then return the persons that have that skill.
    		if(getPersonsBySkill(searchTerm) != null)
    		{
    			getPersonsBySkill(searchTerm);
    			
    		}
    		// otherwise try searching for the search term by name. 
    		else
    		{
    			
        		if(getShipsByName(searchTerm) != null)
        		{
        			getShipsByName(searchTerm);
        		}
            	if(getDocksByName(searchTerm) != null)
        		{
            		getDocksByName(searchTerm);
        		}
            	if(getSeaPortsByName(searchTerm) != null)
        		{
            		getSeaPortsByName(searchTerm);
        		}
            	if(getPersonsByName(searchTerm) != null)
        		{
            		getPersonsByName(searchTerm);
        		}		
    		}	
    	}
		return foundItems;	
    }
    
	/**
	 * Method to find a Person by their skill. 
	 * 
	 * @param skill the skill associated wiht the person.  
	 */
    Person getPersonsBySkill (String skill)
    {
    	for(SeaPort msp: ports)
    	{
    		for (Person ms: msp.persons)
    		{
    			if (ms.skill.equals(skill))
    			{
    				foundItems.add(ms.toString());
    			}
    		}
    	}
    	return null;
    } // end getPersonBySkill
    
    
	/**
	 * Method to link a ship to its parent, and adding the ship to its correct HashMap.
	 * 
	 * @param ms the ship that will be used to link to its parent.  
	 */
    void assignShip (Ship ms)
    {
    	Dock md = hmd.get(ms.parent);
    	if (md == null) {
	    	hmsp.get(ms.parent).ships.add(ms);
	    	hmsp.get(ms.parent).que.add(ms);
	    	
	    	// Add the ship to the HashMap
	    	hms.put(ms.getIndex(), ms);
	    	return;
    	}
    	else{
	    	md.ship = ms;
	    	hmsp.get(md.parent).ships.add(ms);
	    	
	    	// Add the ship to the HashMap
	    	hms.put(ms.getIndex(), ms);
    	}
    	
    } // end method assignShip
    
    
	/**
	 * Method to link a person to its parent, and adding the person to its correct HashMap.
	 * 
	 * @param p the person that will be used to link to its parent.  
	 */
    void assignPerson(Person p)
    {
        for(SeaPort sp:ports)
        {
            if(sp.getIndex()== p.getParent())
            {
                sp.persons.add(p);
            }
        }
        
        // Add the person to the HashMap
        hmp.put(p.getIndex(), p);
    } // end assignPerson
    
    
	/**
	 * Method to link a dock to its parent, and adding the dock to its correct HashMap.
	 * 
	 * @param d the dock that will be used to link to its parent.  
	 */
    void assignDock(Dock d)
    {
        for(SeaPort sp:ports)
        {
            if(sp.getIndex()==d.getParent())
            {
                sp.docks.add(d);
            }
        }
        
        // Add the dock to the HashMap
        hmd.put(d.getIndex(), d);
    }//end assignDock
	
	/**
	 * Method to link a port to its parent, and adding the port to its correct HashMap. 
	 * 
	 * @param p the port that will be used to link to its parent.  
	 */
    void assignPort(SeaPort p)
    {
        ports.add(p);
        
     // Add the port to the HashMap
        hmsp.put(p.getIndex(), p);
    }// end method assignPort
    
    
	/**
	 * Method to find a Ship by its name. 
	 * 
	 * @param name ( the name to use to find the corresponding ship associated with it.  
	 */
    Ship getShipsByName (String name)
    {
    	for(SeaPort msp: ports)
    	{
    		for (Ship ms: msp.ships)
    		{
    			if (ms.name.equals(name))
    			{
    				foundItems.add(ms.toString());
    			}
    		}
    	}
    	return null;
    } // end getShipsByName
    
    
	/**
	 * Method to find a Dock by its name. 
	 * 
	 * @param name the name to use to find the corresponding dock associated with it.  
	 */
    Dock getDocksByName (String name)
    {
    	for(SeaPort msp: ports)
    	{
    		for (Dock ms: msp.docks)
    		{
    			if (ms.name.equals(name))
    			{
    				foundItems.add(ms.toString());
    			}
    		}
    	}
    	return null;
    } // end getDocksByName
    
	/**
	 * Method to find a SeaPort by its name. 
	 * 
	 * @param name the name to use to find the corresponding sea port associated with it.  
	 */
    SeaPort getSeaPortsByName (String name)
    {
    	for(SeaPort msp: ports)
    	{
			if(msp.name.equals(name))
			{
				foundItems.add(msp.toString());
			}

    	}
    	return null;
    } // end getSeaPortsByName
    
	/**
	 * Method to find a Person by its name. 
	 * 
	 * @param name the name to use to find the corresponding person associated with it.  
	 */
    Person getPersonsByName (String name)
    {
    	for(SeaPort msp: ports)
    	{
    		for (Person ms: msp.persons)
    		{
    			if (ms.name.equals(name))
    			{
    				foundItems.add(ms.toString());
    			}
    		}
    	}
    	return null;
    } // end getPersonsByName
	
    
    
	/**
	 * @return the formatted string representation of the class
	 */
    public String toString()
    {
        String str = "";
        for(SeaPort s:ports)
        {
            str += s.toString() + "\n";
        }
        return str;
    }

}
