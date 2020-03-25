package chess.domain.piece;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import chess.domain.board.Position;
import chess.domain.exception.InvalidMovementException;

public class KnightStrategy implements MoveStrategy {

    private final List<Direction> directions = Arrays.asList();

    @Override
    public List<Position> findMovePath(final Position source, final Position target) {
        for (Direction direction : directions) {
            Position position = source.destinationOf(direction).orElse(null);
            if (target.equals(position)) {
                return Collections.emptyList();
            }
        }

        throw new InvalidMovementException();
    }
}