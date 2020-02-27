package fr.jd.main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Scanner;

import fr.jd.main.Bot.DIFFICULTY;

/**
 * @author Jonathan Classe Game implémenant la partie de poker
 */
public class Game {
	/**
	 * Attribut de Game : players, liste des joueurs de la partie
	 */
	private List<Player> players = new ArrayList<>();
	/**
	 * Attribut de Game : dealer
	 */
	private Dealer dealer;
	/**
	 * Attribut de Game : tourdeTable
	 */
	private int tourDeTable;
	/**
	 * Attribut de Game : manche
	 */
	private int manche;
	/**
	 * Attribut de Game : positionPetiteBlinde
	 */
	private int positionPetiteBlinde;
	/**
	 * Attribut de Game : positionGrosseBlinde
	 */
	private int positionGrosseBlinde;
	/**
	 * Attribut de Game : nbTour
	 */
	private int nbTour;
	/**
	 * Attribut de Game : mancheBlinde
	 */
	private int mancheBlinde;
	/**
	 * Attribut de Game : maxBet
	 */
	private double maxBet;
	/**
	 * Attribut de Game : blinde
	 */
	private double blinde;
	/**
	 * Attribut de Game : pot
	 */
	private double pot;
	/**
	 * Attribut de Game : onPlay
	 */
	private boolean onPlay;
	/**
	 * Attribut de Game : debugMode
	 */
	private boolean debugMode;

	/**
	 * Attribut de Game : keyboard, reader pour le mode console du jeu
	 */
	Scanner keyboard = new Scanner(System.in);

	/**
	 * Constructeur de Game
	 * 
	 * @param players
	 */
	public Game(List<Player> players) {
		super();
		this.players = players;
		this.tourDeTable = 0;
		this.nbTour = 0;
		this.manche = 1;
		this.mancheBlinde = 10;
		this.positionPetiteBlinde = 0;
		this.positionGrosseBlinde = 0;
		this.maxBet = 0;
		this.blinde = 0;
		this.pot = 0;
		this.onPlay = true;
		this.debugMode = false;
		this.dealer = new Dealer(new ArrayList<Cards>(EnumSet.allOf(Cards.class)), new ArrayList<Cards>());
	}

	/**
	 * Getter de players
	 * 
	 * @return players
	 */
	public List<Player> getPlayers() {
		return players;
	}

	/**
	 * Setter de players
	 * 
	 * @param players
	 */
	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	/**
	 * Méthode implémentant la configuration des joueurs
	 */
	public void setPlayers() {
		System.out.println("Entrez le nombre de joueurs humains: ");
		int nbPlayers = keyboard.nextInt();
		keyboard.nextLine();
		List<Player> players = new ArrayList<>();
		for (int i = 1; i <= nbPlayers; i++) {
			System.out.println("Entrez le nom du joueur N°" + i + ":");
			String playerName = keyboard.nextLine();
			players.add(new Player(playerName));
		}
		this.setPlayers(players);
	}

	/**
	 * Méthode implémentant les Bots dans une partie de Poker
	 */
	public void configureBots() {
		System.out.println();
		System.out.println("Entrez le nombre de Bots qui vont jouer :");
		String answer1 = keyboard.nextLine();
		int number = Integer.parseInt(answer1);
		for (int i = 0; i < number; i++) {
			System.out.println();
			System.out.println("Veuillez donner un nom au Bot :");
			String answer2 = keyboard.nextLine();
			Bot bot = new Bot(answer2);
			System.out.println("");
			System.out.println("Veuillez selectionner une difficulté pour ce Bot :");
			System.out.println("Tapez 1 pour Easy");
			System.out.println("Tapez 2 pour Medium");
			System.out.println("Tapez 3 pour Hardcore");
			System.out.println("");
			String answer3 = keyboard.nextLine();
			switch (answer3) {
			case "1":
				bot.setDifficulty(bot.getDifficulty().EASY);
				this.players.add(bot);
				break;
			case "2":
				bot.setDifficulty(bot.getDifficulty().MEDIUM);
				this.players.add(bot);
				break;
			case "3":
				bot.setDifficulty(bot.getDifficulty().HARDCORE);
				this.players.add(bot);
				break;
			default:
				System.out.println("Saisie incorrecte, par mesure de sécurité la difficulté a été mise à EASY");
				bot.setDifficulty(bot.getDifficulty().EASY);
				this.players.add(bot);
				break;
			}
		}
		System.out.println("Configuration du Bot terminée.");
		System.out.println();
	}

