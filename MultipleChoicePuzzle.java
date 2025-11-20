import javax.swing.JOptionPane;
 
 /**
   Represents a multiple-choice puzzle in the Mystery Mansion game.
   Extends the base Puzzle class to handle choice-based questions with retry logic.
   Demonstrates inheritance and polymorphism through overridden methods.
   */

   public class MultipleChoicePuzzle extends Puzzle {
      
      // Array of choice options presented to the player for selection.
      private String[] options; 
      
      /**
        Constructor to initialize a MultipleChoicePuzzle with question details and options.
        @param question The puzzle question text to display.
        @param correctAnswer The correct choice text that must be selected.
        @param wrongMsg Message displayed on incorrect attempts.
        @param failMsg Message displayed after exhausting all attempts.
       @param options Array of available choice options.
        */
      public MultipleChoicePuzzle(String question, String correctAnswer, String wrongMsg, String failMsg, String[] options){
            super(question, correctAnswer, wrongMsg, failMsg);
            this.options = options;
      }
      
      /**
        Attempts to solve the puzzle by displaying the question and options in a dialog.
        Allows up to 3 attempts, with feedback on success/failure and graceful cancellation handling.
        @return true if the puzzle is solved correctly within attempts, false otherwise.
        */
      @Override
      public boolean attemptSolve(){
         JOptionPane.showMessageDialog(null, question);
         boolean solved = false;
         int attempts = 0;
         final int MAX_ATTEMPTS = 3;
         while (!solved && attempts < MAX_ATTEMPTS){
               int choice = JOptionPane.showOptionDialog(
                   null, 
                   question, 
                   "Puzzle", 
                   JOptionPane.DEFAULT_OPTION, 
                   JOptionPane.QUESTION_MESSAGE,
                   null, 
                   options, 
                   options[0]
               );
               
               if (choice == JOptionPane.CLOSED_OPTION){ //Handled dialog cancellation
                     JOptionPane.showMessageDialog(
                          null, 
                          "Puzzle canceled. Returning to game.", 
                          "Canceled", 
                          JOptionPane.WARNING_MESSAGE);
                     return false; //excit gracefully without solving
               }
               
               String selected = options[choice];
               if (isCorrect(selected)){
                  solved = true;
               } else {
                  attempts++;
                  if (attempts < MAX_ATTEMPTS){
                      JOptionPane.showMessageDialog(
                           null, 
                           wrongMsg, 
                           "Incorrect", 
                           JOptionPane.ERROR_MESSAGE);
                  } else{
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
        Displays the available options in a separate dialog for player reference.
        Can be called to show choices before or during puzzle attempts.
        */
      public void displayOptions(){
            JOptionPane.showMessageDialog(null, "Options: " + String.join(", ", options), "Choices", JOptionPane.INFORMATION_MESSAGE);
      }
   }