import java.util.Iterator;

public class Subset {
	public static void main(String[] args){
		RandomizedQueue<String> r=new RandomizedQueue<>();
		while(StdIn.hasNextChar()){
			r.enqueue(StdIn.readString());
		}
		int k=Integer.parseInt(args[1]);
		Iterator<String> x=r.iterator();
		for(int i=0;i<k;i++){
			System.out.println(x.next());
		}
			
	}
}
