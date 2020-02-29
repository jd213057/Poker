package fr.jd.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Jonathan Classe implémentant les joeurs dans la partie.
 */
public class Player {
	private String playerName;
	/**
	 * Attribut de Player : hand, main du joueur
	 */
	private List<Cards> hand = new ArrayList<Cards>();
	/**
	 * Attribut de Player : score
	 */
	private int score;
	/**
	 * Attribut de Player : totalMoney
	 */
	private double totalMoney;
	/**
	 * Attribut de Player : moneyBet
	 */
	private double moneyBet;
	/**
	 * Attribut de Player : inGame
	 */
	private boolean inGame;
	/**
	 * Attribut de Player : inPlay
	 */
	private boolean inPlay;

	/**
	 * Attribut de Player : isAllIn
	 */
	private boolean isAllIn;

	/**
	 * @param playerName Constructeur de Player
	 */
	public Player(String playerName) {
		super();
		this.playerName = playerName;
		this.totalMoney = 1000;
		this.score = 0;
		this.inGame = true;
		this.inPlay = true;
		this.isAllIn = false;
	}

	/**
	 * Getter de playerName
	 * @return playername
	 */
	public String getPlayerName() {
		return playerName;
	}

	/**
	 * Setter de playerName
	 * @param playerName
	 */
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	/**
	 * Getter de totalMoney
	 * @return totalMoney
	 */
	public double getTotalMoney() {
		return totalMoney;
	}

	/**
	 * Setter de totalMoney
	 * @param totalMoney
	 */
	public void setTotalMoney(double totalMoney) {
		this.totalMoney = totalMoney;
		if (this.totalMoney <= 0) this.setInPlay(false);
	}

	/**
	 * Getter de moneyBet
	 * @return moneyBet
	 */
	public double getMoneyBet() {
		return moneyBet;
	}

	/**
	 * Setter de moneyBet
	 * @param moneyBet
	 */
	public void setMoneyBet(double moneyBet) {
		this.moneyBet = moneyBet;
	}

	/**
	 * Getter de hand
	 * @return hand
	 */
	public List<Cards> getHand() {
		return hand;
	}

	/**
	 * Setter de hand
	 * @param hand
	 */
	public void setHand(List<Cards> hand) {
		this.hand = hand;
	}

	/**
	 * Getter de score
	 * @return score
	 */
	public int getScore() {
		return score;
	}

	/**
	 * Setter de score
	 * @param score
	 */
	public void setScore(int score) {
		this.score = score;
	}

	/**
	 * Getter de inGame
	 * @return inGame
	 */
	public boolean isInGame() {
		return inGame;
	}

	/**
	 * Setter de inGame
	 * @param inGame
	 */
	public void setInGame(boolean inGame) {
		this.inGame = inGame;
		this.inPlay = false;
	}

	/**
	 * Getter de inPlay
	 * @return inPlay
	 */
	public boolean isInPlay() {
		return inPlay;
	}

	/**
	 * Setter de inPlay
	 * @param inPlay
	 */
	public void setInPlay(boolean inPlay) {
		this.inPlay = inPlay;
	}

	/**
	 * Méthode implémentant la paye de la petiteBlinde
	 * @param blinde
	 */
	public void PayPetiteBlinde(double blinde) {
		this.totalMoney -= (blinde / 2);
		this.moneyBet += (blinde / 2);
	}

	/**
	 * Méthode implémentant la paye de la grosseBlinde
	 * @param blinde
	 */
	public void PayGrosseBlinde(double blinde) {
		this.totalMoney -= (blinde);
		this.moneyBet += (blinde);
	}

	/**
	 * Getter de isAllIn
	 * @return isAllIn
	 */
	public boolean isAllIn() {
		return isAllIn;
	}

	/**
	 * Setter de isAllIn
	 * @param isAllIn
	 */
	public void setAllIn(boolean isAllIn) {
		this.isAllIn = isAllIn;
	}

	/**
	 * Méthode implémentant le check
	 */
	public void check() {
		// Method for non action from player
	}

	/**
	 * Méthode implémentant l'action de se coucher
	 */
	public void fold() {
		this.inPlay = false;
	}

	/**
	 * Méthode implémentant l'action de suivre
	 * @param moneyToTransfer
	 */
	public void call(double moneyToTransfer) {
		this.totalMoney -= moneyToTransfer;
		this.moneyBet += moneyToTransfer;
	}

	/**
	 * Méthode implémentant l'action de relancer
	 * @param moneyToTransfer
	 */
	public void raise(double moneyToTransfer) {
		this.totalMoney -= moneyToTransfer;
		this.moneyBet += moneyToTransfer;
	}

	/**
	 * Méthode implémentant l'action de faire tapis
	 */
	public void allIn() {
		this.moneyBet += this.totalMoney;
		this.totalMoney = 0;
		this.setInPlay(false);
		this.setAllIn(true);
	}

	/**
	 * Méthode implémentant de remporter le pot
	 * @param pot
	 */
	public void winMoney(double pot) {
		this.totalMoney += pot;
	}

	/**
	 * @return information sur le joueur de type String
	 */
	public String toStringOnPlay() {
		return "Player [playerName=" + playerName + ", totalMoney=" + totalMoney + "€, moneyBet=" + moneyBet
				+ "€, inPlay=" + inPlay + "]";
	}

	/**
	 * @return information sur le joueur de type String
	 */
	public String toStringHand() {
		return "Player [playerName=" + playerName + ", hand=" + hand + ", moneyBet=" + moneyBet + "€]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((hand == null) ? 0 : hand.hashCode());
		result = prime * result + (inGame ? 1231 : 1237);
		result = prime * result + (inPlay ? 1231 : 1237);
		result = prime * result + (isAllIn ? 1231 : 1237);
		long temp;
		temp = Double.doubleToLongBits(moneyBet);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((playerName == null) ? 0 : playerName.hashCode());
		result = prime * result + score;
		temp = Double.doubleToLongBits(totalMoney);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		Player other = (Player) obj;
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