package chess.service;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.player.Player;
import chess.domain.score.Score;

import java.util.Map;

public class ChessService {

    public Board initialize() {
        return Board.createInitial();
    }

    public Board move(Board board, String sourceInput, String targetInput) {
        Position source = Position.from(sourceInput);
        Position target = Position.from(targetInput);

        return board.move(source, target);
    }

    public Map<Player, Score> calculateScore(Board board) {
        return board.calculateScore();
    }

    public boolean checkGameNotFinished(Board board) {
        return board.isNotFinished();
    }
}
