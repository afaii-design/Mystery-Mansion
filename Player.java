import java.util.*; // Import List and ArrayList classes for managing collections
import javax.swing.JOptionPane; // For showing dialog boxes to the user

/* Represents the player in the MysteryMansion game.
   This class manages the player's name, score, and inventory of collected objects.
   It provides methods to update the player's state and display information via dialogs.
   Demonstrates encapsulation, collections (ArrayList), and user interaction.
   */
   
public class Player {
    //The name of the player, used for personalization in the game.
    private String name;
    //The players current score, accumulated through successful actions.
    private int score;
    //A list of objects collected by the player during the game 
    private List<String> collectedObjects;

    /* Constructor to initialize a new Player with a given name.
      Sets the score to 0 and creates an empty inventory.
      */
    public Player(String name) {
        this.name = name;
        this.score = 0;
        this.collectedObjects = new ArrayList<>(); // ArrayList used as the actual implementation of List
    }
    
    /**Returns the player's name.
      @return the player's name as a String*/
    public String getName() {
        return name;
    }
    
    /*Returns the player's current score.
      @return the player's score as an integer*/
    public int getScore() {
        return score;
    }
    
    /** Increases the players score by a specified amount.
      Used when the player completes successful actions (ex. solving puzzles).
       @param amount The number of points to add to the score.*/
    public void increaseScore(int amount) {
        score += amount;
    }
    
    /** Adds an object to the player's collection if it's not already present.
       Displays a dialog to notify the player of the addition.
       @param obj The name of the object to add(ex. "Red Key")*/
    public void addObjects(String obj) {
        if (!collectedObjects.contains(obj)) {
            collectedObjects.add(obj); 
            JOptionPane.showMessageDialog(
                null,
                obj + " added to your collected objects!\nCurrent items: " + String.join(", ", collectedObjects),
                "Item Collected",
                JOptionPane.INFORMATION_MESSAGE
            );
            
        }         
   }

    /*Clears all objects from the player's collection.
      Typically called when restarting the game.*/
    public void clearCollectedObjects() {
        collectedObjects.clear();
    }
    

    /* Display the player's collected objects in a dialog box.
     Shows a message if the inventory is empty.*/
      public void showCollectedObjects() {
        if (collectedObjects.isEmpty()) {
            JOptionPane.showMessageDialog(null, "You have not collected anything yet.", "Collected Objects", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Collected objects: " + String.join(", ", collectedObjects), "Collected Objects", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
     /** Returns a string representation of the player's information.
        Includes name, final score, and collected objects.
        
        @return A formatted string with player details.
     */
     @Override
     public String toString() { // Returns player info as a string
         return  name + "!\n" + "Final score: " + score + "\nCollected objects: " + collectedObjects;
     
     }
}
