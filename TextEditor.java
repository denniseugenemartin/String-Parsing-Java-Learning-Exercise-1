import java.io.*;
import java.util.ArrayList;
import java.util.Random;


// TextEditor class has two main functions. a method extractTextBetweenQuotes,
// which will populate a set of arrays with the text from between quotation marks,
// depending on the punctuation found. a method replaceTextBetweenQuotes which will
// take an input file and remove the text between the quotation marks with random
// strings taken from the previous method. If there are not enough stored strings to
// do this, a default phrase will be inserted instead.
public class TextEditor {

   // create a set of arrayLists to hold statements to be
   // used by the TextEditor object. each list will hold
   // strings based on it's puncutation type.
   private static ArrayList<String> statementArray = new ArrayList<String>();
   private static ArrayList<String> questionArray = new ArrayList<String>();
   private static ArrayList<String> exclamationArray = new ArrayList<String>();

   // method will check a given character to see if it is a
   // puncuation, returning true if so. otherwise will return false.
   private boolean isPunctuation(char charToAnalyze) {
      if (charToAnalyze == '.' || charToAnalyze == '!' || charToAnalyze == '?') {
         return true;
      } else {
         return false;
      }

   }

   // method will check a character to see if it matches common quotation
   // marks, including directional quotations found in unicode. if it matches
   // one of these, it will return true, otherwise it will return false.
   private boolean isQuotation(char charToAnalyze){
       int charConvertedToInt = (int)charToAnalyze;
      if (charConvertedToInt == 8220 || charConvertedToInt == 8221 || charToAnalyze == '\"'){
         return true;
      }
      else{
         return false;
      }
   }

