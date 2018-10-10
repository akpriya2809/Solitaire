import java.util.ArrayList;
import java.util.Scanner;
import java.util.*;

// Name:Akanksha Priya
// USC NetID:7864867513
// CSCI455 PA2
// Fall 2018


/**
   Simulates the initial piles of cards in Solitaire depending on 
   arguments either as random configuration or from user input
*/

public class BulgarianSolitaireSimulator {
	static Scanner in = new Scanner(System.in);

   public static void main(String[] args) {
     
      boolean singleStep = false;
      boolean userConfig = false;
      Scanner s =new Scanner(System.in);

      for (int i = 0; i < args.length; i++) {
         if (args[i].equals("-u")) {
            userConfig = true;
         }
         else if (args[i].equals("-s")) {
            singleStep = true;
         }
      }
      
      if(singleStep) {
    	  SolitaireBoard sb = new SolitaireBoard(); 
    	  
          playSolitaireRound(sb, s, singleStep);
         
    	   
      }else if(userConfig) {
    	  ArrayList<Integer> solitaireArr = new ArrayList<Integer>();
    	  
    	  System.out.println("Please enter a space-separated list of positive integers followed by newline:");
    	  String input = in.nextLine();
    	  
    	  Scanner out = new Scanner(input).useDelimiter(" ");
    	  
    	  while(out.hasNextInt()) {
    		  solitaireArr.add(out.nextInt());
    	  }
    	  try{
    		  SolitaireBoard sb = new SolitaireBoard(solitaireArr);
    		  playSolitaireRound(sb, s, singleStep);
    	  }catch(AssertionError e) {
    		  System.out.println(e.getMessage());
    		  
    	  }
      }
      
   }
   
   private static void playSolitaireRound(SolitaireBoard solBoard, Scanner in, boolean singleStep) {
	   int round = 0;
	   
	   while(!solBoard.isDone()) {
		   if(singleStep) {
			   System.out.println("");
			   System.out.print("Press enter to continue.....");
		       in.nextLine();  
		   }
		  solBoard.playRound(); 
 		  String currConfig = solBoard.configString(round);
 		  round++;
 	  }
   }

  
}
