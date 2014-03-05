import java.util.regex.*;
import javax.swing.*;

/**
 * The class RomanNumeral creates a Roman Numeral object. It implements Comparable so that RomanNumerals can sorted using
 * Collections.sort();
 * 
 * @author Isfaqual Alam
 *
 */
public class RomanNumeral implements Comparable {
	
	private String romaNumera;
	
	/**
	 * The RomanNumeral Constructor will accept either Roman Numerals or Arabic Numerals. 
	 * It will convert Arabic Numerals to Roman Numerals.
	 * 
	 * Throws and IllegalRomanNumeralException is the string parameter is not a valid Roman Numeral or cannot be converted to a Roman Numeral object.
	 * 
	 * @param rn The string being made into a RomanNumeral object
	 */
	public RomanNumeral(String rn){
		Pattern pR = Pattern.compile("[IVXLCDM]+"); //Roman Numerals
		Pattern pA = Pattern.compile("\\d+");		//Arabic Numerals
		
		Matcher mR = pR.matcher(rn);
		Matcher mA = pA.matcher(rn);
		
		if (mR.matches())
			romaNumera = rn;
		else if(mA.matches()){
			romaNumera = convertToRoman(rn); //converts to a Roman Numeral if the string contains Arabic Numerals
		}
		else 
			throw new IllegalRomanNumeralException("Not a valid Roman Numeral" + rn);
			
	}
	
	/**
	 * compareTo is implemented by Comparable, and compares two Roman Numerals and returns an integer -1, 0, or 1.
	 * 
	 * -1 when this Roman Numeral is less than the parameter
	 *  0 when this Roman Numeral is equal to the parameter
	 *  1 when this Roman Numeral is greater than the parameter
	 *  
	 *  Throws an IllegalRomanNumeralException if the parameter is not an instance of RomanNumeral
	 * 
	 */
	public int compareTo(Object rn) {
		if (!(rn instanceof RomanNumeral)){
			throw new IllegalRomanNumeralException("Not a Roman Numeral object");
		}
		
		
		if (this.getDecimalValue() > ((RomanNumeral)rn).getDecimalValue()) 
		{
			return 1;
		}
		if (this.getDecimalValue() == ((RomanNumeral)rn).getDecimalValue()) 
		{
			return 0;
		}
		else
			return -1;
	}
	
	/**
	 * Overrides the .equals method
	 * 
	 * @param rn The RomanNumeral being compared to.
	 * @return True if equal, false if not.
	 */
	public boolean equals(RomanNumeral rn) {
		return this.romaNumera.equals(rn);
	}

	/**
	 * Overrides the .toString() method.
	 * 
	 * Provides the a string representation of the RomanNumeral.
	 * 
	 */
	 public String toString() {
      		return romaNumera;
   	}

	
	/**
	 * Converts a string with decimals to Roman Numerals
	 * 
	 * @param rn The string containing decimals
	 * @return The converted Roman Numeral string
	 */
	private String convertToRoman(String rn){
		String convertedF="", converted="";
		int ones=0, tens=0, hundreds=0, thousands=0;
		int V = 0, L = 0, D = 0;
		
			//thousands	
			if (rn.length()>=4){
				thousands= Integer.parseInt( rn.substring( 0, rn.length()-3 ) );
				convertedF += atS(thousands, converted, "M");
			}
			
			//hundreds
			if (rn.length()>=3){
				hundreds = Integer.parseInt(rn.substring(rn.length()-3, rn.length()-2));
				if (hundreds == 4)
					convertedF += "CD";
				else if (hundreds == 9)
					convertedF += "CM";
				else if (hundreds > 5){
					D += hundreds/5;
					hundreds = hundreds%5;
					convertedF += atS(D, converted, "D");
					convertedF += atS(hundreds, converted, "C");
				}
				else convertedF += atS(hundreds, converted, "C");
			}
			
			//tens	
			if (rn.length()>=2){
				tens 	 = Integer.parseInt(rn.substring(rn.length()-2, rn.length()-1));
				if (tens == 4)
					convertedF += "XL";
				else if (tens == 9)
					convertedF += "XC";
				else if (tens > 5){
					L += tens/5;
					tens = tens%5;
					convertedF += atS(L, converted, "L");
					convertedF += atS(tens, converted, "X");
				}
				else
					convertedF += atS(tens, converted, "X");
			}
			
			//ones
			if (rn.length()>=1){
				ones 	 = Integer.parseInt(rn.substring(rn.length()-1, rn.length()));
				if (ones == 4)
					convertedF += "IV";
				else if (ones == 9)
					convertedF += "IX";
				else if (ones > 5){
					V += ones/5;
					ones = ones%5;
					convertedF += atS(V, converted, "V");
					convertedF += atS(ones, converted, "I");
				}
				else
					convertedF += atS(ones, converted, "I");
			}
		return convertedF;
	}
	
	/**
	 * Builds a string by adding a string/substring to the string by a number of times.
	 * 
	 * @param scope The number of times the string/substring will be added to the string
	 * @param builtRN The string to be built
	 * @param rn The string/substring
	 * @return
	 */
	private String atS(int scope, String builtRN, String rn){
		int i=0;
		while (i<scope){
			builtRN += rn;
			i++;
		}
		
		return builtRN;
	}

	/**
	 * Returns the decimal value of the Roman Numeral.
	 * 
	 * @return the value of the Roman Numeral as signed integer
	 */
	public int getDecimalValue() {
		int I=1, V=5, X=10, L=50, C=100, D=500, M=1000; //initialize Roman values
		int[] valueOfNumeral = new int[romaNumera.length()]; //array will hold values
		int result = 0;

		for (int i = 0; i<romaNumera.length(); i++) {
			if (romaNumera.charAt(i)==('I')) {valueOfNumeral[i] = I;}
			if (romaNumera.charAt(i)==('V')) {valueOfNumeral[i] = V;}
			if (romaNumera.charAt(i)==('X')) {valueOfNumeral[i] = X;}
			if (romaNumera.charAt(i)==('L')) {valueOfNumeral[i] = L;}
			if (romaNumera.charAt(i)==('C')) {valueOfNumeral[i] = C;}
			if (romaNumera.charAt(i)==('D')) {valueOfNumeral[i] = D;}
			if (romaNumera.charAt(i)==('M')) {valueOfNumeral[i] = M;}
		}
		
	
		result = valueOfNumeral[romaNumera.length()-1];
		for (int j = valueOfNumeral.length-2; j>=0; j--){
			if (valueOfNumeral[j]<valueOfNumeral[j+1])
				result -= valueOfNumeral[j];
			else result += valueOfNumeral[j];
		}

		return result;
	}

	

} // class RomanNumerals
