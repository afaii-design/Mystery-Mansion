import javax.swing.JOptionPane; //Import JOptionPane for dialog boxes

 /* Manages the core scenes and logic of the Mystery Mansion adventure game.
    This class handles player interactions, scene transitions, puzzle solving, and endings.
    It delegates high-level flow (ex. restarts) to AdventureManager and uses Puzzle subclasses for challenges.
    Demonstrates composition, user input handling, and modular scene management.*/
class MysteryMansion{
   //Reference to the player object for accessing name, score, and inventory.
   private Player player;
   //Reference to the AdventureManager for controlling game flow, restarts, and attempts.
   private AdventureManager manager;
    
    /**Constructor to initialize the MysteryMansion with an AdventureManager 
      Sets up the player reference via the manager for seamless access.
      @param manager The AdventureManager instance for handling game flow.
      */
      public MysteryMansion(AdventureManager manager) {
            this.manager = manager;
            this.player = manager.getPlayer();
      }
   
          /* Displays the garden intro scene, presenting the initial story and choices.
             Transitions to mansion entry or path search based on user input.
             */
         public void showGardenIntro() {
             JOptionPane.showMessageDialog(null, 
               "You and your family went on a vacation to your grandparents' mansion.\n"     
               + "While wandering around the garden, \nyou notice a cute little white rabbit hopping near the bush.\n"
               + "Curious, you decide to follow it.\n"
               + "You follow the rabbit deeper into the woods...");
             String intro = "The air turns colder, and the sound of your family fades away.\n"
               + "After a while, you stop to catch your breath.\n"
               + "When you look around, the rabbit is gone.\n\n"
               + "Then, as you glance ahead, you see an old mansion.\n"
               + "It looks exactly like your grandparents' mansion,\nbut it's older, darker, and abandoned.\n\n"
               + "What will you do?";
             
             String[] options = {"Enter the mansion", "Look for a way back"};
             Scene gardenIntro = new Scene(intro, options, manager, true);
             int choice = gardenIntro.display();
             if (choice == -1){
                  return; //Handle dialog cancellation to avoid crashes
             }
             String selectedText = gardenIntro.getSelectedOption(choice);
             JOptionPane.showMessageDialog(null, "You chose: " + selectedText);
   
               if (choice == 0) {
                  enterMansionScene(); 
               } else {
                  lookForWayBack();  
               }
         }
            //=============SCENE: MANSION ENTRANCE===================
         private void enterMansionScene() {
            JOptionPane.showMessageDialog(null, "Out of curiosity, you slowly approach the mansion.\n" 
               + "The door creaks open as if it's been waiting for you.\n"
               + "As soon as you step inside, a loud bang echoes through the hall.");    
            String mansionIntro = "You quickly turn around, the door has slammed shut behind you.\n"
               + "You try to open it, but it won't budge.\n"
               + "With no other choice, you begin to explore the mansion.\n"
               + "On a dusty table nearby, you notice a strange-looking book...\n\n"
               + "What will you do?";
          
            String[] options = {"Open the strange book", "Stand by the door"};
            Scene enterMansion = new  Scene(mansionIntro, options, manager, true);
            int choice = enterMansion.display();
            if (choice == -1){
                  return; //Handle dialog cancellation to avoid crashes
             }
            String selectedText = enterMansion.getSelectedOption(choice);
            JOptionPane.showMessageDialog(null, "You chose: " + selectedText);
            
               if (choice == 0) {
                   openStrangeBook();
               } else {
                   standByDoor();               
               }
          }
           
           //===========SCENE: LOOK FOR A WAY BACK===============
          private void lookForWayBack() {
            String thePathWay = "A chill runs down your spine, so you decide to\n" 
               + "return to the path where you had followed the rabbit.\n"
               + "As you walk, the single trail splits into two.\n\n"
               + "The left path is lined with bright flowers,\ngiving it a sunny and inviting feel.\n"
               + "While the right path is dark and shadowy,\n"
               + "covered in an eerie, gloomy atmosphere.\n\n"
               + "Which path will you take?";
       
            String[] paths = {"Right path", "Left path"}; 
            Scene pathWay = new Scene(thePathWay, paths, manager, true);
            int choice = pathWay.display();
            if (choice == -1){
                  return; //Handle dialog cancellation to avoid crashes
             }
            String selectedText = pathWay.getSelectedOption(choice);
            JOptionPane.showMessageDialog(null, "You chose: " + selectedText);
      
            if (choice == 0) {
               rightPath();
            } else {
               leftPath();
            }
         }
   
