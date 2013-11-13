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
	
 // Stems every file in input
	
import data.appData;
import java.io.File;	
import java.util.List;
import preprocess.Stemmer;
import java.util.Iterator;
import java.io.PrintStream;
import modules.CollectFiles;
import java.io.FileOutputStream;
	
public class Stem extends Stemmer	{

	public Stem()		{
	
		this.myStem();
	
	}//End Of Constructor
	
	
	private void myStem()			{
	
		CollectFiles ls = new CollectFiles( appData.inputDocs );
		List<File> docList = ls.getList();
		PrintStream out;
		String docName;
		File doc;
	
		try 	{
			
			Iterator<File> iter = docList.iterator();
			
			while( iter.hasNext() )		{
			
				docName = iter.next().getName();
				doc = new File( appData.stemmedDocs, docName );
				out = new PrintStream( new FileOutputStream( doc ) , true );
				System.setOut( out );
				docName = appData.inputDocs +"/"+ docName;
				super.main( new String[]{ docName } );
				out.close();
				
			}//End Of Loop
		
		}catch(Exception e) 	{
			System.err.println( e.getMessage() );
		}//End Of Try Catch
	
	}//End Of Method

	
}//End Of Class