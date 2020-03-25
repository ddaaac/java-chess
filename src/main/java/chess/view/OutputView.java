package chess.view;

import java.util.List;

import chess.domain.board.Board;
import chess.domain.piece.GamePiece;

public class OutputView {

    public static void printStart() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : start");
        System.out.println("> 게임 종료 : end");
        System.out.println("> 게임 이동 : move source위치 target위 - 예. move b1 b3");
    }

    public static void printBoard(Board board) {
        for (List<GamePiece> gamePiece : board.gamePieces()) {
            for (GamePiece piece : gamePiece) {
                System.out.print(piece.getName());
            }
            System.out.println();
        }
    }
}
