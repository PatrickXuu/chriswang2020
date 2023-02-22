//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    (descriptive title of the program making use of this file)
// Course:   CS 300 Spring 2023
//
// Author:   zhenghong (chris) Wang
// Email:    zwang2654@wisc.edu
// Lecturer: (Mouna Kacem)
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:    no
// Partner Email:  no
// Partner Lecturer's Name: no
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   ___ Write-up states that pair programming is allowed for this assignment.
//   ___ We have both read and understand the course Pair Programming Policy.
//   ___ We have registered our team prior to the team registration deadline.
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons:   no
// Online Sources:  https://openai.com/blog/chatgpt/ & IDEA IDE
//
///////////////////////////////////////////////////////////////////////////////
import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import processing.core.PImage;

/**
 * This is the main class for the p03 Dancing Bangers II program
 *
 */
public class DancingBadgers {

  private static PImage backgroundImage; // backgound image
  private static ArrayList<Badger> badgers; // array storing badger objects
  private static Random randGen; // Generator of random numbers
  private static int badgersCountMax; // max number of badgers allowed to create
  private static ArrayList<Thing> things; //arraylist storing Thing objects
  private static ArrayList<StarshipRobot> robots; // robots storing starshiprobot object

  /**
   * Driver method to run this graphic application
   * 
   * @param args
   */
  public static void main(String[] args) {
    Utility.runApplication();
  }

  /**
   * Defines initial environment properties of this graphic application
   */
  public static void setup() {
    badgersCountMax = 5;
    backgroundImage = Utility.loadImage("images" + File.separator + "background.png");
    badgers = new ArrayList<Badger>();
    Thing.setProcessing();
    things = new ArrayList<Thing>();
    things.add(new Thing(50, 50, "target.png"));
    things.add(new Thing(750, 550, "target.png"));
    things.add(new Thing(750, 50, "shoppingCounter.png"));
    things.add(new Thing(50, 550, "shoppingCounter.png"));
    randGen = new Random();


    StarshipRobot.setProcessing();
    robots = new ArrayList<StarshipRobot>();
    robots.add(new StarshipRobot(new Thing(750, 50, "shoppingCounter.png"),
        new Thing(50, 50, "target.png"), 3));

    robots.add(new StarshipRobot(new Thing(50, 550, "shoppingCounter.png"),
        new Thing(750, 550, "target.png"), 3));
  }


  /**
   * Callback method that draws and updates the application display window. This method runs in an
   * infinite loop until the program exits.
   */
  public static void draw() {
    Utility.background(Utility.color(255, 218, 185));
    Utility.image(backgroundImage, Utility.width() / 2, Utility.height() / 2);
    for (Thing thing : things) {
      thing.draw();
    }
    for (Badger b : badgers){
      b.draw();
    }
    for (StarshipRobot robot : robots){
      robot.draw();
    }
  }

  /**
   * Callback method called each time the user presses the mouse
   */
  public static void mousePressed() {
    for (Badger b : badgers) {
      if (b.isMouseOver()) {
        b.startDragging();
      }
    }
  }

  /**
   * Callback method called each time the mouse is released
   */
  public static void mouseReleased() {
    for (Badger b : badgers){
      b.stopDragging();
    }
  }

  /**
   * Checks if the mouse is over a given badger whose reference is provided as input parameter
   * 
   * @param badger reference to a given badger object
   * @return true if the mouse is over the given badger object (i.e. over the image of the badger),
   *         false otherwise
   */
  public static boolean isMouseOver(Badger badger) {
    int badgerWidth = badger.image().width;
    int badgerHeight = badger.image().height;

    // checks if the mouse is over the badger
    return Utility.mouseX() >= badger.getX() - badgerWidth / 2
        && Utility.mouseX() <= badger.getX() + badgerWidth / 2
        && Utility.mouseY() >= badger.getY() - badgerHeight / 2
        && Utility.mouseY() <= badger.getY() + badgerHeight / 2;
  }

  /**
   * Callback method called each time the user presses a key
   */
  public static void keyPressed() {

      switch (Character.toUpperCase(Utility.key())) {
        case 'B':
          if (badgers.size() < badgersCountMax){
            badgers.add(new Badger(randGen.nextInt(Utility.width()), randGen.nextInt(Utility.height())));
          break;
          }
          break;


        case 'R':

          for (Badger b : badgers) {
            if (b != null && isMouseOver(b)) {
              badgers.remove(b);
              break;
            }
          }
          break;
      }

    }


}
