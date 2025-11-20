import javax.swing.JOptionPane;

/**
  The base Puzzle class for the Mystery Mansion game.
  This abstract class defines a generic puzzle structure with common components:
  a question prompt, correct answer, feedback messages, and failure handling.
  Subclasses (e.g., TextPuzzle, MultipleChoicePuzzle) must implement the attemptSolve() method
  to define specific solving logic, demonstrating inheritance and polymorphism.
   */
public abstract class Puzzle{
   protected String question; // The puzzle question or prompt displayed to the player.
   protected String correctAnswer; // The expected correct answer, compared case-insensitively.
   protected String wrongMsg; // Message shown when the player submits an incorrect answer.
   protected String failMsg; // Message shown if the player exhausts all attemots and fails.
   
   /**
      Constructor that initializes all core puzzle components
       @param question The puzzle question or prompt to display.
       @param correctAnswer The expected correct answer (case-insensitive comparison).
       @param wrongMsg Feedback message for incorrect attempts.
       @param failMsg Final message for complete failure.
      */
   public Puzzle (String question, String correctAnswer, String wrongMsg, String failMsg){
      this.question = question;
      this.correctAnswer = correctAnswer;
      this.wrongMsg = wrongMsg;
      this.failMsg = failMsg;
   }
   
    /**
      Abstract method that subclasses must implement to define puzzle-solving logic.
      Handles user interaction, attempts, and success/failure determination.
     
      @return true if the player solves the puzzle successfully, false otherwise.
      */
   public abstract boolean attemptSolve(); 
  
   /**
       Checks if the user's answer matches the expected correct answer.
       Comparison is case-insensitive and trims whitespace for robustness.
       
       @param answer The user's submitted answer.
       @return true if the answer is correct, false otherwise.
     */
   public boolean isCorrect(String answer){
      return answer != null && answer.trim().toUpperCase().equals(correctAnswer.toUpperCase());
   }
   
   /**
       Helper method for displaying a safe input dialog with validation.
       Prevents empty inputs, handles cancellations, and sanitizes responses.
       Used by subclasses for consistent user input handling.
      
       @param message The prompt or question to display in the dialog.
       @param title The dialog window title.
       @return The sanitized user input (trimmed and uppercased), or an empty string if canceled.
       */
      protected String getPuzzleInput(String message, String title){
         String input = "";
         
         //Loop until valid input is provided
         while (input == null || input.trim().isEmpty()){
         
            input = JOptionPane.showInputDialog(null, message, title, JOptionPane.QUESTION_MESSAGE);
            if (input == null){
                  //handle dialog cancellation gracefully
                  JOptionPane.showMessageDialog(null, "Input canceled. Returning to game.", "Canceled", JOptionPane.WARNING_MESSAGE);
                  return ""; //Signal cancellation to caller
            }
            //Handle empty input submission
            if(input.trim().isEmpty()){
               JOptionPane.showConfirmDialog(null, "You didn't enter an answer. Try again.", "No Answer", JOptionPane.WARNING_MESSAGE);
               input = ""; //Reset for retry
            }
         
         }
         return input.trim().toUpperCase(); //return cleaned, standardized input
   }

}
  