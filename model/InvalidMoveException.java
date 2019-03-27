
package model;

/**
 * InvalidMoveException
 */
public class InvalidMoveException extends RuntimeException {

  InvalidMoveException(Coord from, Coord to) {
    super("Unable to move from " + from + " to " + to);
  }
}
