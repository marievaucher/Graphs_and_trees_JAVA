package packnp.tests.tools;
 
 
public class ExempleTests {
	
	public static int testA() {
		System.out.println("testA : test rapide");
		return 50;
	}
	
	public static int testB() {
		System.out.println("testB : test de 10 secondes");
		for (int i=0; i<10; i++) {
			System.out.print(".");
			try {
			Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return 75;
	}
 
	public static int testC() {
		System.out.println("testC : test infini");
		while (true) {
			System.out.print(".");
			try {
			Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static int testD() {
		System.out.println("testB : test de 3 secondes puis ArrayIndexOutOfBounds");
		String[] affichage = {".", ".", "."};
		for (int i=0; i<10; i++) {
			System.out.print(affichage[i]);
			try {
			Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}
	
}
