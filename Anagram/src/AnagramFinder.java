

/**
 * 
 */

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;

/**
 * @author gj4
 *
 */
public class AnagramFinder {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		// create a hash map to store the words, each cell of the map should store a linked list of Anagram objects
		HashMap<String, LinkedList<Anagram>> anagramDictionary = new HashMap<String, LinkedList<Anagram>>();

		// TODO first things first, read the file name from the command line
		// and open it for reading
		Path path = Paths.get(args[0]);
		try (Scanner scanner =  new Scanner(path)){
			while (scanner.hasNextLine()){
				// read each line from the file
				// make a copy of the string for manipulation
				String word = scanner.nextLine().toLowerCase().trim();
				String sortedWord = charSort(word);
		
				// now peek to see if the linked list already exists
				if (anagramDictionary.get(sortedWord) == null) {
					// there are no values yet with this key, so create the anagram, the list 
					// and insert the value into the hash map
					LinkedList<Anagram> wordList = new LinkedList<Anagram>();
					wordList.add(new Anagram(word,sortedWord));
					anagramDictionary.put(sortedWord, wordList);	
				} else {
					// since there is already at least one value in the list, just get the list
					// add another value and put back
					LinkedList<Anagram> wordList = anagramDictionary.get(sortedWord);
					wordList.add(new Anagram(word,sortedWord));
					anagramDictionary.put(sortedWord, wordList);
				}

			}      
		}
		catch (IOException ioe){
			System.out.println("error opening file: ");
			ioe.printStackTrace();
		}

        // sanity spit out the HashMap
		Iterator<String> keySetIterator = anagramDictionary.keySet().iterator();
		while(keySetIterator.hasNext()){
		  String key = keySetIterator.next();
		  LinkedList<Anagram> ll = anagramDictionary.get(key);
		  ListIterator<Anagram> listIterator = ll.listIterator();
	        while (listIterator.hasNext()) {
	            System.out.print(listIterator.next().getWord() + " ");
	        }
		  System.out.println();
		}

		// TODO now return to the user to prompt for word

		// TODO check for failure conditions

		// TODO clone the word the user gave us so we can manipulate it

		// TODO treating the string like an array of words, sort the letters in the string

		// TODO use the sorted string to get the list of objects from the hash map if they exists

		// TODO iterate over the list comparing length of the strings and the sorted values, 
		// if they match then print out to the screen
	}

	// treating the string like an array of words, sort the letters in the string
	public static String charSort(String temp){
		char charArr[] = temp.toCharArray();
		Arrays.sort(charArr);
		return new String(charArr);
	}
}
