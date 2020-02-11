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
		score = score;
	}

	public boolean isInGame() {
		return inGame;
	}

	public void setInGame(boolean inGame) {
		this.inGame = inGame;
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

	public void scenario1() {
		// TODO Auto-generated method stub

	}

}
