// Name: Harutyun Minasyan
// USC NetID: Hminasya
// CSCI 455 PA4
// Spring 2018





import java.util.HashMap;

public class ScoreTable
{
  //Instance Variables 
  //HashMap that will store the characters (upper and Lower case) in the English alphabet as keys and integers as values provided for each one.
  HashMap<Character, Integer> tableOfScores; 

  
  
  /*
   * Creates a ScoreTable that has all English characters and their associated integer values stored in a HashMap data structure. 
   * ScoreTable can compute the total score of any string by calling its getScoreForWord function once the ScoreTable is fully initialized.
   */
  public ScoreTable() 
  {
    tableOfScores = createScoreMap(scoreData());
  }

  
  
  /*
  * takes in a string and computes the total value of all of its characters' values. It uses the tableOfScores HashMap of ScoreTable object to lookup
    values associated with each character in the English alphabet. 
    Note: Upper case and Lower case letters have the same values. (e.g. 'A' and 'a' are equal in value.)
    @param word - the string whose value needs to be computed.
  */
  public int getScoreForWord(String word)
  {
    int total = 0;
    for (int i = 0; i < word.length(); i++)
    {
      total += tableOfScores.get(word.charAt(i));
    }
    return total;
  }

  
  
  /*
   * returns a HashMap that contains Characters as keys and integers as associated values. Uses a double for-loop to iterate over each Character
   * each Character[] in Character[][]. 
    
   * PRE: The first character in each Character[] in the 2-dimensional Character[][] array has to store the value of the rest of the characters. It is 
   *      not a character itself and should not be used as a key.For Character[i][j] i indicates the ith Character[] and j indicates jth 
          Character in ith Character[]. 
   
   * @param CharacterTable - The data that contains the characters and their associated scores- 2D Character array
   * @return - A map that contains the characters as keys and their integer values as values.
   */
  private HashMap<Character, Integer> createScoreMap(Character[][] characterTable)
  {
    HashMap<Character, Integer> scores = new HashMap<>(); 
    int value; //The value associated with each row in CharacterTable
    //Double for loop to iterate over all Characters in each Character[] in CharacterTable
    for (int i = 0; i < characterTable.length; i++)
    {
      value =  characterTable[i][0] - '0'; //subtracting '0' to get the numeric value of the character digits.
      //Since number 10 has two digits, 0 has been used to indicate the value 10. This if statement checks for a 0 to convert it to 10. 
      if (value == 0)
      {
        value = 10;
      }
      for (int j = 1; j < characterTable[i].length; j++)
      {
        scores.put(characterTable[i][j], (Integer) value);
      }
    }
    return scores;
  }

  
  
  /*
  * returns a 2-D array of Characters that contains the data that indicates which character has what value in the English alphabet. 
  * These values are hard coded.

   @return Character[][] - The data in the form of 2D Character array.
  */
  private Character[][] scoreData()
  {
    Character[] onePointPerChar = { '1','a','A', 'e', 'E', 'i','I','o', 'O', 'u', 'U', 'l', 'L', 'n', 'N', 's', 'S', 't', 'T', 'r', 'R' };
    Character[] twoPointPerChar = {'2','d', 'D', 'g', 'G'};
    Character[] threePointPerChar = {'3', 'b', 'B', 'c', 'C', 'm', 'M', 'p', 'P'};
    Character[] fourPointPerChar = {'4', 'f', 'F', 'h', 'H', 'v', 'V', 'w', 'W', 'y', 'Y'};
    Character[] fivePointPerChar = {'5', 'k', 'K'};
    Character[] eightPointPerChar = {'8', 'j', 'J', 'x', 'X'};
    Character[] tenPointPerChar = {'0','q', 'Q', 'z', 'Z'};

    Character[][] characterTable= {onePointPerChar, twoPointPerChar, threePointPerChar, fourPointPerChar,fivePointPerChar, eightPointPerChar, tenPointPerChar};
    return characterTable;
  }
	
}


