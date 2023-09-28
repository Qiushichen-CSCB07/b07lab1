import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream; 


public class Polynomial {
    public double[] coefficient;
    public int[] degree;

    public Polynomial(){
        this.coefficient = null;
        this.degree = null;
    }

    public Polynomial(double[] coefficient, int[] degree){
        this.coefficient = coefficient;
        this.degree = degree;
    }
    
    public Polynomial(File file) throws Exception{
        BufferedReader input = new BufferedReader(new FileReader(file));
        String line = input.readLine();
        line = line.replace("-", "+-");
//        System.out.println(line);
        String[] splited = line.split("\\+");
//        for(int i = 0; i < splited.length; i ++) {
//        	System.out.print(splited[i] + ", ");
//        }
        double[] coefficient = new double[splited.length];
        int[] degree = new int[splited.length];
        String[] part = null;

        for(int i = 0; i < splited.length; i++){
            part = splited[i].split("x");
            coefficient[i] = Double.parseDouble(part[0]);
            if(part.length == 1){
                degree[i] = 0;
            }
            else{
                degree[i] = Integer.parseInt(part[part.length-1]);
            }
        }
        input.close();
        
        this.coefficient = coefficient;
        this.degree = degree;
    }
    
    public double[] PolyArray(double[] coefficient, int[] degree){
    	//this helper function convert coefficient[] and degree[] into a double array :PolyArray[]
        int size = (degree[degree.length - 1]) + 1;
        double[] result = new double[size];
        int j = 0;
        for(int i = 0; i < size; i ++){
            if(i == degree[j]){
                result[i] =  coefficient[j];
                j ++;
            }
            else{
                result[i] = 0;
            }
        }
        return result;

    }
    
    public double[] Coef(double[] polyArray) {
    	//this helper function convert polyArray into coefficient[]
    	int numOfZero = 0;
		for(int i = 0; i < polyArray.length; i++){
			if(polyArray[i] == 0){
				numOfZero ++;
			}
		}
		double[] result = new double[polyArray.length - numOfZero];
        int j = 0;
		for(int i= 0; i < polyArray.length; i ++){
			if(polyArray[i] != 0){
                result[j] = polyArray[i];
                j++;
            }
		}
        return result;
    }
    
    public int[] Dgr(double[] polyArray){
    	//this helper function convert polyArray into degree[]
		int numOfZero = 0;
		for(int i = 0; i < polyArray.length; i++){
			if(polyArray[i] == 0){
				numOfZero ++;
			}
		}
		int[] result = new int[polyArray.length - numOfZero];
        int j = 0;
		for(int i= 0; i < polyArray.length; i ++){
			if(polyArray[i] != 0){
                result[j] = i;
                j++;
            }
		}
        return result;
	}
    
    public Polynomial add(Polynomial that){
    	double[] thisPoly = PolyArray(this.coefficient, this.degree);
        double[] thatPoly = PolyArray(that.coefficient, that.degree);
        int size = Math.max(thisPoly.length, thatPoly.length);
        double[] result = new double[size];
        for(int i = 0; i < size; i++){
            if(i < thisPoly.length && i < thatPoly.length){
                result[i] = thisPoly[i] + thatPoly[i];
            }
            else if(i < thisPoly.length && i >= thatPoly.length){
                result[i] = thisPoly[i];
            }
            else{
                result[i] = thatPoly[i];
            }
        }
        this.coefficient = Coef(result);
        this.degree = Dgr(result);
        return this;
    }
	
    public double evaluate(double num){
        double result = 0;
        for (int i = 0; i < this.coefficient.length; i++){
            result += this.coefficient[i] * Math.pow(num, this.degree[i]);
        }

        return result;
    } 

    public Boolean hasRoot(double num){
        return evaluate(num) == 0;
    }

    public Polynomial multiply(Polynomial that){
    	int size = (int)this.degree[this.degree.length - 1] + (int)that.degree[that.degree.length - 1] + 1;
        double[] newPoly = new double[size];
        for(int i = 0; i < newPoly.length;i++) {
        	newPoly[i] = 0;
        }
        for(int i = 0; i < this.degree.length; i ++){
            for(int j = 0; j < that.degree.length; j++){
                newPoly[this.degree[i] + that.degree[j]] += this.coefficient[i] * that.coefficient[j];
            }
        }
        return new Polynomial(Coef(newPoly), Dgr(newPoly));
    }


    public void saveToFile(String Strfile) throws IOException{
    	File file = new File(Strfile);
    	PrintStream ps = new PrintStream(file);
        String result = "";
        if (this.degree != null){
        	result += Double.toString(this.coefficient[0]);
            for (int i = 1; i < this.degree.length; i++){
                if (this.coefficient[i] > 0){
                    result += "+";
                }
                result += Double.toString(this.coefficient[i]);
                if (this.degree[i] != 0){
                    result += "x";
                    if (this.degree[i] > 1){
                        result += Integer.toString(this.degree[i]);
                    }
                }
                
                
            }

        }
        ps.print(result);
        ps.close();
    }
    
    public void Show() {
    	for(int i = 0; i < this.coefficient.length; i ++) {
			System.out.print(this.coefficient[i] + " * x^(" + this.degree[i] + ")");
			if(i < this.coefficient.length - 1) {
				System.out.print(" + ");
			}
			else {
				System.out.println("");
			}
		}
    }

}