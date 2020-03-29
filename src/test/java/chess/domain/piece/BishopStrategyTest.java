package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.exception.InvalidMovementException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.*;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

class BishopStrategyTest {

    @ParameterizedTest
    @DisplayName("이동 경로 찾기")
    @MethodSource("createSourceToTarget")
    void findMovePath(Position source, Position target, List<Position> expected) {
        Map<Position, GamePiece> board = new TreeMap<>(Board.createEmpty().getBoard());
        GamePiece gamePiece = ChessPiece.WHITE_BISHOP.getGamePiece();
        board.put(source, gamePiece);

        assertThatCode(() -> {
            gamePiece.validatePath(board, source, target);
        }).doesNotThrowAnyException();

    }

    static Stream<Arguments> createSourceToTarget() {
        return Stream.of(
                Arguments.of(Position.from("a1"), Position.from("d4"),
                        Arrays.asList(Position.from("b2"), Position.from("c3"))),
                Arguments.of(Position.from("d4"), Position.from("a1"),
                        Arrays.asList(Position.from("c3"), Position.from("b2"))),
                Arguments.of(Position.from("b2"), Position.from("f6"),
                        Arrays.asList(Position.from("c3"), Position.from("d4"), Position.from("e5"))),
                Arguments.of(Position.from("f6"), Position.from("b2"),
                        Arrays.asList(Position.from("e5"), Position.from("d4"), Position.from("c3"))),
                Arguments.of(Position.from("g5"), Position.from("f4"), Collections.emptyList())
        );
    }

    @ParameterizedTest
    @DisplayName("이동할 수 없는 source, target")
    @MethodSource("createInvalidTarget")
    void invalidMovementException(Position target) {
        Position source = Position.from("d5");
        Map<Position, GamePiece> board = new TreeMap<>(Board.createEmpty().getBoard());
        GamePiece gamePiece = ChessPiece.WHITE_BISHOP.getGamePiece();
        board.put(source, gamePiece);

        assertThatThrownBy(() -> {
            gamePiece.validatePath(board, source, target);
        }).isInstanceOf(InvalidMovementException.class)
                .hasMessage("이동할 수 없습니다.\n이동할 수 없는 경로입니다.");
    }

    static Stream<Arguments> createInvalidTarget() {
        return Stream.of(
                Arguments.of(Position.from("c5")),
                Arguments.of(Position.from("e5")),
                Arguments.of(Position.from("d6")),
                Arguments.of(Position.from("g3"))
        );
    }

    @Test
    @DisplayName("장애물이 있을 경우")
    void obstacle() {
        Map<Position, GamePiece> board = new TreeMap<>(Board.createEmpty().getBoard());
        Position source = Position.from("d5");
        Position target = Position.from("f7");
        GamePiece piece = ChessPiece.WHITE_BISHOP.getGamePiece();

        Position obstacle = Position.from("e6");

        board.put(source, piece);
        board.put(obstacle, ChessPiece.BLACK_PAWN.getGamePiece());

        assertThatThrownBy(() -> {
            piece.validatePath(board, source, target);
        }).isInstanceOf(InvalidMovementException.class)
                .hasMessage("이동할 수 없습니다.\n경로에 기물이 존재합니다.");
    }
}