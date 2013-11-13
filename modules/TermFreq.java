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
	
 // Calculate Term Frequency of each term of a line. and repeat it for every line of every doc and create a matrix for every Doc individually.

import data.Index;
import data.Matrix;
import data.appData; 
import java.io.File;
import java.util.List;
import java.util.HashMap;
import java.io.FileReader;
import java.util.Iterator;
import java.io.BufferedReader;	
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
	
public class TermFreq	{

	private LinkedHashMap<String, Integer> map;
	private LinkedHashSet<String> globalTerms;
	private BufferedReader reader;
	private Index myIndex;
	private Matrix myMat;
	private File doc;

	public TermFreq( File f, List<Index> details )	{
	
		this.map = new LinkedHashMap<String, Integer>();
		this.doc = f;
		
		Iterator<Index> iter = details.iterator();
		Index temp;
		
		while( iter.hasNext() )			{
		
			temp = iter.next();
			
			if( temp.isFile( this.doc ) )				{
			
				this.myIndex = temp;
				this.globalTerms = temp.getSet();
				this.myMat = new Matrix( temp.getLines() );
			
			}//End Of If
			
		}//End Of Loop
		
		
		try		{
		
			FileReader fr = new FileReader( this.doc );
			this.reader = new BufferedReader( fr );
		
		}catch( Exception e )		{
			System.err.println( "File  ERR: " + e.getMessage() );
		}//End Of Try Catch
	
	}//End Of Constructor
	
	
	public void calculate()			{
	
		try			{
		
			String line, words[];
			String tempStr;
			float termFreq[];
			int key;
		
			while( (line=this.reader.readLine())!=null )		{
			
				words = line.split(" ");
				this.map.clear();
				
				for( int i=0; i<words.length; i++ )		{
				
					tempStr = words[i];
					
					if( !this.map.containsKey(tempStr) )		{
						this.map.put( tempStr, 1 );
					}else		{
					
						key = this.map.get( tempStr );
						key++;
						this.map.put( tempStr, key );
						
					}//End Of If Else
				
				}//End Of For Loop
				
				termFreq = globalize();
				this.myMat.init( termFreq );
				
			}//End Of While Loop
			
//			this.myMat.print();
			this.myIndex.setMatrix( this.myMat );								//Saving the TD-IDF Matrix
			this.reader.close();
		
		}catch( Exception e )		{
			System.err.println( "File  ERR: " + e.getMessage() );
		}//End Of Try Catch
	
	}//End Of Method
	
	
	
	private float[] globalize()			{
	
		HashMap<String, Integer> idfMap = this.myIndex.getMap();
		int size = this.globalTerms.size();
		float freq[] = new float[size];
		float lines = (float)this.myIndex.getLines();
		String word;
		float idf;
		int i=0;
		
		Iterator<String> itr = this.globalTerms.iterator();
		
		while( itr.hasNext() )		{
		
			word = itr.next();
			
			if( this.map.containsKey(word) )		{
				freq[i] = (float)this.map.get(word)/(float)size;					//TF
				
				idf = lines/(float)(int)idfMap.get(word);							//IDF
				freq[i] = (float) Math.log( idf );
			}else	{
				freq[i] = 0.00F;
			}
			
			if( Double.isNaN( freq[i] ) )
				freq[i] = 0.00F;
			
			i++;
		
		}//End Of Loop
		
		return freq;
	
	}//End Of Method
	
	
}//End Of Class