/*
 * Dave Rosenberg
 * Comp 1050 - Computer Science II
 * Lab: Graphical, interactive Periodic Table
 * Summer, 2018
 * 
 * Usage restrictions:
 * 
 * You may use this code for exploration, experimentation, and furthering your learning
 * for this course. You may not use this code for any other assignments, in my course or
 * elsewhere, without explicit permission, in advance, from myself (and the instructor of
 * any other course). Further, you may not post or otherwise share this code with anyone
 * other than current students in my sections of this course. Violation of these usage
 * restrictions will be considered a violation of the Wentworth Institute of Technology
 * Academic Honesty Policy.
 */

/**
 * Sample code to read/parse/display the contents of periodic-table.dat
 * 
 * <p>
 * Note: Columns/fields are not displayed in the order in which they appear in the data
 * file.
 * 
 * <p>
 * Note: The contents of periodic-table.dat are for demonstration only - the correctness
 * of its contents is not guaranteed. The data contained therein was collected from
 * several on-line sources including Wikipedia.com, iupac.org, degruyter.com, kcvs.ca, and
 * lanl.gov.
 * 
 * <p>
 * Half-life periods are: a = year, d = day, h = hour, min = minute, s = second, ms =
 * millisecond
 */

package edu.wit.dcsn.comp1050.project;

import javafx.application.Application;
import java.io.File ;
import java.io.FileNotFoundException ;
import java.util.Scanner ;
import java.util.ArrayList;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.geometry.Pos;
import javafx.event.*;


/**
 * @author David M Rosenberg
 * @version 0.1.1 2017-08-11 changed data file column indices to constants
 * @version 0.1.0 2016-12-10 initial version
 */
