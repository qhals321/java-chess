package chess.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public final class Position {

    private static final int MAX_POSITION = 7;
    private static final int MIN_POSITION = 0;
    private static final Map<String, Position> positions = new HashMap<>();

    private final int column;
    private final int row;

    private Position(int column, int row) {
        this.column = column;
        this.row = row;
    }

    public static Position of(int column, int row) {
        validateValues(column, row);
        String key = "" + column + row;
        return positions.computeIfAbsent(key, newKey -> new Position(column, row));
    }

    private static void validateValues(int column, int row) {
        if (isNotValid(column, row)) {
            throw new IllegalArgumentException("체스판을 넘어서는 범위입니다.");
        }
    }

    private static boolean isNotValid(int column, int row) {
        return row < MIN_POSITION || MAX_POSITION < row || column < MIN_POSITION
            || MAX_POSITION < column;
    }

    public boolean invalidGo(Direction moveDirection) {
        return isNotValid(moveDirection.column() + column, moveDirection.row() + row);
    }

    public Position go(Direction moveDirection) {
        return Position.of(moveDirection.column() + column, moveDirection.row() + row);
    }

    public int column() {
        return column;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Position position = (Position) o;
        return column == position.column && row == position.row;
    }

    @Override
    public int hashCode() {
        return Objects.hash(column, row);
    }

    @Override
    public String toString() {
        return "Position.of(" + column + ", " + row + ')';
    }
}