	/**
	 * Méthode implémentant l'initialisation de la partie
	 */
	public void initialize() {
		System.out.println("Appuyez sur Entrée pour initialiser la partie.");
		keyboard.nextLine();
		System.out.println("Souhaitez-vous activer le mode Debug sur votre jeu? y/n");
		String answer = keyboard.nextLine();
		switch (answer) {
		case "y":
			this.setDebugMode(true);
			break;
		case "n":
			break;
		default:
			System.out.println("Réponse non pris en charge.");
			System.out.println("Par mesure de sécurité, l'application va se lancer sans le mode debug.");
			System.out.println();
			break;
		}
		System.out.println("Saisir le montant initial pour chaque joueur :");
		double answer1 = keyboard.nextDouble();
		System.out.println("Saisir le montant de la grosseBlinde :");
		double answer2 = keyboard.nextDouble();
		System.out.println("Doubler la blinde toutes les combien de manche ?");
		int answer3 = keyboard.nextInt();
		for (Player player : this.getPlayers())
			player.setTotalMoney(answer1);
		this.setBlinde(answer2);
		this.setMancheBlinde(answer3);
	}

//	public void configureDebugMode() {
//String answer1;
//String answer2;
//String answer3;
//this.setDebugMode(true);
//System.out.println("Lancement du DebugMode");
//System.out.println("Souhaitez-vous activer les codes de triches? (y/n)");
//answer1 = keyboard.nextLine();
//System.out.println("Souhaitez-vous sauvegardé les rapports de jeu? (y/n)");
//answer2 = keyboard.nextLine();
//System.out.println("Souhaitez-vous affichez les probabilités de jeu en temps réel? (y/n)");
//answer3 = keyboard.nextLine();
//answer1 = "y" ? this.setCheatMode(true) : this.setCheatMode(false);
//answer2 = "y" ? this.setCheatMode(true) : this.setCheatMode(false);
//answer3 = "y" ? this.setCheatMode(true) : this.setCheatMode(false);
//}

	private void setBlinde(double answer2) {
		this.blinde = answer2;
	}

	/**
	 * Getter de tourdeTable
	 * 
	 * @return tourDeTable
	 */
	public int getTour() {
		return tourDeTable;
	}

	/**
	 * Setter de tourDeTable
	 * 
	 * @param tourDeTable
	 */
	public void setTour(int tourDeTable) {
		this.tourDeTable = tourDeTable;
	}

	/**
	 * Getter de dealer
	 * 
	 * @return dealer
	 */
	public Dealer getDealer() {
		return dealer;
	}

	/**
	 * Setter de dealer
	 * 
	 * @param dealer
	 */
	public void setDealer(Dealer dealer) {
		this.dealer = dealer;
	}

	/**
	 * Getter de tourDeTable
	 * 
	 * @return tourDeTable
	 */
	public int getTourDeTable() {
		return tourDeTable;
	}

	/**
	 * Setter de tourDeTable
	 * 
	 * @param tourDeTable
	 */
	public void setTourDeTable(int tourDeTable) {
		this.tourDeTable = tourDeTable;
	}

	/**
	 * Getter mancheBlinde
	 * 
	 * @return mancheBlinde
	 */
	public int getMancheBlinde() {
		return mancheBlinde;
	}

	/**
	 * Setter de mancheBlinde
	 * 
	 * @param mancheBlinde
	 */
	public void setMancheBlinde(int mancheBlinde) {
		this.mancheBlinde = mancheBlinde;
	}

