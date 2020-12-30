package dip107;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		
		int r, k, m;
		char ans;
		
		System.out.println("Labyrinth generation");
		
		Scanner sc = new Scanner(System.in);
		System.out.print("row count: ");
		r = sc.nextInt();
		
		System.out.print("column count: ");
		k = sc.nextInt();
		int[][] l = new int[r+2][k+2];
		//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		System.out.println("generation method number (1-2):");
		System.out.println("1) Backtracker");
		System.out.println("2) Cikliska genereshana");
		m = sc.nextInt();
		
		switch (m) {
		case 1: 
			sc.nextLine();
			System.out.println("crossroads (y/n)?");
			ans = sc.nextLine().charAt(0);
			if (ans == 'y') {
				l = StackBacktracker.maze(r, k, true);
			}
			else if(ans == 'n') {
				l = StackBacktracker.maze(r, k, false);
			}
			break;
		case 2:
			
			
			sc.nextLine();
			System.out.println("vai velaties augstu grutiibas liimeni (y/n)?");
			ans = sc.nextLine().charAt(0);
			if (ans == 'y') {
				l=projekta_labirints.labCreate(r, k, 1);
			}
			else if(ans == 'n') {
				l=projekta_labirints.labCreate(r, k, 0);  
			}
			
			
			//l=projekta_labirints.labCreate(r, k, 0);                        
			projekta_labirints.labPrint(r,k,l);
			break;
		}
		//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		System.out.println("choose method (1-3):");
		System.out.println("1) Wall follower");
		System.out.println("2) Breadth-first search");
		System.out.println("3) Tremaux");
		System.out.println("4) Cikliska mekleshana");
		m = sc.nextInt();
		switch (m) {
		case 1: 
			WallFollower.start(l);
			break;
		case 2: 
			StackMonster.breadthFirst(l);
			break;
		case 3: 
			int[][] position = {{0,1},{1,1}};
			Tremaux tremaux = new Tremaux(l, position);
			tremaux.ChoosePath();
			break;
		case 4:
			
			int path[][]=new int[2][(r*k)/2];
			path=projekta_labirints.pathSearch(r,k,l);
			int q=0;
			do
			{
				q++;
				System.out.println("["+path[0][q]+";"+path[1][q]+"]");
			}while(path[0][q]!=r||path[1][q]!=k);
		}
		sc.close();
		}

}