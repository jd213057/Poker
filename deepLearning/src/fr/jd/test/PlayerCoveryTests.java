package fr.jd.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import fr.jd.main.Cards;
import fr.jd.main.Player;

public class PlayerCoveryTests {

	Player player = new Player("playerTest");

	@Test
	public void testPlayer() {
		assertEquals(1000, player.getTotalMoney(), 0.0);
		assertEquals("playerTest", player.getPlayerName());
		assertEquals(0, player.getScore());
		assertEquals(true, player.isInGame());
		assertEquals(true, player.isInPlay());
	}

	@Test
	public void testGetPlayerName() {
		assertEquals("playerTest", player.getPlayerName());
	}

	@Test
	public void testSetPlayerName() {
		player.setPlayerName("set");
		assertNotEquals("playerTest", player.getPlayerName());
		assertEquals("set", player.getPlayerName());
	}

	@Test
	public void testGetTotalMoney() {
		assertEquals(1000.0, player.getTotalMoney(), 0.0);
	}

	@Test
	public void testSetTotalMoney() {
		player.setTotalMoney(10.0);
		assertNotEquals(100.0, player.getTotalMoney(), 0.0);
		assertEquals(10.0, player.getTotalMoney(), 0.0);
	}

	@Test
	public void testGetMoneyBet() {
		assertEquals(1000.0, player.getTotalMoney(), 0.0);
	}

	@Test
	public void testSetMoneyBet() {
		player.setMoneyBet(100.0);
		assertNotEquals(0.0, player.getMoneyBet(), 0.0);
		assertEquals(100.0, player.getMoneyBet(), 0.0);
	}

	@Test
	public void testGetHand() {
		List<Cards> handCheck = new ArrayList<>();
		assertEquals(handCheck, player.getHand());
	}

	@Test
	public void testSetHand() {
		List<Cards> playerHand = player.getHand();
		playerHand.add(Cards.As_Coeur);
		playerHand.add(Cards.As_Coeur);
		player.setHand(playerHand);
		assertEquals(playerHand, player.getHand());
	}

	@Test
	public void testGetScore() {
		assertEquals(0, player.getScore());
	}

	@Test
	public void testSetScore() {
		player.setScore(100);
		assertNotEquals(0, player.getScore());
		assertEquals(100, player.getScore());
	}

	@Test
	public void testIsInGame() {
		assertEquals(true, player.isInGame());
	}

	@Test
	public void testSetInGame() {
		player.setInGame(true);
		assertEquals(true, player.isInGame());
		player.setInGame(false);
		assertEquals(false, player.isInGame());
	}

	@Test
	public void testIsInPlay() {
		assertEquals(true, player.isInPlay());
	}

	@Test
	public void testSetInPlay() {
		player.setInPlay(true);
		assertEquals(true, player.isInPlay());
		player.setInGame(false);
		assertEquals(false, player.isInPlay());
	}

	@Test
	public void testPayPetiteBlinde() {
		assertEquals(995, player.getTotalMoney(), 0.0);
		assertEquals(5, player.getMoneyBet(), 0.0);
	}

	@Test
	public void testPayGrosseBlinde() {
		assertEquals(990.0, player.getTotalMoney(), 0.0);
		assertEquals(10.0, player.getMoneyBet(), 0.0);
	}

	@Test
	public void testCheck() {
		double totalMoneyCheck = player.getTotalMoney();
		double moneyBetCheck = player.getMoneyBet();
		player.check();
		assertEquals(totalMoneyCheck, player.getTotalMoney(), 0.0);
		assertEquals(moneyBetCheck, player.getMoneyBet(), 0.0);
	}

	@Test
	public void testFold() {
		boolean isInPlay = player.isInPlay();
		player.fold();
		assertNotEquals(true, player.isInPlay());
		assertEquals(false, player.isInPlay());
	}

	@Test
	public void testCall() {
		player.call(100.0);
		assertEquals(900.0, player.getTotalMoney(), 0.0);
		assertEquals(100.0, player.getMoneyBet(), 0.0);
	}

	@Test
	public void testRaise() {
		player.raise(100.0);
		assertEquals(900.0, player.getTotalMoney(), 0.0);
		assertEquals(100.0, player.getMoneyBet(), 0.0);
	}

	@Test
	public void testAllIn() {
		player.allIn();
		assertEquals(0.0, player.getTotalMoney(), 0.0);
		assertEquals(1000.0, player.getMoneyBet(), 0.0);
	}

	@Test
	public void testWinMoney() {
		player.winMoney(1000.0);
		assertEquals(2000.0, player.getTotalMoney(), 0.0);
	}

	@Test
	public void testToStringOnPlay() {
		String toStringOnPlay = player.toStringOnPlay();
		assertEquals("Player [playerName=" + "playerTest" + ", totalMoney=" + "1000.0" + ", moneyBet=" + "0.0"
				+ ", inPlay=" + "true" + "]", toStringOnPlay);
	}

	@Test
	public void testToStringHand() {
		String toStringHand = player.toStringHand();
		assertEquals("Player [playerName=" + "playerTest" + ", hand=" + "[]" + ", moneyBet=" + "0.0" + "€]",
				toStringHand);
	}

}
