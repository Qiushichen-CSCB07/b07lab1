import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.PrintStream;

import javax.print.StreamPrintService;
import javax.sound.sampled.AudioFormat.Encoding;
import javax.swing.DebugGraphics;


class Polynomial{
	double[] coefficient;
	int[] degree;

	public Polynomial(){
		this.coefficient = new double[1];
		this.coefficient[0] = 0;
		this.degree = new int[1];
		this.degree[0] = 0;
	}
	
	public Polynomial(double[] coefficient, int[] degree){
		this.coefficient = coefficient;
		this.degree = degree;
	}

    public Polynomial(File file) throws Exception{
        BufferedReader input = new BufferedReader(new FileReader(file));
        String line = input.readLine();
        line = line.replace("-", "+-");
        if(line.indexOf('+') == 0){
            line = line.substring(1, line.length());
        }

        String[] splited = line.split("\\+");
    
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
        this.coefficient = coefficient;
        this.degree = degree;
    }


    public Polynomial add(Polynomial that){
        double[] thisPoly = polynomial(this.coefficient, this.degree);
        double[] thatPoly = polynomial(that.coefficient, that.degree);
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
        this.coefficient = coef(result);
        this.degree = dgr(result);
        return this;
    }

    public double evaluate(double num){
		double result;
        result = 0;
		for(int i = 0; i < this.coefficient.length; i ++){
			result = result + this.coefficient[i]*Math.pow(num, this.degree[i]);
		}
		return result;
	}

    public boolean hasRoot(double root){
		return this.evaluate(root) == 0;
	
	}

    public Polynomial multiply(Polynomial that){
        int size = (int)this.degree[this.degree.length - 1] + (int)that.degree[that.degree.length - 1] + 1;
        double[] newPoly = new double[size];
        for(int i = 0; i < this.degree.length; i ++){
            for(int j = 0; j < that.degree.length; j++){
                newPoly[this.degree[i] + that.degree[j]] = newPoly[this.degree[i] + that.degree[j]] + this.coefficient[i] * that.coefficient[j];
            }
        }
        return new Polynomial(coef(newPoly), dgr(newPoly));
    }

    public void saveToFile(String filename){
        String result = "";
       for(int i = 0; i < this.degree.length; i ++){
        result = result + equationPart(coefficient[i], degree[i]);
       }
       if(result.indexOf('+') == 0){
        result = result.substring(1,result.length());
       }
       try{
        PrintStream ps = new PrintStream(new FileOutputStream(filename));
        ps.println(result);
       }catch(FileNotFoundException e){
        e.printStackTrace();
       }

    }

    public static String equationPart(double coefficient, int degree){
        String coef = "";
        String drg = "";
        if(coefficient < 0){
            coef = "" + (int)coefficient;
        }
        else{
            coef = "+" + ("" + (int)coefficient);
        }
        if(degree != 0){
            drg = "x" + ("" + degree);
        }
        return coef + drg;
    }



    public static double[] polynomial(double[] coefficient, int[] degree){
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
   
    

    public static double[] coef(double[] poly){
		int numOfZero = 0;
		for(int i = 0; i < poly.length; i++){
			if(poly[i] == 0){
				numOfZero ++;
			}
		}
		double[] result = new double[poly.length - numOfZero];
        int j = 0;
		for(int i= 0; i < poly.length; i ++){
			if(poly[i] != 0){
                result[j] = poly[i];
                j++;
            }
		}
        return result;
	}

    public static int[] dgr(double[] poly){
		int numOfZero = 0;
		for(int i = 0; i < poly.length; i++){
			if(poly[i] == 0){
				numOfZero ++;
			}
		}
		int[] result = new int[poly.length - numOfZero];
        int j = 0;
		for(int i= 0; i < poly.length; i ++){
			if(poly[i] != 0){
                result[j] = i;
                j++;
            }
		}
        return result;
	}

}
