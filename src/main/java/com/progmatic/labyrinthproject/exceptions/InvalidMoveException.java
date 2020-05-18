package com.progmatic.labyrinthproject.exceptions;

import com.progmatic.labyrinthproject.enums.Direction;

/**
 *
 * @author pappgergely
 */
public class InvalidMoveException extends Exception {

    private Direction direction;

    public InvalidMoveException(Direction direction) {
        this.direction = direction;
    }

}
