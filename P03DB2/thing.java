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
import processing.core.PApplet;
import processing.core.PImage;

import java.io.File;

/**
 * ClassName: thing Package PACKAGE_NAME Description Author zwang2654
 *
 * @Create 2/18/2023 9:48 PM
 * @Version 1.0
 */

public class Thing {

  private static PApplet processing;
  private PImage image;
  private float x;
  private float y;

  public Thing(float x, float y, String imageFilename) {
    this.x = x;
    this.y = y;
    this.image = processing.loadImage("images" + File.separator + imageFilename);
  }

  public static void setProcessing(){
    processing = Badger.getProcessing();
  }

  public void draw() {
    processing.image(image, x, y);
  }

  public float getX() {
    return x;
  }

  public float getY() {
    return y;
  }

  public void setX(float x) {
    this.x = x;
  }

  public void setY(float y) {
    this.y = y;
  }
  public PImage getImage(){return image;}
}
