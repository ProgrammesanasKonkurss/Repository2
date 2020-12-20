package maze;

// Tieši tas pats kas ir klase "Move", bet šiet mes taisam parvietojumu 
// neparbaudot vai tas ir legali

import java.util.Random;

public class NoCheckMove {

	public static void right(int[][] maze, int[][] position) {
		int r0, k0, r, k, dr, dk;
		r0=position[0][0];
		k0=position[0][1];
		r=position[1][0];
		k=position[1][1];
		dr=r-r0;
		dk=k-k0;
		position[0][0]=r;
		position[0][1]=k;
		position[1][0]=r+dk;
		position[1][1]=k-dr;
	}
	
	public static void forward(int[][] maze, int[][] position) {
		int r0, k0, r, k, dr, dk;
		r0=position[0][0];
		k0=position[0][1];
		r=position[1][0];
		k=position[1][1];
		dr=r-r0;
		dk=k-k0;
		position[0][0]=r;
		position[0][1]=k;
		position[1][0]=r+dr;
		position[1][1]=k+dk;
	}
	
	public static void left(int[][] maze, int[][] position) {
		int r0, k0, r, k, dr, dk;
		r0=position[0][0];
		k0=position[0][1];
		r=position[1][0];
		k=position[1][1];
		dr=r-r0;
		dk=k-k0;
		position[0][0]=r;
		position[0][1]=k;
		position[1][0]=r-dk;
		position[1][1]=k+dr;
	}
	
	public static void back(int[][] maze, int[][] position) {
		int r0, k0, r, k;
		r0=position[0][0];
		k0=position[0][1];
		r=position[1][0];
		k=position[1][1];
		position[0][0]=r;
		position[0][1]=k;
		position[1][0]=r0;
		position[1][1]=k0;
	}
	
	public static void randomOne(int[][] maze, int[][] position) {
		Random ran = new Random();
		int r = ran.nextInt(6)+1;
		if (r==1 || r==2) {
			if (Look.right(maze, position)==1) {
				NoCheckMove.right(maze, position);
			}
			else if (Look.forward(maze, position)==1) {
				NoCheckMove.forward(maze, position);
			}
			else {
				NoCheckMove.left(maze, position);
			}
		}
		else if (r==3) {
			if (Look.forward(maze, position)==1) {
				NoCheckMove.forward(maze, position);
			}
			else if (Look.right(maze, position)==1) {
				NoCheckMove.right(maze, position);
			}
			else {
				NoCheckMove.left(maze, position);
			}
		}
		else if (r==4) {
			if (Look.forward(maze, position)==1) {
				NoCheckMove.forward(maze, position);
			}
			else if (Look.left(maze, position)==1) {
				NoCheckMove.left(maze, position);
			}
			else {
				NoCheckMove.right(maze, position);
			}
		}
		else {
			if (Look.left(maze, position)==1) {
				NoCheckMove.left(maze, position);
			}
			else if (Look.forward(maze, position)==1) {
				NoCheckMove.forward(maze, position);
			}
			else {
				NoCheckMove.right(maze, position);
			}
		}
	}

}
