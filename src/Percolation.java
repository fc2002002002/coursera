import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Percolation{
	private WeightedQuickUnionUF w,full;
	private boolean[] open;
	private int N;
	private void validate(int N){
		if(N<1)
			throw new IllegalArgumentException();
			
	}
	private void validate(int i,int j){
		if(i<1 || i>N ||j<1 ||j>N){
			throw new IndexOutOfBoundsException() ;
		}
	}
	public Percolation(int N){
		validate(N);
		this.N=N;
		w=new WeightedQuickUnionUF(N*N+2);
		full=new WeightedQuickUnionUF(N*N+2);
		open=new boolean[N*N+2];
	}               // create N-by-N grid, with all sites blocked
	public void open(int i, int j){
		validate(i, j);
		open[getIndex(i,j)]=true;
		if(i==1){
			full.union(getIndex(i, j), 0);
			w.union(getIndex(i, j), 0);
		}
		if(i==N){
			w.union(getIndex(i, j), N*N+1);
		}
		union(i,j,i-1,j);
		union(i,j,i+1,j);
		union(i,j,i,j-1);
		union(i,j,i,j+1);
		open[getIndex(i, j)]=true;
	}          // open site (row i, column j) if it is not open already

	public boolean isOpen(int i, int j){
		validate(i, j);
		return open[getIndex(i,j)];
	}     // is site (row i, column j) open?
	public boolean isFull(int i, int j){
		validate(i, j);
		return isOpen(i, j)&&full.connected(getIndex(i, j), 0);
	}     // is site (row i, column j) full?
	public boolean percolates(){
		return w.connected(0, N*N+1);
	}             // does the system percolate?
	private void union(int i,int j,int m ,int n){
		if(getIndex(m, n)!=-1){
			if(isOpen(m, n)){
				w.union(getIndex(i, j), getIndex(m,n));
				full.union(getIndex(i, j), getIndex(m,n));
			}


		}
	}
	private int getIndex(int i,int j){
		if(i<=N && i>0 && j<=N && j>0){
			return (i-1)*N+j;
		}
		return -1; 
		//throw new IndexOutOfBoundsException("index is not between 0 and " + N+"  "+i+","+j); 
	}
	public static void main(String[] args) throws FileNotFoundException{
		File f=new File("percolation/input1-no.txt") ;
		In in=new In(f);
		Percolation p=new Percolation(in.readInt());
		int i,j;
		while(!in.isEmpty()){
			i=in.readInt();
			j=in.readInt();
			System.out.println(p.isFull(i, j));
			p.open(i,j);
		}
		in.close();
		System.out.println(p.percolates());
	}   // test client (optional)
}