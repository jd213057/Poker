package fr.jd.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import fr.jd.main.Bot.DIFFICULTY;

/**
 * @author Jonathan
 * Classe implémentant Controller, le gestionnaire de la partie
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
	 * Setter de game
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
	 * Méthode implémentant d'initialisation du jeu
	 */
	public void initialize() {
		boolean isCorrect = false;
		while (!isCorrect) {
			game.setPlayers();
			game.configureBots();
			if (game.getPlayersInGame().size() >= 2) {
				game.initialize();
				isCorrect = true;
			} else {
				System.out.println("Desolez vous devez au moins etre deux pour jouer, veuillez recommencer.");
			}
		}
	}

	/**
	 * Méthode implémenatant la configuration de la partie
	 */
	public void configure() {
//		game.configureDebugMode();
	}

	/**
	 * Méthode implémentant le début d'une manche
	 */
	public void startRound() {
		System.out.println("Manche N°: " + game.getManche());
		game.setPot(0);
		game.shuffleCards();
		game.distributeCards();
		game.setBlindeRound(game.getBlinde());
		game.setTourDeTable(1);
	}

	/**
	 * Méthode implémentant le déroulement d'une manche, tour par tour
	 */
	public void round() {
		while (game.getPlayersInPlay().size() > 1 && game.getTourDeTable() <= 4) {
			boolean isBetPaid = false;
			game.distributeCards();
			while (game.getPlayersInPlay().size() > 1 && !isBetPaid) {
				for (Player player : game.getPlayersInPlay()) {
					if (player instanceof Bot) {
						System.out.println("Tour de table N°: " + game.getTourDeTable());
						System.out.println();
						System.out.println("C'est au tour de " + player.getPlayerName() + " de jouer !");
						System.out.println("La mise est de : " + game.getMaxBet() + " €");
						System.out.println("Le pot a remporté est de : " + game.getPot() + " €");
						System.out.println("Le joueur " + player.getPlayerName() + " a sur lui encore "
								+ player.getTotalMoney() + " €");
						System.out.println("Pour le moment la mise de joueur " + player.getPlayerName() + " est de : "
								+ player.getMoneyBet() + " €");
						System.out.println();
						System.out.println("Cartes sur la table : " + game.getDealer().getCarpet()); // a enlever
						
						if (((Bot) player).getDifficulty().equals(DIFFICULTY.EASY)) game.getEasyBotMove((Bot) player);
						if (((Bot) player).getDifficulty().equals(DIFFICULTY.MEDIUM)) game.getMediumBotMove((Bot) player);
						if (((Bot) player).getDifficulty().equals(DIFFICULTY.HARDCORE)) game.getHardcoreBotMove((Bot) player);
						System.out.println();
						if (player.isAllIn()) System.out.println("Le joueur " + player.getPlayerName() + " a fait tapis.");
						else if (!(player.isInPlay()) && !(player.isAllIn())) 
							System.out.println("Le joueur " + player.getPlayerName() + " s'est couché.");
						else if (player.getMoneyBet() == game.getMaxBet()) 
							System.out.println("Le joueur " + player.getPlayerName() + " a suivi ou a checké.");
						else if (player.getMoneyBet() > game.getMaxBet()) {
							System.out.println("Le joueur " + player.getPlayerName() + " a relancé de : "
									+ (player.getMoneyBet() - game.getMaxBet()) + " €.");
							System.out.println("Il lui reste : " + player.getTotalMoney() + " €.");
							System.out.println();
						}
						System.out.println();
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

					if (!(player instanceof Bot)) {
						System.out.println("Tour de table N°: " + game.getTourDeTable());
						System.out.println();
						System.out.println("A vous de jouer " + player.getPlayerName() + "!");
						System.out.println("Votre main : " + player.getHand());
						System.out.println("Sur la table il y a : " + game.getDealer().getCarpet());
						System.out.println("La mise est de : " + game.getMaxBet() + " €");
						System.out.println("Le pot a remporté est de : " + game.getPot() + " €");
						System.out.println("Vous disposez de : " + player.getTotalMoney() + " €");
						System.out.println("Votre mise pour le moment est de : " + player.getMoneyBet() + " €");
						System.out.println("Les autres disposent de : ");
						System.out.println();
						game.getPlayersInPlay().forEach(
								p -> System.out.println("Joueur : " + p.getPlayerName() + "    Argent sur la table : "
										+ p.getMoneyBet() + "€     Argent total : " + p.getTotalMoney() + " €."));
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

				}
				for (Player gamer : game.getPlayersInPlay())
					if (isBetPaid == true) {
						gamer.setMoneyBet(0);
						game.setMaxBet(0);
					}

			}
			game.setNbTour(0);
			game.setTourDeTable(game.getTourDeTable() + 1);
		}
	}

	/**
	 * Méthode implémentant la finalisation d'une manche
	 */
	public void endRound() {
		List<Player> playersResults = new ArrayList<>();
		if (game.getPlayersInPlay().size() == 1) {
			System.out.println("Le gagnant de la manche N° " + game.getManche() + " est "
					+ game.getPlayersInPlay().get(0).getPlayerName());
			System.out.println("car tous les autres se sont couchés");
			System.out.println("et remporte : " + game.getPot() + "€");
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
	 * Méthode implémenatnt la finalisation d'une partie
	 */
	public void endGame() {
		game.endGame();
	}

	/**
	 * Méthode retournant l'information de type String concernant le gestionnaire de jeu
	 */
	@Override
	public String toString() {
		return "Controller [game=" + game + "]";
	}
}