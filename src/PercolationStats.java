public class PercolationStats {
	private int N;
	private int T;
	private double stddev,mean;
	public PercolationStats(int N, int T){
		validate(N,T);
		this.N=N;
		this.T=T;
		getResult();
	}     // perform T independent experiments on an N-by-N grid
	private void validate(int n,int t){
		if(n<1||t<1)
				throw new IllegalArgumentException();
	}
	private int getNumber(){
		Percolation p=new Percolation(N);
		int count=0;
		int i,j;
		while(!p.percolates()){
			i=StdRandom.uniform(N)+1;
			j=StdRandom.uniform(N)+1;
//			System.out.println(i+","+j);
			if(p.isOpen(i, j)){
				continue;
			}
			p.open(i,j);
			count++;
		}
		return count;
	}
	private void getResult(){
		double[] result=new double[T];
		for(int i=0;i<T;i++){
			result[i]=(double)getNumber()/(double)N/(double)N;
		}
		this.mean=StdStats.mean(result);
		this.stddev=StdStats.stddev(result);
	}
	public double mean(){
		return mean;
	}                      // sample mean of percolation threshold
	public double stddev(){
		
		return stddev;
	}                    // sample standard deviation of percolation threshold
	public double confidenceLo(){
		return mean-1.96*stddev/Math.sqrt(T);
	}              // low  endpoint of 95% confidence interval
	public double confidenceHi(){
		return mean+1.96*stddev/Math.sqrt(T);
	}              // high endpoint of 95% confidence interval

	public static void main(String[] args){
		int N=Integer.parseInt(args[0]);
		int T=Integer.parseInt(args[1]);
		PercolationStats ps=new PercolationStats(N, T);
		System.out.println("mean                    = "+ps.mean);
		System.out.println("stddev                  = "+ps.stddev);
		System.out.println("95% confidence interval = "+ps.confidenceLo()+","+ps.confidenceHi());

	}    // test client (described below)
}