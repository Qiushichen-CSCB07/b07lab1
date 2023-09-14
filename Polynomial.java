
public class Polynomial {
	public double[] coefficient;

	public Polynomial(){
		this.coefficient = new double[1];
		this.coefficient[0] = 0;
	}
	
	public Polynomial(double[] coefficient){
		this.coefficient = coefficient;
	}

    public Polynomial add(Polynomial that){
        double[] thisPoly = this.coefficient;
        double[] thatPoly = that.coefficient;
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
        this.coefficient = result;
        return this;
    }

    public double evaluate(double num){
		double result;
        result = 0;
		for(int i = 0; i < this.coefficient.length; i ++){
			result = result + this.coefficient[i]*Math.pow(num, i);
		}
		return result;
	}

    public boolean hasRoot(double root){
		return this.evaluate(root) == 0;
	
	}

}
