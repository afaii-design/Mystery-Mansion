import javax.swing.JOptionPane;
   
   /**
    Represents a text-based puzzle in the Mystery Mansion game.
    Extends the base Puzzle class to handle input-based questions with hint support and retry logic.
    Demonstrates inheritance and polymorphism through overridden methods.
    */
   public class TextPuzzle extends Puzzle {
      
      private String hint; // An optional clue that can be displayed to the player upon request.
      
       /**
        Creates a new text-based puzzle with question, answer, messages, and hint.
        @param question The puzzle prompt displayed to the player.
        @param correctAnswer The expected correct answer (case-insensitive).
        @param wrongMsg Message shown when an attempt is incorrect.
        @param failMsg Message shown after exhausting all attempts.
        @param hint An optional hint the player can request by typing "HINT".
        */
      public TextPuzzle(String question, String correctAnswer, String wrongMsg, String failMsg, String hint){
         super(question, correctAnswer, wrongMsg, failMsg);
         this.hint = hint;
      }
      
      /**
        Attempts to solve the puzzle by prompting the player for text input.
        Allows up to 3 attempts, with options to request a hint or submit an answer.
        Input is sanitized and validated for correctness.
        @return true if the puzzle is solved correctly within attempts, false otherwise.
        */
      @Override
      public boolean attemptSolve(){
         // Display the puzzle question to set the context
         JOptionPane.showMessageDialog(null, question);
         
         boolean solved = false;
         int attempts = 0;
         final int MAX_ATTEMPTS = 3;
         
         //Loop until solved or max attempts reached
         while (!solved && attempts < MAX_ATTEMPTS){
               //Prompt for user input, allowing hint requests
               String answer = getPuzzleInput("Enter your answer (or type \'HINT\' for a clue):", "Puzzle");
               
               //Handle hint request without counting as an attempt
               if (answer.equals("HINT")){
                  provideHint();
                  continue;//skip to next iteration
               }
               //Check if the answer is correct
               if (isCorrect(answer)){
                  solved = true;
               } else {
                  attempts++;
                  
                  //Provide feedback based on remaining attempts
                  if (attempts < MAX_ATTEMPTS){
                     JOptionPane.showMessageDialog(
                          null, 
                          wrongMsg, 
                          "Incorrect", 
                          JOptionPane.ERROR_MESSAGE);
                         
                  } else{
                      //All attempts exhausted
                     JOptionPane.showMessageDialog(
                          null, 
                          failMsg, 
                          "Game Over", 
                          JOptionPane.ERROR_MESSAGE);
                  }
               }
          }
          return solved;
      }
      
       /**
        Displays the hint for this puzzle in a dialog.
        Called when the player requests help during puzzle attempts.
        */
      public void provideHint(){
         JOptionPane.showMessageDialog(
               null, 
               "Hint: " + hint, 
               "Hint", 
               JOptionPane.INFORMATION_MESSAGE);
     
      }
   }