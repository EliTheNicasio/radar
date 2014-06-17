package com.eNic.GameWorld;

import com.badlogic.gdx.math.Rectangle;
import com.eNic.GameObjects.Scanner;

public class GameWorld {
    
    private Scanner scanner;
    private Rectangle ground;
    private int score = 0;
    private float runTime = 0;
    private int gameHeight, gameWidth;
    private int midPointY;
    private GameRenderer renderer;
    
    //GameStates go here
    
    public GameWorld( int gameWidth, int gameHeight) {
	//currentState = GameState.MENU
	this.midPointY = midPointY;
	this.gameWidth = gameWidth;
	this.gameHeight = gameHeight;
	scanner = new Scanner(gameWidth / 2, gameHeight / 2);
    }
    
    public void update(float delta) {
	runTime += delta;
	
	//GameStates
//	switch (currentState) {
//	case READY:
//	case MENU:
//		updateReady(delta);
//		break;
//	case RUNNING:
		updateRunning(delta);
//		break;
//	default:
//		break;
//	}
    }
    
    private void updateReady(float delta) {
	//projectile.updateReady(runTime);
	//scroller.updateReadyDelta);
    }
    
    public void updateRunning(float delta) {
	if(delta > .15f) {
	    delta = .15f;
	}
	scanner.update(delta);
    }
    
    
    //Getters
    public int getMidPointY() {
	return midPointY;
    }
    
    public int getScore() {
	return score;
    }
    
    public void addScore(int increment) {
	score+= increment;
    }
    
    public Scanner getScanner() {
	return scanner;
    }
    
    //GameStates e.g.- isReady
    
    public void setRenderer(GameRenderer renderer) {
	this.renderer = renderer;
    }
    
    public GameRenderer getRenderer() {
	return renderer;
    }
}
