// Name: Harutyun Minasyan
// USC NetID: Hminasya
// CS 455 PA4
// Spring 2018

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.HashMap;

  /**
   * A dictionary of all anagram sets. 
   * Note: the processing is case-sensitive; so if the dictionary has all lower
   * case words, you will likely want any string you test to have all lower case
   * letters too, and likewise if the dictionary words are all upper case.
   */  
public class AnagramDictionary
{
	
  //Instance Variables
  Scanner fileReader; //The Scanner object that reads from the dictionary files.
  //An internal dictionary that will store anagram sets. The keys are the sorted variants of all anagrams stored in an array as values of the hashMap.
  HashMap<String, ArrayList<String>> dictionary = new HashMap<String, ArrayList<String>>(); 

  
  /*
   * Create an anagram dictionary from the list of words given in the file
   * indicated by fileName.  
   * PRE: The strings in the file are unique.
   * @param fileName  the name of the file to read from
   * @throws FileNotFoundException  if the file is not found
   */
  public AnagramDictionary(String fileName) throws IOException 
  {
    File file = new File(fileName);
    fileReader = new Scanner(file); // initializing the fileReader and giving it a file with the specified name
    createAnagramSets();   
  }
   

  /*
   * Get all anagrams of the given string. This method is case-sensitive.
   * E.g. "CARE" and "race" would not be recognized as anagrams.
   * @param s string to process
   * @return a list of the anagrams of s
   */
  public ArrayList<String> getAnagramsOf(String s) 
  {
    return dictionary.get(s);
  }
   
   
  /*
   * Creates the entries in the dictionary and populates it with arrays of words that are anagrams of each other. The keys are unique sorted strings.
   * Uses a scanner to read each space separated word, sorts a copy of that word and compares it to the key set of the dictionary HashMap.
     if The word has an anagram already stored in the dicitonary, it will add that word to the end of its anagrams array stored as a value for the sorted
     copy key of that word.
   */
  private void createAnagramSets() 
  {
    while (fileReader.hasNext()) //this loop continues as long as there are words in the original dictionary.
    {
      String word = fileReader.next();
      String sortedCopy = sortTheWord(word);
      if (dictionary.containsKey(sortedCopy))
      { //If it finds a key in dictionary using the sorted copy of the word, add the word to the anagram array found for that key.
        dictionary.get(sortedCopy).add(word);
      }
      else
      { //if the key is not found, it starts a new array list to group this word and all subsequently read anagrams of it.
        dictionary.put(sortedCopy, new ArrayList<String>());
        dictionary.get(sortedCopy).add(word);
      }
    }
  }
   
   
  /*
   * Takes a string as an argument and returns another string that is the alphabetically sorted copy of that string.
   * @param s - The string object to obtain the sorted copy of.
   * @return - The sorted copy string.
   */
  private String sortTheWord(String s)
  {
    char[] chars = s.toCharArray();
    Arrays.sort(chars);
    String sortedCopy = new String(chars);
    return sortedCopy;
  }
   
  
}