	/**
	 * Getter de manche
	 * 
	 * @return manche
	 */
	public int getManche() {
		return manche;
	}

	/**
	 * Setter de manche
	 * 
	 * @param manche
	 */
	public void setManche(int manche) {
		this.manche = manche;
	}

	/**
	 * Getter de nbTour
	 * 
	 * @return nbTour
	 */
	public int getNbTour() {
		return nbTour;
	}

	/**
	 * Setter de nbTour
	 * 
	 * @param nbTour
	 */
	public void setNbTour(int nbTour) {
		this.nbTour = nbTour;
	}

	/**
	 * Getter de maxBet
	 * 
	 * @return maxBet
	 */
	public double getMaxBet() {
		return maxBet;
	}

	/**
	 * Setter de maxBet
	 * 
	 * @param maxBet
	 */
	public void setMaxBet(double maxBet) {
		this.maxBet = maxBet;
	}

	/**
	 * Getter de positionPetiteBlinde
	 * 
	 * @return positionBlinde
	 */
	public int getPositionPetiteBlinde() {
		return positionPetiteBlinde;
	}

	/**
	 * Setter de positionPetiteBlinde
	 */
	public void setPositionPetiteBlinde() {
		this.positionPetiteBlinde = (this.manche + 1) % (this.getPlayersInGame().size() + 1);
	}

	/**
	 * Getter de positionPetiteBlinde
	 * 
	 * @return positionPetiteBlinde
	 */
	public int getPositionGrosseBlinde() {
		return this.positionGrosseBlinde;
	}

	/**
	 * Setter de positionPetiteBlinde
	 */
	public void setPositionGrosseBlinde() {
		this.positionGrosseBlinde = this.manche % (this.getPlayersInGame().size() + 1);
	}

	/**
	 * Getter de blinde
	 * 
	 * @return blinde
	 */
	public double getBlinde() {
		return blinde;
	}

	/**
	 * Setter spéciale de blinde
	 * 
	 * @param blinde
	 */
	public void setBlindeRound(double blinde) {
		this.blinde = blinde;
		List<Player> playersInGame = new ArrayList<>();
		if (this.getManche() % this.getMancheBlinde() == 0) {
			this.blinde *= 2;
			System.out.println("La blinde augmonte ce tour ci est passa à : " + this.blinde + " €.");
		}
		for (Player player : this.players)
			if (player.isInGame())
				playersInGame.add(player);
		playersInGame.get(this.manche % getPlayersInGame().size()).PayGrosseBlinde(blinde);
		playersInGame.get((this.manche - 1) % getPlayersInGame().size()).PayPetiteBlinde(blinde);
		this.maxBet += blinde;
		this.pot += blinde;
	}

	/**
	 * Getter de pot
	 * 
	 * @return pot
	 */
	public double getPot() {
		return pot;
	}

	/**
	 * Setter de pot
	 * 
	 * @param pot
	 */
	public void setPot(double pot) {
		this.pot = pot;
	}

	/**
	 * Getter de onPlay
	 * 
	 * @return onPlay
	 */
	public boolean isOnPlay() {
		return onPlay;
	}

	/**
	 * Setter de onPlay
	 * 
	 * @param onPlay
	 */
	public void setOnPlay(boolean onPlay) {
		this.onPlay = onPlay;
	}

	/**
	 * Getter de debugMode
	 * 
	 * @return debugMode
	 */
	public boolean isDebugMode() {
		return debugMode;
	}

	/**
	 * Setter de debugMode
	 * 
	 * @param debugMode
	 */
	public void setDebugMode(boolean debugMode) {
		this.debugMode = debugMode;
	}

	/**
	 * Méthode implémentant l'encaissement de la mise par un joueur
	 * 
	 * @param player
	 */
	public void winMoney(Player player) {
		player.setTotalMoney(player.getTotalMoney() + this.pot);
	}

