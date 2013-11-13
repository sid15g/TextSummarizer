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

 
 // Starts the Application

 import preprocess.RemoveStopwords;
 import modules.FinalDocument;
 import modules.CollectFiles;
 import java.io.PrintStream;
 import java.util.ArrayList;
 import java.util.Iterator;
 import modules.Threshold;
 import modules.TermFreq;
 import java.io.Console;
 import modules.Cleaner;
 import modules.Fscore;
 import modules.Cosine;
 import java.util.List;
 import modules.Stem;
 import java.io.File;
 import data.appData;
 import data.Index;
	
public class Start	{

	private List<Index> details;
	private List<File> docList;
	private PrintStream stdout;
	private CollectFiles LS;
	
	public Start()	{
		
		this.stdout = new PrintStream( System.out, true );
		Stem stemmer = new Stem();
		this.restoreIO();
		this.LS = new CollectFiles( appData.stemmedDocs );
		this.docList = this.LS.getList();
		this.details = new ArrayList<Index>();
		
	}//End Of Constructor
	

	public static void main( String args[] )			{
	
		new Cleaner().clean();
		Start app = new Start();
		app.run();
	
		System.out.println("\nDone - Check the output(s) in './docs/output/' directory.");
		System.out.println("Thank You!");
	
	}//End Of Main
	
	
	public void run()				{					//Not a thread

		new RemoveStopwords().addStopTerms();
		
		System.out.print("\nEnter you choice(1/2)\n 1. F-Score\n 2. Cosine \n -> ");
		Console in = System.console();
		String ch = in.readLine();
		int choice = 0;
		
		Iterator<File> iter = this.docList.iterator();
		
		while( iter.hasNext() )			{
	
			File doc = iter.next();
			
			RemoveStopwords rs = new RemoveStopwords( doc );
			rs.remove();
			
			doc = new File( appData.tempDocs, doc.getName() );	
			Index myIndex = new Index( doc, rs.getSet(), rs.getMap(), rs.getLines() );
			this.details.add( myIndex );
			
			//---Preprocessing Complete---//
			
			TermFreq tf = new TermFreq( doc, this.details );
			tf.calculate();
	
			Threshold ts = null;
			
			try			{
			
				if( choice == 0 )
					choice = Integer.parseInt( ch );
			
				if( choice == 1 )		{
				
					Fscore fs = new Fscore( myIndex.getMatrix() );
//					fs.setBeta( 1.00001F );
					fs.fMeasure();
//					fs.display();	

					ts = new Threshold( fs.getResult() );
				
				}else if( choice == 2 )		{
				
					Cosine cs = new Cosine( myIndex.getMatrix() );
					cs.cMeasure();
//					cs.display();			

					ts = new Threshold( cs.getResult() );					
				
				}else			{
					choice = -1;
					System.err.println("ERR: Wrong Choice");
				}//End Of If Else
			
			}catch(Exception e)		{
				System.err.println("ERR: Unknown Input");
				System.exit(0);
			}//End Of Try Catch
				
			ts.find();			
		
			FinalDocument fDoc = new FinalDocument( doc, ts.getSelectedLines() );
			fDoc.create();
			fDoc.close();
		
		}//End Of While Loop
		
		
	}//End Of Method
	
	
	private void restoreIO()		{
		
		try 	{
			System.setOut( this.stdout );
		}catch(Exception e) 	{
			System.err.println( e.getMessage() );
		}//End Of Try Catch
	
	}//End Of Method	
	
}//End Of Class