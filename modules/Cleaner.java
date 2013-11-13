/**
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it.
 * This code is distributed in the hope that it will be useful.
 *
 * Developed By Students Of <The LNM Institute Of Information Technology, Jaipur>.
 *	- Garvit Sharma: y10uc112
 *	- Nandita Jain: y10uc188
 *	- Parul Chaudhary: y10uc209
 *	- Shubhra Kabra: y10uc319
 *	- Siddhant Goenka: y10uc321
 * 
 */
	
package	modules;
	
 // Cleans or deletes the previous documents

import java.util.Iterator; 
import java.util.List;
import java.io.File;
import data.appData;
	
	
public class Cleaner	{

	private List<File> stemDocs, tempDocs, outputDocs;

	public Cleaner()	{
	
		this.stemDocs = new CollectFiles( appData.stemmedDocs ).getList();
		this.tempDocs = new CollectFiles( appData.tempDocs ).getList();
		this.outputDocs = new CollectFiles( appData.outDocs ).getList();
	
	}//End Of Constructor
	

	public void clean()		{
	
		Iterator<File> iter;
		
		iter = this.stemDocs.iterator();
		while( iter.hasNext() )
			iter.next().delete(); 
		
		
		iter = this.tempDocs.iterator();
		while( iter.hasNext() )
			iter.next().delete();		
		
		iter = this.outputDocs.iterator();
		while( iter.hasNext() )
			iter.next().delete();		
		
	}//End Of Method
	
}//End Of Class