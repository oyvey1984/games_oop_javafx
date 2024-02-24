package ru.job4j.chess.firuges.black;

import ru.job4j.chess.ImpossibleMoveException;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;

public class BishopBlack implements Figure {
    private final Cell position;

    public BishopBlack(final Cell ps) {
        position = ps;
    }

    @Override
    public Cell position() {
        return position;
    }

    @Override
    public Cell[] way(Cell dest) {
        if (!isDiagonal(position, dest)) {
            throw new ImpossibleMoveException(
                    String.format("Could not move by diagonal from %s to %s", position, dest)
            );
        }
        int size = Math.abs(position.getX() - dest.getX());
        Cell[] steps = new Cell[size];
        int x = position.getX();
        int y = position.getY();
        int deltaX = position.getX() - dest.getX();
        int deltaY = position.getY() - dest.getY();
        for (int index = 0; index < size; index++) {
            if (deltaX > 0 && deltaY > 0) {
                x--;
                y--;
                steps[index] = Cell.findBy(x, y);
            }
            if (deltaX < 0 && deltaY < 0) {
                x++;
                y++;
                steps[index] = Cell.findBy(x, y);
            }
            if (deltaX > 0 && deltaY < 0) {
                x--;
                y++;
                steps[index] = Cell.findBy(x, y);
            }
            if (deltaX < 0 && deltaY > 0) {
                x++;
                y--;
                steps[index] = Cell.findBy(x, y);
            }
        }
        return steps;
    }

    public boolean isDiagonal(Cell source, Cell dest) {
        return (dest != null && Math.abs(source.getX() - dest.getX()) == Math.abs(source.getY() - dest.getY()));
    }

    @Override
    public Figure copy(Cell dest) {
        return new BishopBlack(dest);
    }
}