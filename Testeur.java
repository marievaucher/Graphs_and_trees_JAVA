 
 
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import packnp.tests.tools.*;
 
public class Testeur {
	public static final int TIME_OUT = 15000; // en millisecondes
	public static final int LARGEUR_LIGNES = 80;
 
	public static BufferedReader input=new BufferedReader (new InputStreamReader(System.in));
	public static String lireString() {
		System.out.flush();
		try {
			return input.readLine();
		} catch (Exception e) {
			return "";
		}
	}
 
	public static String heure() {
		return (new SimpleDateFormat("HH:mm:ss")).format(new Date()) + "";
	}
	@SuppressWarnings("deprecation")
	public static HashMap<String, Integer> tester(@SuppressWarnings("rawtypes") Class c) {
		DateFormat df = new SimpleDateFormat("HH:mm:ss");
 
		HashMap<String, Integer> resultats = new HashMap<String, Integer>();
 
		List<Method> methodes = Arrays.asList(c.getDeclaredMethods());
 
		//Collections.sort(methodes, (Method m1, Method m2) -> m1.getName().compareTo(m2.getName()));
		Collections.sort(methodes, new Comparator<Method>() {
			public int compare(Method m1, Method m2) {
				return m1.getName().compareTo(m2.getName());
			}
		});
		int im=0;
		String choix ="";
		while (im<methodes.size() && !choix.toUpperCase().equals("Q")) {//for (Method methode : methodes) {
			Method methode = methodes.get(im);
			Invocateur threadInvocateur = new Invocateur(methode);
			Thread threadTueur = new Thread(new Tueur(threadInvocateur, TIME_OUT));
 
			System.out.println("\n"+completer(df.format(new Date()) + " debut du test "+methode.getName(), LARGEUR_LIGNES, '-'));
 
			threadInvocateur.start();
			threadTueur.start();
 
			try {
				threadInvocateur.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
 
			System.out.println(df.format(new Date()) + " fin du test");
			if (!threadTueur.isInterrupted()) {// Le thread1 s'est acheve avant le tueur
				threadTueur.stop();            // --> On tue le tueur
			}
			int res = threadInvocateur.getResultat();
			System.out.println("Resultat = "+res+"/100");
			resultats.put(methode.getName(), res);
			if (choix.equals("") && res!=100 && im!=methodes.size()-1) {
				System.out.println("<<<  Saisissez \"Q\" ([Q]+[ENTREE]) pour Quitter les tests                                       >>>");
				System.out.println("<<<         ou \"T\" pour Terminer les tests sans les interrompre                                >>>");
				System.out.println("<<<  Toute autre saisie entrainera la poursuite des tests jusqu'a la prochaine methode erronee >>>");
				choix = lireString();
				if (!choix.toUpperCase().equals("Q") && !choix.toUpperCase().equals("T")) {
					choix="";
				}
			}
			im++;
		}
		return resultats;
	}
 
	public static String completer(String ligne, int largeurDesiree, char c) {
		String res = ligne;
		while (res.length()<largeurDesiree) {
			res=res+c;
		}
		return res;
	}
	public static void main(String[] args) {
		//HashMap<String, Integer> resultats = tester(ExempleTests.class);
		//System.out.println(heure());
		///////////////////////////////////////////////////////////////////////////////  ici
/*
 		if (!Test.run()) {
			//System.out.println("!Test.run() passed "+heure());
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} else {
			//System.out.println("!Test.run() passed "+heure());
		}
//*/
		HashMap<String, Integer> resultats = tester(packnp.tests.tools.TestsSA2020TP6.class);
		System.out.println("\nRESUME :");
		List<String> keys = new ArrayList<String>(resultats.keySet());
		Collections.sort(keys);
		for (String s : keys) {
			int res = resultats.get(s);
			System.out.println(completer(s, 45, '-')+"> "+(res<10 ? "  "+res : (res<100 ? " "+res : res))+"/100");
		}
 
	}
}
 
