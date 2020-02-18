package fr.jd.main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Scanner;

public class Game {
	private List<Player> players = new ArrayList<>();
	private Dealer dealer;
	private int tourDeTable;
	private int manche;
	private int positionPetiteBlinde;
	private int positionGrosseBlinde;
	private int nbTour;
	private int tourBlinde;
	private double maxBet;
	private double blinde;
	private double pot;
	private boolean onPlay;
	private boolean debugMode;

	Scanner keyboard = new Scanner(System.in);

	public Game(List<Player> players) {
		super();
		this.players = players;
		this.tourDeTable = 0;
		this.nbTour = 0;
		this.manche = 1;
		this.positionPetiteBlinde = 0;
		this.positionGrosseBlinde = 0;
		this.maxBet = 0;
		this.blinde = 0;
		this.pot = 0;
		this.onPlay = true;
		this.debugMode = false;
		this.dealer = new Dealer(new ArrayList<Cards>(EnumSet.allOf(Cards.class)), new ArrayList<Cards>());
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers() {
		System.out.println("Entrez le nombre de joueurs: ");
		int nbPlayers = keyboard.nextInt();
		keyboard.nextLine();
		List<Player> players = new ArrayList<>();
		for (int i = 1; i <= nbPlayers; i++) {
			System.out.println("Entrez le nom du joueur N�" + i + ":");
			String playerName = keyboard.nextLine();
			players.add(new Player(playerName));
		}
		this.setPlayers(players);
	}

	public int getTour() {
		return tourDeTable;
	}

	public void setTour(int tourDeTable) {
		this.tourDeTable = tourDeTable;
	}

	public Dealer getDealer() {
		return dealer;
	}

	public void setDealer(Dealer dealer) {
		this.dealer = dealer;
	}

	public int getTourDeTable() {
		return tourDeTable;
	}

	public void setTourDeTable(int tourDeTable) {
		this.tourDeTable = tourDeTable;
	}

	public int getTourBlinde() {
		return tourBlinde;
	}

	public void setTourBlinde(int answer2) {
		this.tourBlinde = answer2;
	}

	public int getManche() {
		return manche;
	}

	public void setManche(int manche) {
		this.manche = manche;
	}

	public int getNbTour() {
		return nbTour;
	}

	public void setNbTour(int nbTour) {
		this.nbTour = nbTour;
	}

	public double getMaxBet() {
		return maxBet;
	}

	public int getPositionPetiteBlinde() {
		return positionPetiteBlinde;
	}

	public void setPositionPetiteBlinde() {
		this.positionPetiteBlinde = (this.manche + 1) % (this.getPlayersInGame().size() + 1);
	}

	public int getPositionGrosseBlinde() {
		return this.positionGrosseBlinde;
	}

	public void setPositionGrosseBlinde() {
		this.positionGrosseBlinde = this.manche % (this.getPlayersInGame().size() + 1);
	}

	public double getBlinde() {
		return blinde;
	}

	public void setBlinde(double blinde) {
		List<Player> playersInGame = new ArrayList<>();
		for (Player player : this.players)
			if (player.isInGame())
				playersInGame.add(player);
		playersInGame.get(this.manche % getPlayersInGame().size()).PayGrosseBlinde(blinde);
		playersInGame.get((this.manche - 1) % getPlayersInGame().size()).PayPetiteBlinde(blinde);
		this.maxBet += blinde;
	}

	public double getPot() {
//		List<Player> playersInGame = new ArrayList<>();
//		for (Player player : this.players)
//			if (player.isInGame())
//				playersInGame.add(player);
//		double pot = 0;
//		for (Player player : playersInGame) {
//			pot += player.getMoneyBet();
//		}
		return pot;
	}

	public void setPot(double pot) {
		this.pot = pot;
	}

	public void setMaxBet(double maxBet) {
		this.maxBet = maxBet;
	}

	public boolean isOnPlay() {
		return onPlay;
	}

	public void setOnPlay(boolean onPlay) {
		this.onPlay = onPlay;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	public boolean isDebugMode() {
		return debugMode;
	}

	public void setDebugMode(boolean debugMode) {
		this.debugMode = debugMode;
	}

	public void winMoney(Player player) {
		player.setTotalMoney(player.getTotalMoney() + this.pot);
	}

	public void initialize() {
		System.out.println("Appuyez sur Entr�e pour initialiser la partie.");
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
			System.out.println("R�ponse non pris en charge.");
			System.out.println("Par mesure de s�curit�, l'application va se lancer sans le mode debug.");
			System.out.println();
		}
		System.out.println("Saisir le montant initial pour chaque joueur :");
		double answer1 = keyboard.nextDouble();
		System.out.println("Saisir le montant de la grosseBlinde :");
		double answer2 = keyboard.nextDouble();
		System.out.println("Augmonter la blinde tous les combien de tours ?");
		int answer3 = keyboard.nextInt();
		for (Player player : this.getPlayers())
			player.setTotalMoney(answer1);
		this.setBlinde(answer2);
		this.setTourBlinde(answer3);
	}

//	public void configureDebugMode() {
//String answer1;
//String answer2;
//String answer3;
//this.setDebugMode(true);
//System.out.println("Lancement du DebugMode");
//System.out.println("Souhaitez-vous activer les codes de triches? (y/n)");
//answer1 = keyboard.nextLine();
//System.out.println("Souhaitez-vous sauvegard� les rapports de jeu? (y/n)");
//answer2 = keyboard.nextLine();
//System.out.println("Souhaitez-vous affichez les probabilit�s de jeu en temps r�el? (y/n)");
//answer3 = keyboard.nextLine();
//answer1 = "y" ? this.setCheatMode(true) : this.setCheatMode(false);
//answer2 = "y" ? this.setCheatMode(true) : this.setCheatMode(false);
//answer3 = "y" ? this.setCheatMode(true) : this.setCheatMode(false);
//}

	public void check(Player player) {
		player.check();
	}

	public void fold(Player player) {
		player.fold();
		this.pot += player.getMoneyBet();
		player.setMoneyBet(0);
	}

	public void allIn(Player player) {
		player.allIn();
		this.maxBet += player.getTotalMoney();
	}

	public void call(Player player) {
		double moneyToTransfer = this.maxBet - player.getMoneyBet();
		player.call(moneyToTransfer);
	}

	public void raise(Player player) {
		System.out.println("De combien souhaitez-vous relancer?");
		System.out.println();
		double raiseMoney = keyboard.nextDouble();
		double moneyToTransfer = this.maxBet - player.getMoneyBet() + raiseMoney;
		player.raise(moneyToTransfer);
		this.maxBet += raiseMoney;
	}

	public void shuffleCards() {
		dealer.shuffle();
	}

	public void distributeCards() {

		if (this.tourDeTable == 0) {
			for (int j = 0; j < 2; j++) {
				for (int i = 0; i < players.size(); i++) {
					dealer.distribute(players.get(i));
				}
			}
			System.out.println("Jeu de cartes distribu� aux joueurs");
		} else {
			dealer.putOnTable(this.tourDeTable - 1);
		}
	}

	public void recoverCards() {
		for (int i = 0; i < players.size(); i++)
			players.get(i).getHand().clear();
		dealer.recoverCards();

	}

	public List<Player> getPlayersInGame() {
		List<Player> playersInGame = new ArrayList<>();
		for (Player player : players)
			if (player.isInGame())
				playersInGame.add(player);
		return playersInGame;
	}

	public List<Player> getPlayersInPlay() {
		List<Player> playersInPlay = new ArrayList<>();
		for (Player player : players)
			if (player.isInPlay() && player.isInGame())
				playersInPlay.add(player);
		return playersInPlay;
	}

	public void showAllHands() {
		System.out.println("Situation finale : ");
		List<Player> playersInPlay = this.getPlayersInPlay();
		playersInPlay.forEach(p -> System.out.println(p.toStringHand()));
		System.out.println();
	}

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
				System.out.println("et remporte : " + this.pot + "�");
				System.out.println();
				player.winMoney(this.pot);
				winner = player;
				return winner;
			}
		}
		return winner;
	}

	public void eliminate(Player player) {
		player.setInGame(false);
		System.out.println("Le joueur : " + player.getPlayerName() + "a perdu.");
		System.out.println();
	}

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
		System.out.println("Voici le r�capitulatif de la partie : ");
		this.players.forEach(player -> System.out.println(player.toString()));
		System.out.println("Jeu termin�.");
	}

	public void endRound() {
		for (Player player : this.players) {
			if (player.isInGame()) {
				player.setInPlay(true);
			}
			player.setScore(0);
			player.setMoneyBet(0);
			player.getHand().clear();
		}
		nbTour += this.tourDeTable;
		this.manche++;
		dealer.recoverCards();
		System.out.println("Fin de la manche.");
		keyboard.nextLine();
		System.out.println("Continuer la prochaine manche? Appuyez sur Entr�e puis y/n");
		
		String answer = keyboard.nextLine();
		if (answer == "n" || answer == "N")
			this.setOnPlay(false);
	}

	public void scenario1(Player player) {
		System.out.println("Tapez 3 : Relancer");
		System.out.println("Tapez 5 : Check");
		System.out.println();
		System.out.println("Appuyez sur Entr�e avant de saisir votre choix");
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
		case 3:
			boolean isCorrect = false;
			while (!isCorrect) {
				Scanner answer = new Scanner(System.in);
				System.out.println("De combien souhaitez-vous relancer?");
				System.out.println("Attention � ne pas mettre plus que ce que vous avez!");
				int raiseMoney = answer.nextInt();
				if (raiseMoney <= (player.getTotalMoney() + (this.maxBet - player.getMoneyBet()))) {
					player.raise(raiseMoney);
					isCorrect = true;
				}

				answer.close();
			}
			break;
		case 5:
			player.check();
			break;
		default:
			System.out.println("Vous avez tap� : " + playerMove + ".");
			System.out.println("La r�ponse n'est pas prise en charge par le syst�me.");
			System.out.println();
			System.out.println("R�ponse par d�faut : Check");
			player.check();
			break;
		}

	}

	public void scenario2(Player player) {
		System.out.println();
		System.out.println("Vous n'avez pas assez pour suivre.");
		System.out.println("Appuyez sur Entr�e avant de saisir votre choix");
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
			System.out.println("Vous avez tap� : " + playerMove + ".");
			System.out.println("La r�ponse n'est pas prise en charge par le syst�me.");
			System.out.println();
			System.out.println("R�ponse par d�faut : Se Coucher");
			player.fold();
			break;
		}

	}

	public void scenario3(Player player) {
		System.out.println("Tapez 3 : Relancer");
		System.out.println("Tapez 4 : Suivre (" + (this.maxBet - player.getMoneyBet()) + ")");
		System.out.println();
		System.out.println("Appuyez sur Entr�e avant de saisir votre choix");
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
		case 3:
			boolean isCorrect = false;
			while (!isCorrect) {
				Scanner answer = new Scanner(System.in);
				System.out.println("De combien souhaitez-vous relancer?");
				System.out.println("Attention � ne pas mettre plus que ce que vous avez!");
				int raiseMoney = answer.nextInt();
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
			System.out.println("Vous avez tap� : " + playerMove + ".");
			System.out.println("La r�ponse n'est pas prise en charge par le syst�me.");
			System.out.println();
			System.out.println("R�ponse par d�faut : Se Coucher");
			player.fold();
			break;
		}
	}

	public Player getCombo(Player player) {
		List<Cards> playerCombo = new ArrayList<Cards>();
		List<Integer> playerResults = new ArrayList<>();
		playerCombo.addAll(player.getHand());
		playerCombo.addAll(this.dealer.getCarpet());
		int playerFinalScore;
		int playerStraightFlushScore = Cards.checkStraightFlush(playerCombo);
		int playerFourOfAKindScore = Cards.checkFourOfAKind(playerCombo);
		int playerFullHouseScoreCards = Cards.checkFull(playerCombo);
		int playerFlushScore = Cards.checkFlush(playerCombo);
		int playerStraightScore = Cards.checkStraight(playerCombo);
		int playerThreeOfAKind = Cards.checkThreeOfAKind(playerCombo);
		int playersOnePairScore = Cards.checkOnePair(playerCombo);
		int playersTwoPairsScore = Cards.checkTwoPairs(playerCombo);
		int playerHighRaiseScore = Cards.checkHighRaise(playerCombo);
		playerResults.add(playerStraightFlushScore);
		playerResults.add(playerFourOfAKindScore);
		playerResults.add(playerFullHouseScoreCards);
		playerResults.add(playerFlushScore);
		playerResults.add(playerStraightScore);
		playerResults.add(playerThreeOfAKind);
		playerResults.add(playersOnePairScore);
		playerResults.add(playersTwoPairsScore);
		playerResults.add(playerHighRaiseScore);
		playerFinalScore = Collections.max(playerResults);
		player.setScore(playerFinalScore);
		return player;
	}

	public String playReport() {
		return "Game [players=" + players + ", dealer=" + dealer + ", tourDeTable=" + tourDeTable + ", manche=" + manche
				+ ", positionPetiteBlinde=" + positionPetiteBlinde + ", positionGrosseBlinde=" + positionGrosseBlinde
				+ ", nbTour=" + nbTour + ", maxBet=" + maxBet + ", blinde=" + blinde + ", pot=" + pot + ", onPlay="
				+ onPlay + "]";
	}

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
		result = prime * result + tourBlinde;
		result = prime * result + tourDeTable;
		return result;
	}

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
		if (tourBlinde != other.tourBlinde)
			return false;
		if (tourDeTable != other.tourDeTable)
			return false;
		return true;
	}

	public void closeReader() {
		keyboard.close();
	}

}