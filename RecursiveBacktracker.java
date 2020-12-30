package dip107;

//Algoritms absoluti tads pats, kads ir StackBacktrackeram,
//bet sheit tas ir izpildits ar rekursiju
public class RecursiveBacktracker {

	public static int[][] maze(int Rlen, int Klen, boolean exit) {
		int[][] A = new int[Rlen+2][Klen+2];
		int i, j;
		for (i=0; i<=Klen+1; i++) {
			A[0][i]=9;
			A[Rlen+1][i]=9;
		}
		for (i=0; i<=Rlen; i++) {
			A[i][0]=9;
			A[i][Klen+1]=9;
		}
		A[1][2]=1;
		A[2][1]=1;
		int[][] position= {{0, 1},
				           {1, 1}};
		
		RecursiveBacktracker.recursion(A, position);
		
		for (i=0; i<=Rlen+1; i++) {
			for (j=0; j<=Klen+1; j++) {
				if (A[i][j]>1) {
					A[i][j]=1;
				}
			}
		}
		if (exit) {
			A[Rlen][Klen]=0;
		}
		else {
			A[Rlen][Klen]=1;
		}
		
		return A;
	}
	
	public static void AddOnesAround(int[][] maze, int[][] position) {
		int r0, k0, r, k;
		r0 = position[0][0];
		k0 = position[0][1];
		r = position[1][0];
		k = position[1][1];
		if (((r-1) != r0) || (k != k0))
			maze[r-1][k] = maze[r-1][k] + 1;
		if ((r != r0) || ((k+1) != k0))
			maze[r][k+1] = maze[r][k+1] + 1;
		if (((r+1) != r0) || (k != k0))
			maze[r+1][k] = maze[r+1][k] + 1;
		if ((r != r0) || ((k-1) != k0))
			maze[r][k-1] = maze[r][k-1] + 1;
	}
	
	public static void recursion(int[][] maze, int[][] position) {
		if (Look.right(maze, position)==1 || Look.forward(maze, position)==1 || Look.left(maze, position)==1) {
			NoCheckMove.randomOne(maze, position);
			RecursiveBacktracker.AddOnesAround(maze, position);
			maze[position[1][0]][position[1][1]]=0;
		}
		else {
			Move.rightHand(maze, position);
		}
		if (position[1][0] != 1 || position[1][1] != 1) {
			RecursiveBacktracker.recursion(maze, position);
		}
		else {
			return;
		}
	}

}
