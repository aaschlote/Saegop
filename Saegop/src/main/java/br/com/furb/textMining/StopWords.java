package br.com.furb.textMining;

import java.util.HashSet;

public class StopWords {

	private static String[] s_stopWords = { "o", "a", "os", "as", "um", "uma", "uns", "umas",
			"eu", "tu", "ele", "ela", "nós", "vós", "eles", "elas", "me", "mim", "comigo", "te", "ti",
			"contigo", "se", "si", "lhe", "o", "a", "nos", "conosco", "vos", "lhes", "os", "as", 
			"meu", "minha", "meus", "minhas", "teu", "tua", "teus", "tuas", "seu", "sua", "seus",
			"suas", "nosso", "nossa", "nossos", "nossas", "vosso", "vossa", "vossos", "vossas",
			"este", "estes", "esta", "estas", "esse", "esses", "essa", "essas", "aquele", "aqueles",
			"aquela", "aquelas", "mesmo", "mesmos", "mesma", "mesmas", "próprio",
			"pr�prios", "pr�pria", "pr�prias", "tal", "tais", "isto", "isso", "aquilo",
			"sim",  "deveras",  "talvez",  "qui��",  "acaso",  "porventura",  "decerto",  "muito", 
			"pouco",  "assaz",  "bastante",  "mais",  "menos",  "t�o",  "demasiado",  "meio",  "todo", 
			"demais",  "que",  "qu�o",  "quanto",  "quase",  "como",  "abaixo",  "acima",  "acol�",  "c�", 
			"l�",  "aqui",  "ali",  "a�",  "além",  "aqu�m",  "algures",  "alhures",  "nenhures",  "atr�s", 
			"fora",  "afora",  "dentro",  "perto",  "longe",  "adiante",  "diante",  "onde",  "avante", 
			"atrav�s",  "defronte",  "aonde",  "donde",  "bem",  "mal",  "assim",  "depressa", 
			"devagar",  "debalde",  "alerta",  "melhor",  "pior",  "não",  "tampouco",  "agora", 
			"hoje",  "amanh�",  "depois",  "ontem",  "anteontem",  "jã",  "sempre",  "ami�de", 
			"nunca",  "jamais",  "ainda",  "logo",  "antes",  "cedo",  "tarde",  "ora",  "outrora",  "então", 
			"absolutamente",  "breve",  "calmamente",  "certamente",  "corretamente", 
			"hereupon", "hers", "herself", "him", "himself", "his", "how",
			"efetivamente",  "fielmente",  "levemente",  "possivelmente", 
			"primeiramente",  "provavelmente",  "qui��",  "realmente",  "tanto",  "tarde", 
			"cujo", "cujos", "cuja", "cujas", "quanto", "quantos", "quanta", "quantas", "quem",
			"eis", "exclusive", "menos", "exceto", "fora", "salvo", "senão", "sequer",
			"inclusive", "também", "mesmo", "ainda", "at�", "ademais", "al�m disso", "de",
			"mais a mais", "sã", "apenas, � que", "sobretudo", "embora", "aliás", "ou",
			"melhor", "isto �, ou antes", "a saber, por exemplo", "ou seja", "afinal",
			"do", "da", "policia", "militar", "fato:", "no", "na","mas", "ocorrencia", "porta", "portas"};

	private HashSet<String> stopWords;

	public StopWords() {
		stopWords = new HashSet<String>(s_stopWords.length);

		for (String stopWord : s_stopWords) {
			stopWords.add(stopWord);
		}
	}

	public boolean isStopWord(String word) {
		return stopWords.contains(word.toLowerCase());

	}

}
