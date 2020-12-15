package packnp.tests.graphe;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class AretePonderee implements Comparable<AretePonderee>{
	private List<String> sommets;
	private Double poids;
	
	public AretePonderee(Double poids, String... sommets) {
		this.sommets=Arrays.asList(sommets);
		Collections.sort(this.sommets);
		this.poids = poids;
	}

	public Double getPoids() {
		return this.poids;
	}
	
	public List<String> getSommets() {
		return this.sommets;
	}
	
	public boolean contains(String sommet) {
		return this.sommets.contains(sommet) ;
	}
	/**
	 * Retourne true si o est une AretePonderee portant sur le meme
	 * ensemble de sommets que this (meme si o et this ont des poids differents)
	 * (retourne false dans tous les autres cas : si o est null ou o n'est pas
	 *  une AretePonderee ou o est une AretePonderee qui ne porte pas sur le meme
	 *  ensemble de sommets que this)
	 */
	public boolean equals(Object o) {
		return o!=null 
				&& (o instanceof AretePonderee) 
				&& this.sommets.equals(((AretePonderee)o).sommets);
	}

	public int compareTo(AretePonderee o) {
		return this.getPoids()<o.getPoids()?-1:this.getPoids()==o.getPoids()?0:1;
	}
	
	public String toString() {
		String res="<"+this.sommets.get(0);
		for (int i=1; i<this.sommets.size(); i++) {
			res=res+","+this.sommets.get(i);
		}
		return res+":"+this.getPoids()+">";
	}
}
