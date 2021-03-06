package AI2Demo;

/**
 * A simple implementation of an Anamgram finding application
 * developed for AI2 interview
 * Gabe Jimenez
 * February 22, 2015
 */

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;

/**
 * The AnagramFinder class hold the main function, it takes in a path to an input file.
 * It assumes that the words in the file are separated by a line return.
 */
public class AnagramFinder {

	/**
	 * takes in an input file of word and loads into a hash map. The letters of each word in
	 * the file are sorted in alphabetical order and the original word and its sorted version 
	 * are placed into a Anagram object. A linked list of anagram objects is created and put
	 * into the hash map, as more anagrams are found they are added to the linked list. 
	 * The sorted version of a word is used as the key to each list of anagrams.
	 */
	public static void main(String[] args) {
		
		// highly used variables
		String word;
		String sortedWord;
		Anagram anagram;
		LinkedList<Anagram> wordList;
		ListIterator<Anagram> listIterator;

		// create a hash map to store the words, each cell of the map should store a linked list of Anagram objects
		HashMap<String, LinkedList<Anagram>> anagramDictionary = new HashMap<String, LinkedList<Anagram>>();

		// read the file as argument and open it for reading
		Path path = Paths.get(args[0]);
		try (Scanner scanner =  new Scanner(path)){
			while (scanner.hasNextLine()){
				// read each line from the file
				// make a copy of the string for manipulation
				word = scanner.nextLine().toLowerCase().trim();
				sortedWord = charSort(word);
		
				// now peek to see if the linked list already exists
				if (anagramDictionary.get(sortedWord) == null) {
					// there are no values yet with this key, so create the anagram, the list 
					// and insert the value into the hash map
					wordList = new LinkedList<Anagram>();
					wordList.add(new Anagram(word,sortedWord));
					anagramDictionary.put(sortedWord, wordList);	
				} else {
					// since there is already at least one value in the list, just get the list
					// add another value and put back
					wordList = anagramDictionary.get(sortedWord);
					wordList.add(new Anagram(word,sortedWord));
					anagramDictionary.put(sortedWord, wordList);
				}

			}
			// done reading, close the connection to the file
			scanner.close();
		}
		catch (IOException ioe){
			// something went wrong, print the stack trace to start investigating
			ioe.printStackTrace();
			System.exit(1);
		}

        // sanity: spit out the HashMap
//		Iterator<String> keySetIterator = anagramDictionary.keySet().iterator();
//		while(keySetIterator.hasNext()){
//		  String key = keySetIterator.next();
//		  LinkedList<Anagram> ll = anagramDictionary.get(key);
//		  ListIterator<Anagram> listIterator = ll.listIterator();
//	        while (listIterator.hasNext()) {
//	            System.out.print(listIterator.next().getWord() + " ");
//	        }
//		  System.out.println();
//		}
		
		// set up variable for user input and other things
		String[] userWords;
		String key;

		// now return to the user to prompt for word
		Scanner in = new Scanner(System.in);

		// give the user the anagram search options
		System.out.println("enter a word, or a series of words separated by a comma to search for anagrams, or 'q' to quit");
		
		// split the string and assign to the string array
		userWords = in.nextLine().split(",");
		
		// keep going as long as the user wants
		while (!userWords[0].equals("q")){
			for ( String aWord : userWords){
				// this is the same as sorted word, but now we use it as a key
				key = charSort(aWord);
				// use the sorted string key to get the list of objects from the hash map if they exists
				wordList = anagramDictionary.get(key);
				if (wordList == null) {
					// there was no linked list, which means this word has no anagrams in the file
					System.out.println("no anagrams were found for " + aWord);
				} else {
					// it looks like we found some possible anagrams
					System.out.println("anagrams were found for " + aWord + ":");
					listIterator = wordList.listIterator();
					while (listIterator.hasNext()) {
						anagram = listIterator.next();
						// make sure words actually match
						if (key.equals(anagram.getsortedWord())) {
							System.out.println(anagram.getWord());
						}
			        }
				}
			}
			// give the user the anagram search options
			System.out.println("enter a word, or a series of words separated by a comma to search for anagrams, or 'q' to quit");
			
			// split the string and assign to the string array
			userWords = in.nextLine().split(",");
		}
		
		// close the system.in stream
		in.close();
		
	}

	// treating the string like an array of words, sort the letters in the string
	public static String charSort(String temp){
		char charArr[] = temp.toCharArray();
		Arrays.sort(charArr);
		return new String(charArr);
	}
}
