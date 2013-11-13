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
	
 // Calculates Cosine Similarity of the input Matrix
	
import data.Matrix;
	
public class Cosine	{

    private float freq[][];					//Frequence Matrix
	private float CM[][];					//CosineMeasure
	private float RS[];						//RScore
	private int row;						//No. Of Sentences
	

	public Cosine(Matrix m)	{
	
		this.row = m.length();
		this.freq = m.getMatrix();
		this.RS = new float[this.row];
		this.CM = new float[this.row][this.row];
	
	}//End Of Constructor
	

	public void cMeasure()	{
	     
        float upper, sq1, sq2, sqSum, lower, cMeasure;
		int column = this.freq[0].length;
		 
		for (int i=0; i<this.row; i++)			{
		    
			for (int j=i+1; j<this.row; j++)		   {
			
				upper = lower = sq1 = sq2 = sqSum = cMeasure = 0.00F;
			   
				for(int k=0;k<column; k++)				{
				
					upper += freq[i][k]*freq[j][k];
					sq1 += freq[i][k]*freq[i][k];
					sq2 += freq[j][k]*freq[j][k];
					
				}//End of Loop (k)
				
				sqSum = sq1 * sq2;
				lower = (float)Math.sqrt(sqSum);

				if(lower!=0)							
					cMeasure = upper/lower;		

				if( Double.isNaN( cMeasure ) )
					cMeasure = 0.00F;
					
				CM[i][j] = cMeasure;
			 
			}//End of Loop(j)
		}//End of Loop(i)
		
		this.copyCM();
		
	}//End Of Method
	
	
	
	private void copyCM()				{
	
		int column = this.freq.length;
		
		for(int i=0; i<this.row; i++)			{
			for(int j=0; j<column; j++)			{
			
				if(i==j)
					CM[i][j]=0.00F;
				else if(i>j)
					CM[i][j]=CM[j][i];
					
			}//End Of Loop(j)
			
		}//End Of Loop(i)
	
	    for(int i=0; i<this.row; i++)			{
		
			RS[i]=0.00F;
			
			for(int j=0; j<column; j++)
				RS[i] += CM[i][j];
				
		}//End Of Loop
		
	}//End Of Method
	   
	   
	   
	public void display()				{	
	
		int column = this.freq.length;
	
		for(int i=0; i<this.row; i++)			{		
		
			for(int j=0; j<column; j++)
				System.out.print(CM[i][j]+" ");
	
			System.out.println("\n");
			
		}//End Of Loop
		
		System.out.println("\n\n\n");
		
		for(int i=0; i<this.row; i++)
			System.out.println(RS[i]);
			
	}//End Of Method   
	   
	 
	
	public float[] getResult()			{
	
		return this.RS;
	
	}//End Of Method	 
	  
	  
}//End Of Class