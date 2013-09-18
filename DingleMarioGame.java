/*  PRESENTING........ THE DINGLE MARIO GAME!!! aka dingle pop remake
Daniyal Khan
Ryan Nhieu
June 14 2010
Summative Game Assignment


--Controls on Menu Screen--
move luigi and mario with arrow keys
to choose luigi's option, use the B button
to choose mario's option, use the A button.

--Controls In Game--
tilt barell with arrow right/left arrow
fire with up key
press 1 throguh 5 to use that item if u have it
fire within 7 seconds, or it will auto fire for you!
press enetr for all options i.e. select diffulty and to replay or not


--Program Capabilities--
A lot of extra options have been implemnted into this program

-Menu System Capabilities-
1.Music (2 kinds)
2.An intro for the game
    -*********************************** to increase/decrease speed of game intro press s *************************************88
3.Highscores
4.Whole instruction/controls pages
5.The moving background/ overall menu system
6.Animated sprites
7.option to view high score

-Game capabilities-
1.Select yoru diffulty level
    -Levels get more difficult
    -harder levels have different colour balls
    -each difficulty level has a different background
    -as user destroys more balls, the background becomes uncovered (easter egg)
    -each difficulty level has a differrent item spawn rate (insane = 10%, hard = 15%, medium = 20%, easy = 25%)
    -Combo system earns mroe poitns as difficulty increases and more balls break in a chain
    -each map has differnt layout
    -each map is harder to win
2.Pausing implemented (press enter in game)
3.autoshoot function implemented (if u do not fire within 7 seconds, ball will automatically shoot)
    -a bobomb animation will flash and explode if tiemr runs out
    -pausing works with timer, ie.i timer stops when paused
4.The balls will pop all balls around it, called a chain pop.
7.win/death implemented into game
8.records amount of turns, and outputs it as a sprite
8.score implemented, outputs as a sprite
    -score is nto linear, a whoel algorith as has been defined that calucaltes score based on combo, difficulty, and numebr of balls on the field
9.a random generator function has been added
    -random generator is "smart" i.e. it will onyl give the balls u need once u have 10 or less balls
10.3 differnt items have been implemented
    -mario does an animation when u get an item
11.to the right of the score, the word "good," "great," and "excellent" will appear as u combo more balls
12.win/lose/highscore screen all have differnt interactive screen
13.option to play again
    -if not system will exit
14.GUI implmented in high score screen to get name of winner
15.high score records and writes to a file
16.unique high score method implemented thatr ecords final score and number of turns it took to win
17.ball boucnes off balls
18.shows current ball in barell and next ball in barell
19.randomly generate balls

-Things to improve-

1.firing mechanism is nto perfect, since we are using calculate values
2. very rarely the ball will pop a random ball on the field, because we defined the whole field as one row, but it will pop a ball that it thinks is beside it but in reality is on a differnt row
3. chain pop pops a hexagon around it, then a hexagon aroudn each ball it comes into contact with, and thats it cannto chain more than, since we hard coded each chainpop
    -however that is all tahst needed sinc eit is impossible to create a combinatiosn of balls on the field taht will not pop, so it is not a proble
4. very rarely will the items malfunction, however for exmaple the mushroom will not change to colour of a ball to teh upepr right hand corner of teh new ball
5. sometimes music will not play, due to ram issues or whatnot, just wait a sec and keep trying

the logic and thiknig of up of how teh game works was equally divided between both people
equal amoutnof work between both people
gotta give ryan to the sick graphics though, that was all him.

*/




// The "Dingle_Mario_Game" class.
import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.awt.image.*;
import java.io.*;
import java.util.*;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import sun.audio.*;

public class DingleMarioGame extends Applet implements Runnable, ActionListener
{
    // Place instance variables here
    public static final Image GAME_PICS[] [] = new Image [100] [100]; //stores all pictures here
    public static final int BALL_GRID[] [] = new int [114] [4]; //ball grid defines where balls will be on the field
    public static final int ITEM_GRID[] [] = new int [5] [2]; //same things bove with items
    public static final int BALL_COLOUR_QUANTITY[] [] = new int [7] [2]; //variable used with random ball generatorto coutn colour of each ball on field
    public static final int PLAYER_ROW = 10; // numebr of players in the highscore file
    public static final int PLAYER_COLUMN = 3; //each players gets a name, final score, and amoutn of turns
    public static final String SCORES[] [] = new String [PLAYER_ROW] [PLAYER_COLUMN]; //varibale to store teh scores
    public static final String TEMP_SCORES[] [] = new String [PLAYER_ROW] [PLAYER_COLUMN]; //variable to temp store scores, to write back into orginal array
    public static final int MAX_X = 800; // max x of applet
    public static final int MAX_Y = 600; //max y of applet

    //Next set of constants are sued with menu screen coordinatges
    public static final int START_Y = 300;
    public static final int CONTROLS_Y = 360;
    public static final int HIGH_SCORE_Y = 420;
    public static final int INSTRUCTIONS_Y = 480;
    public static final int QUIT_Y = 540;
    public static final int RIGHT_MENU_ITEM = 500;
    public static final int TITLE_PAGE = 0;
    public static final int START = 1;
    public static final int CONTROLS = 2;
    public static final int HIGH_SCORE = 3;
    public static final int INSTRUCTIONS = 4;
    public static final int QUIT = 5;
    public static final int MODE = 20;
    public static final int LEFT_MENU_ITEM = 100;
    public static final int PAGE_NUMBER_X = 450;
    public static final int PAGE_1 = 1;
    public static final int PAGE_2 = 2;
    public static final int MARIO_LEFT_SELECTION = 80;
    public static final int MARIO_RIGHT_SELECTION = 480;
    public static final int MARIO_BOTTOM_SELECTION = 550;
    public static final int CHANGE_IN_MARIO_Y = 60;
    public static final int CHANGE_IN_LUIGI_X = 150;
    public static final int OPTIONS_2_Y = 500;
    public static final int OPTIONS_1_Y = 450;
    public static final int OPTIONS_3_Y = 550;
    public static final int OPTIONS_1_X = 50;
    public static final int OPTIONS_2_X = 200;
    //the line in which teh balls cannot pass ont he field
    public static final int END_Y = 100;
    //the max row number of each row
    public static final int MAX_ROW_1 = 9;
    public static final int MAX_ROW_2 = 18;
    public static final int MAX_ROW_3 = 28;
    public static final int MAX_ROW_4 = 37;
    public static final int MAX_ROW_5 = 47;
    public static final int MAX_ROW_6 = 56;
    public static final int MAX_ROW_7 = 66;
    public static final int MAX_ROW_8 = 75;
    public static final int MAX_ROW_9 = 85;
    public static final int MAX_ROW_10 = 94;
    public static final int MAX_ROW_11 = 104;
    public static final int MAX_ROW_12 = 113;
    //the min row numebr on each row
    public static final int MIN_ROW_1 = 0;
    public static final int MIN_ROW_2 = 10;
    public static final int MIN_ROW_3 = 19;
    public static final int MIN_ROW_4 = 29;
    public static final int MIN_ROW_5 = 38;
    public static final int MIN_ROW_6 = 48;
    public static final int MIN_ROW_7 = 57;
    public static final int MIN_ROW_8 = 67;
    public static final int MIN_ROW_9 = 76;
    public static final int MIN_ROW_10 = 86;
    public static final int MIN_ROW_11 = 95;
    public static final int MIN_ROW_12 = 105;

    public static final int LENGTH_ROW_1 = 10; //length of a row(odd)
    public static final int LENGTH_ROW_2 = 9; //length of a row(even)
    public static final int ONE_RIGHT = 1; //ball to the right
    public static final int ONE_LEFT = 1; //ball to the left
    public static final int MAX_BALL = 114; //maximum number of balls
    public static final int DIAM_BALL = 25; //diameter of each ball
    public static final int ITEM_10 = 150; //10% chance of item
    public static final int ITEM_15 = 100; //15% chance of item
    public static final int ITEM_20 = 75; //20% chance of item
    public static final int ITEM_25 = 60; //25% chance of item
    public static final int ITEM_SHEL = 12; //indicates mushroom item
    public static final int ITEM_MUSH = 13; //indicated shell item
    public static final int ITEM_FIRE = 14; //indicated fireflower item
    public static final int BALL_GREY = 1; //indicating ball is grey
    public static final int BALL_PURPLE = 2; //indicating ball is purple
    public static final int BALL_TEAL = 3; //indicating ball is teal
    public static final int BALL_BLUE = 4; //indicating ball is blue
    public static final int BALL_PINK = 5; //indicating ball is pink
    public static final int BALL_GREEN = 6; //indicating ball is green
    public static final int BALL_RED = 7; //indicating ball is red


    //variables
    URL base;
    MediaTracker mediaTracker;

    //decide which mario/luigi pic to draw
    int picDecisionMario = 0;
    int columnMario = 1;
    int xMario = MARIO_RIGHT_SELECTION;
    int yMario = START_Y;
    int picDecisionLuigi = 0;
    int xLuigi = OPTIONS_1_X + 15;
    int yLuigi = 400;

    //draw/moves background
    int backgroundMove = 0;
    boolean backgroundMoveDirection = true;

    //make mario originall display title page
    int marioChoice = TITLE_PAGE;

    //calcualte ball movement
    double rise, run;
    int degreeRotation = 0;
    boolean directionRotation = true;

    //decide which option luigi chooses
    boolean optionChoice = false;

    //play intro or not
    boolean intro = false;

    //draws hand for luigi
    int yHand;

    //keeps track of bluball on controls page
    int yBlueBall = 22;

    //which button frame to draw for mario/luigi
    int picDecisionButtonB, picDecisionButtonA = 0;

    //page numebr on menu
    int page = 0;

    //sleep for menu
    int sleep = 75;

    //graphics buffereing
    private Image dbImage;
    private Graphics dbg;

    //File IO
    FileReader fileReader;
    BufferedReader bufferedReader;

    FileWriter fileWriter;
    BufferedWriter bufferedWriter;

    //music
    AudioClip music1;

    //variables that controls the music which is running
    int runMusic = 0;

    //all game avriables
    boolean fire = false;
    int ballCounter = 0;
    boolean ballDirection;
    int xPosition, yPosition;
    int consecutivePops = 0;
    boolean pause = false;
    boolean insane, hard, medium, easy = false;
    int difficultyChoice = 20;
    boolean death = false;
    boolean win = false;
    boolean newHighScore = false;
    int playAgainChoice = 205;
    long currentTime;
    int currentBobombPic;

    //random generate/ map generator variables
    int maximumBallColour = 0;
    int mapGenerationCounter = 0;
    int maximumColourBall = 0;
    Random generator = new Random ();
    int nextRandomNumber = 0;
    int currentRandomNumber;
    int randomNumberStored;

    //item variables
    int randomItem;
    int numItems = -1;
    int itemUsed = 0;
    int itemPower = 0;
    int itemMario = 0;

    //score/turn/final score variables
    String turnString = "0";
    int turns = 0;
    int ballLocation = 0;
    int group = 0;
    int checkLocation = 0;
    int score = 0;
    String scoreString = "0";
    int finalScore = 0;
    String finalScoreString = "0";
    String name;

    //intro variables
    int introFrame = 0;
    int yMonster1 = 560;
    int yMonster2 = 300;
    int yMonster3 = 300;
    int xMonster1 = 0;
    int xMonster2 = 300;
    int barrelFrame = 0;

    //GUI stuffs
    TextField nameField;
    Button GO;

