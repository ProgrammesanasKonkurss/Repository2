package maze;

public class Move {
	// r,k - pašreizeja elementa koordinates (rinda, kolonna)
	// r0,k0 - iepriekšeja elementa koordinates
	// dr=r-r0, dk=k-k0 - parvietojums
	// int[][]maze - divdimensiju masivs kuraa glabajas labirints
	// int[][]position - divdimensiju masivs kuraa glabajas pašreizeja un iepriekšeja elementu koordinates  {{r0, k0}, {r, k}};
	// metodes turnRight, goForward, turnLeft un turnBack veic parvietojumu un, ja parvietojums ir iespejams, atgriež vertibu TRUE 
	
	
	public static void rightHand(int[][] maze, int[][] position) {
		if (turnRight(maze, position)) {
			return;
		}
		if (goForward(maze, position)) {
			return;
		}
		if (turnLeft(maze, position)) {
			return;
		}
		if (turnBack(maze, position)) {
			return;
		}
	}
	public static boolean turnRight(int[][] maze, int[][] position) {
		int r0, k0, r, k, dr, dk;
		r0=position[0][0];
		k0=position[0][1];
		r=position[1][0];
		k=position[1][1];
		dr=r-r0;
		dk=k-k0;
		if (maze[r+dk][k-dr]==0) {
			position[0][0]=r;
			position[0][1]=k;
			position[1][0]=r+dk;
			position[1][1]=k-dr;
			return true;
		}
		else {
			return false;
		}
	}
	public static boolean goForward(int[][] maze, int[][] position) {
		int r0, k0, r, k, dr, dk;
		r0=position[0][0];
		k0=position[0][1];
		r=position[1][0];
		k=position[1][1];
		dr=r-r0;
		dk=k-k0;
		if (maze[r+dr][k+dk]==0) {
			position[0][0]=r;
			position[0][1]=k;
			position[1][0]=r+dr;
			position[1][1]=k+dk;
			return true;
		}
		else {
			return false;
		}
	}
	public static boolean turnLeft(int[][] maze, int[][] position) {
		int r0, k0, r, k, dr, dk;
		r0=position[0][0];
		k0=position[0][1];
		r=position[1][0];
		k=position[1][1];
		dr=r-r0;
		dk=k-k0;
		if (maze[r-dk][k+dr]==0) {
			position[0][0]=r;
			position[0][1]=k;
			position[1][0]=r-dk;
			position[1][1]=k+dr;
			return true;
		}
		else {
			return false;
		}
	}
	public static boolean turnBack(int[][] maze, int[][] position) {
		int r0, k0, r, k;
		r0=position[0][0];
		k0=position[0][1];
		r=position[1][0];
		k=position[1][1];
		if (maze[r0][k0]==0) {
			position[0][0]=r;
			position[0][1]=k;
			position[1][0]=r0;
			position[1][1]=k0;
			return true;
		}
		else {
			return false;
		}
	}
}
