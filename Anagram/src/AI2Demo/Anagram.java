package AI2Demo;

/**
 * A simple implementation of an Anagram finding application
 * developed for AI2 interview
 * Gabe Jimenez
 * February 22, 2015
 *
 */

/**
 * a container class to hold a word and its sorted version
 * 
 *
 */
public class Anagram {
	
	private String word;
	private String sortedWord;
	
	Anagram(String anaWord, String sortedAnaWord){
		word = anaWord;
		sortedWord = sortedAnaWord;
	}
	
	public String getWord(){
		return word;
	}
	
	public String getsortedWord(){
		return sortedWord;
	}

}