    public void init ()
    {
	// set GUI / screen size
	setSize (MAX_X, MAX_Y);

	setLayout (null);

	nameField = new TextField ("Enter Name Here", 100);
	nameField.setBounds (330, 400, 140, 20);

	GO = new Button ("GO!");
	GO.setBounds (380, 500, 40, 20);

	mediaTracker = new MediaTracker (this);

	try
	{
	    base = getDocumentBase ();
	}
	catch (Exception e)
	{
	}



	//stick pictures in pic array
	GAME_PICS [0] [0] = getImage (base, "images/front page.png");
	GAME_PICS [1] [0] = getImage (base, "images/start.png");
	GAME_PICS [2] [0] = getImage (base, "images/controls.png");
	GAME_PICS [3] [0] = getImage (base, "images/highscore.png");
	GAME_PICS [4] [0] = getImage (base, "images/instructions.png");
	GAME_PICS [5] [0] = getImage (base, "images/quit.png");
	GAME_PICS [6] [0] = getImage (base, "images/next.png");
	GAME_PICS [7] [0] = getImage (base, "images/back.png");
	GAME_PICS [8] [0] = getImage (base, "images/town.png");
	GAME_PICS [9] [0] = getImage (base, "images/arrow.png");
	GAME_PICS [10] [0] = getImage (base, "images/arrowright.png");
	GAME_PICS [11] [0] = getImage (base, "images/arrowleft.png");
	GAME_PICS [12] [0] = getImage (base, "images/shell.png");
	GAME_PICS [13] [0] = getImage (base, "images/mushroom.png");
	GAME_PICS [14] [0] = getImage (base, "images/fireball.png");
	GAME_PICS [15] [0] = getImage (base, "images/arrowup.png");
	GAME_PICS [16] [0] = getImage (base, "images/blueball2.png");
	GAME_PICS [17] [0] = getImage (base, "images/redball.png");
	GAME_PICS [18] [0] = getImage (base, "images/greenball.png");
	GAME_PICS [19] [0] = getImage (base, "images/shellitem.png");
	GAME_PICS [20] [0] = getImage (base, "images/mushroomitem.png");
	GAME_PICS [21] [0] = getImage (base, "images/firefloweritem.png");
	GAME_PICS [22] [0] = getImage (base, "images/arrowup2.png");
	GAME_PICS [23] [0] = getImage (base, "images/credits.png");
	GAME_PICS [24] [0] = getImage (base, "images/intro.png");
	GAME_PICS [25] [0] = getImage (base, "images/toadtown.png");
	GAME_PICS [26] [0] = getImage (base, "images/hand.png");
	GAME_PICS [27] [0] = getImage (base, "images/normal music.png");
	GAME_PICS [28] [0] = getImage (base, "images/epic music.png");
	GAME_PICS [29] [0] = getImage (base, "images/castle.png");
	GAME_PICS [30] [0] = getImage (base, "images/dooropen.png");
	GAME_PICS [31] [0] = getImage (base, "images/dooropen1.png");
	GAME_PICS [32] [0] = getImage (base, "images/dooropen2.png");
	GAME_PICS [33] [0] = getImage (base, "images/dooropen3.png");
	GAME_PICS [34] [0] = getImage (base, "images/monster1.png");
	GAME_PICS [35] [0] = getImage (base, "images/monster2.png");
	GAME_PICS [36] [0] = getImage (base, "images/monster3.png");
	GAME_PICS [37] [0] = getImage (base, "images/monster4.png");
	GAME_PICS [38] [0] = getImage (base, "images/monster5.png");
	GAME_PICS [39] [0] = getImage (base, "images/monster6.png");
	GAME_PICS [40] [0] = getImage (base, "images/arrowdown.png");
	GAME_PICS [41] [0] = getImage (base, "images/cancel.png");
	GAME_PICS [42] [0] = getImage (base, "images/currentball.png");
	GAME_PICS [43] [0] = getImage (base, "images/next ball.png");
	GAME_PICS [44] [0] = getImage (base, "images/score.png");
	GAME_PICS [45] [0] = getImage (base, "images/turn.png");
	GAME_PICS [46] [0] = getImage (base, "images/DingleMarioGameScreen.png");
	GAME_PICS [47] [0] = getImage (base, "images/paused.png");
	GAME_PICS [48] [0] = getImage (base, "images/insane.png");
	GAME_PICS [49] [0] = getImage (base, "images/hard.png");
	GAME_PICS [50] [0] = getImage (base, "images/medium.png");
	GAME_PICS [51] [0] = getImage (base, "images/easy.png");
	GAME_PICS [52] [0] = getImage (base, "images/greybackground.png");
	GAME_PICS [53] [0] = getImage (base, "images/yes.png");
	GAME_PICS [54] [0] = getImage (base, "images/no.png");
	GAME_PICS [55] [0] = getImage (base, "images/youlose.png");
	GAME_PICS [56] [0] = getImage (base, "images/youwin.png");
	GAME_PICS [57] [0] = getImage (base, "images/DingleMarioGameScreen2.png");
	GAME_PICS [58] [0] = getImage (base, "images/DingleMarioGameScreen3.png");
	GAME_PICS [59] [0] = getImage (base, "images/DingleMarioGameScreen4.png");
	GAME_PICS [60] [0] = getImage (base, "images/mariostar.png");
	GAME_PICS [61] [0] = getImage (base, "images/marioidle.png");
	GAME_PICS [62] [0] = getImage (base, "images/new highscore.png");

	GAME_PICS [0] [1] = getImage (base, "images/Mwalk1.png");
	GAME_PICS [1] [1] = getImage (base, "images/Mwalk2.png");
	GAME_PICS [2] [1] = getImage (base, "images/Mwalk3.png");
	GAME_PICS [3] [1] = getImage (base, "images/Mwalk4.png");
	GAME_PICS [4] [1] = getImage (base, "images/Mwalk5.png");
	GAME_PICS [5] [1] = getImage (base, "images/Mwalk4.png");
	GAME_PICS [6] [1] = getImage (base, "images/Mwalk3.png");
	GAME_PICS [7] [1] = getImage (base, "images/Mwalk2.png");

	GAME_PICS [0] [2] = getImage (base, "images/Ldance1.png");
	GAME_PICS [1] [2] = getImage (base, "images/Ldance2.png");
	GAME_PICS [2] [2] = getImage (base, "images/Ldance3.png");
	GAME_PICS [3] [2] = getImage (base, "images/Ldance4.png");
	GAME_PICS [4] [2] = getImage (base, "images/Ldance5.png");
	GAME_PICS [5] [2] = getImage (base, "images/Ldance7.png");
	GAME_PICS [6] [2] = getImage (base, "images/Ldance8.png");
	GAME_PICS [7] [2] = getImage (base, "images/Ldance9.png");
	GAME_PICS [8] [2] = getImage (base, "images/Ldance10.png");
	GAME_PICS [9] [2] = getImage (base, "images/Ldance11.png");
	GAME_PICS [10] [2] = getImage (base, "images/Ldance12.png");
	GAME_PICS [11] [2] = getImage (base, "images/Ldance13.png");
	GAME_PICS [12] [2] = getImage (base, "images/Ldance14.png");
	GAME_PICS [13] [2] = getImage (base, "images/Ldance15.png");

	GAME_PICS [0] [3] = getImage (base, "images/Bbutton1.png");
	GAME_PICS [1] [3] = getImage (base, "images/Bbutton1.png");
	GAME_PICS [2] [3] = getImage (base, "images/Bbutton2.png");
	GAME_PICS [3] [3] = getImage (base, "images/Bbutton2.png");
	GAME_PICS [4] [3] = getImage (base, "images/Bbutton3.png");
	GAME_PICS [5] [3] = getImage (base, "images/Bbutton3.png");
	GAME_PICS [6] [3] = getImage (base, "images/Bbutton2.png");
	GAME_PICS [7] [3] = getImage (base, "images/Bbutton2.png");

	GAME_PICS [0] [4] = getImage (base, "images/Abutton1.png");
	GAME_PICS [1] [4] = getImage (base, "images/Abutton1.png");
	GAME_PICS [2] [4] = getImage (base, "images/Abutton2.png");
	GAME_PICS [3] [4] = getImage (base, "images/Abutton2.png");
	GAME_PICS [4] [4] = getImage (base, "images/Abutton3.png");
	GAME_PICS [5] [4] = getImage (base, "images/Abutton3.png");
	GAME_PICS [6] [4] = getImage (base, "images/Abutton2.png");
	GAME_PICS [7] [4] = getImage (base, "images/Abutton2.png");

	GAME_PICS [0] [5] = getImage (base, "images/1.png");
	GAME_PICS [1] [5] = getImage (base, "images/2.png");
	GAME_PICS [2] [5] = getImage (base, "images/3.png");
	GAME_PICS [3] [5] = getImage (base, "images/4.png");
	GAME_PICS [4] [5] = getImage (base, "images/5.png");
	GAME_PICS [5] [5] = getImage (base, "images/6.png");
	GAME_PICS [6] [5] = getImage (base, "images/7.png");
	GAME_PICS [7] [5] = getImage (base, "images/8.png");
	GAME_PICS [8] [5] = getImage (base, "images/9.png");
	GAME_PICS [9] [5] = getImage (base, "images/10.png");
	GAME_PICS [13] [5] = getImage (base, "images/0.png");
	GAME_PICS [10] [5] = getImage (base, "images/excellent.png");
	GAME_PICS [11] [5] = getImage (base, "images/good.png");
	GAME_PICS [12] [5] = getImage (base, "images/great.png");

	GAME_PICS [0] [6] = getImage (base, "images/MwalkL1.png");
	GAME_PICS [1] [6] = getImage (base, "images/MwalkL2.png");
	GAME_PICS [2] [6] = getImage (base, "images/MwalkL3.png");
	GAME_PICS [3] [6] = getImage (base, "images/MwalkL4.png");
	GAME_PICS [4] [6] = getImage (base, "images/MwalkL5.png");
	GAME_PICS [5] [6] = getImage (base, "images/MwalkL4.png");
	GAME_PICS [6] [6] = getImage (base, "images/MwalkL3.png");
	GAME_PICS [7] [6] = getImage (base, "images/MwalkL2.png");

	GAME_PICS [0] [7] = getImage (base, "images/Mscared1.png");
	GAME_PICS [1] [7] = getImage (base, "images/Mscared2.png");
	GAME_PICS [2] [7] = getImage (base, "images/Mscared3.png");
	GAME_PICS [3] [7] = getImage (base, "images/Mscared2.png");

	GAME_PICS [0] [8] = getImage (base, "images/MwalkU1.png");
	GAME_PICS [1] [8] = getImage (base, "images/MwalkU2.png");
	GAME_PICS [2] [8] = getImage (base, "images/MwalkU3.png");
	GAME_PICS [3] [8] = getImage (base, "images/MwalkU4.png");
	GAME_PICS [4] [8] = getImage (base, "images/MwalkU5.png");
	GAME_PICS [5] [8] = getImage (base, "images/MwalkU4.png");
	GAME_PICS [6] [8] = getImage (base, "images/MwalkU3.png");
	GAME_PICS [7] [8] = getImage (base, "images/MwalkU2.png");

	GAME_PICS [0] [9] = getImage (base, "images/barrel1.png");
	GAME_PICS [1] [9] = getImage (base, "images/barrel2.png");
	GAME_PICS [2] [9] = getImage (base, "images/barrel3.png");
	GAME_PICS [3] [9] = getImage (base, "images/barrel4.png");
	GAME_PICS [4] [9] = getImage (base, "images/barrel5.png");
	GAME_PICS [5] [9] = getImage (base, "images/barrel6.png");
	GAME_PICS [6] [9] = getImage (base, "images/barrel7.png");
	GAME_PICS [7] [9] = getImage (base, "images/barrel8.png");
	GAME_PICS [8] [9] = getImage (base, "images/barrel9.png");
	GAME_PICS [9] [9] = getImage (base, "images/barrel10.png");
	GAME_PICS [10] [9] = getImage (base, "images/barrel11.png");
	GAME_PICS [11] [9] = getImage (base, "images/barrel12.png");
	GAME_PICS [12] [9] = getImage (base, "images/barrel13.png");
	GAME_PICS [13] [9] = getImage (base, "images/barrel14.png");
	GAME_PICS [14] [9] = getImage (base, "images/barrel15.png");

	GAME_PICS [1] [10] = getImage (base, "images/grey.png");
	GAME_PICS [2] [10] = getImage (base, "images/purple.png");
	GAME_PICS [3] [10] = getImage (base, "images/teal.png");
	GAME_PICS [4] [10] = getImage (base, "images/blue.png");
	GAME_PICS [5] [10] = getImage (base, "images/pink.png");
	GAME_PICS [6] [10] = getImage (base, "images/green.png");
	GAME_PICS [7] [10] = getImage (base, "images/red.png");

	GAME_PICS [0] [11] = getImage (base, "images/marioitem1.png");
	GAME_PICS [1] [11] = getImage (base, "images/marioitem1.png");
	GAME_PICS [2] [11] = getImage (base, "images/marioitem1.png");
	GAME_PICS [3] [11] = getImage (base, "images/marioitem1.png");
	GAME_PICS [4] [11] = getImage (base, "images/marioitem1.png");
	GAME_PICS [5] [11] = getImage (base, "images/marioitem1.png");
	GAME_PICS [6] [11] = getImage (base, "images/marioitem1.png");
	GAME_PICS [7] [11] = getImage (base, "images/marioitem1.png");
	GAME_PICS [8] [11] = getImage (base, "images/marioitem1.png");
	GAME_PICS [9] [11] = getImage (base, "images/marioitem1.png");
	GAME_PICS [10] [11] = getImage (base, "images/marioitem2.png");
	GAME_PICS [11] [11] = getImage (base, "images/marioitem2.png");
	GAME_PICS [12] [11] = getImage (base, "images/marioitem2.png");
	GAME_PICS [13] [11] = getImage (base, "images/marioitem2.png");
	GAME_PICS [14] [11] = getImage (base, "images/marioitem2.png");
	GAME_PICS [15] [11] = getImage (base, "images/marioitem2.png");
	GAME_PICS [16] [11] = getImage (base, "images/marioitem2.png");
	GAME_PICS [17] [11] = getImage (base, "images/marioitem2.png");
	GAME_PICS [18] [11] = getImage (base, "images/marioitem2.png");
	GAME_PICS [19] [11] = getImage (base, "images/marioitem2.png");
	GAME_PICS [20] [11] = getImage (base, "images/marioitem3.png");
	GAME_PICS [21] [11] = getImage (base, "images/marioitem3.png");
	GAME_PICS [22] [11] = getImage (base, "images/marioitem3.png");
	GAME_PICS [23] [11] = getImage (base, "images/marioitem3.png");
	GAME_PICS [24] [11] = getImage (base, "images/marioitem3.png");
	GAME_PICS [25] [11] = getImage (base, "images/marioitem3.png");
	GAME_PICS [26] [11] = getImage (base, "images/marioitem3.png");
	GAME_PICS [27] [11] = getImage (base, "images/marioitem3.png");
	GAME_PICS [28] [11] = getImage (base, "images/marioitem3.png");
	GAME_PICS [29] [11] = getImage (base, "images/marioitem3.png");
	GAME_PICS [30] [11] = getImage (base, "images/marioitem4.png");
	GAME_PICS [31] [11] = getImage (base, "images/marioitem4.png");
	GAME_PICS [32] [11] = getImage (base, "images/marioitem4.png");
	GAME_PICS [33] [11] = getImage (base, "images/marioitem4.png");
	GAME_PICS [34] [11] = getImage (base, "images/marioitem4.png");
	GAME_PICS [35] [11] = getImage (base, "images/marioitem4.png");
	GAME_PICS [36] [11] = getImage (base, "images/marioitem4.png");
	GAME_PICS [37] [11] = getImage (base, "images/marioitem4.png");
	GAME_PICS [38] [11] = getImage (base, "images/marioitem4.png");
	GAME_PICS [39] [11] = getImage (base, "images/marioitem4.png");
	GAME_PICS [40] [11] = getImage (base, "images/marioitem5.png");
	GAME_PICS [41] [11] = getImage (base, "images/marioitem5.png");
	GAME_PICS [42] [11] = getImage (base, "images/marioitem5.png");
	GAME_PICS [43] [11] = getImage (base, "images/marioitem5.png");
	GAME_PICS [44] [11] = getImage (base, "images/marioitem5.png");
	GAME_PICS [45] [11] = getImage (base, "images/marioitem5.png");
	GAME_PICS [46] [11] = getImage (base, "images/marioitem5.png");
	GAME_PICS [47] [11] = getImage (base, "images/marioitem5.png");
	GAME_PICS [48] [11] = getImage (base, "images/marioitem5.png");
	GAME_PICS [49] [11] = getImage (base, "images/marioitem5.png");
	GAME_PICS [50] [11] = getImage (base, "images/marioitem6.png");
	GAME_PICS [51] [11] = getImage (base, "images/marioitem6.png");
	GAME_PICS [52] [11] = getImage (base, "images/marioitem6.png");
	GAME_PICS [53] [11] = getImage (base, "images/marioitem6.png");
	GAME_PICS [54] [11] = getImage (base, "images/marioitem6.png");
	GAME_PICS [55] [11] = getImage (base, "images/marioitem6.png");
	GAME_PICS [56] [11] = getImage (base, "images/marioitem6.png");
	GAME_PICS [57] [11] = getImage (base, "images/marioitem6.png");
	GAME_PICS [58] [11] = getImage (base, "images/marioitem6.png");
	GAME_PICS [59] [11] = getImage (base, "images/marioitem6.png");


	GAME_PICS [0] [12] = getImage (base, "images/bobomb1.png");
	GAME_PICS [1] [12] = getImage (base, "images/bobomb1.png");
	GAME_PICS [2] [12] = getImage (base, "images/bobomb2.png");
	GAME_PICS [3] [12] = getImage (base, "images/bobomb3.png");
	GAME_PICS [4] [12] = getImage (base, "images/bobomb4.png");
	GAME_PICS [5] [12] = getImage (base, "images/bobomb5.png");
	GAME_PICS [6] [12] = getImage (base, "images/bobomb6.png");
	GAME_PICS [7] [12] = getImage (base, "images/explosion.png");
	GAME_PICS [8] [12] = getImage (base, "images/spark.png");


	// add all pictrue sinto media tracker
	for (int i = 0 ; i < 100 ; i++)
	{
	    for (int j = 0 ; j < 100 ; j++)
	    {
		if (GAME_PICS [i] [j] != null)
		{
		    mediaTracker.addImage (GAME_PICS [i] [j], 1);
		}
	    }
	}


	/* [colour] [x] [y]
	   [colour] [x + 25] [y]
	   [colour] [x + 50] [y]
	*/

	for (int i = MIN_ROW_1 ; i < 7 ; i++)
	{
	    BALL_COLOUR_QUANTITY [i] [0] = i;
	    //ball colour quantity keeps tarck of ball colour
	}

	//define all coordiantes for ball array
	for (int i = MIN_ROW_1 ; i < MAX_BALL ; i++)
	{
	    BALL_GRID [i] [0] = 0;
	    BALL_GRID [i] [3] = 0;
	}

	for (int i = MIN_ROW_1 ; i < 5 ; i++)
	{
	    ITEM_GRID [i] [0] = 0;
	}

	for (int i = MIN_ROW_1 ; i < MIN_ROW_2 ; i++)
	{
	    BALL_GRID [i] [1] = 275 + i * DIAM_BALL;
	    BALL_GRID [i] [2] = 100;
	}
	for (int i = MIN_ROW_2 ; i < MIN_ROW_3 ; i++)
	{
	    BALL_GRID [i] [1] = 275 + (i - MIN_ROW_2) * DIAM_BALL + DIAM_BALL / 2;
	    BALL_GRID [i] [2] = 125;
	}
	for (int i = MIN_ROW_3 ; i < MIN_ROW_4 ; i++)
	{
	    BALL_GRID [i] [1] = 275 + (i - MIN_ROW_3) * DIAM_BALL;
	    BALL_GRID [i] [2] = 150;
	}
	for (int i = MIN_ROW_4 ; i < MIN_ROW_5 ; i++)
	{
	    BALL_GRID [i] [1] = 275 + (i - MIN_ROW_4) * DIAM_BALL + DIAM_BALL / 2;
	    BALL_GRID [i] [2] = 175;
	}
	for (int i = MIN_ROW_5 ; i < MIN_ROW_6 ; i++)
	{
	    BALL_GRID [i] [1] = 275 + (i - MIN_ROW_5) * DIAM_BALL;
	    BALL_GRID [i] [2] = 200;
	}
	for (int i = MIN_ROW_6 ; i < MIN_ROW_7 ; i++)
	{
	    BALL_GRID [i] [1] = 275 + (i - MIN_ROW_6) * DIAM_BALL + DIAM_BALL / 2;
	    BALL_GRID [i] [2] = 225;
	}
	for (int i = MIN_ROW_7 ; i < MIN_ROW_8 ; i++)
	{
	    BALL_GRID [i] [1] = 275 + (i - MIN_ROW_7) * DIAM_BALL;
	    BALL_GRID [i] [2] = 250;
	}
	for (int i = MIN_ROW_8 ; i < MIN_ROW_9 ; i++)
	{
	    BALL_GRID [i] [1] = 275 + (i - MIN_ROW_8) * DIAM_BALL + DIAM_BALL / 2;
	    BALL_GRID [i] [2] = 275;
	}
	for (int i = MIN_ROW_9 ; i < MIN_ROW_10 ; i++)
	{
	    BALL_GRID [i] [1] = 275 + (i - MIN_ROW_9) * DIAM_BALL;
	    BALL_GRID [i] [2] = 300;
	}
	for (int i = MIN_ROW_10 ; i < MIN_ROW_11 ; i++)
	{
	    BALL_GRID [i] [1] = 275 + (i - MIN_ROW_10) * DIAM_BALL + DIAM_BALL / 2;
	    BALL_GRID [i] [2] = 325;
	}
	for (int i = MIN_ROW_11 ; i < MIN_ROW_12 ; i++)
	{
	    BALL_GRID [i] [1] = 275 + (i - MIN_ROW_11) * DIAM_BALL;
	    BALL_GRID [i] [2] = 350;
	}
	for (int i = MIN_ROW_12 ; i < MAX_BALL ; i++)
	{
	    BALL_GRID [i] [1] = 275 + (i - MIN_ROW_12) * DIAM_BALL + DIAM_BALL / 2;
	    BALL_GRID [i] [2] = 375;
	}
	try
	{
	    mediaTracker.waitForAll ();
	}
	catch (InterruptedException e)
	{
	}



	// Place the body of the initialization method here
    } // init method



