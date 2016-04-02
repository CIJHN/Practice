package net.alex.graph;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * @author ci010
 */
public class EditDis
{
	static Scanner STDIN = new Scanner(System.in);//To not mess up with System.in
	static Map<String, List<Set<String>>> dictionaryMaps;

	public static void main(String[] Args) throws FileNotFoundException
	{
		takeInFile();
		findEditDistance();
	}

	public static void findEditDistance()
	{
		System.out.print("Enter two words separated by a space: ");
		String initialWord = STDIN.next();
		String finalWord = STDIN.next();
		System.out.println();

		if (!dictionaryMaps.keySet().contains(initialWord) || !dictionaryMaps.keySet().contains(finalWord))
		{
			System.out.print("Word does not exist");
			//If either of the words cannot be found in the dictionary,
			//the word does not exist. The progress of find edit distance ends.
		}
		else
		{
			List<String> path = getPath(initialWord, finalWord);
			//Find the edit distance and the path
			if (path.isEmpty())
				System.out.println("No Solution");
			else
				for (String s : path)
					System.out.println(s);
		}
	}

	public static List<String> getPath(String a, String b)
	{
		List<String> path = new ArrayList<>();//The list of path
		List<Integer> diff = new LinkedList<>();//The list indicates which indexes of a and b are different.
		for (int i = 0; i < a.length(); i++)
			if (a.charAt(i) != b.charAt(i))
				diff.add(i);
		getPath(a, b, path, diff);
		path.remove(b);
		return path;
	}

	private static void getPath(String a, String b, List<String> path, List<Integer> diff)
	{
		char[] origin = a.toCharArray();
		for (int i = 0; i < diff.size(); i++)
		{
			//build a new word from a with just only one char replaced by the b at #index.
			int index = diff.get(i);
			char temp = origin[index];
			origin[index] = b.charAt(index);
			String next = new String(origin);
			origin[index] = temp;

			Set<String> set = dictionaryMaps.get(a).get(index);//get all the neighbors of a
			if (set != null)
				if (set.contains(next))
				//if a's neighbor contains new guessing word, remove #i from diff so that we not tracking it anymore.
				//and add new word to path
				{
					path.add(next);
					diff.remove(i);
					getPath(next, b, path, diff);
					return;
				}
		}
	}

//	public static List<String> findPath(String initialWord, String finalWord)
//	{
//		List<String> wordPath = new LinkedList<String>();
//		Set<String> wordExplored = new HashSet<String>();
//		findEdit(initialWord, finalWord, wordPath, wordExplored);
//		return wordPath;
//	}
	////

	//Find all possibilities using recursion
//	private static boolean findEdit(String initialWord, String finalWord,
//									List<String> wordPath, Set<String> wordExplored)
//	{
//		if (!wordExplored.containsAll(dictionaryMaps.get(initialWord)))
//		{
//			wordExplored.add(initialWord);
//			if (dictionaryMaps.get(initialWord).contains(finalWord))
//			{
//				wordPath.add(initialWord);
//				return true;
//			}
//			else
//			{
//				for (String s : dictionaryMaps.get(initialWord))
//				{
//					if (!wordExplored.contains(s))
//					{
//						if (findEdit(s, finalWord, wordPath, wordExplored))
//						{
//							wordPath.add(s);
//							return true;
//						}
//						return false;
//					}
//					return false;
//				}
//			}
//			return false;
//		}
//		return false;
//	}


	public static void takeInFile() throws FileNotFoundException
	{
		File DictionaryFile = new File("C:\\Users\\CIJhn\\Desktop\\dict.txt");
//		do
//		{
//			System.out.print("Enter name of dictionary file: ");
//			String FileName = STDIN.next();
//			DictionaryFile = new File(FileName);
//			if (!DictionaryFile.exists())
//			{
//				System.out.println("File Not Found. Try again.");
//				//Let the user do it again if the user entered a non-existing file name
//			}
//		} while (!DictionaryFile.exists());
		Scanner scanDictionary = new Scanner(DictionaryFile);
		//Scan the file just read

		Set<String> dictionarySet = new HashSet<String>();//A set of all words in the dictionary
		while (scanDictionary.hasNext())
		{
			dictionarySet.add(scanDictionary.next());
		}//Put all words in a hash set
		scanDictionary.close();
		dictionMap(dictionarySet);
	}


	//Make a map of with all the string in dictionary as keys to all their one step neighbors
	private static void dictionMap(Set<String> dictionarySet)
	{
		dictionaryMaps = new HashMap<>();
		for (String words : dictionarySet)
		{//go through all words
			dictionaryMaps.put(words, closeNeighbors(words, dictionarySet));
			//add the word and all its neighbors
		}
	}

	//Make a set of 1 edit distance neighbors of a given word
	private static List<Set<String>> closeNeighbors(String w, Set<String> dictionarySet)
	{
		int wordLength = w.length();
		char[] real = w.toCharArray();
		List<Set<String>> closeNeighbor = new ArrayList<>(wordLength);
		for (int i = 0; i < wordLength; i++)
			closeNeighbor.add(Collections.<String>emptySet());
		for (int num = 0; num < wordLength; num++)
		{
			for (int alphaB = 97; alphaB < 123; alphaB++)
			{
				char temp = real[num];
				real[num] = (char) alphaB;
				String subWord = new String(real);
				real[num] = temp;
				if (dictionarySet.contains(subWord))
				{
					Set<String> nei = closeNeighbor.get(num);
					if (nei == Collections.EMPTY_SET)
					{
						nei = new HashSet<>();
						closeNeighbor.set(num, nei);
					}
					nei.add(subWord);
				}
			}
		}//Replacing all char with all alphabet to find all the word with one step edit
//		closeNeighbor.remove(w); //Remove the original word
		return closeNeighbor;
	}

}
