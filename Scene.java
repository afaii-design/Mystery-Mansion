import javax.swing.JOptionPane;

/**
  Represents a scene in the Mystery Mansion game, handling user interaction via dialogs.
  This class encapsulates the display of narrative text and choice options, with built-in error handling for cancellations.
  It integrates with AdventureManager for flow control, demonstrating encapsulation and user input management.
  */
public class Scene{
   //The narrative text displayed in the scene dialog.
   private String text; 
   // Array of choice op[tions presented to the player.
   private String[] options; 
   // Reference to AdventureManager for managing game flow, such as restarts on cancellations
   private AdventureManager manager; 
   //Flag indicating whether to allow a "back" or restart action on dialog cancellation for better UX.
   private boolean allowBack; 
   
   /** Constructor to initialize a Scene with text, options, manager, and back flag.
      @param text The narrative text to display
      @param options Array of player choice options.
      @param manager Reference to AdventureManager for flow control
      @param allowBack True to enable restart on cancellation; false otherwise.*/
   public Scene (String text, String[] options, AdventureManager manager, boolean allowBack){
      this.text = text;
      this.options = options;
      this.manager = manager;
      this.allowBack = allowBack;
   
   }
   
   /** Display the scene dialog and returns the user's choice index.
       Handles cancellations by calling handleCancellation and returning a special value.
       @return The index of the selected option, or -1 if cancelled.*/
   public int display(){
      int result = JOptionPane.showOptionDialog(null, 
         text, 
         "Mystery Mansion",
         JOptionPane.DEFAULT_OPTION,
         JOptionPane.QUESTION_MESSAGE,
         null,
         options,
         options[0]
       );
      
      //Check for dialog cancellation
      if (result == JOptionPane.CLOSED_OPTION){
         handleCancellation(); //Handle gracefully to avoid crashes
         return -1; //Indicate cancellation to caller
      }
         return result;
   
   }
   /** Retrieves the text of the selected option based on the choice index.
       Validates the index to prevent out-of-bounds errors.
       @param choiceIndex The index of the selected option.
       @return The text of the selected option, or "Invalid choice" if index is out of range.
     */
   public String getSelectedOption(int choiceIndex){
      if (choiceIndex >= 0 && choiceIndex < options.length){
         return options[choiceIndex];
      } else {
         return "Invalid choice";
      }
   }
   
   /* Handles dialog cancellation by showing a warning message.
     Optionally triggers a restart via AdventureManager if allowBack is true.*/
   private void handleCancellation(){
         JOptionPane.showMessageDialog(
            null,
            "Dialog canceled. " + (allowBack ? "Returning to previous scene." : "Game will continue."),
            "Canceled",
            JOptionPane.WARNING_MESSAGE
         );
         if (allowBack && manager != null){
               //Trigger restart to prevents abrupt exits and allow replay
               manager.restartGame(); 
         }
         // If allowback is false, just show message; caller handles retry
   }
   
}
