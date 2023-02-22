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
// File header comes here
import java.io.File;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * This class models a Badger object in the P03 Dancing Badgers programming assignment
 *
 */
public class Badger {


  // badger's identification fields

  // Fields defined to draw the badger in the application display window
  private static PApplet processing; // PApplet object that represents the display window

  private PImage image; // badger's image

  private float x; // badger's x-position in the display window
  private float y; // badger's y-position in the display window

  private boolean isDragging; // indicates whether the badger is being dragged or not
  private static int oldMouseX; // old x-position of the mouse
  private static int oldMouseY; // old y-position of the mouse

  public static PApplet getProcessing(){
    return processing;
  }
  /**
   * Creates a new badger object positioned at the center of the display window
   * 
   */
  public Badger() {
    this(processing.width / 2.0f, processing.height / 2.0f);

  }

  /**
   * Creates a new badger object positioned at a specific position of the display window
   * 
   * @param x x-position of this Badger object within the display window
   * @param y y-position of this Badger object within the display window
   */
  public Badger(float x, float y) {
    // Set badger draw parameters
    this.image = processing.loadImage("images" + File.separator + "badger.png");
    // sets the position of the badger object
    this.x = x;
    this.y = y;
    isDragging = false; // initially the badger is not dragging
  }

  /**
   * checks if mouse is over this badger to the display window.
   */

  public boolean isMouseOver() {
    int badgerWidth = this.image().width;
    int badgerHeight = this.image().height;
    return Utility.mouseX() >= this.getX() - badgerWidth / 2
        && Utility.mouseX() <= this.getX() + badgerWidth / 2
        && Utility.mouseY() >= this.getY() - badgerHeight / 2
        && Utility.mouseY() <= this.getY() + badgerHeight / 2;
  }


  /**
   * Draws this badger to the display window. It sets also its position to the mouse position if
   * this badger is being dragged (i.e. if its isDragging field is set to true).
   */
  public void draw() {
    // if the badger is dragging, set its position to the mouse position
    if (this.isDragging) {
      drag();
    }

    // draw the badger at its current position
    processing.image(this.image, x, y);

  }



  /**
   * Returns a reference to the image of this badger
   * 
   * @return the image of type PImage of the badger object
   */
  public PImage image() {
    return image;
  }


  /**
   * Returns the x-position of this badger in the display window
   * 
   * @return the X coordinate of the badger position
   */
  public float getX() {
    return x;
  }

  /**
   * Returns the y-position of this badger in the display window
   * 
   * @return the y-position of the badger
   */
  public float getY() {
    return y;
  }


  /**
   * Sets the x-position of this badger in the display window
   * 
   * @param x the x-position to set
   */
  public void setX(float x) {
    this.x = x;
  }

  /**
   * Sets the y-position of this badger in the display window
   * 
   * @param y the y-position to set
   */
  public void setY(float y) {
    this.y = y;
  }


  /**
   * Checks whether this badger is being dragged
   * 
   * @return true if the badger is being dragged, false otherwise
   */
  public boolean isDragging() {
    return isDragging;
  }

  
  /**
   * Helper method to drag this Badger object to follow the mouse moves
   * 
   */
  private void drag() {
    
    int dx = processing.mouseX - oldMouseX;
    int dy = processing.mouseY - oldMouseY;
    x+=dx;
    y+=dy;
    
    if(x > 0)
      x = Math.min(x, processing.width);
    else
      x = 0;
    if(y > 0)
     y = Math.min(y, processing.height);
    else
      y = 0;
    oldMouseX = processing.mouseX;
    oldMouseY = processing.mouseY;
  }
  
  /**
   * Starts dragging this badger
   * 
   */
  public void startDragging() {
    oldMouseX = processing.mouseX;
    oldMouseY = processing.mouseY;
    this.isDragging = true;
    drag();
  }
  
  /**
   * Stops dragging this Badger object
   * 
   */
  public void stopDragging() {
    this.isDragging = false;
  }

  /**
   * Sets the PApplet object display window where this badger will be drawn
   * 
   * @param processing PApplet object that represents the display window
   */
  public static void setProcessing(PApplet processing) {
    Badger.processing = processing;
  }

}
