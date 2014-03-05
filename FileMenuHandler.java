import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class FileMenuHandler implements ActionListener {
	public int lengthCount = 0;
	public TextArea left = new TextArea();
	RomanNumeralGUI gui;
	public inOrderRomanNumeralList inOrder = new inOrderRomanNumeralList();
 	public sortedRomanNumeralList sorted = new sortedRomanNumeralList();
 	public String inputWord;
   
   public FileMenuHandler (RomanNumeralGUI thisGUI) {
      gui = thisGUI;
   }
   
   public void actionPerformed(ActionEvent event) {
      String  menuName;
      menuName = event.getActionCommand();
      if (menuName.equals("Open")){
    	  openFile( ); 
    	  constructLists();
      }
      else if (menuName.equals("Add")){
    	  RomanNumeral temp;
    	  while(true){
    		  try{
		    	  inputWord = JOptionPane.showInputDialog(null, "Please enter a Roman Numeral: ");
		    	  temp = new RomanNumeral(inputWord);
		    	  gui.appendToLeft(left, temp.toString());
		    	  inOrder.add(temp);
		    	  sorted.add(temp);
		    	  break;
    		  }
    		  catch(IllegalArgumentException iae){
    			  JOptionPane.showMessageDialog(null,  "Not a a proper Roman Numeral: "+ inputWord + ". Please try again.");
    		  }
    		  catch(NullPointerException iae){
    			  break;
    		  }
    		  catch(Exception e){
    			  break;
    		  }
    	  }
      }
      else if (menuName.equals("Sorted")){
    	  left.setText("");
    	  gui.appendToLeft(left, sorted.toString());
      }
      else if (menuName.equals("Unsorted")){
    	  left.setText("");
    	  gui.appendToLeft(left, inOrder.toString());
      }
      else if (menuName.equals("Quit"))
         System.exit(0);
   } //actionPerformed

	private void openFile( ) {
	   JFileChooser chooser;
	   int          status;
	   FileNameExtensionFilter filter = new FileNameExtensionFilter(".txt files", "txt");
	   chooser = new JFileChooser( );
	   chooser.setFileFilter(filter);
	   status = chooser.showOpenDialog(null);
	   if (status == JFileChooser.APPROVE_OPTION) 
	      readSource(chooser.getSelectedFile());
	   else 
	      JOptionPane.showMessageDialog(null, "Open File dialog canceled");
	} //openFile
  
    private void readSource(File chosenFile) {
       String chosenFileName = chosenFile.getPath();
       TextFileInput inFile = new TextFileInput(chosenFileName);
       String in;
       Container myContentPane = gui.getContentPane();
       myContentPane.add(left, BorderLayout.WEST);
	   gui.pack();
       
	   
	   //Appends the lines in the file to the GUI TextArea
       in = inFile.readLine();
       
       lengthCount++;
       while (in != null) {
    	   try{
    		  RomanNumeral checker = new RomanNumeral(in);
    		  gui.appendToLeft(left, in+"\n");
	          lengthCount++;
    	   }
    	   catch(IllegalArgumentException iae){
    		   JOptionPane.showMessageDialog(null, "Not a roman numeral: "+in);
    		   System.out.println("Not a roman numeral: "+ in);
    	   }
    	   finally{
    		   in = inFile.readLine();
    	   }
       }
       gui.setVisible(true);  
    }//readsource
    
    //Constructs the two lists
    public void constructLists(){
    	String []readFromTextArea = left.getText().split("\n");
  		RomanNumeral rnn;
       	for (int i = 0; i<lengthCount-1; i++){
       		rnn = new RomanNumeral(readFromTextArea[i]);
      		inOrder.add(rnn);
      		sorted.add(rnn);	 
       	}   
    }//constructLists
    
    public int getLength(){
    	return lengthCount;
    }
}