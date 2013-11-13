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
	
package	data;
	
 // Maintains record of every input document and its details relevant for other modules
	
import java.io.File;
import java.util.HashMap;
import java.util.LinkedHashSet;
	
public class Index	{

	private HashMap<String,Integer> map;
	private LinkedHashSet<String> set;	
	private Matrix mat;
	private int lines;
	private File doc;

	public Index( File f, LinkedHashSet<String> hs, HashMap<String, Integer> hm, int l )	{
	
		this.map = hm;
		this.set = hs;
		this.doc = f;
		this.lines = l;
		this.mat = null;
	
	}//End Of Constructor
	
	
	public boolean isFile( File f )		{
	
		if( this.doc.equals(f) )
			return true;
		else
			return false;
	
	}//End Of Method	
	
	
	public HashMap<String,Integer> getMap()			{
	
		return this.map;
	
	}//End Of Method
	
	
	public LinkedHashSet<String> getSet()			{
	
		return this.set;
	
	}//End Of Method
	

	public int getLines()			{
	
		return this.lines;
	
	}//End Of Method
	
	
	public void setMatrix( Matrix m )			{
	
		this.mat = m;
	
	}//End Of Method
	
	
	public Matrix getMatrix()			{
	
		return this.mat;
	
	}//End Of Method	
	
	
}//End Of Class