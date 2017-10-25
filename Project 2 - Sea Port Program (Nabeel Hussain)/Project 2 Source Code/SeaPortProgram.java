import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

/**
 * This is the main class that extends Project 1 to write more efficient code when constructing the internal data structures from the data file. 
 * 
 * Name: Nabeel Hussain
 * Class: CMSC 335
 * Professor: Amitava Karmaker
 * Project 2
 * Date: 9/14/2017
 * 
 * @author Nabeel Hussain
 */

@SuppressWarnings("serial")
public class SeaPortProgram extends JFrame
{	
	// Create an instance of the World class, which will be used to execute the necessary requirements of the program. 
	private World world = new World(null);
	
	private JTextField searchTextField;
	private JButton searchButton;
	private JLabel sortShipsMessageLabel; 
	private JLabel sortAllItemsMessageLabel;
    private JRadioButton sortByWeightButton;  
    private JRadioButton sortByLengthButton;   
    private JRadioButton sortByWidthButton; 
    private JRadioButton sortByDraftButton;  

    private ButtonGroup radioButtonGroup;  // To group radio buttons
    
    private JComboBox<String> jcb;
    
    private JTextArea textAreaOuput;
    
    
	public SeaPortProgram() throws IOException
	{
		
	    // Creates the textfield which will allow the user to enter a search query
	    searchTextField = new JTextField();
	    searchTextField.setPreferredSize(new Dimension(180, 25));
		
		// Button that will be used to search for the query a user enters
		searchButton = new JButton("Search");
			
	    // Create a panel, which will be used to contain the textField and button for entering a search of the text file that's read in 
	    JPanel searchRowPanel = new JPanel();
	    searchRowPanel.add(searchTextField , BorderLayout.EAST);
	    searchRowPanel.add(searchButton , BorderLayout.EAST);
	    
	    sortShipsMessageLabel = new JLabel("Sort Ships By: ");
	    sortByWeightButton = new JRadioButton("Weight");
	    sortByLengthButton = new JRadioButton("Length");
	    sortByWidthButton = new JRadioButton("Width");
	    sortByDraftButton = new JRadioButton("Draft");


	    // Group the radio buttons.
	    radioButtonGroup = new ButtonGroup();
		radioButtonGroup.add(sortByWeightButton);
		radioButtonGroup.add(sortByLengthButton);
		radioButtonGroup.add(sortByWidthButton);
		radioButtonGroup.add(sortByDraftButton);
		
		// Add action listeners to the radio buttons.
		sortByWeightButton.addActionListener(new RadioButtonListener());
		sortByLengthButton.addActionListener(new RadioButtonListener());
		sortByWidthButton.addActionListener(new RadioButtonListener());
		sortByDraftButton.addActionListener(new RadioButtonListener());


        // Create a panel, which will be used to contain the radio button options for sorting the ships in port que ArrayList by weight, length, width, and draft. 
	    JPanel sortingShipsRowPanel = new JPanel();
	    sortingShipsRowPanel.add(sortShipsMessageLabel);
	    sortingShipsRowPanel.add(sortByWeightButton);
	    sortingShipsRowPanel.add(sortByLengthButton);
	    sortingShipsRowPanel.add(sortByWidthButton);
	    sortingShipsRowPanel.add(sortByDraftButton);
	    
	    
	    sortAllItemsMessageLabel = new JLabel("Sort All Items By: ");

	    jcb = new JComboBox<String>();
	    jcb.addItem("");
		jcb.addItem("Name");
		
		 // Event handler for when the user selects the JComboBox option "Name", to sort all the items by.
		 //Add Actionlistner to listen for change
	     jcb.addActionListener(new ActionListener()
	     {
          @Override
          public void actionPerformed(ActionEvent e)
          {  
        	  if (jcb.getSelectedItem() == "Name")
 	         {
        		 // clear the radio button selection, if the user selects the option to sort all the items by name. 
        		 radioButtonGroup.clearSelection();
        		 
        		 sortAllItemsByName();
 	         }
          }
      });
		

		JPanel sortingAllItemsRowPanel = new JPanel();
		sortingAllItemsRowPanel.add(sortAllItemsMessageLabel);
		sortingAllItemsRowPanel.add(jcb);

		
	    // Create a topSection panel, which will be used to nest the search Row Panel and Sorting options next to each other. 
	    JPanel topSection = new JPanel();
	    topSection.setBorder ( new TitledBorder ( new EtchedBorder (), " " ) );
	    topSection.setLayout(new BoxLayout(topSection, BoxLayout.Y_AXIS));
	    topSection.add(searchRowPanel);
	    topSection.add(sortingShipsRowPanel);
	    topSection.add(sortingAllItemsRowPanel);


	    
	    textAreaOuput = new JTextArea ();
	    textAreaOuput.setBackground(Color.white);
	    textAreaOuput.setEditable ( false ); // set textArea non-editable
	    
	    JScrollPane bottomSection = new JScrollPane(textAreaOuput);

	    
		// Will display a window box allowing the user to select a file from their computer to open, in order to read its data. 
		JFileChooser fileChooser = new JFileChooser(".");
		int status = fileChooser.showOpenDialog(null);
		if (status == JFileChooser.APPROVE_OPTION)
		{
			// extract the file selected by the user
			File selectedFile = fileChooser.getSelectedFile();
			
			readFile(selectedFile);	
		}
		
		
		// Event handler for when the user clicks on the "Search" button.
	    searchButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				String result = "";
				
				textAreaOuput.setText("");
				
				// extract the query that needs to be searched for from the searchTextField
				String searchTerm = searchTextField.getText();
				
				ArrayList<String> foundItems = world.searchResult(searchTerm);
				
				
				
				//Convert the ArrayList containing all the found items from the search into a formatted String, so it can be displayed in the gui nicely. 
		        for(String s: foundItems)
		        {
		        	result += s.toString() + "\n";
		        }
		        
				textAreaOuput.setText(result);
			}
		});
	    
	    
	        		
	    // Create a main pane that will hold all the sections of the GUI in a column 
	    JPanel mainPane = new JPanel();
	    mainPane.setLayout(new BoxLayout(mainPane, BoxLayout.Y_AXIS));
	    mainPane.add( topSection,BorderLayout.NORTH );
	    mainPane.add(bottomSection,BorderLayout.SOUTH );

	  
		// Create a frame that will be used to properly display all the components in the GUI 
	    JFrame displayFrame = new JFrame ();
	    displayFrame.setTitle("Sea Port Program");
	    displayFrame.setPreferredSize(new Dimension(600, 450));
	    displayFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    // Add the main pane holding the contents of the GUI onto the diplayFrame, which will display it. 
	    displayFrame.add(mainPane,BorderLayout.CENTER);

	    displayFrame.pack ();
	    displayFrame.setLocationRelativeTo(null);
	    displayFrame.setVisible(true); 
	}
	
	   /**
	    *  Private inner class that handles the event when the user clicks one of the radio buttons.
	    */
	   private class RadioButtonListener implements ActionListener
	   {
	      public void actionPerformed(ActionEvent e)
	      {  
	    	  String sortInput = "";
	    	  String resultText = "";
	    	  
	    	  // If one of the radio button options are selected for sorting the ships, then show the default blank option for the JComboBox.
	    	  // It should only be selected to display "Name", if we want to sort all the items by name.  
	    	  jcb.setSelectedIndex(0);
	    	  
	         // Determine the button that was clicked and perform the selected sort.
	         if (e.getSource() == sortByWeightButton)
	         {
				sortInput = "Weight";
				resultText = sortByShipAttribute(sortInput);
	         
	         }
	         else if (e.getSource() == sortByLengthButton)
	         {
				sortInput = "Length";
				resultText = sortByShipAttribute(sortInput);
	         }
	         else if (e.getSource() == sortByWidthButton)
	         {
				sortInput = "Width";
				resultText = sortByShipAttribute(sortInput);
	         }
	         else if (e.getSource() == sortByDraftButton)
	         {
				sortInput = "Draft";
				resultText = sortByShipAttribute(sortInput);
	         }
	         
	         textAreaOuput.setText(resultText);
	      }
	   }
	   
	/***
	 * Sorts the data of the ships by there weight, length, width, or draft attributes. 
	 * 
	 * @param sortInput
	 * @return
	 */
	public String sortByShipAttribute(String sortType) {
		
		String result = "";
		ArrayList<Ship> ships = new ArrayList<>();
		
		for (SeaPort msp : world.ports) {
			for (Ship ms : msp.ships) {
				ships.add(ms);
			}
		}
		
		if (sortType.equals("Weight"))
		{
			Collections.sort(ships, new ShipComparator("Weight"));
		} else if (sortType.equals("Length"))
		{
			Collections.sort(ships, new ShipComparator("Length"));
		} else if (sortType.equals("Width"))
		{
			Collections.sort(ships, new ShipComparator("Width"));
		} else if (sortType.equals("Draft"))
		{
			Collections.sort(ships, new ShipComparator("Draft"));
		}
		
		for (Ship ship : ships) {
			result += ship.toString() + "\n";
		}
		
		return result;
	}
		
	/***
	 * Sort all the items in the ArrayLists of the data structure by name.
	 */
	public void sortAllItemsByName() {
		
		String result = "";
		
		// Will go through each SeaPort added to our data structure and sort all of their ArrayLists by name for docks, ships, persons, and que.
		for (SeaPort msp : world.ports)
		{
			Collections.sort(msp.getDocks(), new NameComparator());
			Collections.sort(msp.getShips(), new NameComparator());
			Collections.sort(msp.getPersons(), new NameComparator());
			Collections.sort(msp.getQue(), new NameComparator());
		}
		
		textAreaOuput.setText(world.toString());
	}
	
	/**
	 * A method that will read in a file and display the contents onto the specified JTextArea.
	 * 
	 * @param f the file to be read
	 * @param output the text area to display the formatted internal data structure. 
	 */	
	public void readFile(File f)
    {
        try 
        {
            @SuppressWarnings("resource")
			BufferedReader inputFile = new BufferedReader(new FileReader(f));
            
            while(inputFile.ready())
            {
                String str = inputFile.readLine().trim();

                if(!str.startsWith("//"))
                {
                	world.process(str);
                }               
            }
                         		
            textAreaOuput.setText(world.toString());
        } 
        catch (Exception e) 
        {
            System.out.println(e+"-----");
        }
    }
	
	
	/***
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException
	{
		new SeaPortProgram();
	}

}
