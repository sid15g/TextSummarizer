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
	
package preprocess;

 // Remove Stopwords from the documents
	
import data.appData;
import java.io.File;
import java.util.List;
import java.util.HashMap;
import java.util.Iterator;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.LinkedHashSet;

public class RemoveStopwords		{

	private static LinkedHashSet<String> stopwords;
	private LinkedHashSet<String> set, lineSet;
	private HashMap<String,Integer> map;
	private File wordsFile, docFile;
	private BufferedWriter writer;
	private int totalLine;
	
	
	public RemoveStopwords()			{
	
		this.wordsFile = new File (appData.stopwords);
		this.stopwords = new LinkedHashSet<String>();
	
	}//End Of Constructor
	
	
	public RemoveStopwords( File doc )			{
		
		this.lineSet = new LinkedHashSet<String>();
		this.map = new HashMap<String,Integer>();
		this.set = new LinkedHashSet<String>();
		this.docFile = doc;
		this.totalLine = 0;
		
		try			{
		
			File newDoc = new File( appData.tempDocs, this.docFile.getName() );
			
			FileWriter fStream = new FileWriter( newDoc );
			this.writer = new BufferedWriter( fStream );
		
		}catch( Exception e )	{
			System.err.println("IO Error: " + e.getMessage());
		}//End Of Try Catch
		
	}//End Of Constructor
	
	
	// Method to all stop words to the list
	public void addStopTerms()				{
	
		try		{

			int ind;
			String words[], line, tempStr;
			BufferedReader reader = new BufferedReader( new FileReader( this.wordsFile ) );	
			
			while((line=reader.readLine())!=null)		{
			
				line = this.Trim( line );
				words = line.split(" ");
				
				for(ind=0; ind<words.length; ind++)		{

					tempStr = words[ind];
					this.stopwords.add(tempStr);
					
				}//End Of For Loop	
				
			}//End Of While Loop
		
		}catch(Exception e)		{
			System.err.println("IO Error: " + e.getMessage());
		}//End Of Try Catch	
		
	}//End Of Method	
	
	
	// Method to remove the stopwords
	public void remove()		{
		
		this.getTermSet(); 
//		this.outputFinalTerms();	
	
	}//End Of Method
	
	
	// Method to collect terms from the document
	private void getTermSet()			{
		
		try		{
		
			int temp, ind, key;
			String words[], line[], sentence, tempStr;
	
			BufferedReader reader = new BufferedReader(new FileReader( this.docFile ));	
			
			while((sentence=reader.readLine())!=null)		{
			
				if( sentence.equals("") )
					continue;
					
				line = sentence.split("\\.");
				
				for( int i=0; i<line.length; i++ )		{
				
					line[i] = this.Trim( line[i] );
					words = line[i].split(" ");
					this.lineSet.clear();
					this.totalLine++;
					
					for(ind=0; ind<words.length; ind++)			{
					
						tempStr = words[ind];
						
						if( tempStr.length() > 2 && !this.stopwords.contains(tempStr) )				{
						
							this.writer.write(tempStr+" ");
						
							if( !this.lineSet.contains(tempStr) )		{
					
								this.lineSet.add(tempStr);
								this.set.add( tempStr );						
							
								if( this.map.containsKey(tempStr) )		{
								
									key = this.map.get(tempStr);
									key++;
									this.map.put( tempStr, key );
								
								}else		{
									this.map.put( tempStr, 1 );
								}//End Of If Else
								
							}//End Of Inner IF
							
						}//End Of MainIF
						
					}//End Of For(words) Loop
				
					this.writer.write("\n");
				
				}//End Of Main For(lines) Loop
				
			}//End Of While Loop
				
			this.writer.close();
			
		}catch(Exception e)			{
			System.err.println("IO Error: " + e.getMessage() );
		}//End Of Try Catch

	}//End Of Method
	
	
	// Method to trim the string i.e. remove spaces, tabs and special characters
	private String Trim( String str )		{
	
		str = str.toLowerCase();
		str = str.trim();
		str = str.replaceAll( "\t", "" );
		str = str.replaceAll( "[^\\p{L}\\p{Z}]", "" );
		
		char singleQ = (char)226;
		char dblQ = (char)339;
		
		str = str.replaceAll( ((Character)singleQ).toString() , "" );
		str = str.replaceAll( ((Character)dblQ).toString() , "" );
		
		return str;
	
	}//End Of Method
	
	
	
	// Method to output the final set of terms
	private void outputFinalTerms()		{
	
		Iterator<String> iter = this.set.iterator();
		
		while(iter.hasNext())
			System.out.println(iter.next());
		
	}//End Of Method
	
	
	public HashMap<String,Integer> getMap()			{
	
		return this.map;
	
	}//End Of Method
	
	
	public LinkedHashSet<String> getSet()			{
	
		return this.set;
	
	}//End Of Method

	
	public int getLines()			{
	
		return this.totalLine;
	
	}//End Of Method	
	
	
}//End Of Class