    public void start ()
    {

	// define a new thread
	Thread th = new Thread (this);
	// start this thread
	th.start ();

    }



    public boolean keyDown (Event e, int key)
    {
	if (key == Event.LEFT)
	{ //if mario is on title page
	    if (marioChoice == TITLE_PAGE && optionChoice == false)
	    {
		if (xLuigi > OPTIONS_1_X + 15)
		{
		    xLuigi = xLuigi - CHANGE_IN_LUIGI_X;
		}
	    }
	    //if mario is on controls
	    else if (marioChoice == CONTROLS || marioChoice == INSTRUCTIONS)
	    {
		if (xMario == MARIO_RIGHT_SELECTION)
		{
		    xMario = MARIO_LEFT_SELECTION;
		}
	    }
	    //if u lost/won
	    else if (death == true || win == true)
	    {
		if (playAgainChoice == 433)
		{
		    playAgainChoice = 205;
		}
	    }
	    //if your in game
	    else if (marioChoice == START && fire == false && pause == false && (insane == true || hard == true || medium == true || easy == true) && newHighScore == false)
	    {
		if (degreeRotation != -50)
		{
		    degreeRotation = degreeRotation - 2;
		}
	    }
	    //if your deciding difficulty
	    else if (marioChoice == START && insane == false && hard == false && medium == false && easy == false && newHighScore == false)
	    {
		if (difficultyChoice > 20)
		{
		    difficultyChoice = difficultyChoice - 200;
		}
	    }
	}
	else if (key == Event.RIGHT)
	{ //if mario is on title page
	    if (marioChoice == TITLE_PAGE && optionChoice == false)
	    {
		if (xLuigi < OPTIONS_2_X + 15)
		{
		    xLuigi = xLuigi + CHANGE_IN_LUIGI_X;
		}
	    } //if mario is on controls
	    else if (marioChoice == CONTROLS || marioChoice == INSTRUCTIONS)
	    {
		if (xMario == MARIO_LEFT_SELECTION)
		    if (page != PAGE_2)
		    {
			xMario = MARIO_RIGHT_SELECTION;
		    }
	    } //if u lost/won
	    else if (death == true || win == true)
	    {
		if (playAgainChoice == 205)
		{
		    playAgainChoice = 433;
		}
	    } //if your in game
	    else if (marioChoice == START && fire == false && pause == false && (insane == true || hard == true || medium == true || easy == true) && newHighScore == false)
	    {
		if (degreeRotation != 50)
		{
		    degreeRotation = degreeRotation + 2;

		}
	    } //if your deciding difficulty
	    else if (marioChoice == START && insane == false && hard == false && medium == false && easy == false && newHighScore == false)
	    {
		if (difficultyChoice < 620)
		{
		    difficultyChoice = difficultyChoice + 200;
		}
	    }
	}
	else if (key == Event.UP && win == false && newHighScore == false)
	{ //if your in game
	    if (marioChoice == START && fire == false && pause == false)
	    {
		TurnKeeper (); //add oen turn
		fire = true; //fire
		//inital ball coordintaes
		xPosition = 400 + (int) 0 - DIAM_BALL / 2 + degreeRotation / 2;
		yPosition = 505;
		if (degreeRotation > 0)
		{
		    ballDirection = true; // ball is travallign positive x
		}
		else
		{
		    ballDirection = false; // ball is travallign negative x
		}

	    }
	    else if (optionChoice == false) // if yorue on title page and the hand is not on for luigi
	    {
		if (yMario > START_Y && marioChoice == TITLE_PAGE)
		{
		    yMario = yMario - CHANGE_IN_MARIO_Y;
		}
	    }
	    else // if hand is ont he luigi
	    {
		if (yHand > OPTIONS_1_Y)
		{
		    yHand = yHand - 50;
		}
	    }
	}
	else if (key == Event.DOWN)
	{
	    if (optionChoice == false) //if yoru controllign mario on title screen
	    {
		if (yMario < RIGHT_MENU_ITEM && marioChoice == TITLE_PAGE)
		{
		    yMario = yMario + CHANGE_IN_MARIO_Y;
		}
	    }
	    else // if yoru controlling luigi hand
	    {
		if (yHand < OPTIONS_3_Y)
		{
		    yHand = yHand + 50;
		}
	    }
	}
	else if (key == '1' && fire == false) // use item 1
	{
	    if (ITEM_GRID [0] [0] != 0)
	    {
		itemUsed = 1;
		ItemUsed ();
	    }
	}
	else if (key == '2' && fire == false) // use item 2
	{
	    if (ITEM_GRID [1] [0] != 0)
	    {
		itemUsed = 2;
		ItemUsed ();
	    }
	}
	else if (key == '3' && fire == false) // use item 3
	{
	    if (ITEM_GRID [2] [0] != 0)
	    {
		itemUsed = 3;
		ItemUsed ();
	    }
	}
	else if (key == '4' && fire == false) // use item 4
	{
	    if (ITEM_GRID [3] [0] != 0)
	    {
		itemUsed = 4;
		ItemUsed ();
	    }
	}
	else if (key == '5' && fire == false) // use item 5
	{
	    if (ITEM_GRID [4] [0] != 0)
	    {
		itemUsed = 5;
		ItemUsed ();
	    }
	}
	else if (key == 'b' || key == 'B') // if yoru on the menu screen chosoe luigis option
	{
	    if (intro == false && marioChoice == TITLE_PAGE)
	    {
		if (optionChoice == false)
		{
		    optionChoice = true;

		    yHand = OPTIONS_1_Y;
		}
		else
		{
		    if (yHand == OPTIONS_1_Y && xLuigi == OPTIONS_2_X + 15)
		    {
			RunMusic ();
			runMusic = 1;
			music1.loop ();
		    }
		    else if (yHand == OPTIONS_2_Y && xLuigi == OPTIONS_2_X + 15)
		    {
			RunMusic ();
			runMusic = -1;
			music1.loop ();
		    }
		    else if (yHand == OPTIONS_1_Y && xLuigi == OPTIONS_1_X + 15)
		    {
			yMario = 100;
			intro = true;
			introFrame = 0;
			yMonster1 = 560;
			xMonster1 = 0;
			picDecisionMario = 0;
		    }

		    optionChoice = false;
		}
	    }
	}

	else if (key == 's' && intro == true) // increase delay for intro
	{
	    if (sleep == 75)
	    {
		sleep = 30;
	    }
	    else
	    {
		sleep = 75;
	    }

	}

	else if (key == Event.ENTER && marioChoice == START && (insane == true || hard == true || medium == true || easy == true) && death == false && win == false && newHighScore == false) //in game pause
	{
	    if (pause == false)
	    {
		pause = true;
	    }
	    else
	    {
		pause = false;
	    }
	}

	else if (key == Event.ENTER && marioChoice == START && hard == false && medium == false && easy == false && death == false && win == false && newHighScore == false) //deicde diffilculty
	{
	    if (difficultyChoice == 20)
	    {
		easy = true;
		currentTime = System.currentTimeMillis ();
	    }
	    else if (difficultyChoice == 220)
	    {
		medium = true;
		currentTime = System.currentTimeMillis ();
	    }
	    else if (difficultyChoice == 420)
	    {
		hard = true;
		currentTime = System.currentTimeMillis ();
	    }
	    else
	    {
		insane = true;
		currentTime = System.currentTimeMillis ();
	    }
	    for (int i = 0 ; i < MAX_BALL ; i++)
	    {
		BALL_GRID [i] [0] = 0;
		BALL_GRID [i] [3] = 0;
	    }
	    //start a game
	    fire = false; //set fire to false
	    Generate (); //generate balls
	    degreeRotation = 0; // set rotation to 0
	    yPosition = 500; // set y position
	    GenerateMap (); //generate map
	}
	else if (death == true || win == true && newHighScore == false)
	{
	    if (playAgainChoice == 205) // if you decide to play again
	    {
		for (int i = 0 ; i < 5 ; i++)
		{
		    ITEM_GRID [i] [0] = 0; //reset all items
		} //reset all these variables to play again
		insane = false;
		hard = false;
		medium = false;
		easy = false;
		death = false;
		win = false;
		score = 0;
		turns = 0;
		finalScore = 0;
		finalScoreString = "0";
		turnString = "0";
		scoreString = "0";
		marioChoice = START;
		mapGenerationCounter = 0;
	    }
	    else
	    {
		System.exit (0); //else exit
	    }
	}
	else if ((key == 'a' || key == 'A') && optionChoice == false && intro == false) // if you pressed A
	{

	    if (marioChoice == TITLE_PAGE) //mario was on title page
	    {
		columnMario = 6; //ne mario animation

		if (yMario == START_Y) // start game
		{
		    marioChoice = START;
		}
		if (yMario == CONTROLS_Y) // go to controls
		{
		    yMario = MARIO_BOTTOM_SELECTION;
		    marioChoice = CONTROLS;
		    xMario = MARIO_LEFT_SELECTION;
		    page = PAGE_1;
		}
		if (yMario == HIGH_SCORE_Y) //go to high score
		{
		    yMario = MARIO_BOTTOM_SELECTION;
		    marioChoice = HIGH_SCORE;
		    page = PAGE_1;
		}
		if (yMario == INSTRUCTIONS_Y) // got o instructions
		{
		    yMario = MARIO_BOTTOM_SELECTION;
		    marioChoice = INSTRUCTIONS;
		    xMario = MARIO_LEFT_SELECTION;
		    page = PAGE_1;
		}
		if (yMario == QUIT_Y)
		{
		    System.exit (0); // exi twith mario
		}
	    }

	    else if (marioChoice == HIGH_SCORE) //go back to title page
	    {

		yMario = HIGH_SCORE_Y;
		marioChoice = TITLE_PAGE;
		columnMario = 1;

	    }
	    else if (marioChoice == CONTROLS)
	    {
		if (xMario == MARIO_LEFT_SELECTION && page == PAGE_1) //go back to title page
		{
		    page = page - 1;
		    yMario = CONTROLS_Y;
		    marioChoice = TITLE_PAGE;
		    xMario = MARIO_RIGHT_SELECTION;
		    columnMario = 1;
		}
		else if (xMario == MARIO_LEFT_SELECTION && page == PAGE_2) //go back one page
		{
		    page = page - 1;
		    xMario = MARIO_RIGHT_SELECTION;
		}
		else if (xMario == MARIO_RIGHT_SELECTION && page < PAGE_2) //go back one page
		{
		    if (page == PAGE_1)
		    {
			xMario = MARIO_LEFT_SELECTION;
		    }
		    page = page + 1;
		}
	    }
	    else if (marioChoice == INSTRUCTIONS)
	    {

		if (xMario == MARIO_LEFT_SELECTION && page == PAGE_1) //go back to title page
		{
		    page = page - 1;
		    yMario = INSTRUCTIONS_Y;
		    marioChoice = TITLE_PAGE;
		    xMario = MARIO_RIGHT_SELECTION;
		    columnMario = 1;
		}
		else if (xMario == MARIO_LEFT_SELECTION && page == PAGE_2) //go back one page
		{
		    page = page - 1;
		    xMario = MARIO_RIGHT_SELECTION;
		}
		else if (xMario == MARIO_RIGHT_SELECTION && page < PAGE_2) //go back one page
		{
		    if (page == PAGE_1)
		    {
			xMario = MARIO_LEFT_SELECTION;
		    }
		    page = page + 1;
		}
	    }
	}

	return true;
    }