	/**
	 * Méthode implémentant le check par le joueur
	 * 
	 * @param player
	 */
	public void check(Player player) {
		player.check();
	}

	/**
	 * Méthode implémentant le fait de se coucher par le joueur
	 * 
	 * @param player
	 */
	public void fold(Player player) {
		player.fold();
		this.pot += player.getMoneyBet();
		player.setMoneyBet(0);
	}

	/**
	 * Méthode implémentant le tapis par le joueur
	 * 
	 * @param player
	 */
	public void allIn(Player player) {
		player.allIn();
		this.maxBet += player.getTotalMoney();
	}

	/**
	 * Méthode implémentant l'action de suivre par le joueur
	 * 
	 * @param player
	 */
	public void call(Player player) {
		player.call(this.maxBet - player.getMoneyBet());
	}

	/**
	 * Méthode implémentant la relance par le joueur
	 * 
	 * @param player
	 */
	public void raise(Player player) {
		System.out.println("De combien souhaitez-vous relancer?");
		System.out.println();
		double raiseMoney = keyboard.nextDouble();
		player.raise(this.maxBet - player.getMoneyBet() + raiseMoney);
		this.maxBet += raiseMoney;
	}

	/**
	 * Méthode déterminant l'action à mener par le Bot en fonction de son niveau et
	 * de sa main
	 * 
	 * @param bot
	 */
	public void getBotMove(Bot bot) {
		double probaCombo = this.getComboProbability(bot);
		if (this.tourDeTable > 1) {
			if (bot.getMoneyBet() == this.getMaxBet()) {
				if (bot.getDifficulty().equals(DIFFICULTY.EASY)) {
					if (probaCombo > 0.7 
							&& (bot.getTotalMoney() > 2 * (this.getMaxBet() - bot.getMoneyBet())))
						bot.raise(2 * (this.getMaxBet() - bot.getMoneyBet()));
					else
						bot.check();
				}

				else if (probaCombo > 0.25 && bot.getDifficulty().equals(DIFFICULTY.MEDIUM)
						&& (bot.getTotalMoney() > 2 * (this.getMaxBet() - bot.getMoneyBet()))) {
					if (this.pot < (10 * this.blinde))
						bot.raise(2 * (this.getMaxBet() - bot.getMoneyBet()));
					else
						bot.check();
				} else if (probaCombo > 0.25 && bot.getDifficulty().equals(DIFFICULTY.HARDCORE)
						&& (bot.getTotalMoney() > 3 * (this.getMaxBet() - bot.getMoneyBet()))) {
					if (this.pot < (10 * this.blinde))
						bot.raise(3 * (this.getMaxBet() - bot.getMoneyBet()));
					else
						bot.check();
				}
			} else if ((bot.getMoneyBet() < this.getMaxBet())
					&& (this.getMaxBet() - bot.getMoneyBet() > bot.getTotalMoney())) {
				if (probaCombo > 0.5 && bot.getDifficulty().equals(DIFFICULTY.HARDCORE))
					bot.allIn();
				else
					bot.fold();
			} else if (bot.getMoneyBet() < this.getMaxBet()) {
				if (probaCombo > 0.1 && bot.getDifficulty().equals(DIFFICULTY.EASY))
					bot.call(this.getMaxBet() - bot.getMoneyBet());
				else if (probaCombo > 0.25 && (bot.getDifficulty().equals(DIFFICULTY.MEDIUM)
						|| bot.getDifficulty().equals(DIFFICULTY.HARDCORE)))
					bot.call(this.getMaxBet() - bot.getMoneyBet());
				else if (probaCombo > 0.5 && bot.getDifficulty().equals(DIFFICULTY.HARDCORE)
						&& (bot.getTotalMoney() > 2 * (this.getMaxBet() - bot.getMoneyBet()))) {
					if (this.pot < (10 * this.blinde))
						bot.raise(2 * (this.getMaxBet() - bot.getMoneyBet()));
					else
						bot.check();
				} else if (probaCombo > 0.7 && bot.getDifficulty().equals(DIFFICULTY.HARDCORE))
					bot.allIn();
				else
					bot.fold();
			}
		} else {
			if (bot.getMoneyBet() == this.getMaxBet())
				bot.check();
			else
				bot.call(this.getMaxBet() - bot.getMoneyBet());
		}
		System.out.println(probaCombo); // a enlever
	}

