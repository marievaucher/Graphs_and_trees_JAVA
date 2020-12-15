package packnp.tests.tools;
 
import java.lang.reflect.Method;
 
public class Invocateur extends Thread {
	private int resultat;
	private Method methode;
 
	public Invocateur(Method methode) {
		this.methode = methode;
		this.resultat = 0;
	}
 
	public int getResultat() {
		return resultat;
	}
 
	public void run() {
		try {
			resultat = (Integer)(methode.invoke(null));
		} catch (Exception e) {
			if (e instanceof java.lang.reflect.InvocationTargetException) {
				if (!(e.getCause() instanceof java.lang.ThreadDeath)) {
					e.getCause().printStackTrace();
				}
			} else {
				e.printStackTrace();
			}
		}
	}
}
