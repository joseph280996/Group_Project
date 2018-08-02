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

import java.io.File ;
import java.io.FileNotFoundException ;
import java.util.Scanner ;
import java.util.HashMap;
import java.util.ArrayList;


/**
 * @author David M Rosenberg
 * @version 0.1.1 2017-08-11 changed data file column indices to constants
 * @version 0.1.0 2016-12-10 initial version
 */
public class LoadPeriodicTable
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
    public static void main( String[] args )
        {
    	HashMap<Integer,HashMap<Integer,ArrayList<Element>>> periodicTable;
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
            	Element element = new Element( headerFields[SYMBOL], 
                               headerFields[ELEMENT], 
                               Integer.parseInt(headerFields[ATOMIC_NUMBER]), 
                               headerFields[ATOMIC_WEIGHT], 
                               Integer.parseInt(headerFields[PERIOD]),
                               Integer.parseInt(headerFields[GROUP_NUMBER]),
                               headerFields[GROUP_NAME],
                               headerFields[HALF_LIFE], 
                               headerFields[ALTERNATE_SPELLING]);
            	// there appear to be odd formatting choices here - they exist so we can
            	// use the same format as above
            	System.out.printf( "%6s  %-13s  %-13s  %-22s  %-8s  %-7s  %-20s  %-16s  %-25s%n",
            	                   String.format( "%-3s ", elementFields[SYMBOL] ),
            	                   elementFields[ELEMENT],
            	                   String.format( "     %3d", atomicNumber ),
            	                   elementFields[ATOMIC_WEIGHT],
            	                   String.format( "  %2d", period ),
            	                   ( group < 1
                    				   ? ""
                    				   : String.format( "  %2d", group ) ), 
            	                   elementFields[GROUP_NAME], 
            	                   elementFields[HALF_LIFE],
            	                   elementFields[ALTERNATE_SPELLING] ) ;
            	}
            }
        catch ( FileNotFoundException e )
            {
            System.out.println( "Can't open file: periodic-table.dat" ) ;
            System.exit( 0 );
            }
        
        }
    

	/**
	 * Massages and parses a 9-field record into its component elements
	 * 
	 * @param recordToParse
	 *        the full text record/line to parse
	 * @return the individual elements of the supplied record
	 */
    private static String[] parseRecord( String recordToParse )
        {
        return recordToParse.replace( "\"", "" ).split( "\t", 9 ) ;
        }

    }
