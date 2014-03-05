
public class sortedRomanNumeralList extends RomanNumeralList {
	
	public void add(RomanNumeral rn){
		RomanNumeralNode current;
		RomanNumeralNode wantToInsert = new RomanNumeralNode(rn);
		RomanNumeralNode n = new RomanNumeralNode(rn);
		if (length == 0){ 
			tail.next = n;
			tail = n;
			length++;
		}
		else if (rn.compareTo(first.next.data) == -1){ 
			n.next = first.next;
			first.next = n;
			length++;
		}
		else if (rn.compareTo(tail.data)==1){
			tail.next = n;
			tail = n;
			length++;
		}
		current = first.next;
		while (current.next != null && current.data.compareTo(wantToInsert.data)==-1){
				if (current.next.data.compareTo(wantToInsert.data)==1){
					wantToInsert.next = current.next;
					current.next = wantToInsert;
				}
				current = current.next;
		}
		length++;
	}//insert

}
