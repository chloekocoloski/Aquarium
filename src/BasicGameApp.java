//Basic Game Application
//Version 2
// Basic Object, Image, Movement
// Astronaut moves to the right.
// Threaded

//K. Chun 8/2018

//*******************************************************************************
//Import Section
//Add Java libraries needed for the game
//import java.awt.Canvas;

//Graphics Libraries
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;


//*******************************************************************************
// Class Definition Section

public class BasicGameApp implements Runnable {

   //Variable Definition Section
   //Declare the variables used in the program 
   //You can set their initial values too
   
   //Sets the width and height of the program window
	final int WIDTH = 1000;
	final int HEIGHT = 700;

   //Declare the variables needed for the graphicws
	public JFrame frame;
	public Canvas canvas;
   public JPanel panel;
   
	public BufferStrategy bufferStrategy;
	public Image aquariumBackgroundPic;
	public Image Fish1;
	public Image Fish2;
	public Image Fish3;
	public Image Fish4;
	public Image Fish5;
	public Image Fish6;

   //Declare the objects used in the program
   //These are things that are made up of more than one variable type
	private Fish fish1;
	private Fish fish2;
	private Fish fish3;
	private Fish fish4;
	private Fish fish5;
	private Fish fish6;



   // Main method definition
   // This is the code that runs first and automatically
	public static void main(String[] args) {
		BasicGameApp ex = new BasicGameApp();   //creates a new instance of the game
		new Thread(ex).start();                 //creates a threads & starts up the code in the run( ) method  
	}


   // Constructor Method
   // This has the same name as the class
   // This section is the setup portion of the program
   // Initialize your variables and construct your program objects here.
	public BasicGameApp() {
      
      setUpGraphics();
       
      //variable and objects
      //create (construct) the objects needed for the game and load up 
		Fish1 = Toolkit.getDefaultToolkit().getImage("Fish1.png"); //load the picture
		Fish2 = Toolkit.getDefaultToolkit().getImage("Fish2.png"); //load the picture
		Fish3 = Toolkit.getDefaultToolkit().getImage("Fish3.png"); //load the picture
		Fish4 = Toolkit.getDefaultToolkit().getImage("Fish4.png"); //load the picture
		Fish5 = Toolkit.getDefaultToolkit().getImage("Fish5.png"); //load the picture
		Fish6 = Toolkit.getDefaultToolkit().getImage("Fish6.png"); //load the picture

		aquariumBackgroundPic = Toolkit.getDefaultToolkit().getImage("aquariumBackgroundPib.png"); //load the picture
		fish1 = new Fish (10,100);
		fish2 = new Fish(30,100);
		fish3 = new Fish(50,100);
		fish4 = new Fish(70,100);
		fish5 = new Fish(90,100);
		fish6 = new Fish(10,300);



	}// BasicGameApp()

   
//*******************************************************************************
//User Method Section
//
// put your code to do things here.

   // main thread
   // this is the code that plays the game after you set things up
	public void run() {

      //for the moment we will loop things forever.
		while (true) {

         moveThings();  //move all the game objects
         render();  // paint the graphics
         pause(20); // sleep for 10 ms
		}
	}


	public void moveThings() {
		collisions();
		fish1.bounce();
		fish2.wrap();

	}

	public void collisions(){
      //ADD EXPLANATION HERE
		if(fish1.rec.intersects(fish2.rec) && fish1.isCrashing == false && fish1.isAlive && fish2.isAlive){
			fish1.dx = -fish1.dx;
			fish1.dy = -fish1.dy;
			fish2.dx = -fish1.dx;
			fish2.dy = -fish2.dy;
			fish2.isAlive = false;
			fish1.width = fish1.width + 30;
			fish1.height = fish1.height + 30;
			fish2.dx = fish2.dx + 30;
			fish2.dy = fish2.dy + 30;
			fish1.isCrashing = true;
		}

		if(!fish1.rec.intersects(fish2.rec)){
			fish1.isCrashing = false;
		}
	}
	
   //Pauses or sleeps the computer for the amount specified in milliseconds
   public void pause(int time ){
   		//sleep
			try {
				Thread.sleep(time);
			} catch (InterruptedException e) {

			}
   }

   //Graphics setup method
   private void setUpGraphics() {
      frame = new JFrame("Application Template");   //Create the program window or frame.  Names it.
   
      panel = (JPanel) frame.getContentPane();  //sets up a JPanel which is what goes in the frame
      panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));  //sizes the JPanel
      panel.setLayout(null);   //set the layout
   
      // creates a canvas which is a blank rectangular area of the screen onto which the application can draw
      // and trap input events (Mouse and Keyboard events)
      canvas = new Canvas();  
      canvas.setBounds(0, 0, WIDTH, HEIGHT);
      canvas.setIgnoreRepaint(true);
   
      panel.add(canvas);  // adds the canvas to the panel.
   
      // frame operations
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //makes the frame close and exit nicely
      frame.pack();  //adjusts the frame and its contents so the sizes are at their default or larger
      frame.setResizable(false);   //makes it so the frame cannot be resized
      frame.setVisible(true);      //IMPORTANT!!!  if the frame is not set to visible it will not appear on the screen!
      
      // sets up things so the screen displays images nicely.
      canvas.createBufferStrategy(2);
      bufferStrategy = canvas.getBufferStrategy();
      canvas.requestFocus();
      System.out.println("DONE graphic setup");
   
   }


	//paints things on the screen using bufferStrategy
	private void render() {
		Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
		g.clearRect(0, 0, WIDTH, HEIGHT);

		//draw the background image
		g.drawImage(aquariumBackgroundPic, 0, 0, WIDTH, HEIGHT, null);
      	//draw the image of the astronaut
		g.drawImage(Fish1, fish1.xpos, fish1.ypos, fish1.width, fish1.height, null);
		g.drawImage(Fish2, fish2.xpos, fish2.ypos, fish2.width, fish2.height, null);
		g.drawImage(Fish3, fish3.xpos, fish3.ypos, fish3.width, fish3.height, null);
		g.drawImage(Fish4, fish4.xpos, fish4.ypos, fish4.width, fish4.height, null);
		g.drawImage(Fish5, fish5.xpos, fish5.ypos, fish5.width, fish5.height, null);
		g.drawImage(Fish6, fish6.xpos, fish6.ypos, fish6.width, fish6.height, null);

		if(fish2.isAlive == true) {
			g.drawImage(Fish2, fish2.xpos, fish2.ypos, fish2.width, fish2.height, null);
		}
		g.dispose();

		bufferStrategy.show();
	}
}