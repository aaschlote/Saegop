package br.com.furb.textMining;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Collection;

import ptstemmer.Stemmer;

public class WordTokenizer {
	
	private static boolean removeNumbersAndSingleLetters = false;
	private final static StopWords stopWords = new StopWords();

	public Collection<Word> getWords(String value, Stemmer stemmer) throws Exception {
		
		String temp = Normalizer.normalize(value, Normalizer.Form.NFKD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "");   
		value = temp.replaceAll("[^\\p{ASCII}]"," "); 
		value = temp.replaceAll("[\\p{Punct}]"," ");
		
		String[] wordStrs = value.split("\\s");

		ArrayList<Word> words = new ArrayList<Word>();
		for (String word : wordStrs) {

			if ((removeNumbersAndSingleLetters && word.length() > 1 && !word.matches("[0-9]+"))
					|| (!removeNumbersAndSingleLetters && word.length() > 0)) {
				if	(!stopWords.isStopWord(word)){
					word = word.replace(".", "");
					word = word.replace(",", "");
					word = word.replace(")", "");
					word = word.replace("(", "");
					word = word.replace("“", "");
					word = word.replace("”", "");
					words.add(new Word(stemmer.getWordStem(word)));
				}
				
			}

		}
		
		stemmer.clearIgnoreList();
		
		return words;

	}

}
