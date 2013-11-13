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
	
 // Creates final output files
	
import data.appData;
import java.io.File;	
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.BufferedReader;

public class FinalDocument		{

	private BufferedReader reader;
	private BufferedWriter writer;
	private boolean summary[];
	private int lineNo;

	public FinalDocument( File doc, boolean lines[] )	{
	
		try			{
	
			this.summary = lines;
			this.lineNo = 0;
	
			doc = new File( appData.inputDocs, doc.getName() );
			this.reader = new BufferedReader( new FileReader( doc ) );
			
			doc = new File( appData.outDocs, doc.getName() );
			this.writer = new BufferedWriter( new FileWriter( doc ) );
			
		}catch(Exception e)		{
			System.err.println("File ERR: "+e.getMessage());
		}//End Of Try Catch
		
	}//End Of Constructor

	
	public void create()				{
	
		String sentence, line[];
	
		try		{
		
			while( (sentence=this.reader.readLine())!=null )		{
			
				line = sentence.split("\\.");
				this.write( line );
		
			}//End Of Loop
		
		}catch(Exception e)	{
			System.err.println("File ERR: "+e.getMessage());
		}//End Of Try Catch		
	
	}//End Of Method
	
	
	private void write( String lines[] ) throws Exception		{
	
		String str;
		
		for(int i=0; i<lines.length; i++)			{
		
			str = lines[i].trim();
			
			if( !str.equals("") )		{
			
				if( this.summary[this.lineNo] == true )
					this.writer.write( str+".\n" );
				
				this.lineNo++;
				
			}//End Of If
			
		}//End Of Loop		
		
	}//End Of Method
	
	
	public void close()			{
	
		try		{
	
			this.reader.close();
			this.writer.close();
		
		}catch(Exception e)	{
			System.err.println("File ERR: "+e.getMessage());
		}//End Of Try Catch		
	
	}//End Of Method
	
	
}//End Of Class