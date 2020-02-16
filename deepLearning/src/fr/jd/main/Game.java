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
	private double maxBet;
	private double blinde;
	private double pot;
	private boolean onPlay;
	private boolean debugMode;

	public Game(List<Player> players) {
		super();
		this.players = players;
		this.tourDeTable = 0;
		this.nbTour = 0;
		this.manche = 0;
		this.positionPetiteBlinde = 0;
		this.positionGrosseBlinde = 0;
		this.maxBet = 0;
		this.blinde = 10;
		this.pot = 0;
		this.onPlay = true;
		this.debugMode = false;
		this.dealer = new Dealer(new ArrayList<Cards>(EnumSet.allOf(Cards.class)), new ArrayList<Cards>());
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers() {
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Entrez le nombre de joueurs: ");
		int nbPlayers = keyboard.nextInt();
		List<Player> players = new ArrayList<>();
		for (Player player : players) {
			int i = 1;
			System.out.println("Entrez le nom du joueur N°" + i + ":");
			String playerName = keyboard.toString();
			players.add(new Player(playerName));
			i++;
		}
		keyboard.close();
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
		playersInGame.get(this.manche % (getPlayersInGame().size() + 1)).PayGrosseBlinde(blinde);
		playersInGame.get((this.manche + 1) % (getPlayersInGame().size() + 1)).PayPetiteBlinde(blinde);
	}

	public double getPot() {
		List<Player> playersInGame = new ArrayList<>();
		for (Player player : this.players)
			if (player.isInGame())
				playersInGame.add(player);
		double pot = 0;
		for (Player player : playersInGame) {
			pot += player.getMoneyBet();
		}
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
		Scanner keyboard = new Scanner(System.in);
		double raiseMoney = keyboard.nextDouble();
		double moneyToTransfer = this.maxBet - player.getMoneyBet() + raiseMoney;
		player.raise(moneyToTransfer);
		this.maxBet += raiseMoney;
		keyboard.close();
	}

	public void distributeCards() {
		dealer.shuffle();
		if (this.tourDeTable == 0) {
			for (int j = 0; j < 2; j++) {
				for (int i = 0; i < players.size(); i++) {
					dealer.distribute(players.get(i));
				}
			}
		} else
			dealer.putOnTable(this.tourDeTable);
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
				System.out.println("et remporte : " + this.pot);
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
		this.onPlay = false;
		System.out.println("Le gagnant est : " + getPlayersInGame().get(0).toString());
		System.out.println("");
		System.out.println("Voici le récapitulatif de la partie : ");
		this.players.forEach(player -> System.out.println(player.toString()));
		System.out.println("Jeu terminé.");
	}

	public void endRound() {
		for (Player player : this.players) {
			if (player.isInGame()) {
				player.setInPlay(true);
			}
			player.setScore(0);
			player.setMoneyBet(0);
			player.setPetiteBlinde(false);
			player.setGrosseBlinde(false);
		}
		nbTour += this.tourDeTable;
		this.tourDeTable = 0;
		this.manche++;
		this.pot = 0;
		dealer.recoverCards();
	}

	public void scenario1(Player player) {
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Tapez 3 : Relancer");
		System.out.println("Tapez 5 : Check");
		System.out.println();
		int playerMove = keyboard.nextInt();
		keyboard.close();
		switch (playerMove) {
		case 1:
			player.allIn();
			break;
		case 2:
			player.fold();
			break;
		case 3:
			boolean isCorrect = false;
			while (!isCorrect) {
				Scanner answer = new Scanner(System.in);
				System.out.println("De combien souhaitez-vous relancer?");
				System.out.println("Attention à ne pas mettre plus que ce que vous avez!");
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
			System.out.println("Vous avez tapé : " + playerMove + ".");
			System.out.println("La réponse n'est pas prise en charge par le système.");
			System.out.println();
			System.out.println("Réponse par défaut : Check");
			player.check();
			break;
		}

	}

	public void scenario2(Player player) {
		Scanner keyboard = new Scanner(System.in);
		System.out.println();
		System.out.println("Vous n'avez pas assez pour suivre.");
		int playerMove = keyboard.nextInt();
		keyboard.close();
		switch (playerMove) {
		case 1:
			player.allIn();
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

	public void scenario3(Player player) {
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Tapez 3 : Relancer");
		System.out.println("Tapez 4 : Suivre (" + (this.maxBet - player.getMoneyBet()) + ")");
		System.out.println();
		int playerMove = keyboard.nextInt();
		keyboard.close();
		switch (playerMove) {
		case 1:
			player.allIn();
			break;
		case 2:
			player.fold();
			break;
		case 3:
			boolean isCorrect = false;
			while (!isCorrect) {
				Scanner answer = new Scanner(System.in);
				System.out.println("De combien souhaitez-vous relancer?");
				System.out.println("Attention à ne pas mettre plus que ce que vous avez!");
				int raiseMoney = answer.nextInt();
				if (raiseMoney <= (player.getTotalMoney() + (this.maxBet - player.getMoneyBet()))) {
					player.raise(raiseMoney);
					isCorrect = true;
				}

				answer.close();
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
		if (tourDeTable != other.tourDeTable)
			return false;
		return true;
	}

}
