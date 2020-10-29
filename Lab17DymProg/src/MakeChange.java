
public class MakeChange {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] denominations = { 10000, 5000, 2000, 1000, 500, 100, 25, 10, 5, 1 }; 
		int amount = 0;
		if (args.length==1){
			String a = args[0];
			amount = Integer.parseInt(a); // $0.63, or 63 pennies 
		} else if (args.length==0){
			amount = 163;
		}
		int[] change = makeChange(amount, denominations); 
		System.out.print("Change for " + amount + " is {"); 
		for (int i = 0; i< change.length; i++) 
			if (i!=change.length-1)
				System.out.print(change[i] + ", "); 
			else
				System.out.print(change[i]); 
		System.out.println("}");
	}
	
	public static int[] makeChange(int amount, int [] denom){
		int [] count = new int[denom.length+1];
		table = new int[amount+1][];
		recMakeChange(amount, denom, count);
		int [] change = new int[count[count.length-1]];
		int size = denom.length;
		int e = 0;
		for (int i = 0; i < size; i++){
			int num = count[i];
			if (num!=0){
				for (int n = e; n < num+e; n++){
					change[n]=denom[i];
				}
				e+=num;
			}
		}
		return change;
	}
	private static int[][] table;
	public static boolean recMakeChange(int amount, int[] denom, int[] count){
		boolean b = false;
		if(amount==0){
			b=true;
		} else if (amount>0){
		
		int [] tmp = new int[count.length];
		int [] best = new int[count.length];
		best[count.length-1] = amount+1;
		int size = denom.length;
		for (int i = 0; i < size; i++){
			for(int z = 0; z<tmp.length;z++) {
				tmp[z]=0; 
			} 
			if (table[amount] != null) { 
				for (int k = 0; k < table[amount].length; k++)
					count[k] = table[amount][k]; 
				return true; 
				}
			if (recMakeChange(amount - denom[i], denom, tmp)) { 
				
				if (tmp[tmp.length-1] < best[best.length-1]) { 
					tmp[i]++; 
					tmp[tmp.length-1]++; 
					for(int z = 0; z<best.length;z++) 
						best[z] = tmp[z]; 
				} 
				b = true;
		}
		
		if (b) { 
			for(int z = 0; z<count.length;z++) 
				count[z] = best[z]; 
			
			table[amount] = new int[count.length]; 
			for(int z = 0; z < best.length; z++) 
				table[amount][z] = count[z] = best[z]; 
			}
		}
		}
		return b;
	}

}