         private void rightPath() {
            JOptionPane.showMessageDialog(null,
            "You choose the right path, even though its gloomy,\neerie atmosphere sends a chill down your spine.\n" 
            + "The trees loom tall, their shadows stretching like twisted fingers across the trail.\n"
            + "Walking cautiously, you spot the white rabbit again,\n"
            + "quietly munching on a carrot in the path.\n\n"
            + "You approach carefully, unsure if it will run away.\n"
            + "But instead, the rabbit lifts its head… and speaks...");
            JOptionPane.showMessageDialog(null,
            "\"Oh? You didn’t enter the mansion?\" it says with a disappointed sigh.\n"
            + "\"What a shame… I expected you to be more adventurous. But it doesn't matter.\"\n"
            + "The rabbit stands up on its hind legs.\n\n\"I can guide you back to your family.\n"
            + "But only if you answer my question correctly. If you fail…\"\n"
            + "Its crimson eyes glint.\n\"…you’ll find yourself right inside the mansion.\"");
            MultipleChoicePuzzle riddlePuzzle = new MultipleChoicePuzzle(
            "You freeze. A talking rabbit? A threat? A riddle?\n"
            + "You try to say something, but you’re too shocked to speak, so you simply nod.\n"
            + "\"Very well,\" the rabbit says, curling its whiskers into a smile.\n"
            + "\"Here is your riddle…\"\n\n"
            + "Its voice grows deeper.\n"
            + "\"I walk without feet.\n"
            + "I follow you everywhere…\n"
            + "yet I disappear in the dark.\n\n"
            + "What am I?\"", // Question to display
            "A. Your shadow", //Correct answer
            "The rabbit grins wickedly.\n\"Wrong answer, try again!\"", //Feedback on failure
            "The rabbit's eyes glow... you fail and are trapped.", //Final failure text
            new String[]{ "A. Your shadow", "B. A ghost", "C. The wind"} 
            );                 
                  if (riddlePuzzle.attemptSolve()){ //Attempt the puzzle; returns true on success
                     followRabbitPath();
                  } else {
                     manager.restartGame(); //Delegate restart on failure
                  }
         }
   
         private void followRabbitPath(){
            JOptionPane.showMessageDialog(null, 
            "You hold your breath as the rabbit studies your answer.\n"
            + "For a tense moment, it says nothing,\nits crimson eyes fixed on you.\n"
            + "Then its expression softens, a small, satisfied grin appearing.\n"
            + "\"Correct… my dear, " + player.getName() + ".\"\n\n"
            + "Its voice returns to a gentle tone,\nas though the eerie shift never happened.\n"
            + "The rabbit hops aside, clearing the dim trail ahead of you.\n"
            + "\"Follow this path. It will take you straight back to where you belong.\"");
            JOptionPane.showMessageDialog(null,
            "Still shaking, you step forward.\n"
            + "The deeper you go, the brighter the surroundings become.\n"
            + "Soon, the familiar garden behind your grandparents’ mansion appears before you.\n"
            + "Relief washes over you. You turn around and wave.\n\n"
            + "\"Thank you, Mister Rabbit! I… hope we don’t cross paths again.\"\n"
            + "The rabbit tilts its head, giving a playful smile\n"
            + "\"Oh, " + player.getName() + "… We always cross paths with our shadows.\"");
            JOptionPane.showMessageDialog(null,
            "And with a blink, the rabbit fades away,\nas if it were never there.\n"
            + "A strange fog lifts from your mind.\n"
            + "The fear, the forest, the riddle…\nit all feels blurry, distant, dreamlike.\n\n"
            + "You shake your head, dismissing the thought,\nand run toward your family.\n"
            + "Soon, you’re laughing with them by the pool,\n"
            + "completely forgetting the strange creature you encountered in the woods.\n\n"
            + "You didn't enter the mansion...\n"
            + "but you escaped its mystery without even stepping inside.\n"  
            + "The End.");
            player.increaseScore(50); //Award points for successfull riddle escape
            manager.restartGame(); //restart to allow replay or exit

         }
   
