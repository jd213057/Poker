package fr.jd.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Controller {
	private Game game;

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public Controller() {
		super();
		this.game = new Game(null);
	}

	public void initialize() {
		game.setPlayers();
		System.out.println("Souhaitez-vous activer le mode Debug sur votre jeu? y/n");
		Scanner keyboard = new Scanner(System.in);
		String answer = keyboard.toString();
		keyboard.close();
		switch (answer) {
		case "y":
			game.setDebugMode(true);
			break;
		case "n":
			break;
		default:
			System.out.println("Réponse non pris en charge.");
			System.out.println("Par mesure de sécurité, l'application va se lancer sans le mode debug.");
			System.out.println();
		}
	}

	public void configure() {
		// TODO Auto-generated method stub

	}

	public void round() {
		System.out.println("Manche N°: " + game.getManche());
		game.distributeCards();
		game.setBlinde(game.getBlinde());
		while (game.getPlayersInPlay().size() < 1) {
			for (Player player : game.getPlayersInPlay()) {
				System.out.println("Tour de table N°: " + game.getTourDeTable());
				System.out.println();
				System.out.println("Votre main : " + player.getHand());
				System.out.println("La mise est de : " + game.getMaxBet());
				System.out.println("Vous disposez de : " + player.getTotalMoney() + " €");
				System.out.println("Votre mise pour le moment est de : " + player.getMoneyBet());
				System.out.println("Les autres disposent de : ");
				System.out.println();
				System.out.println("Choisir parmis les options suivantes : ");
				System.out.println("Tapez 1 : Tapis");
				double moneyToCall = game.getMaxBet() - player.getMoneyBet();
				System.out.println("Tapez 2 : Se Coucher");
				game.getPlayersInPlay()
						.forEach(p -> System.out.println("Joueur : " + p.getPlayerName() + "    Argent sur la table : "
								+ p.getMoneyBet() + "€     Argent total : " + p.getTotalMoney() + "€."));
				if (player.getMoneyBet() == game.getMaxBet()) {
					game.scenario1(player);
				} else if (player.getMoneyBet() < game.getMaxBet()
						&& game.getMaxBet() - player.getMoneyBet() > player.getTotalMoney()) {
					game.scenario2(player);

				} else if (player.getMoneyBet() < game.getMaxBet()) {
					game.scenario3(player);
				}

			}
		}
	}

	public void endRound() {
		List<Player> playersResults = new ArrayList<>();
		if (game.getPlayersInPlay().size() == 1) {
			System.out.println("Le gagnant de la manche N° " + game.getManche() + " est "
					+ game.getPlayersInPlay().get(0).getPlayerName());
			System.out.println("car tous les autres se sont couchés");
			System.out.println("et remporte : " + game.getPot());
			System.out.println();
			game.getPlayersInPlay().get(0).winMoney(game.getPot());
		}
		for (Player player : game.getPlayersInPlay()) {
			playersResults.add(game.getCombo(player));
		}
		game.getWinner(playersResults);
		game.showAllHands();
		for (Player player : game.getPlayersInGame())
			if (player.getTotalMoney() <= 0)
				game.eliminate(player);
		if (game.getPlayersInGame().size() < 1)
			game.endGame();
		else
			game.endRound();
	}

	@Override
	public String toString() {
		return "Controller [game=" + game + "]";
	}

}
