package dip107;
import java.util.Stack;
public class StackMonster {
	
	public static Stack<Integer> copyInteger(Stack<Integer> stack1){
		Stack<Integer> stack2 = new Stack<Integer>();
		Stack<Integer> stack3 = new Stack<Integer>();
		
		while(!stack1.empty()) {
			stack2.push(stack1.pop());
		}
		
		while(!stack2.empty()) {
			stack1.push(stack2.peek());
			stack3.push(stack2.pop());
		}
		
 		return stack3;
	}
	
	public static void copyStack(Stack<Stack<Integer>> stack1, Stack<Stack<Integer>> stack2){
		Stack<Stack<Integer>> stack3 = new Stack<Stack<Integer>>();
		
		while(!stack1.empty()) {
			stack3.push(stack1.pop());
		}
		
		while(!stack3.empty()) {
			stack1.push(stack3.peek());
			stack2.push(stack3.pop());
		}
	}
	
	public static Stack<Integer> roll(Stack<Integer> stack1){
		Stack<Integer> stack2 = new Stack<Integer>();
		
		while(!stack1.empty()) {
			stack2.push(stack1.pop());
		}
		
		return stack2;
	}

	public static void breadthFirst(int[][] labirints) {
		
		Stack<Integer> path1 = new Stack<Integer>();
		Stack<Integer> path2 = new Stack<Integer>();
		Stack<Stack<Integer>> stack1 = new Stack<Stack<Integer>>();
		Stack<Stack<Integer>> stack2 = new Stack<Stack<Integer>>();
		int r=1, k=1;
		int Rlen=labirints.length-2, Klen=labirints[0].length-2;
		
		
		path1.push(k);
		path1.push(r);
		stack1.push(path1);
		labirints[r][k]=2;
		path1 = new Stack<Integer>();
		
		while(!stack1.empty()) {
			
			path1 = stack1.pop();   
			r = path1.pop();
			k = path1.pop();
			path1.push(k);
			path1.push(r);
			
			if (labirints[r-1][k]==0) {
				path2 = copyInteger(path1);
				path2.push(k);
				path2.push(r-1);
				labirints[r-1][k]=2;
				if (r-1==Rlen && k==Klen) {
					break;
				}
				stack2.push(path2);
				path2 = new Stack<Integer>();
			}
			
			if (labirints[r][k+1]==0) {
				path2 = copyInteger(path1);
				path2.push(k+1);
				path2.push(r);
				labirints[r][k+1]=2;
				if (r==Rlen && k+1==Klen) {
					break;
				}
				stack2.push(path2);
				path2 = new Stack<Integer>();
			}
			
			if (labirints[r+1][k]==0) {
				path2 = copyInteger(path1);
				path2.push(k);
				path2.push(r+1);
				labirints[r+1][k]=2;
				if (r+1==Rlen && k==Klen) {
					break;
				}
				stack2.push(path2);
				path2 = new Stack<Integer>();
			}
			
			if (labirints[r][k-1]==0) {
				path2 = copyInteger(path1);
				path2.push(k-1);
				path2.push(r);
				labirints[r][k-1]=2;
				if (r==Rlen && k-1==Klen) {
					break;
				}
				stack2.push(path2);
				path2 = new Stack<Integer>();
			}
			
			copyStack(stack1, stack2);
			
			stack1 = stack2;
			stack2 = new Stack<Stack<Integer>>();
			
		}
		
		path2 = StackMonster.roll(path2);
		
		while(!path2.empty()) {
			k = path2.pop();
			r = path2.pop();
			labirints[r][k] = 3;
		}
		
		
		int i, j;
		for (i=0; i<=Rlen+1; i++) {
			for (j=0; j<=Klen+1; j++) {
				if (labirints[i][j]==3)
					System.out.print("+" + " ");
				else if (labirints[i][j]==2 || labirints[i][j]==0)
					System.out.print("0" + " ");
				else 
					System.out.print(" " + " ");
			}
			System.out.println();
		}
	}

}
