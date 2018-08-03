package edu.wit.dcsn.comp1050.project;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Element {
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
    
	String symbol;
	String elementName;
	int atomicNum;
	String standardAtomicWeight;
	int PeriodNum;
	String groupName;
	String halfLife;
	String alternateSpelling;
	int groupNum;
	public Element(String symbol,
						   String elementName,
						   int atomicNum,
						   String standardAtomicWeight,
						   int PeriodNum,
						   int groupNum,
						   String groupName,
						   String halfLife,
						   String alternateSpelling)
	{
		this.symbol = symbol;
		this.elementName = elementName;
		this.atomicNum = atomicNum;
		this.standardAtomicWeight = standardAtomicWeight;
		this.PeriodNum = PeriodNum;
		this.groupName = groupName;
		this.halfLife = halfLife;
		this.alternateSpelling = alternateSpelling;
	}
	
	public Element(String[] recordParse) {
		//String[] elementFields = parseRecord(recordParse);
		this.atomicNum = Integer.parseInt(recordParse[ATOMIC_NUMBER]);
		this.groupNum = (recordParse[GROUP_NUMBER].equals( "" ) ? -1 : Integer.parseInt(recordParse[GROUP_NUMBER]));
		this.PeriodNum = Integer.parseInt(recordParse[PERIOD]);
		this.symbol = recordParse[SYMBOL];
		this.elementName = recordParse[ELEMENT];
		this.standardAtomicWeight = recordParse[ATOMIC_WEIGHT];
		this.alternateSpelling = recordParse[ALTERNATE_SPELLING];
		this.groupName = recordParse[GROUP_NAME];
		this.halfLife = recordParse[HALF_LIFE];
	}
	
}
