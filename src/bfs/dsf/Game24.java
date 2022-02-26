/*
*/


public class Game24 {
    // Back tracing
    boolean res = false;
    final double eps = 0.001;

    public boolean judgePoint24(int[] nums) {
        List<Double> arr = new ArrayList<>();
        for(int n: nums) arr.add((double) n);
        helper(arr);
        return res;
    }

    private void helper(List<Double> arr){
        if(res) return;
        if(arr.size() == 1){
            if(Math.abs(arr.get(0) - 24.0) < eps)
                res = true;
            return;
        }
        for (int i = 0; i < arr.size(); i++) {
            for (int j = 0; j < i; j++) {
                List<Double> next = new ArrayList<>();
                Double p1 = arr.get(i), p2 = arr.get(j);
                next.addAll(Arrays.asList(p1+p2, p1-p2, p2-p1, p1*p2));
                if(Math.abs(p2) > eps)  next.add(p1/p2);
                if(Math.abs(p1) > eps)  next.add(p2/p1);
                
                arr.remove(i);
                arr.remove(j);
                for (Double n: next){
                    arr.add(n);
                    helper(arr);
                    arr.remove(arr.size()-1);
                }
                arr.add(j, p2);
                arr.add(i, p1);
            }
        }
    }

    //////////////DFS
    /*
    Given: (a, b, c, d) - (A tuple of 4)
Generate:
Edited: Thanks to @bellevue

    ((a+b),c,d) ((a-b),c,d) ((b-a),c,d) ((a*b),c,d) ((a/b),c,d) ((b/a),c,d)
    ((a+c),b,d) ................................................................. ((c/a),b,d)
    ((a+d),b,c) ................................................................. ((d/a),b,c)
    (a,(b+c),d) ................................................................. (a,(c/b),d)
    (a,(b+d),d) ................................................................. (a,(d/b),d)
    (a,b,(c+d)) ................................................................. (a,b,(d/c))

There are 36 (6*6) such tuples. Of these, + & - are not order dependent. That is 2+3 = 3+2. But / & - are order dependent. i.e. 2/3 != 3/2. These look like (e,f,g) i.e. a tuple of 3 now.

    Carrying out similar reductions gives 18 (6*3) tuples for each of the above-generated tuples. These now look like (h, i) i.e. a tuple of 2 now.

    Similiar, the final reduction now yields 6 answers (a+b, a-b, a*b, a/b, b-a, b/a) for each of the above-generated tuple.

Thus in total 36x18x6 final values can be generated using the 4 operators and 4 initial values.

Algo: Generate all such answers using dfs method and stop when it's 24.

Catches:

    Use double instead of int
    Be careful about the classical divide by zero error

    */


    enum ops{
	ADD('+'), SUB('-'), MUL('*'), DIV('/');
	char type;
	ops(char c){
		type = c;
	}
	public double doFunc(double a, double b){
		if(type=='+') return a+b;
		if(type=='-') return a-b;
		if(type=='*') return a*b;
		else return a/b;
	}
}
    static ops[] operations;
    public static boolean judgePoint24(int[] nums) {
		operations = ops.values();
		double[] oops = new double[4];
		for(int i=0;i<4;i++)
			oops[i] = 1.0*nums[i];
        return doit(oops,4,new boolean[]{true,true,true,true});
    }
    public static boolean doit(double[] nums,int count,boolean[] use){
    	if(count==1){
    		for(int i=0;i<4;i++)
    			if(use[i] && nums[i]==24.0) return true;
    		return false;
    	}
        for(int i=0;i<4;i++){
        	if(use[i]){
        		for(int j=0;j<4;j++){
        			if(j!=i && use[j]){
        				double a = nums[i];
        				use[j] = false;
        				boolean r = false;
        				for(int ai=0;ai<ops.values().length;ai++){
        					if((operations[ai]==ops.ADD || operations[ai]==ops.MUL) && j<i) continue;
        					if(operations[ai]==ops.DIV && nums[j]==0) continue;
        					nums[i] = operations[ai].doFunc(a, nums[j]);
        					r = r|| doit(nums,count-1,use);
        					if(r) return r;
        				}
        				nums[i] = a;
        				use[j] = true;
        			}
        		}
        	}
        	
        }
        return false;
    }
    //

    public boolean judgePoint24(int[] nums) {
        return f(new double[] {nums[0], nums[1], nums[2], nums[3]});
    }
    
    private boolean f(double[] a) {
        if (a.length == 1) {
            return a[0] == 24;
        }
        for (int i = 0; i < a.length; i++) {
            for (int j = i + 1; j < a.length; j++) {
                double[] b = new double[a.length - 1];
                for (int k = 0, l = 0; k < a.length; k++) {
                    if (k != i && k != j) {
                        b[l++] = a[k];
                    }
                }
                for (double k : compute(a[i], a[j])) {
                    b[a.length - 2] = k;
                    if (f(b)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    private double[] compute(double a, double b) {
        return new double[] {a + b, a - b, b - a, a * b, a / b, b / a};
    }
}