
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
	private class Node{
		Node pre,next;
		Item item;
		public Node(Item item){
			this.item=item;
		}
	}
	private Node f,l;
	private int n;
	public Deque(){
		n=0;
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
		Node tmp=new Node(item);
		if(f!=null){
			tmp.next=f;
			f=tmp;
		}else{
			f=tmp;
			l=tmp;
		}
		n++;
		// add the item to the front
	}
	public void addLast(Item item){
		addValidate(item);
		Node tmp=new Node(item);
		if(l!=null){
			tmp.pre=l;
			l=tmp;
		}else{
			f=tmp;
			l=tmp;
		}
		n++;
	}
	
	public Item removeFirst(){
		removeValidate();
		Node tmp=f;
		f=tmp.next;
		n--;
		return tmp.item;
		
		// remove and return the item from the front
	}
	public Item removeLast(){
		removeValidate();
		Node tmp=l;
		l=tmp.pre;
		n--;
		return tmp.item;
		// remove and return the item from the end
	}
	public Iterator<Item> iterator(){
		return new DequeIterator();
		// return an iterator over items in order from front to end
		
	}
	private class DequeIterator implements Iterator<Item>{
		Node f=this.f,l=this.l;
		int n=this.n;
		public boolean hasNext(){
			return n!=0;
		}
        public Item next() {
        	Node tmp=f;
        	f=f.next;
        	return tmp.item;
        }
        public void remove()     { throw new UnsupportedOperationException(); }
		
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
		Deque<Integer> deque=new Deque();
		deque.addFirst(0);
		deque.addLast(2);
		System.out.println(deque.isEmpty());

//		System.out.println(deque.removeFirst());System.out.println(deque.removeFirst());
		
//		Iterator<Integer> i=deque.iterator();
//		while(i.hasNext()){
//			System.out.println(i.next());
//		}

			
	}
}
