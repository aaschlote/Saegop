package br.com.furb.textMining;

public class Word {
	
	private String word;

	public Word(String word) {
		super();
		this.word = word.toLowerCase();
	}

	public String toString() {
		return word;
	}

}
