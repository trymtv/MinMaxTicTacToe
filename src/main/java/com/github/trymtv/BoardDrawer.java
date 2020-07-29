package com.github.trymtv;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class BoardDrawer {
	private final Canvas canvas;
	private final GraphicsContext context;
	private final double width, height;
	private final double spacing;
	private final Image xIcon = new Image(App.class.getResource("x_icon.png").toString());
	private final Image oIcon = new Image(App.class.getResource("o_icon.png").toString());


	public BoardDrawer(Canvas canvas){
		this.canvas = canvas;
		context = canvas.getGraphicsContext2D();
		width = canvas.getWidth();
		height = canvas.getHeight();
		spacing = width / 3 + 4;
	}

	public void drawPlayer(char player, int x, int y){
		if(player == 'x')
			context.drawImage(xIcon, x * spacing, y*spacing);
		else if(player == 'o'){
			context.drawImage(oIcon, x *spacing, y*spacing);
		}else{
			throw new IllegalArgumentException("Not a valid player character");
		}
	}


}