	/**
	 * Méthode calculant les chances de victoire du Bot
	 * 
	 * @param bot
	 * @return probaCombo
	 */
	public double getComboProbability(Bot bot) {
		System.out.println("Le Bot " + bot.getPlayerName() + " a " + bot.getHand()); // a enlever
		double probaCombo = 1;
		List<Player> playersInPlayOrAllIn = new ArrayList<>();
		playersInPlayOrAllIn.addAll(this.getPlayersInPlay());
		for (Player player : this.getPlayersInGame())
			if (player.isAllIn())
				playersInPlayOrAllIn.add(player);
		List<Cards> botCombo = new ArrayList<>();
		List<Integer> playerResults = new ArrayList<>();
		botCombo.addAll(bot.getHand());
		botCombo.addAll(dealer.getCarpet());
		int playerFinalScore;
		switch (dealer.getCarpet().size()) {
		case 0:
			playerResults.add(Cards.checkOnePair(botCombo));
			playerResults.add(Cards.checkHighRaise(botCombo));
			playerFinalScore = Collections.max(playerResults);
			probaCombo = Cards.getProbaCoef(playersInPlayOrAllIn, playerFinalScore);
			break;
		case 1:
			playerResults.add(Cards.checkThreeOfAKind(botCombo));
			playerResults.add(Cards.checkOnePair(botCombo));
			playerResults.add(Cards.checkHighRaise(botCombo));
			playerFinalScore = Collections.max(playerResults);
			probaCombo = Cards.getProbaCoef(playersInPlayOrAllIn, playerFinalScore);
			break;
		case 2:
			playerResults.add(Cards.checkFourOfAKind(botCombo));
			playerResults.add(Cards.checkThreeOfAKind(botCombo));
			playerResults.add(Cards.checkOnePair(botCombo));
			playerResults.add(Cards.checkTwoPairs(botCombo));
			playerResults.add(Cards.checkHighRaise(botCombo));
			playerFinalScore = Collections.max(playerResults);
			probaCombo = Cards.getProbaCoef(playersInPlayOrAllIn, playerFinalScore);
			break;
		case 3:
		case 4:
		case 5:
			playerResults.add(Cards.checkStraightFlush(botCombo));
			playerResults.add(Cards.checkFourOfAKind(botCombo));
			playerResults.add(Cards.checkFull(botCombo));
			playerResults.add(Cards.checkFlush(botCombo));
			playerResults.add(Cards.checkStraight(botCombo));
			playerResults.add(Cards.checkThreeOfAKind(botCombo));
			playerResults.add(Cards.checkOnePair(botCombo));
			playerResults.add(Cards.checkTwoPairs(botCombo));
			playerResults.add(Cards.checkHighRaise(botCombo));
			playerFinalScore = Collections.max(playerResults);
			probaCombo = Cards.getProbaCoef(playersInPlayOrAllIn, playerFinalScore);
			break;
		default:
			System.out.println("Erreur - Impossible de lire dealer.getCarpet().");
			probaCombo = 0;
			break;
		}
		return probaCombo;
	}

	/**
	 * Méthode implémentant le mélange du paquet de cartes
	 */
	public void shuffleCards() {
		dealer.shuffle();
	}

	/**
	 * Méthode implémentant la distribution des cartes
	 */
	public void distributeCards() {
		if (this.tourDeTable == 0) {
			for (int j = 0; j < 2; j++) {
				for (int i = 0; i < players.size(); i++) {
					dealer.distribute(players.get(i));
				}
			}
			System.out.println("Jeu de cartes distribué aux joueurs");
		} else {
			dealer.putOnTable(this.tourDeTable - 1);
		}
	}

