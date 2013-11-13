ifdef SystemRoot
	RM = del /Q
	CLASS = \*.class
	XML = \*.xml
	
else
	ifeq ($(shell uname), Linux)
		RM = rm -rf
		CLASS = /*.class
		XML = /*.xml
	endif
endif

JCC = javac
JFLAGS = -Xlint:all -g

Start.class: Start.java	
	$(JCC) $(JFLAGS) Start.java
	
jar:
	jar cmf Manifest.txt summarizer.jar Start.class data modules preprocess
	
clean:
	$(RM) summarizer.jar
	$(RM) Start.class
	$(RM) data$(CLASS)
	$(RM) modules$(CLASS)
	$(RM) preprocess$(CLASS)	