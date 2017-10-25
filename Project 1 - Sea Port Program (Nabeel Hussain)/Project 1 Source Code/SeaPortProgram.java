import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
/**
 * This is the main class that will implement a GUI application, which reads in a data file and displays the contents onto the gui.  
 * 
 * Name: Nabeel Hussain
 * Class: CMSC 335
 * Professor: Amitava Karmaker
 * Project 1
 * Date: 9/2/2017
 * 
 * @author Nabeel Hussain
 */

@SuppressWarnings("serial")
public class SeaPortProgram extends JFrame
{	
	// Create an instance of the World class, which will be used to execute the necessary requirements of the program. 
	World world = new World(null);
	
	public SeaPortProgram() throws IOException
	{			       
	    // Creates the textfield which will allow the user to enter a search query
		JTextField searchTextField = new JTextField();
		searchTextField.setPreferredSize(new Dimension(180, 25));
		
		// Button that will be used to search for the query a user enters
		JButton searchButton = new JButton("Search");
		
        // Create a panel, which will be used to contain the textField and button for entering a search of the text file that's read in 
	    JPanel searchRowPanel = new JPanel();
	    searchRowPanel.add(searchTextField , BorderLayout.EAST);
	    searchRowPanel.add(searchButton , BorderLayout.EAST);
	    
	    
	    // Create a topSection panel, which will be used to nest the topRowPanel and bottomRowPanel on top of each other in a column
	    JPanel topSection = new JPanel();
	    topSection.setBorder ( new TitledBorder ( new EtchedBorder (), " " ) );
	    topSection.setLayout(new BoxLayout(topSection, BoxLayout.Y_AXIS));
	    topSection.add(searchRowPanel);

	    
	    
	    JTextArea textAreaOuput = new JTextArea ();
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
			
			readFile(selectedFile,textAreaOuput );
			
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
	    displayFrame.setPreferredSize(new Dimension(600, 328));
	    displayFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    // Add the main pane holding the contents of the GUI onto the diplayFrame, which will display it. 
	    displayFrame.add(mainPane,BorderLayout.CENTER);

	    displayFrame.pack ();
	    displayFrame.setLocationRelativeTo(null);
	    displayFrame.setVisible(true); 
	}
	
	/**
	 * A method that will read in a file and display the contents onto the specified JTextArea.
	 * 
	 * @param f the file to be read
	 * @param output the text area to display the formatted internal data structure. 
	 */	
	public void readFile(File f, JTextArea output)
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
            output.setText(world.toString());
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
