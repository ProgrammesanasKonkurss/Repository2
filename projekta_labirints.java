package dip107;

import java.util.Scanner;
import java.util.Random; 

public class projekta_labirints 
{
	static void go(int i, int j, int dir, int step, int lab[][])//labirinta ceļa ģenerēšana
	{
		int i_izm=0, j_izm=0;
		switch(dir) //atkarībā no virziena nosaka koordinātu izmaiņu katram ceļa posmam
		{
			case 1: i_izm=0;  j_izm=1; break;
			case 2: i_izm=1;  j_izm=0; break;
			case 3: i_izm=0;  j_izm=-1; break;
			case 4: i_izm=-1; j_izm=0; break;
		}
		do
		{
			i+=i_izm; //izmaina i koordinātu
			j+=j_izm; //izmaina j koordinātu
			lab[i][j]=0; //izveido ceļu vienā punktā
			step--;
		}while(step!=0); //izveido ceļu noteiktā garumā
		
	}
	
	static int checkSides(int i, int j, int x, int y, int dir, int step, int lab[][])
	//pārbauda, vai potenciālajam labirinta ceļam kāds ceļš neiet paralēli un tieši blakus
	{
		int condition=0, temp=0, i_check_1=0, i_check_2=0, j_check_1=0, j_check_2=0, i_dir=0, j_dir=0;
		if(dir==1)
			{i_check_1=i+1; i_check_2=i-1; j_check_1=j+1; j_check_2=j+1; i_dir=0; j_dir=1;}
		else if(dir==2)
			{i_check_1=i+1; i_check_2=i+1; j_check_1=j+1; j_check_2=j-1; i_dir=1; j_dir=0;}
		else if(dir==3)
			{i_check_1=i+1; i_check_2=i-1; j_check_1=j-1; j_check_2=j-1; i_dir=0; j_dir=-1;}
		else if(dir==4)
			{i_check_1=i-1; i_check_2=i-1; j_check_1=j+1; j_check_2=j-1; i_dir=-1; j_dir=0;}
		//i_check_1, i_check_2, j_check_1 un j_check_2 ir blakus esošo labirinta punktu koordinātas
		//katra virzxiena gadījumā katram ceļam blakus esošie punkti ir savā izkārtojuma
		//piemēram, ja ceļš iet pa labi, tad jāpārbauda punkti virs un zem ceļa
		//ja ceļš iet uz leju, jāpārbauda no ceļa pa labi un pa kreisi esošie punkti 
		do
		{
			//visi if cikli veic pārbaudes un blakus esoša posma gadījumā pieskaita mainīgajam condition vērtību 1
			if(i!=0&&i!=y-1&&j!=0&&j!=x-1)
			{
				if(lab[i_check_1][j_check_1]==0||lab[i_check_2][j_check_2]==0)
				{
					condition++;
				}
			}
			else if((i==0&&j!=x-1)||(i!=y-1&&j==0))
			{
				if(lab[i_check_1][j_check_1]==0)
				{
					condition++;
				}
			}
			else if((i==y-1&&j!=0)||(i!=0&&j==x-1))
			{
				if(lab[i_check_2][j_check_2]==0)
				{
					condition++;
				}
			}
			if(i_check_1==i_check_2)
			{
			i_check_1+=i_dir; i_check_2+=i_dir; i+=i_dir; j+=j_dir;
			}
			if(j_check_1==j_check_2)
			{
			j_check_1+=j_dir; j_check_2+=j_dir;  i+=i_dir; j+=j_dir;
			}//izmaina pārbaudāmo posmu koordinātas
			temp++;
		}
		while(temp!=step); //kamēr nav pārbaudīti visi posmi
		if(condition<1)
			return 1;
		else return 0;
			
}
	static int checkCrossings(int i, int j, int x, int y, int dir, int step, int lab[][])
	{
	//pārbauda vai ceļš nekrusto citu ceļu
		int temp=0, cond_0=0, cond_1=0;
		do
		{
			if(dir==1)
			{
				if(lab[i][j+1]==0)
					{cond_0++;}				
				j++;
			}
			if(dir==2)
			{
				if(lab[i+1][j]==0)
					{cond_0++;}
				i++; 
			}
			if(dir==3)
			{
				if(lab[i][j-1]==0)
					{cond_0++;}
				j--; 
			}
			if(dir==4)
			{
				if(lab[i-1][j]==0)
					{cond_0++;}
				i--; 
			}
			temp++;
		}while(temp!=step); //ja viss ceļš ir tukšs, tad cond_0 vērtība sakritīs ar ceļa garumu
		
		temp=0;
		do
		{
			if(dir==1)
			{
				if(j!=x-1&&lab[i][j+1]==1)
					{cond_1++;j++;}
				else if(j==x-1&&lab[i][j]==1)
					{cond_1++;}	
				
			}
			if(dir==2)
			{
				if(i!=y-1&&lab[i+1][j]==1)
					{cond_1++;i++;}
				else if(i==y-1&&lab[i][j]==1)
					{cond_1++;}	
				 
			}
			if(dir==3)
			{
				if(j!=0&&lab[i][j-1]==1)
					{cond_1++;j--;}
				else if(j==0&&lab[i][j]==1)
					{cond_1++;}
				 
			}
			if(dir==4)
			{
				if(i!=0&&lab[i-1][j]==1)
					{cond_1++;i--;}
				else if(i==0&&lab[i][j]==1)
					{cond_1++;}	
				
			}
			temp++;
		}while(temp!=step+1); //ja ceļš jau eksistē, cond_1 vērtība sakritīs ar ceļa garuma pluss viens vērtību
		if(cond_0==step||cond_1==step+1)
			return 1;
		else return 0;
	}
	static int randomDir(int i, int j, int x, int y)//izvēlas random virzienu ceļa ģenerēšanai
	{
		Random randdir=new Random();
		int dir;
		do
		{
			dir= randdir.nextInt(4)+1;
			if((j==0&&dir==3))
			{
				continue;
			}
			else if((i==0&&dir==4))
			{
				continue;
			}
			else if((j==x-1&&dir==1))
			{
				continue;
			}
			else if((i==y-1&&dir==2))
			{
				continue;
			}
			else break;
		}while(true);
		return dir;
	}
	static int randomStep(int i, int j, int x, int y, int dir)//izvēlas random ceļa garumu
	{
		Random randstep=new Random();
		int step; 
		do
		{	
			step=randstep.nextInt(5)+1;
			if(dir==1&&j+step>x-1) continue;
			if(dir==2&&i+step>y-1) continue;
			if(dir==3&&j-step<0) continue;
			if(dir==4&&i-step<0) continue;
			else break;
		}
		while(true);
		return step;
	}
	
