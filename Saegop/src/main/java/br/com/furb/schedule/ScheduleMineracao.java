package br.com.furb.schedule;

import br.com.furb.textMining.TextMining;

public class ScheduleMineracao {
	
	public static void main(String[] args) {
		TextMining mining = new TextMining();
		mining.extrair();
		System.exit(0);
	}

}
