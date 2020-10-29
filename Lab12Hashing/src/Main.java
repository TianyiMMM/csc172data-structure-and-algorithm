/*CSC 172
 * Author: Tianyi Ma
 * NetID: tma8
 * Lab time: MW 615-730
 * Lab number: Lab12Hashing
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Main {
	
	protected static class hashTable{
		
		public int hash( String key, int tableSize ) { 
			int hashVal = 0;
			for( int i = 0; i < key.length( ); i++ ) 
				hashVal = 37 * hashVal + key.charAt( i );
			hashVal %= tableSize; 
			if( hashVal < 0 ) 
				hashVal += tableSize;
			return hashVal;
		}
		
		String[] table;
		
		public hashTable(int size){
			table = new String[size];
		}
		
		public void insert(String str){
			if (getLoadFactor()>=0.5){
				String[] tmp = new String[getSize()*2];
				for (int i = 0; i < getSize(); i++){
					if (table[i]!=null){
						String strr = table[i];
						int h = hash(strr, tmp.length);
						while (tmp[h]!=null){
							h++;
						}
						tmp[h]=strr;
					}
				}
				table = tmp;
			}
			if (contains(str)==false){
				int h = hash(str, table.length);
				while (table[h]!=null){
					h++;
				}
				table[h]=str;
			}
		}
		
		public int getSize(){
			return table.length;
		}
		
		public double getLoadFactor(){
			double l = getSize();
			double count = getNumElements();
			return count/l;
		}
		
		public int getNumElements(){
			int l = getSize();
			int count = 0;
			for (int i = 0; i < l; i++){
				if (table[i]!=null)
					count++;
			}
			return count;
		}
		
		public boolean contains(String str){
			Boolean check =  false;
			for (int i = 0; i < table.length; i++){
				if (table[i]!=null && table[i].equals(str)){
					check = true;
				}
			}
			return check;
		}
		
		public void print(){
			for (String str : table){
				if (str!=null)
					System.out.print(str+" ");
			}
			System.out.println();
		}
	}
	
	public static void main(String[] arg){
		hashTable table = new hashTable(13);
		table.insert("Tianyi");
		table.insert("Tianyi");
		table.insert("Apocolypse");
		table.insert("Charles");
		table.insert("Xavier");
		table.print();
		System.out.println();
		
		hashTable test = new hashTable(13);
		int count = 0;
		
		File inputFile = new File("Lorem_ipsum.txt");
	    try {
		       BufferedReader br = new BufferedReader(new FileReader(inputFile));
		       String inputsText;
		       while ((inputsText = br.readLine()) != null) {
		    	   String[] words = inputsText.split(" ");
		    	   for (String str : words){
		    		   count++;
		    		   test.insert(str);
		    	   }
		       }
		       br.close();
		       } catch (Exception e) {
		    	   e.printStackTrace();
		       }
	    test.print();
	    System.out.println("the number of unique words: "+test.getNumElements());
	    System.out.println("the total count of words read in: "+count);
	    System.out.println("the final size of your hash table: "+test.getSize());
	}
}
