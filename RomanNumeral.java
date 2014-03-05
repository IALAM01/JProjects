public class RomanNumeral {
	
	private String romaNumera;
	private String[] wordArray = { "I", "V", "X", "L", "C", "D", "M" };
	public  String[] compare;

	public RomanNumeral(String rn) {
		int count = 0;
		compare = new String[rn.length()];
		for (int a = 0; a < compare.length; a++) {
			// copies the user's input into an array so I can compare each
			// letter with the ones in wordArray
			compare[a] = rn.substring(a, a + 1);
		}

		for (int i = 0; i < compare.length; i++) {
			for (int j = 0; j < wordArray.length; j++) {
				/* If the letter is not equal to one Roman numeral, it increments count.
				   If count reaches 7 then that means the letter being compared
				   is not a Roman numeral. 
				*/
				if (!(compare[i].equals(wordArray[j]))) {
					count++;
				}
			}

			// if count reaches 7 that means nothing in wordArray was comparable
			if (count == 7) {
				throw new IllegalArgumentException("Contains a non-Roman character");
			}
			// count reset to 0 to avoid letting count>7.
			count = 0;
		}
		String str="";
		for(int i=0;i<compare.length;i++)
		{
			str += compare[i];
		}
		romaNumera =str;

	}
	
	public int compareTo(RomanNumeral rn) {
		if (this.getDecimalValue() > rn.getDecimalValue()) 
		{
			return 1;
		}
		if (this.getDecimalValue() == rn.getDecimalValue()) 
		{
			return 0;
		}
		else
			return -1;
	}

	public boolean equals(RomanNumeral rn) {
		return this.romaNumera.equals(rn);
	}

	 public String toString() {
      		return romaNumera;
   	}

		
	

	public int getDecimalValue() {
		int I=1, V=5, X=10, L=50, C=100, D=500, M=1000; //initialize Roman values
		int[] valueOfNumeral = new int[compare.length]; //array will hold values
		int result = 0;

		for (int i = 0; i<compare.length; i++) {
			if (compare[i].equals("I")) {valueOfNumeral[i] = I;}
			if (compare[i].equals("V")) {valueOfNumeral[i] = V;}
			if (compare[i].equals("X")) {valueOfNumeral[i] = X;}
			if (compare[i].equals("L")) {valueOfNumeral[i] = L;}
			if (compare[i].equals("C")) {valueOfNumeral[i] = C;}
			if (compare[i].equals("D")) {valueOfNumeral[i] = D;}
			if (compare[i].equals("M")) {valueOfNumeral[i] = M;}
		}
		
	
		result = valueOfNumeral[compare.length-1];
		for (int j = valueOfNumeral.length-2; j>=0; j--){
			if (valueOfNumeral[j]<valueOfNumeral[j+1])
				result -= valueOfNumeral[j];
			else result += valueOfNumeral[j];
		}

		return result;
	}

	

} // class RomanNumerals
