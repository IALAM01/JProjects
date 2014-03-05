import javax.swing.*;
import java.util.regex.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class FileMenuHandler implements ActionListener {
	public int lengthCount = 0;
	public TextArea left = new TextArea();
	RomanNumeralGUI gui;
	public LinkedList <RomanNumeral> rnList = new LinkedList<RomanNumeral>();
 	public String inputWord;
    public ListIterator rnli;
 	
   /**
    * Constructs the FileMenuHandler
    * 
    * @param thisGUI The GUI on which FileMenuHandler will be used.
    */
   public FileMenuHandler (RomanNumeralGUI thisGUI) {
      gui = thisGUI;
   }
   
   /**
    * actionPerformed is one of the default methods of ActionListener.
    * Here it handles Open, Add, Delete, Sorted, Unsorted and Quit.
    * 
    * Clicking "Open" will call a method "openFile()", which will open a new window to choose a file. Check the openFile() method for more info.
    * 
    * Clicking "Add" will open a dialog box in which the user can enter either a Roman or Arabic numeral. All Arabic numerals are simply printed to the text area "left" as a Roman Numeral.
    * 
    * Clicking "Delete" will reorder the list as unsorted (or in the order the list was made) and open a dialog box asking for the index of the Roman numeral the user wants to delete.
    * 
    * Clicking "Sorted" will sort the list from lowest Roman Numeral to highest Roman Numeral.
    * 
    * Clicking "Unsorted" will sort the list "as is"; meaning as it was read from the text file and as the user added numbers to the list.
    * 
    */
   public void actionPerformed(ActionEvent event) {
      String  menuName;
      menuName = event.getActionCommand();
      
      //OPEN
      if (menuName.equals("Open")){
	    openFile();
      }
      //ADD
      else if (menuName.equals("Add")){
    	  RomanNumeral temp;
    	  while(true){
    		  try{
		    	  inputWord = JOptionPane.showInputDialog(null, "Please enter a Roman Numeral: ");
		    	  temp = new RomanNumeral(inputWord);
		    	  gui.appendToLeft(left, temp.toString()+"\n");
		    	  rnList.add(temp);
		    	  break;
    		  }
    		  catch(IllegalRomanNumeralException irne){
    			  JOptionPane.showMessageDialog(null,  "Invalid Roman Numeral: "+ inputWord + ". Please try again.");
    		  }
    		  catch(NullPointerException iae){
    			  break;
    		  }
    		  
    	  }//end while
      }
      //DELETE
      else if (menuName.equals("Delete")){
    	  int d;
    	  //PRINTS THE LIST UNSORTED WITH THEIR CORRESPONDING INDEX NUMBERS
    	  String deleteThis = "";
    	  left.setText("");
    	  int i = 0; 
    	  while ( i < rnList.size() ){
    		  gui.appendToLeft(left, (rnList.indexOf(rnList.get(i))+1)+". "+(rnList.get(i))+"\n");
    		  i++;
      	  }
    	  //SHOWS DIALOGBOX THAT ASKS FOR INDEX TO BE DELETED AND HANDLES EXCEPTION
    	  while(true){
    		  try{
    			  deleteThis = JOptionPane.showInputDialog(null, "Please enter the index of the Roman Numeral you want to delete: ");
    	    	  
    			  d = Integer.parseInt(deleteThis);
    			  rnList.remove(d-1);
    	    	  
    	    	  left.setText("");
    	    	  i = 0; 
    	    	  while ( i < rnList.size() ){
    	    		  gui.appendToLeft(left, (rnList.indexOf(rnList.get(i))+1)+". "+(rnList.get(i))+"\n");
    	    		  i++;
    	      	  }
    	    	  break;
    		  }
    		  catch (NumberFormatException nfe){
    			  if (deleteThis == null)
    				  break;
    			  JOptionPane.showMessageDialog(null,  "Non-integer input. Please try again.");
    		  }
    		  catch (IndexOutOfBoundsException ioobe){
    			  JOptionPane.showMessageDialog(null,  "Index out of bounds. Please try again.");
    		  }
    	  }//end while
    	  
      }
      //SORTED
      else if (menuName.equals("Sorted")){
    	  //COPIES THE LIST, AND SORTS IT VIA THE Collections.sort() METHOD
    	  left.setText("");
    	  LinkedList <RomanNumeral> sortedRNList = new LinkedList<RomanNumeral>(rnList);
    	  Collections.sort(sortedRNList);
    	  int i = 0; 
    	  while (i < sortedRNList.size()){
    		  gui.appendToLeft(left, sortedRNList.get(i)+"\n");
    	  	  i++;
      	  }
      }
      //UNSORTED
      else if (menuName.equals("Unsorted")){
    	  //DISPLAYS THE LIST "AS IS"
    	  left.setText("");
    	  int i = 0; 
    	  while ( i < rnList.size() ){
    		  gui.appendToLeft(left, rnList.get(i)+"\n");
    		  i++;
      	  }
      }
      //QUIT
      else if (menuName.equals("Quit"))
         System.exit(0);
   } //actionPerformed

    /** 
     * Opens a JFileChooser to select a .txt file to read Roman Numerals from.
     * If user chooses a file, the method erases the previous data and calls the method readSource() to read for the new data.
     * 
     */
	private void openFile( ) {
	   JFileChooser chooser;
	   int          status;
	   //.txt FILTER
	   FileNameExtensionFilter filter = new FileNameExtensionFilter(".txt files", "txt");
	   chooser = new JFileChooser( );
	   chooser.setFileFilter(filter);
	   status = chooser.showOpenDialog(null);
	   if (status == JFileChooser.APPROVE_OPTION) { //CLEARS THE TEXT AND THEN READS THE SELECTED FILE
		  left.setText(""); //Clears the textArea
	      rnList.clear();   //Clears the list
	      readSource(chooser.getSelectedFile());
	   }
	   else 
	      JOptionPane.showMessageDialog(null, "Open File dialog canceled");
	} //openFile
	
	
	/**
	 * Reads each line of text off of the .txt file and prints a message if a line is not a Roman Numeral.
	 * 
	 * @param chosenFile
	 */
    private void readSource(File chosenFile) {
       String chosenFileName = chosenFile.getPath();
       TextFileInput inFile = new TextFileInput(chosenFileName);
       String in;
       Container myContentPane = gui.getContentPane();
       myContentPane.add(left, BorderLayout.WEST);
       
	   
	   //Appends the lines in the file to the GUI TextArea
       in = "";
       while (in != null) {
    	   try{
			  RomanNumeral checker = new RomanNumeral(in);
    		  rnList.add(checker);
    		  gui.appendToLeft(left,in+"\n");
	       }
    	   catch(IllegalRomanNumeralException iae){
    		   if (in.equals(""))
    			   continue;
    		   else System.out.println("Cannot convert to Roman Numeral: "+ in);
    	   }
    	   finally{
    		   in =null;
    		   in = inFile.readLine();
    	   }
       }
       gui.setVisible(true);  
    }//readsource
    
}