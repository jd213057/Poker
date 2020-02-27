package fr.jd.test;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import fr.jd.main.Cards;
import fr.jd.main.Dealer;
import fr.jd.main.Game;
import fr.jd.main.Player;

public class GameCoveryTests {

	private List<Player> players = new ArrayList<>();

	Game game = new Game(players);

	@Test
	public void testGame() {
		List<Cards> cardsPackage = new ArrayList<Cards>(EnumSet.allOf(Cards.class));
		assertEquals(0, game.getPlayers().size());
		assertEquals(0, game.getTourDeTable());
		assertEquals(0, game.getManche());
		assertEquals(0, game.getMaxBet());
		assertTrue(game.isOnPlay());
		assertTrue(!game.isDebugMode());
		assertEquals(52, game.getDealer().getCardsPackage().size());
		assertEquals(0, game.getDealer().getCarpet().size());
		assertEquals(cardsPackage, game.getDealer().getCardsPackage());
	}

	@Test
	public void testGetPlayers() {
		assertEquals(7, game.getPlayers().size());
	}

	@Test
	public void testSetPlayers() {
		game.setPlayers();

		// Entrez 4 joueurs avec le nom "playerTest"

		assertTrue(game.getPlayers().size() == 4);
		for (Player player : game.getPlayers()) {
			assertEquals("playerTest", player.getPlayerName());
			assertEquals(1000, player.getTotalMoney(), 0.0);
			assertEquals(0, player.getScore(), 0.0);
			assertTrue(player.isInGame());
			assertTrue(player.isInPlay());
		}
	}

	@Test
	public void testGetTour() {
		assertEquals(0, game.getTourDeTable());
	}

	@Test
	public void testSetTour() {
		game.setTour(1);
		assertEquals(1, game.getTour());
	}

	@Test
	public void testGetDealer() {
		List<Cards> cardsPackage = new ArrayList<Cards>(EnumSet.allOf(Cards.class));
		assertEquals(52, game.getDealer().getCardsPackage().size());
		assertEquals(0, game.getDealer().getCarpet().size());
		assertEquals(cardsPackage, game.getDealer().getCardsPackage());
		assertEquals(cardsPackage, game.getDealer().getCardsPackage());
	}

	@Test
	public void testSetDealer() {
		Dealer dealerCheck = new Dealer(new ArrayList<Cards>(EnumSet.allOf(Cards.class)),
				new ArrayList<Cards>());
		game.setDealer(dealerCheck);
		assertEquals(dealerCheck, game.getDealer());
		assertTrue(game.getDealer().getCardsPackage().size() == 52);
		assertEquals(dealerCheck.getCarpet(), game.getDealer().getCarpet());
		assertTrue(dealerCheck.getCarpet().size() == 0);
		for (Cards card : game.getDealer().getCardsPackage())
			assertEquals(card, game.getDealer().getCardsPackage().get(card.value()));
	}

	@Test
	public void testGetTourDeTable() {
		assertEquals(0, game.getTourDeTable());
	}

	@Test
	public void testSetTourDeTable() {
		game.setTourDeTable(1);
		assertEquals(1, game.getTourDeTable());
	}

	@Test
	public void testGetManche() {
		assertEquals(1, game.getManche());
	}

	@Test
	public void testSetManche() {
		game.setManche(1);
		assertEquals(1, game.getManche());
	}

	@Test
	public void testGetNbTour() {
		assertEquals(0, game.getNbTour());
	}

	@Test
	public void testSetNbTour() {
		game.setNbTour(1);
		assertEquals(1, game.getNbTour());
	}

	@Test
	public void testGetMaxBet() {
		assertEquals(0, game.getMaxBet(), 0.0);
	}

	@Test
	public void testGetPositionPetiteBlinde() {
		for (int i = 0; i < 7; i++) {
			Player player = new Player("PlayerTest");
			players.add(player);
		}
		game.setManche(1);
		game.setPositionPetiteBlinde();
		assertEquals(2, game.getPositionPetiteBlinde());
		game.setManche(8);
		game.setPositionPetiteBlinde();
		assertEquals(1, game.getPositionPetiteBlinde());
		game.setManche(10);
		game.setPositionPetiteBlinde();
		assertEquals(3, game.getPositionPetiteBlinde());
	}

	@Test
	public void testSetPositionPetiteBlinde() {
		for (int i = 0; i < 7; i++) {
			Player player = new Player("PlayerTest");
			players.add(player);
		}
		game.setManche(1);
		game.setPositionPetiteBlinde();
		assertEquals(2, game.getPositionPetiteBlinde());
		game.setManche(8);
		game.setPositionPetiteBlinde();
		assertEquals(1, game.getPositionPetiteBlinde());
		game.setManche(10);
		game.setPositionPetiteBlinde();
		assertEquals(3, game.getPositionPetiteBlinde());
	}