         private void leftPath(){
            JOptionPane.showMessageDialog(null,
            "You choose the left path, relieved by its bright and sunny atmosphere.\n"
            + "Maybe this was the better choice, you think.\n"
            + "But after a few minutes of walking, something feels… off.\n"
            + "The warm air grows cold.\n\nThe cheerful chirping of birds disappears,\nreplaced by an uncomfortable silence.\n"
            + "You look around and realize. You’ve been walking in circles. The trees all look the same...");
            JOptionPane.showMessageDialog(null,
            "The path feels endless. And the bright, sunny forest you saw earlier\n"
            + "is now nothing more than a memory swallowed by thick, creeping fog.\n"
            + "You take another step, trying to focus your eyes through the haze.\n\n"
            + "Suddenly, the ground beneath you disappears.\n"
            + "You fall, spiraling downward into darkness.\n"
            + "Your voice echoes, but no one hears you.\n"
            + "No one ever will...");
            gameOver("Sorry, " + player.getName() 
            + ", you didn't successfully find the way back home.\n"
            + "The mansion claims another lost soul.");

        }
      
            //==============SCENE: THE STRANGE BOOK==============
         private void openStrangeBook() { 
            JOptionPane.showMessageDialog(null, 
             "As you open the book, a cloud of dust rises, making you cough.\n"
             + "You wipe the cover clean and flip through the pages.\n"
             + "Inside you find a drawing of a large key,\nand below it, a short paragraph reads:\n\n"
             + "\"If you wish to escape this mansion, you must find this key.\n"
             + "If not... you'll have no choice but to stay forever.\n"
             + "Have fun, my dear " + player.getName() + ". I hope you truly want to escape.\"");
            String msg = "A chill runs down your spine, but you push the fear aside.\n"
             + "Determination fills your heart. You must find that key, \nbecause your loving family is"
             + "waiting for you to come home.\n\n"
             + "As you close the book, a faint melody begins to play from upstairs.\n"
             + "The sound is soft yet haunting, echoing through the empty halls of the mansion.\n"
             + "It feels like the music is calling you...guiding you toward something.\n\n"
             + "What will you do?";
    
            String[] options = {"Search the ground floor for the key", "Follow the haunting music upstairs"}; 
            Scene strangeBookScene = new Scene(msg, options, manager, true);
            int choice = strangeBookScene.display();
            if (choice == -1){
                  return; //Handle dialog cancellation to avoid crashes
             }
             String selectedText = strangeBookScene.getSelectedOption(choice);
             JOptionPane.showMessageDialog(null, "You chose: " + selectedText);
           
            if (choice == 0) {
               searchGroundFloor();
            } else {
               goUpstairs();
            }
         }
           //=======================SCENE: STAND BY THE DOOR ==============
         private void standByDoor() { 
            JOptionPane.showMessageDialog(null,
            "You choose to stand by the door, hoping that your family will save you...\n"
            + "But the mansion remains silent.\n" 
            + "You stand still, quietly gazing around the room.\nCobwebs cover every corner, everything\n"
            + "is covered in dust, and the air feels heavy.\n\n"
            + "Above you, a grand chandelier sways slightly, its chains creaking.\n"
            + "You stare at it for a moment, something feels off.\n"
            + "Before you can react, the chandelier snaps loose and crashes down on you.\n\n");
            gameOver("Sorry, " + player.getName() 
            + ".\nYou were unable to escape the Mystery Mansion:(");
         }
   
