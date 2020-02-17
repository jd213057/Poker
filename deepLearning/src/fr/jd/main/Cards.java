package fr.jd.main;

import java.util.ArrayList;
import java.util.Arrays;
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

	private static List<Integer> SUITE_5 = Arrays.asList(1, 2, 3, 4, 13);
	private static List<Integer> SUITE_6 = Arrays.asList(1, 2, 3, 4, 5);
	private static List<Integer> SUITE_7 = Arrays.asList(2, 3, 4, 5, 6);
	private static List<Integer> SUITE_8 = Arrays.asList(3, 4, 5, 6, 7);
	private static List<Integer> SUITE_9 = Arrays.asList(4, 5, 6, 7, 8);
	private static List<Integer> SUITE_10 = Arrays.asList(5, 6, 7, 8, 9);
	private static List<Integer> SUITE_J = Arrays.asList(6, 7, 8, 9, 10);
	private static List<Integer> SUITE_Q = Arrays.asList(7, 8, 9, 10, 11);
	private static List<Integer> SUITE_K = Arrays.asList(8, 9, 10, 11, 12);
	private static List<Integer> SUITE_A = Arrays.asList(9, 10, 11, 12, 13);

	private enum COEF_COMBO {
		HIGH_RAISE(1), ONE_PAIR(7), TWO_PAIRS(19), THREE_OF_A_KIND(159), STRAIGHT(414), FLUSH(1085), FULL(2325),
		FOUR_OF_A_KIND(18310), STRAIGHT_FLUSH(63475);

		private int coef;

		COEF_COMBO(int coef) {
			this.coef = coef;
		}

		int coef() {
			return this.coef;
		}

	}

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

	public static int getCount(List<Cards> playerFinalCombo) {
		int cardsTotalValues = 0;
		for (Cards card : playerFinalCombo)
			cardsTotalValues += card.value();
		return cardsTotalValues;
	}

	public static int getSum(List<Integer> playerFinalCombo) {
		int cardsTotalValues = 0;
		for (int value : playerFinalCombo)
			cardsTotalValues += value;
		return cardsTotalValues;
	}

	public static List<Integer> getListOfValues(List<Cards> playerCombo) {
		List<Integer> playerListOfValues = new ArrayList<>();
		for (Cards card : playerCombo)
			playerListOfValues.add(card.value());
		return playerListOfValues;
	}

	public static List<String> getListOfColors(List<Cards> playerCombo) {
		List<String> playerListOfColors = new ArrayList<>();
		for (Cards card : playerCombo)
			playerListOfColors.add(card.color());
		return playerListOfColors;
	}

	public static int checkHighRaise(List<Cards> playerCombo) {
		int playerScore = 0;
		List<Integer> cardsValues = new ArrayList<>();
		for (Cards card : playerCombo)
			cardsValues.add(card.value());
		for (Cards card : playerCombo) {
			if (card.value() == Collections.max(cardsValues))
				playerScore = card.value() * COEF_COMBO.HIGH_RAISE.coef();
		}
		return playerScore;
	}

	public static int checkOnePair(List<Cards> playerCombo) {
		List<Integer> pairs = new ArrayList<>();
		List<Integer> cardsTempValues = new ArrayList<>();
		int playerScore = 0;
		for (Cards card : playerCombo)
			cardsTempValues.add(card.value());
		for (int value : cardsTempValues) {
			if (Collections.frequency(cardsTempValues, value) == 2) {
				pairs.add(value);
			}
		}
		Collections.sort(pairs, Collections.reverseOrder());
		if (!pairs.isEmpty()) {
			playerScore += pairs.get(0) * 2 * COEF_COMBO.ONE_PAIR.coef();
			return playerScore;
		}
		return playerScore;
	}

	public static int checkTwoPairs(List<Cards> playerCombo) {
		List<Integer> pairs = new ArrayList<>();
		List<Integer> cardsTempValues = new ArrayList<>();
		int playerScore = 0;
		for (Cards card : playerCombo)
			cardsTempValues.add(card.value());
		for (int value : cardsTempValues) {
			if (Collections.frequency(cardsTempValues, value) == 2) {
				pairs.add(value);
			}
		}
		Collections.sort(pairs, Collections.reverseOrder());
		if (!pairs.isEmpty() & pairs.size() >= 4) {
			playerScore += (pairs.get(0) + pairs.get(2)) * 2 * COEF_COMBO.TWO_PAIRS.coef();
			return playerScore;
		}
		return playerScore;
	}

	public static int checkThreeOfAKind(List<Cards> playerCombo) {
		List<Integer> playerValues = new ArrayList<>(Cards.getListOfValues(playerCombo));
		List<Integer> brelansTemp = new ArrayList<>();
		List<Integer> brelans = new ArrayList<>();
		int playerScore = 0;
		for (int value : playerValues) {
			if (Collections.frequency(playerValues, value) == 3) {
				brelansTemp.add(value);
			}
		}
		if (!brelansTemp.isEmpty() && brelansTemp.size() >= 3) {
			Collections.sort(brelansTemp, Collections.reverseOrder());
			brelans.add(brelansTemp.get(0));
			brelans.add(brelansTemp.get(1));
			brelans.add(brelansTemp.get(2));
			playerScore += getSum(brelans) * COEF_COMBO.THREE_OF_A_KIND.coef();
		}
		return playerScore;
	}

	public static int checkStraight(List<Cards> playerCombo) {
		int playerScore = 0;
		List<Integer> values = new ArrayList<>(Cards.getListOfValues(playerCombo));
		List<List<Integer>> combo = new ArrayList<>();
		List<Integer> playerBestCombo = new ArrayList<>();
		Collections.sort(values);
		if (values.containsAll(SUITE_5))
			combo.add(SUITE_5);
		if (values.containsAll(SUITE_6))
			combo.add(SUITE_6);
		if (values.containsAll(SUITE_7))
			combo.add(SUITE_7);
		if (values.containsAll(SUITE_8))
			combo.add(SUITE_8);
		if (values.containsAll(SUITE_9))
			combo.add(SUITE_9);
		if (values.containsAll(SUITE_10))
			combo.add(SUITE_10);
		if (values.containsAll(SUITE_J))
			combo.add(SUITE_J);
		if (values.containsAll(SUITE_Q))
			combo.add(SUITE_Q);
		if (values.containsAll(SUITE_K))
			combo.add(SUITE_K);
		if (values.containsAll(SUITE_A))
			combo.add(SUITE_A);
		if (combo.isEmpty()) return playerScore;
		playerBestCombo.addAll(combo.get(combo.size() - 1));
		if (playerBestCombo.contains(13))
			playerScore += (getSum(playerBestCombo) - 13) * COEF_COMBO.STRAIGHT.coef();
		playerScore += getSum(playerBestCombo) * COEF_COMBO.STRAIGHT.coef();
		return playerScore;
	}

	public static int checkFlush(List<Cards> playerCombo) {
		List<String> playerColors = new ArrayList<>(Cards.getListOfColors(playerCombo));
		List<String> checkColor = new ArrayList<>();
		List<Cards> playerColor = new ArrayList<>();
		int playerScore = 0;
		for (Cards card : playerCombo)
			if (Collections.frequency(playerColors, card.color()) >= 5) {
				checkColor.add(card.color());
				playerColor.add(card);
			}
		if (!checkColor.isEmpty())
			playerScore += getCount(playerColor) * COEF_COMBO.FLUSH.coef();
		return playerScore;
	}

	public static int checkFull(List<Cards> playerCombo) {
		List<Integer> values = new ArrayList<>(Cards.getListOfValues(playerCombo));
		List<Cards> brelansTemp = new ArrayList<Cards>();
		List<Integer> brelans = new ArrayList<>();
		List<Cards> pairsTemp = new ArrayList<Cards>();
		List<Integer> pairs = new ArrayList<>();
		List<Integer> playerFinalCombo = new ArrayList<>();
		int playerScore = 0;
		for (Cards card : playerCombo) {
			if (Collections.frequency(values, card.value()) == 3) {
				brelansTemp.add(card);
			}
			if (Collections.frequency(values, card.value()) == 2) {
				pairsTemp.add(card);
			}
		}
		if (!(brelansTemp.isEmpty() && pairs.isEmpty())) {
			Collections.sort(brelansTemp, Collections.reverseOrder());
			brelans.add(brelansTemp.get(0).value());
			if (brelansTemp.size()>=4) brelans.add(brelansTemp.get(3).value());
			Collections.sort(pairsTemp, Collections.reverseOrder());
			pairs.add(pairsTemp.get(0).value());
			if (pairs.size()>=2) pairs.add(pairsTemp.get(2).value());
			playerFinalCombo.add(brelans.get(0));
			playerFinalCombo.add(brelans.get(0));
			playerFinalCombo.add(brelans.get(0));
			playerFinalCombo.add(pairs.get(0));
			playerFinalCombo.add(pairs.get(0));
			System.out.println(playerFinalCombo);
			playerScore += getSum(playerFinalCombo) * COEF_COMBO.FULL.coef();
		}
		return playerScore;
	}

	public static int checkFourOfAKind(List<Cards> playerCombo) {
		List<Integer> playerValues = new ArrayList<>(Cards.getListOfValues(playerCombo));
		List<Integer> carreTemp = new ArrayList<>();
		List<Integer> carre = new ArrayList<>();
		int playerScore = 0;
		for (int value : playerValues) {
			if (Collections.frequency(playerValues, value) == 4) {
				carreTemp.add(value);
			}
		}
		if (!carreTemp.isEmpty() && carreTemp.size() >= 4) {
			Collections.sort(carreTemp, Collections.reverseOrder());
			carre.add(carreTemp.get(0));
			carre.add(carreTemp.get(1));
			carre.add(carreTemp.get(2));
			carre.add(carreTemp.get(3));
			playerScore += getSum(carre) * COEF_COMBO.FOUR_OF_A_KIND.coef();
		}
		return playerScore;
	}

	public static int checkStraightFlush(List<Cards> playerCombo) {
		int playerScore = 0;
		List<Integer> values = new ArrayList<>(Cards.getListOfValues(playerCombo));
		List<String> colors = new ArrayList<>(Cards.getListOfColors(playerCombo));
		List<List<Integer>> combo = new ArrayList<>();
		List<Integer> playerBestCombo = new ArrayList<>();
		boolean hasColor = false;
		for (Cards card : playerCombo)
			if (Collections.frequency(colors, card.color()) >= 5) {
				hasColor = true;
			}
		;
		System.out.println(hasColor);
		if (!hasColor)
			return playerScore;
		Collections.sort(values);
		if (values.containsAll(SUITE_5))
			combo.add(SUITE_5);
		if (values.containsAll(SUITE_6))
			combo.add(SUITE_6);
		if (values.containsAll(SUITE_7))
			combo.add(SUITE_7);
		if (values.containsAll(SUITE_8))
			combo.add(SUITE_8);
		if (values.containsAll(SUITE_9))
			combo.add(SUITE_9);
		if (values.containsAll(SUITE_10))
			combo.add(SUITE_10);
		if (values.containsAll(SUITE_J))
			combo.add(SUITE_J);
		if (values.containsAll(SUITE_Q))
			combo.add(SUITE_Q);
		if (values.containsAll(SUITE_K))
			combo.add(SUITE_K);
		if (values.containsAll(SUITE_A))
			combo.add(SUITE_A);
		if (combo.isEmpty()) return playerScore;
			playerBestCombo.addAll(combo.get(combo.size() - 1));
		if (playerBestCombo.contains(13))
			playerScore += (getSum(playerBestCombo) - 13) * COEF_COMBO.STRAIGHT_FLUSH.coef();
		playerScore += getSum(playerBestCombo) * COEF_COMBO.STRAIGHT_FLUSH.coef();
		return playerScore;
	}
}

