package dip107;
import java.util.Stack;     //importe steka biblioteku

public class StackBacktracker {
	//Rlen - labirinta rindu skaits
	//Klen - labirinta kolonnu skaits
	//crossroads - ja true, tad genere labirintu ar vairakiem marshrutiem
	//             ja false, tad genere labirintu ar vienu vienigo marshrutu
	public static int[][] maze(int Rlen, int Klen, boolean crossroads) {
		// mainigo inicializacija
		Stack<Integer> stack = new Stack<Integer>();
		int[][] A = new int[Rlen+2][Klen+2]; //pieskaitam 2, lai labirintam varetu uztaisit ramiti
		int[][] position = {{0,1},
				            {1,1}};
		int i, j, r0=0, k0=1, r=1, k=1;
		//Random ran = new Random();
		
		// labirinta sagatave (pievienojam ramiti no deviniem)
		// apstradajam sakuma poziciju
		A[1][2]=1;
		A[2][1]=1;
		for (i=0; i<=Klen+1; i++) {
			A[0][i]=9;
			A[Rlen+1][i]=9;
		}
		for (i=0; i<=Rlen; i++) {
			A[i][0]=9;
			A[i][Klen+1]=9;
		}
		
		
		// cikls kas genere labirintu
		stack.push(k0);
		stack.push(r0);
		stack.push(k);
		stack.push(r);
		while(!stack.empty()) {
			try {
				position[1][0]=stack.pop();
				position[1][1]=stack.pop();
				position[0][0]=stack.pop();
				position[0][1]=stack.pop();
			}
			catch (Exception ex) {
				break;
			}
			stack.push(position[0][1]);
			stack.push(position[0][0]);
			if (Look.right(A, position)==1 || Look.forward(A, position)==1 || Look.left(A, position)==1) {
				NoCheckMove.randomOne(A, position);
				stack.push(position[0][1]);
				stack.push(position[0][0]);
				stack.push(position[1][1]);
				stack.push(position[1][0]);
				RecursiveBacktracker.AddOnesAround(A, position);
				A[position[1][0]][position[1][1]]=0;
			}
			
		}
		
		// labirinta apstrade - liekus ciparus aizvietojam ar vieniniekiem
		for (i=0; i<=Rlen+1; i++) {
			for (j=0; j<=Klen+1; j++) {
				if (A[i][j]>1) {
					A[i][j]=1;
				}
				if (crossroads==true && A[i][j]==1 && i!=0 && i!=Rlen+1 && j!=0 && j!=Klen+1) {
					if ((A[i-1][j]==0 && A[i+1][j]==0 && A[i][j-1]!=0 && A[i][j+1]!=0) || (A[i][j-1]==0 && A[i][j+1]==0 && A[i-1][j]!=0 && A[i+1][j]!=0)) {
						A[i][j]=0;
					}
				}
			}
		}
		A[Rlen][Klen]=0;
		
		
		//labirinta izvade
		if (Rlen*Klen<=2500) {
			for (i=0; i<=Rlen+1; i++) {
				for (j=0; j<=Klen+1; j++) {
					if (A[i][j]==0) {
						System.out.print(A[i][j] + " ");
					}
					else {
						System.out.print(" " + " ");
					}
				}
				System.out.println();
			}
		}
		
		return A;
		
	}

}