         //=======================SCENE: THE GROUND FLOOR============
         private void searchGroundFloor() { 
            String redKey = "You ignore the strange melody and continue searching the ground floor.\n"
            + "While exploring the kitchen, you notice something shiny under the counter.\n"
            + "It's a small red key!\n\n"
            + "What will you do next?";
            //Add the red key to the player's inventory (passive tracking)
            player.addObjects("Red Key");
            player.showCollectedObjects();
      
            String[] options = {"Try using the red key on the front door", "Continue searching the mansion for more clues"}; 
            Scene groundFloorScene = new Scene (redKey, options, manager, true);
            int choice = groundFloorScene.display();
            if (choice == -1){
                  return; //Handle dialog cancellation to avoid crashes
             }
            String selectedText = groundFloorScene.getSelectedOption(choice);
            JOptionPane.showMessageDialog(null, "You chose: " + selectedText);
             
            if (choice == 0) {
               tryFrontDoor();
            } else {
               searchForClues();
            }
         }
            //================SCENE: FRONT DOOR================
         private void tryFrontDoor() { 
            JOptionPane.showMessageDialog(null,
            "You hurry to the front door and try the red key.\n"
            + "But as soon as you hold it up to the lock, you realize it's too small.\n"
            + "Feeling frustrated, you sit on the nearest chair, then suddenly, you hear a loud crack.\n"
            + "The floor gives way beneath you and you fall into the darkness below."); 
            
            gameOver("You crash into the darkness below, unable to see or move.\n"
               + "The mansion creaks above you, as if satisfied with your fall.\n\n"
               + "Sorry, " + player.getName() + ".\n"
               +"You failed to escape the Mystery Mansion:(");
         }
   
            //=================SCENE: SEARCHING FOR MORE CLUES==============
         private void searchForClues() { 
            String msg = "After finding the red key, you consider trying it on the front door.\n"
             + "But then, you remember the strange book you found earlier.\n"
             + "You open it again and notice something new,\na faint, half-blurred word under the drawing:\n"
             + "\"Silver Key.\"\n\n"
             + "You can barely read the second word, but you know it's \"key.\"\n"
             + "As soon as you close the book, the music from upstairs grows louder... and closer.\n\n"
             + "What will you do?";
   
            String[] options = {"Search the ground floor for the silver key", "Follow the haunting music upstairs"};
            Scene findingCluesScene = new Scene(msg, options, manager, true);
            int choice = findingCluesScene.display();
            if (choice == -1){
                  return; //Handle dialog cancellation to avoid crashes
             }
            String selectedText = findingCluesScene.getSelectedOption(choice);
            JOptionPane.showMessageDialog(null, "You chose: " + selectedText);
      
            if (choice == 0) {
               groundFloorTrapScene();
            } else {
               upstairsTrapScene();
            }
         }
   
         //===============SCENE: THE GROUND FLOOR TRAP ==============
         private void groundFloorTrapScene() { 
            JOptionPane.showMessageDialog(null,
            "You pick up the book and walk back into the main hall.\n"
            + "As you pass by the table where the book once lay,\nyou notice a door a few steps ahead.\n"
            + "You approach it, but it has no knob.\n\n"
            + "Beside the door, there's a lever, you pull it without thinking.\n"
            + "Suddenly, arrows shoot out from both sides of the hallway!\n"
            + "You have no time to react.\n"
            + "The arrows strike you before you can even breathe...");
               manager.restartGame();
          }
   
            //=============SCENE: THE UPSTAIRS TRAP ===============
         private void upstairsTrapScene() { 
            JOptionPane.showMessageDialog(null,
            "You take the book and walk toward the staircase where the music is coming from.\n"
            + "Before stepping on the first stair, you notice a note pinned to the wall beside it.\n"
            + "It reads:\n\"Always pull the rope under the handle of the stairs before stepping onto it.\"\n"
            + "You ignore the warning and rush upstairs.\n"
            + "After your third step, your foot gets stuck.\n"
            + "A split second later, a massive boulder drops from above.\n"
            + "Everything goes dark...");
               manager.restartGame();
         }
    
