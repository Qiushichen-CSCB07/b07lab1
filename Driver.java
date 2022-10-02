public class Driver { 
	public static void main(String [] args) { 
		Polynomial p = new Polynomial();
		System.out.println(p.evaluate(3));
		double [] c1Coefficient = {6,5};
		int[] c1Degree = {0,3};
		Polynomial p1 = new Polynomial(c1Coefficient,c1Degree); 
		double [] c2Coefficient = {-2, -9};
		int[] c2Degree = {1,4};
		Polynomial p2 = new Polynomial(c2Coefficient,c2Degree); 
		Polynomial s = p1.add(p2); 
		System.out.println("s(0.1) = " + s.evaluate(0.1)); 
		if(s.hasRoot(1)) 
			System.out.println("1 is a root of s"); 
		else 
			System.out.println("1 is not a root of s"); 
	}
} 
