
import java.io.PrintStream;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class DequeArray<Item> implements Iterable<Item> {
	private int first,last;
	private Item[] a;
	private int n;
	public DequeArray(){
		a=(Item[]) new Object[2];
		n=0;
		first=0;
		last=1;
		// construct an empty deque
	}
	public boolean isEmpty(){
		return n==0;
		// is the deque empty?
	}
	public int size(){
		return n;
		// return the number of items on the deque
	}
	public void addFirst(Item item){
		addValidate(item);
		if(n==a.length)resize(2*n);
		a[first]=item;
		first=first==0?a.length-1:first-1;
	
		n++;
		// add the item to the front
	}
	public void addLast(Item item){
		addValidate(item);
		if(n==a.length)resize(2*n);
		// add the item to the end
		a[last]=item;
		last=last==a.length-1?0:last+1; 
		
		n++;
	}
	
	public Item removeFirst(){
		// remove and return the item from the front
		removeValidate();
		first=first==a.length-1?0:first+1;
		Item item=a[first];
		n--;
		if(n>0&&n==a.length/4)resize(a.length/2);
		return item;
	}
	public Item removeLast(){
		// remove and return the item from the end
		removeValidate();
		last=last==0?a.length-1:last-1;
		Item item=a[last];
		n--;
		if(n>0&&n==a.length/4)resize(a.length/2);
		return item;
	}
	public Iterator<Item> iterator(){
		return new DequeIterator();
		// return an iterator over items in order from front to end
		
	}
	private class DequeIterator implements Iterator<Item>{
		int x=n;
		int f=first;
		public boolean hasNext(){
			return x>0;
		}
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            f=f==a.length-1?0:f+1;
            Item firstItem=a[f];
            x--;
            return firstItem;
        }
        public void remove()     { throw new UnsupportedOperationException(); }
		
	}
	private void resize(int c){
		Item[] temp=(Item[]) new Object[c];
		int f=first==a.length-1?0:first+1;
		for(int i=0;i<n;i++){
			temp[i]=a[f];
			f++;
			if(f==n)f=0;
		}
		a=temp;
		first=c-1;
		last=n;
	}
	private void addValidate(Item item){
		if(item==null){
			throw new NullPointerException();
		}
	}
	private void removeValidate(){
		if(size()==0){
			throw new NoSuchElementException();
		}
	}
	public static void main(String[] args){
		// unit testing
		DequeArray<Integer> deque=new DequeArray();
		deque.addFirst(0);
		deque.addFirst(1);			
		deque.addFirst(2);			
		deque.addFirst(3);			
		deque.addFirst(4);			
		deque.addFirst(5);			
		deque.addFirst(6);			
		deque.addFirst(7);			
		deque.addFirst(8);			
		System.out.println(deque.removeLast());

//		System.out.println(deque.removeFirst());System.out.println(deque.removeFirst());
		
//		Iterator<Integer> i=deque.iterator();
//		while(i.hasNext()){
//			System.out.println(i.next());
//		}

			
	}
}
