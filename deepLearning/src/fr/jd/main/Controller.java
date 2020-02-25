package fr.jd.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Jonathan
 * Classe impl�mentant Controller, le gestionnaire de la partie
 */
public class Controller {
	/**
	 * Attribut de Controller : game de type Game
	 */
	private Game game;

	/**
	 * Getter de game
	 * @return game
	 */
	public Game getGame() {
		return game;
	}

	/**
	 * Stter de game
	 * @param game
	 */
	public void setGame(Game game) {
		this.game = game;
	}

	/**
	 * Constructeur de Controller
	 */
	public Controller() {
		super();
		this.game = new Game(null);
	}

	/**
	 * M�thode impl�mentant d'initialisation du jeu
	 */
	public void initialize() {
		boolean isCorrect = false;
		while (!isCorrect) {
			game.setPlayers();
			if (game.getPlayersInGame().size() >= 2) {
				game.initialize();
				isCorrect = true;
			} else {
				System.out.println("Desolez vous devez au moins etre deux pour jouer, veuillez recommencer.");
			}
		}
	}

	/**
	 * M�thode impl�menatant la configuration de la partie
	 */
	public void configure() {
//		game.configureDebugMode();
	}

	/**
	 * M�thode impl�mentant le d�but d'une manche
	 */
	public void startRound() {
		System.out.println("Manche N�: " + game.getManche());
		game.setPot(0);
		game.shuffleCards();
		game.distributeCards();
		game.setBlinde(game.getBlinde());
		game.setTourDeTable(1);
	}

	/**
	 * M�thode impl�mentant le d�roulement d'une manche, tour par tour
	 */
	public void round() {
		while (game.getPlayersInPlay().size() > 1 && game.getTourDeTable() <= 4) {
			boolean isBetPaid = false;
			game.distributeCards();
			while (game.getPlayersInPlay().size() > 1 && !isBetPaid) {
				for (Player player : game.getPlayersInPlay()) {
					System.out.println("Tour de table N�: " + game.getTourDeTable());
					System.out.println();
					System.out.println("A vous de jouer " + player.getPlayerName() + "!");
					System.out.println("Votre main : " + player.getHand());
					System.out.println("Sur la table il y a : " + game.getDealer().getCarpet());
					System.out.println("La mise est de : " + game.getMaxBet() + " �");
					System.out.println("Le pot a remport� est de : " + game.getPot() + " �");
					System.out.println("Vous disposez de : " + player.getTotalMoney() + " �");
					System.out.println("Votre mise pour le moment est de : " + player.getMoneyBet() + " �");
					System.out.println("Les autres disposent de : ");
					System.out.println();
					game.getPlayersInPlay().forEach(
							p -> System.out.println("Joueur : " + p.getPlayerName() + "    Argent sur la table : "
									+ p.getMoneyBet() + "�     Argent total : " + p.getTotalMoney() + " �."));
					System.out.println();
					System.out.println("Choisir parmis les options suivantes : ");
					System.out.println("Tapez 1 : Tapis");
					System.out.println("Tapez 2 : Se Coucher");
					System.out.println();
					if (player.getMoneyBet() == game.getMaxBet()) {
						game.scenario1(player);
					} else if ((player.getMoneyBet() < game.getMaxBet())
							&& (game.getMaxBet() - player.getMoneyBet() > player.getTotalMoney())) {
						game.scenario2(player);

					} else if (player.getMoneyBet() < game.getMaxBet()) {
						game.scenario3(player);
					}
					game.setPot(game.getPot() + player.getMoneyBet());
					if (game.getMaxBet() < player.getMoneyBet())
						game.setMaxBet(player.getMoneyBet());
					if (game.getPlayersInPlay().size() <= 1)
						break;
					isBetPaid = true;
					for (Player gamer : game.getPlayersInPlay()) {
						if (game.getMaxBet() > gamer.getMoneyBet())
							isBetPaid = false;
					}
				}
				for (Player gamer : game.getPlayersInPlay())
					if (isBetPaid == true) {
						gamer.setMoneyBet(0);
						game.setMaxBet(0);
					}

			}
			game.setTourDeTable(game.getTourDeTable() + 1);
		}
	}

	/**
	 * M�thode impl�mentant la finalisation d'une manche
	 */
	public void endRound() {
		List<Player> playersResults = new ArrayList<>();
		if (game.getPlayersInPlay().size() == 1) {
			System.out.println("Le gagnant de la manche N� " + game.getManche() + " est "
					+ game.getPlayersInPlay().get(0).getPlayerName());
			System.out.println("car tous les autres se sont couch�s");
			System.out.println("et remporte : " + game.getPot() + "�");
			System.out.println();
			game.getPlayersInPlay().get(0).winMoney(game.getPot());
		} else {
			for (Player player : game.getPlayersInGame()) {
				if (player.isInPlay() || (player.isAllIn() && !player.isInPlay())) playersResults.add(game.getCombo(player));
			}
			System.out.println("Le gagnant de cette manche est : " + game.getWinner(playersResults).getPlayerName());
			System.out.println("Voici tous les mains des joueurs :");
			this.getGame().getPlayersInGame()
					.forEach(p -> System.out.println("Joueur " + p.getPlayerName() + " a " + p.getHand()));
			for (Player player : game.getPlayersInGame())
				if (player.getTotalMoney() <= 0)
					game.eliminate(player);
		}
		game.endRound();
	}

	/**
	 * M�thode impl�menatnt la finalisation d'une partie
	 */
	public void endGame() {
		game.endGame();
	}

	/**
	 * M�thode retournant l'information de type String concernant le gestionnaire de jeu
	 */
	@Override
	public String toString() {
		return "Controller [game=" + game + "]";
	}
}