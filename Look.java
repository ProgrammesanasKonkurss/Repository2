package maze;

// Tas pats kas ir klase "Move", bet neveic parvietojumu - viekarši atgriež
// vertibu kas ir pa labi/pa kreisi/ priekšaa/ aiz muguras
public class Look {

	public static int right(int[][] maze, int[][] position) {
		int r0, k0, r, k, dr, dk;
		r0=position[0][0];
		k0=position[0][1];
		r=position[1][0];
		k=position[1][1];
		dr=r-r0;
		dk=k-k0;
		return maze[r+dk][k-dr];
	}
	
	public static int forward(int[][] maze, int[][] position) {
		int r0, k0, r, k, dr, dk;
		r0=position[0][0];
		k0=position[0][1];
		r=position[1][0];
		k=position[1][1];
		dr=r-r0;
		dk=k-k0;
		return maze[r+dr][k+dk];
	}
	
	public static int left(int[][] maze, int[][] position) {
		int r0, k0, r, k, dr, dk;
		r0=position[0][0];
		k0=position[0][1];
		r=position[1][0];
		k=position[1][1];
		dr=r-r0;
		dk=k-k0;
		return maze[r-dk][k+dr];
	}
	
	public static int back(int[][] maze, int[][] position) {
		int r0, k0;
		r0=position[0][0];
		k0=position[0][1];
		return maze[r0][k0];
	}

}