	static void labClear(int x, int y, int lab[][])//notīra labirintu neveiksmīgas ģenerācijas gadījumā
	{
		for(int a=0; a<y; a++)
		{
			for(int b=0; b<x; b++)
			{
				lab[a][b]=1;
			}
		}
		lab[0][0]=0;
	}
	
	static void labPrint(int x, int y, int lab[][])//izvada labirintu
	{
		for(int a=0; a<y+2; a++)
		{
			for(int b=0; b<x+2; b++)
			{
				if(lab[a][b]==0)
					System.out.print("0"+" ");
				else if(lab[a][b]==2)
					System.out.print("2"+" ");
				else
					System.out.print(" "+" ");
			}
			System.out.println();
		}
	}
	static int[][] labCreate(int x, int y, int z)
	{
		int lab[][]=new int[y][x];
		for(int a=0; a<y; a++)
		{
			for(int b=0; b<x; b++)
			{
				lab[a][b]=1;
			}
		}//aizpilda labirintu bez ceļiem (ar 1)
		lab[0][0]=0; //labirinta sākums ir ceļš
		int dir, step, i=0, j=0, cond=0;
		do
		{
			dir=randomDir(i, j, x, y);
			step=randomStep(i, j, x, y, dir);
			if(checkSides(i,j,x,y,dir,step,lab)==1&&z==1&&checkCrossings(i,j,x,y,dir,step,lab)==1||(checkSides(i,j,x,y,dir,step,lab)==1&&z==0))
			{
			go(i,j,dir,step,lab);
			switch(dir)
			{
			case 1: j+=step; break;
			case 2: i+=step; break;
			case 3: j-=step; break;
			case 4: i-=step; break;
			}
			//ja ir iespējams izveidot ceļu, tad to izveido un nosaka i un j pārvietojumu atkarībā no virziena
			}	
			cond++;
			if(cond>x*y)
			{
				labClear(x, y, lab);
				i=0;
				j=0;
				cond=0;
			}//ja labirints ir ieciklējies, to notīra un veido atkārtoti
			
		}//izveido ceļu no labirinta sākumam līdz beigām
		while(lab[y-1][x-1]!=0);
		int count_zar=0, cond_zar=0;
		do
		{
			Random rand=new Random();
			do
			{
				i=rand.nextInt(y);
				j=rand.nextInt(x);
			}while(lab[i][j]==1); //atrod random ceļa punktu
		
			do //cikls, kas veic ceļa zarojumus
			{
				dir=randomDir(i, j, x, y);
				step=randomStep(i, j, x, y, dir);
				if((checkSides(i,j,x,y,dir,step,lab)==1&&z==1&&checkCrossings(i,j,x,y,dir,step,lab)==1)||(checkSides(i,j,x,y,dir,step,lab)==1&&z==0))
					{
					go(i,j,dir,step,lab);
					switch(dir)
					{
						case 1: j+=step; break;
						case 2: i+=step; break;
						case 3: j-=step; break;
						case 4: i-=step; break;
					}
					}
				cond_zar++;
			}while(cond_zar<rand.nextInt((x+y)/2)+3); //cond_zar nosaka viena zarojuma ceļa veidošanas mēģinājumu skaitu
			count_zar++;
		}while(count_zar<6*(x+y));
		int []lab_exp[]=new int[y+2][x+2];
		for(int ii=0; ii<y+2; ii++)
		{
			for(int jj=0; jj<x+2; jj++)
			{
				if(ii==0||jj==0||ii==y+1||jj==x+1)
					lab_exp[ii][jj]=1;
				else lab_exp[ii][jj]=lab[ii-1][jj-1];
			}
		}
		return lab_exp;
	}
	
