
import javax.swing.*;
import java.awt.*;

	
	public class RomanNumeralGUI extends JFrame{
		public RomanNumeralGUI myRomanNumeralGUI;
		String inputLine;
		RomanNumeral rn;
		String inOrderArray;
		

		public void initialize(){
			
			//Set size and title
			myRomanNumeralGUI = new RomanNumeralGUI();
			myRomanNumeralGUI.setTitle("Title");
			myRomanNumeralGUI.setSize(400,400);
			myRomanNumeralGUI.setLocation(200,200);
			
			//Create Menu Bar
			myRomanNumeralGUI.createFileMenu();
			
			//Exit on close and TextAreas/ContenPane
			myRomanNumeralGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			//myRomanNumeralGUI.pack();
			myRomanNumeralGUI.setVisible(true);
		}
		
		
		private void createFileMenu( ) {
		      JMenuItem   item,item2,item3;
		      JMenuBar    menuBar  = new JMenuBar();
		      JMenu       fileMenu = new JMenu("File");
		      JMenu		  editMenu = new JMenu("Edit");
		      JMenu		  displayMenu = new JMenu("Display");		  
		      FileMenuHandler fmh  = new FileMenuHandler(this);

		      item = new JMenuItem("Open");    
		      item.addActionListener(fmh);
		      fileMenu.add(item);

		      fileMenu.addSeparator();           
		    
		      item = new JMenuItem("Quit");       
		      item.addActionListener(fmh);
		      fileMenu.add(item);
		      
		      item2 = new JMenuItem("Add");
		      item2.addActionListener(fmh);
		      editMenu.add(item2);
		      
		      item3 = new JMenuItem("Sorted");
		      item3.addActionListener(fmh);
		      displayMenu.add(item3);
		      
		      displayMenu.addSeparator();
		      
		      item3 = new JMenuItem("Unsorted");
		      item3.addActionListener(fmh);
		      displayMenu.add(item3);
		      
		      setJMenuBar(menuBar);
		      menuBar.add(fileMenu);
		      menuBar.add(editMenu);
		      menuBar.add(displayMenu);
		    
		   } //createMenu

		
		public void appendToLeft(TextArea l, String s){
			l.append(s);
		}
		
		public void appendToRight(TextArea r, String s){
			r.append(s);
		}

		
	}