   public void extractTextBetweenQuotes(File fileToParse) throws IOException {
     
      // create a reader for the files passed into the method. each string 
      // will, in turn, be passed to the stringToParse variable as read from the
      // file.
      BufferedReader reader = new BufferedReader(new FileReader(fileToParse));
      String stringToParse = "";
      boolean lookingForNextSentence = false;
     
      // this loop will continue until the we reach the end of the input file.
      while ((stringToParse = reader.readLine()) != null) {

         boolean skippingText = true;
         String textToStore = "";
         char currentChar;
         // for loop will iterate until it reaches the end of the string.
         for (int i = 0; i < stringToParse.length(); i++) {

            currentChar = stringToParse.charAt(i);

            // first check to see if we are looking for next sentence. 
            // this is a check to handle cases of multiple punctuation marks
            // at the end of a sentence (ie "text!!!"). if it finds something
            // other than a punctuation mark, it will go through the rest
            // of the for loop, otherwise it will skip that text and move
            // on to the next char.
            if (lookingForNextSentence)
            {
               if (isPunctuation(currentChar)){
                  continue;
               }
               else{
                  lookingForNextSentence = false;
                  
               }
            }

            // if the character being read is not a quotation mark and
            // we are already skipping text
            // (we are not in the middle of a quotation) simply
            // continue with the next iteration.
            if (!(isQuotation(currentChar)) && (skippingText)) {
               continue;
            }

            // if we are at a quotation mark and we are already skipping text
            // that means we have come to the opening quotation mark. We will
            // begin to construct a string made up of the characters. We will
            // also change the boolen skippingText to reflect that we will no
            // longer be skipping over relevant text, we will be appending it
            // to a string.
            else if ((isQuotation(currentChar)) && (skippingText)) {
               textToStore = "\"";
               skippingText = false;
            }

            // if we are at a quotation mark and we not already
            // skipping text that means we have come to the closing
            // quotation mark of a sentence. set the skippingText
            // boolean to true. we will no longer store text from the
            // input string until we reach another quotation mark.
            else if ((isQuotation(currentChar)) && (!skippingText)) {
               skippingText = true;
            }

            // if we reach this point and the char being read
            // is a punctuation mark it will cause one of the
            // following switch statements to activate depending
            // on the punctuation mark being used. lookingForNextSentence
            // will be changed to true, and text will be skipped until
            // it finds something other than a puncuation mark.
            else if (isPunctuation(currentChar)){
               textToStore = textToStore + currentChar + '\"';
               lookingForNextSentence = true;
           
               switch (currentChar){
           
                  case '.': 
                     statementArray.add(textToStore);
                     textToStore = "\"";
                     break;
                  
                  case '!':
                     exclamationArray.add(textToStore);
                     textToStore = "\"";
                     break;
                   
                  case '?':
                     questionArray.add(textToStore);
                     textToStore = "\"";
                     break;
                  
               }
            }   
            
            // if none of the above is true, we add the char being read to
            // the string textToStore.
            else{
              textToStore = textToStore + currentChar;
            }
         }
         
      }
     reader.close();
     }
   public void replaceTextBetweenQuotes(File fileToParse, File fileToWrite) throws IOException{
      
      // if the output file does not exist, create it.
      if (!fileToWrite.exists()) 
      {
         fileToWrite.createNewFile();
      }
      // create a reader for the files passed into the method. each string 
      // will, in turn, be passed to the stringToParse variable as read from the
      // file. also create a writer to handle output.
      BufferedReader reader = new BufferedReader(new FileReader(fileToParse));
      BufferedWriter writer = new BufferedWriter(new FileWriter(fileToWrite));
      String stringToParse = "";

       // this is a variable to handle cases of multiple punctuation marks
       // at the end of a sentence (ie "text!!!").
      boolean lookingForNextSentence = false;
      
      // random object is created and also array lists that correspond to the number
      // of items in each given array.  This will be used by the random object to
      // prevent duplication for as long as possible.
      Random rng = new Random();
      int arrayPosition;
      char currentChar;
      boolean skippingText = false;


      // this loop will continue until the we reach the end of the input file.
      while ((stringToParse = reader.readLine()) != null) {
         writer.write(' ');
         writer.write("\n");
         // for loop will iterate until it reaches the end of the string.
         for (int i = 0; i < stringToParse.length(); i++) {
            
            currentChar = stringToParse.charAt(i);

            // first check to see if we are looking for next sentence. 
            // this is a check to handle cases of multiple punctuation marks
            // at the end of a sentence (ie "text!!!"). if it finds something
            // other than a punctuation mark, it will go through the rest
            // of the for loop, otherwise it will skip that text and move
            // on to the next char.
            if (lookingForNextSentence)
            {
               if (isPunctuation(currentChar)){
                  writer.write(currentChar);
                  continue;
               }
               else {
                 
                  lookingForNextSentence = false;
                  continue;
               }
               
            }
            
            // if we are not skipping text (we are not in the middle of
            // quotation marks) and the current char is not a quotation
            // mark, write char to file.
            if (!(isQuotation(currentChar)) && (!skippingText)) {
               writer.write(currentChar);
            }

            // if we are skipping text (we are in the middle of
            // quotation marks) and the current char is not a punctuation
            // mark, simply continue to the next iteration.
            else if (!(isPunctuation(currentChar)) && (skippingText)) {
               continue;
            }

            // if we are not skipping text (we are not in the middle of
            // quotation marks) and the current char is a quotation
            // mark, we have come to the opening quotation mark. we
            // will set the skippingText boolean to true, so we no longer
            // ouput the text from file.
            else if ((isQuotation(currentChar)) && (!skippingText)) {
               skippingText = true;
            }

            // if we are skipping text (we are in the middle of
            // quotation marks) and the current char is a quotation
            // mark, we have come to the closing quotation mark. we
            // will set the skippingText boolean to false, so we will
            // ouput the text from file.
            else if ((isQuotation(currentChar)) && (skippingText)) {
               skippingText = false;
            }

            // if the current character is a punctuation mark, then 
            // set the lookingForNextSentence boolen to true (this will
            // handle instances of multiple consecutive punctuation marks.)
            // and switch based on punctuation mark found.
            else if (isPunctuation(currentChar)){
               lookingForNextSentence = true;
               skippingText = false;
           
               // depending on the punctuation mark, take a random string from
               // the appropriate array list and output it to file. if there are
               // no unique phrases to write, instead write a default phrase.
               switch (currentChar){
           
                  case '.': 
                     if (statementArray.size() == 0){
                        writer.write ("\"I don't think that birds actually exist.\"");

                     }
                     else{
                        arrayPosition  = rng.nextInt(statementArray.size());
                        writer.write(statementArray.get(arrayPosition));
                        statementArray.remove(arrayPosition);
                    }
                     break;
                  
                  case '!':
                  if (exclamationArray.size() == 0){
                     writer.write ("\"I am the law!\"");

                  }
                  else{
                        arrayPosition  = rng.nextInt(exclamationArray.size());
                        writer.write(exclamationArray.get(arrayPosition));
                        exclamationArray.remove(arrayPosition);;
                    }
                  break;
                  case '?':
                     if (questionArray.size() == 0){
                        writer.write ("\"Does anyone else smell toast?\"");
                     }
                     else{
                        arrayPosition  = rng.nextInt(questionArray.size());
                        writer.write(questionArray.get(arrayPosition));
                        questionArray.remove(arrayPosition);;
                   }
                  break;
               }
            }
            
           // if we reach here, simple write the char and loop again.
           else{
              writer.write(currentChar);
            }
         }
         
      }
      // close reader/writer
      reader.close();
      writer.close();
   } 

}