#Dingle Mario#

A remake of the popular dingle pop game ... Mario Style!

###Lead Developer: Daniyal Khan###
###Lead Designer: Ryan Nhieu###
###Start Date: April 2010###
###Completion Date: June 14 2010###


Controls on Menu Screen
--------------------------
- Move luigi using left/right arrow keys
- Choose an option with luigi using B button
- Move mario using up/down arrow keys
- Choose an option with mario using A button

Controls In Game
------------------
-  Tilt barrel with arrow right/left arrow
-  Fire with up key
-  Press 1 through 5 to use that item if u have it
-  Fire within 7 seconds, or it will auto fire for you!
-  Press enter for all options i.e. select difficulty and to replay or not


Menu System Capabilities
-------------------------
-  Music (2 kinds)
-  An intro for the game (press 's' to increase/decrease speed)
-  Highscores
-  Whole instruction/controls pages
-  The moving background/ overall menu system
-  Animated sprites
-  Option to view high score

Game Capabilities
---------------------
1. Select your difficulty level
    - Levels get more difficult
    - harder levels have different colour balls
    - each difficulty level has a different background
    - as user destroys more balls, the background becomes uncovered (easter egg)
    - each difficulty level has a different item spawn rate (insane = 10%, hard = 15%, medium = 20%, easy = 25%)
    - Combo system earns more points as difficulty increases and more balls break in a chain
    - each map has different layout
    - each map is harder to win
2. Pausing implemented (press enter in game)
3. Autoshoot function implemented (if u do not fire within 7 seconds, ball will automatically shoot)
    - a bombomb animation will flash and explode if teemr runs out
    - pausing works with timer, ie. the timer stops when paused
4. The balls will pop all balls around it, called a chain pop.
7. Win/death implemented into game
8. Records amount of turns, and outputs it as a sprite
8. Score implemented, outputs as a sprite
    - score is not linear, a whole algorithm as has been defined that calculates score based on combo, difficulty, and number of balls on the field
9. A random generator function has been added
    - random generator is "smart" i.e. it will only give the balls u need once u have 10 or less balls
10. 3 different items have been implemented
    - mario does an animation when u get an item
11. To the right of the score, the word "good," "great," and "excellent" will appear as u combo more balls
12. Win/lose/highscore screen all have different interactive screen
13. Option to play again
    - if not system will exit
14. GUI implemented in high score screen to get name of winner
15. High score records and writes to a file
16. Unique high score method implemented that records final score and number of turns it took to win
17. Ball bounces off balls
18. Shows current ball in barrel and next ball in barrel
19. Randomly generate balls
