package dip107;

public class WallFollower {

	public static void start(int[][] labirints) {
		
		int[][] pozicija = {{0,1},{1,1}};
		int[][] path = new int[labirints.length][labirints[0].length];
		int r=labirints.length-2, k=labirints[0].length-2;
		int i, j;
		
		for (i=0; i<r+2; i++) {
			for (j=0; j<k+2; j++) {
				if (labirints[i][j] == 0) {
					path[i][j] = 1;
				}
				else {
					path[i][j] = 3;
				}
			}
		}
		path[1][1] = 0;
		
		while (pozicija[1][0]!=r || pozicija[1][1]!=k) {
			Move.rightHand(labirints, pozicija);
			path[pozicija[1][0]][pozicija[1][1]] = 0;
		}
		
		pozicija[0][0] = r+1;
		pozicija[0][1] = k;
		pozicija[1][0] = r;
		pozicija[1][1] = k;
		path[r][k] = 2;
		while (pozicija[1][0]!=1 || pozicija[1][1]!=1) {
			Move.rightHand(path, pozicija);
			path[pozicija[1][0]][pozicija[1][1]] = 2;
		}
		
		for (i=0; i<r+2; i++) {
			for (j=0; j<k+2; j++) {
				if (path[i][j]==0 || path[i][j]==1) {
					System.out.print("0" + " ");
				}
				else if (path[i][j]==2) {
					System.out.print("+" + " ");
				}
				else {
					System.out.print(" " + " ");
				}
			}
			System.out.println();
		}
	}
}
