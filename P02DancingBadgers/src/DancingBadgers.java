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
import processing.core.PImage;

import java.io.File;
import java.util.Arrays;
import java.util.Random;

/**
 * @author zwang
 * this class creates an interaction panel that can create, remove, dragging badgers function with
 * fixed background image and color. Utility and Badger classes are used internally.
 */
public class DancingBadgers {

  private static Random randGen = new Random();
  private static PImage backgroundImage;
  private static Badger[] badgers = new Badger[5];

  /**
   * this method is called once when the program starts, it sets the background color
   */
  public static void setup() {
    // background color
    Utility.background(Utility.color(255, 218, 185));
    // Random setup_ran = randGen;

//    creating rand badgers in array
//    for (int i = 0; i <= (badgers.length - 1); i++) {
//      badgers[i] = new Badger();
//      badgers[i] = new Badger(setup_ran.nextInt(Utility.width()), setup_ran.nextInt(Utility.height()));
//    }

  }

  /**
   * this class is called repetitively as long as the program is running, it refreshes the background
   * so that the old image can be covered and updated. It draws the badger that is in the badgers array
   * as long as those are not null
   */

  public static void draw() {
    Utility.background(Utility.color(255, 218, 185));
    // load the image of the background
    PImage backgroundImage = Utility.loadImage("images" + File.separator + "background.png");

    // Draw the background image to the center of the screen
    Utility.image(backgroundImage, Utility.width() / 2, Utility.height() / 2);

    for (int i = 0; i <= (badgers.length - 1); i++) {
      if (badgers[i]!=null){badgers[i].draw();}
    }

  }



  /**
   * Checks if the mouse is over a specific Badger whose reference is provided
   * as input parameter
   * *
   @param Badger reference to a specific Badger object
    * @return true if the mouse is over the specific Badger object passed as input
   * (i.e. over the image of the Badger), false otherwise
   */
  public static boolean isMouseOver(Badger Badger) {
    if (Badger == null){return false;}
    // x position
    float left = (float) Badger.getX() - (float) Badger.image().width/2;
    float right = (float) Badger.getX() + (float) Badger.image().width/2;
    //y position
    float top = (float) Badger.getY() + (float) Badger.image().height/2;
    float bottom = (float) Badger.getY() - (float) Badger.image().height/2;

    float mouse_x = Utility.mouseX();
    float mouse_y = Utility.mouseY();

    if (Utility.mouseX()>left && Utility.mouseX()<right && Utility.mouseY()>bottom && Utility.mouseY()<top){
      return true;
    }
    else{return false;}

  }

  /**
   * Callback method called each time the user presses the mouse
   * start dragging the only one badger that mouse is on
   */
  public static void mousePressed() {
    for (int i = 0; i <= (badgers.length - 1); i++) {
      if (isMouseOver(badgers[i])) {
        badgers[i].startDragging();
        break;
      }
    }
  }
  /**
   * Callback method called each time the mouse is released
   * stop dragging the all the badgers
   */
  public static void mouseReleased() {
    for (int i = 0; i <= (badgers.length - 1); i++) {
      if (badgers[i]!=null){badgers[i].stopDragging();}

    }
  }

  /**
   * Callback method called each time the user presses a key
   * add a badger when pressed B key
   * remove a badger when pressed R key which the mouse is on
   */
  public static void keyPressed() {
    for (int i = 0; i <= (badgers.length - 1); i++) {
      int key = Utility.key();
      if ((badgers[i] == null) && (key == 'b' || key == 'B')) {
        badgers[i] = new Badger();
        badgers[i] = new Badger(randGen.nextInt(Utility.width()), randGen.nextInt(Utility.height()));
        break;
      }
      else if ((badgers[i] != null) && (key == 'r' || key == 'R') && (isMouseOver(badgers[i]))) {
        badgers[i] = null;
        break;
      }
    }
  }










}