    public void GenerateMap ()  // generate maps
    {
	if (insane == true) //generate insane map
	{
	    for (int i = 0 ; i < 57 ; i++)
	    {
		BALL_GRID [i] [0] = mapGenerationCounter + 1;
		mapGenerationCounter++;
		if (mapGenerationCounter == BALL_RED)
		{
		    mapGenerationCounter = 0;
		}
	    }

	}

	else if (hard == true) //generate hard map
	{
	    for (int j = MIN_ROW_2 ; j < 50 ; j = j + MIN_ROW_3)
	    {
		for (int i = 0 ; i < MAX_ROW_1 ; i++)
		{
		    BALL_GRID [i + j] [0] = mapGenerationCounter + 1;
		    mapGenerationCounter++;
		    if (mapGenerationCounter == BALL_GREEN)
		    {
			mapGenerationCounter = 0;
		    }
		}
	    }

	    for (int k = 0 ; k < MIN_ROW_7 ; k = k + MIN_ROW_3)
	    {
		for (int j = 0 ; j < MAX_ROW_1 ; j = j + 3)
		{
		    for (int i = 1 ; i < 3 ; i++)
		    {

			BALL_GRID [i + j + k] [0] = mapGenerationCounter + 1;
			mapGenerationCounter++;
			if (mapGenerationCounter == BALL_GREEN)
			{
			    mapGenerationCounter = 2;
			}
		    }

		}
	    }
	}

	else if (medium == true) //generate medium map
	{
	    for (int j = 0 ; j < MIN_ROW_2 ; j = j + 2)
	    {
		for (int i = 0 ; i < 2 ; i++)
		{

		    BALL_GRID [i + j] [0] = mapGenerationCounter + 1;

		}
		mapGenerationCounter++;
	    }

	    mapGenerationCounter = 1;
	    for (int j = MIN_ROW_3 ; j < MAX_ROW_3 ; j = j + 2)
	    {
		for (int i = 0 ; i < 2 ; i++)
		{

		    BALL_GRID [i + j] [0] = mapGenerationCounter + 1;

		}
		mapGenerationCounter++;
		if (mapGenerationCounter == BALL_PINK)
		{
		    mapGenerationCounter = 0;
		}

	    }
	    mapGenerationCounter = 0;
	    for (int j = MIN_ROW_5 ; j < MIN_ROW_6 ; j = j + 2)
	    {
		for (int i = 0 ; i < 2 ; i++)
		{

		    BALL_GRID [i + j] [0] = mapGenerationCounter + 1;

		}
		mapGenerationCounter++;

	    }

	    mapGenerationCounter = 0;
	    for (int j = MIN_ROW_2 ; j < MIN_ROW_3 ; j = j + 2)
	    {


		BALL_GRID [j] [0] = mapGenerationCounter + 1;


		mapGenerationCounter++;

		if (mapGenerationCounter == BALL_PINK)
		{
		    mapGenerationCounter = 0;
		}
	    }

	    mapGenerationCounter = 0;
	    for (int j = MIN_ROW_4 ; j < MIN_ROW_5 ; j = j + 2)
	    {


		BALL_GRID [j] [0] = mapGenerationCounter + 1;


		mapGenerationCounter++;

		if (mapGenerationCounter == BALL_PINK)
		{
		    mapGenerationCounter = 0;
		}
	    }

	    mapGenerationCounter = 4;
	    for (int j = MIN_ROW_6 ; j < MIN_ROW_7 ; j = j + 2)
	    {

		BALL_GRID [j] [0] = mapGenerationCounter + 1;


		mapGenerationCounter++;

		if (mapGenerationCounter == BALL_PINK)
		{
		    mapGenerationCounter = 0;
		}
	    }

	}
	else //generate easy map
	{
	    for (int l = 3 ; l < 46 ; l = l + 26)
	    {
		for (int k = 0 ; k < 27 ; k = k + LENGTH_ROW_2)
		{
		    for (int j = 0 ; j < MAX_ROW_1 ; j = j + 3)
		    {
			for (int i = 0 ; i < 2 ; i++)
			{
			    if (l == 3)
			    {
				BALL_GRID [i + j + 1 + k + l - 3] [0] = mapGenerationCounter + 1;
			    }
			    else
			    {
				BALL_GRID [i + j + 1 + k + l] [0] = mapGenerationCounter + 1;
			    }
			}
		    }
		    mapGenerationCounter++;
		    if (mapGenerationCounter == BALL_TEAL)
		    {
			mapGenerationCounter = 0;
		    }
		}
	    }

	    for (int i = 0 ; i < MIN_ROW_3 ; i++)
	    {
		if (BALL_GRID [i] [0] == 0)
		{
		    BALL_GRID [i] [0] = 4;
		}
	    }
	    for (int i = MIN_ROW_5 ; i < MIN_ROW_7 ; i++)
	    {
		if (BALL_GRID [i] [0] == 0)
		{
		    BALL_GRID [i] [0] = 4;
		}
	    }
	    for (int i = 0 ; i < MIN_ROW_4 ; i++)
	    {
		if (BALL_GRID [i] [0] == 0)
		{
		    BALL_GRID [i] [0] = 1;
		}
	    }
	    for (int i = MIN_ROW_4 ; i < MIN_ROW_7 ; i++)
	    {
		if (BALL_GRID [i] [0] == 0)
		{
		    BALL_GRID [i] [0] = 3;
		}
	    }
	    BALL_GRID [28] [0] = 3;

	}
    }


    public void CheckPop ()  //check to see if a ball was poped around initial ball
    {
	//check down left
	if (ballLocation > MAX_ROW_1 && ballLocation != MAX_ROW_3 && ballLocation != MAX_ROW_5 && ballLocation != MAX_ROW_7 && ballLocation != MAX_ROW_9 && ballLocation != MAX_ROW_11)
	{
	    if (BALL_GRID [ballLocation] [0] == BALL_GRID [ballLocation - LENGTH_ROW_2] [0])
	    {
		BALL_GRID [ballLocation - LENGTH_ROW_2] [3] = 1;
		checkLocation = ballLocation - LENGTH_ROW_2;
		ChainPop ();

	    }
	}
	//check down right
	if (ballLocation >= 10)
	{
	    if (BALL_GRID [ballLocation] [0] == BALL_GRID [ballLocation - 10] [0])
	    {
		BALL_GRID [ballLocation - LENGTH_ROW_1] [3] = 1;
		checkLocation = ballLocation - LENGTH_ROW_1;
		ChainPop ();
	    }
	}
	//check right
	if (ballLocation != MAX_ROW_1 && ballLocation != MAX_ROW_2 && ballLocation != MAX_ROW_3 && ballLocation != MAX_ROW_4 && ballLocation != MAX_ROW_5 && ballLocation != MAX_ROW_6 && ballLocation != MAX_ROW_7 && ballLocation != MAX_ROW_8 && ballLocation != MAX_ROW_9 && ballLocation != MAX_ROW_10 && ballLocation != MAX_ROW_11 && ballLocation != MAX_ROW_12)
	{
	    if (BALL_GRID [ballLocation] [0] == BALL_GRID [ballLocation + 1] [0])
	    {
		BALL_GRID [ballLocation + ONE_RIGHT] [3] = 1;
		checkLocation = ballLocation + ONE_RIGHT;
		ChainPop ();
	    }
	}
	//check left
	if (ballLocation != MIN_ROW_1 && ballLocation != MIN_ROW_2 && ballLocation != MIN_ROW_3 && ballLocation != MIN_ROW_4 && ballLocation != MIN_ROW_5 && ballLocation != MIN_ROW_6 && ballLocation != MIN_ROW_7 && ballLocation != MIN_ROW_8 && ballLocation != MIN_ROW_9 && ballLocation != MIN_ROW_10 && ballLocation != MIN_ROW_11 && ballLocation != MIN_ROW_12)
	{
	    if (BALL_GRID [ballLocation] [0] == BALL_GRID [ballLocation - 1] [0])
	    {
		BALL_GRID [ballLocation - ONE_LEFT] [3] = 1;
		checkLocation = ballLocation - ONE_LEFT;
		ChainPop ();
	    }
	}
	//check up right
	if (ballLocation != MIN_ROW_1 && ballLocation != MIN_ROW_3 && ballLocation != MIN_ROW_5 && ballLocation != MIN_ROW_7 && ballLocation != MIN_ROW_9 && ballLocation != MIN_ROW_11 && ballLocation < MIN_ROW_12)
	{
	    if (BALL_GRID [ballLocation] [0] == BALL_GRID [ballLocation + LENGTH_ROW_2] [0])
	    {
		BALL_GRID [ballLocation + LENGTH_ROW_2] [3] = 1;
		checkLocation = ballLocation + LENGTH_ROW_2;
		ChainPop ();
	    }
	}
	//checl up left
	if (ballLocation < MAX_ROW_11)
	{
	    if (BALL_GRID [ballLocation] [0] == BALL_GRID [ballLocation + 10] [0])
	    {
		BALL_GRID [ballLocation + LENGTH_ROW_1] [3] = 1;
		checkLocation = ballLocation + LENGTH_ROW_1;
		ChainPop ();
	    }
	}
	CheckGroupings ();
    }


    public void CheckGroupings ()
    {
	// if u find three balls to destroy
	for (int i = 0 ; i < MAX_BALL ; i++)
	{
	    if (BALL_GRID [i] [3] == 1)
	    {
		group = group + 1;
	    }

	}
	//delay to shwo where ball landed
	try
	{
	    Thread.sleep (100);
	}
	catch (InterruptedException ex)
	{
	}
	//destroy balls

	if (group >= 3)
	{
	    GenerateItem ();
	    consecutivePops++;
	    for (int i = 0 ; i < MAX_BALL ; i++)
	    {
		if (BALL_GRID [i] [3] == 1)
		{
		    BALL_GRID [i] [0] = 0;
		}

	    }
	    ScoreKeeper ();
	}
	else
	{
	    consecutivePops = 0;
	}
	//use items if applicibale
	FlowerPower ();
	ShellPower ();

	//reset value to determien whether or nto a ball should be popped
	for (int i = 0 ; i < MAX_BALL ; i++)
	{
	    BALL_GRID [i] [3] = 0;
	}

    }


    //FLOWER POWERRRR
    public void FlowerPower ()
    {
	if (itemPower == ITEM_FIRE)
	{
	    for (int i = 0 ; i < MAX_BALL ; i++)
	    {
		if (BALL_GRID [ballLocation] [2] == BALL_GRID [i] [2])
		{
		    BALL_GRID [i] [0] = 0;
		}
	    }
	}
    }


    //SHELLL POWERRR
    public void ShellPower ()
    {
	if (itemPower == ITEM_SHEL)
	{
	    if (ballLocation > MAX_ROW_1 && ballLocation != MAX_ROW_3 && ballLocation != MAX_ROW_5 && ballLocation != MAX_ROW_7 && ballLocation != MAX_ROW_9 && ballLocation != MAX_ROW_11)
	    {
		BALL_GRID [ballLocation - LENGTH_ROW_2] [0] = 0;
		BALL_GRID [ballLocation] [0] = 0;
	    }
	    if (ballLocation >= LENGTH_ROW_1)
	    {
		BALL_GRID [ballLocation - LENGTH_ROW_1] [0] = 0;
		BALL_GRID [ballLocation] [0] = 0;
	    }
	    if (ballLocation != MAX_ROW_1 && ballLocation != MAX_ROW_2 && ballLocation != MAX_ROW_3 && ballLocation != MAX_ROW_4 && ballLocation != MAX_ROW_5 && ballLocation != MAX_ROW_6 && ballLocation != MAX_ROW_7 && ballLocation != MAX_ROW_8 && ballLocation != MAX_ROW_9 && ballLocation != MAX_ROW_10 && ballLocation != 104 && ballLocation != MAX_ROW_12)
	    {
		BALL_GRID [ballLocation + ONE_RIGHT] [0] = 0;
		BALL_GRID [ballLocation] [0] = 0;
	    }
	    if (ballLocation != MIN_ROW_1 && ballLocation != MIN_ROW_2 && ballLocation != MIN_ROW_3 && ballLocation != MIN_ROW_4 && ballLocation != MIN_ROW_5 && ballLocation != MIN_ROW_6 && ballLocation != MIN_ROW_7 && ballLocation != MIN_ROW_8 && ballLocation != MIN_ROW_9 && ballLocation != MIN_ROW_10 && ballLocation != MIN_ROW_11 && ballLocation != MIN_ROW_12)
	    {
		BALL_GRID [ballLocation - ONE_LEFT] [0] = 0;
		BALL_GRID [ballLocation] [0] = 0;
	    }
	    if (ballLocation != MIN_ROW_1 && ballLocation != MIN_ROW_3 && ballLocation != MIN_ROW_5 && ballLocation != MIN_ROW_7 && ballLocation != MIN_ROW_9 && ballLocation != MIN_ROW_11 && ballLocation <= MIN_ROW_12)
	    {
		BALL_GRID [ballLocation + LENGTH_ROW_2] [0] = 0;
		BALL_GRID [ballLocation] [0] = 0;
	    }

	    if (ballLocation < MAX_ROW_11)
	    {
		BALL_GRID [ballLocation + LENGTH_ROW_1] [0] = 0;
		BALL_GRID [ballLocation] [0] = 0;
	    }
	}
    }


    //keep track of teh score
    public void ScoreKeeper ()
    {
	for (int i = 0 ; i < MAX_BALL ; i++)
	{
	    if (BALL_GRID [i] [0] != 0)
	    {
		ballCounter++;
	    }
	}
	if (insane == true)
	{
	    score = (int) (score + 50 * (Math.pow (2, group)) + 25 * (Math.pow (2, consecutivePops)) + (MAX_BALL - ballCounter) * consecutivePops / 2 + (Math.pow (group, 4)));
	}
	if (hard == true)
	{
	    score = (int) (score + 50 * (Math.pow (2, group)) + 25 * (Math.pow (2, consecutivePops)) + (MAX_BALL - ballCounter) * consecutivePops / 2 + (Math.pow (group, 3)));
	}
	if (medium == true)
	{
	    score = (int) (score + 50 * (Math.pow (2, group)) + 25 * (Math.pow (2, consecutivePops)) + (MAX_BALL - ballCounter) * consecutivePops / 2 + (Math.pow (group, 2)));
	}
	if (easy == true)
	{
	    score = (int) (score + 50 * (Math.pow (2, group)) + 25 * (Math.pow (2, consecutivePops)) + (MAX_BALL - ballCounter) * consecutivePops / 2 + (Math.pow (group, 1)));
	}
	scoreString = new Integer (score).toString ();
	ballCounter = 0;
    }


    //add oen turn
    public void TurnKeeper ()
    {
	turns++;
	turnString = new Integer (turns).toString ();
    }


