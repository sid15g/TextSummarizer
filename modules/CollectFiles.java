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
	
 // Provides an option to choose a file
	
import java.io.File;
import java.util.List;
import java.util.ArrayList;
	
public class CollectFiles		{

	private static List<File> fileList;
	private File[] files;

	public CollectFiles( String path )		{
	
		this.files = new File( path ).listFiles();
		this.fileList = new ArrayList<File>();
		
		this.checkSelectedFiles();
	
	}//End Of Constructor
	
	
	
	private void checkSelectedFiles()		{
	
		int i;
		String fileName = null;
	
		for( i=0; i<this.files.length; i++ )		{
		
			fileName = this.files[i].getName();

			int index = fileName.lastIndexOf('.');
			
			if (index > 0)		{
			
				String extension = fileName.substring(index+1);
				
				if( !extension.equals("txt") )		{
					System.err.println("File Error: Invalid Extension");
				}else		{
//					System.out.println(fileName);
					this.fileList.add( this.files[i] );
				}
				
			}else			{
			
				if( this.files[i].isFile() )
					this.fileList.add( this.files[i] );
			
			}//End Of IF Else
			
		}//End Of Loop

	}//End Of Method
	
	
	
	public List<File> getList()		{
	
		return this.fileList;
	
	}//End Of Method	
	
}//End Of Class