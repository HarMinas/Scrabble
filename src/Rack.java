// Name: Harutyun Minasyan	
// USC NetID: Hminasya
// CS 455 PA4
// Spring 2018

import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;

/**
 * A Rack of Scrabble tiles
 * The rack contains letters, that are stored as alphabetically sorted sequence of unique characters and multiplicities of each of these
   letters in an array of integers.
   The rack can compute all the subsets of its letters and return them as an ArrayList.
 */

public class Rack 
{
 
  //Instance Variables
  private String letters; //Will contain the letters sorted in alphabetical order.
  private String uniqueChars = ""; //A String that will store alphabetically sorted unique letters in letters
  private int[] mults; //integer array that will store the multiplicity of the letters in uniqueChars String.
    
  
 /*
  * Creates an new rack object with the given letters. The letters passed in are sorted alphabetically and stored in a multiset representation
    that is comprised of two parts. The first part is an a string that stores the unique characters in the passed in string, the second part
    is an array of integers that stores the multiplicity of the letters in the unique characters string.
    PRE: The mults[].length >= uniqueChars.length. The multiplicity of the character at uniqueChars.charAt(i) is always in mults[i].
         uniqueChars should not have more than one copy of a given character in it.
    @param letters - The group of original characters passed in to create the Rack.
 */
  public Rack(String letters)
  {
    this.letters = sortTheWord(letters); //sorting the word and putting it into letters of the rack.
    createMultiset(this.letters); //Creating the multiset from the given letters. 
  }

	
  /**
  * Finds all subsets of the multiset starting at position k in unique and mult.
  * unique and mult describe a multiset such that mult[i] is the multiplicity of the char
  *      unique.charAt(i).
  * PRE: mult.length must be at least as big as unique.length()
  *      0 <= k <= unique.length()
  * @param unique a string of unique letters
  * @param mult the multiplicity of each letter from unique.  
  * @param k the smallest index of unique and mult to consider.
  * @return all subsets of the indicated multiset
  * @author Claire Bono
  */
  private static ArrayList<String> allSubsets(String unique, int[] mult, int k) 
  {
    ArrayList<String> allCombos = new ArrayList<>();
	      
    if (k == unique.length()) 
    {  // multiset is empty
      allCombos.add("");
      return allCombos;
    }
	      
    // get all subsets of the multiset without the first unique char
    ArrayList<String> restCombos = allSubsets(unique, mult, k+1);
	      
    // prepend all possible numbers of the first char (i.e., the one at position k) 
    // to the front of each string in restCombos.  Suppose that char is 'a'...
    String firstPart = "";          // in outer loop firstPart takes on the values: "", "a", "aa", ...
    for (int n = 0; n <= mult[k]; n++) 
    {   
      for (int i = 0; i < restCombos.size(); i++) // for each of the subsets
      {  // create and add a new string with n 'a's in front of that subset
        allCombos.add(firstPart + restCombos.get(i));  
      }
      firstPart += unique.charAt(k);  // append another instance of 'a' to the first part
    }
    return allCombos;
  }
   
	
  /*
  * This method takes a string, puts the unique letters of that string into uniqueChars instance variable of the rack object and 
  * the multiplicity of each letter in mults integer array of the rack (if letter "a" appears 3 times in the parameter string, mults will have a 
  * value of 3 in index 0 since 'a' is the first letter in the English alphabet. In order to determine if the letter is unique or not, a treeSet is used.
  * This method initializes uniqueChars and mults instance variables and is designed to be called in the constructor of the Rack.
 
  * PRE: The parameter string has to be sorted alphabetically. 

  * @param letters - alphabetically sorted string from which the 2 piece multiset is to be constructed.
  */
  private void createMultiset(String letters)
  {
    TreeSet<Character> uniqueSet = new TreeSet<Character>(); //treeSet is used to minimize the search time of the character in the add method of the set.
    for (int i = 0; i < letters.length(); i++)
    { //Traverses over all characters in the letters and tries to put them in a set. If the set contains it, its add method returns false
      if (uniqueSet.add(letters.charAt(i))) 
      { //character  i in letters is added only when the set did not have that character inside it.
        uniqueChars +=letters.charAt(i);
      }
    }	   
    mults = new int[uniqueSet.size()]; //initializing the mults array with the size of the uniqueSet.
    int start = 0; //The starting index for traversing over the letters in the inner loop.
	//double for loop that traverses each index of the mults array and figures out the multiplicity of a unique char that correspond to mults index.
    for (int i = 0; i < mults.length; i ++)
    {  
      for (int j = start; j < letters.length(); j ++) 
      {
        if (letters.charAt(start) == letters.charAt(j))
        {
          mults[i] = mults[i] + 1;
        }
        else 
        {
          start = j;
          //Since the letters is sorted alphabetically, when jth letter is not equal to letter at start index, there is no chance the letter will
          // appear later in letters. Therefore, we break from the iteration to increase efficiency of the program.
          break; 
        }
      }
    }  
  }
   
   
  /*
  * returns a copy of an ArrayList that stores all subsets returned by the allSubsets method.
  * @return - a new ArrayList<String> object.
  */
  public ArrayList<String> getSubsets()
  {
    ArrayList<String> subsets = new ArrayList<>(allSubsets(uniqueChars, mults, 0));
    return subsets;
  }

  
/*
 * Returns a copy of the original letters used to create the rack.
 */
  public String getLetters()
  {
	  return letters;
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