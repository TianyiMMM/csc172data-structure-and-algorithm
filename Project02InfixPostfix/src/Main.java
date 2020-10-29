/*CSC 172
 * Author: Tianyi Ma
 * NetID: tma8
 * Lab time: MW 615-730
 * Lab number: Project02InfixPostfix
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.lang.Math;

import Queue.QueueList;
import Stack.StackList;

public class Main {
	public static void main(String[] args) throws IOException{
		textParser();
	}
	public static void textParser() throws IOException {
		
		// create a map of precedence from the highest to the lowest
		HashMap<String, Integer> pcdMap = new HashMap<String, Integer>();
		pcdMap.put("(", 8);
		pcdMap.put(")", 8);
		pcdMap.put("^", 7);
		pcdMap.put("*", 6);
		pcdMap.put("/", 6);
		pcdMap.put("tan", 6);
		pcdMap.put("cos", 6);
		pcdMap.put("sin", 6);
		pcdMap.put("%", 6);
		pcdMap.put("+", 5);
		pcdMap.put("-", 5);
		pcdMap.put(">", 4);
		pcdMap.put("<", 4);
		pcdMap.put("=", 3);
		pcdMap.put("!", 3);
		pcdMap.put("&", 2);
		pcdMap.put("|", 1);
		
		// process each expression in the text file
		File inputFile = new File("infix_expr_short.txt");
		ArrayList<String> results = new ArrayList<String>();
	    try {
		       BufferedReader br = new BufferedReader(new FileReader(inputFile));
		       String inputsText;
		       while ((inputsText = br.readLine()) != null) {
		    	   results.add(infixPostfix(inputsText, pcdMap));
		       }
		       br.close();
		       } catch (Exception e) {
		    	   e.printStackTrace();
		       }
	    textWriter(results);
		} 
	
	public static String infixPostfix(String infix, HashMap<String, Integer> pcdMap) throws IOException{
		QueueList<String> que = new QueueList<String>();
		StackList<String> stack = new StackList<String>();
		int l = infix.length();
		String tempOper = "";
		String tempOperator = "";
		for (int n = 0; n < l; n++){
			
			// if a complete token is inputed or the expression ends
			if (infix.charAt(n) == ' ' || n == l-1){ 
				if (tempOper.equals(")")){
					if (!(stack.peek().equals(")"))){
						stack.push(")");
					}
					stack.pop();
					int size = stack.size();
					for (int x = size-1; x >= 0; x--){ // enqueue operators in the parenthesis
						if (!(stack.peek().equals("("))){
							que.enqueue(stack.peek()); // enqueue token operator within the parenthesis
							stack.pop();
						} else {
							stack.pop(); // pop the "(" and jump out the for loop (stop popping operators in stack)
							x = -1;
						}
					}
					// if the stack is still not empty, pop every items in the stack and enqueue them into queue
					if (n==l-1 && stack != null){
						int len = stack.size();
						for (int x = len-1; x >= 0; x--){
							que.enqueue(stack.peek());
							stack.pop();
						}
					}
				} else if (n==l-1){ // if it's the end of the expression
					if (tempOper.equals("!")){
						tempOper = Character.toString(infix.charAt(n));
					} else if (!(infix.charAt(n)==')')){
						tempOper += infix.charAt(n);
					}
					if (!(tempOper.equals("")))
						que.enqueue(tempOper); // enqueue the last operand
					tempOper = "";
					int size = stack.size();
					for (int x = size-1; x >= 0; x--){ // enqueue and pop every operator left in the stack
						if (!(stack.peek().equals("("))){
							que.enqueue(stack.peek());
						}
						stack.pop();
					}
				} else if (tempOper.equals("+") || tempOper.equals("-") || tempOper.equals("*") ||
						tempOper.equals("/") || tempOper.equals("^") || tempOper.equals("(") || tempOper.equals("<")
						|| tempOper.equals(">") || tempOper.equals("=") || tempOper.equals("&") || tempOper.equals("|") || tempOper.equals("!")
						|| tempOper.equals("%") || tempOper.equals("tan") || tempOper.equals("cos") || tempOper.equals("sin")){
					int size = stack.size();
					for (int x = size-1; x >= 0; x--){
						String temp = stack.peek();
						stack.pop();
						if (stack.peek()==null){
							stack.push(tempOper);
						// if the prior operator doesn't have a lower precedence
						} else if (!(pcdMap.get(temp)-pcdMap.get(stack.peek())>0) || stack.peek().equals("!")){ 
							if (!(stack.peek().equals("("))){
								que.enqueue(stack.peek());
							} else {
								stack.push(tempOper);
								x = -1;
							}
							// if the prior operator has a lower precedence
						} else {
							if (!(infix.charAt(n)==')') && (infix.charAt(n)=='=')){
								que.enqueue(stack.peek());
							}
							stack.push(tempOper);
							x = -1;
						}
					}
					tempOper = "";
					tempOperator = "";
				} else { // enqueue the complete operand
					que.enqueue(tempOper);
					tempOper = "";
				}
				
				
			} else if (infix.charAt(n) == '+' || infix.charAt(n) == '-' || infix.charAt(n) == '*' || // if token is an operator, add token to the stack
					infix.charAt(n) == '/' || infix.charAt(n) == '^' || infix.charAt(n) == '(' || infix.charAt(n) == ')' || infix.charAt(n) == '<'
					|| infix.charAt(n) == '>' || infix.charAt(n) == '=' || infix.charAt(n) == '&' || infix.charAt(n) == '|' || infix.charAt(n) == '!'
					|| infix.charAt(n) == '%'){
				tempOperator += infix.charAt(n);
				tempOper = Character.toString(infix.charAt(n)); // store the most temporary operator
				stack.push(tempOperator);
				tempOperator = ""; // set operator to null to avoid cases like "!(3..."
				
				if (n != l-1 && (infix.charAt(n+1)==')')){ // if there is a double parenthesis, then process this one first
					stack.pop();
					int size = stack.size();
					for (int x = size-1; x >= 0; x--){ // enqueue operators in the parenthesis
						if (!(stack.peek().equals("("))){
							que.enqueue(stack.peek()); // enqueue token operator within the parenthesis
							stack.pop();
						} else {
							stack.pop(); // pop the "(" and jump out the for loop (stop popping operators in stack)
							x = -1;
						}
					}
				}
			} else if (infix.charAt(n) == 't' || infix.charAt(n) == 'a' || infix.charAt(n) == 'n' || infix.charAt(n) == 'c' || 
					infix.charAt(n) == 'o' || infix.charAt(n) == 's' || infix.charAt(n) == 'i'){
				tempOperator += infix.charAt(n);
				if (tempOperator.equals("tan") || tempOperator.equals("cos") || tempOperator.equals("sin")){
					tempOper = tempOperator;
					tempOperator = "";
					if (n != l-1 && infix.charAt(n+1)=='('){
						que.enqueue(tempOper);
						tempOper = "";
					}
				}
				
			} else {
				if (tempOper.equals("!") || tempOper.equals("(")){
					tempOper = "";
				}
				tempOper += infix.charAt(n); // if token is a number, increment until the number is completely inputed (next char is ' ')
				if (n != l-1 && infix.charAt(n+1)==')'){
					que.enqueue(tempOper);
					tempOper = "";
				}
			}
		}

		return calculator(que);
	}
	
	public static String calculator(QueueList<String> que) throws IOException{
		StackList<String> stack = new StackList<String>();
		int size = que.size();

		for (int i = 1; i <= size; i++){
			// start evaluating and popping each token in the queue until the queue is empty
			// if the token is an operator, execute corresponding operation
			
		if (que.peek().equals("+")){
			double b = Double.parseDouble(stack.peek());
			stack.pop();
			double a = Double.parseDouble(stack.peek());
			stack.pop();
			try {
				String c = Double.toString(a+b);
				stack.push(c);
			} catch (ArithmeticException e){
				int s  = stack.size();
				for (int x = 1; x <= s; x++)
					stack.pop();
				stack.push("ArithmeticException: "+e);
				i = 10000000;
			}
		} else if (que.peek().equals("-")){
			double b = Double.parseDouble(stack.peek());
			stack.pop();
			double a = Double.parseDouble(stack.peek());
			stack.pop();
			try {
				String c = Double.toString(a-b);
				stack.push(c);
			} catch (ArithmeticException e){
				int s  = stack.size();
				for (int x = 1; x <= s; x++)
					stack.pop();
				stack.push("ArithmeticException: "+e);
				i = 10000000;
			}
		} else if (que.peek().equals("*")){
			double b = Double.parseDouble(stack.peek());
			stack.pop();
			double a = Double.parseDouble(stack.peek());
			stack.pop();
			try {
				String c = Double.toString(a*b);
				stack.push(c);
			} catch (ArithmeticException e){
				int s  = stack.size();
				for (int x = 1; x <= s; x++)
					stack.pop();
				stack.push("ArithmeticException: "+e);
				i = 10000000;
			}
		} else if (que.peek().equals("/")){
			double b = Double.parseDouble(stack.peek());
			if (b == 0){
				int s  = stack.size();
				for (int x = 1; x <= s; x++)
					stack.pop();
				stack.push("invalid expression: division by zero");
				i = 10000000;
			} else {
				stack.pop();
				double a = Double.parseDouble(stack.peek());
				stack.pop();
				String c = Double.toString(a/b);
				stack.push(c);
			}
		} else if (que.peek().equals("^")){
			double b = Double.parseDouble(stack.peek());
			stack.pop();
			double a = Double.parseDouble(stack.peek());
			stack.pop();
			try {
				String c = Double.toString(Math.pow(a, b));
				stack.push(c);
			} catch (ArithmeticException e){
				int s  = stack.size();
				for (int x = 1; x <= s; x++)
					stack.pop();
				stack.push("ArithmeticException: "+e);
				i = 10000000;
			}
		} else if (que.peek().equals("<")){
			double b = Double.parseDouble(stack.peek());
			stack.pop();
			double a = Double.parseDouble(stack.peek());
			stack.pop();
			try {
				if (a < b){
					stack.push("1.0");
				} else {
					stack.push("0.0");
				}
			} catch (ArithmeticException e){
				int s  = stack.size();
				for (int x = 1; x <= s; x++)
					stack.pop();
				stack.push("ArithmeticException: "+e);
				i = 10000000;
			}
		} else if (que.peek().equals(">")){
			double b = Double.parseDouble(stack.peek());
			stack.pop();
			double a = Double.parseDouble(stack.peek());
			stack.pop();
			try {
				if (a > b){
					stack.push("1.0");
				} else {
					stack.push("0.0");
				}
			} catch (ArithmeticException e){
				int s  = stack.size();
				for (int x = 1; x <= s; x++)
					stack.pop();
				stack.push("ArithmeticException: "+e);
				i = 10000000;
			}
		} else if (que.peek().equals("=")){
			double b = Double.parseDouble(stack.peek());
			stack.pop();
			double a = Double.parseDouble(stack.peek());
			stack.pop();
			try {
				if (a == b){
					stack.push("1.0");
				} else {
					stack.push("0.0");
				}
			} catch (ArithmeticException e){
				int s  = stack.size();
				for (int x = 1; x <= s; x++)
					stack.pop();
				stack.push("ArithmeticException: "+e);
				i = 10000000;
			}
		} else if (que.peek().equals("&")){
			double b = Double.parseDouble(stack.peek());
			stack.pop();
			double a = Double.parseDouble(stack.peek());
			stack.pop();
			try {
				int c = (int)a & (int)b;
				String cstr = Double.toString((double)c);
				stack.push(cstr);
			} catch (ArithmeticException e){
				int s  = stack.size();
				for (int x = 1; x <= s; x++)
					stack.pop();
				stack.push("ArithmeticException: "+e);
				i = 10000000;
			}
		} else if (que.peek().equals("|")){
			double b = Double.parseDouble(stack.peek());
			stack.pop();
			double a = Double.parseDouble(stack.peek());
			stack.pop();
			try {
				int c = (int)a | (int)b;
				String cstr = Double.toString((double)c);
				stack.push(cstr);
			} catch (ArithmeticException e){
				int s  = stack.size();
				for (int x = 1; x <= s; x++)
					stack.pop();
				stack.push("ArithmeticException: "+e);
				i = 10000000;
			}
		} else if (que.peek().equals("!")){
			String a = stack.peek();
			stack.pop();
			if (!(a.equals("1.0")) && !(a.equals("1")) && !(a.equals("0.0")) && !(a.equals("0"))){
				int s  = stack.size();
				for (int x = 1; x <= s; x++)
					stack.pop();
				stack.push("invalid expression: not '1.0' or '0.0' after logical NOT");
				i = 10000000;
			} else {
				if (a.equals("1.0") || a.equals("1")){
					stack.push("0.0");
				} else {
					stack.push("1.0");
				}
			}
		} else if (que.peek().equals("%")){
			double b = Double.parseDouble(stack.peek());
			stack.pop();
			double a = Double.parseDouble(stack.peek());
			stack.pop();
			try {
				String c = Double.toString(a%b);
				stack.push(c);
			} catch (ArithmeticException e){
				int s  = stack.size();
				for (int x = 1; x <= s; x++)
					stack.pop();
				stack.push("ArithmeticException: "+e);
				i = 10000000;
			}
		} else if (que.peek().equals("tan")){
			que.dequeue();
			stack.push(que.peek());
			i++;
			double a = Double.parseDouble(stack.peek());
			stack.pop();
			try {
				String c = Double.toString(Math.tan(Math.toRadians(a)));
				stack.push(c);
			} catch (ArithmeticException e){
				int s  = stack.size();
				for (int x = 1; x <= s; x++)
					stack.pop();
				stack.push("ArithmeticException: "+e);
				i = 10000000;
			}
		} else if (que.peek().equals("cos")){
			que.dequeue();
			stack.push(que.peek());
			i++;
			double a = Double.parseDouble(stack.peek());
			stack.pop();
			try {
				String c = Double.toString(Math.cos(Math.toRadians(a)));
				stack.push(c);
			} catch (ArithmeticException e){
				int s  = stack.size();
				for (int x = 1; x <= s; x++)
					stack.pop();
				stack.push("ArithmeticException: "+e);
				i = 10000000;
			}
		} else if (que.peek().equals("sin")){
			que.dequeue();
			stack.push(que.peek());
			i++;
			double a = Double.parseDouble(stack.peek());
			stack.pop();
			try {
				String c = Double.toString(Math.sin(Math.toRadians(a)));
				stack.push(c);
			} catch (ArithmeticException e){
				int s  = stack.size();
				for (int x = 1; x <= s; x++)
					stack.pop();
				stack.push("ArithmeticException: "+e);
				i = 10000000;
			}
			// if the token is an operand, push it into stack
		} else {
			stack.push(que.peek());
		}
			que.dequeue();

	} // end of the for loop (end of the evaluation for the entire expression)

	return stack.peek();
	}
	
	public static void textWriter(ArrayList<String> results) throws IOException{
		BufferedWriter writer = new BufferedWriter(new FileWriter(
                "my_eval.txt"));
		for (String str : results){
	        writer.write(str);
	        writer.newLine();
		}
        writer.close();
	}
}

