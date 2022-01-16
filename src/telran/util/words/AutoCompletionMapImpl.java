package telran.util.words;

import java.util.function.Function;
import java.util.function.Predicate;
import java.util.*;
public class AutoCompletionMapImpl implements AutoCompletion {
HashMap<Character, TreeSet<String>> words = new HashMap<>(); //key - first character of a word;
// value - collection (TreeSet) of words beginning with the given first character case insensitive
	@Override
	/**
	 * adds word 
	 * with applying the method computeIfAbsent
	 */
	public boolean addWord(String word) {
		return words.computeIfAbsent(word.charAt(0), s -> new TreeSet<String>()).add(word);
	}

	@Override
	public boolean removeWord(String word) {
		TreeSet<String> res = words.computeIfPresent(word.charAt(0), (k, v) -> v);
		return res !=null ? res.remove(word) : false;
	}

	@Override
	public Iterable<String> getCompletionOptions(String prefix) {
		List<String> valuesWithPrefix = new LinkedList<>();
		for (TreeSet<String> set : words.values()) {
			valuesWithPrefix.addAll(set.subSet(prefix, getPrefixLimit(prefix)));
		}
		
		return valuesWithPrefix;
	}
	private String getPrefixLimit(String prefix) {
		char lastChar = prefix.charAt(prefix.length() - 1);
		char limitChar = (char) (lastChar + 1);
		return prefix.substring(0, prefix.length() - 1) + limitChar;

	}
	
	/**
	 * removes words matching a given predicate
	 * @param predicate
	 * @return count of the removed words
	 */
	public int removeIf(Predicate<String> predicate) {
		int count = 0;
		for (TreeSet<String> set : words.values())
		{
			count += set.size();
			set.removeIf(predicate);
			count -= set.size();
		}
		return count;
	}

}
