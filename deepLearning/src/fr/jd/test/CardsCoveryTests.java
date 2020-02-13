package fr.jd.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;

import org.junit.jupiter.api.Test;

import fr.jd.main.Cards;

class CardsCoveryTests {

	@Test
	void testCards() {
		List<Cards> cards = new ArrayList<>(EnumSet.allOf(Cards.class));
		assertNotNull(cards);
		assertEquals(52, cards.size());
		for (Cards card : cards) assertEquals(1, Collections.frequency(cards, card));
	}

	@Test
	void testValue() {
		assertEquals(13, Cards.As_Coeur.value());
		assertEquals(1, Cards.Deux_Coeur.value());
		assertEquals(6, Cards.Sept_Coeur.value());
	}

	@Test
	void testColor() {
		assertEquals("Coeur", Cards.As_Coeur.color());
		assertEquals("Pique", Cards.Deux_Pique.color());
		assertEquals("Carreau", Cards.Sept_Carreau.color());
	}

	@Test
	void testGetCount() {
		List<Cards> playerCombo = new ArrayList<>();
		List<Integer> cardsValuesCheck = new ArrayList<>();
		for (int i =0; i<7; i++) playerCombo.add(Cards.Deux_Coeur);
		int getCountCheck = Cards.getCount(playerCombo);
		assertEquals(playerCombo.size(), getCountCheck);
	}

	@Test
	void testGetSum() {
		List<Cards> playerCombo = new ArrayList<>();
		for (int i =0; i<7; i++) playerCombo.add(Cards.Deux_Coeur);
		assertEquals(7, Cards.getCount(playerCombo)); 
	}

	@Test
	void testCheckHighRaise() {
		List<Cards> playerCombo = new ArrayList<>();
		playerCombo.add(Cards.As_Coeur);
		playerCombo.add(Cards.Deux_Coeur);
		playerCombo.add(Cards.Deux_Pique);
		playerCombo.add(Cards.Sept_Carreau);
		playerCombo.add(Cards.Sept_Coeur);
		playerCombo.add(Cards.Trois_Coeur);
		playerCombo.add(Cards.Trois_Coeur);
		assertEquals(13, Cards.checkHighRaise(playerCombo));
		playerCombo.remove(Cards.As_Coeur);
		assertEquals(6, Cards.checkHighRaise(playerCombo));
		playerCombo.remove(Cards.Sept_Carreau);
		playerCombo.remove(Cards.Sept_Coeur);
		assertEquals(2, Cards.checkHighRaise(playerCombo));
		playerCombo.remove(Cards.Trois_Coeur);
		playerCombo.remove(Cards.Trois_Coeur);
		assertEquals(1, Cards.checkHighRaise(playerCombo));
	}

	@Test
	void testCheckOnePair() {
		List<Cards> playerCombo = new ArrayList<>();
		playerCombo.add(Cards.As_Coeur);
		playerCombo.add(Cards.Deux_Coeur);
		playerCombo.add(Cards.Deux_Pique);
		playerCombo.add(Cards.Sept_Carreau);
		playerCombo.add(Cards.Sept_Coeur);
		playerCombo.add(Cards.Trois_Coeur);
		playerCombo.add(Cards.Trois_Coeur);
		assertEquals(84, Cards.checkOnePair(playerCombo));
		playerCombo.remove(Cards.Sept_Carreau);
		playerCombo.remove(Cards.Sept_Coeur);
		assertEquals(28, Cards.checkOnePair(playerCombo));
		playerCombo.remove(Cards.Trois_Coeur);
		playerCombo.remove(Cards.Trois_Coeur);
		assertEquals(14, Cards.checkOnePair(playerCombo));
	}

	@Test
	void testCheckTwoPairs() {
		List<Cards> playerCombo = new ArrayList<>();
		playerCombo.add(Cards.As_Coeur);
		playerCombo.add(Cards.Deux_Coeur);
		playerCombo.add(Cards.Deux_Pique);
		playerCombo.add(Cards.Sept_Carreau);
		playerCombo.add(Cards.Sept_Coeur);
		playerCombo.add(Cards.Trois_Coeur);
		playerCombo.add(Cards.Trois_Coeur);
		assertEquals(304, Cards.checkTwoPairs(playerCombo));
		playerCombo.remove(Cards.Sept_Carreau);
		playerCombo.remove(Cards.Sept_Coeur);
		playerCombo.remove(Cards.Trois_Coeur);
		playerCombo.remove(Cards.Trois_Coeur);
		assertEquals(0, Cards.checkTwoPairs(playerCombo));
	}

