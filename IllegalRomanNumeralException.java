public class IllegalRomanNumeralException extends IllegalArgumentException{
	/**
	 * A custom exception that is thrown for illegal Roman numerals. The class extends IllegalArgumentException.
	 * 
	 * @param m Passes an string containing an error message to the super constructor in IllegalArgumentException.
	 */
	public IllegalRomanNumeralException(String m){
		super(m);
	}
}
