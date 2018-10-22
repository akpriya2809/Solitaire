// Name:Akanksha priya


// USC NetID:7864867513
// CSCI455 PA2
// Fall 2018

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

/*
  class SolitaireBoard
  The board for Bulgarian Solitaire.  You can change what the total number of cards is for the game
  by changing NUM_FINAL_PILES, below.  Don't change CARD_TOTAL directly, because there are only some values
  for CARD_TOTAL that result in a game that terminates.
  (See comments below next to named constant declarations for more details on this.)
*/


public class SolitaireBoard {
   
   public static final int NUM_FINAL_PILES = 9;
   // number of piles in a final configuration
   // (note: if NUM_FINAL_PILES is 9, then CARD_TOTAL below will be 45)
   
   public static final int CARD_TOTAL = NUM_FINAL_PILES * (NUM_FINAL_PILES + 1) / 2;
   // bulgarian solitaire only terminates if CARD_TOTAL is a triangular number.
   // see: http://en.wikipedia.org/wiki/Bulgarian_solitaire for more details
   // the above formula is the closed form for 1 + 2 + 3 + . . . + NUM_FINAL_PILES

   // Note to students: you may not use an ArrayList -- see assgt description for details.
   
   
   /**
      Representation invariant:

     * Sum of the integers in solitaireArr should total to CARD_TOTAL
     * solitaireArr should not contain any negative integers
     * All entries in the array should be integers
     * number of piles (with non zero & non negative values) should be equal to NUM_FINAL_PILES

   */
   
   private int[] solitaireArr = new int [CARD_TOTAL]; //change to private 
   private int sizeOfSolitaireArr = 0;  //change to private 
   Random rand1 = new Random();


  
 
   /**
      Creates a solitaire board with the configuration specified in piles.
      piles has the number of cards in the first pile, then the number of cards in the second pile, etc.
      PRE: piles contains a sequence of positive numbers that sum to SolitaireBoard.CARD_TOTAL
   */
   public SolitaireBoard(ArrayList<Integer> piles){
	   for (int i =0; i<piles.size(); i++) {
		   solitaireArr[i] = piles.get(i);
		   sizeOfSolitaireArr++;
	   }
	   // enclosing in try catch block to handle assertion error
      try{
    	  assert isValidSolitaireBoard();
    	  }catch(AssertionError e) {
    		  throw new AssertionError("ERROR: Each pile must have at least one card and the total number of cards must be:"+ CARD_TOTAL);
    	  
      }
   }
 
   
   /**
      Creates a solitaire board with a random initial configuration.
   */
   public SolitaireBoard() {
	   
	   int sum = CARD_TOTAL;
	   int index = 0;
	   while(sum>0) {
		   int num = rand1.nextInt((sum - 1) + 1) + 1;
		   solitaireArr[index]=num;
		   sum = sum - num;
		   index++;
		   sizeOfSolitaireArr++;
	   }
   }
  
   
   /** 
      Plays one round of Bulgarian solitaire.  Updates the configuration according to the rules
      of Bulgarian solitaire: Takes one card from each pile, and puts them all together in a new pile.
      The old piles that are left will be in the same relative order as before, 
      and the new pile will be at the end.
   */
   public void playRound() {
	   int sum  = 0;
	   int i=0;
	   for(; i <= sizeOfSolitaireArr; i++) {
		   if(i>0 && solitaireArr[i]== 0 && sum>0) {
			   solitaireArr[i] = CARD_TOTAL - sum;
			   sum=0;
			   sizeOfSolitaireArr++;
			   
		   }else if(solitaireArr[i]>1) {
			   solitaireArr[i]-= 1;
			   sum += solitaireArr[i];
			   
		   }else if(solitaireArr[i]==1 ) {
			   solitaireArr[i]= solitaireArr[i]-1;
			   
		   }
	   }
	   pushZerosToEnd();

   }
    /**
    * pushes zeros to end of the solitaire array
    */
    private void pushZerosToEnd() 
   { 
       int count = 0;
       int numOfNonZeroVal = 0;// Count of non-zero elements 
 
       // Traverse the array. If element encountered is 
       // non-zero, then replace the element at index 'count' 
       // with this element 
       for (int i = 0; i < this.sizeOfSolitaireArr; i++) {
    	   if (solitaireArr[i] != 0) {
        	   solitaireArr[count++] = solitaireArr[i]; 
           }
       }
       numOfNonZeroVal = count;
       // Now all non-zero elements have been shifted to 
       // front and 'count' is set as index of first 0. 
       // Make all elements 0 from count to end. 
       while (count < sizeOfSolitaireArr) {
    	   solitaireArr[count++] = 0; 
       }
    	  
       sizeOfSolitaireArr = numOfNonZeroVal;
   } 


   
   /**
      Returns true if the current board is at the end of the game.  That is, there are NUM_FINAL_PILES
      piles that are of sizes 1, 2, 3, . . . , NUM_FINAL_PILES, in any order.
   */
   
   public boolean isDone() {
	  boolean isDone = false; // by default set is Done to false
	  int count = 0;
	  /**
	   * checking if the value of array is one more than index that is 1,2,3,4,5....and so on 
	   */
	 for(int i =0; i<NUM_FINAL_PILES; i++) {
		 if(solitaireArr[i]== i+1 ) {
			 count++;
		 }
	 }
	 //check the count is equal to NUM_FINAL_PILES for sum to be valid CARD_TOTAL
	 if(count == NUM_FINAL_PILES) {
		 isDone = true;
	 }
      return isDone;  
   }

   
   /**
      Returns current board configuration as a string with the format of
      a space-separated list of numbers with no leading or trailing spaces.
      The numbers represent the number of cards in each non-empty pile.
   */
   public String configString(int round) {
	   System.out.println(" "); // introducing new line for clarity of output display
	   System.out.print("After round ["+round+"]: ");
	   for(int i=0; i< solitaireArr.length; i++) {
     	  if(solitaireArr[i]>0) {
     		  System.out.print(solitaireArr[i]+" "); 
     	  }
       }
      return solitaireArr.toString();  
   }
   
   
   /**
      Returns true if the solitaire board data is in a valid state
      (See representation invariant comment for more details.)
   */
   private boolean isValidSolitaireBoard() {
	   boolean isValidSolitaireBoard = true;
	   if(solitaireArr.length != CARD_TOTAL ) {
		   isValidSolitaireBoard = false;
	   }
	   int sum =0;
	   for (int num : solitaireArr) {
		   if(num<0 || num > CARD_TOTAL) isValidSolitaireBoard = false;
		   sum += num;
		}
	   if (sum !=CARD_TOTAL) {
		   isValidSolitaireBoard = false; 
	   }
      return isValidSolitaireBoard;

   }

}
