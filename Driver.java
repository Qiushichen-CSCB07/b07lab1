import java.io.File;

public class Driver {
	
	
	public static void main(String[] args) throws Exception{
		double coefA[] = {5,6};
		int dgrA[] =  {3,0};
		
		double coefB[] = {-9,-2};
		int dgrB[] = {4,1};

		Polynomial polyA = new Polynomial(coefA, dgrA);
		Polynomial polyB = new Polynomial(coefB,dgrB);
		System.out.print("The PolyA is: ");
		polyA.Show();
		System.out.print("The PolyB is: ");
		polyB.Show();
		
		System.out.print("The PolyA * PolyB is: ");
		polyA.multiply(polyB).Show();
		polyA.add(polyB);
		System.out.print("The PolyA+B is: ");
		polyA.Show();
		
		System.out.println("PolyA(0.1) is " + polyA.evaluate(0.1));
		if(polyA.hasRoot(1)) {
			System.out.println("1 is a root of polyA");
		}
		else {
			System.out.println("1 is not a root of PolyA");
		}
		
		File file = new File("C:\\Users\\86184\\Desktop\\2023 UTSC\\CSCB07\\JavaFileTestFolder\\Lab2InputFileTest.txt");
		Polynomial polyC = new Polynomial(file);
		System.out.print("The PolyC is: ");
		polyC.Show();
		String Strfile = "C:\\Users\\86184\\Desktop\\2023 UTSC\\CSCB07\\JavaFileTestFolder\\Lab2SaveFileTest.txt";
		polyB.saveToFile(Strfile);
	
	}
	
	
	
}