    //check to see if the ball around the initial ball was popped (see checkpop)
    public void ChainPop ()
    {
	if (checkLocation > MAX_ROW_1 && checkLocation != MAX_ROW_3 && checkLocation != MAX_ROW_5 && checkLocation != MAX_ROW_7 && checkLocation != MAX_ROW_9 && checkLocation != MAX_ROW_11)
	{
	    if (BALL_GRID [checkLocation] [0] == BALL_GRID [checkLocation - LENGTH_ROW_2] [0])
	    {
		if (BALL_GRID [checkLocation - LENGTH_ROW_2] [3] != 1)
		{
		    BALL_GRID [checkLocation - LENGTH_ROW_2] [3] = 1;
		}

	    }
	}
	if (checkLocation >= LENGTH_ROW_1)
	{
	    if (BALL_GRID [checkLocation] [0] == BALL_GRID [checkLocation - LENGTH_ROW_1] [0])
	    {
		if (BALL_GRID [checkLocation - LENGTH_ROW_1] [3] != 1)
		{
		    BALL_GRID [checkLocation - LENGTH_ROW_1] [3] = 1;
		}
	    }
	}
	if (checkLocation != MAX_ROW_1 && checkLocation != MAX_ROW_2 && checkLocation != MAX_ROW_3 && checkLocation != MAX_ROW_4 && checkLocation != MAX_ROW_5 && checkLocation != MAX_ROW_6 && checkLocation != MAX_ROW_7 && checkLocation != MAX_ROW_8 && checkLocation != MAX_ROW_9 && checkLocation != MAX_ROW_10 && checkLocation != MAX_ROW_11 && checkLocation != MAX_ROW_12)
	{
	    if (BALL_GRID [checkLocation] [0] == BALL_GRID [checkLocation + ONE_RIGHT] [0])
	    {
		if (BALL_GRID [checkLocation + ONE_RIGHT] [3] != 1)
		{
		    BALL_GRID [checkLocation + ONE_RIGHT] [3] = 1;
		}
	    }
	}

	if (checkLocation != MIN_ROW_1 && checkLocation != MIN_ROW_2 && checkLocation != MIN_ROW_3 && checkLocation != MIN_ROW_4 && checkLocation != MIN_ROW_5 && checkLocation != MIN_ROW_6 && checkLocation != MIN_ROW_7 && checkLocation != MIN_ROW_8 && checkLocation != MIN_ROW_9 && checkLocation != MIN_ROW_10 && checkLocation != MIN_ROW_11 && checkLocation != MIN_ROW_12)
	{
	    if (BALL_GRID [checkLocation] [0] == BALL_GRID [checkLocation - ONE_LEFT] [0])
	    {
		if (BALL_GRID [checkLocation - ONE_LEFT] [3] != 1)
		{
		    BALL_GRID [checkLocation - ONE_LEFT] [3] = 1;
		}
	    }
	}

	if (checkLocation != MIN_ROW_1 && checkLocation != MIN_ROW_3 && checkLocation != MIN_ROW_5 && checkLocation != MIN_ROW_7 && checkLocation != MIN_ROW_9 && checkLocation != MIN_ROW_11 && checkLocation < MIN_ROW_12)
	{
	    if (BALL_GRID [checkLocation] [0] == BALL_GRID [checkLocation + LENGTH_ROW_2] [0])
	    {
		if (BALL_GRID [checkLocation + LENGTH_ROW_2] [3] != 1)
		{
		    BALL_GRID [checkLocation + LENGTH_ROW_2] [3] = 1;
		}
	    }
	}

	if (checkLocation < MAX_ROW_11)
	{
	    if (BALL_GRID [checkLocation] [0] == BALL_GRID [checkLocation + LENGTH_ROW_1] [0])
	    {
		if (BALL_GRID [checkLocation + LENGTH_ROW_1] [3] != 1)
		{
		    BALL_GRID [checkLocation + LENGTH_ROW_1] [3] = 1;
		}
	    }
	}
    }


    //generate randomballs
    public void Generate ()
    {
	currentRandomNumber = nextRandomNumber;

	for (int i = 0 ; i < MAX_BALL ; i++)
	{
	    if (BALL_GRID [i] [0] != 0)
	    {
		ballCounter++;
	    }
	}

	//next part determines whether or not the "smart generator" shoudl kick in and generate balls u need if u have 10 or less balls
	if (ballCounter <= 10)
	{

	    for (int i = 0 ; i < MAX_BALL ; i++)
	    {
		if (BALL_GRID [i] [0] != 0)
		{

		    for (int j = 0 ; j < 7 ; j++)
		    {
			if ((BALL_GRID [i] [0] - 1) == BALL_COLOUR_QUANTITY [j] [0])
			{
			    BALL_COLOUR_QUANTITY [j] [1]++;
			}
		    }
		}
	    }


	    maximumBallColour = BALL_COLOUR_QUANTITY [0] [0];
	    ballCounter = BALL_COLOUR_QUANTITY [0] [1];

	    for (int i = 0 ; i < 7 ; i++)
	    {
		if (ballCounter < BALL_COLOUR_QUANTITY [i] [1])
		{
		    maximumBallColour = BALL_COLOUR_QUANTITY [i] [0];
		    ballCounter = BALL_COLOUR_QUANTITY [i] [1];
		}
	    }

	    nextRandomNumber = (maximumBallColour + 1) * 50;
	    maximumBallColour = 0;
	    ballCounter = 0;

	    for (int i = 0 ; i < 7 ; i++)
	    {
		BALL_COLOUR_QUANTITY [i] [1] = 0;
	    }
	}

	else
	{
	    if (insane == true)
	    {
		nextRandomNumber = generator.nextInt (350);
	    }
	    else if (hard == true)
	    {
		nextRandomNumber = generator.nextInt (300);
	    }
	    else if (medium == true)
	    {
		nextRandomNumber = generator.nextInt (250);
	    }
	    else if (easy == true)
	    {
		nextRandomNumber = generator.nextInt (200);
	    }
	}
    }


    //generate a random item if the random numebrs falls unders 15
    public void GenerateItem ()
    {
	if (insane == true)
	{
	    randomItem = generator.nextInt (ITEM_10);
	}


	if (hard == true)
	{
	    randomItem = generator.nextInt (ITEM_15);
	}


	if (medium == true)
	{
	    randomItem = generator.nextInt (ITEM_20);
	}


	if (easy == true)
	{
	    randomItem = generator.nextInt (ITEM_25);
	}


	if (numItems < 4 && randomItem <= 15)
	{
	    numItems++;

	    if (randomItem <= 15)
	    {
		ITEM_GRID [numItems] [0] = ITEM_SHEL;
	    }
	    if (randomItem <= 10)
	    {
		ITEM_GRID [numItems] [0] = ITEM_MUSH;
	    }
	    if (randomItem <= 5)
	    {
		ITEM_GRID [numItems] [0] = ITEM_FIRE;
	    }
	    ITEM_GRID [numItems] [1] = 305 + numItems * 40;
	}
    }


    //determine if user used as item
    public void ItemUsed ()
    {

	if (ITEM_GRID [itemUsed - 1] [0] == ITEM_MUSH)
	{
	    itemPower = ITEM_MUSH;
	}


	else if (ITEM_GRID [itemUsed - 1] [0] == ITEM_SHEL)
	{
	    itemPower = ITEM_SHEL;
	}


	else if (ITEM_GRID [itemUsed - 1] [0] == ITEM_FIRE)
	{
	    itemPower = ITEM_FIRE;
	}

	//fix item grid to move back 1
	for (int i = 0 ; i < numItems - (itemUsed - 1) ; i++)
	{
	    ITEM_GRID [itemUsed + (i - 1)] [0] = ITEM_GRID [itemUsed + i] [0];
	}


	itemUsed = 0;

	ITEM_GRID [numItems] [0] = 0;

	numItems = numItems - 1;


    }


    // check to see if a ball aligns with another ball

    public void AlignBall ()
    {

	for (int i = 0 ; i < MAX_BALL ; i++) //check every ball
	{ //check to see if it sticks to the right
	    if (BALL_GRID [i] [0] != 0 && yPosition - DIAM_BALL <= BALL_GRID [i] [2] && yPosition + DIAM_BALL > BALL_GRID [i] [2] + 15 && xPosition - 5 <= BALL_GRID [i] [1] + DIAM_BALL && xPosition >= BALL_GRID [i] [1] + 18 && BALL_GRID [i + 1] [0] == 0)
	    {
		if (i != MAX_ROW_1 && i != MAX_ROW_2 && i != MAX_ROW_3 && i != MAX_ROW_4 && i != MAX_ROW_5 && i != MAX_ROW_6 && i != MAX_ROW_7 && i != MAX_ROW_8 && i != MAX_ROW_9 && i != MAX_ROW_10 && i != MAX_ROW_11 && i != MAX_ROW_12)
		{
		    yPosition = BALL_GRID [i] [2];
		    xPosition = BALL_GRID [i + 1] [1];
		    for (int j = 0 ; j < MAX_BALL ; j++)
		    {
			if (yPosition == BALL_GRID [j] [2] && xPosition == BALL_GRID [j] [1])
			{
			    BALL_GRID [j] [1] = xPosition;
			    BALL_GRID [j] [2] = yPosition;
			    BALL_GRID [j] [0] = randomNumberStored;
			    ballLocation = j;
			    fire = false;
			    i = MAX_BALL;
			}
		    }
		}
	    }
	    //check to see if it sticks to the left
	    else if (BALL_GRID [i] [0] != 0 && yPosition - DIAM_BALL <= BALL_GRID [i] [2] && yPosition + DIAM_BALL > BALL_GRID [i] [2] + 15 && xPosition + 30 >= BALL_GRID [i] [1] && xPosition + DIAM_BALL <= BALL_GRID [i] [1] + 18 && BALL_GRID [i - 1] [0] == 0)
	    {
		if (i != MIN_ROW_1 && i != MIN_ROW_2 && i != MIN_ROW_3 && i != MIN_ROW_4 && i != MIN_ROW_5 && i != MIN_ROW_6 && i != MIN_ROW_7 && i != MIN_ROW_8 && i != MIN_ROW_9 && i != MIN_ROW_10 && i != MIN_ROW_11 && i != MIN_ROW_12)
		{
		    yPosition = BALL_GRID [i] [2];
		    xPosition = BALL_GRID [i - 1] [1];
		    for (int j = 0 ; j < MAX_BALL ; j++)
		    {
			if (yPosition == BALL_GRID [j] [2] && xPosition == BALL_GRID [j] [1])
			{
			    BALL_GRID [j] [1] = xPosition;
			    BALL_GRID [j] [2] = yPosition;
			    BALL_GRID [j] [0] = randomNumberStored;
			    ballLocation = j;
			    fire = false;
			    i = MAX_BALL;
			}
		    }
		}
	    }
	    //check to see if it sticks to the bottom
	    else if (BALL_GRID [i] [0] != 0 && yPosition - DIAM_BALL <= BALL_GRID [i] [2] && yPosition + DIAM_BALL > BALL_GRID [i] [2] + 15 && xPosition - DIAM_BALL <= BALL_GRID [i] [1] && xPosition + DIAM_BALL >= BALL_GRID [i] [1])
	    {
		yPosition = BALL_GRID [i] [2] + DIAM_BALL;

		if (yPosition > 375)
		{
		    death = true;
		}

		if (xPosition + DIAM_BALL >= BALL_GRID [i] [1] && xPosition <= BALL_GRID [i] [1] && death == false)
		{
		    if (BALL_GRID [i + LENGTH_ROW_2] [0] != 0)
		    {
			xPosition = BALL_GRID [i + LENGTH_ROW_1] [1];

		    }
		    else
		    {
			xPosition = BALL_GRID [i + LENGTH_ROW_2] [1];

		    }
		}
		else if (xPosition <= BALL_GRID [i] [1] + DIAM_BALL && xPosition >= BALL_GRID [i] [1] && death == false)
		{
		    if (BALL_GRID [i + LENGTH_ROW_1] [0] != 0)
		    {
			xPosition = BALL_GRID [i + LENGTH_ROW_2] [1];

		    }
		    else
		    {
			xPosition = BALL_GRID [i + LENGTH_ROW_1] [1];
		    }
		}
		//find the coordinates of the new ball and assign them
		for (int j = 0 ; j < MAX_BALL ; j++)
		{
		    if (yPosition == BALL_GRID [j] [2] && xPosition == BALL_GRID [j] [1] && death == false)
		    {
			BALL_GRID [j] [1] = xPosition;
			BALL_GRID [j] [2] = yPosition;
			BALL_GRID [j] [0] = randomNumberStored;
			//use balllocation to figure out whetehr to to check these balls
			ballLocation = j;
			//end firing
			fire = false;
			//exit for loop
			i = MAX_BALL;
		    }
		}
	    }

	}
    }


    //SHROOOM POWERRRRRR
    public void MushroomPower ()
    {
	if (itemPower == ITEM_MUSH)
	{
	    if (ballLocation > MAX_ROW_1 && ballLocation != MAX_ROW_3 && ballLocation != MAX_ROW_5 && ballLocation != MAX_ROW_7 && ballLocation != MAX_ROW_9 && ballLocation != MAX_ROW_11)
	    {
		if (BALL_GRID [ballLocation - LENGTH_ROW_2] [0] != 0)
		{
		    BALL_GRID [ballLocation] [0] = BALL_GRID [ballLocation - LENGTH_ROW_2] [0];
		}
	    }
	    if (ballLocation >= LENGTH_ROW_1)
	    {
		if (BALL_GRID [ballLocation - LENGTH_ROW_1] [0] != 0)
		{
		    BALL_GRID [ballLocation] [0] = BALL_GRID [ballLocation - LENGTH_ROW_1] [0];
		}
	    }
	    if (ballLocation != MAX_ROW_1 && ballLocation != MAX_ROW_2 && ballLocation != MAX_ROW_3 && ballLocation != MAX_ROW_4 && ballLocation != MAX_ROW_5 && ballLocation != MAX_ROW_6 && ballLocation != MAX_ROW_7 && ballLocation != MAX_ROW_8 && ballLocation != MAX_ROW_9 && ballLocation != MAX_ROW_10 && ballLocation != MAX_ROW_11 && ballLocation != MAX_ROW_12)
	    {
		if (BALL_GRID [ballLocation + ONE_RIGHT] [0] != 0)
		{
		    BALL_GRID [ballLocation] [0] = BALL_GRID [ballLocation + ONE_RIGHT] [0];
		}
	    }
	    if (ballLocation != MIN_ROW_1 && ballLocation != MIN_ROW_2 && ballLocation != MIN_ROW_3 && ballLocation != MIN_ROW_4 && ballLocation != MIN_ROW_5 && ballLocation != MIN_ROW_6 && ballLocation != MIN_ROW_7 && ballLocation != MIN_ROW_8 && ballLocation != MIN_ROW_9 && ballLocation != MIN_ROW_10 && ballLocation != MIN_ROW_11 && ballLocation != MIN_ROW_12)
	    {
		if (BALL_GRID [ballLocation - ONE_LEFT] [0] != 0)
		{
		    BALL_GRID [ballLocation] [0] = BALL_GRID [ballLocation - ONE_LEFT] [0];
		}
	    }
	    if (ballLocation != MIN_ROW_1 && ballLocation != MIN_ROW_3 && ballLocation != MIN_ROW_5 && ballLocation != MIN_ROW_7 && ballLocation != MIN_ROW_9 && ballLocation != MIN_ROW_11 && ballLocation <= MIN_ROW_12)
	    {
		if (BALL_GRID [ballLocation + LENGTH_ROW_2] [0] != 0)
		{
		    BALL_GRID [ballLocation] [0] = BALL_GRID [ballLocation + LENGTH_ROW_2] [0];
		}
	    }

	    if (ballLocation < MAX_ROW_11)
	    {
		if (BALL_GRID [ballLocation + LENGTH_ROW_1] [0] != 0)
		{
		    BALL_GRID [ballLocation] [0] = BALL_GRID [ballLocation + LENGTH_ROW_1] [0];
		}
	    }
	}
    }


