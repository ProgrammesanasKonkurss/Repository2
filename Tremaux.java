package maze;

import java.util.Arrays;
import java.util.Random;

public class Tremaux {
	public int[][] labirints;
	public int[][][] markers;
	public int[][] position;
	boolean right, left, forward, bottom;
	
	
	public Tremaux(int[][] labirints, int[][] position) {
		this.labirints = labirints;
		this.position = position;
		this.markers = new int[labirints.length][labirints[0].length][4];
	}
	
	public int[] LastPosition() {
		return this.position[0];
	}
	
	
	public int[] CurrentPosition() {
		return this.position[1];
	}

	public void PrintMaze() {
		
		for(int i=0;labirints.length>i;i++) {
			for(int j=0;labirints[0].length>j;j++) {
				if(CurrentPosition()[0] == i && CurrentPosition()[1] ==  j) {
					System.out.print("A");	
				}
				 else if(labirints[i][j] == 1) {
					System.out.print("@");
				}
				else if(labirints[i][j] == 0) {
					System.out.print(" ");	
				}
				
			}
			System.out.println(" ");
		}
		System.out.println(" ");
		
	}
	
	public void ChoosePath() {
		while(!EndOfMaze()) {
			right = false;
			left = false;
			forward = false;
			
			if(Look.right(labirints, position) != 1 && this.GetMarkersOnRight() != 2) {
				right= true;
			}
			if(Look.left(labirints, position) != 1 &&  this.GetMarkersOnLeft() != 2 ) {
				left = true;
			}
			
			if( Look.forward(labirints, position) != 1 &&  this.GetMarkersOnForward() != 2) {
				forward = true;
			}
			
			PrintMaze();
			
			if(CheckWaysNumber()>1) {
				MoveOnCrossroad();
			}
			else if (CheckWaysNumber()==1) {
				MoveOnCoridor();
			}	
			else{
				MoveOnDeadEnd();	
			}
		}
	}
	
	
	
	
	public int CheckWaysNumber() {
		int waysCount = 0;
		if(right == true) {
			waysCount++;
		}
		if(left == true) {
			waysCount++;
		}
		
		if( forward == true) {
			waysCount++;
		}
		
		return waysCount;
	}
	
	
	public void MoveOnCoridor() {
		if(right) {
			Move.turnRight(labirints, position);
		}
		if(left) {
			Move.turnLeft(labirints, position);
		}
		
		if(forward) {
			Move.goForward(labirints, position);
		}
	}
	
	
	public void MoveOnDeadEnd() {
		Move.turnBack(labirints, position); 
	}
	
	
	
	public void MoveOnCrossroad() {
		int left, right, forward;
		
		left =  this.GetMarkersOnLeft() + Look.left(labirints, position) * 2;
		right =  this.GetMarkersOnRight() + Look.right(labirints, position) * 2;
		forward = this.GetMarkersOnForward() + Look.forward(labirints, position) * 2;
		
		if(right == 1 && left == 1 && forward == 1 ||
				right == 0 && left==0 && forward == 0) {
			
			Random random = new Random();
			
			int direction=random.nextInt(3);
			
			if(direction == 0) {
				SetMarkersOnBack(GetMarkersOnBack()+1);
				SetMarkersOnRight(this.GetMarkersOnRight()+1);
				Move.turnRight(labirints, position);
			}
			if(direction == 1) {
				SetMarkersOnBack(GetMarkersOnBack()+1);
				SetMarkersOnLeft(GetMarkersOnLeft()+1);
				Move.turnLeft(labirints, position);
			}
			if(direction == 2) {
				SetMarkersOnBack(GetMarkersOnBack()+1);
				SetMarkersOnForward(GetMarkersOnForward()+1);	
				Move.goForward(labirints, position);
			}
			
		}
		
		else {

			if(left <= right && left <= forward) {
				SetMarkersOnBack(GetMarkersOnBack()+1);
				SetMarkersOnLeft(GetMarkersOnLeft()+1);
				Move.turnLeft(labirints, position);
			}
			else if(forward <= right && forward <= left) {
				SetMarkersOnBack(GetMarkersOnBack()+1);
				SetMarkersOnForward(GetMarkersOnForward()+1);		
				Move.goForward(labirints, position);
			}
			else {
				SetMarkersOnBack(GetMarkersOnBack()+1);
				SetMarkersOnRight(this.GetMarkersOnRight()+1);
				Move.turnRight(labirints, position);
			}
			
		}
	}
	
	
	public boolean EndOfMaze() {
		if(CurrentPosition()[0] == labirints.length-2 && CurrentPosition()[1] == labirints[0].length-2) {
			return true;
		}
		return false;
	}

	
	public int GetMarkersOnRight() {
		int direction = (GetFacingDirection() + 1) % 4 ;
		return this.markers[this.CurrentPosition()[0]][this.CurrentPosition()[1]][direction];
	}
	
	public int GetMarkersOnForward() {
		int direction = GetFacingDirection();
		return this.markers[this.CurrentPosition()[0]][this.CurrentPosition()[1]][direction];
	}
	
	public int GetMarkersOnLeft() {
		int direction = (GetFacingDirection() - 1) % 4 ;
		if(direction == -1 ) direction = 3;
		return this.markers[this.CurrentPosition()[0]][this.CurrentPosition()[1]][direction];
	}
	
	public int GetMarkersOnBack() {
		int direction = (GetFacingDirection() - 2) % 4 ;
		if(direction == -1 ) direction = 3;
		if(direction == -2 ) direction = 2;
		return this.markers[this.CurrentPosition()[0]][this.CurrentPosition()[1]][direction];
	}
	
	public void SetMarkersOnRight(int value) {
		int direction = (GetFacingDirection() + 1) % 4 ;
		this.markers[this.CurrentPosition()[0]][this.CurrentPosition()[1]][direction] = value;
	}
	
	public void SetMarkersOnForward(int value) {
		int direction = GetFacingDirection();
		this.markers[this.CurrentPosition()[0]][this.CurrentPosition()[1]][direction]= value;
	}
	
	public void SetMarkersOnLeft(int value) {
		int direction = (GetFacingDirection() - 1) % 4 ;
		if(direction == -1 ) direction = 3;
		this.markers[this.CurrentPosition()[0]][this.CurrentPosition()[1]][direction]= value;
	}
	
	public void SetMarkersOnBack(int value) {
		int direction = (GetFacingDirection() - 2) % 4 ;
		if(direction == -1 ) direction = 3;
		if(direction == -2 ) direction = 2;
		this.markers[this.CurrentPosition()[0]][this.CurrentPosition()[1]][direction]= value;
	}
	
	public int GetFacingDirection() {
		if(this.CurrentPosition()[0] == this.LastPosition()[0] ) {
			if(this.CurrentPosition()[1] < this.LastPosition()[1] ) {
				return 3; // left
			}
			else {
				return 1; // right
			}
		}
		else {
			if(this.CurrentPosition()[0] < this.LastPosition()[0] ) {
				return 0; // top
			}
			else {
				return 2; // bottom
			}
		}
		
	}
}

