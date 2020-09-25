package com.github.trymtv;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class SecondaryController {

    private char next = 'x';
    private boolean useMinMax = false;

    public void setUseMinMax(boolean useMinMax) {
        this.useMinMax = useMinMax;
    }

    @FXML
    private Canvas boardCanvas;
    @FXML
    private Label winLabel;

    private Board board;
    private BoardDrawer drawer;

    public void initialize(){
        board = new Board();
        drawer = new BoardDrawer(boardCanvas);
    }

    @FXML
    private void clickMove(MouseEvent e){
        int x = (int) e.getX() / 141;
        int y = (int) e.getY() / 141;
        makeMove(x, y);
        if (useMinMax && board.isEmptyCell())
            moveMinMax();
    }

    private void moveMinMax(){
        int[] nextMove = next == 'x' ? board.minMax('x', 'o') : board.minMax('o', 'x');
        makeMove(nextMove[0], nextMove[1]);
    }

    private void makeMove(int x, int y){
        char winner;
        if(board.move(x, y, next)) {
            drawer.drawPlayer(next, x, y);
            if (!board.isEmptyCell() || board.checkWin() != 0) {
                winner = board.checkWin();
                if (winner == 0)
                    winLabel.setText("Draw!");
                else
                    winLabel.setText(winner + " won!");
                winLabel.setVisible(true);
                boardCanvas.setOnMouseClicked(this::resetGame);
            }
            next = next == 'x' ? 'o' : 'x';
        }
    }

    @FXML
    private void resetGame(MouseEvent e){
        board = new Board();
        boardCanvas.getGraphicsContext2D().clearRect(0,0, boardCanvas.getWidth(), boardCanvas.getHeight());
        winLabel.setVisible(false);
        boardCanvas.setOnMouseClicked(this::clickMove);
        moveMinMax();
    }

}