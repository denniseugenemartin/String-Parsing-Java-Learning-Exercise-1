import java.io.*;


public class fileParser
{ 
public static void main(String args[]) throws IOException

  {
    // Link program to its input and output files. there will also
    // be a set of questions and statments to input from.
     File inputFile = new File("input.txt");
     File outputFile = new File("output.txt");
     File filetoChange = new File("fileToChange.txt");

     // if the output file does not exist, create it.
    if (!outputFile.exists()) 
    {
      outputFile.createNewFile();
    }

    // create a readers and writers for various input/output files.
    BufferedReader reader = new BufferedReader(new FileReader(inputFile));
    BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
    TextEditor editor1 = new TextEditor();
    
    editor1.extractTextBetweenQuotes(inputFile);
    editor1.replaceTextBetweenQuotes(filetoChange, outputFile);

    
    writer.close();
    reader.close();
    
    
  }
 
}
