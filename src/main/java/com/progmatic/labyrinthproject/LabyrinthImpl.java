package com.progmatic.labyrinthproject;

import com.progmatic.labyrinthproject.enums.CellType;
import com.progmatic.labyrinthproject.enums.Direction;
import com.progmatic.labyrinthproject.exceptions.CellException;
import com.progmatic.labyrinthproject.exceptions.InvalidMoveException;
import com.progmatic.labyrinthproject.interfaces.Labyrinth;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author pappgergely
 */
public class LabyrinthImpl implements Labyrinth {

    private CellType[][] labyrinthArray;
    int width;
    int height;
    Coordinate playerPosition;
    Coordinate end;

    public LabyrinthImpl() {
        width = -1;
        height = -1;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public void loadLabyrinthFile(String fileName) {
        try {
            Scanner sc = new Scanner(new File(fileName));
            width = Integer.parseInt(sc.nextLine());
            height = Integer.parseInt(sc.nextLine());
            setSize(width,height);
            CellType type;

            for (int hh = 0; hh < height; hh++) {
                String line = sc.nextLine();
                for (int ww = 0; ww < width; ww++) {
                    switch (line.charAt(ww)) {
                        case 'W':
                            type = CellType.WALL;
                            break;
                        case 'E':
                            type = CellType.END;
                            break;
                        case 'S':
                            type = CellType.START;
                            break;
                        default:
                            type = CellType.EMPTY;
                    }
                    setCellType(new Coordinate(hh,ww),type);
                }
            }
        } catch (FileNotFoundException | NumberFormatException ex) {
            System.out.println(ex.toString());
        } catch (CellException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public CellType getCellType(Coordinate c) throws CellException {
        checkBounds(c);
        return labyrinthArray[c.getRow()][c.getCol()];
    }

    @Override
    public void setSize(int width, int height) {
        this.width = width;
        this.height = height;
        labyrinthArray = new CellType[height][width];
    }

    @Override
    public void setCellType(Coordinate c, CellType type) throws CellException {
        checkBounds(c);
        if (type.equals(CellType.START)) {
            playerPosition = new Coordinate(c.getCol(),c.getRow());
        }
        if (type.equals(CellType.END)) {
            end = new Coordinate(c.getCol(),c.getRow());
        }
        labyrinthArray[c.getRow()][c.getCol()] = type;
    }

    @Override
    public Coordinate getPlayerPosition() {
        return playerPosition;
    }

    @Override
    public boolean hasPlayerFinished() {
        return playerPosition.equals(end);
    }

    @Override
    public List<Direction> possibleMoves() {
        List<Direction> possibleMovesList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            try {
                CellType cell;
                //TODO: simplify

                //North
                if (i == 0){
                    cell = getCellType(new Coordinate(playerPosition.getCol(),playerPosition.getRow() - 1));
                    if (cell.equals(CellType.EMPTY)) {
                        possibleMovesList.add(Direction.NORTH);
                    }
                }

                // South
                if (i == 1) {
                    cell = getCellType(new Coordinate(playerPosition.getCol(), playerPosition.getRow() + 1));
                    if (cell.equals(CellType.EMPTY)) {
                        possibleMovesList.add(Direction.SOUTH);
                    }
                }
                // East
                if (i == 2) {
                    cell = getCellType(new Coordinate(playerPosition.getCol() + 1, playerPosition.getRow()));
                    if (cell.equals(CellType.EMPTY)) {
                        possibleMovesList.add(Direction.EAST);
                    }
                }

                // West
                if (i == 3) {
                    cell = getCellType(new Coordinate(playerPosition.getCol() - 1, playerPosition.getRow()));
                    if (cell.equals(CellType.EMPTY)) {
                        possibleMovesList.add(Direction.WEST);
                    }
                }
            } catch (CellException ex) {
                ex.getMessage();
            }

        }
        return possibleMovesList;
    }

    @Override
    public void movePlayer(Direction direction) throws InvalidMoveException {
        
    }

    private void checkBounds(Coordinate c) throws  CellException{
        if (c.getCol() >= width || c.getCol() < 0 || c.getRow() >= height || c.getRow() < 0) {
            throw new CellException(c.getRow(),c.getCol(),"Out of labyrinth cell call");
        }
    }

}