    //main game method of program
    public void Start ()
    {
	if (fire == true && pause == false && death == false) //if u didnt die paue and if a ball was not fired already
	{
	    //check to see if a ball aligned
	    AlignBall ();
	    if (yPosition - DIAM_BALL < BALL_GRID [0] [2])
	    {
		LockBallCeiling ();
	    }

	    // if u find where teh ball is located ont he field do this
	    if (ballLocation != 0)
	    {
		//renew currenttime
		currentTime = System.currentTimeMillis ();
		//set variables to 0
		MushroomPower ();
		CheckPop ();
		ballLocation = 0;
		group = 0;
		itemPower = 0;
		Generate ();
		itemMario = 0;

		ballCounter = 0;
		//check to see if all balls are gone
		for (int i = 0 ; i < MAX_BALL ; i++)
		{
		    if (BALL_GRID [i] [0] == 0)
		    {
			ballCounter++;
		    }
		    if (ballCounter == MAX_BALL)
		    { //if tehy are, player wins and final score calculated
			finalScore = (int) (Math.round (score / turns));
			finalScoreString = new Integer (finalScore).toString ();

			try
			{
			    //read highscore
			    HighScore ();
			}
			catch (IOException e)
			{
			}


			for (int j = 0 ; j < PLAYER_ROW ; j++)
			{
			    if (finalScore >= Integer.parseInt (SCORES [j] [1]))
			    { //add GUi stuff
				newHighScore = true;
				add (nameField);
				nameField.addActionListener (this);
				add (GO);
				GO.addActionListener (this);
				j = PLAYER_ROW;
			    }
			    else
			    {
				win = true;
			    }
			}
		    }
		}
		ballCounter = 0;
	    }
	    Calculate ();

	}
	if (marioChoice == START && fire == false && currentTime + 7000 == System.currentTimeMillis () && pause == false && win == false && death == false && newHighScore == false) //if timer runs out
	{ //fire ball (refer to Event.UP method
	    fire = true;
	    TurnKeeper ();
	    xPosition = 400 + (int) 0 - DIAM_BALL / 2 + degreeRotation / 2;
	    yPosition = 505;
	    if (degreeRotation > 0)
	    {
		ballDirection = true;
	    }
	    else
	    {
		ballDirection = false;
	    }
	}
    }


    public void LockBallCeiling ()  // if teh ball is not touchign a nother ball to lock, lock it agaisnt the ceiling
    {
	for (int i = 0 ; i < 10 ; i++)
	{
	    if (xPosition + DIAM_BALL / 2 >= 500)
	    {
		xPosition = 500;
	    }
	    else if (xPosition + DIAM_BALL / 2 <= 275 + i * DIAM_BALL)
	    {
		xPosition = 275 + (i - 1) * DIAM_BALL;
		i = LENGTH_ROW_1;
	    }
	    else if (xPosition < 300)
	    {
		xPosition = 300;
	    }
	}


	yPosition = BALL_GRID [0] [2];

	for (int j = 0 ; j < MAX_BALL ; j++)
	{
	    if (yPosition == BALL_GRID [j] [2] && xPosition == BALL_GRID [j] [1])
	    {
		BALL_GRID [j] [1] = xPosition;
		BALL_GRID [j] [0] = randomNumberStored;
		ballLocation = j;
		fire = false;
	    }
	}
    }



    public void Calculate ()  //caclualtes trajectory of teh ball

    {
	rise = (36 * Math.cos ((Math.toRadians (degreeRotation)))) / 5;
	if (degreeRotation < 0)
	{
	    run = (-1 * 36 * Math.sin ((Math.toRadians (degreeRotation)))) / 5;
	}


	else
	{
	    run = (36 * Math.sin ((Math.toRadians (degreeRotation)))) / 5;
	}


	if (ballDirection == true)
	{
	    xPosition = xPosition + (int) run;
	    if (xPosition > 500)
	    {
		xPosition = 500;
		ballDirection = false;
	    }
	}


	else
	{
	    xPosition = xPosition - (int) run;
	    if (xPosition < 275)
	    {
		xPosition = 275;
		ballDirection = true;
	    }
	}


	yPosition = yPosition - (int) rise;

    }


    //handles music
    public void RunMusic ()
    {
	try
	{
	    if (yHand == OPTIONS_2_Y)
	    {
		if (runMusic == 1)
		{
		    music1.stop ();
		}
		music1 = getAudioClip (base, "Audio.wav");
	    }
	    else
	    {
		if (runMusic == -1)
		{
		    music1.stop ();
		}
		music1 = getAudioClip (base, "Audio2.wav");
	    }
	}


	catch (Exception e)
	{
	}
    }


    //handles barell on controls page
    public void Controls ()
    {
	if (page == PAGE_1)
	{
	    if (directionRotation == true)
	    {
		degreeRotation = degreeRotation + 3;
		if (degreeRotation == 24)
		{
		    directionRotation = false;
		}
	    }
	    else if (directionRotation == false)
	    {
		degreeRotation = degreeRotation - 3;
		if (degreeRotation == -24)
		{
		    directionRotation = true;
		}
	    }
	}


	else
	{
	    if (yBlueBall < 80)
	    {
		yBlueBall = yBlueBall + 5;
	    }
	    else
	    {
		yBlueBall = 22;
	    }
	}
    }


    //reads highscore
    public void HighScore () throws IOException
    {

	fileReader = new FileReader ("Scores.txt");
	bufferedReader = new BufferedReader (fileReader);

	for (int i = 0 ; i < PLAYER_ROW ; i++)
	{
	    for (int j = 0 ; j < PLAYER_COLUMN ; j++)
	    {
		String scores = bufferedReader.readLine ();
		if (scores != null)
		{
		    SCORES [i] [j] = scores;
		}
	    }
	}


	bufferedReader.close ();

    }


    //writes highscore
    public void RecordHighScore () throws IOException
    {
	for (int i = 0 ; i < PLAYER_ROW ; i++)
	{
	    for (int j = 0 ; j < PLAYER_COLUMN ; j++)
	    {
		TEMP_SCORES [i] [j] = SCORES [i] [j];
	    }
	}

	for (int j = 0 ; j < PLAYER_ROW ; j++)
	{
	    if (finalScore >= Integer.parseInt (SCORES [j] [1]))
	    {

		SCORES [j] [1] = Integer.toString (finalScore);
		SCORES [j] [0] = name;
		SCORES [j] [2] = Integer.toString (turns);

		for (int i = j ; i < 9 ; i++)
		{
		    SCORES [i + 1] [0] = TEMP_SCORES [i] [0];
		    SCORES [i + 1] [1] = TEMP_SCORES [i] [1];
		    SCORES [i + 1] [2] = TEMP_SCORES [i] [2];
		}

		j = PLAYER_ROW;
	    }
	    fileWriter = new FileWriter ("Scores.txt");
	    bufferedWriter = new BufferedWriter (fileWriter);

	    for (int i = 0 ; i < PLAYER_ROW ; i++)
	    {
		for (int k = 0 ; k < PLAYER_COLUMN ; k++)
		{
		    bufferedWriter.write (SCORES [i] [k]);
		    bufferedWriter.newLine ();
		}
	    }
	    bufferedWriter.close ();
	}
    }


    //handles marios movement
    public void MarioMove ()
    {
	if (introFrame > 139 && introFrame < 180)
	{
	    columnMario = 8;
	}


	picDecisionMario++;

	if (picDecisionMario == 8)
	{
	    picDecisionMario = 0;
	}


	picDecisionButtonA++;

	if (picDecisionButtonA == 8)
	{
	    picDecisionButtonA = 0;
	}
    }


    //handles when amrio gets scared
    public void MarioScared ()
    {
	picDecisionMario++;

	columnMario = 7;

	if (picDecisionMario >= 3)
	{
	    picDecisionMario = 0;
	}
    }


    //when mario gets an item
    public void ItemMario ()
    {
	if (itemMario < 59)
	{
	    itemMario++;
	}
    }


    //handles luigi's movement
    public void LuigiMove ()
    {

	picDecisionLuigi++;

	if (picDecisionLuigi == 14)
	{
	    picDecisionLuigi = 0;
	}


	picDecisionButtonB++;

	if (picDecisionButtonB == 8)
	{
	    picDecisionButtonB = 0;
	}
    }


    //sleep for the menu
    public void Sleep ()
    {

	try
	{
	    Thread.sleep (sleep);
	}


	catch (InterruptedException ex)
	{
	}
    }


    //sleep for the intro
    public void SleepIntro ()
    {

	try
	{
	    Thread.sleep (500);
	}


	catch (InterruptedException ex)
	{
	}
    }


    //sleep for the game
    public void SleepGame ()
    {

	try
	{
	    Thread.sleep (10);
	}


	catch (InterruptedException ex)
	{
	}
    }


    //moves background
    public void BackgroundMove ()
    {
	if (backgroundMove == 0)
	{
	    backgroundMoveDirection = true;
	}


	else if (backgroundMove == 400)
	{
	    backgroundMoveDirection = false;
	}


	if (backgroundMoveDirection == true)
	{
	    backgroundMove = backgroundMove + 2;
	}


	else if (backgroundMoveDirection == false)
	{
	    backgroundMove = backgroundMove - 2;
	}
    }


    //plays intro and handles which frame to play
    public void Intro ()
    {
	introFrame++;
	if (introFrame < 4)
	{
	    SleepIntro ();
	}


	else
	{
	    Sleep ();
	}


	if (introFrame < 51)
	{
	    MarioMove ();
	    yMario = yMario + 3;
	}


	if (introFrame > 51 && introFrame < 70)
	{
	    yMonster1 = yMonster1 - 5;
	}


	if (introFrame > 80 && introFrame < 90)
	{
	    xMonster1 = xMonster1 + 5;
	}


	if (introFrame > 99 && introFrame < 140)
	{
	    MarioScared ();
	}


	if (introFrame > 139 && introFrame < 180)
	{
	    MarioMove ();
	    yMario = yMario - 3;
	}


	if (introFrame > 198 && introFrame < 249)
	{
	    MarioMove ();
	    yMario = yMario + 3;
	    columnMario = 1;
	}


	if (introFrame > 270 && introFrame < 284)
	{
	    barrelFrame++;
	}


	if (introFrame > 282 && introFrame < 291)
	{
	    yMonster2 = yMonster2 + 15;
	}


	if (introFrame > 300 && introFrame < 307)
	{
	    yMonster3 = yMonster3 + 15;
	}


	if (introFrame == 308)
	{
	    yMonster3 = yMonster3 + 10;
	}


	if (introFrame > 374 && introFrame < 387)
	{
	    yMonster1 = yMonster1 + 10;
	}


	if (introFrame > 389 && introFrame < 426)
	{
	    MarioMove ();
	    yMario = yMario + 9;

	}

	//rreset all intro frames onc eitnro is done playing
	if (introFrame == 450)
	{
	    intro = false;
	    yMario = START_Y;
	    columnMario = 1;
	    yMonster1 = 560;
	    xMonster1 = 0;
	    sleep = 75;
	    barrelFrame = 0;
	    yMonster2 = 300;
	    yMonster3 = 300;
	}
    }


    //main method of whole program
    public void run ()
    {
	Thread.currentThread ().setPriority (Thread.MIN_PRIORITY);

	while (marioChoice != QUIT && marioChoice != START)
	{
	    if (intro == false) //if allevaluates to true, mario is on main screen
	    {
		MarioMove ();

		if (marioChoice == TITLE_PAGE) //on title page
		{
		    LuigiMove ();
		}

		else if (marioChoice == CONTROLS) //on controls page
		{
		    Controls ();
		}
		else if (marioChoice == HIGH_SCORE) //read high score
		{
		    try
		    {
			HighScore ();
		    }
		    catch (IOException e)
		    {
		    }
		}
		Sleep (); //delay
		repaint (); //repaint
		BackgroundMove (); //move background
		Thread.currentThread ().setPriority (Thread.MAX_PRIORITY);
	    }
	    else //other wise the intro is true
	    {
		Intro ();
		repaint ();
	    }
	}


	if (marioChoice == START && insane == false && hard == false && medium == false && easy == false) //user is deciding difficulty level (first time)
	{
	    while (insane == false && hard == false && medium == false && easy == false)
	    {
		Sleep ();
		repaint ();
		BackgroundMove ();
	    }
	}


	while (marioChoice == START) //user is decidign diffuculty level
	{
	    if (marioChoice == START && insane == false && hard == false && medium == false && easy == false)
	    {
		while (insane == false && hard == false && medium == false && easy == false)
		{
		    Sleep ();
		    repaint ();
		    BackgroundMove ();
		}
	    }
	    else //actually in game
	    {
		Start ();
		if (fire == true)
		{
		    SleepGame ();
		}

		repaint ();
		Thread.currentThread ().setPriority (Thread.MAX_PRIORITY);
	    }
	}
    }


    public void update (Graphics g)
    {

	// initialize buffer
	if (dbImage == null)
	{
	    dbImage = createImage (this.getSize ().width, this.getSize ().height);
	    dbg = dbImage.getGraphics ();
	}


	// clear screen in background
	dbg.setColor (getBackground ());
	dbg.fillRect (0, 0, this.getSize ().width, this.getSize ().height);

	// draw elements in background
	dbg.setColor (getForeground ());
	paint (dbg);

	// draw image on the screen
	g.drawImage (dbImage, 0, 0, this);

    }