	@Test
	void testCheckThreeOfAKind() {
		List<Cards> playerCombo = new ArrayList<>();
		playerCombo.add(Cards.As_Coeur);
		playerCombo.add(Cards.Deux_Coeur);
		playerCombo.add(Cards.Deux_Pique);
		playerCombo.add(Cards.Sept_Carreau);
		playerCombo.add(Cards.Trois_Coeur);
		playerCombo.add(Cards.Trois_Coeur);
		playerCombo.add(Cards.Trois_Coeur);
		assertEquals(954, Cards.checkThreeOfAKind(playerCombo));
		playerCombo.remove(Cards.Sept_Carreau);
		playerCombo.remove(Cards.Trois_Coeur);
		assertEquals(0, Cards.checkThreeOfAKind(playerCombo));
	}

	@Test
	void testCheckStraight() {
		List<Cards> playerCombo = new ArrayList<>();
		playerCombo.add(Cards.As_Coeur);
		playerCombo.add(Cards.Deux_Coeur);
		playerCombo.add(Cards.Trois_Pique);
		playerCombo.add(Cards.Quatre_Coeur);
		playerCombo.add(Cards.Cinq_Coeur);
		playerCombo.add(Cards.Six_Coeur);
		playerCombo.add(Cards.Sept_Coeur);
		assertEquals(8280, Cards.checkStraight(playerCombo));
		playerCombo.remove(Cards.Trois_Coeur);
		playerCombo.remove(Cards.Six_Coeur);
		assertEquals(0, Cards.checkStraight(playerCombo));
	}

	@Test
	void testCheckFull() {
		List<Cards> playerCombo = new ArrayList<>();
		playerCombo.add(Cards.As_Coeur);
		playerCombo.add(Cards.Deux_Coeur);
		playerCombo.add(Cards.Deux_Pique);
		playerCombo.add(Cards.Sept_Carreau);
		playerCombo.add(Cards.Trois_Coeur);
		playerCombo.add(Cards.Trois_Coeur);
		playerCombo.add(Cards.Trois_Coeur);
		assertEquals(18600, Cards.checkFull(playerCombo));
		playerCombo.remove(Cards.Trois_Coeur);
		assertEquals(0, Cards.checkFull(playerCombo));
	}

	@Test
	void testCheckFourOfAKind() {
		List<Cards> playerCombo = new ArrayList<>();
		playerCombo.add(Cards.As_Coeur);
		playerCombo.add(Cards.Deux_Coeur);
		playerCombo.add(Cards.Deux_Pique);
		playerCombo.add(Cards.Trois_Coeur);
		playerCombo.add(Cards.Trois_Coeur);
		playerCombo.add(Cards.Trois_Coeur);
		playerCombo.add(Cards.Trois_Coeur);
		assertEquals(146480, Cards.checkFourOfAKind(playerCombo));
		playerCombo.remove(Cards.Sept_Carreau);
		playerCombo.remove(Cards.Trois_Coeur);
		playerCombo.remove(Cards.Trois_Coeur);
		assertEquals(0, Cards.checkFourOfAKind(playerCombo));
	}

	@Test
	void testCheckStraightFlush() {
		List<Cards> playerCombo = new ArrayList<>();
		playerCombo.add(Cards.As_Coeur);
		playerCombo.add(Cards.Deux_Coeur);
		playerCombo.add(Cards.Trois_Coeur);
		playerCombo.add(Cards.Quatre_Coeur);
		playerCombo.add(Cards.Cinq_Coeur);
		playerCombo.add(Cards.Six_Coeur);
		playerCombo.add(Cards.Sept_Coeur);
		assertEquals(1269500, Cards.checkStraight(playerCombo));
		playerCombo.remove(Cards.Trois_Coeur);
		playerCombo.remove(Cards.Six_Coeur);
		assertEquals(0, Cards.checkStraight(playerCombo));
	}

}
