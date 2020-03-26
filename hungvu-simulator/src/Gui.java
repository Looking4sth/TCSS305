/*
 * TCSS 305 - Fall 2019
 * Assignment 3 - Traffic Simulator
 * This class provide a traffic simulation game. 
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextArea;
/**
 * This class contains model, view and controller for 
 * traffic simulation game. It will open a game window 
 * when executed.
 * @author Hung Vu
 * @version 13 11 2019
 */
public class Gui extends JFrame implements ActionListener {
	/**
	 * instance field serialVersionUID is default serial version UID
	 * for this class.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Instance field FPS_MIN is minimum value of Fps slider.
	 */
	private static final int FPS_MIN = 0;
	/**
	 * Instance field FPS_MIN is maximum value of Fps slider.
	 */
	private static final int FPS_MAX = 60;
	/**
	 * Instance field FPS_MIN is major division (step) of Fps slider.
	 * E.g: (0,10,20,etc)
	 */
	private static final int FPS_INIT = 10;
	/**
	 * Instance field myFpsPrompt is label that show slider's name.
	 */
	private JLabel myFpsPrompt = new JLabel("FPS");
	/**
	 * Instance field myFpsSlide is a slider for Fps.
	 */
	private JSlider myFpsSlide = new JSlider(JSlider.HORIZONTAL, FPS_MIN, FPS_MAX, FPS_INIT);
	/**
	 * Instance field myDebug is a check box to enable/disable debug view.
	 */
	private JCheckBox myDebug = new JCheckBox("Debug Mode");
	/**
	 * Instance field myDebugInfo is a text area appeared when debug check box 
	 * is chosen, hidden otherwise.
	 */
	private JTextArea myDebugInfo = new JTextArea(2,50);
	/**
	 * Instance field myDebugScroll makes debug text area scrollable.
	 */
	private JScrollPane myDebugScroll = new JScrollPane(myDebugInfo);
	/**
	 * Instance field myNorthPanel is a panel for North side of window.
	 */
	private JPanel myNorthPanel = new JPanel(new FlowLayout());
	/**
	 * Instance field mySouthPanel is a panel for South side of window.
	 */
	private JPanel mySouthPanel = new JPanel(new FlowLayout());
	/**
	 * Instance field myCenterPanel is a panel for Center side of window,
	 * where renders a map.
	 */
	private JPanel myCenterPanel = new JPanel();
	/**
	 * Instance field myButtons is an array of buttons which contains
	 * Start, Stop, Step, Reset buttons for North side of window.
	 */
	private JButton[] myButtons = new JButton[4];
	/**
	 * Instance field myGridPanel is a 2D array of panels, which contains
	 * panels added to Center panel at their specific coordinate.
	 */
	private JPanel[][] myGridPanel;
	/**
	 * Instance field myCoordinateLabel is a label which shows coordinate
	 * of Center panel, appeared when debug check box is chosen, false otherwise.
	 */
	private JLabel[][] myCoordinateLabel;
	/**
	 * Instance field myHumanList is an array list contains all Human objects
	 * on the map, either dead or alive.
	 */
	private ArrayList<Human> myHumanList = new ArrayList<Human>();
	/**
	 * Instance field myBicycleList is an array list contains all Bicycle objects
	 * on the map, either dead or alive.
	 */
	private ArrayList<Bicycle> myBicycleList = new ArrayList<Bicycle>();
	/**
	 * Instance field myAtvList is an array list contains all Atv objects
	 * on the map, either dead or alive.
	 */
	private ArrayList<ATV> myAtvList = new ArrayList<ATV>();
	/**
	 * Instance field myTaxiList is an array list contains all Taxi objects
	 * on the map, either dead or alive.
	 */
	private ArrayList<Taxi> myTaxiList = new ArrayList<Taxi>();
	/**
	 * Instance field myCarList is an array list contains all Car objects
	 * on the map, either dead or alive.
	 */
	private ArrayList<Car> myCarList = new ArrayList<Car>();
	/**
	 * Instance field myTruckList is an array list contains all Truck objects
	 * on the map, either dead or alive.
	 */
	private ArrayList<Truck> myTruckList = new ArrayList<Truck>();
	/**
	 * Instance field myHumanLabel is an array list contains image of all
	 * Human objects on the map, either dead or alive, in the form of a label.
	 */
	private ArrayList<JLabel> myHumanLabel = new ArrayList<JLabel>();
	/**
	 * Instance field myBicycleLabel is an array list contains image of all
	 * Bicycle objects on the map, either dead or alive, in the form of a label.
	 */
	private ArrayList<JLabel> myBicycleLabel = new ArrayList<JLabel>();
	/**
	 * Instance field myAtvLabel is an array list contains image of all
	 * Atv objects on the map, either dead or alive, in the form of a label.
	 */
	private ArrayList<JLabel> myAtvLabel = new ArrayList<JLabel>();
	/**
	 * Instance field myTaxiLabel is an array list contains image of all
	 * Taxi objects on the map, either dead or alive, in the form of a label.
	 */
	private ArrayList<JLabel> myTaxiLabel = new ArrayList<JLabel>();
	/**
	 * Instance field myCarLabel is an array list contains image of all
	 * Car objects on the map, either dead or alive, in the form of a label.
	 */
	private ArrayList<JLabel> myCarLabel = new ArrayList<JLabel>();
	/**
	 * Instance field myTruckLabel is an array list contains image of all
	 * Truck objects on the map, either dead or alive, in the form of a label.
	 */
	private ArrayList<JLabel> myTruckLabel = new ArrayList<JLabel>();
	/**
	 * Instance field myTrafficLightCoordinate is an array list contains
	 * coordinate of all traffic lights for vehicles in the form of strings.
	 */
	private ArrayList<String> myTrafficLightCoordinate = new ArrayList<String>();
	/**
	 * Instance field myPeopleLightCoordinate is an array list contains
	 * coordinate of all traffic lights for people in the form of strings (row, column).
	 * Starting from (0,0) at top left of map, increasing downward.
	 * E.g:
	 * (0,0) (0,1) (0,2)
	 * (1,0)
	 * (2,0)
	 */
	private ArrayList<String> myPeopleLightCoordinate = new ArrayList<String>();
	/**
	 * Instance field myPeopleLight is an ellipse represents 
	 * traffic light for people.
	 */
	private Ellipse2D myPeopleLight = new Ellipse2D.Double(12, 8, 30, 30);
	/**
	 * Instance field myTrafficLight is an ellipse represents 
	 * traffic light for vehicles.
	 */
	private Ellipse2D myTrafficLight = new Ellipse2D.Double(4.5, 0, 45, 45);
	/**
	 * Instance field myStep is a counter for predefined steps interval (7).
	 */
	private int myStep = 0;
	/**
	 * Instance field myLightType is a counter that represents which color
	 * the light have to change to after certain steps.
	 * 1 is Yellow
	 * 2 is Red
	 * 3 is Green
	 */
	private int myLightType = 0;
	/**
	 * Instance field y is a number of rows the map should have, taken from 
	 * city_map.txt
	 */
	private int y = 0;
	/**
	 * Instance field x is a number of columns the map should have, taken from 
	 * city_map.txt
	 */
	private int x = 0;
	/**
	 * This method is a constructor for game window (Gui).
	 */
	public Gui() {
		northPanel();
		centerPanel();
		southPanel();
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(myNorthPanel, BorderLayout.NORTH);
		getContentPane().add(mySouthPanel, BorderLayout.SOUTH);
		getContentPane().add(myCenterPanel, BorderLayout.CENTER);		
	}
	/**
	 * This method builds a sub panel for North side of window.
	 */
	public void northPanel() {
		myButtons[0] = new JButton("Start");
		myButtons[1] = new JButton("Stop");
		myButtons[2] = new JButton("Step");
		myButtons[3] = new JButton("Reset");
		for (int i = 0; i < myButtons.length; i++) {
			myButtons[i].addActionListener(this);
			myNorthPanel.add(myButtons[i]);
		}
	}
	/**
	 * This method builds a sub panel for Center side of window,
	 * where city map is rendered.
	 */
	public void centerPanel() {
		ArrayList<String> line = readTxtFile();
		/**
		 * Set y and x to number of rows and column respectively.
		 * This information is from first line of "city_map.txt" and
		 * used to create coordinate for grid layout and 2D array.
		 */
		y = Integer.valueOf(line.get(0).split(" ")[0]);
		x = Integer.valueOf(line.get(0).split(" ")[1]);
		myCenterPanel.setLayout(new GridLayout(y, x));
		myGridPanel = new JPanel[y][x];
		myCoordinateLabel = new JLabel[y][x];
		/**
		 * Variable mapCoordinate is a list contains information
		 * to render a map terrain and light based on information in "city_map".
		 * Its elements starts from line 2 of "city_map" and end at line (y+1)
		 */
		List<String> mapCoordinate = line.subList(1, y + 1);
		for (int row = 0; row < y; row ++) {
			for (int col = 0; col < x; col++) {
				/**
				 * Create grid panel elements for 2D array myGridPanel.
				 */
				myGridPanel[row][col] = new JPanel();
				/**
				 * Create coordinate of each grid point in the form of a JLabel.
				 * This is only shown when debug box is chosen.
				 */
				myCoordinateLabel[row][col] = new JLabel ("(" + row + " " + col +")");
				myCoordinateLabel[row][col].setVisible(false);
				/**
				 * The if else statement is used to build map based on given 
				 * information (E.g: if that point in city map is X, render
				 * black border around map).
				 */
				if(mapCoordinate.get(row).charAt(col) == 'X'){
					myGridPanel[row][col].setBackground(Color.BLACK);					
				}
				else if(mapCoordinate.get(row).charAt(col) == '-') {
					myGridPanel[row][col].setBackground(Color.GREEN);
				}
				else if(mapCoordinate.get(row).charAt(col) == '|') {
					myGridPanel[row][col].setBackground(Color.GRAY);
				}
				else if(mapCoordinate.get(row).charAt(col) == '.') {
					myGridPanel[row][col].setBackground(new Color(128, 128, 0));
				}
				else if(mapCoordinate.get(row).charAt(col) == '#') {	
					myGridPanel[row][col] = new JPanel() {
						/**
						 * Variable serialVersionUID is default serial version UID
						 * for this anonymous class.
						 */
						private static final long serialVersionUID = 1L;
						public void paintComponent(Graphics g) {
							super.paintComponent(g);
		                    Graphics2D g2 = (Graphics2D)g;
		                    g2.setColor(new Color(0, 128, 0));
		                    g2.fill(myPeopleLight);	                    
						}
					};
					/**
					 * Provide coordinate of people traffic light.
					 */
					myPeopleLightCoordinate.add(row + " " + col);
					myGridPanel[row][col].setBackground(Color.GRAY);										
				}
				else {
					myGridPanel[row][col] = new JPanel() {
						/**
						 * Variable serialVersionUID is default serial version UID
						 * for this anonymous class.
						 */
						private static final long serialVersionUID = 1L;
						public void paintComponent(Graphics g) {
							super.paintComponent(g);
		                    Graphics2D g2 = (Graphics2D)g;
		                    g2.setColor(new Color(0, 128, 0));
		                    g2.fill(myTrafficLight);
		                    
						}
					};
					/**
					 * Provide coordinate of vehicles traffic light.
					 */
					myTrafficLightCoordinate.add(row + " " + col);
					myGridPanel[row][col].setBackground(Color.GRAY);	
				}
				/**
				 * Add coordinate to the myGridPanel elements, this is only shown
				 * when debug check box is chosen.
				 */
				myGridPanel[row][col].add(myCoordinateLabel[row][col]);
				/**
				 * The method addImage will render all vehicles image on the map.
				 */
				addImage(col, row);
				/**
				 * Add all the elements of myGridPanel to Center side of window.
				 */
				myCenterPanel.add(myGridPanel[row][col]);
			}
		}
	}
	/**
	 * This class builds a sub panel for South side of window.
	 */
	public void southPanel() {
		myDebugScroll.setVisible(false);
		myFpsSlide.setMajorTickSpacing(10);
		myFpsSlide.setMinorTickSpacing(1);
		myFpsSlide.setPaintTicks(true);
		myFpsSlide.setPaintLabels(true);
		mySouthPanel.add(myFpsPrompt);
		mySouthPanel.add(myFpsSlide);
		mySouthPanel.add(myDebug);
		mySouthPanel.add(myDebugScroll);
		myDebug.addActionListener(this);
	}
	/**
	 * This method will read a given "city_map.txt" file which has
	 * information to build a map, and return its information
	 * in the form of ArrayList<String>. 
	 * 
	 * Each line of the file is an array element. The information
	 * started from "//" (E.g: "// X indicates black colored borders of the map")
	 * is excluded.
	 * @return an array list contains "city_map.txt" line as an element.
	 */
	public ArrayList<String> readTxtFile() {
		ArrayList<String> line = new ArrayList<String>();
		Scanner input;
		try {
			input = new Scanner(new File("city_map.txt"));
			while(input.hasNextLine()) {
				String tempLine = input.nextLine();
				if(tempLine.contains("//")) {
					tempLine = tempLine.substring(0, tempLine.indexOf("//"));
					line.add(tempLine);
				}
				else {
					line.add(tempLine);
				}
			}
			input.close();	
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return line;
			
	}
	/**
	 * This method will render image of vehicles on the map.
	 * @param theCol x coordinate (column) where the vehicle exists.
	 * @param theRow y coordinate (row) where the vehicle exists.
	 */
	private void addImage(int theCol, int theRow){
		ArrayList<String> line = readTxtFile();
		/**
		 * Variable vehiclesCoordinate is a list contains coordinate and amount of 
		 * vehicles taken from "city_map.txt", this list start after information
		 * about terrain and light ended.
		 */
		List<String> vehiclesCoordinate = line.subList(y + 1, line.size());
		/**
		 * Variable vehiclesAmount is a number of vehicles initially exist on map.
		 * Also a number of line we have to look in city_map file.
		 */
		int vehiclesAmount = Integer.valueOf(vehiclesCoordinate.get(0).split(" ")[0]);
		for(int n = 1; n <= vehiclesAmount; n++ ) {
			/**
			 * Variables vehiclesType, colVehicle, rowVehicle represent type
			 * of vehicle, its row (y), and its column (x) respectively
			 */
			String vehiclesType = vehiclesCoordinate.get(n).split(" ")[0];
			int colVehicle = Integer.valueOf(vehiclesCoordinate.get(n).split(" ")[1]);
			int rowVehicle = Integer.valueOf(vehiclesCoordinate.get(n).split(" ")[2]);
			/**
			 * The if statement makes the vehicles render only at specific grid point
			 */
			if (theRow == rowVehicle && theCol == colVehicle) {
				/**
				 * The switch case statement makes sure the vehicles use their
				 * own image at their points.
				 * 
				 * This also add objects to the their respective list to 
				 * have a reference point.
				 * 
				 * All the images are used as a JLabel.
				 */
				switch (vehiclesType) {						
				case "H":
					Human human = new Human(theCol, theRow);
					myHumanList.add(human);
					ImageIcon imageH = human.getImageFileName("Human.gif");
					JLabel imageLabelH = new JLabel("", imageH, JLabel.CENTER);
					myHumanLabel.add(imageLabelH);
					myGridPanel[theRow][theCol].add(imageLabelH);
					break;
					
				case "B": 
					Bicycle bicycle = new Bicycle(theCol, theRow);
					myBicycleList.add(bicycle);
					ImageIcon imageB = bicycle.getImageFileName("Bicycle.gif");
					JLabel imageLabelB = new JLabel("", imageB, JLabel.CENTER);
					myBicycleLabel.add(imageLabelB);
					myGridPanel[theRow][theCol].add(imageLabelB);
					break;
					
				case "A":
					ATV atv = new ATV(theCol, theRow);
					myAtvList.add(atv);
					ImageIcon imageA = atv.getImageFileName("ATV.gif");
					JLabel imageLabelA = new JLabel("", imageA, JLabel.CENTER);
					myAtvLabel.add(imageLabelA);
					myGridPanel[theRow][theCol].add(imageLabelA);							
					break;
				
				case "X": 
					Taxi taxi = new Taxi(theCol, theRow);
					myTaxiList.add(taxi);
					ImageIcon imageTA = taxi.getImageFileName("Taxi.gif");
					JLabel imageLabelTA = new JLabel("", imageTA, JLabel.CENTER);
					myTaxiLabel.add(imageLabelTA);
					myGridPanel[theRow][theCol].add(imageLabelTA);						
					break;	
					
				case "C": 
					Car car = new Car(theCol, theRow);
					myCarList.add(car);
					ImageIcon imageC = car.getImageFileName("Car.gif");
					JLabel imageLabelC = new JLabel("", imageC, JLabel.CENTER);
					myCarLabel.add(imageLabelC);
					myGridPanel[theRow][theCol].add(imageLabelC);						
					break;
					
				case "T": 
					Truck truck = new Truck(theCol, theRow);
					myTruckList.add(truck);
					ImageIcon imageT = truck.getImageFileName("Truck.gif");
					JLabel imageLabelT = new JLabel("", imageT, JLabel.CENTER);
					myTruckLabel.add(imageLabelT);
					myGridPanel[theRow][theCol].add(imageLabelT);						
					break;
				}
			}
		}
	}
	/**
	 * This method return vehicles to their initial point after reset button is clicked
	 * without affecting traffic lights state.
	 * This method and vehiclesByStep() work the same way.
	 * @param theArrayList The array list of Vehicles-child to have a reference point.
	 * (E.g: myHumanList)
	 * @param theLabel The list contains each reference point respective label.
	 * @param theFileName Respective image file name of each reference vehicle.
	 */
	private void resetVehicles (ArrayList<? extends Vehicles> theArrayList, ArrayList<JLabel> theLabel, String theFileName) {
		for (int i = 0; i < theArrayList.size(); i++) {
			theArrayList.get(i).reset();
		}
		for(int j = 0; j < theLabel.size(); j++) {
			theLabel.get(j).setVisible(false);
		}
		/**
		 * Reset label list after the reset button is clicked
		 * to create a new label list.
		 */
		theLabel.clear();
		for(int i = 0; i < theArrayList.size(); i++) {
			int newRow = theArrayList.get(i).getY();
			int newCol = theArrayList.get(i).getX();
			JLabel newVehicle = new JLabel("", theArrayList.get(i).getImageFileName(theFileName), JLabel.CENTER);
			myGridPanel[newRow][newCol].add(newVehicle);
			theLabel.add(newVehicle);
		}
	}
	/**
	 * This methods render vehicles at their new position after step button is clicked.
	 * This happens only when the vehicles are alive, else it won't render that vehicle.
	 * 
	 * This method and resetVehicles() work the same way.
	 * @param theArrayList The array list of Vehicles-child to have a reference point.
	 * (E.g: myHumanList)
	 * @param theLabel The list contains each reference point respective label.
	 * @param theFileName Respective image file name of each reference vehicle.
	 */
	private void vehiclesByStep(ArrayList<? extends Vehicles> theArrayList, ArrayList<JLabel> theLabel, String theFileName) {
		for(int j = 0; j < theLabel.size(); j++) {
			theLabel.get(j).setVisible(false);
		}
		theLabel.clear();
		for(int i = 0; i < theArrayList.size(); i++) {
			if(theArrayList.get(i).isAlive()) {
				theArrayList.get(i).move();
				int newRow = theArrayList.get(i).getY();
				int newCol = theArrayList.get(i).getX();
				JLabel newVehicle = new JLabel("", theArrayList.get(i).getImageFileName(theFileName), JLabel.CENTER);
				myGridPanel[newRow][newCol].add(newVehicle);
				theLabel.add(newVehicle);
			}
		}
	}
	/**
	 * This method change vehicles/people traffic lights after a certain step (7).
	 * @param theCoordinate The list contains traffic lights coordinate.
	 * @param theLight An integer indicate type of light should change to (color).
	 * @param theType The string indicate whether people or vehicles traffic light is affected
	 * by the call.
	 */
	private void lightByStep(ArrayList<String> theCoordinate, int theLight, String theType) {
		myCenterPanel.removeAll();	
		for(int i = 0; i < theCoordinate.size(); i++) {
			int row = Integer.valueOf(theCoordinate.get(i).split(" ")[0]);
			int col = Integer.valueOf(theCoordinate.get(i).split(" ")[1]);
			myGridPanel[row][col] = new JPanel() {
				/**
				 * Variable serialVersionUID is default serial version UID
				 * for this anonymous class.
				 */
				private static final long serialVersionUID = 1L;
				public void paintComponent(Graphics g) {
					super.paintComponent(g);
			        Graphics2D g2 = (Graphics2D)g;
			        if (theLight == 1) {
			        	g2.setColor(Color.YELLOW);
			        }
			        else if(theLight == 2) {
			            g2.setColor(Color.RED);
			        }
			        else if (theLight == 3){
			            g2.setColor(new Color(0, 128, 0));

			        }
			        if(theType.equalsIgnoreCase("Traffic")) {
				        g2.fill(myTrafficLight);
			        }
			        else if (theType.equalsIgnoreCase("People")){
			            g2.fill(myPeopleLight);
			        }			            
				}
			};
			myGridPanel[row][col].add(myCoordinateLabel[row][col]);
			myGridPanel[row][col].setBackground(Color.GRAY);
		}
		for(int row = 0; row < y; row++) {
			for(int col = 0; col < x; col++) {			

				myCenterPanel.add(myGridPanel[row][col]);
			}
		}
	}
	/**
	 * This method provide vehicles information for text debug are when
	 * debug check box is chosen.
	 * @param theArrayList The array list of Vehicles-child to have a reference point.
	 * @return a string contains information of all vehicles
	 */
	private String debugInfo(ArrayList<? extends Vehicles> theArrayList) {
		String info = "";
		for(int i = 0; i < theArrayList.size(); i++) {
			info += theArrayList.get(i).toString() + "\n";
		}
		return info;
	}
	/**
	 * This method provide a coordinate hash set of respective vehicle type at 
	 * a specific time. This is used to detect the collision.
	 * 
	 * Assume that the same vehicles is not affected when they collide each other.
	 * Hash set is used because it isn't affected even if multiple same vehicles already
	 * collide at one grid point. 
	 * @param theArray The array list of Vehicles-child to have a reference point.
	 * @return
	 */
	private HashSet<String> arrayOfCoordinate(ArrayList<? extends Vehicles> theArray){
		HashSet<String> coordinate = new HashSet<String>();
		for(int i = 0; i < theArray.size(); i ++) {
			coordinate.add(theArray.get(i).getY() + " " + theArray.get(i).getX());
		}
		return coordinate;
	}
	/**
	 * This method is used to detect collision between multiple vehicles types, by comparing
	 * their respective coordinate on the map. If coordinate of different vehicles type are 
	 * the same, it means they collides.
	 * @return the HashSet contains collision coordinate.
	 */
	private HashSet<String>intersection(){
		ArrayList<String> intersection = new ArrayList<String>();
		intersection.addAll(arrayOfCoordinate(myHumanList));
		intersection.addAll(arrayOfCoordinate(myBicycleList));
		intersection.addAll(arrayOfCoordinate(myAtvList));
		intersection.addAll(arrayOfCoordinate(myCarList));
		intersection.addAll(arrayOfCoordinate(myTaxiList));
		intersection.addAll(arrayOfCoordinate(myTruckList));
		HashSet<String> duplicates = new HashSet<String>();
		HashSet<String> unique = new HashSet<String>();
		for(String i : intersection) {
			if(!unique.add(i)) {
				duplicates.add(i);
			}
		}
		return duplicates;
	}
	/**
	 * This method permanently set vehicle to dead after a collision happens.
	 * @param theIntersection HashSet which contains collision coordinate.
	 */
	private void collision(HashSet<String> theIntersection) {
		if(theIntersection.size() != 0) {
			for(Human i : myHumanList) {
				if(theIntersection.contains(i.getY() + " " + i.getX())) {
					i.setAlive(false);
				}
			}
			for(Bicycle i : myBicycleList) {
				if(theIntersection.contains(i.getY() + " " + i.getX())) {
					i.setAlive(false);
				}
			}
			for(ATV i : myAtvList) {
				if(theIntersection.contains(i.getY() + " " + i.getX())) {
					i.setAlive(false);
				}
			}
			for(Car i : myCarList) {
				if(theIntersection.contains(i.getY() + " " + i.getX())) {
					i.setAlive(false);
				}
			}
			for(Taxi i : myTaxiList) {
				if(theIntersection.contains(i.getY() + " " + i.getX())) {
					i.setAlive(false);
				}
			}
			for(Truck i : myTruckList) {
				if(theIntersection.contains(i.getY() + " " + i.getX())) {
					i.setAlive(false);
				}
			}				
		}
	}
	@Override
	/**
	 * This method provide behavior for game window after a button is clicked
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == myButtons[2]) {
			vehiclesByStep(myHumanList, myHumanLabel, "Human.gif");
			vehiclesByStep(myBicycleList, myBicycleLabel, "Bicycle.gif");
			vehiclesByStep(myAtvList, myAtvLabel, "ATV.gif");
			vehiclesByStep(myTaxiList, myTaxiLabel, "Taxi.gif");
			vehiclesByStep(myCarList, myCarLabel, "Car.gif");
			vehiclesByStep(myTruckList, myTruckLabel, "Truck.gif");			
			myDebugInfo.setText(debugInfo(myHumanList));
			myDebugInfo.setText(myDebugInfo.getText() + "\n" + debugInfo(myBicycleList));
			myDebugInfo.setText(myDebugInfo.getText() + "\n" + debugInfo(myAtvList));
			myDebugInfo.setText(myDebugInfo.getText() + "\n" + debugInfo(myTaxiList));
			myDebugInfo.setText(myDebugInfo.getText() + "\n" + debugInfo(myCarList));
			myDebugInfo.setText(myDebugInfo.getText() + "\n" + debugInfo(myTruckList));	
			/**
			 * myStep is increased after each Step-click, when it reaches multiple of 7, 
			 * the light will changes.
			 */
			myStep++;
			if(myStep % 7 == 0) {
				if(myLightType < 3 ) {
					myLightType++;
				}
				else {
					myLightType = 1;
				}
				lightByStep(myTrafficLightCoordinate, myLightType, "Traffic");
				lightByStep(myPeopleLightCoordinate, myLightType, "People");
			}
			collision(intersection());			
			myCenterPanel.revalidate();
			myCenterPanel.repaint();
		}
		else if (e.getSource() == myDebug) {
			/**
			 * Set visible to grid point coordinate based on debug check box choice.
			 */
			for (int row = 0; row < 13; row++) {
				for(int col = 0; col < 24; col++) {
					if (myDebug.isSelected()) {
						myCoordinateLabel[row][col].setVisible(true);
						
					}
					else {
						myCoordinateLabel[row][col].setVisible(false);
						
					}
					mySouthPanel.revalidate();
					mySouthPanel.repaint();
				}
			}
			/**
			 * Set visible to debug text area based on debug check box choice.
			 */
			if(myDebug.isSelected()) {			
				myDebugInfo.setText(debugInfo(myHumanList));
				myDebugInfo.setText(myDebugInfo.getText() + "\n" + debugInfo(myBicycleList));
				myDebugInfo.setText(myDebugInfo.getText() + "\n" + debugInfo(myAtvList));
				myDebugInfo.setText(myDebugInfo.getText() + "\n" + debugInfo(myTaxiList));
				myDebugInfo.setText(myDebugInfo.getText() + "\n" + debugInfo(myCarList));
				myDebugInfo.setText(myDebugInfo.getText() + "\n" + debugInfo(myTruckList));
				myDebugScroll.setVisible(true);
			}
			else {
				myDebugScroll.setVisible(false);
			}
		}
		else if(e.getSource() == myButtons[3]) {			
			resetVehicles(myHumanList, myHumanLabel, "Human.gif");
			resetVehicles(myBicycleList, myBicycleLabel, "Bicycle.gif");
			resetVehicles(myAtvList, myAtvLabel, "ATV.gif");
			resetVehicles(myTaxiList, myTaxiLabel, "Taxi.gif");
			resetVehicles(myCarList, myCarLabel, "Car.gif");
			resetVehicles(myTruckList, myTruckLabel, "Truck.gif");	
		}	
	}
}
