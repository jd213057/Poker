package fr.jd.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Player {
	private String playerName;
	private List<Cards> hand = new ArrayList<Cards>();
	private int score;
	private double totalMoney;
	private double moneyBet;
	private boolean inGame;
	private boolean inPlay;
	private boolean petiteBlinde;
	private boolean grosseBlinde;

	public Player(String playerName) {
		super();
		this.playerName = playerName;
		this.totalMoney = 1000;
		this.score = 0;
		this.inGame = true;
		this.inPlay = true;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public double getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(double totalMoney) {
		this.totalMoney = totalMoney;
	}

	public double getMoneyBet() {
		return moneyBet;
	}

	public void setMoneyBet(double moneyBet) {
		this.moneyBet = moneyBet;
	}

	public List<Cards> getHand() {
		return hand;
	}

	public void setHand(List<Cards> hand) {
		this.hand = hand;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public boolean isInGame() {
		return inGame;
	}

	public void setInGame(boolean inGame) {
		this.inGame = inGame;
		this.inPlay = false;
	}

	public boolean isInPlay() {
		return inPlay;
	}

	public void setInPlay(boolean inPlay) {
		this.inPlay = inPlay;
	}

	public boolean isPetiteBlinde() {
		return petiteBlinde;
	}

	public void setPetiteBlinde(boolean petiteBlinde) {
		this.petiteBlinde = petiteBlinde;
	}

	public void setPetiteBlinde(boolean petiteBlinde, double blinde) {
		this.petiteBlinde = petiteBlinde;
		this.totalMoney -= (blinde) / 2;
		this.moneyBet += (blinde) / 2;
	}

	public boolean isGrosseBlinde() {
		return grosseBlinde;
	}

	public void setGrosseBlinde(boolean grosseBlinde) {
		this.grosseBlinde = grosseBlinde;
	}

	public void setGrosseBlinde(boolean grosseBlinde, double blinde) {
		this.grosseBlinde = grosseBlinde;
		this.totalMoney -= (blinde);
		this.moneyBet += (blinde);
	}

	public void PayPetiteBlinde(double blinde) {
		this.grosseBlinde = true;
		this.totalMoney -= (blinde / 2);
		this.moneyBet += (blinde / 2);
	}

	public void PayGrosseBlinde(double blinde) {
		this.grosseBlinde = true;
		this.totalMoney -= (blinde);
		this.moneyBet += (blinde);
	}

	public void check() {
	}

	public void fold() {
		this.inPlay = false;
	}

	public void call(double moneyToTransfer) {
		this.totalMoney -= moneyToTransfer;
		this.moneyBet += moneyToTransfer;
	}

	public void raise(double moneyToTransfer) {
		this.totalMoney -= moneyToTransfer;
		this.moneyBet += moneyToTransfer;
	}

	public void allIn() {
		this.moneyBet += this.totalMoney;
		this.totalMoney = 0;
	}

	public void winMoney(double pot) {
		this.totalMoney += pot;
	}

	public String toStringOnPlay() {
		return "Player [playerName=" + playerName + ", totalMoney=" + totalMoney + ", moneyBet=" + moneyBet
				+ ", inPlay=" + inPlay + "]";
	}

	public String toStringHand() {
		return "Player [playerName=" + playerName + ", hand=" + hand + ", moneyBet=" + moneyBet + "€]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (grosseBlinde ? 1231 : 1237);
		result = prime * result + ((hand == null) ? 0 : hand.hashCode());
		result = prime * result + (inGame ? 1231 : 1237);
		result = prime * result + (inPlay ? 1231 : 1237);
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

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Player other = (Player) obj;
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