public class LoadPeriodicTable extends Application
    {
    // column indices in the data file
    private final static int SYMBOL =              0 ;
    private final static int ELEMENT =             1 ;
   private final static int ATOMIC_NUMBER =       2 ;
    private final static int ATOMIC_WEIGHT =       3 ;
    private final static int GROUP_NUMBER =        4 ;
    private final static int PERIOD =              5 ;
   private final static int ALTERNATE_SPELLING =  6 ;
   private final static int GROUP_NAME =          7 ;
    private final static int HALF_LIFE =           8 ;
    

	/**
	 * @param args
	 *        -unused-
 */
   public void start (Stage primaryStage) throws Exception
        {
    	ArrayList <Element> elements = new ArrayList <Element>();
        // use a Scanner object to read the data file line-by-line
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
    
            	// convert select fields to their numeric values (will aid in placing them in the GUI)
            	// the Group might not be specified - substitute a sentinel value (-1)
            	int atomicNumber =      Integer.parseInt( elementFields[ATOMIC_NUMBER] ) ;
            	int group =             ( elementFields[GROUP_NUMBER].equals( "" )
            					? -1
            					: Integer.parseInt( elementFields[GROUP_NUMBER] ) ) ;
            	int period =            Integer.parseInt( elementFields[PERIOD] ) ;
            	Element element = new Element( elementFields[SYMBOL], 
                               elementFields[ELEMENT], 
                               atomicNumber, 
                               elementFields[ATOMIC_WEIGHT], 
                               period,
                               group,
                               elementFields[GROUP_NAME],
                               elementFields[HALF_LIFE], 
                               elementFields[ALTERNATE_SPELLING]);
            	// there appear to be odd formatting choices here - they exist so we can
            	// use the same format as above
            	elements.add(element);
            	System.out.printf( "%6s  %-13s  %-13s  %-22s  %-8s  %-7s  %-20s  %-16s  %-25s%n",
            	                   String.format( "%-3s ", element.symbol ),
            	                   element.elementName,
            	                   String.format( "     %3d", element.atomicNum ),
            	                   element.standardAtomicWeight,
            	                   String.format( "  %2d", element.PeriodNum ),
            	                   ( element.groupNum < 1
                    				   ? ""
                    				   : String.format( "  %2d", group ) ), 
            	                   element.groupName, 
            	                   element.halfLife,
            	                   element.alternateSpelling ) ;
            	}
            }
        catch ( FileNotFoundException e )
            {
            System.out.println( "Can't open file: periodic-table.dat" ) ;
            System.exit( 0 );
           }
       	try {
       			GridPane matrix = new GridPane();
       			Label[][] label = new Label[19][10];
       		  	int tempGroupNum8 = 4;
       		  	int tempGroupNum9 = 4;
       			Label displayLabel = new Label() ;
       			StackPane displayPane = new StackPane();
       			for(Element element : elements) {
       				final String content = String.format("%s%nElement Name: %s%nAtomic Number: %d%nStandard Atomic Weight: %s%nPeriod: %d%nGroup Number: %d%nGroup Name: %s%nHalf Life: %s%nAlternate Spelling: %s%n",
   		            		element.symbol,
   		            		element.elementName,
   		            		element.atomicNum,
   		            		element.standardAtomicWeight,
   		            		element.PeriodNum,
   		            		element.groupNum,
   		            		element.groupName,
   		            		element.halfLife,
   		            		(element.alternateSpelling.isEmpty())?"N/A":element.alternateSpelling);
       				if (element.groupNum == -1 && 57 <= element.atomicNum && element.atomicNum <= 70){
       			    	label[tempGroupNum8][8] = new Label();
       	            	label[tempGroupNum8][8].setText(" " + element.symbol + " ");
       	            	label[tempGroupNum8][8].autosize();
       	            	switch(element.groupName) {
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
       	            	label[tempGroupNum8][8].setOnMouseEntered((event)->{
       						displayLabel.setText(content);
       						displayLabel.setTextAlignment(TextAlignment.CENTER);
       						displayPane.getChildren().clear();
       						displayPane.getChildren().add(displayLabel);
       						displayPane.setAlignment(Pos.CENTER);
       					});
       	                matrix.add(label[tempGroupNum8][8], tempGroupNum8, 8);
       	                tempGroupNum8++;
       			    }
       			    else if (element.groupNum == -1 && 89 <= element.atomicNum && element.atomicNum <= 102){
       				    	label[tempGroupNum9][9] = new Label();
       		            	label[tempGroupNum9][9].setText(" " + element.symbol + " ");
       		            	label[tempGroupNum9][9].autosize();
       		            	
       		            	switch(element.groupName) {
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
       		            	label[tempGroupNum9][9].setOnMouseEntered((event)->{
           						displayLabel.setText(content);
           						displayLabel.setTextAlignment(TextAlignment.CENTER);
           						displayPane.getChildren().clear();
           						displayPane.getChildren().add(displayLabel);
           						displayPane.setAlignment(Pos.CENTER);
           					});
       		                matrix.add(label[tempGroupNum9][9], tempGroupNum9, 9);
       		                tempGroupNum9++;
       				    	}
       			    else {
       			    	label[element.groupNum][element.PeriodNum] = new Label();
       			    	label[element.groupNum][element.PeriodNum].setText(" " + element.symbol + " ");
       			    	label[element.groupNum][element.PeriodNum].autosize();
       			    	switch(element.groupName) {
       			    	case "Alkali Metal": label[element.groupNum][element.PeriodNum].setTextFill(Color.DARKRED); break;
       			    	case "Nobel Gas": label[element.groupNum][element.PeriodNum].setTextFill(Color.CADETBLUE); break;
       			    	case "Alkaline Earth Metal": label[element.groupNum][element.PeriodNum].setTextFill(Color.DARKSLATEBLUE); break;
       			    	case "Boron Group": label[element.groupNum][element.PeriodNum].setTextFill(Color.CORAL); break;
       			    	case "Carbon Group": label[element.groupNum][element.PeriodNum].setTextFill(Color.CRIMSON); break;
       			    	case "Pnictogen": label[element.groupNum][element.PeriodNum].setTextFill(Color.DARKGOLDENROD); break;
       			    	case "Chalcogen": label[element.groupNum][element.PeriodNum].setTextFill(Color.DARKGRAY); break;
       			    	case "Halogen": label[element.groupNum][element.PeriodNum].setTextFill(Color.DARKOLIVEGREEN); break;
       			    	default: label[element.groupNum][element.PeriodNum].setTextFill(Color.BLACK);
       			    	}
       			    	label[element.groupNum][element.PeriodNum].setOnMouseEntered((event)->{
       			    		displayLabel.setText(content);
       			    		displayLabel.setTextAlignment(TextAlignment.CENTER);
       			    		displayPane.getChildren().clear();
       			    		displayPane.getChildren().add(displayLabel);
       			    		displayPane.setAlignment(Pos.CENTER);
       			    		});
       			    	matrix.add(label[element.groupNum][element.PeriodNum], element.groupNum, element.PeriodNum);
       			    	}
       			}
       			matrix.setPrefSize(400, 200);
       			BorderPane Stack = new BorderPane();
       			Stack.setTop(matrix);
       			Stack.setCenter(displayPane);
       			Scene scene = new Scene(Stack,600,600);
       			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
       			primaryStage.setScene(scene);
       			primaryStage.show();
       		} catch(Exception e) {
       			e.printStackTrace();
       		} 
        }
    

/**
	 * Massages and parses a 9-field record into its component elements
	 * 
	 * @param recordToParse
	 *        the full text record/line to parse
	 * @return the individual elements of the supplied record
	 */
   public static void main (String [] args) {
	   launch(args);
   }
    private static String[] parseRecord( String recordToParse )
        {
        return recordToParse.replace( "\"", "" ).split( "\t", 9 ) ;
        }

    }
class ButtonHandler implements EventHandler<ActionEvent>{

	@Override
	public void handle(ActionEvent event) {
		// TODO Auto-generated method stub
		System.out.println("Mouse Entered!");
	}
	
}
