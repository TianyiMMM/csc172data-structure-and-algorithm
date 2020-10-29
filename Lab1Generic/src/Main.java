/* CSC 172
 * Name: Tianyi Ma
 * NetID: tma8
 * Lab: MW 615-730
 */
public class Main {

	public static void main(String[] args) {
		Integer [] intArry = {1, 2, 3, 4, 5 }; 
		Double [] doubArry = {1.1, 2.2, 3.3, 4.4}; 
		Character [] charArry = {'H','E','L', 'L', 'O'}; 
		String [] strArry = {"once", "upon", "a", "time" }; 
		printarray(intArry); 
		printarray(doubArry); 
		printarray(charArry); 
		printarray(strArry);
		
		System.out.println("max Integer is: " + getMax(intArry)); 
		System.out.println("max Double is: " + getMax(doubArry));
		System.out.println("max Character is: " + getMax(charArry)); 
		System.out.println("max String is: " + getMax(strArry));
	}
	
	/* Q1
	public static void printarray(Object[] object){
		for (Object element : object){
			System.out.print(element + " ");
		}
		System.out.println();
	}
	*/
	
	/* Q2
	public static void printarray(Integer[] arr){
		for (Object element : arr){
			System.out.print(element + " ");
		}
		System.out.println();
	}
	public static void printarray(Double[] arr){
		for (Object element : arr){
			System.out.print(element + " ");
		}
		System.out.println();
	}
	public static void printarray(Character[] arr){
		for (Object element : arr){
			System.out.print(element + " ");
		}
		System.out.println();
	}
	public static void printarray(String[] arr){
		for (Object element : arr){
			System.out.print(element + " ");
		}
		System.out.println();
	}
	*/
	
	// Q3
	public static <E> void printarray(E[] object){
		for (E element : object){
			System.out.print(element + " ");
		}
		System.out.println();
	}
	
	/* Q4
	public static Comparable getMax(Comparable [] arr){
		int arrIndex = 0;
		for (int i = 0; i < arr.length; i++){
			if (arr[i].compareTo(arr[arrIndex])>0){
				arrIndex = i;
			}
		}
		return arr[arrIndex];
	}
	*/
	
	// Q5
	public static <T extends Comparable<T>> T getMax(T [] arr){
		int arrIndex = 0;
		for (int i = 0; i < arr.length; i++){
			if (arr[i].compareTo(arr[arrIndex])>0){
				arrIndex = i;
			}
		}
		return arr[arrIndex];
	}
}
