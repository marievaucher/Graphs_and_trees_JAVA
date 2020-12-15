package packnp.tests.tools;
 
public class Tueur implements Runnable {
	private Thread thread;
	long duree;
	
	public Tueur(Thread thread, long duree) {
		this.thread=thread;
		this.duree=duree;
	}
	
	@SuppressWarnings("deprecation")
	public void run() {
		try {
			Thread.sleep(duree);
			if (!thread.isInterrupted()) {
				System.out.println("Time out !  Le delai de "+(duree/1000)+" secondes alloue pour le test est depasse. Verifiez, entre autres, que votre code ne comporte pas de boucle infinie.");
				thread.stop();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
