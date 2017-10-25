import java.util.ArrayList;
import java.util.Scanner;

/**
 * This is a child class of Thing, which will be used to properly process and create the internal data structure from the text data file. 
 * 
 * @author Nabeel Hussain
 */
public class World extends Thing {
	
	ArrayList<SeaPort> ports = new ArrayList<SeaPort>();
	PortTime time;
	
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
    			assignPort(new SeaPort(sc));
    			break;
    		case "dock":
    			assignDock(new Dock(sc));
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
    			assignPerson(new Person(sc));
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
		
    	//If the search term is an integer number, then search for the items with matching index values. 
    	if(isInteger(searchTerm))
    	{
    		int st = Integer.parseInt(searchTerm);
    		        	
        	if(getShipByIndex(st) != null)
    		{
        		foundItems.add(getShipByIndex(st).toString());
    		}
        	if(getDockByIndex(st) != null)
    		{
        		foundItems.add(getDockByIndex(st).toString());
    		}
        	if(getSeaPortByIndex(st) != null)
    		{
        		foundItems.add(getSeaPortByIndex(st).toString());
    		}
        	if(getPersonByIndex(st) != null)
    		{
        		foundItems.add(getPersonByIndex(st).toString());
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
	 * Method to find a Ship by the given index number. 
	 * 
	 * @param x the index number to use to find the corresponding ship associated with it.  
	 */
    Ship getShipByIndex (int x)
    {
    	for(SeaPort msp: ports)
    	{
    		for (Ship ms: msp.ships)
    		{
    			if (ms.index == x)
    			{
    				return ms;
    			}
    		}
    	}
    	return null;
    } // end getShipByIndex
    
	/**
	 * Method to find a Dock by the given index number. 
	 * 
	 * @param x the index number to use to find the corresponding dock associated with it.  
	 */
    Dock getDockByIndex (int x)
    {
    	for(SeaPort msp: ports)
    	{
    		for (Dock ms: msp.docks)
    		{
    			if (ms.index == x)
    			{
    				return ms;
    			}
    		}
    	}
    	return null;
    } // end getShipByIndex
    
    
    
	/**
	 * Method to find a Person by the given index number. 
	 * 
	 * @param x the index number to use to find the corresponding person associated with it.  
	 */
    Person getPersonByIndex (int x)
    {
    	for(SeaPort msp: ports)
    	{
    		for (Person ms: msp.persons)
    		{
    			if (ms.index == x)
    			{
    				return ms;
    			}
    		}
    	}
    	return null;
    } // end getPersonByIndex
    
    
	/**
	 * Method to find a SeaPort by the given index number. 
	 * 
	 * @param x the index number to use to find the corresponding port associated with it.  
	 */
    SeaPort getSeaPortByIndex (int x)
    {
    	for(SeaPort msp: ports)
    	{
			if (msp.index == x)
			{
				return msp;
			}
   		
    	}
    	return null;
    } // end getSeaPortByIndex
    
    
	/**
	 * Method to link a ship to its parent:
	 * 
	 * @param ms the ship that will be used to link to its parent.  
	 */
    void assignShip (Ship ms)
    {
    	Dock md = getDockByIndex (ms.parent);
    	if (md == null) {
	    	getSeaPortByIndex (ms.parent).ships.add (ms);
	    	getSeaPortByIndex (ms.parent).que.add (ms);
	    	return;
    	}
    	md.ship = ms;
    	getSeaPortByIndex (md.parent).ships.add (ms);
    } // end method assignShip
    
    
	/**
	 * Method to link a person to its parent:
	 * 
	 * @param p the person that will be used to link to its parent.  
	 */
    void assignPerson(Person p)
    {
        for(SeaPort sp:ports)
        {
            if(sp.getIndex()==p.getParent())
            {
                sp.persons.add(p);
            }
        }
    } // end assignPerson
    
    
	/**
	 * Method to link a dock to its parent:
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
    }//end assignDock
	
	/**
	 * Method to link a port to its parent:
	 * 
	 * @param p the port that will be used to link to its parent.  
	 */
    void assignPort(SeaPort p)
    {
        ports.add(p);
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
