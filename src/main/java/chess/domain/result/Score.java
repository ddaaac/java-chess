package chess.domain.result;

import chess.domain.piece.GamePiece;
import chess.domain.piece.newGamePiece;

import java.util.Map;
import java.util.Objects;

public class Score {

    private static final Score ZERO = new Score(0);
    private static final double SAME_COLUMN_PAWN_SCORE = 0.5;

    private final double score;

    private Score(double score) {
        this.score = score;
    }

    public static Score of(Map<newGamePiece, Integer> piecesCount, int sameFilePawnCount) {
        Score score = Score.ZERO;
        for (Map.Entry<newGamePiece, Integer> entry : piecesCount.entrySet()) {
            score = score.plus(entry.getKey().calculateScore(entry.getValue()));
        }
        score = score.minus(sameFilePawnCount * SAME_COLUMN_PAWN_SCORE);
        return score;
    }

    public static Score from(double score) {
        return new Score(score);
    }

    private Score plus(double score) {
        return new Score(this.score + score);
    }

    private Score minus(double score) {
        return new Score(this.score - score);
    }

    public double getScore() {
        return score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Score))
            return false;
        Score score1 = (Score) o;
        return Double.compare(score1.score, score) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(score);
    }
}
