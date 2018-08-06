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
			GridPane matrix = new GridPane();
		    Label[][] label = new Label[19][19];
		    
		    for(Element elm : elementList) {
		    	if (elm.groupNum != -1){
		    	label[elm.groupNum][elm.PeriodNum] = new Label();
            	label[elm.groupNum][elm.PeriodNum].setText(elm.symbol);
                matrix.add(label[elm.groupNum][elm.PeriodNum], elm.groupNum, elm.PeriodNum);
		    	}
		    }
		    
			BorderPane root = new BorderPane();
			Scene scene = new Scene(matrix,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
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