	@Test
	public void testGetPositionGrosseBlinde() {
		for (int i = 0; i < 7; i++) {
			Player player = new Player("PlayerTest");
			players.add(player);
		}
		game.setManche(1);
		game.setPositionGrosseBlinde();
		assertEquals(1, game.getPositionGrosseBlinde());
		game.setManche(8);
		game.setPositionGrosseBlinde();
		assertEquals(0, game.getPositionGrosseBlinde());
		game.setManche(10);
		game.setPositionGrosseBlinde();
		assertEquals(2, game.getPositionGrosseBlinde());
	}

	@Test
	public void testSetPositionGrosseBlinde() {
		for (int i = 0; i < 7; i++) {
			Player player = new Player("PlayerTest");
			players.add(player);
		}
		game.setManche(1);
		System.out.println(game.getManche());
		game.setPositionGrosseBlinde();
		assertEquals(1, game.getPositionGrosseBlinde());
		game.setManche(8);
		game.setPositionGrosseBlinde();
		assertEquals(0, game.getPositionGrosseBlinde());
		game.setManche(10);
		game.setPositionGrosseBlinde();
		assertEquals(2, game.getPositionGrosseBlinde());
	}

	@Test
	public void testGetBlinde() {
		assertNotNull(game.getBlinde());
		assertEquals(10, game.getBlinde(), 0.0);
	}

	@Test
	public void testSetBlinde() {
		double blinde = 10.0;
		double moneyBetCheck =0;
		double totalMoneyCheck = 2000;
		for (int i = 0; i < 7; i++) {
			Player player = new Player("Player " + i);
			player.setTotalMoney(totalMoneyCheck);
			player.setMoneyBet(moneyBetCheck);
			players.add(player);
		}
		for (int i = 0; i <= 20; i++) {
			game.setManche(i);
			game.setBlindeRound(blinde);
			System.out.println(game.getPlayers().get(i).getTotalMoney());
			System.out.println(game.getPlayers().get(i).getMoneyBet());
			moneyBetCheck += game.getPlayers().get(i).getMoneyBet();
			assertEquals(1990.0, game.getPlayers().get(i).getTotalMoney(), 0.0);
			assertEquals(10.0, game.getPlayers().get(i).getMoneyBet(), 0.0);
		}
	}

	@Test
	public void testGetPot() {
		assertNotNull(game.getPot());
		assertEquals(0.0, game.getPot(), 0.0);
	}

	@Test
	public void testSetPot() {
	this.game.setPot(100);
	assertEquals(100, this.game.getPot());
	assertNotNull(this.game.getPot());
	this.game.setPot(500);
	assertEquals(500, this.game.getPot());
	assertNotNull(this.game.getPot());
	}

	@Test
	public void testSetMaxBet() {
		assertNotNull(game.getPot());
		assertEquals(0, game.getPot(), 0.0);
		game.setMaxBet(100);
		assertNotNull(game.getMaxBet());
		assertEquals(100, game.getMaxBet(), 0.0);
	}

	@Test
	public void testIsOnPlay() {
		assertTrue(game.isOnPlay());
		game.setOnPlay(false);
		assertTrue(!game.isOnPlay());
	}

	@Test
	public void testSetOnPlay() {
		assertTrue(game.isOnPlay());
		game.setOnPlay(false);
		assertTrue(!game.isOnPlay());
	}

	@Test
	public void testSetPlayersListOfPlayer() {
		List<Player> playersCheck = new ArrayList<>();
		for (int i = 0; i < 7; i++)
			playersCheck.add(new Player("Player " + i));
		game.setPlayers(playersCheck);
		assertTrue(!game.getPlayers().isEmpty());
		assertEquals(playersCheck, game.getPlayers());
	}

	@Test
	public void testIsDebugMode() {
		assertTrue(!game.isDebugMode());
		game.setDebugMode(true);
		assertTrue(game.isDebugMode());
	}

	@Test
	public void testSetDebugMode() {
		assertTrue(!game.isDebugMode());
		game.setDebugMode(true);
		assertTrue(game.isDebugMode());
	}

	@Test
	public void testWinMoney() {
		game.setPot(1000);
		for (Player player : game.getPlayers())
			game.winMoney(player);
		for (Player player : game.getPlayers())
			assertEquals(2000, player.getTotalMoney(), 0.0);
	}

	@Test
	public void testCheck() {
		for (int i = 0; i < 7; i++) {
			Player player = new Player("PlayerTest");
			players.add(player);
		}
		game.getPlayers().get(0).setTotalMoney(2000);
		game.getPlayers().get(0).setMoneyBet(100);
		game.check(game.getPlayers().get(0));
		assertEquals(2000, game.getPlayers().get(0).getTotalMoney(), 0.0);
		assertEquals(100, game.getPlayers().get(0).getMoneyBet(), 0.0);
	}

