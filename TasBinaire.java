package packnp.tests.graphe;

import java.util.ArrayList;
import java.util.List;

public class TasBinaire<Element extends Comparable<Element>> {

	private List<Element> elements;

	public TasBinaire() {
		this.elements = new ArrayList<Element>();
	}

	public TasBinaire(Element[] t) {
		this();
		for (Element e : t) {
			this.elements.add(e);
		}
		if (!this.test()) {
			throw new IllegalArgumentException("Appel du constructeur de TasBinaire avec un tableau qui ne respecte pas la propriete de tas binaire");
		}
	}

	public boolean isEmpty() {
		return elements.isEmpty();
	}

	public void add(Element e) {
		
		this.elements.add(e);
		int i=elements.size();
		while (i>0 && (e.compareTo(this.elements.get((i-1)/2))>0)) {
			elements.set((i-1)/2,e);
		}
	
	}

	public int indexPlusPetitFils(int pere) {
		
	    
		return 0;
	}

	public Element remove() {
		if (isEmpty()) {
			return null;
		} else {
			elements.set(0,elements.get(elements.size()));
			elements.remove();
			
			
			}
	
			
			
			return null;
		
	}

	public String toString() {
		String res="";
		int i = 0;
		int maxNiveau = 1;
		int somme=maxNiveau;
		while (i<this.elements.size()) {
			res+=this.elements.get(i);
			i++;
			if (i<somme) {
				res+=",";
			} else {
				res+="\n";
				maxNiveau=maxNiveau*2;
				somme+=maxNiveau;
			}
		}
		return res;
	}

	public String toStringTab() {
		String res="{"+(this.elements.size()>0 ? this.elements.get(0) :"");
		for (int i=1; i<this.elements.size(); i++) {
			res=res+","+this.elements.get(i);
		}
		return res+"}";
	}
	@SuppressWarnings("rawtypes")
	public boolean equals(Object o) {
		return (o!=null)
				&& (o instanceof TasBinaire)
				&& this.elements.equals(((TasBinaire)o).elements);
	}

	public boolean test() {
		return this.isEmpty() || testRec(0);
	}

	private boolean testRec(int root) {
		int indexDernier = elements.size() - 1;
		return 2*root+1>indexDernier
				|| ( 2*root+2>indexDernier && this.elements.get(root).compareTo(this.elements.get(2*root+1))<=0 && testRec(2*root+1))
				|| (   this.elements.get(root).compareTo(this.elements.get(2*root+1))<=0 && testRec( 2*root+1) 
				&& this.elements.get(root).compareTo(this.elements.get(2*root+2))<=0 &&testRec(2*root+2));	
	}
}

