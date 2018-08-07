package edu.wit.dcsn.comp1050.project;
	
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;


public class Main extends Application {
	private final static int SYMBOL =              0 ;
    private final static int ELEMENT =             1 ;
    private final static int ATOMIC_NUMBER =       2 ;
    private final static int ATOMIC_WEIGHT =       3 ;
    private final static int GROUP_NUMBER =        4 ;
    private final static int PERIOD =              5 ;
    private final static int ALTERNATE_SPELLING =  6 ;
    private final static int GROUP_NAME =          7 ;
    private final static int HALF_LIFE =           8 ;
    @Override
	public void start(Stage primaryStage) {
    	ArrayList<Element> elementList = new ArrayList<Element>();
		try ( Scanner elementsDB =	new Scanner( new File( "./data/periodic-table.dat" ) ) )
        {
        // read and display the header line
        // this is done separately since all column headers are text
        String[] headerFields =     parseRecord( elementsDB.nextLine() ) ;
        System.out.printf( "%6s  %-13s  %-13s  %-22s  %-8s  %-7s  %-20s  %-16s  %-25s%n",
                           headerFields[SYMBOL], 
                           headerFields[ELEMENT], 
                           headerFields[ATOMIC_NUMBER], 
                           headerFields[ATOMIC_WEIGHT], 
                           headerFields[PERIOD],
                           headerFields[GROUP_NUMBER],
                           headerFields[GROUP_NAME],
                           headerFields[HALF_LIFE], 
                           headerFields[ALTERNATE_SPELLING] ) ;
        
        // read and display each data line (one line per element in the periodic table
        String[] elementFields ;
        while( elementsDB.hasNext() )
        	{
        	// each line is tab-delimited
        	// some fields may be surrounded by double quotes (") - remove them
        	// split the line into 9 separate fields (as defined in the file)
        	elementFields =         parseRecord( elementsDB.nextLine() ) ;
        	//Instantiate an element and add to elementList
        	Element el = new Element(elementFields);
        	elementList.add(el);
        	}
        }
		
		catch ( FileNotFoundException e )
        {
        System.out.println( "Can't open file: periodic-table.dat" ) ;
        System.exit( 0 );
        }
		try {
			GridPane matrix = new GridPane(); //To print element easily
			BorderPane pane = new BorderPane(); //outer container
		    Label[][] label = new Label[19][10]; //Label used to display element's symbol
		    int tempGroupNum8 = 4; //Group of non-GroupNum elements
		    int tempGroupNum9 = 4;
		    for(Element elm : elementList) {
		    	if (elm.groupNum == -1 && 57 <= elm.atomicNum && elm.atomicNum <= 70){
		    		//First row of elements without group number
		    		//Add text to label to display element's symbol
		    		label[tempGroupNum8][8] = new Label();
		    		label[tempGroupNum8][8].setText(" " + elm.symbol + " ");
		    		label[tempGroupNum8][8].autosize();
		    		switch(elm.groupName) {
            			case "Alkali Metal": label[tempGroupNum8][8].setTextFill(Color.DARKRED); break;
            			case "Nobel Gas": label[tempGroupNum8][8].setTextFill(Color.CADETBLUE); break;
            			case "Alkaline Earth Metal": label[tempGroupNum8][8].setTextFill(Color.DARKSLATEBLUE); break;
            			case "Boron Group": label[tempGroupNum8][8].setTextFill(Color.CORAL); break;
            			case "Carbon Group": label[tempGroupNum8][8].setTextFill(Color.CRIMSON); break;
            			case "Pnictogen": label[tempGroupNum8][8].setTextFill(Color.DARKGOLDENROD); break;
            			case "Chalcogen": label[tempGroupNum8][8].setTextFill(Color.DARKGRAY); break;
            			case "Halogen": label[tempGroupNum8][8].setTextFill(Color.DARKOLIVEGREEN); break;
            			default: label[tempGroupNum8][8].setTextFill(Color.BLACK);
            	}
            	//Event Handler
            	label[tempGroupNum8][8].setOnMouseEntered(e -> {
        			Label name = new Label("Name: " + elm.elementName);
        			Label number = new Label("Number: " + Integer.toString(elm.atomicNum));
        			Label weight = new Label("Weight: " + ((elm.standardAtomicWeight.isEmpty())?"N/A":elm.standardAtomicWeight));
        			Label sym = new Label("Symbol: " + elm.symbol);
        			Label group = new Label( "Group Name: "+((elm.groupName.isEmpty())?"N/A":elm.groupName));
        			//Add Labels to Panes to display element info
        			pane.setCenter(sym);
        			pane.setBottom(weight);
        			pane.setTop(name);
        			pane.setLeft(number);
        			pane.setRight(group);
        	});
                matrix.add(label[tempGroupNum8][8], tempGroupNum8, 8);
                tempGroupNum8++;
		    	}
		    	else if (elm.groupNum == -1 && 89 <= elm.atomicNum && elm.atomicNum <= 102){
			    	//Second Row of elements without Group number
		    		//Add text to display element's symbol
		    		label[tempGroupNum9][9] = new Label();
	            	label[tempGroupNum9][9].setText(" " + elm.symbol + " ");
	            	label[tempGroupNum9][9].autosize();
	            	//Change color of element according to group
	            	switch(elm.groupName) {
            		case "Alkali Metal": label[tempGroupNum9][9].setTextFill(Color.DARKRED); break;
            		case "Nobel Gas": label[tempGroupNum9][9].setTextFill(Color.CADETBLUE); break;
            		case "Alkaline Earth Metal": label[tempGroupNum9][9].setTextFill(Color.DARKSLATEBLUE); break;
            		case "Boron Group": label[tempGroupNum9][9].setTextFill(Color.CORAL); break;
            		case "Carbon Group": label[tempGroupNum9][9].setTextFill(Color.CRIMSON); break;
            		case "Pnictogen": label[tempGroupNum9][9].setTextFill(Color.DARKGOLDENROD); break;
            		case "Chalcogen": label[tempGroupNum9][9].setTextFill(Color.DARKGRAY); break;
            		case "Halogen": label[tempGroupNum9][9].setTextFill(Color.DARKOLIVEGREEN); break;
            		default: label[tempGroupNum9][9].setTextFill(Color.BLACK);
            	}
	            	//Even Handler
	            	label[tempGroupNum9][9].setOnMouseEntered(e -> {
            			Label name = new Label("Name: " + elm.elementName);
            			Label number = new Label("Number: " + Integer.toString(elm.atomicNum));
            			Label weight = new Label("Weight: " + ((elm.standardAtomicWeight.isEmpty())?"N/A":elm.standardAtomicWeight));
            			Label sym = new Label("Symbol: " + elm.symbol);
            			Label group = new Label( "Group Name: "+((elm.groupName.isEmpty())?"N/A":elm.groupName));
            			//Add Labels to Panes to display element info
            			pane.setCenter(sym);
            			pane.setBottom(weight);
            			pane.setTop(name);
            			pane.setLeft(number);
            			pane.setRight(group);
            	});
	            	
	                matrix.add(label[tempGroupNum9][9], tempGroupNum9, 9);
	                tempGroupNum9++;
			    	}
		    	else {
		    	//add Texts to label to display element's symbol
		    	label[elm.groupNum][elm.PeriodNum] = new Label();
            	label[elm.groupNum][elm.PeriodNum].setText(" " + elm.symbol + " ");
            	label[elm.groupNum][elm.PeriodNum].autosize();
            	//Change element's color according to Group
            	switch(elm.groupName) {
        		case "Alkali Metal": label[elm.groupNum][elm.PeriodNum].setTextFill(Color.DARKRED); break;
        		case "Nobel Gas": label[elm.groupNum][elm.PeriodNum].setTextFill(Color.CADETBLUE); break;
        		case "Alkaline Earth Metal": label[elm.groupNum][elm.PeriodNum].setTextFill(Color.DARKSLATEBLUE); break;
        		case "Boron Group": label[elm.groupNum][elm.PeriodNum].setTextFill(Color.CORAL); break;
        		case "Carbon Group": label[elm.groupNum][elm.PeriodNum].setTextFill(Color.CRIMSON); break;
        		case "Pnictogen": label[elm.groupNum][elm.PeriodNum].setTextFill(Color.DARKGOLDENROD); break;
        		case "Chalcogen": label[elm.groupNum][elm.PeriodNum].setTextFill(Color.DARKGRAY); break;
        		case "Halogen": label[elm.groupNum][elm.PeriodNum].setTextFill(Color.DARKOLIVEGREEN); break;
        		default: label[elm.groupNum][elm.PeriodNum].setTextFill(Color.BLACK);
        	}
            	//Event Handler
            	label[elm.groupNum][elm.PeriodNum].setOnMouseEntered(e -> {
            			Label name = new Label("Name: " + elm.elementName);
            			Label number = new Label("Number: " + Integer.toString(elm.atomicNum));
            			Label weight = new Label("Weight: " + ((elm.standardAtomicWeight.isEmpty())?"N/A":elm.standardAtomicWeight));
            			Label sym = new Label("Symbol: " + elm.symbol);
            			Label group = new Label( "Group Name: "+((elm.groupName.isEmpty())?"N/A":elm.groupName));
            			//Add Labels to Panes to display element info
            			pane.setCenter(sym);
            			pane.setBottom(weight);
            			pane.setTop(name);
            			pane.setLeft(number);
            			pane.setRight(group);
            	});
            	//Add Elements with group number
                matrix.add(label[elm.groupNum][elm.PeriodNum], elm.groupNum, elm.PeriodNum);
		    	}
		    }
		    //Display setting
		    matrix.setPrefSize(400, 200);
			BorderPane root = new BorderPane();
			root.setTop(matrix);
			root.setCenter(pane);
			Scene scene = new Scene(root,600,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Periodic Table");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
    
	
	public static void main(String[] args) {
		launch(args);
	}
	private static String[] parseRecord( String recordToParse )
    {
    return recordToParse.replace( "\"", "" ).split( "\t", 9 ) ;
    }

}