	@Test
	public void testFold() {
		for (int i = 0; i < 7; i++) {
			Player player = new Player("PlayerTest");
			players.add(player);
		}
		game.getPlayers().get(0).setTotalMoney(2000);
		game.getPlayers().get(0).setMoneyBet(100);
		game.fold(game.getPlayers().get(0));
		assertEquals(2000, game.getPlayers().get(0).getTotalMoney(), 0.0);
		assertEquals(0, game.getPlayers().get(0).getMoneyBet(), 0.0);
		assertTrue(!game.getPlayers().get(0).isInPlay());
	}

	@Test
	public void testAllIn() {
		for (int i = 0; i < 7; i++) {
			Player player = new Player("PlayerTest");
			players.add(player);
		}
		game.getPlayers().get(0).setTotalMoney(2000);
		game.getPlayers().get(0).setMoneyBet(100);
		game.allIn(game.getPlayers().get(0));
		assertEquals(0, game.getPlayers().get(0).getTotalMoney(), 0.0);
		assertEquals(2100, game.getPlayers().get(0).getMoneyBet(), 0.0);
	}

	@Test
	public void testCall() {
		for (int i = 0; i < 7; i++) {
			Player player = new Player("PlayerTest");
			players.add(player);
		}
		game.getPlayers().get(0).setTotalMoney(2000);
		game.getPlayers().get(0).setMoneyBet(100);
		game.setMaxBet(200);
		game.call(game.getPlayers().get(0));
		assertEquals(200, game.getPlayers().get(0).getMoneyBet());
		assertEquals(1900, game.getPlayers().get(0).getTotalMoney());
	}

	@Ignore
	@Test
	public void testRaise() {
		for (int i = 0; i < 7; i++) {
			Player player = new Player("PlayerTest");
			players.add(player);
		}
		game.getPlayers().get(0).setTotalMoney(2000);
		game.getPlayers().get(0).setMoneyBet(100);
		game.setMaxBet(200);
		game.raise(game.getPlayers().get(0));
		// Pour que le test fonctionne, saisir 100;
		assertEquals(300, game.getPlayers().get(0).getMoneyBet());
		assertEquals(1800, game.getPlayers().get(0).getTotalMoney());
	}

	@Test
	public void testDistributeCards() {
		List<Cards> cardsPackageCheck = new ArrayList<>(game.getDealer().getCardsPackage());
		for (int i = 0; i < 7; i++) {
			Player player = new Player("PlayerTest");
			players.add(player);
		}
		game.setTourDeTable(0);
		game.distributeCards();
		assertNotEquals(cardsPackageCheck, game.getDealer().getCardsPackage());
		game.getPlayers().forEach(p -> assertEquals(2, p.getHand().size()));
		assertTrue(game.getDealer().getCarpet().isEmpty());
		assertEquals(0, game.getDealer().getCarpet().size());
		assertEquals(38, game.getDealer().getCardsPackage().size());
		game.setTourDeTable(1);
		game.distributeCards();
		assertEquals(3, game.getDealer().getCarpet().size());
		assertEquals(34, game.getDealer().getCardsPackage().size());
		game.setTourDeTable(2);
		game.distributeCards();
		assertEquals(4, game.getDealer().getCarpet().size());
		assertEquals(32, game.getDealer().getCardsPackage().size());
		game.setTourDeTable(3);
		game.distributeCards();
		assertEquals(5, game.getDealer().getCarpet().size());
		assertEquals(30, game.getDealer().getCardsPackage().size());
	}

	@Test
	public void testRecoverCards() {
		List<Cards> cardsPackageCheck = new ArrayList<>(game.getDealer().getCardsPackage());
		for (int i = 0; i < 7; i++) {
			Player player = new Player("PlayerTest");
			players.add(player);
		}
		for (int i = 0; i <= 3; i++) {
			game.setTourDeTable(i);
			game.distributeCards();
		}
		game.recoverCards();
		game.getPlayers().forEach(p -> assertEquals(0, p.getHand().size()));
		assertEquals(52, game.getDealer().getCardsPackage().size());
		assertEquals(0, game.getDealer().getCarpet().size());
	}

	@Test
	public void testGetPlayersInGame() {
		for (int i = 0; i < 7; i++) {
			Player player = new Player("PlayerTest");
			players.add(player);
		}
		assertEquals(7, game.getPlayersInGame().size());
	}

