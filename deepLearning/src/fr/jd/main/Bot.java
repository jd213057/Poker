package fr.jd.main;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jonathan 
 * Classe Bot implémentant l'IA du jeu
 */
public class Bot extends Player {

	/**
	 * @author Jonathan Classe 
	 * DIFFICULTY implémentant le niveau de difficulté du Bot
	 */
	enum DIFFICULTY {
	EASY, MEDIUM, HARDCORE
	}

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((difficulty == null) ? 0 : difficulty.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bot other = (Bot) obj;
		if (difficulty != other.difficulty)
			return false;
		return true;
	}

}
