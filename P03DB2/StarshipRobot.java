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

import java.awt.*;

/**
 * ClassName: StarshipRobot Package PACKAGE_NAME Description Author zwang2654
 *
 * @Create 2/18/2023 10:26 PM
 * @Version 1.0
 */
public class StarshipRobot {
  private static PApplet processing;
  private int speed;
  private PImage image;
  private float x;
  private float y;
  private Thing source;
  private Thing destination;

  /**
   * constructor that give source and destination of one object with constant speed
   * @param source
   * @param destination
   * @param speed
   */
  public StarshipRobot(Thing source, Thing destination, int speed) {
    // source: Thing objet referring to the start point of this StarshipRobot
    // destination: Thing object referring to the destination point of this StarshipRobot
    // speed: movement speed of this StarshipRobot.

    this.source = source;
    this.destination = destination;
    this.speed = speed;
    image = processing.loadImage("images" + java.io.File.separator + "starshipRobot.png");

    // Set the initial position to the source position
    x = source.getX();
    y = source.getY();
  }
  /**
   * gets the reference of the Starship image object
   */
  public PImage image() {
    // returns a reference to the PImage of the current StarshipRobot object
    return image;
  }
  /**
   * gets the x-position of the current StarshipRobot object
   * @param x
   */
  public float getX() {
    // returns the x-position of the current StarshipRobot object
    return x;
  }
  /**
   * gets the y-position of the current StarshipRobot object
   * @param y
   */
  public float getY() {
    // returns the y-position of the current StarshipRobot object
    return y;
  }
  /**
   * sets the x-position of the current StarshipRobot object
   * @param x
   */
  public void setX(float x) {
    // sets the x-position of the current StarshipRobot object
    x = getX();
  }

  /**
   * sets the y-position of the current StarshipRobot object
   * @param y
   */
  public void setY(float y) {
    y = getY();
  }

  /**
   * set the processing equals the Badgers processing
   */
  public static void setProcessing() {
    // sets the processing PApplet static field to the processing
    // of the Badger class.
    processing = Badger.getProcessing();
  }

  /**
   * draw the starship at position x,y
   */
  public void draw() {
    // draws this StarshipRobot to the display window at its current
    // (x,y) position by calling processing.image() method
    processing.image(image, x, y);
    this.go();
  }

  /**
   * make the starship move towards destination
   */
  private void moveTowardsDestination() {
    float dx = destination.getX() - x;
    float dy = destination.getY() - y;
    int distance = (int) Math.sqrt(dx*dx + dy*dy);

    double newX = x + speed * dx / distance;
    double newY = y + speed * dy / distance;
    x = (int) newX;
    y = (int) newY;
  }

  /**
   * check the position of starship and destination whether they overlap
   * @param t
   * @return whether starship is over desitination
   */
  public boolean isOver(Thing t) {
    double robotLeft = x - image.width / 2.0;
    double robotRight = x + image.width / 2.0;
    double robotTop = y - image.height / 2.0;
    double robotBottom = y + image.height / 2.0;

    double thingLeft = t.getX() - t.getImage().width / 2.0;
    double thingRight = t.getX() + t.getImage().width / 2.0;
    double thingTop = t.getY() - t.getImage().height / 2.0;
    double thingBottom = t.getY() + t.getImage().height / 2.0;

    return robotRight >= thingLeft && robotLeft <= thingRight && robotBottom >= thingTop && robotTop <= thingBottom;
  }

/**
 * Move the StarshipRobot ONE step towards its destination
 * if destination is reached (meaning the StarshipRobot is over its destination),
 * switch source and destination
 */
  public void go() {
    if (isOver(destination)) {
      Thing temp = source;
      source = destination;
      destination = temp;
    }
    this.moveTowardsDestination();
  }

}