	@Test
	public void testGetPlayersInPlay() {
		for (int i = 0; i < 7; i++) {
			Player player = new Player("PlayerTest");
			players.add(player);
		}
		assertEquals(7, game.getPlayersInPlay().size());
		game.getPlayers().forEach(p -> game.fold(p));
		assertEquals(0, game.getPlayersInPlay().size());
	}

	@Test
	public void testShowAllHands() {
		List<Cards> hand = new ArrayList<>();
		hand.add(Cards.As_Carreau);
		hand.add(Cards.As_Carreau);
		for (int i = 0; i < 7; i++) {
			Player player = new Player("PlayerTest");
			player.setHand(hand);
			players.add(player);
		}
		game.showAllHands();
		String checkString = "Situation finale : \r\n"
				+ "Player [playerName=PlayerTest, hand=[As_Carreau, As_Carreau], moneyBet=0.0€]\r\n"
				+ "Player [playerName=PlayerTest, hand=[As_Carreau, As_Carreau], moneyBet=0.0€]\r\n"
				+ "Player [playerName=PlayerTest, hand=[As_Carreau, As_Carreau], moneyBet=0.0€]\r\n"
				+ "Player [playerName=PlayerTest, hand=[As_Carreau, As_Carreau], moneyBet=0.0€]\r\n"
				+ "Player [playerName=PlayerTest, hand=[As_Carreau, As_Carreau], moneyBet=0.0€]\r\n"
				+ "Player [playerName=PlayerTest, hand=[As_Carreau, As_Carreau], moneyBet=0.0€]\r\n"
				+ "Player [playerName=PlayerTest, hand=[As_Carreau, As_Carreau], moneyBet=0.0€]";
//		assertEquals(checkString, game.showAllHands());
	}

	@Test
	public void testGetWinner() {
		for (int i = 0; i < 4; i++) {
			Player player = new Player("PlayerTest" + i);
			players.add(player);
		}
		List<Cards> hand1 = new ArrayList<>();
		hand1.add(Cards.As_Carreau);
		hand1.add(Cards.Roi_Carreau);
		List<Cards> hand2 = new ArrayList<>();
		hand2.add(Cards.Trois_Carreau);
		hand2.add(Cards.Trois_Carreau);
		List<Cards> hand3 = new ArrayList<>();
		hand3.add(Cards.Roi_Coeur);
		hand3.add(Cards.Valet_Coeur);
		List<Cards> hand4 = new ArrayList<>();
		hand4.add(Cards.Sept_Coeur);
		hand4.add(Cards.Huit_Coeur);
		List<Cards> carpet = new ArrayList<>();
		carpet.add(Cards.Deux_Pique);
		carpet.add(Cards.Trois_Pique);
		carpet.add(Cards.Quatre_Coeur);
		carpet.add(Cards.Cinq_Coeur);
		carpet.add(Cards.Six_Coeur);
		game.getPlayers().get(0).setHand(hand1);
		game.getPlayers().get(1).setHand(hand2);
		game.getPlayers().get(2).setHand(hand3);
		game.getPlayers().get(3).setHand(hand4);
		game.getDealer().setCarpet(carpet);
		Player winner = game.getWinner(players);
		assertEquals(game.getPlayers().get(3).getPlayerName(), winner.getPlayerName());
	}

	@Test
	public void testEliminate() {
		for (int i = 0; i < 7; i++)
			game.getPlayers().add(new Player("Player " + i));
		game.eliminate(players.get(0));
		assertTrue(!players.get(0).isInGame());
		assertEquals(6, game.getPlayersInGame().size());
	}

	@Ignore
	@Test
	public void testEndGame() {
		fail("Not yet implemented");
	}

	@Ignore
	@Test
	public void testEndRound() {
		fail("Not yet implemented");
	}

	@Ignore
	@Test
	public void testScenario1() {
		fail("Not yet implemented");
	}

	@Ignore
	@Test
	public void testScenario2() {
		fail("Not yet implemented");
	}

	@Ignore
	@Test
	public void testScenario3() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetCombo() {
		Player playerTest = new Player("playerTest");
		List<Cards> hand1 = new ArrayList<>();
		hand1.add(Cards.As_Carreau);
		hand1.add(Cards.Roi_Carreau);
		playerTest.setHand(hand1);
		List<Cards> carpet = new ArrayList<>();
		carpet.add(Cards.As_Pique);
		carpet.add(Cards.Roi_Pique);
		carpet.add(Cards.Quatre_Carreau);
		carpet.add(Cards.Cinq_Carreau);
		carpet.add(Cards.Six_Carreau);
		Dealer dealer =  new Dealer(carpet, carpet);
		dealer.setCarpet(carpet);
		game.getCombo(playerTest);
		assertEquals(1586875, playerTest.getScore());
	}

	@Ignore
	@Test
	public void testPlayReport() {
		fail("Not yet implemented");
	}
}
