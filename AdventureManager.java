import javax.swing.JOptionPane;

/**
   Manages the overall flow and lifecycle of the Mystery Mansion adventure game.
   This class handles game initialization, restarts, attempt tracking, and player management.
   It acts as a central controller, delegating scene-specific logic to MysteryMansion
   while ensuring smooth transitions, data persistence, and user confirmations.
   Demonstrates encapsulation by managing the Player object and game state.
   */
public class AdventureManager{
   private Player player; // Reference to the Player object, containing name, score, and collected items.
   private int attempts; // Counter for the number of game attempts (playthroughs) by the player.
   
      /**
       Constructor to initialize the AdventureManager with a Player instance.
       Sets the initial attempt count to zero, preparing for the first game start.
     
       @param player The Player object to manage throughout the game.
       */
      public AdventureManager(Player player){
         this.player = player;
         this.attempts = 0;
      }
        /*
         Starts a new game session by incrementing the attempt count and launching the first scene.
         Creates a MysteryMansion instance to handle scene progression and user interactions.
         */
      public void startGame(){
         incrementAttempts(); //Track this as a new playthrough
         MysteryMansion mansion = new MysteryMansion(this); //Pass manager for flow control
         mansion.showGardenIntro(); // Launch the initial garden scene
      }
      
      /**
        Handles game restart after a failure or completion.
        Resets the player's inventory, prompts for replay confirmation,
        and either restarts the game or displays final stats and exits.
       */
      public void restartGame() {
         player.clearCollectedObjects(); //Clear all collected items for a fresh start
         //Prompt user for replay decision
         int choice = JOptionPane.showConfirmDialog(
             null,
             "Would you like to play again?",
             "Mystery Mansion",
             JOptionPane.YES_NO_OPTION
         );
          
          
         if (choice == JOptionPane.YES_OPTION){
            startGame(); //Begin a new game session
         } else {
            //Display farewell message with player stats and exit
            JOptionPane.showMessageDialog(
                  null,
                  "Thank you for playing Mystery Mansion, " + player.toString()
                  + "\nYou played the game: " + attempts + " time(s).",
                  "Game Over",
                  JOptionPane.INFORMATION_MESSAGE
            );
            System.exit(0);//Terminate the program gracefully
         }
      
      }
      /*
        Increments the attempt counter by one.
       Called at the start of each new game to track playthroughs.
       */
      public void incrementAttempts(){
            attempts++;
      }
       /**
        Retrieves the current number of game attempts.
      
        @return The total number of times the player has started the game.
        */
      public int getAttempts(){
         return attempts;
      }
      /**
       Retrieves the managed Player object.
       @return The Player instance associated with this manager.
      */
      public Player getPlayer(){
         return player;
      }
}