package fr.jd.main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public enum Cards {
	As_Coeur(13, "Coeur"), Deux_Coeur(1, "Coeur"), Trois_Coeur(2, "Coeur"), Quatre_Coeur(3, "Coeur"),
	Cinq_Coeur(4, "Coeur"), Six_Coeur(5, "Coeur"), Sept_Coeur(6, "Coeur"), Huit_Coeur(7, "Coeur"),
	Neuf_Coeur(8, "Coeur"), Dix_Coeur(9, "Coeur"), Valet_Coeur(10, "Coeur"), Dame_Coeur(11, "Coeur"),
	Roi_Coeur(12, "Coeur"), As_Pique(13, "Pique"), Deux_Pique(1, "Pique"), Trois_Pique(2, "Pique"),
	Quatre_Pique(3, "Pique"), Cinq_Pique(4, "Pique"), Six_Pique(5, "Pique"), Sept_Pique(6, "Pique"),
	Huit_Pique(7, "Pique"), Neuf_Pique(8, "Pique"), Dix_Pique(9, "Pique"), Valet_Pique(10, "Pique"),
	Dame_Pique(11, "Pique"), Roi_Pique(12, "Pique"), As_Trefle(13, "Trefle"), Deux_Trefle(1, "Trefle"),
	Trois_Trefle(2, "Trefle"), Quatre_Trefle(3, "Trefle"), Cinq_Trefle(4, "Trefle"), Six_Trefle(5, "Trefle"),
	Sept_Trefle(6, "Trefle"), Huit_Trefle(7, "Trefle"), Neuf_Trefle(8, "Trefle"), Dix_Trefle(9, "Trefle"),
	Valet_Trefle(10, "Trefle"), Dame_Trefle(11, "Trefle"), Roi_Trefle(12, "Trefle"), As_Carreau(13, "Carreau"),
	Deux_Carreau(1, "Carreau"), Trois_Carreau(2, "Carreau"), Quatre_Carreau(3, "Carreau"), Cinq_Carreau(4, "Carreau"),
	Six_Carreau(5, "Carreau"), Sept_Carreau(6, "Carreau"), Huit_Carreau(7, "Carreau"), Neuf_Carreau(8, "Carreau"),
	Dix_Carreau(9, "Carreau"), Valet_Carreau(10, "Carreau"), Dame_Carreau(11, "Carreau"), Roi_Carreau(12, "Carreau");

	private final int value;
	private final String color;

	Cards(int value, String color) {
		this.value = value;
		this.color = color;
	}

	public int value() {
		return this.value;
	}

	public String color() {
		return this.color;
	}

	public static int checkHighRaise(List<Cards> playerCombo) {
		Cards card = Collections.max(playerCombo);
		int score = card.value();
		return score;
	}

	public static int checkPairs(List<Cards> playerCombo) {
		List<Cards> pairsTemp = new ArrayList<Cards>();
		List<Cards> pairs = new ArrayList<Cards>();
		int playerScore = 0;
		for (Cards card : playerCombo) {
			if (Collections.frequency(playerCombo, card) == 2) {
				pairsTemp.add(card);
			}
		}
		Collections.sort(pairsTemp, Collections.reverseOrder());
		pairs.add(pairsTemp.get(0));
		pairs.add(pairsTemp.get(2));
		for (Cards card : pairs) {
			playerScore += (2 * card.value());
		}
		return playerScore;
	}

	public static int checkThreeOfAKind(List<Cards> playerCombo) {
		List<Cards> brelansTemp = new ArrayList<Cards>();
		List<Cards> brelans = new ArrayList<Cards>();
		int playerScore = 0;
		for (Cards card : playerCombo) {
			if (Collections.frequency(playerCombo, card) == 3) {
				brelansTemp.add(card);
			}
		}
		if (!brelansTemp.isEmpty()) {
			Collections.sort(brelansTemp, Collections.reverseOrder());
			brelans.add(brelansTemp.get(0));
			brelans.add(brelansTemp.get(3));
			for (Cards card : brelans) {
				playerScore += (3 * card.value());
			}
		}
		return playerScore;
	}

	public static int checkFourOfAKind(List<Cards> playerCombo) {
		List<Cards> carreTemp = new ArrayList<Cards>();
		List<Cards> carre = new ArrayList<Cards>();
		int playerScore = 0;
		for (Cards card : playerCombo) {
			if (Collections.frequency(playerCombo, card) == 4) {
				carreTemp.add(card);
			}
		}
		if (!carreTemp.isEmpty()) {
			Collections.sort(carreTemp, Collections.reverseOrder());
			carre.add(carreTemp.get(0));

			playerScore += (4 * carre.get(0).value());

		}
		return playerScore;
	}

	public static int checkOnePair(List<Cards> playerCombo) {
		int score = 0;
		return score;
	}

	public static int checkTwoPairs(List<Cards> playerCombo) {
		int score = 0;
		return score;
	}

	public static int checkFlush(List<Cards> playerCombo) {
		List<String> checkColor = new ArrayList<>();
		int playerScore = 0;
		for (Cards card : playerCombo)
			checkColor.add(card.color());
		for (Cards card : playerCombo)
			if (Collections.frequency(checkColor, card.color()) == 5) {
				playerScore += 1000;
				return playerScore;
			}
		;
		return playerScore;
	}

	public static int checkStraight(List<Cards> playerCombo) {
		int score = 0;
		return score;
	}

	public static int checkFullHouse(List<Cards> playerCombo) {
		int score = 0;
		return score;
	}

	public static int checkStraightFlush(List<Cards> playerCombo) {
		int score = 0;
		return score;
	}

}
