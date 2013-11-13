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
	
 // Calculates the threshold of the result of the similarity measures.
	
public class Threshold		{

	private boolean selectedLines[];
	private float mean, stdDev;
	private float[] measures;

	public Threshold( float[] mat )		{
	
		this.selectedLines = new boolean[mat.length];
		this.measures = mat;
		this.mean = 0.00F;
	
	}//End Of Constructor
	

	public void find()		{
	
		for(int i=0; i<this.measures.length; i++)
			this.mean += measures[i];
	
		this.mean /= (float)this.measures.length;
	
		this.stdDev = getStdDev();
	
	}//End Of Method
	
	
	private float getStdDev()			{
	
		float temp, sum=0.00F;
	
		for(int i=0; i<this.measures.length; i++)		{
		
			temp = this.measures[i] - this.mean;
			sum += temp*temp;
		
		}//End Of Loop
		
		float variance = sum/(float)this.measures.length;
		
		return (float)Math.sqrt( variance );
	
	}//End Of Method
	
	
	public boolean[] getSelectedLines()				{
	
		float lowerLimit = this.mean;
		float upperLimit = this.mean + this.stdDev;
		
		for(int i=0; i<this.selectedLines.length; i++)			{
		
			if( this.measures[i] >= lowerLimit && this.measures[i] <= upperLimit )		{
				this.selectedLines[i] = true;
			}else
				this.selectedLines[i] = false;
			
		}//End Of Loop
	
		return this.selectedLines;
	
	}//End Of Method
	
	
}//End Of Class