            //===================SCENE: THE HAUNTING MUSIC==================
         private void goUpstairs() { 
            JOptionPane.showMessageDialog(null,
            "You walk toward the staircase leading to the second floor,\nwhere the strange music is coming from.\n"
            + "Before stepping on the first stair,\nyou notice a note pinned to the wall beside it.\n\n"
            + "It reads: \"Always pull the rope under the handle of the stairs before stepping onto it.\"\n"
            + "You didn't ignore the warning, you followed it...");
            String msg = "You pull the rope carefully,\nand the stairs make a faint clicking sound,\nalmost like something was unlocked.\n"
            + "You climb up, one step at a time, until you finally reach the second floor.\n\n"
            + "At the top, the eerie music grows louder.\nYou see a piano playing by itself in the middle of the hallway.\n"
            + "The melody feels haunting, yet it draws you closer.\n"
            + "On each side of the piano, there's a door. One to the left and one to the right.";
      
            String[] options = {"Approach the piano", "Go to the right door", "Go to the left door"};                
            Scene upstairsScene = new Scene(msg, options, manager, true);
            int choice = upstairsScene.display();
            if (choice == -1){
                  return; //Handle dialog cancellation to avoid crashes
             }
            String selectedText = upstairsScene.getSelectedOption(choice);
            JOptionPane.showMessageDialog(null, "You chose: " + selectedText);
               
            if (choice == 0) {
               solvePianoPuzzle(); 
            } else if (choice == 1) {
               enterRightDoor();
            } else {
               leftDoorTrapScene();
            }
         }
               //==============SCENE: THE PUZZLE IN THE PIANO ================
         private void solvePianoPuzzle() {     
            JOptionPane.showMessageDialog(null, 
            "You slowly approach the piano. Suddenly, the music stops.\n"
            + "On the piano's wooden surface,\nyou see letters carved into the top.\nThey read:\n\n"
            + "U N A H T E D\n\n"
            + "You stare at it for a moment, trying to make sense of it...");
            TextPuzzle puzzle = new TextPuzzle ("Then, you hear a faint electronic hum behind you.\n"
               + "When you turn around,\nyou see an old box-type computer sitting on a dusty table.\n\n"
               + "Its screen flickers to life, displaying a message:\n"
               + "\"Figure out what the word is on the piano and enter it below.\n"
               + "Enter your answer in CAPITAL letters.\"", //Question to display
               "HAUNTED", //Correct answer
               "\"Wrong answer...\"\n" 
               + "The computer screen flashes red.\n"
               + "Whispers swirl around you.\n\n"
               + "Try again!", //Feedback on failure
               "\"Wrong answer...\"\n"
               + "The screen turns blood red.\n" 
               + "A chilling whisper echoes through the room:\n\n"
               + "\"You failed,\"" + player.getName() + "...\"\n\n"
               + "Darkness swallows the light around you.\n"
               + "The piano's final note echoes faintly.\n\n"
               + "The End.", //Final failure text
               "Rearrange the letters for something spooky." //optional clue
            );

            if (puzzle.attemptSolve()){ //Attempt the puzzle; returns true on success
               pianoPuzzleSuccess();
            } else {
               manager.restartGame();// delegate restart on failure
            }  
        }
   
         //=============SCENE: AFTER ANSWERING THE PUZZLE IN THE PIANO===========
        private void pianoPuzzleSuccess() { 
            JOptionPane.showMessageDialog(null,
            "You got the correct answer:\nHAUNTED.\n"
            + "Suddenly, the computer screen flickers again.\n"
            + "A green glow flashes across the dusty room,\nand new words appear on the screen:\n\n"
            + "\"Go back to the kitchen. Find the red key... and always bring the book with you.\"");
            JOptionPane.showMessageDialog(null,
            "Before you can react, the screen turns off with a loud click.\n"
            + "Behind you, the piano begins playing again, slower this time.\nAlmost like it's warning you.\n"
            + "Realizing what you must do, you rush downstairs,\n"
            + "grab the book and the red key from the kitchen...");

            MultipleChoicePuzzle keyPuzzle = new MultipleChoicePuzzle(
               "As you turn around, another computer appears before you.\n"
               + "Its screen flickers to life with a new message:\n\n"
               + "\"How many keys are there on a standard piano? Select the correct letter.\"", //Questions to display
               "B. 88", //Correct answer
               "The computer flashes red. Try again!", //Feedback on failure
               "Wrong answer... The mansion claims another lost soul", //Final failure text
               new String[]{"A. 85", "B. 88", "C. 76"});
                                 
               if (keyPuzzle.attemptSolve()) { // Attempt the puzzle; returns true on success
                  escapeMansionTrueEnding();
               } else {
                  manager.restartGame(); //Delegate restart on failure
               }
            
         }
   
