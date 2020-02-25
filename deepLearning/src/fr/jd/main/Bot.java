package fr.jd.main;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jonathan Classe Bot implémentant l'IA du jeu
 */
public class Bot extends Player {

	/**
	 * @author Jonathan Classe DIFFICULTY implémenatnt le niveau de difficulté du
	 *         Bot
	 */
	private enum DIFFICULTY {
		EASY, MEDIUM, HARDCORE
	}

	/**
	 * Attribut de Bot : playerName, nom du Bot
	 */
	private String playerName;
	/**
	 * Attribut de Bot : hand, main du Bot
	 */
	private List<Cards> hand = new ArrayList<Cards>();
	/**
	 * Attribut de Bot : score, score du Bot
	 */
	private int score;
	/**
	 * Attribut de Bot : totalMoney, argent total du bot
	 */
	private double totalMoney;
	/**
	 * Attribut de Bot : moneyBet,n argent parié sur un tour
	 */
	private double moneyBet;
	/**
	 * Attribut de Bot : inGame
	 */
	private boolean inGame;
	/**
	 * Attribut de Bot : inPlay
	 */
	private boolean inPlay;
	/**
	 * Attribut de Bot : petiteBlinde
	 */
	private boolean petiteBlinde;
	/**
	 * Attribut de Bot : grosseBlinde
	 */
	private boolean grosseBlinde;
	/**
	 * Attribut de Bot : isAllIn
	 */
	private boolean isAllIn;
	/**
	 * Attribut de Bot : difficulty de type DIFFICULTY, difficulté du Bot
	 */
	private DIFFICULTY difficulty;

	/**
	 * Constructeur de Bot
	 * 
	 * @param botName
	 */
	public Bot(String botName) {
		super(botName);
		this.difficulty = DIFFICULTY.EASY;
	}

	/**
	 * Getter de palyerName
	 */
	public String getPlayerName() {
		return playerName;
	}

	/**
	 * Setter de playerName
	 */
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	/**
	 * Getter de hand
	 */
	public List<Cards> getHand() {
		return hand;
	}

	/**
	 * Setter de hand
	 */
	public void setHand(List<Cards> hand) {
		this.hand = hand;
	}

	/**
	 * Getter de score
	 */
	public int getScore() {
		return score;
	}

	/**
	 * Setter de score
	 */
	public void setScore(int score) {
		this.score = score;
	}

	/**
	 * Getter de totalMoney
	 */
	public double getTotalMoney() {
		return totalMoney;
	}

	/**
	 * Setter de totalMoney
	 */
	public void setTotalMoney(double totalMoney) {
		this.totalMoney = totalMoney;
	}

	/**
	 * Getter de moneyBet
	 */
	public double getMoneyBet() {
		return moneyBet;
	}

	/**
	 * Setter de moneybet
	 */
	public void setMoneyBet(double moneyBet) {
		this.moneyBet = moneyBet;
	}

	/**
	 * Getter de inGame
	 */
	public boolean isInGame() {
		return inGame;
	}

	/**
	 * Setter de inGame
	 */
	public void setInGame(boolean inGame) {
		this.inGame = inGame;
	}

	/**
	 * Getter de inPlay
	 */
	public boolean isInPlay() {
		return inPlay;
	}

	/**
	 * Setter de inPlay
	 */
	public void setInPlay(boolean inPlay) {
		this.inPlay = inPlay;
	}

	/**
	 * Getter de petiteBlinde
	 */
	public boolean isPetiteBlinde() {
		return petiteBlinde;
	}

	/**
	 * Setter de petiteBlinde
	 */
	public void setPetiteBlinde(boolean petiteBlinde) {
		this.petiteBlinde = petiteBlinde;
	}

	/**
	 * Getter de grosseBlinde
	 */
	public boolean isGrosseBlinde() {
		return grosseBlinde;
	}

	/**
	 * Setter de grosseBlinde
	 */
	public void setGrosseBlinde(boolean grosseBlinde) {
		this.grosseBlinde = grosseBlinde;
	}

	/**
	 * Getter de isAllIn
	 */
	public boolean isAllIn() {
		return isAllIn;
	}

	/**
	 * Setter de isAllIn
	 */
	public void setAllIn(boolean isAllIn) {
		this.isAllIn = isAllIn;
	}

	/**
	 * Getter de difficulty
	 * 
	 * @return difficulty
	 */
	public DIFFICULTY getDifficulty() {
		return difficulty;
	}

	/**
	 * Setter de difficulty
	 * 
	 * @param difficulty
	 */
	public void setDifficulty(DIFFICULTY difficulty) {
		this.difficulty = difficulty;
	}

	/**
	 * Méthode Hashcode de Bot
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (grosseBlinde ? 1231 : 1237);
		result = prime * result + ((hand == null) ? 0 : hand.hashCode());
		result = prime * result + (inGame ? 1231 : 1237);
		result = prime * result + (inPlay ? 1231 : 1237);
		result = prime * result + (isAllIn ? 1231 : 1237);
		long temp;
		temp = Double.doubleToLongBits(moneyBet);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + (petiteBlinde ? 1231 : 1237);
		result = prime * result + ((playerName == null) ? 0 : playerName.hashCode());
		result = prime * result + score;
		temp = Double.doubleToLongBits(totalMoney);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	/**
	 * Méthode Equals de Bot
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bot other = (Bot) obj;
		if (grosseBlinde != other.grosseBlinde)
			return false;
		if (hand == null) {
			if (other.hand != null)
				return false;
		} else if (!hand.equals(other.hand))
			return false;
		if (inGame != other.inGame)
			return false;
		if (inPlay != other.inPlay)
			return false;
		if (isAllIn != other.isAllIn)
			return false;
		if (Double.doubleToLongBits(moneyBet) != Double.doubleToLongBits(other.moneyBet))
			return false;
		if (petiteBlinde != other.petiteBlinde)
			return false;
		if (playerName == null) {
			if (other.playerName != null)
				return false;
		} else if (!playerName.equals(other.playerName))
			return false;
		if (score != other.score)
			return false;
		if (Double.doubleToLongBits(totalMoney) != Double.doubleToLongBits(other.totalMoney))
			return false;
		return true;
	}

}
