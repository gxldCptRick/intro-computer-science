package models;

public class Fraction implements Comparable<Fraction> {

	private int numerator = 0, denominator = 1, wholeNum = 0;
	private double decimalValue = 0.0;

	public Fraction(int wholeNum) {

		this.wholeNum = wholeNum;

		this.numerator = this.wholeNum;

		this.decimalValue = this.wholeNum;
	}

	public Fraction(double decimalValue) {

		int amountOfTens = 0;

		this.decimalValue = decimalValue;

		this.wholeNum = (int) decimalValue;

		decimalValue -= wholeNum;

		do {

			decimalValue *= 10;

			amountOfTens++;

		} while (decimalValue < 1 && decimalValue != 0);

		this.numerator = (int) decimalValue;

		this.denominator = 10 * amountOfTens;

	}

	public Fraction(int num, int dem) {

		this();

		this.numerator = num;

		if (dem == 0) {
			throw new ArithmeticException("The  denominator cannot be zero");
		}

		this.denominator = dem;

		this.decimalValue = ((double) num) / dem;

	}

	public Fraction(int num, int dem, int whole) {

		this(num, dem);

		this.wholeNum = whole;

		this.decimalValue += wholeNum;

	}

	public Fraction() {
	}

	public static int compare(Fraction firstFrac, Fraction secondFrac) {

		return Double.compare(firstFrac.decimalValue, secondFrac.decimalValue);
	}

	public int getNumerator() {
		return numerator;
	}

	public int getDenominator() {
		return denominator;
	}

	public double getDecimalValue() {
		return decimalValue;
	}

	public int getWholeNum() {
		return this.wholeNum;
	}

	public boolean equals(Fraction frac2) {
		return (this.decimalValue == frac2.decimalValue);
	}

	@Override

	public boolean equals(Object obj) {
		boolean equal = false;
		if (Fraction.class.isInstance(obj)) {
			equal = equals((Fraction) obj);
		}

		return equal;

	}

	@Override

	public int hashCode() {

		return Double.hashCode(decimalValue);
	}

	public void simplify() {

		int gcf = 0;

		if (this.numerator > this.denominator || (this.numerator < this.denominator && this.numerator < 0)) {

			this.wholeNum += this.numerator / this.denominator;

			if (this.numerator < 0) {

				this.numerator *= -1;

			}

			this.numerator %= this.denominator;

		} else if (this.numerator == this.denominator) {

			this.wholeNum += 1;

			this.denominator = 1;

			this.numerator = 1;

		}

		gcf = gCF(this.numerator, this.denominator);

		this.numerator /= gcf;

		this.denominator /= gcf;

	}

	public int mod(int num) {
		return this.mod(new Fraction(num));
	}
	
	public int mod(Fraction otherFrac) {
		Fraction mod = null;

		int remainder = 0;
		
		if (otherFrac.numerator != 0 && this.equals(otherFrac)) {

			
			mod = this.divide(otherFrac);
			
			remainder = mod.numerator;
			
		}
		

		return remainder;
	}

	public Fraction add(int num) {
		
		return this.add(new Fraction(num));
	
	}
	
	public Fraction add(Fraction otherFrac) {

		Fraction sum;

		int wholeNum = this.wholeNum + otherFrac.wholeNum, numNum = 0, demNum = this.denominator;

		if (this.denominator == otherFrac.denominator) {
			numNum = this.numerator + otherFrac.numerator;

		} else {
			numNum = this.numerator * otherFrac.denominator + this.denominator * otherFrac.numerator;
			demNum = this.denominator * otherFrac.denominator;
		}

		sum = new Fraction(numNum, demNum, wholeNum);

		sum.simplify();

		return sum;

	}
	
	public Fraction subtract(int num) {
		return this.subtract(new Fraction(num));
	}
	

	public Fraction subtract(Fraction otherFrac) {
		Fraction difference;
		int numNum = 0, demNum = 1;

		if (this.denominator == otherFrac.denominator) {

			demNum = this.denominator;

			numNum = this.numerator + this.wholeNum * demNum;

			numNum -= otherFrac.numerator + otherFrac.wholeNum * demNum;

		} else {

			numNum = (this.numerator + this.wholeNum * this.denominator) * otherFrac.denominator;

			numNum -= (otherFrac.numerator + otherFrac.wholeNum * otherFrac.denominator) * this.denominator;

			demNum = this.denominator * otherFrac.denominator;
		}

		difference = new Fraction(numNum, demNum);

		difference.simplify();

		return difference;
	}

	public Fraction multiply(int num) {
		
		return this.multiply(new Fraction(num));
		
	}
	
	public Fraction multiply(Fraction otherFrac) {

		Fraction product;

		int numNum = 0, demNum = 1;

		numNum = this.numerator + this.wholeNum * this.denominator;

		numNum *= otherFrac.numerator + otherFrac.wholeNum * otherFrac.denominator;

		demNum *= this.denominator * otherFrac.denominator;

		product = new Fraction(numNum, demNum);

		product.simplify();

		return product;

	}
	
	public Fraction divide(int num) {
		if(num == 0) {
			throw new ArithmeticException("You cant divide by zero");
		}
		
		return this.divide(new Fraction(num));
		
	}

	public Fraction divide(Fraction otherFrac) {

		int numNum = 0, demNum = 1;

		numNum = otherFrac.denominator;

		demNum = otherFrac.numerator + otherFrac.denominator * otherFrac.wholeNum;

		Fraction flippedFrac1 = new Fraction(numNum, demNum);

		return flippedFrac1.multiply(this);

	}

	public Fraction pow(int power) {
		Fraction newFraction = new Fraction(1);

		if (power < 0) {

			throw new IllegalArgumentException("I cant raise to a negative power");
		} else {

			for (int i = 0; i < power; i++) {

				newFraction = newFraction.multiply(this);
			}

		}

		newFraction.simplify();

		return newFraction;

	}

	private int gCF(int num, int dem) {

		if (dem == 0) {
			return num;
		} else {
			return gCF(dem, num % dem);
		}
	}

	@Override
	public String toString() {
		if (this.denominator == 1) {
			return (this.wholeNum + "");
		} else if (this.wholeNum != 0) {
			return (this.wholeNum + " " + this.numerator + "/" + this.denominator);
		} else {
			return (this.numerator + "/" + this.denominator);
		}
	}

	@Override
	public int compareTo(Fraction otherFrac) {
		return Double.compare(otherFrac.decimalValue, this.decimalValue);
	}

}
