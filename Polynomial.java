public class Polynomial{

	double[] coefficient;

	public Polynomial(){
		this.coefficient = new double[1];
		this.coefficient[0] = 0;
	}
	
	public Polynomial(double[] thePoly){
		this.coefficient = thePoly;
	}

	public Polynomial add(Polynomial polyTheSecond){
		int lnOfLocal = this.coefficient.length;
		int lnOfOther = polyTheSecond.coefficient.length;
		int size = Math.max(lnOfLocal, lnOfOther);
		double[] result = new double[size];
		for(int i = 0; i < size; i ++){
			if(i < lnOfLocal && i < lnOfOther){
				result[i] = this.coefficient[i] + polyTheSecond.coefficient[i];
			}
			else if(i < lnOfLocal && i >= lnOfOther){
				result[i] = this.coefficient[i];
			}
			else{
				result[i] = polyTheSecond.coefficient[i];
			}
		}
		this.coefficient = result;
        return this;
	}
	
	public double evaluate(double num){
		double result;
        result = 0;
		for(int i = 0; i < this.coefficient.length; i ++){
			result = result + this.coefficient[i]*Math.pow(num, this.coefficient[i]);
		}
		return result;
	}

	public boolean hasRoot(double root){
		double result = 0;
		for(int i = 0; i < this.coefficient.length; i++){
			result = result + this.coefficient[i]*Math.pow(root, this.coefficient[i]);
		}
		return result == 0;
	
	}
}

