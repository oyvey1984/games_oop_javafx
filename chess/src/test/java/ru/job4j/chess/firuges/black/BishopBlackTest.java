package ru.job4j.chess.firuges.black;

import org.junit.jupiter.api.Test;
import ru.job4j.chess.ImpossibleMoveException;
import ru.job4j.chess.firuges.Cell;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BishopBlackTest {

    @Test
    public void whenStartPositionActually() {
        BishopBlack bishopBlack = new BishopBlack(Cell.A1);
        assertThat(bishopBlack.position()).isEqualTo(Cell.A1);
    }

    @Test
    public void whenCopyToDestIsOk() {
        BishopBlack bishopBlack = new BishopBlack(Cell.A1);
        assertThat(bishopBlack.copy(Cell.E4).position()).isEqualTo(Cell.E4);
    }

    @Test
    public void whenWayToDownAndRightIsOk() {
        BishopBlack bishopBlack = new BishopBlack(Cell.C1);
        Cell[] wayArray = {Cell.D2, Cell.E3, Cell.F4, Cell.G5};
        assertThat(bishopBlack.way(Cell.G5)).isEqualTo(wayArray);
    }

    @Test
    public void whenWayToDownAndLeftIsOk() {
        BishopBlack bishopBlack = new BishopBlack(Cell.E3);
        Cell[] wayArray = {Cell.F2, Cell.G1};
        assertThat(bishopBlack.way(Cell.G1)).isEqualTo(wayArray);
    }

    @Test
    public void whenWayToTopAndRightIsOk() {
        BishopBlack bishopBlack = new BishopBlack(Cell.H4);
        Cell[] wayArray = {Cell.G5, Cell.F6, Cell.E7, Cell.D8};
        assertThat(bishopBlack.way(Cell.D8)).isEqualTo(wayArray);
    }

    @Test
    public void whenWayToTopAndLeftIsOk() {
        BishopBlack bishopBlack = new BishopBlack(Cell.E3);
        Cell[] wayArray = {Cell.D2, Cell.C1};
        assertThat(bishopBlack.way(Cell.C1)).isEqualTo(wayArray);
    }

    @Test
    public void whenIsDiagonalIsFalls() throws ImpossibleMoveException {
        BishopBlack bishopBlack = new BishopBlack(Cell.A1);
        ImpossibleMoveException exception = assertThrows(ImpossibleMoveException.class, () -> {
            bishopBlack.way(Cell.B6);
        });
        assertThat(exception.getMessage()).isEqualTo("Could not move by diagonal from A1 to B6");
    }
}