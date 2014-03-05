
public class RNLinkedListIterator {
	private RomanNumeralNode node;
	
	public RNLinkedListIterator(RomanNumeralNode first){
		node = first;
	}
	
	public boolean hasNext() {
		return(node!=null);
	}
	
	public RomanNumeral next() {
		if (node==null)
			throw new NullPointerException("Linked list empty");
		RomanNumeral currentData = node.data;
		node = node.next;
		return currentData;
	}

}

/*
public class LinkedListIterator {

private ListNode node;
public LinkedListIterator(ListNode first)  {
     node = first;
}

public boolean hasNext()  {
   return ( node != null );
}

public String next()  {
   if ( node == null )
      throw new NullPointerException("Linked list empty.");
   String currentData = node.data;
   node = node.next;
   return currentData;
}

}
*/