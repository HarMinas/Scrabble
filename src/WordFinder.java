// Name: Harutyun Minasyan
// USC NetID: Hminasya
// CS 455 PA4
// Spring 2018

import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;





/*
 * A WordFinder program.
 * Determines if a legal word (given by a dictionary file) can be formed from a list of characters and prints
   the total value of the word by adding the values of all its letters.
   The program lists all legal words and their values in a list sorted alphabetically and the total number of words found.
 */

public class WordFinder 
{

/*
  WordFinder is a class that uses a dictionary and a rack to provide all legal words that can be created from the letters 
  in the rack and exist in the dictionary. Once the rack is processed, all possible combinations of the rack are matched 
  with words in the dictionary.If such a match exists, the word in the dictionary is displayed, along with the total score 
  value formed by adding the individual values of the words letters. The score computation is delegated to a ScoreTable object. 
  This class contains a main method and is the entry point to the program.
  
  Once the program starts, it pre-processes a dictionary and asks the user for rack input. It then enters a loop of processing 
  the rack with the given
  input, displaying all matched words with their scores in alphabetical order and asking the user to input another rock. 
  The program terminates when the user enters the sentinel value ".".
  
  The file name of the original dictionary can be given to the program as a command line argument. If no such command line is givem, it defaults to 
  the file named "sowpods.txt".
*/
	
  public static void main(String[] args)
  {
    String rackInput; //The characters that the user should input from which the rack is to be created.
    String fileName; //The file that contains the original dictionary.
    AnagramDictionary dictionary; //The object that contains all words from original dictionary but grouped by anagrams in a hashMap.
    ScoreTable scores = new ScoreTable(); //The object that can compute the value of a word.
    Rack rack; //The Rack object that stores the information about the scrabble rack.
    Scanner in = new Scanner(System.in); //Scanner object used to get input from the user for the letters of the rack.
		
    if (args.length < 1) 
    { //Checking if the command line argument is missing, the file name is defaulted to "sowpods.txt". 
      fileName = "sowpods.txt";
    }
    else 
    { //if argument is provided, it is taken to be the file name of the original dictionary.
      fileName = args[0];
    }

    try  //Checks for IOException if the file does not exist.
    {
      dictionary = new AnagramDictionary(fileName);
      rackInput = askForInput(in); //prompts the user to input 
	    	 		
      while (!rackInput.equals("."))  
      { //This loop asks for new rack input and prints the words that can be made from that rack until user types ".".
      rack = new Rack(rackInput);
      printWordsWithScores(rack, dictionary, scores);
      rackInput = askForInput(in);
      }
    }	     
    ///Error Handling --Copied from Assignment 3 MazeViewer File
    catch (FileNotFoundException exc)
    {
      System.out.println("ERROR: File not found: " + fileName);
    }
    catch (IOException exc) 
    {
      exc.printStackTrace();
    }
  }
	
	
  /*
    Prints all words that are found in the dictionary and can be formed with the letters provided in the rack object
    and prints them along with the total score of each word computed by the table object that gets the scores.
    The words are first printed by decreasing score, and alphabetically for words with the same score.

  * @param rack - A Rack object that contains the letters and can provide with all permutations with the given letters.
  * @param dictionary - and object that stores all the words in the dictionary grouped by anagram sets
  * @param table - a ScoreTable object that contains the values of each letter in the alphabet and can compute
                    the total value of a given word.
  */
  public static void printWordsWithScores(Rack rack, AnagramDictionary dictionary, ScoreTable table) 
  {
    ArrayList<String> anagrams = new ArrayList<String>(); 
    ArrayList<String> subsets = rack.getSubsets();
    ArrayList<Word> results = new ArrayList<Word>();
    String currentWord; //a temp. storage for a string from the dictionary.
    
    for (int i = 0; i < subsets.size(); i ++)
    { //Determining if subsets formed from rack letters corresponds to any word in the dictionary. If it does, add all anagram words that 
    	  //correspond to it to the results HashMap.
      anagrams = dictionary.getAnagramsOf(subsets.get(i)); //Getting a particular anagram
      if (anagrams != null)
      {
        for (int j = 0; j < anagrams.size(); j++)
        {  
        	  currentWord = anagrams.get(j);
        	  results.add(new Word(currentWord, table.getScoreForWord(currentWord)));
        }
      }
    }	//Printing the total number of words found for the rack with given letters.
    System.out.println("There are a total of " + results.size() + " number of words found for the rack " + rack.getLetters());
    Collections.sort(results); //sorting the words according to the compareTo rules of the Word class.
    for (Word word: results)  //traverses the entire tree map and prints all value/entry pairs
    {
      System.out.println(word.getScore() + ": " + word.getWord());
    }
  }
	

  
  /* 
   * asks for user input on a console and returns the characters typed by the user as a string
   * @param in - the Scanner object to read the data from the console
   * @return - the string object read from the console
   */
  private static String askForInput(Scanner in)
  {
    System.out.println("Type . to quit.");
    System.out.println("Rack? ");
    return in.next();
  }
  
  
}


 
