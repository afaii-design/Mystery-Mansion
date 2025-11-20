import javax.swing.*;// Import JOptionPane for dialog boxes and other Swing components


/* 
 The main entry point for the Mystery Mansion adventure game.
 This class handles the initial set up, including displaying a welcome screen,
 prompting for user input (ex. name), and initializing the game manager.
 It demonstrates basic user interaction and program flow in Java.
 */
public class AdventureGame {

   /* The main method that starts the application.
      It sets up the welcome dialog, handles user choices, and launches the game.*/
    public static void main(String[] args) {
          //Load the mansion image for the welcome screen
         ImageIcon mansionImage = new ImageIcon("mansion.png");
           
           //Create a panel to hold the image and message, arranged vertically
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
           
           //Add the image label, centered horizontally
            JLabel imageLabel = new JLabel(mansionImage);
            imageLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
       
            //Create and add the welcome message label, centered horizontally
            // Uses HTML for formatting (ex. centering and line breaks)
            JLabel messageLabel = new JLabel("<html><center>"
               + "Welcome to Mystery Mansion, where your adventure awaits!<br>" 
               + "Get ready to unlock your potential as you solve puzzles, make choices, and uncover secrets.<br>" 
               + "Will you have what it takes to escape the mansion successfully?<br>" 
               + "Good luck, and enjoy your journey!<br><br>"
               + "Click Start to begin."
               + "</center></html>");
            messageLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
               
               //Add the image and message to the panel
               panel.add(imageLabel);
               panel.add(messageLabel);
            
            //Define options for the dialog: Start or Exit
            Object[] options = {"Start", "Exit"};
            
            //Display the welcome dialog with the panel, image, and message
            //Returns the user's choice (YES_OPTION for start, NO_OPTION for exit)
            int choice = JOptionPane.showOptionDialog(
                     null, //Parent component (null for centered)
                     panel, //Content to display
                     "Mystery Mansion", //Dialog title
                     JOptionPane.YES_NO_OPTION, // Option type (Yes/No buttons)
                     JOptionPane.PLAIN_MESSAGE, // Message type (plain, no icon)
                     null, //Icon? (none)
                     options, //Button options
                     options[0] //Default selection (Start)
            );
               //If the user chooses Exit (not YES_OPTION), end the program
               if(choice != JOptionPane.YES_OPTION){
                  return; //Exit main method, terminating the program
               }
        
               //Prompt the user for their name using an input dialog
               String playerName = JOptionPane.showInputDialog(
                     null,   //Parent component
                     "Before we begin, what is your name?", //Promot message
                     "Mystery Mansion",  //dialog title
                     JOptionPane.QUESTION_MESSAGE //Message type (question icon)
               );
         
                  // If no name is provided or it's empty, use a default name
                  if (playerName == null || playerName.trim().isEmpty()){
                     playerName = "Player"; //Default fallback
                  }
                  //If the user confirmed Start, create the player and game manager, then start the game
                  if (choice == JOptionPane.YES_OPTION){ 
                     
                     //Instantiate the Player with the provided or default name
                     Player player = new Player(playerName);
                     
                     //Create the AdventureManager to handle high-level game flow
                     AdventureManager manager = new AdventureManager(player);
                     
                     //Start the game through the manager
                     manager.startGame();
                  
                  }
                  
    }
}