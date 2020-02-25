package fr.jd.main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * @author Jonathan
 * Classe implémentant le Dealer du jeu
 */
public class Dealer {

	/**
	 * Attribut de Dealer : cardsPackage, paquet de cartes
	 */
	private List<Cards> cardsPackage = new ArrayList<Cards>(EnumSet.allOf(Cards.class));
	/**
	 * Attribut de Dealer : carpet, cartes sur la table de mise
	 */
	private List<Cards> carpet = new ArrayList<Cards>();

	/**
	 * Constructeur de Dealer
	 * @param cardsPackage
	 * @param carpet
	 */
	public Dealer(List<Cards> cardsPackage, List<Cards> carpet) {
		super();
		this.cardsPackage = cardsPackage;
		this.carpet = carpet;
	}

	/**
	 * Getter de cardsPackage
	 * @return cardsPackage
	 */
	public List<Cards> getCardsPackage() {
		return cardsPackage;
	}

	/**
	 * Setter de cardsPackage
	 * @param cardsPackage
	 */
	public void setCardsPackage(List<Cards> cardsPackage) {
		this.cardsPackage = cardsPackage;
	}

	/**
	 * Getter de carpet
	 * @return carpet
	 */
	public List<Cards> getCarpet() {
		return carpet;
	}

	/**
	 * Setter de carpet
	 * @param carpet
	 */
	public void setCarpet(List<Cards> carpet) {
		this.carpet = carpet;
	}

	/**
	 * @return information sur le dealer de type String
	 */
	@Override
	public String toString() {
		return "Package [cardsPackage=" + cardsPackage + "]";
	}

	/**
	 * Méthode implémentant le mélange du paquet de cartes
	 */
	public void shuffle() {
		Collections.shuffle(cardsPackage);
	}

	/**
	 * Méthode implémentant la vérification du mélange du paquet de cartes
	 */
	public void checkShuffle() {
		cardsPackage.forEach(System.out::println);
	}

	/**
	 * Méthode implémentant la distribution des cartes aux joueurs
	 * @param player
	 */
	public void distribute(Player player) {
		player.getHand().add(cardsPackage.get(cardsPackage.size() - 1));
		cardsPackage.remove(cardsPackage.get(cardsPackage.size() - 1));
	}

	/**
	 * Méthode implémentant la pose des cartes sur la table de mise
	 * @param tour
	 */
	public void putOnTable(int tour) {
		switch (tour) {
		case 0:
			break;
		case 1:
			cardsPackage.remove(cardsPackage.get(cardsPackage.size() - 1));
			for (int i = 0; i < 3; i++) {
				carpet.add(cardsPackage.get(cardsPackage.size() - 1));
				cardsPackage.remove(cardsPackage.get(cardsPackage.size() - 1));
			}
			break;
		case 2:
		case 3:
			this.cardsPackage.remove(this.cardsPackage.get(this.cardsPackage.size() - 1));
			this.carpet.add(this.cardsPackage.get(this.cardsPackage.size() - 1));
			this.cardsPackage.remove(this.cardsPackage.get(this.cardsPackage.size() - 1));
			break;
		}
	}

	/**
	 * Méthode implémentant la récupération des cartes en fin de manche
	 */
	public void recoverCards() {
		carpet.clear();
		cardsPackage.clear();
		cardsPackage.addAll(new ArrayList<Cards>(EnumSet.allOf(Cards.class)));
	}

	/**
	 * Méthode hashcode de Dealer
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cardsPackage == null) ? 0 : cardsPackage.hashCode());
		result = prime * result + ((carpet == null) ? 0 : carpet.hashCode());
		return result;
	}

	/**
	 * Méthode Equals de Dealer
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Dealer other = (Dealer) obj;
		if (cardsPackage == null) {
			if (other.cardsPackage != null)
				return false;
		} else if (!cardsPackage.equals(other.cardsPackage))
			return false;
		if (carpet == null) {
			if (other.carpet != null)
				return false;
		} else if (!carpet.equals(other.carpet))
			return false;
		return true;
	}

}