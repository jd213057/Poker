package fr.jd.main;

import java.util.ArrayList;
import java.util.List;

public class Bot {
	private String botName;
	private List<Cards> hand = new ArrayList<Cards>();
	private int score;
	private double totalMoney;
	private double moneyBet;
	private boolean inGame;
	private boolean inPlay;
	private boolean petiteBlinde;
	private boolean grosseBlinde;

	public String getBotName() {
		return botName;
	}

	public void setBotName(String botName) {
		this.botName = botName;
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

	public boolean isGrosseBlinde() {
		return grosseBlinde;
	}

	public void setGrosseBlinde(boolean grosseBlinde) {
		this.grosseBlinde = grosseBlinde;
	}

	public Bot(String botName) {
		super();
		this.botName = botName;
		this.totalMoney = 1000;
		this.score = 0;
		this.inGame = true;
		this.inPlay = true;
	}
	
	public String toStringOnPlay() {
		return "Bot [playerName=" + botName + ", totalMoney=" + totalMoney + ", moneyBet=" + moneyBet
				+ ", inPlay=" + inPlay + "]";
	}

	public String toStringHand() {
		return "Bot [playerName=" + botName + ", hand=" + hand + ", moneyBet=" + moneyBet + "€]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((botName == null) ? 0 : botName.hashCode());
		result = prime * result + (grosseBlinde ? 1231 : 1237);
		result = prime * result + ((hand == null) ? 0 : hand.hashCode());
		result = prime * result + (inGame ? 1231 : 1237);
		result = prime * result + (inPlay ? 1231 : 1237);
		long temp;
		temp = Double.doubleToLongBits(moneyBet);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + (petiteBlinde ? 1231 : 1237);
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
		Bot other = (Bot) obj;
		if (botName == null) {
			if (other.botName != null)
				return false;
		} else if (!botName.equals(other.botName))
			return false;
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
		if (score != other.score)
			return false;
		if (Double.doubleToLongBits(totalMoney) != Double.doubleToLongBits(other.totalMoney))
			return false;
		return true;
	}

}
