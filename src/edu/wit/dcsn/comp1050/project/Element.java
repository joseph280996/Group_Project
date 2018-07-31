package edu.wit.dcsn.comp1050.project;


public class Element {
	String symbol;
	String elementName;
	int atomicNum;
	String standardAtomicWeight;
	int PeriodNum;
	String groupName;
	String halfLife;
	String alternateSpelling;
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
}
