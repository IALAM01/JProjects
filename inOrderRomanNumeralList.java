
public class inOrderRomanNumeralList extends RomanNumeralList {
	
	public void add(RomanNumeral rn){
		RomanNumeralNode n = new RomanNumeralNode(rn);
		tail.next = n;
		tail = n;
		length++;
	}

}
