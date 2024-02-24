package ru.job4j.chess;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.black.BishopBlack;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LogicTest {

    @Test
    public void whenMoveThenFigureNotFoundException()
            throws FigureNotFoundException, OccupiedCellException, ImpossibleMoveException {
        Logic logic = new Logic();
        FigureNotFoundException exception = assertThrows(FigureNotFoundException.class, () -> {
            logic.move(Cell.C1, Cell.H6);
        });
        assertThat(exception.getMessage()).isEqualTo("Figure not found on the board.");
    }

    @Test
    public void whenMoveThenOccupiedCellException() throws OccupiedCellException,
            FigureNotFoundException, ImpossibleMoveException {
        Logic logic = new Logic();
        BishopBlack bishopBlack = new BishopBlack(Cell.C1);
        BishopBlack bishopBlack2 = new BishopBlack(Cell.D2);
        logic.add(bishopBlack);
        logic.add(bishopBlack2);
        OccupiedCellException exception = assertThrows(OccupiedCellException.class, () -> {
            logic.move(Cell.C1, Cell.F4);
        });
        assertThat(exception.getMessage()).isEqualTo("Occupied cells on the way.");
    }

    @Test
    public void whenMoveThenOImpossibleMoveException() throws OccupiedCellException,
            FigureNotFoundException, ImpossibleMoveException {
        Logic logic = new Logic();
        BishopBlack bishopBlack = new BishopBlack(Cell.C1);
        logic.add(bishopBlack);
        ImpossibleMoveException exception = assertThrows(ImpossibleMoveException.class, () -> {
            logic.move(Cell.C1, Cell.C2);
        });
        assertThat(exception.getMessage()).isEqualTo("Could not move by diagonal from C1 to C2");
    }

    @Disabled
    @Test
    public void whenMoveThenFigureCopyToDest()
            throws FigureNotFoundException, OccupiedCellException, ImpossibleMoveException{
        Logic logic = new Logic();
        BishopBlack bishopBlack = new BishopBlack(Cell.C1);
        logic.add(bishopBlack);
        logic.move(Cell.C1, Cell.F4);
        assertThat(bishopBlack.position()).isEqualTo(Cell.F4);
        //не можем обновиться изначального бишопа в рамках теста
        //не можем получить фигуру по ячейке F4
    }
}