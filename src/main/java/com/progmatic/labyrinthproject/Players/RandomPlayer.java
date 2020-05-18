package com.progmatic.labyrinthproject.Players;

import com.progmatic.labyrinthproject.enums.Direction;
import com.progmatic.labyrinthproject.interfaces.Labyrinth;
import com.progmatic.labyrinthproject.interfaces.Player;

import java.util.List;
import java.util.Random;

public class RandomPlayer implements Player {

    @Override
    public Direction nextMove(Labyrinth l) {
        List<Direction> directions = l.possibleMoves();
        Random r = new Random();
        return directions.get(r.nextInt(directions.size()));
    }
}
