import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
	private class node{
		node next;
		node former;
		Item item;
		protected node(Item item) {
			super();
			this.item = item;
		}
		protected node getNext() {
			return next;
		}
		protected void setNext(node next) {
			this.next = next;
		}
		protected node getFormer() {
			return former;
		}
		protected void setFormer(node former) {
			this.former = former;
		}
		protected Item getItem() {
			return item;
		}
		protected void setItem(Item item) {
			this.item = item;
		}
		
	}
	private int n;
	private node head,tail;
	public RandomizedQueue(){
		head = new node(null);
		tail= new node(null);
		head.setFormer(null);
		head.setNext(tail);
		tail.setFormer(head);
		tail.setNext(null);
		n=0;

	}
	public boolean isEmpty(){
	   // is the queue empty?
		return n==0;
	}
	public int size(){
	   // return the number of items on the queue
		return n;
	}
	public void enqueue(Item item){
		if(item==null)throw new NullPointerException();
		node tmp=new node(item);
		tmp.setFormer(tail.former);
		tmp.setNext(tail);
		tail.former.setNext(tmp);
		tail.setFormer(tmp);
		n++;
		// add the item
	}
	public Item dequeue(){
		if(n==0)throw new NoSuchElementException();
		int ran=StdRandom.uniform(n);
		node tmp=getIndex(ran);
		tmp.former.setNext(tmp.next);
		tmp.next.setFormer(tmp.former);
		n--;
		return tmp.item;
	}
	public Item sample(){
		if(n==0)throw new NoSuchElementException();
		int ran=StdRandom.uniform(n);
		node tmp=getIndex(ran);
		return tmp.item;
	}
	private node getIndex(int n){
		node tmp=head;
		while(n-->=0){
			tmp=tmp.next;
		}
		return tmp;
	}
	public Iterator<Item> iterator(){
	   // return an independent iterator over items in random order
		
		
		return new ranIterator();
	}
	private class ranIterator implements Iterator<Item>{
		//private node now;
		private int last=0;
		private int[] s;
		
		public ranIterator() {
			super();
			//now=head;
			s=new int[n];
			for(int i=0;i<n;i++){
				s[i]=i;
			}
			StdRandom.shuffle(s);
		}
		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return last!=n;
		}

		@Override
		public Item next() {
			// TODO Auto-generated method stub
			/*int offset;
			if(last!=0){
				offset=s[last]-s[last-1];
			}else offset=0;
			*/
			if(n==0)throw new NoSuchElementException();
			return getIndex(s[last++]).item;
		}
		public void remove(){
			
			if(n==0)throw new UnsupportedOperationException();
		}
		
	}
	public static void main(String[] args){
	   // unit testing
		RandomizedQueue<Integer> d=new RandomizedQueue<>();
		d.enqueue(2);
		d.enqueue(3);
		d.enqueue(4);
		d.enqueue(5);
		d.enqueue(6);
		d.enqueue(7);
		d.enqueue(8);
		Iterator<Integer> a=d.iterator();
		Iterator<Integer> b=d.iterator();
		while(a.hasNext()){
			System.out.print(a.next());
		}
		System.out.println();

		while(b.hasNext()){
			System.out.print(b.next());
		}
		System.out.println();
		
		System.out.println(d.dequeue());
		System.out.println(d.dequeue());
		System.out.println(d.dequeue());
		System.out.println(d.dequeue());
		System.out.println(d.dequeue());
		System.out.println(d.dequeue());
		System.out.println(d.dequeue());
	}
}