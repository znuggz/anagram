/**
 * 
 */

/**
 * @author gj4
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