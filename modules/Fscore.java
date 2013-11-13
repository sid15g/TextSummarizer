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
	
 // Calculates F-Score of the input Matrix
	
import data.Matrix;
	
public class Fscore	{

	private float freq[][];					//Frequence Matrix
	private float FM[][];					//FMeasure
	private float FS[];						//FScore
	private float beta;
	private int row;						//No. Of Sentences
	
	public Fscore( Matrix m )	{
	
		this.beta = 1.00F;
		this.row = m.length();
		this.freq = m.getMatrix();
		this.FS = new float[this.row];
		this.FM = new float[this.row][this.row];
	
	}//End Of Constructor	
	
	
	public void setBeta( float b )			{
	
		this.beta = b;
		
	}//End Of Method
	
	
	private float min( float a, float b )		{
	
		return a>b?b:a;
	
	}//End Of Method
	
	
	public void fMeasure()		{
	
		float intersection, precision, recall, fMeasure, sum, min, m1, m2;
		int column = this.freq[0].length;
		
		this.beta *= this.beta;
		
		for (int i=0; i<this.row; i++)				{	
			
			for(int j=i+1; j<this.row ; j++)		{	
			
				intersection = precision = recall = fMeasure = m1 = m2 = 0.00F;
				
				for(int k=0; k<column; k++)		{
				
					min = this.min( freq[i][k], freq[j][k] );
					
					if(  min > 0.00F )			
						intersection += min;
					
					if( freq[i][k] > 0.00F )
						m1 += freq[i][k];
					
					if( freq[j][k] > 0.00F )
						m2 += freq[j][k];
					
				}//End Of Loop(k)
				
				precision = intersection/m1;
				recall = intersection/m2;
				sum = precision + recall;
				
				if ( sum > 0.00F )										//else fMeasure already 0
					fMeasure = ((1+beta)*precision*recall)/(sum*beta);
				
				if( Double.isNaN( fMeasure ) )
					fMeasure = 0.00F;
					
				FM[i][j] = FM[j][i] = fMeasure;
			
			}//End Of Loop(j)
			
		}//End Of Loop(i)
		
		this.calculateFS();
		
	}//End Of Method
	
	
	private void calculateFS()				{
	
		int column = this.freq.length;	
	
		for(int i=0; i<this.row; i++)			{
		
			FS[i] = 0.00F;
			
			for(int j=0; j<column; j++)			{
			
				if( i==j )
					FM[i][j] = 0.00F;
					
				if( !Double.isInfinite( FM[i][j] ) )
					FS[i] += FM[i][j];
				else
					FS[i] += FS[i]/(float)(i+1);
					
			}//End Of Loop(j)
			
		}//End Of Loop(i)
		
	}//End Of Method
	
	
	public void display()				{
	
		int column = this.freq.length;
		
		for(int i=0; i<this.row; i++)			{
		
			for(int j=0; j<column; j++)
				System.out.print(FM[i][j]+" ");
	
			System.out.println("\n");
			
		}//End Of Loop
		
		System.out.println("\n\n\n");
		
		for(int i=0; i<this.row; i++)
			System.out.println(FS[i]);
			
	}//End Of Method
	
	
	public float[] getResult()			{
	
		return this.FS;
	
	}//End Of Method

}//End Of Class