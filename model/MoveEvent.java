
package model;

/**
 * MoveEvent
 */
public class MoveEvent {
  public Coord from;
  public Coord to;

  MoveEvent(Coord from, Coord to) {
    this.from = from;
    this.to = to;
  }
}