    public void paint (Graphics g)  //whole paint command NO LOGIC DECIDED HERE, ONYL FOR LOOPS TO DRAW AND IF STATEMENTS
    {
	Graphics2D g2d = (Graphics2D) g;

	if (intro == false)
	{

	    if (marioChoice != START && marioChoice != QUIT) // draw mario/luigi/background
	    {
		g.drawImage (GAME_PICS [8] [0], 0 - backgroundMove, 0, this);
		g.drawImage (GAME_PICS [0] [0], 0, 0, this);

		g.drawImage (GAME_PICS [picDecisionMario] [columnMario], xMario - (GAME_PICS [picDecisionMario] [columnMario].getWidth (this)) / 2, yMario, this);

		if (picDecisionButtonA > 1)
		{
		    g.drawImage (GAME_PICS [picDecisionButtonA] [4], xMario - 60, yMario + 5, this);
		}
		else
		{
		    g.drawImage (GAME_PICS [picDecisionButtonA] [4], xMario - 60, yMario, this);
		}

	    }


	    if (marioChoice == TITLE_PAGE) //draw title page stuff
	    {
		g.setFont (new Font ("Calibri", Font.BOLD, 16));

		g.drawImage (GAME_PICS [15] [0], 304, START_Y - 15, this);
		g.drawImage (GAME_PICS [10] [0], 335, START_Y + 21, this);
		g.drawImage (GAME_PICS [11] [0], 269, START_Y + 21, this);
		g.drawImage (GAME_PICS [40] [0], 304, START_Y + 19, this);
		g.drawString ("Move", 298, CONTROLS_Y + 10);

		g.drawImage (GAME_PICS [1] [0], RIGHT_MENU_ITEM, START_Y, this);
		g.drawImage (GAME_PICS [2] [0], RIGHT_MENU_ITEM, CONTROLS_Y, this);
		g.drawImage (GAME_PICS [3] [0], RIGHT_MENU_ITEM, HIGH_SCORE_Y, this);
		g.drawImage (GAME_PICS [4] [0], RIGHT_MENU_ITEM, INSTRUCTIONS_Y, this);
		g.drawImage (GAME_PICS [5] [0], RIGHT_MENU_ITEM, QUIT_Y, this);
		g.drawImage (GAME_PICS [23] [0], OPTIONS_1_X, OPTIONS_2_Y, this);
		g.drawImage (GAME_PICS [24] [0], OPTIONS_1_X, OPTIONS_1_Y, this);
		g.drawImage (GAME_PICS [27] [0], OPTIONS_2_X, OPTIONS_1_Y, this);
		g.drawImage (GAME_PICS [28] [0], OPTIONS_2_X, OPTIONS_2_Y, this);
		g.drawImage (GAME_PICS [41] [0], OPTIONS_1_X, OPTIONS_3_Y, this);
		g.drawImage (GAME_PICS [41] [0], OPTIONS_2_X, OPTIONS_3_Y, this);

		if (optionChoice == true)
		{
		    g.drawImage (GAME_PICS [26] [0], xLuigi - 30, yHand, this);
		}


		g.drawImage (GAME_PICS [picDecisionLuigi] [2], xLuigi - (GAME_PICS [picDecisionLuigi] [2].getWidth (this)) / 2, yLuigi, this);

		if (picDecisionButtonB > 1)
		{
		    g.drawImage (GAME_PICS [picDecisionButtonB] [3], xLuigi - (GAME_PICS [picDecisionButtonB] [3].getWidth (this)) / 2, yLuigi - 40, this);
		}
		else
		{
		    g.drawImage (GAME_PICS [picDecisionButtonB] [3], xLuigi - (GAME_PICS [picDecisionButtonB] [3].getWidth (this)) / 2, yLuigi - 45, this);
		}
	    }


	    else if (marioChoice == CONTROLS) //draw control page stuff
	    {
		g.setFont (new Font ("Calibri", Font.BOLD, 20));
		if (page == PAGE_1) //page one
		{
		    AffineTransform origXform = g2d.getTransform ();
		    AffineTransform newXform = (AffineTransform) (origXform.clone ());
		    //center of rotation is center of the panel
		    int xRot = GAME_PICS [14] [9].getWidth (this) / 2 + 233;
		    int yRot = GAME_PICS [14] [9].getHeight (this) / 2 + START_Y + 30;
		    newXform.rotate (Math.toRadians (degreeRotation), xRot, yRot);
		    g2d.setTransform (newXform);
		    //draw image centered in panel
		    g2d.drawImage (GAME_PICS [14] [9], 233, START_Y + 30, this);
		    g2d.setTransform (origXform);

		    g.drawImage (GAME_PICS [6] [0], RIGHT_MENU_ITEM, QUIT_Y, this);
		    g.drawImage (GAME_PICS [0] [5], PAGE_NUMBER_X, MODE, this);


		    g.drawImage (GAME_PICS [10] [0], 295, 340, this);
		    g.drawImage (GAME_PICS [11] [0], 207 - (GAME_PICS [11] [0].getWidth (this)), 340, this);

		    g.drawString ("Utilize", 225, START_Y);
		    g.drawString ("Left", 175, 410);
		    g.drawString ("Right", 290, 410);
		    g.drawString ("Arrow Keys", 200, 450);
		    g.drawString ("To Rotate The Arrow", 165, 500);

		    g.drawImage (GAME_PICS [12] [0], 580, START_Y - 20, this);
		    g.drawImage (GAME_PICS [13] [0], 630, START_Y - 20, this);
		    g.drawImage (GAME_PICS [14] [0], 680, START_Y - 20, this);

		    g.drawString ("Collect Power - Ups", 562, CONTROLS_Y - 20);

		    for (int i = 0 ; i < 5 ; i++)
		    {
			g.drawString ("Key", RIGHT_MENU_ITEM + 50, (i * 35) + 375);
			g.drawImage (GAME_PICS [i] [5], RIGHT_MENU_ITEM + 100, (i * 35) + 355, this);
			g.drawString ("Use Item " + (i + 1), RIGHT_MENU_ITEM + 150, (i * 35) + 375);
		    }
		}
		if (page == PAGE_2) //page 2
		{
		    g.drawImage (GAME_PICS [1] [5], PAGE_NUMBER_X, MODE, this);
		    g.drawImage (GAME_PICS [14] [9], 202, CONTROLS_Y, this);
		    g.drawImage (GAME_PICS [15] [0], 270, HIGH_SCORE_Y - 30, this);
		    g.drawString ("Up Key", 255, 445);
		    g.drawString ("To Fire Balls", 190, 500);
		    g.drawImage (GAME_PICS [16] [0], 205, CONTROLS_Y - yBlueBall, this);

		    g.drawImage (GAME_PICS [47] [0], 500, CONTROLS_Y, this);
		    g.drawString ("Press Enter to Pause", 480, 420);

		}

		g.drawImage (GAME_PICS [2] [0], RIGHT_MENU_ITEM, MODE, this);
		g.drawImage (GAME_PICS [7] [0], LEFT_MENU_ITEM, QUIT_Y, this);
		g.drawImage (GAME_PICS [7] [0], LEFT_MENU_ITEM, QUIT_Y, this);
	    }


	    else if (marioChoice == HIGH_SCORE) //draw hgihscore stuff
	    {
		if (page == PAGE_1)
		{
		    g.drawImage (GAME_PICS [0] [5], PAGE_NUMBER_X, MODE, this);
		}
		if (page == PAGE_2)
		{
		    g.drawImage (GAME_PICS [1] [5], PAGE_NUMBER_X, MODE, this);
		}

		g.drawImage (GAME_PICS [7] [0], RIGHT_MENU_ITEM, QUIT_Y, this);
		g.drawImage (GAME_PICS [3] [0], RIGHT_MENU_ITEM, MODE, this);

		try //for loop and properly place highs core text file on sreen
		{
		    for (int i = 0 ; i < PLAYER_ROW ; i++)
		    {
			if (i <= 4)
			{
			    g.drawImage (GAME_PICS [i] [5], 100, (i * 50) + 300, this);
			}
			else
			{
			    g.drawImage (GAME_PICS [i] [5], 450, ((i - 5) * 50) + 300, this);
			}

			g.setFont (new Font ("Arial", Font.PLAIN, 20));

			for (int j = 0 ; j < PLAYER_COLUMN ; j++)
			{
			    if (i <= 4)
			    {
				if (j == 0)
				{
				    g.drawString (SCORES [i] [j], 150, (i * 50) + 315);
				}
				else if (j == 1)
				{
				    g.drawString (SCORES [i] [j], 250, (i * 50) + 315);
				}
				else
				{
				    g.drawString (SCORES [i] [j], 350, (i * 50) + 315);
				}
			    }
			    else
			    {
				if (j == 0)
				{
				    g.drawString (SCORES [i] [j], 500, ((i - 5) * 50) + 315);
				}
				else if (j == 1)
				{
				    g.drawString (SCORES [i] [j], 600, ((i - 5) * 50) + 315);
				}
				else
				{
				    g.drawString (SCORES [i] [j], 700, ((i - 5) * 50) + 315);
				}
			    }

			}
		    }
		}
		catch (Exception e)
		{
		}

		g.drawString ("Score", 600, 280); //;places words here

		g.drawString ("Score", 250, 280);

		g.drawString ("Turns", 700, 280);

		g.drawString ("Turns", 350, 280);


	    }


	    else if (marioChoice == INSTRUCTIONS) //draw instructiosn stuff
	    {
		if (page == PAGE_1) //page 1
		{
		    g.setFont (new Font ("Calibri", Font.BOLD, 20));

		    g.drawImage (GAME_PICS [0] [5], PAGE_NUMBER_X, MODE, this);
		    g.drawImage (GAME_PICS [6] [0], RIGHT_MENU_ITEM, QUIT_Y, this);

		    g.drawImage (GAME_PICS [16] [0], 105, START_Y, this);
		    g.drawImage (GAME_PICS [16] [0], 105 + 25, START_Y, this);
		    g.drawImage (GAME_PICS [16] [0], 105 + 50, START_Y, this);

		    g.drawImage (GAME_PICS [17] [0], 195, START_Y, this);
		    g.drawImage (GAME_PICS [17] [0], 195 + 25, START_Y, this);
		    g.drawImage (GAME_PICS [17] [0], 195 + 25 / 2, START_Y + 22, this);

		    g.drawImage (GAME_PICS [18] [0], 255, START_Y, this);
		    g.drawImage (GAME_PICS [18] [0], 255 + 25 / 2, START_Y + 22, this);
		    g.drawImage (GAME_PICS [18] [0], 255 + 50 / 2, START_Y + 44, this);

		    g.drawImage (GAME_PICS [11] [5], 170, CONTROLS_Y + 20, this);

		    g.drawString ("Align 3 of the Same Balls", 105, HIGH_SCORE_Y + 20);
		    g.drawString ("To Clear Them!", 145, HIGH_SCORE_Y + 50);

		    g.drawImage (GAME_PICS [16] [0], 405, START_Y, this);
		    g.drawImage (GAME_PICS [17] [0], 405 + 25, START_Y, this);
		    g.drawImage (GAME_PICS [18] [0], 405 + 50, START_Y, this);

		    g.drawImage (GAME_PICS [16] [0], 430, START_Y + 50, this);
		    g.drawImage (GAME_PICS [17] [0], 430, START_Y + 75, this);
		    g.drawImage (GAME_PICS [18] [0], 430, START_Y + 100, this);

		    g.drawString ("Or They Will Stack!", 365, INSTRUCTIONS_Y);

		    g.drawImage (GAME_PICS [18] [0], 605, START_Y, this);
		    g.drawImage (GAME_PICS [18] [0], 630, START_Y, this);
		    g.drawImage (GAME_PICS [18] [0], 655, START_Y, this);
		    g.drawImage (GAME_PICS [18] [0], 630 + 25 / 2, START_Y + 22, this);
		    g.drawImage (GAME_PICS [12] [5], 610, CONTROLS_Y, this);

		    g.drawString ("Clear As Many As You Can!", 535, HIGH_SCORE_Y + 30);
		}
		if (page == PAGE_2) //page 2
		{
		    g.drawImage (GAME_PICS [1] [5], PAGE_NUMBER_X, MODE, this);
		    g.drawImage (GAME_PICS [19] [0], 180, START_Y + 5, this);
		    g.drawImage (GAME_PICS [20] [0], 220, START_Y, this);
		    g.drawImage (GAME_PICS [21] [0], 260, START_Y, this);

		    g.drawString ("Sometimes Items Will Appear!", 110, CONTROLS_Y + 30);
		    g.drawString ("Hold Up to 5 Items to Use", 130, HIGH_SCORE_Y + 30);

		    g.drawImage (GAME_PICS [17] [0], 475, START_Y, this);
		    g.drawImage (GAME_PICS [17] [0], 475 + 25, START_Y, this);
		    g.drawImage (GAME_PICS [20] [0], 475 + 25 / 2, START_Y + 22, this);
		    g.drawImage (GAME_PICS [22] [0], 570, START_Y + 11, this);
		    g.drawImage (GAME_PICS [17] [0], 625, START_Y, this);
		    g.drawImage (GAME_PICS [17] [0], 625 + 25, START_Y, this);
		    g.drawImage (GAME_PICS [17] [0], 625 + 25 / 2, START_Y + 22, this);

		    g.drawString ("Discover Their", 520, CONTROLS_Y + 30);
		    g.drawString ("Hidden Abilities!", 510, HIGH_SCORE_Y + 30);
		}

		g.drawImage (GAME_PICS [4] [0], RIGHT_MENU_ITEM, MODE, this);
		g.drawImage (GAME_PICS [7] [0], LEFT_MENU_ITEM, QUIT_Y, this);
	    }


	    if (marioChoice == START && (insane != false || hard != false || medium != false || easy != false)) //draw  background stuff
	    {
		if (insane == true) //background for insane
		{
		    g.drawImage (GAME_PICS [46] [0], 0, 0, this);
		}
		else if (hard == true) //background for hard
		{
		    g.drawImage (GAME_PICS [59] [0], 0, 0, this);
		}
		else if (medium == true) //background for medium
		{
		    g.drawImage (GAME_PICS [58] [0], 0, 0, this);
		}
		else if (easy == true) //background for easy
		{
		    g.drawImage (GAME_PICS [57] [0], 0, 0, this);
		}
		if (fire == true)
		{
		    g.drawImage (GAME_PICS [randomNumberStored] [10], xPosition, yPosition, this); //draw ball firing
		}
		//ball rotates
		AffineTransform origXform = g2d.getTransform ();
		AffineTransform newXform = (AffineTransform) (origXform.clone ());
		//center of rotation is center of the panel
		int xRot = 400;
		int yRot = 550;
		newXform.rotate (Math.toRadians (degreeRotation), xRot, yRot);
		g2d.setTransform (newXform);
		//draw image centered in panel
		g2d.drawImage (GAME_PICS [14] [9], 400 - GAME_PICS [14] [9].getWidth (this) / 2, 550 - GAME_PICS [14] [9].getHeight (this), this);

		g2d.setTransform (origXform);

		//draw the next ball to come up depending on random number
		if (nextRandomNumber <= 50)
		{
		    g.drawImage (GAME_PICS [1] [10], 570, 537, this);
		}
		else if (nextRandomNumber <= 100)
		{
		    g.drawImage (GAME_PICS [2] [10], 570, 537, this);
		}
		else if (nextRandomNumber <= 150)
		{
		    g.drawImage (GAME_PICS [3] [10], 570, 537, this);
		}
		else if (nextRandomNumber <= 200)
		{
		    g.drawImage (GAME_PICS [4] [10], 570, 537, this);
		}
		else if (nextRandomNumber <= 250)
		{
		    g.drawImage (GAME_PICS [5] [10], 570, 537, this);
		}
		else if (nextRandomNumber <= 300)
		{
		    g.drawImage (GAME_PICS [6] [10], 570, 537, this);
		}
		else
		{
		    g.drawImage (GAME_PICS [7] [10], 570, 537, this);
		}



		g.drawImage (GAME_PICS [43] [0], 600, 537, this);

		//draw current ball in barell
		if (currentRandomNumber <= 50)
		{
		    randomNumberStored = BALL_GREY;
		}
		else if (currentRandomNumber <= 100)
		{
		    randomNumberStored = BALL_PURPLE;
		}
		else if (currentRandomNumber <= 150)
		{
		    randomNumberStored = BALL_TEAL;
		}
		else if (currentRandomNumber <= 200)
		{
		    randomNumberStored = BALL_BLUE;
		}
		else if (currentRandomNumber <= 250)
		{
		    randomNumberStored = BALL_PINK;
		}
		else if (currentRandomNumber <= 300)
		{
		    randomNumberStored = BALL_GREEN;
		}
		else
		{
		    randomNumberStored = BALL_RED;
		}

		g.drawImage (GAME_PICS [randomNumberStored] [10], 570, 487, this);
		g.drawImage (GAME_PICS [42] [0], 600, 487, this);

		//draw every ball on the field
		for (int i = 0 ; i < MAX_BALL ; i++)
		{
		    if (BALL_GRID [i] [0] != 0)
		    {
			g.drawImage (GAME_PICS [BALL_GRID [i] [0]] [10], BALL_GRID [i] [1], BALL_GRID [i] [2], this);
		    }
		}
		//draw score
		if (score > 0)
		{
		    for (int i = 0 ; i < scoreString.length () ; i++)
		    {
			if (Integer.parseInt (Character.toString (scoreString.charAt (i))) == 0)
			{
			    g.drawImage (GAME_PICS [13] [5], 590 + i * 15, 390, this);
			}
			else
			{
			    g.drawImage (GAME_PICS [Integer.parseInt (Character.toString (scoreString.charAt (i))) - 1] [5], 590 + i * 15, 390, this);
			}
		    }
		}

		else
		{
		    g.drawImage (GAME_PICS [13] [5], 590, 390, this);
		}
		//draw turns
		if (turns > 0)
		{
		    for (int i = 0 ; i < turnString.length () ; i++)
		    {
			if (Integer.parseInt (Character.toString (turnString.charAt (i))) == 0)
			{
			    g.drawImage (GAME_PICS [13] [5], 590 + i * 15, 305, this);
			}
			else
			{
			    g.drawImage (GAME_PICS [Integer.parseInt (Character.toString (turnString.charAt (i))) - 1] [5], 590 + i * 15, 305, this);
			}
		    }
		}
		else
		{
		    g.drawImage (GAME_PICS [13] [5], 590, 305, this);
		}
		//set bob omb to draw depedning on timer
		if (fire == false && pause == false && death == false && win == false && newHighScore == false)
		{
		    if (currentTime <= System.currentTimeMillis ())
		    {
			g.drawImage (GAME_PICS [0] [12], 660, 437, this);
			g.drawImage (GAME_PICS [8] [12], 660, 420, this);
			currentBobombPic = 0;
		    }
		    if (currentTime + 1000 <= System.currentTimeMillis ())
		    {
			g.drawImage (GAME_PICS [1] [12], 660, 437, this);
			g.drawImage (GAME_PICS [8] [12], 660, 420, this);
			currentBobombPic = 0;
		    }
		    if (currentTime + 2000 <= System.currentTimeMillis ())
		    {
			g.drawImage (GAME_PICS [2] [12], 660, 437, this);
			g.drawImage (GAME_PICS [8] [12], 660, 420, this);
			currentBobombPic = 1;
		    }
		    if (currentTime + 3000 <= System.currentTimeMillis ())
		    {
			g.drawImage (GAME_PICS [3] [12], 660, 437, this);
			g.drawImage (GAME_PICS [8] [12], 660, 420, this);
			currentBobombPic = 2;
		    }
		    if (currentTime + 4000 <= System.currentTimeMillis ())
		    {
			g.drawImage (GAME_PICS [4] [12], 660, 437, this);
			g.drawImage (GAME_PICS [8] [12], 660, 420, this);
			currentBobombPic = 3;
		    }
		    if (currentTime + 5000 <= System.currentTimeMillis ())
		    {
			g.drawImage (GAME_PICS [5] [12], 660, 437, this);
			g.drawImage (GAME_PICS [8] [12], 660, 420, this);
			currentBobombPic = 4;
		    }
		    if (currentTime + 6000 <= System.currentTimeMillis ())
		    {
			g.drawImage (GAME_PICS [6] [12], 660, 437, this);
			g.drawImage (GAME_PICS [8] [12], 660, 420, this);
			currentBobombPic = 5;
		    }
		}
		//draw an explodign bobobm if ball is fired
		if (fire == true)
		{
		    g.drawImage (GAME_PICS [7] [12], 660, 445, this);
		}

		if (pause == true && fire == false) // continue drawing ombobm in its original psoition is paused
		{
		    g.drawImage (GAME_PICS [currentBobombPic] [12], 660, 437, this);
		    g.drawImage (GAME_PICS [8] [12], 660, 420, this);

		}
		for (int i = 0 ; i < 5 ; i++) //draw items
		{
		    if (ITEM_GRID [i] [0] != 0)
		    {
			g.drawImage (GAME_PICS [ITEM_GRID [i] [0]] [0], ITEM_GRID [i] [1], 560, this);
		    }
		}

		if (itemPower == 0) //if mario is not using an item,
		{
		    g.drawImage (GAME_PICS [61] [0], 320, 513, this);
		}
		else //draw amrio usign an item
		{
		    ItemMario ();
		    if (itemMario == 59)
		    {
			if (itemPower == ITEM_MUSH)
			{
			    g.drawImage (GAME_PICS [20] [0], 320, 485, this);
			}
			else if (itemPower == ITEM_SHEL)
			{
			    g.drawImage (GAME_PICS [19] [0], 320, 485, this);
			}
			else if (itemPower == ITEM_FIRE)
			{
			    g.drawImage (GAME_PICS [21] [0], 320, 485, this);
			}
		    }
		    g.drawImage (GAME_PICS [itemMario] [11], 320 - (GAME_PICS [itemMario] [11].getWidth (this)) / 2, 513, this);
		}

		//draw good/great/excellent depening on numebr of consecutive pops
		if (consecutivePops == 1)
		{
		    g.drawImage (GAME_PICS [11] [5], 670, 350, this);
		}
		else if (consecutivePops == 2)
		{
		    g.drawImage (GAME_PICS [12] [5], 670, 350, this);
		}
		else if (consecutivePops >= 3)
		{
		    g.drawImage (GAME_PICS [10] [5], 670, 350, this);
		}

		if (pause) //if game is paused
		{
		    currentTime = currentTime + 10;
		    g.drawImage (GAME_PICS [52] [0], 0, 0, this);
		    g.drawImage (GAME_PICS [47] [0], 400 - GAME_PICS [47] [0].getWidth (this) / 2, 450, this);
		}
		if (death == true)  //if player lost
		{
		    g.drawImage (GAME_PICS [52] [0], 0, 0, this);
		    g.drawImage (GAME_PICS [55] [0], 177, 0, this);
		    g.drawImage (GAME_PICS [53] [0], 222, 500, this);
		    g.drawImage (GAME_PICS [54] [0], 450, 500, this);
		    g.drawImage (GAME_PICS [26] [0], playAgainChoice, 500, this);
		}
		if (win == true) //if player won
		{
		    g.drawImage (GAME_PICS [52] [0], 0, 0, this);
		    g.drawImage (GAME_PICS [56] [0], 177, 0, this);
		    g.drawImage (GAME_PICS [53] [0], 222, 500, this);
		    g.drawImage (GAME_PICS [54] [0], 450, 500, this);
		    g.drawImage (GAME_PICS [26] [0], playAgainChoice, 500, this);
		}

		if (newHighScore == true) //if u got a new high score
		{
		    g.drawImage (GAME_PICS [52] [0], 0, 0, this);
		    g.drawImage (GAME_PICS [62] [0], 177, 0, this);
		}

		if (win == true || newHighScore == true) //if player got won, drawing their final score
		{
		    for (int i = 0 ; i < scoreString.length () ; i++)
		    {
			if (Integer.parseInt (Character.toString (scoreString.charAt (i))) == 0)
			{
			    g.drawImage (GAME_PICS [13] [5], 270 + i * 15, 450, this);
			}
			else
			{
			    g.drawImage (GAME_PICS [Integer.parseInt (Character.toString (scoreString.charAt (i))) - 1] [5], 270 + i * 15, 450, this);
			}
			g.drawString ("/", 280 + (scoreString.length () * 15), 465);
		    }

		    for (int i = 0 ; i < turnString.length () ; i++)
		    {
			if (Integer.parseInt (Character.toString (turnString.charAt (i))) == 0)
			{
			    g.drawImage (GAME_PICS [13] [5], 300 + (scoreString.length () * 15) + i * 15, 450, this);
			}
			else
			{
			    g.drawImage (GAME_PICS [Integer.parseInt (Character.toString (turnString.charAt (i))) - 1] [5], 300 + (scoreString.length () * 15) + i * 15, 450, this);
			}
		    }

		    g.drawString ("=", 310 + (scoreString.length () * 15) + (turnString.length () * 15), 465);


		    for (int i = 0 ; i < finalScoreString.length () ; i++)
		    {
			if (Integer.parseInt (Character.toString (finalScoreString.charAt (i))) == 0)
			{
			    g.drawImage (GAME_PICS [13] [5], 300 + (finalScoreString.length () * 15) + i * 15, 450, this);
			}
			else
			{
			    g.drawImage (GAME_PICS [Integer.parseInt (Character.toString (finalScoreString.charAt (i))) - 1] [5], 330 + (scoreString.length () * 15) + (turnString.length () * 15) + i * 15, 450, this);
			}
		    }
		}



	    }

	    else if (marioChoice == START) //if player is deciding the diffulty
	    {
		g.drawImage (GAME_PICS [8] [0], 0 - backgroundMove, 0, this);
		g.setFont (new Font ("Calibri", Font.BOLD, 20));
		g.drawString ("Press Enter to Select Difficulty", 280, 200);

		g.drawImage (GAME_PICS [51] [0], 36, 300, this);
		g.drawImage (GAME_PICS [50] [0], 236, 300, this);
		g.drawImage (GAME_PICS [49] [0], 436, 300, this);
		g.drawImage (GAME_PICS [48] [0], 636, 300, this);
		g.drawImage (GAME_PICS [26] [0], difficultyChoice, 300, this);

		g.setFont (new Font ("Calibri", Font.BOLD, 16));
		g.drawString ("4 Different Balls", 36, 400);
		g.drawString ("5 Different Balls", 236, 400);
		g.drawString ("6 Different Balls", 436, 400);
		g.drawString ("7 Different Balls", 636, 400);

		g.drawString ("25% Chance of Item", 36, 450);
		g.drawString ("20% Chance of Item", 236, 450);
		g.drawString ("15% Chance of Item", 436, 450);
		g.drawString ("10% Chance of Item", 636, 450);

		g.drawString ("Small Multiplier", 36, 500);
		g.drawString ("Average Multiplier", 236, 500);
		g.drawString ("Huge Multiplier", 436, 500);
		g.drawString ("Massive Multiplier", 636, 500);
	    }


	}


	else if (intro == true) //if intro is true
	{
	    g.drawImage (GAME_PICS [29] [0], 0, -8, this);

	    if (introFrame < 4)
	    {
		g.drawImage (GAME_PICS [introFrame + 29] [0], 272, 408, this);
	    }
	    else if (introFrame >= 3)
	    {
		g.drawImage (GAME_PICS [33] [0], 272, 408, this);

	    }
	    if (introFrame >= 3 && introFrame < 99)
	    {
		g.drawImage (GAME_PICS [picDecisionMario] [columnMario], 400 - (GAME_PICS [picDecisionMario] [columnMario].getWidth (this)) / 2, yMario, this);
	    }
	    if (introFrame > 51 && introFrame < 80)
	    {
		g.drawImage (GAME_PICS [35] [0], 400 - (GAME_PICS [35] [0].getWidth (this)) / 2, yMonster1, this);
		g.drawImage (GAME_PICS [37] [0], 400 - (GAME_PICS [37] [0].getWidth (this)) / 2, yMonster1 - 60, this);
		g.drawImage (GAME_PICS [39] [0], 400 - (GAME_PICS [39] [0].getWidth (this)) / 2, yMonster1 - 30, this);
	    }
	    if (introFrame > 79 && introFrame < 375)
	    {
		g.drawImage (GAME_PICS [35] [0], 400 - (GAME_PICS [35] [0].getWidth (this)) / 2, yMonster1, this);
		g.drawImage (GAME_PICS [37] [0], xMonster1 + 400 - (GAME_PICS [37] [0].getWidth (this)) / 2, yMonster1 - 60, this);
		if (introFrame > 79 && introFrame < 315)
		{
		    g.drawImage (GAME_PICS [39] [0], 400 - (GAME_PICS [39] [0].getWidth (this)) / 2 - xMonster1, yMonster1 - 30, this);
		}
	    }
	    if (introFrame > 90 && introFrame < 180)
	    {
		g.drawImage (GAME_PICS [picDecisionMario] [columnMario], 400 - (GAME_PICS [picDecisionMario] [columnMario].getWidth (this)) / 2, yMario, this);
	    }
	    if (introFrame > 199 && introFrame < 426)
	    {
		g.drawImage (GAME_PICS [picDecisionMario] [columnMario], 400 - (GAME_PICS [picDecisionMario] [columnMario].getWidth (this)) / 2, yMario, this);
	    }
	    if (introFrame > 260 && introFrame < 390)
	    {
		g.drawImage (GAME_PICS [barrelFrame] [9], 400 - (GAME_PICS [barrelFrame] [9].getWidth (this)) / 2 - xMonster1, yMario - (GAME_PICS [barrelFrame] [9].getHeight (this)) / 2, this);
	    }
	    if (introFrame > 282 && introFrame < 315)
	    {
		g.drawImage (GAME_PICS [39] [0], 400 - (GAME_PICS [39] [0].getWidth (this)) / 2 - xMonster1, yMonster2, this);
	    }
	    if (introFrame > 300 && introFrame < 315)
	    {
		g.drawImage (GAME_PICS [39] [0], 400 - (GAME_PICS [39] [0].getWidth (this)) / 2 - xMonster1, yMonster3, this);
	    }
	    if (introFrame > 314 && introFrame < 330)
	    {
		g.drawImage (GAME_PICS [11] [5], 400 - (GAME_PICS [11] [5].getWidth (this)) / 2, yMonster3 - 30, this);
	    }
	    g.setFont (new Font ("Calibri", Font.BOLD, 20));

	    if (introFrame > 340 && introFrame < 370)
	    {
		g.drawString (".", 390, 400);
	    }
	    if (introFrame > 350 && introFrame < 370)
	    {
		g.drawString (".", 400, 400);
	    }
	    if (introFrame > 360 && introFrame < 370)
	    {
		g.drawString (".", 410, 400);
	    }
	    if (introFrame > 374 && introFrame < 387)
	    {
		g.drawImage (GAME_PICS [34] [0], 400 - (GAME_PICS [35] [0].getWidth (this)) / 2, yMonster1, this);
		g.drawImage (GAME_PICS [36] [0], xMonster1 + 400 - (GAME_PICS [37] [0].getWidth (this)) / 2, yMonster1 - 60, this);
	    }

	}


	// Place the body of the drawing method here
    } // paint method


    public void actionPerformed (ActionEvent evt)  //action event to process GUI when player gets a new high score
    {
	if (evt.getSource () == GO) //when player hits go
	{
	    name = nameField.getText (); //get name from text field
	    newHighScore = false; //turn high score false
	    //remove go
	    remove (GO);
	    remove (nameField);
	    //read high score from file
	    try
	    {
		RecordHighScore ();
	    }
	    catch (IOException e)
	    {
	    }
	    win = true;

	}
    }
} // Dingle_Mario_Game class