         //===============SCENE: ESCAPING THE MANSION================
         private void escapeMansionTrueEnding() {
            JOptionPane.showMessageDialog(null,
            "After selecting the correct answer,\na mysterious elevator appears where the stairs once stood.\n"
            + "Without hesitation, driven by your desire to escape,\nyou step inside.\n\n"
            + "The elevator carries you smoothly to the second floor.\n"
            + "When the doors open, you notice the piano is gone and only one door remains.\n"
            + "You cautiously open the door and find a treasure box in the center of the room.\n"
            + "Next to it lies a note that reads:\n\"Open the book.\"");
            JOptionPane.showMessageDialog(null,
            "You open the book and freeze in surprise.\n" 
            + "The drawing you saw earlier is no longer a sketch.\n"
            + "Its lines now glow with a shimmering silver light.\n\n"
            + "Suddenly, a gust of wind flips the pages,\nand new words appear as if written by an unseen hand:\n\n"
            + "\"Open the treasure box with the red key.\"\n"
            + "You take the red key from your pocket and insert it into the lock.\n"
            + "With a soft click, the box opens,\nrevealing a silver key resting on a velvet cushion.");
               //Add the silver key to the player's inventory
               player.addObjects("Silver Key");
               player.showCollectedObjects();
         
            JOptionPane.showMessageDialog(null, "It glints in the dim light, perhaps the final key to your escape.\n"
            + "Behind the treasure box, you notice a door.\n"
            + "An arrow painted on the wall points toward it,\nwith a small note below that reads:\n\"Enter through me.\"\n\n"
            + "You insert the silver key into the lock and slowly turn it.\n"
            + "The door creaks open and a blinding light floods your vision.\nYou shield your eyes and step forward.\n\n"
            + "When the light fades, you find yourself standing in your grandparents' garden.\n"
            + "The haunted mansion has vanished into thin air, leaving only the sunlight and familiar surroundings...");
            player.increaseScore(150);
        
            JOptionPane.showMessageDialog(null, 
            "Yay! Congratulations " + player.getName() + "\n"
            + "You have successfully escaped the Mystery Mansion!");
            manager.restartGame();
         }
   
         //================SCENE: THE RIGHT DOOR =============
         private void enterRightDoor() { 
            JOptionPane.showMessageDialog(null,
            "Ignoring the piano's haunting melody, you cautiously approach the right door,\n"
            + "feeling a twinge of fear as the music continues to echo behind you.\n"
            + "You slowly turn the knob and open the door.\n"
            + "Inside, you're greeted by a beautiful scenery,\nalmost like your grandparents' garden\n"
            + "bathed in warm sunlight.");
      
            String[] options = {"Step into the room", "Go back and try the left door"};
            Scene rightDoorScene = new Scene("What will you do?", options, manager, true);
            int choice = rightDoorScene.display();
            if (choice == -1){
                  return; //Handle dialog cancellation to avoid crashes
             }
            String selectedText = rightDoorScene.getSelectedOption(choice);
            JOptionPane.showMessageDialog(null, "You chose: " + selectedText);
               
               if (choice == 0) {
                  fallIntoTrapRoom();
               } else {
               enterLeftDoorLibrary();
               }
         }
   
            //========================SCENE: TRAP IN THE RIGHT DOOR===============
         private void fallIntoTrapRoom() { 
            JOptionPane.showMessageDialog(null,
            "You step into the room without a second thought. At the moment, all you can think about\n"
            + "is how much you miss your family, hoping that maybe, just maybe, this is the way home.\n"
            + "As soon as you enter, the door slams shut behind you.\n"
            + "You turn around, but the beautiful garden is gone.\n"
            + "In its place is a dark, endless pit.\n"
            + "The floor beneath your feet begins to crumble, and before you can react,\n"
            + "you fall into the darkness, swallowed whole by the shadows waiting below.");
             gameOver("Sorry, " + player.getName()
             + ". You didn't escape the game:(");
      
         }
   