	/**
	 * Méthode implémentant la récupération des cartes
	 */
	public void recoverCards() {
		for (int i = 0; i < players.size(); i++)
			players.get(i).getHand().clear();
		dealer.recoverCards();

	}

	/**
	 * Méthode calculant la liste des joeurs en jeu sur la partie
	 * 
	 * @return playersInGame
	 */
	public List<Player> getPlayersInGame() {
		List<Player> playersInGame = new ArrayList<>();
		for (Player player : players)
			if (player.isInGame())
				playersInGame.add(player);
		return playersInGame;
	}

	/**
	 * Méthode construisant la liste des joeurs en jeu sur la manche
	 * 
	 * @return playersInPlay
	 */
	public List<Player> getPlayersInPlay() {
		List<Player> playersInPlay = new ArrayList<>();
		for (Player player : players)
			if (player.isInPlay() && player.isInGame())
				playersInPlay.add(player);
		return playersInPlay;
	}

	/**
	 * Méthode montrant l'ensemble des joueurs
	 */
	public void showAllHands() {
		System.out.println("Situation finale : ");
		List<Player> playersInPlay = this.getPlayersInPlay();
		playersInPlay.forEach(p -> System.out.println(p.toStringHand()));
		System.out.println();
	}

	/**
	 * Méthode determinant le gagant de la manche
	 * 
	 * @param playersResults
	 * @return winner
	 */
	public Player getWinner(List<Player> playersResults) {
		Player winner = null;
		List<Integer> results = new ArrayList<>();
		for (Player player : playersResults) {
			results.add(player.getScore());
		}
		int winnerScore = Collections.max(results);

		for (Player player : playersResults) {
			if (player.getScore() == winnerScore) {
				List<Cards> bestCombo = new ArrayList<Cards>();
				bestCombo.addAll(player.getHand());
				bestCombo.addAll(this.dealer.getCarpet());
				System.out.println("Le gagnant de la manche est : " + player.getPlayerName());
				System.out.println("avec la combinaison suivante : " + bestCombo);
				System.out.println("et remporte : " + this.pot + "");
				System.out.println();
				player.winMoney(this.pot);
				winner = player;
				return winner;
			}
		}
		return winner;
	}

	/**
	 * Méthode implémentant l'elemination du joueur
	 * 
	 * @param player
	 */
	public void eliminate(Player player) {
		player.setInGame(false);
		System.out.println("Le joueur : " + player.getPlayerName() + " a perdu.");
		System.out.println();
	}

	/**
	 * Méthode implémentant la finalisation d'une partie
	 */
	public void endGame() {
		List<Double> playersMoney = new ArrayList<>();
		this.onPlay = false;
		Player winner;
		if (this.getPlayersInGame().size() == 1) {
			winner = getPlayersInGame().get(0);
			System.out.println("Le gagnant est : " + winner.getPlayerName());
			System.out.println("");
		} else {
			for (Player player : this.getPlayersInGame()) {
				playersMoney.add(player.getTotalMoney());
			}
			for (Player player : this.getPlayersInGame())
				if (player.getTotalMoney() == Collections.max(playersMoney)) {
					winner = player;
					System.out.println("Le gagnant est : " + winner.getPlayerName());
					System.out.println("");
				}
		}
		System.out.println("Voici le récapitulatif de la partie : ");
		this.players.forEach(player -> System.out.println(player.toStringOnPlay()));
		System.out.println("Jeu terminé.");
	}

	/**
	 * Méthode implémentant la finalisation d'une manche
	 */
	public void endRound() {
		for (Player player : this.players) {
			if (player.isInGame() && player.getTotalMoney() > 0) {
				player.setInPlay(true);
			}
			player.setScore(0);
			player.setMoneyBet(0);
			player.setAllIn(false);
			player.getHand().clear();
		}
		this.tourDeTable = 0;
		this.manche++;
		dealer.recoverCards();
		System.out.println("Fin de la manche.");
		keyboard.nextLine();
		System.out.println("Continuer la prochaine manche? y/n");
		String answer = keyboard.nextLine();
		if (answer.equals("n") || answer.equals("N"))
			this.setOnPlay(false);
	}

