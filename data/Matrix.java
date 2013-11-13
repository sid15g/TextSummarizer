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
	
 // Stores the matrix of every input document
	
public class Matrix		{

	private float tfIdf[][];
	private int nextRow;
	
	public Matrix( int rows )			{
	
		this.tfIdf = new float[rows][];
		this.nextRow = 0;
	
	}//End Of Constructor
	
	public void init( float[] col )			{
	
		this.tfIdf[ this.nextRow ] = col;
		
		this.nextRow++;
	
	}//End Of Method
	
	
	public int length()			{
	
		return this.tfIdf.length;
	
	}//End Of Method
	

	public void print()		{

	
		for( int i=0; i< this.tfIdf.length; i++ )		{
	
			for( int j=0; j< this.tfIdf[i].length; j++ )
				System.out.print( this.tfIdf[i][j] + " " );
	
			System.out.println("\n");
		
		}//End Of Inner Loop
	
	}//End Of Method

	
	public float[][] getMatrix()			{
	
		return this.tfIdf;
	
	}//End Of Method
	
}//End Of Class