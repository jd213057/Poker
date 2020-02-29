package fr.jd.main;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jonathan
 * Interface Playable implémentant toutes les méthodes inhérantes aux joeurs/bots d'une partie de Poker.
 */
public interface Playable {
	/**
	 * Attribut de Playable : playerName, nom du joueur
	 */
	String playerName = "playerName";
	/**
	 * Attribut de Playable : hand, main du joueur
	 */
	List<Cards> hand = new ArrayList<Cards>();
	/**
	 * Attribut de Playable : score
	 */
	int score = 0;
	/**
	 * Attribut de Playable : totalMoney
	 */
	double totalMoney = 1000;
	/**
	 * Attribut de Playable : moneyBet
	 */
	double moneyBet = 0;
	/**
	 * Attribut de Playable : inGame
	 */
	boolean inGame = true;
	/**
	 * Attribut de Playable : inPlay
	 */
	boolean inPlay = true;

	/**
	 * Attribut de Playable : isAllIn
	 */
	boolean isAllIn = false;

	public String getPlayerName();

	public void setPlayerName(String playerName);

	public double getTotalMoney();

	public void setTotalMoney(double totalMoney);

	public double getMoneyBet();

	public void setMoneyBet(double moneyBet);

	public List<Cards> getHand();

	public void setHand(List<Cards> hand);

	public int getScore();

	public void setScore(int score);

	public boolean isInGame();

	public void setInGame(boolean inGame);

	public boolean isInPlay();

	public void setInPlay(boolean inPlay);

	public void PayPetiteBlinde(double blinde);

	public void PayGrosseBlinde(double blinde);

	public boolean isAllIn();

	public void setAllIn(boolean isAllIn);

	public void check();

	public void fold();

	public void call(double moneyToTransfer);

	public void raise(double moneyToTransfer);

	public void allIn();

	public void winMoney(double pot);

	public String toStringOnPlay();

	public String toStringHand();
}