	/**
	 * Méthode implémentant le scénario 1
	 * 
	 * @param player
	 */
	public void scenario1(Player player) {
		System.out.println("Tapez 3 : Relancer");
		System.out.println("Tapez 5 : Check");
		System.out.println();
		int playerMove = keyboard.nextInt();
		switch (playerMove) {
		case 1:
			player.allIn();
			if (player.getMoneyBet() > this.maxBet)
				this.maxBet = player.getMoneyBet();
			break;
		case 2:
			player.fold();
			break;
		case 3:
			boolean isCorrect = false;
			while (!isCorrect) {
				System.out.println("De combien souhaitez-vous relancer?");
				System.out.println("Attention à ne pas mettre plus que ce que vous avez!");
				int raiseMoney = keyboard.nextInt();
				System.out.println("Appuyez sur Entrée pour confirmer");
				keyboard.nextLine();
				if (raiseMoney <= (player.getTotalMoney() + (this.maxBet - player.getMoneyBet()))) {
					player.raise(raiseMoney);
					isCorrect = true;
				}
			}
			break;
		case 5:
			player.check();
			break;
		default:
			System.out.println("Vous avez tapé : " + playerMove + ".");
			System.out.println("La réponse n'est pas prise en charge par le système.");
			System.out.println();
			System.out.println("Réponse par défaut : Check");
			player.check();
			break;
		}

	}

	/**
	 * Méthode implémentant le scénario 2
	 * 
	 * @param player
	 */
	public void scenario2(Player player) {
		System.out.println();
		System.out.println("Vous n'avez pas assez pour suivre.");
		System.out.println("Appuyez sur Entrée avant de saisir votre choix");
		keyboard.nextLine();
		int playerMove = keyboard.nextInt();
		switch (playerMove) {
		case 1:
			player.allIn();
			if (player.getMoneyBet() > this.maxBet)
				this.maxBet = player.getMoneyBet();
			break;
		case 2:
			player.fold();
			break;
		default:
			System.out.println("Vous avez tapé : " + playerMove + ".");
			System.out.println("La réponse n'est pas prise en charge par le système.");
			System.out.println();
			System.out.println("Réponse par défaut : Se Coucher");
			player.fold();
			break;
		}

	}

	/**
	 * Méthode implémentant le scénario 3
	 * 
	 * @param player
	 */
	public void scenario3(Player player) {
		System.out.println("Tapez 3 : Relancer");
		System.out.println("Tapez 4 : Suivre (" + (this.maxBet - player.getMoneyBet()) + ")");
		System.out.println();
		int playerMove = keyboard.nextInt();
		switch (playerMove) {
		case 1:
			player.allIn();
			if (player.getMoneyBet() > this.maxBet)
				this.maxBet = player.getMoneyBet();
			break;
		case 2:
			player.fold();
			break;
		case 3:
			boolean isCorrect = false;
			while (!isCorrect) {
				System.out.println("De combien souhaitez-vous relancer?");
				System.out.println("Attention à ne pas mettre plus que ce que vous avez!");
				int raiseMoney = keyboard.nextInt();
				System.out.println("Appuyez sur Entrée pour confirmer");
				keyboard.nextLine();
				if (raiseMoney <= (player.getTotalMoney() + (this.maxBet - player.getMoneyBet()))) {
					player.raise(raiseMoney);
					isCorrect = true;
				}
			}
			break;
		case 4:
			double moneyToTransfer = this.maxBet - player.getMoneyBet();
			player.call(moneyToTransfer);
			break;
		default:
			System.out.println("Vous avez tapé : " + playerMove + ".");
			System.out.println("La réponse n'est pas prise en charge par le système.");
			System.out.println();
			System.out.println("Réponse par défaut : Se Coucher");
			player.fold();
			break;
		}
	}