         //====================SCENE: TRAP IN THE LEFT DOOR================
         private void leftDoorTrapScene() { 
            JOptionPane.showMessageDialog(null,
            "You walk toward the left door and slowly open it.\n"
            + "Inside, there's nothing but darkness.\n"
            + "You take a few cautious steps forward, trying to let your eyes adjust...\n"
            + "But before you can see anything...\n"
            + "The ground suddenly disappears beneath your feet.\n"
            + "You fall.\n"
            + "\"AHhhhhHHHH!\" You scream as the darkness swallows you whole.");
            JOptionPane.showMessageDialog(null,
            "Then, you stop falling.\n"
            + "Slowly, you open your eyes and find yourself standing in front of your own funeral.\n"
            + "Your family is there, crying, grieving, mourning you.\n"
            + "\"No! I'm still alive!!\" you shout, but no one seems to hear you.\n\n"
            + "Your voice echoes into nothing.\n"
            + "You realize the horrible truth.\n"
            + "You entered the old mansion...\n"
            + "And now, you're one of its ghosts.\n\n");
            gameOver("Sorry, " + player.getName() 
            + ". You didn't escape the game:(");
      
         }
   
         //===================SCENE: THE LIBRARY==============
         private void enterLeftDoorLibrary() { 
            JOptionPane.showMessageDialog(null,
            "You think about entering the right room but hesitate, feeling it might be a trap.\n"
            + "You slowly close the door, deciding to look elsewhere for clues.\n"
            + "Still ignoring the piano that continues to play by itself, you turn toward the left door.\n"
            + "Maybe there's something important hidden there.\n"
            + "You open it carefully and find yourself inside a large, dusty library.\n"
            + "In the center of the room, sitting on a pedestal, is that same strange-looking book from earlier.");
      
            String[] options = {"Examine the strange book", "Search the library"};
            Scene libraryScene = new Scene("What will you do?", options, manager, true);
            int choice = libraryScene.display();
            if (choice == -1){
                return; //Handle dialog cancellation to avoid crashes
             }
            String selectedText = libraryScene.getSelectedOption(choice);
            JOptionPane.showMessageDialog(null, "You chose: " + selectedText);
            
               if (choice == 0) {
                  quickEscapeScene();
               } else {
                  solveLibraryPuzzle();
               }
         }
   
            //===============SCENE: THE QUICK ENDING==============
         private void quickEscapeScene() { 
            JOptionPane.showMessageDialog(null,
            "You walk closer to the book and slowly open it.\n"
            + "To your surprise, the drawing of the key you saw earlier is now real,\n"
            + "a large silver key lies inside the pages.\n" 
            + "Beneath it, a single sentence is written:\n"
            + "\"Go to the mansion's front door.\""); 
            player.addObjects("Silver Key"); 
            player.showCollectedObjects(); 
         
            JOptionPane.showMessageDialog(null, 
            "Realizing this might be your way out, you grab the silver key and the book,\n"
            + "then rush downstairs toward the entrance.\n"
            + "Your heart races as you insert the key into the lock.\n"
            + "With a loud click, the door creaks open.\n"
            + "A burst of cold air hits your face and just like that, you've finally escaped the mansion.");
            JOptionPane.showMessageDialog(null,
            "Yay! Congratulations " 
            + player.getName() + "!\n"
            + "You've unlocked one of the endings.");
               player.increaseScore(75); //Award points for successfully escaping the mansion
      
            if (askYesNo("Would you like to play again to discover the other endings?")) {
                manager.startGame(); // Restart to allow replay or exit
            } else {
                JOptionPane.showMessageDialog(null, 
               "Thank you for playing Mystery Mansion, " + player.toString()
               + "\nYou played the game: " + manager.getAttempts() + " time(s).");
               System.exit(0);
            }
         }
   
