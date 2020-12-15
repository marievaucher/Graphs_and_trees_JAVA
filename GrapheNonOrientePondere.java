package packnp.tests.graphe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class GrapheNonOrientePondere {

	private HashMap<String, HashMap<String, Double>> adjacences;

	public GrapheNonOrientePondere() {
		this.adjacences = new HashMap<String, HashMap<String, Double>>();
	}

	/**
	 * Leve une IllegalArgumentException si la matrice fournie n'est pas carree,
	 * ou si elle n'est pas symetrique.
	 * Sinon (si poids est carree et symetrique)
	 * initialise le graphe avec les poids indiques dans la matrice poids
	 * @param poids
	 */
	public GrapheNonOrientePondere(Double[][] poids) {
		this();
		if (poids.length<1 || poids.length!=poids[0].length) {
			throw new IllegalArgumentException("La matrice passee en parametre du constructeur de GrapheNonOrientePonderee doit etre carree");
		}
		for (int i=0; i<poids.length; i++) {
			this.adjacences.put("s"+i, new HashMap<String, Double>());
		}
		for (int i=0; i<poids.length; i++) {
			for (int j=i; j<poids[i].length; j++) {
				if (!poids[i][j].equals(poids[j][i])) {
					throw new IllegalArgumentException("la matrice passee en parametre du constructeur de GrapheNonOrientePonderee doit etre symetrique : ["+i+"]["+j+"]="+poids[i][j]+" vs ["+j+"]["+i+"]="+poids[j][i]);	
				} else if (poids[i][j]>=0) {
					this.add(new AretePonderee(poids[i][j], "s"+i, "s"+j ));
				}
			}
		}
	}
	
	public Set<String> getSommets() {
		return this.adjacences.keySet();
	}

	public Set<String> getVoisins(String sommet) {
		if (this.adjacences.containsKey(sommet)) {
			return this.adjacences.get(sommet).keySet();
		} else {
			return new HashSet<String>();
		}
	}

	public Double getPoids(String sommet1, String sommet2) {
		if (!this.adjacences.containsKey(sommet1) || !this.adjacences.get(sommet1).containsKey(sommet2)) {
			return Double.POSITIVE_INFINITY;
		} else {
			return this.adjacences.get(sommet1).get(sommet2);
		}
	}

	public void add(AretePonderee arete) {
		String sommet1 = arete.getSommets().get(0);
		String sommet2 = arete.getSommets().get(1);
		if (!this.adjacences.containsKey(sommet1)) {
			this.adjacences.put(sommet1, new HashMap<String, Double>());
		}
		if (!this.adjacences.containsKey(sommet2)) {
			this.adjacences.put(sommet2, new HashMap<String, Double>());
		}
		this.adjacences.get(sommet1).put(sommet2, arete.getPoids());
		this.adjacences.get(sommet2).put(sommet1, arete.getPoids());
	}
	
	/**
	 * @return Retourne true si le graphe est connexe (et false s'il n'est pas connexe).
	 */
	public boolean estConnexe() {
	        int nbSommets = this.getSommets().size();
	        List<String> atteints = new ArrayList<String>();
	        List<String> propages = new ArrayList<String>();
	        atteints.add(  this.getSommets().iterator().next() );
	        while (atteints.size()+propages.size() < nbSommets && atteints.size()>0) {
	            String s1 = atteints.remove(0);
	            propages.add(s1);
	            for (String s2 : this.getVoisins(s1)) {
	                if (!propages.contains(s2) && !atteints.contains(s2)) {
	                    atteints.add(s2);
	                }
	            }
	        }
	        return atteints.size()+propages.size() == nbSommets;
	    }

		


	/**
	 * L'algorithme de Prim base sur un tas binaire
	 * @return La liste des aretes d'un arbre recouvrant de poids minimal obtenu via l'algorithme de Prim
	 */
	public List<AretePonderee> prim() {
		if (!this.estConnexe()) {
			throw new IllegalArgumentException("Le graphe sur lequel est appele la methode prim n'est pas connexe");
		}
		int nbSommets = this.getSommets().size();
		List<String> visited = new ArrayList<String>();
		List<AretePonderee> sol = new ArrayList<AretePonderee>();
		TasBinaire<AretePonderee> tas = new TasBinaire<AretePonderee>();
		String first = this.getSommets().iterator().next();
		visited.add(first);
		
		return sol;
	}


	public String toString() {
		String res="";
		List<String> sommets = new LinkedList<String>(this.getSommets());
		Collections.sort(sommets);
		for (String s1 : sommets) {
			res=res+s1+" : ";
			List<String> voisins = new LinkedList<String>(this.getVoisins(s1));
			Collections.sort(voisins);
			for (String s2 : voisins) {
				res=res+"("+s2+","+this.getPoids(s1, s2)+") ";
			}
			res=res+"\n";
		}
		return res;
	}

	private String sur(String s, int largeur) {
		while (s.length()<largeur) {
			s=" "+s;
		}
		return s.substring(0, largeur);
	}
	
	public String toStringMatrice() {
		String res="      ";
		List<String> etiquettes = new ArrayList<String>();
		etiquettes.addAll(this.adjacences.keySet());
		Collections.sort(etiquettes);
		for (String s1 : etiquettes) {
			res+=sur(s1,5)+" ";
		}
		res+="\n";
		for (String s1 : etiquettes) {
			res=res+sur(s1,5)+" ";
			for (String s2 : etiquettes) {
				res=res+sur(this.getPoids(s1, s2)==Double.POSITIVE_INFINITY? "---":this.getPoids(s1, s2)+"",5)+" ";
			}
			res=res+"\n";
		}
		return res;
	}
}
