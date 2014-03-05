
public abstract class RomanNumeralList {
	public RomanNumeralNode first;
	public RomanNumeralNode tail;
	public int length;
	
	public RomanNumeralList() {
		RomanNumeralNode dummy = new RomanNumeralNode();
		first = dummy;
		tail = dummy;
		length = 0;
	}
	
	/*
	public void append(RomanNumeral rn){
		RomanNumeralNode n = new RomanNumeralNode(rn);
		tail.next = n;
		tail = n;
		length++;
	}
	
	public void prepend(RomanNumeral rn){
		RomanNumeralNode n = new RomanNumeralNode(rn);
		n.next = first.next;
		first.next = n;
		length++;
	}//prepend
	
	public void insert(RomanNumeral rn){
		RomanNumeralNode current;
		RomanNumeralNode temp = new RomanNumeralNode(rn);
		RomanNumeralNode wantToInsert = new RomanNumeralNode(rn);
		if (length == 0) 
			append(rn);
		else if (rn.compareTo(first.next.data) == -1) 
				prepend(rn);
		else if (rn.compareTo(tail.data)==1)
				append(rn);
		for (current=first.next; (current.next != null) && (current.data.compareTo(wantToInsert.data)==-1); current = current.next){
				if (current.next.data.compareTo(wantToInsert.data)==1){
					temp.next = current.next;
					current.next = temp;
			}
		}
		length++;
	}//insert
	*/
	
	public RNLinkedListIterator reset() {
		return new RNLinkedListIterator(first.next);
	}
		
	public int getLength() {
		return length;
	}
	
	public boolean equals(Object other){
		if(other==null || getClass()!=other.getClass() || length!=((RomanNumeralList)other).length)
			return false;
		RomanNumeralNode p=first.next;
		RomanNumeralNode q=((RomanNumeralList)other).first.next;
		for(int i=0;i<length;i++){
			if(p.data!=q.data)  return false;
			p=p.next;
			q=q.next;
		}
		return true;
	}//equals method
	
	public String toString() {
		RomanNumeralNode p = first.next;
		String returnRNString = "";
		while (p != null) {
			returnRNString += p.data + "\n";
			p = p.next;
		}
		return returnRNString;
	}//toString method
}