            //====================SCENE: THE PUZZLE IN THE LIBRARY==============
         private void solveLibraryPuzzle() { 
            TextPuzzle puzzle = new TextPuzzle(
              "You decide to ignore the book and start exploring the library.\n"
               + "To your right, you notice a small wooden table just a few steps away,\nfrom where the strange book rests.\n\n"
               + "On top of the table sits an old box-type computer, covered in dust.\n"
               + "Curious, you walk closer and suddenly, the screen flickers to life.\n"
               + "A glowing message appears:\n\n"
               + "\"Enter what item is drawn in the book. Make sure your answer is in Capital letters.\"", //Question to display
               "KEY", //Correct Answer
               "The screen flashes red, and faint whispers echo around you.\n"
               + "Focus carefully and try again.", //Feedback on failure
               "Wrong answer, " + player.getName() + "...\n"
               + "The mansion claims another lost soul.\n"
               + "The shadows deepen around you, and the room fades into darkness.\n\n"
               + "The End.", //Final failure text
               "It is use to unlock a door." //Optional clue
            );
            if (puzzle.attemptSolve()){ //Attempts the puzzle; return true on success
               escapeMansionLibraryEnding();
            }else {
               manager.restartGame();// delegate restart on failure
            }
         }
         //====================SCENE: LIBRARY ENDING =================
         private void escapeMansionLibraryEnding() {
            JOptionPane.showMessageDialog(null,
            "The computer screen flashes, and suddenly,\na music box appears on the right side of the table.\n"
            + "You slowly open it, and inside lies a blue key.\n"
            + "Then, a faint whisper echoed around you:\n"
            + "\"Open the treasure box with the blue key...\"\n\n"
            + "You glance around and notice that the strange-looking book in the center of the library\n"
            + "has disappeared, in its place now sits a treasure box.\n"
            + "Without hesitation, you grab the blue key and rush to open it.");
            player.addObjects("Blue Key");
            player.showCollectedObjects();
            
            JOptionPane.showMessageDialog(null,
            "As soon as you lift the lid, a mysterious door appears right in front of the treasure box,\n"
            + "it looks exactly like the front door of the mansion.\n"
            + "Inside the treasure box, you find a note and a large silver key.\n\n"
            + "The note reads: \"Open the door using this key.\"\n"
            + "Following the instructions, you insert the silver key into the mysterious door.\n"
            + "The lock clicks and you slowly step through.\n"
            + "Suddenly, everything changes. You're standing inside your bedroom at your grandparents' house.\n"
            + "Behind you, the door fades away, along with the silver key as if it never existed...");
      
            JOptionPane.showMessageDialog(null, 
               "Yay! Congratulations, " 
               + player.getName() + "!\n"
               + "You've unlocked one of the endings!");
               player.increaseScore(95); //Award points for successfully escaping the mansion
     
            if (askYesNo("Would you like to play again to discover the other endings?")) {
                  manager.startGame(); // Restart to allow replay or exit
            } else {
               JOptionPane.showMessageDialog(null, 
               "Thank you for playing Mystery Mansion, " + player.toString()
               + "\nYou played the game: " + manager.getAttempts() + " time(s).");
               System.exit(0);
            }
         }
       
       //==================HELPER METHODS=====================
            /** Displays a confirmation dialog with a yes/no question.
               Used for decisons like restarting the game or confirming actions.
               @param message The question or prompt to display in the dialog.
               @return true if the user selects "Yes", false if "No" or cancels.
               */
            private boolean askYesNo(String message){
            int choice = JOptionPane.showConfirmDialog(
                   null, 
                   message, 
                   "Mystery Mansion", 
                   JOptionPane.YES_NO_OPTION);
            return choice == JOptionPane.YES_OPTION;
   
            }
         //===================FAILED ENDING METHOD==============
         /** Display a game over message and delegates restart to the manager.
             Used for failure endings.
             @param message The custom message to display for the game over scenario.
             */
            private void gameOver(String message){
               JOptionPane.showMessageDialog(null, message + "\n\nThe End.");
               manager.restartGame();
            }
    
}
