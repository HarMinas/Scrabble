// Name: Harutyun Minasyan.
// USC NetID: Hminasya.
// CSCI 455 Pa4
// Spring 2018



/*
 * A Word class that stores a the word and its value. 
 * Implements Comparable interface that can sort the word from decreasing order of values.
 * If two words have the same value, they will be sorted alphabetically.
*/
public class Word implements Comparable<Word>
{
  //Instance Variables
  private String word; //Storing the characters of the word
  private int score; //Storing the value of the score
	
  /*
  * Constructs a new word.
  * Sets the word and the value.
  * PRE: value has to be greater then 0.
  * 
  * @param word - String that is associated with the word.
  * @param value - The value associated with the word.
  */
  public Word(String word, int value)
  {
    this.word = word;
    score = value;
  }
	
  /*
  * returns the score
  *
  *@return - an integer score.
  */
  public int getScore()
  {
    return score;
  }
	
  /*
   * returns the 
   *
   *@return - String representation of the word.
   */
  public String getWord() 
  {
    return word;
  }
	
  /*
   * The implementation of the comparTo method of the Comparable interface. 
   * The method compares two word objects by first comparing the value of the words, such that the
     higher value word comes first, and then compares the String representations of the words iff the value
     of two words are the same. The word, whose value are the same to the other word, but the string 
     is alphabetically lower comes first.
     @param other - The other word object that the word is going be compared to 
   * @see java.lang.Comparable#compareTo(java.lang.Object)
   */
  public int compareTo(Word other)
  {
    if (this.getScore() > other.getScore())
    {
      return -1;
    }
    else if (this.getScore() < other.getScore())
    {
      return 1;
    }
    return this.getWord().compareTo(other.getWord());
  }

}
