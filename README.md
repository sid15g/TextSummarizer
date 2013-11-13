
What is Text Summarizer?
Text Summarizing has been very important part of data mining. It summarizes the given documents based on similarity measures like F-Measure and Cosine Similarity.

How does it work?
It collects the input document and removes stopwords from the documents. It stems the words using Porter stemming and then uses TF-IDF to form similarity matrix.
This matrix is provided to the modules which calculates similarity. Module could implement F-Measure or Cosine Similarity. The module to be implemented is asked as input during runtime. And then the result is given in a file with same file name as input file name.

How to run?

	Use the make file to first clean the class files:
		make clean
	Then compile the code by
		make
	Now use the command
		make jar
	to create the jar file.
	
	Create a folder named "docs" which contains all the input files
	Create a folder inside "docs" named "outputs" to save the outputs in the directory

	Place the jar file in the same folder where "docs" exists
	
		-.<current directory>
		|
		|-summarizer.jar
		|-docs/<input files>
		|-docs/outputs/<expected output files>
		
	Use command Prompt or Terminal to run the command
		java -jar <filename>.jar