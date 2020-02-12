package fr.jd.test;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;

import org.junit.Test;

import fr.jd.main.Cards;
import fr.jd.main.Dealer;
import fr.jd.main.Player;

public class DealerCoveryTests {

	private List<Cards> cardsPackage = new ArrayList<Cards>(EnumSet.allOf(Cards.class));
	private List<Cards> carpet = new ArrayList<Cards>();
	Dealer dealer = new Dealer(cardsPackage, carpet);

	@Test
	public void testDealer() {
		assertEquals(52, cardsPackage.size());
		assertEquals(0, carpet.size());
	}

	@Test
	public void testGetCardsPackage() {
		List<Cards> cardsPackageCheck = new ArrayList<Cards>(dealer.getCardsPackage());
		assertEquals(cardsPackage, cardsPackageCheck);
	}

	@Test
	public void testSetCardsPackage() {
		List<Cards> cardsPackageCheck = new ArrayList<>();
		dealer.checkShuffle();
		for (int i = 0; i < 52; i++)
			cardsPackageCheck.add(Cards.As_Coeur);
		dealer.setCardsPackage(cardsPackageCheck);
		dealer.checkShuffle();
		assertEquals(cardsPackageCheck, dealer.getCardsPackage());
		assertEquals(52, cardsPackage.size());
	}

	@Test
	public void testGetCarpet() {
		List<Cards> carpetCheck = new ArrayList<Cards>(dealer.getCarpet());
		assertEquals(carpet, carpetCheck);
	}

	@Test
	public void testSetCarpet() {
		List<Cards> carpetCheck = new ArrayList<>();
		for (int i = 0; i == 51; i++)
			carpetCheck.add(Cards.As_Coeur);
		dealer.setCarpet(carpetCheck);
		assertEquals(carpetCheck, carpet);
	}

	@Test
	public void testShuffle() {
		List<Cards> cardsPackageCheck = new ArrayList<Cards>(EnumSet.allOf(Cards.class));
		dealer.shuffle();
		assertNotEquals(cardsPackageCheck, cardsPackage);
		assertEquals(52, cardsPackage.size());
	}

	@Test
	public void testDistribute() {
		Player player = new Player("playerTest");
		dealer.distribute(player);
		assertEquals(51, cardsPackage.size());
		assertEquals(1, player.getHand().size());
	}

	@Test
	public void testPutOnTable() {
		int nbTourDeTable = 0;
		dealer.putOnTable(nbTourDeTable);
		assertEquals(0, carpet.size());
		nbTourDeTable = 1;
		dealer.putOnTable(nbTourDeTable);
		assertEquals(3, carpet.size());
		nbTourDeTable = 2;
		dealer.putOnTable(nbTourDeTable);
		assertEquals(4, carpet.size());
		nbTourDeTable = 3;
		dealer.putOnTable(nbTourDeTable);
		assertEquals(5, carpet.size());
	}

	@Test
	public void testRecoverCards() {
		assertEquals(52, cardsPackage.size());
		List<Cards> cardsBeforeRecover = new ArrayList<>(this.cardsPackage);
		dealer.recoverCards();
		assertEquals(52, cardsPackage.size());
		assertTrue(cardsPackage.equals(cardsBeforeRecover));
		assertEquals(0, carpet.size());
		assertTrue(carpet.isEmpty());
	}

}
