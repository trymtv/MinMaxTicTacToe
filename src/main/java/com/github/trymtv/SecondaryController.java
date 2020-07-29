package com.github.trymtv;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;

public class SecondaryController {

    @FXML
    private Canvas boardCanvas;

    private Board board;
    private BoardDrawer drawer;

    public void initialize(){
        board = new Board();
        drawer = new BoardDrawer(boardCanvas);
    }

    @FXML
    private void makeMove(MouseEvent e){
        int x = (int) e.getX() / 141;
        int y = (int) e.getY() / 141;
        if(board.move(x, y, 'x'))
            drawer.drawPlayer('x', x, y);
    }

}