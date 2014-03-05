public class RomanNumeralNode {
	RomanNumeral data;
	RomanNumeralNode next;
	
	public RomanNumeralNode(RomanNumeral data, RomanNumeralNode next) {
		this.data = data;
		this.next = next;
	} //constructor1
	
	public RomanNumeralNode() {
		this.data = null;
		this.next = null;
	} //constructor2
	
	public RomanNumeralNode(RomanNumeral data){
		this.data = data;
		this.next = null;
	}
	
}

