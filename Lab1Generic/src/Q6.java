import java.util.Comparator;

/* CSC 172
 * Author: Tianyi Ma
 * NetID: tma8
 * Lab time: MW 615-730
 * Lab number: Lab1Generic
 */

public class Q6 {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Character [] charArry = {'H','E','L', 'L', 'O'}; 
		String [] strArry = {"ZEBRA", "alligator", "crocodile"};
		String [] strArry2 = {"H", "E", "L", "L", "O"};
		System.out.println("max Character is: " + findMax(charArry, new CaseSensitiveCompare())); 
		System.out.println("max String is: " + findMax(strArry, new CaseInsensitiveCompare())); 
		System.out.println("max String2 is: " + findMax(strArry2, new CaseSensitiveCompare2())); 
	}
	
	public static <AnyType>
	AnyType findMax(AnyType [] arr,
	        Comparator<? super AnyType> cmp){
	 int maxIndex = 0 ;
	 for (int i = 1; i < arr.length;i++)
	   if ( cmp.compare(arr[i], arr[maxIndex]) > 0 )
	       maxIndex = i;
	 return arr[maxIndex];
	}
}

class CaseSensitiveCompare <T extends Comparable<? super T>> implements     
			Comparator<T>{
		public int compare(T lhs,T rhs){
			return lhs.compareTo(rhs);
		}
}

class CaseSensitiveCompare2 implements Comparator<String>{
		public int compare(String lhs, String rhs){
			return lhs.compareTo(rhs);
		}
}

class CaseInsensitiveCompare implements Comparator<String>{
	public int compare(String lhs, String rhs){
		return lhs.compareToIgnoreCase(rhs);
	}
}
