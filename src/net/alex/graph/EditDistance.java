package net.alex.graph;

import net.ci010.common.Session;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class EditDistance
{
	private static Scanner STDIN = new Scanner(System.in);
	private static Session session = new Session();

	public static void main(String[] Args) throws FileNotFoundException
	{
		File file = getFileFromSTDIN();
		session.start("make map");
		Map<String, Set<String>> dictionaryMaps = makeMapsFromFile(file);
		session.end();
		findEditDistance(dictionaryMaps);
	}

	public static File getFileFromSTDIN()
	{
//		File dictionaryFile;
//		do
//		{
//			System.out.print("Enter name of dictionary file: ");
//			String fileName = STDIN.next();
//			dictionaryFile = new File(fileName);
//			if (!dictionaryFile.exists())
//			{
//				System.out.println("File Not Found. Try again.");
//				//Let the user do it again if the user entered a non-existing file name
//			}
//		} while (!dictionaryFile.exists());
//		return dictionaryFile;
		return new File("C:\\Users\\CIJhn\\Desktop\\dict.txt");
	}

	public static Map<String, Set<String>> makeMapsFromFile(File file) throws FileNotFoundException
	{
		session.start("read file");
		Scanner scanDictionary = new Scanner(file);

		Set<String> dictionarySet = new HashSet<String>();
		while (scanDictionary.hasNext()) //Put all words in a hash set
			dictionarySet.add(scanDictionary.next());
		scanDictionary.close();
		session.end();
		session.start("put neighbor");
		Map<String, Set<String>> dicMap = new HashMap<String, Set<String>>();
		char[] alphabetArray = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
								'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y',
								'z'};//array of all letters
		for (String word : dictionarySet)
			dicMap.put(word, closeNeighbors(word, dictionarySet, alphabetArray));
		session.end();
		return dicMap;
		//Return a map with all the string in dictionary as keys to all their one step neighbors.
		//Calculate all the one-step neighbors of all words
		//Store it in the main method for latter use
	}

	public static void findEditDistance(Map<String, Set<String>> makeMaps)
	{
		System.out.print("Enter two words separated by a space: ");
		String initialWord = STDIN.next();
		String finalWord = STDIN.next();

		System.out.println();
		if (!makeMaps.keySet().contains(initialWord) || !makeMaps.keySet().contains(finalWord))
		{
			System.out.print("Word does not exist");
			//If either of the words cannot be found in the dictionary,
			//the word does not exist. The progress of find edit distance ends.
		}
		else
		{
			session.start("find path");
			List<String> path = findPath(initialWord, finalWord, makeMaps);
			session.end();
			//Find the edit distance and the path
			if (path.isEmpty())
				System.out.println("No Solution");
			else
				for (String s : path)
					System.out.println(s);
		}
	}


	private static List<String> findPath(String initialWord, String finalWord, Map<String, Set<String>> makeMaps)
	{
		List<String> wordPath = new LinkedList<String>();
		Set<String> wordExplored = new HashSet<String>();
		return findPath(initialWord, finalWord, makeMaps, wordPath, wordExplored);
	}

	////
	private static List<String> findPath(String init, String target, Map<String, Set<String>> src, List<String> wordP, Set<String> wordExplored)
	{
		Set<String> currentWord = new HashSet<String>();
		currentWord.addAll(src.get(init));
		currentWord.removeAll(wordExplored);
		List<String> wordPath = new LinkedList<String>();
		wordPath.addAll(wordP);

		if (currentWord.contains(target))
		{
			wordPath.add(init);
			return wordPath;
		}
		if (!currentWord.isEmpty())
		{
			Iterator<String> ir = src.get(init).iterator();//make a iterator of the initial input's one step neighbors
			wordPath.add(init);
			wordExplored.add(init);
			while (ir.hasNext())
			{//go through all of the neighbors to see if neighbors have the final word as a neighbor
				String s = ir.next();
				wordExplored.add(s);
				if (src.get(s).contains(target))
				{
					wordPath.add(s);
					return wordPath;
				}
				else
				{
					List<String> ww = findPath(s, target, src, wordPath, wordExplored);
					if (!ww.isEmpty())
					{
						return ww;
					}
				}
			}

		}
		//If the word's all neighbor has been explored before, return nothing
		return wordPath;

	}
	////


	//Find all possibilities using recursion


	private static Set<String> closeNeighbors(String word, Set<String> dictionarySet, char[] alphabetArray)
	{
		Set<String> closeNeighbor = new HashSet<String>();
		int alphL = alphabetArray.length;
		int wordLength = word.length();
		char[] real = word.toCharArray();
		for (int i = 0; i < wordLength; i++)
		{
			for (int j = 0; j < alphL; j++)
			{
				char temp = real[i];
				real[i] = alphabetArray[j];
				String subWord = new String(real);
				real[i] = temp;
				if (dictionarySet.contains(subWord))
					closeNeighbor.add(subWord);
			}
		}//Replacing all char with all alphabet to find all the word with one step edit
		closeNeighbor.remove(word); //Remove the original word
		return closeNeighbor;
	}

}
