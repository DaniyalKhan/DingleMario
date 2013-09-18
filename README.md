
Lead Developer: Daniyal Khan
Lead Designer: Ryan Nhieu
Start Date: April 2010
Completion Date: June 14 2010


--Controls on Menu Screen--
move luigi and mario with arrow keys
to choose luigi's option, use the B button
to choose mario's option, use the A button.

--Controls In Game--
tilt barrel with arrow right/left arrow
fire with up key
press 1 through 5 to use that item if u have it
fire within 7 seconds, or it will auto fire for you!
press enter for all options i.e. select difficulty and to replay or not


-Menu System Capabilities-
1.Music (2 kinds)
2.An intro for the game (press 's' to increase/decrease speed)
3.Highscores
4.Whole instruction/controls pages
5.The moving background/ overall menu system
6.Animated sprites
7.option to view high score

-Game capabilities-
1.Select your difficulty level
    -Levels get more difficult
    -harder levels have different colour balls
    -each difficulty level has a different background
    -as user destroys more balls, the background becomes uncovered (easter egg)
    -each difficulty level has a different item spawn rate (insane = 10%, hard = 15%, medium = 20%, easy = 25%)
    -Combo system earns more points as difficulty increases and more balls break in a chain
    -each map has different layout
    -each map is harder to win
2.Pausing implemented (press enter in game)
3.autoshoot function implemented (if u do not fire within 7 seconds, ball will automatically shoot)
    -a bombomb animation will flash and explode if teemr runs out
    -pausing works with timer, ie. the timer stops when paused
4.The balls will pop all balls around it, called a chain pop.
7.win/death implemented into game
8.records amount of turns, and outputs it as a sprite
8.score implemented, outputs as a sprite
    -score is not linear, a whole algorithm as has been defined that calculates score based on combo, difficulty, and number of balls on the field
9.a random generator function has been added
    -random generator is "smart" i.e. it will only give the balls u need once u have 10 or less balls
10.3 different items have been implemented
    -mario does an animation when u get an item
11.to the right of the score, the word "good," "great," and "excellent" will appear as u combo more balls
12.win/lose/highscore screen all have different interactive screen
13.option to play again
    -if not system will exit
14.GUI implemented in high score screen to get name of winner
15.high score records and writes to a file
16.unique high score method implemented that records final score and number of turns it took to win
17.ball bounces off balls
18.shows current ball in barrel and next ball in barrel
19.randomly generate balls