	/**
	 * Méthode determinant la meilleure combinaison possible possédée par un joueur
	 * 
	 * @param player
	 * @return player
	 */
	public Player getCombo(Player player) {
		List<Cards> playerCombo = new ArrayList<Cards>();
		List<Integer> playerResults = new ArrayList<>();
		playerCombo.addAll(player.getHand());
		playerCombo.addAll(this.dealer.getCarpet());
		int playerFinalScore;
		playerResults.add(Cards.checkStraightFlush(playerCombo));
		playerResults.add(Cards.checkFourOfAKind(playerCombo));
		playerResults.add(Cards.checkFull(playerCombo));
		playerResults.add(Cards.checkFlush(playerCombo));
		playerResults.add(Cards.checkStraight(playerCombo));
		playerResults.add(Cards.checkThreeOfAKind(playerCombo));
		playerResults.add(Cards.checkOnePair(playerCombo));
		playerResults.add(Cards.checkTwoPairs(playerCombo));
		playerResults.add(Cards.checkHighRaise(playerCombo));
		playerFinalScore = Collections.max(playerResults);
		player.setScore(playerFinalScore);
		return player;
	}

	/**
	 * @return information concernat Game de type String
	 */
	public String playReport() {
		return "Game [players=" + players + ", dealer=" + dealer + ", tourDeTable=" + tourDeTable + ", manche=" + manche
				+ ", positionPetiteBlinde=" + positionPetiteBlinde + ", positionGrosseBlinde=" + positionGrosseBlinde
				+ ", nbTour=" + nbTour + ", maxBet=" + maxBet + ", blinde=" + blinde + ", pot=" + pot + ", onPlay="
				+ onPlay + "]";
	}

	/**
	 * Méthode Hashcode de Game
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(blinde);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((dealer == null) ? 0 : dealer.hashCode());
		result = prime * result + (debugMode ? 1231 : 1237);
		result = prime * result + ((keyboard == null) ? 0 : keyboard.hashCode());
		result = prime * result + manche;
		temp = Double.doubleToLongBits(maxBet);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + nbTour;
		result = prime * result + (onPlay ? 1231 : 1237);
		result = prime * result + ((players == null) ? 0 : players.hashCode());
		result = prime * result + positionGrosseBlinde;
		result = prime * result + positionPetiteBlinde;
		temp = Double.doubleToLongBits(pot);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + mancheBlinde;
		result = prime * result + tourDeTable;
		return result;
	}

	/**
	 * Méthode Equals de Game
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Game other = (Game) obj;
		if (Double.doubleToLongBits(blinde) != Double.doubleToLongBits(other.blinde))
			return false;
		if (dealer == null) {
			if (other.dealer != null)
				return false;
		} else if (!dealer.equals(other.dealer))
			return false;
		if (debugMode != other.debugMode)
			return false;
		if (keyboard == null) {
			if (other.keyboard != null)
				return false;
		} else if (!keyboard.equals(other.keyboard))
			return false;
		if (manche != other.manche)
			return false;
		if (Double.doubleToLongBits(maxBet) != Double.doubleToLongBits(other.maxBet))
			return false;
		if (nbTour != other.nbTour)
			return false;
		if (onPlay != other.onPlay)
			return false;
		if (players == null) {
			if (other.players != null)
				return false;
		} else if (!players.equals(other.players))
			return false;
		if (positionGrosseBlinde != other.positionGrosseBlinde)
			return false;
		if (positionPetiteBlinde != other.positionPetiteBlinde)
			return false;
		if (Double.doubleToLongBits(pot) != Double.doubleToLongBits(other.pot))
			return false;
		if (mancheBlinde != other.mancheBlinde)
			return false;
		if (tourDeTable != other.tourDeTable)
			return false;
		return true;
	}

	/**
	 * Méthode cloturant le reader du jeu
	 */
	public void closeReader() {
		keyboard.close();
	}

}