	static int[][] pathSearch(int x, int y, int lab_exp[][])
	{
		int order[] = new int[(x*y)/2]; //glabā virzienu secību
		int path[][]=new int[2][(x*y)/2]; //glabās ceļa koordinātas
		//cikls apliek labirintu ar "vieninieku rāmi", lai novērstu out of range kļūdas
		//apliek labirintu - jaunā 2d masīvā ievieto labirinta masīvu un rāmi
		int i_exp=1, j_exp=1, int_order=0;
		do //ceļš tiek meklēts ar ciklus
		{
			path[0][int_order]=i_exp;
			path[1][int_order]=j_exp;
			if(lab_exp[i_exp][j_exp+1]==0) //ja pa labi esošā pozīcija ir ceļš tad..
			{
				j_exp++; //izmaina esošo pozīcijas indeksu par 1
				lab_exp[i_exp][j_exp]=1; //nomaina esošo labirinta pozīciju ar sienu
				order[int_order]=1; //ieraksta pārvietošanās virzienu
				int_order++; // izmaina masīva order[] indeksu
				
			} //analogs princips pārējos if nosacījumos
			else if(lab_exp[i_exp+1][j_exp]==0)
			{
				i_exp++;
				lab_exp[i_exp][j_exp]=1;
				order[int_order]=2;
				int_order++;
			}
			else if(lab_exp[i_exp][j_exp-1]==0)
			{
				j_exp--;
				lab_exp[i_exp][j_exp]=1;
				order[int_order]=3;
				int_order++;
			}
			else if(lab_exp[i_exp-1][j_exp]==0)
			{
				i_exp--;
				lab_exp[i_exp][j_exp]=1;
				order[int_order]=4;
				int_order++;
			}
			else //ja ir strupceļš, tad...
			{
				switch(order[int_order-1]) //atkarībā no iepriekšējā pārvietošanas virziena...
				{
				case 1://ja pirms tam bija pārvietojums pa labi, tad..
				{
					j_exp--; //samazina esošās pozīcijas indeksu par 1
					int_order--; //samazina virzienu masīva indeksu par 1
					break;
				}//analogs princips pārēejos gadījumos
				case 2:
				{
					i_exp--;
					int_order--;
					break;
				}
				case 3:
				{
					j_exp++;
					int_order--;
					break;
				}
				case 4:
				{
					i_exp++;
					int_order--;
					break;
				}
				}
			}
			if(i_exp==y&&j_exp==x) //pēdējā ceļa posma saglabāšana
			{
				lab_exp[i_exp][j_exp]=1;
				path[0][int_order]=i_exp;
				path[1][int_order]=j_exp;
			}
		}while(lab_exp[y][x]!=1);
		
		return path;
	}
	public static void main(String[] args)
	{
		int x,y,z;
		Scanner sc =new Scanner(System.in);
		System.out.print("x:");
		x=sc.nextInt();
		System.out.print("y:");
		y=sc.nextInt();
		System.out.print("Vai vēlaties augstu sarežģītību? (1:jā, 0:nē)");
		z=sc.nextInt();
		
		int lab[][]=new int[y+2][x+2];
		lab=labCreate(x, y, z);                //!!!!!!        
		labPrint(x,y,lab);
		int path[][]=new int[2][(x*y)/2];
		path=pathSearch(x,y,lab);
		int q=-1;
		//izvada ceļu koordināšu formātā
		do
		{
			q++;
			System.out.println("["+path[0][q]+";"+path[1][q]+"]");
		}while(path[0][q]!=y||path[1][q]!=x);
}
}