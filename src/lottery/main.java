package lottery;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
public class main {

	public static void main(String[] args) throws IOException {
		
		String restart = "Y";
		do{
			Stack player1 = new Stack(13);          //oyuncu 1 kartı
			Stack player2 = new Stack(13);		    //oyuncu 2 kartı
			Stack sortedBag = new Stack(13);	    //oyuncu 1 kartını doldurmak için stack
			Stack sortedBag2 = new Stack(13);		//oyuncu 2 kartını doldurmak için stack
			Queue gameBag = new Queue(13);			//oyun oynama çantası
			Queue emptyGameBag = new Queue(13);		//oyun  için boş çanta					
			Queue HighScoreName = new Queue(12);
			Queue HighScore = new Queue (12);
			Stack temp = new Stack(24);				
			Stack temp2 = new Stack(13);	
			Queue temp3 = new Queue(12);
			Queue temp4 = new Queue(12);
			Queue temp5 = new Queue(12);
			Queue selectedvalue = new Queue(14);
			String [] patates = new String [26];
			
			int random = 0;
			int n;
			int player1Score=0;
			int player2Score=0;
			int roundcounter = 1;
			
			boolean existControl1 = false;
			boolean existControl2 = false;
			boolean endGameCheck = false;
			boolean halfGameCheck = false;
			Scanner input = new Scanner(System.in);
		do{                         										//n değerini alırken harfleri kabul etmemeyi başaramadım çöküyor.
		System.out.print("Please enter a number between 7 and 10 :  ");				
		
		n = input.nextInt();
		
		if (Character.isDigit(n)== true)
		    System.out.print("What is this bro?");		
		}while(n<7 | n>10 | Character.isLetter(n)==true);
		
		sortedBag.push("A");sortedBag.push(2);sortedBag.push(3);sortedBag.push(4);sortedBag.push(5);sortedBag.push(6);sortedBag.push(7) // desteleri dolduruyoruz.
		;sortedBag.push(8);sortedBag.push(9);sortedBag.push(10);sortedBag.push("J");sortedBag.push("Q");sortedBag.push("K");
		
		sortedBag2.push("A");sortedBag2.push(2);sortedBag2.push(3);sortedBag2.push(4);sortedBag2.push(5);sortedBag2.push(6);sortedBag2.push(7)
		;sortedBag2.push(8);sortedBag2.push(9);sortedBag2.push(10);sortedBag2.push("J");sortedBag2.push("Q");sortedBag2.push("K");
		
		gameBag.enqueue("A");gameBag.enqueue(2);gameBag.enqueue(3);gameBag.enqueue(4);gameBag.enqueue(5);gameBag.enqueue(6);gameBag.enqueue(7)
		;gameBag.enqueue(8);gameBag.enqueue(9);gameBag.enqueue(10);gameBag.enqueue("J");gameBag.enqueue("Q");gameBag.enqueue("K");
		
		for(int k = 0; k<2 ; k++ ) {					//oyuncuların kartlarını dolduruyoruz.
			for(int i = 0 ; i < n ; i++ ) {

					Random rastgele = new Random();
					random = rastgele.nextInt(sortedBag.size());
			
				for(int j = 0; j<random +1; j++) {
				
					if(j != random )
					{
						if(k==0)
							temp.push(sortedBag.pop());
						if(k==1)
							temp.push(sortedBag2.pop());
					}		
					
					if(j == random) 
					{			
						if(k==0) {						
							player1.push(sortedBag.pop());

							while(!temp.isEmpty()) 							
							{
								sortedBag.push(temp.pop());			
							}
							
						}
						if(k==1) {							
							player2.push(sortedBag2.pop());
							while(!temp.isEmpty()) 
							{
								sortedBag2.push(temp.pop());								
							}							
						}
						
					}				
											
				}						
			}
		}
		
		int player1Size = n;
		int player2Size = n;
					
		do {                 //Main game loop
		System.out.print("\n\n");			
			Random rastgele = new Random();
			random = rastgele.nextInt(gameBag.size());
					
			for(int j = 0; j<random+1 ; j++) {				// çekilen kart oyuncularda mevcut mu kontrol ediyoruz.
		
				
				if(j!= random)
					temp3.enqueue(gameBag.dequeue());				
				
				if(j == random) {	
					for(int k = 0 ; k<n ; k++)
					{
						
						if (gameBag.peek() == player1.peek()) {
							existControl1 = true;
							player1.pop();
							player1Size =player1Size-1;
						}							
						else
							temp.push(player1.pop());
							
						if (gameBag.peek() == player2.peek()) {
							existControl2 = true;
							player2.pop();
							player2Size = player2Size-1;
						}													
						else
							temp2.push(player2.pop());
						
					}
																						
					selectedvalue.enqueue(gameBag.peek());	
					emptyGameBag.enqueue(gameBag.dequeue());			
				}
				
				
			}			
			while(!gameBag.isEmpty()) 							
			{			
				temp3.enqueue(gameBag.dequeue());
			}									
			while(!temp3.isEmpty()) 							
			{			
				gameBag.enqueue(temp3.dequeue());
			}
			while(!temp2.isEmpty()) 							
			{
				player2.push(temp2.pop());
			}
			while(!temp.isEmpty()) 							
			{
				player1.push(temp.pop());
			}
			
			
			if(existControl1 == true) {		//çekilen kartın bulunma durumuna göre puan ekleme çıkarma
				player1Score =player1Score+10 ;			
				existControl1 = false;
			}			
			else{
				player1Score =player1Score-5 ;
				existControl1 = false;
			}			
			
			if(existControl2 == true){
				player2Score =player2Score+10 ;
				existControl2 = false;
			}		
			else{
				player2Score =player2Score-5 ;
				existControl2 = false;
			}		
													// çoğu yazdırma işlemi burada yapılıyor
			
			System.out.print(roundcounter + "." + "selected value: "+selectedvalue.peek()); 
			selectedvalue.dequeue();
			System.out.print("\nPlayer1:");
			while(!player1.isEmpty()) {
				if(player1.peek() != null)
				System.out.print(player1.peek()+ " ");
				
				temp.push(player1.pop());
				
			}			
			while(!temp.isEmpty()) 							
			{
				player1.push(temp.pop());			
			}
			
			if(player1Size>=4)
				System.out.print("\tScore: "+ player1Score);
			if(player1Size<4)
				System.out.print("\t\tScore: "+ player1Score);
			System.out.print("\tBag1  ");
			
			while(!gameBag.isEmpty()) {
				temp3.enqueue(gameBag.peek());
				System.out.print(gameBag.dequeue()+ " ");
			}			
			while(!temp3.isEmpty()) 							
			{
				gameBag.enqueue(temp3.dequeue());			
			}
									
			System.out.print("\nPlayer2:");
			while(!player2.isEmpty()) {
				if(player2.peek() != null)
					System.out.print(player2.peek()+ " ");
				
					temp2.push(player2.pop());
			}		
			while(!temp2.isEmpty()) 							
			{
				player2.push(temp2.pop());			
			}
			
			if(player2Size>=4)
				System.out.print("\tScore: "+ player2Score);
			if(player2Size<4)
				System.out.print("\t\tScore: "+ player2Score);
			
			System.out.print("\tBag2  ");
			while(!emptyGameBag.isEmpty()) {
				temp3.enqueue(emptyGameBag.peek());
				System.out.print(emptyGameBag.dequeue()+ " ");
			}			
			while(!temp3.isEmpty()) 							
			{
				emptyGameBag.enqueue(temp3.dequeue());			
			}
			
			                 
			if(halfGameCheck == false & (player1Size== n/2 |player2Size== n/2 )) {   // destenin yarısı bittiğinde buraya geliyoruz.
				
				if(player1Size== n/2) 
				{
					player1Score = player1Score+30;					
					halfGameCheck = true;
				}
				if(player2Size== n/2) 
				{
					player2Score = player2Score + 30;
					halfGameCheck = true;
				}			
				System.out.println("\n\nFIRST TOURNAMENT IS COMPLETED");
				
			}			
							
			if(player1Size == 0) {
				player1Score = player1Score +50;
				endGameCheck = true;
			}
			
			else if(player2Size == 0) {
				player2Score = player2Score +50;
				endGameCheck = true;
			}
												
			if(endGameCheck==true) {                                     //oyun sonu uygulamalarımız burada gerçekleşiyor.				
				Scanner input2 = new Scanner(System.in);
				
				System.out.println("\n\n\nGame Over!");
				
				
				if(player1Score > player2Score || player1Score <= player2Score) { 
					if(player1Score > player2Score)
						System.out.println("Winner: Player1 with " + player1Score + " points");
					if(player1Score < player2Score)
						System.out.println("Winner: Player2 with " + player2Score + " points");
					if(player1Score == player2Score)
						System.out.println("Points are equal, there is no winner");
					System.out.print("What is your name: ");
					String winner = input2.next();
				
																		 // dosya okuma işlemi.
					File file = new File("C:\\Users\\yusuf\\eclipse-workspace\\lottery\\src\\lottery\\HighScoreTable.txt");					
					Scanner input1 = new Scanner(file);
					
					  while (input1.hasNextLine()) {
					      String word  = input1.nextLine();					    
					      patates = word.split(" ");	
					      
					      HighScoreName.enqueue(patates[0]);
					      HighScore.enqueue(patates[1]);
					  }
					  
					  
					for(int i = 0 ; i < 12; i++) {         //yazdırma şartlarımız ve high skor tablomuz burada şekilleniyor.
																			 
							String değer = (String)HighScore.peek();
							int reeldeğer =Integer.parseInt(değer);
							
							if(player1Score > player2Score) { 							//eğer player1 kazandıysa
							 	if	(player1Score<reeldeğer)	{
							 		temp5.enqueue(HighScore.dequeue());
							 		temp4.enqueue(HighScoreName.dequeue());
							 	}
							 	
							 	else if(player1Score>reeldeğer) {
							 		
							 		temp4.enqueue(winner);
							 		temp5.enqueue(player1Score);
								 
								 while(!temp5.isFull()) {
									 temp5.enqueue(HighScore.dequeue());
								 	 temp4.enqueue(HighScoreName.dequeue());
								 	 
								 }
								 break;
								 }
							 	
							 	else if(player1Score==reeldeğer) {
							 		temp5.enqueue(HighScore.dequeue());
								 	temp4.enqueue(HighScoreName.dequeue());
							 		temp4.enqueue(winner);
							 		temp5.enqueue(player1Score);
								 
							 			
							 		
								 while(!temp5.isFull()) {
									 temp5.enqueue(HighScore.dequeue());
								 	 temp4.enqueue(HighScoreName.dequeue());
								 	 
								 }
								 break;
								 }
							}
							
							if(player1Score < player2Score) {								//eğer player2 kazandıysa
							 	if	(player2Score<reeldeğer)	{
							 		temp5.enqueue(HighScore.dequeue());
							 		temp4.enqueue(HighScoreName.dequeue());
							 	}
							 	
							 	else if(player2Score>reeldeğer) {
							 		
							 		temp4.enqueue(winner);
							 		temp5.enqueue(player2Score);
								 
								 while(!temp5.isFull()) {
									 temp5.enqueue(HighScore.dequeue());
								 	 temp4.enqueue(HighScoreName.dequeue());
								 	 
								 }
								 break;
								 }
							 	
							 	else if(player2Score==reeldeğer) {
							 		temp5.enqueue(HighScore.dequeue());
								 	temp4.enqueue(HighScoreName.dequeue());
							 		temp4.enqueue(winner);
							 		temp5.enqueue(player2Score);
								 
							 			
							 		
								 while(!temp5.isFull()) {
									 temp5.enqueue(HighScore.dequeue());
								 	 temp4.enqueue(HighScoreName.dequeue());
								 	 
								 }
								 break;
								 }
							}
												
							}
					while(!HighScoreName.isFull()) {
						 HighScoreName.enqueue(temp4.dequeue());
						 HighScore.enqueue(temp5.dequeue());					
					}
				
				}
																					// son olarak dosya yazdırma işlemimiz.
				PrintWriter writer1 = new PrintWriter("C:\\Users\\yusuf\\eclipse-workspace\\lottery\\src\\lottery\\HighScoreTable.txt");
				temp.push(HighScore.dequeue());	  
				temp.push(HighScoreName.dequeue());
	        			
			        while(!HighScoreName.isEmpty() && !HighScore.isEmpty()){
			        	
			        	writer1.print(String.valueOf(HighScoreName.dequeue()));			        	
			        	writer1.print(" " +(HighScore.dequeue())+ "\n");
			        	
			        }
			        
			        writer1.print(String.valueOf(temp.pop()));
			        writer1.print(" " +(temp.pop()));	        	    	
			        
			        writer1.close();					
			        					//kendimi tutamadım ve zihnimde yankılanan sese kulak verip bunu eklemem gerektiğini düşündüm
			        
				System.out.println("If you really want to see the smurfs, you have to be a good boy.");
				System.out.println("If you really want to play again, you have to press 'Y' (don't forget to Caps and Enter).");
				restart = input.next();
							
				}
		
			roundcounter++;
					
		}while(endGameCheck == false);
		}while(restart.equals("Y")) ;
				
	}

}
