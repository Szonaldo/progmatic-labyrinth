package com.progmatic.labyrinthproject.Players;

import com.progmatic.labyrinthproject.enums.Direction;
import com.progmatic.labyrinthproject.interfaces.Labyrinth;
import com.progmatic.labyrinthproject.interfaces.Player;

import java.util.ArrayList;
import java.util.List;

public class WallFollowerPlayer implements Player {

    Direction myDirection;

    // jobb fal
    // EAST - SOUTH
    // SOUTH - WEST
    // WEST - NORTH
    // NORTH - EAST

    @Override
    public Direction nextMove(Labyrinth l) {
        List<Direction> directions = new ArrayList<>();

        if (myDirection == null || directions.size() == 1) {
            myDirection = directions.get(0);
        }

        for (Direction direction : directions) {
            
        }

//        switch (myDirection) {
//            case SOUTH:
//                if (directions.size() > 2 && directions.contains(Direction.WEST)) {
//                    myDirection = Direction.WEST;
//                    return myDirection;
//                } else if (directions.size() == 1 ) {
//                    myDirection = directions.get(0);
//                    return myDirection;
//                }
//                break;
//            case WEST:
//                break;
//            case NORTH:
//                break;
//            case EAST:
//                break;
//            default:
//                myDirection = directions.get(0);
//        }
        return myDirection;

    }
}
