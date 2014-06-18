package com.eNic.GameWorld;

import java.util.ArrayList;

import com.badlogic.gdx.math.Rectangle;
import com.eNic.GameObjects.Blip;
import com.eNic.GameObjects.Scanner;

public class GameWorld {
    
    private Scanner scanner;
    private ArrayList<Blip> blips;
    private Rectangle ground;
    private int score = 0;
    private float runTime = 0;
    private int gameHeight, gameWidth;
    private int midPointY;
    private GameRenderer renderer;
    private int blipRandomizer;
    
    //GameStates go here
    
    public GameWorld( int gameWidth, int gameHeight) {
	//currentState = GameState.MENU
	this.midPointY = midPointY;
	this.gameWidth = gameWidth;
	this.gameHeight = gameHeight;
	scanner = new Scanner(gameWidth / 2, gameHeight / 2);
	blips = new ArrayList<Blip>();
	blipRandomizer = 2;
	Blip blip2 = new Blip();
	blip2.setX(411.05017f);
	blip2.setY(539.24756f);
	blips.add(blip2);
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
	updateBlips(delta);
    }
    
    private void updateBlips(float delta) {
	
	
//	System.out.println(blips.get(0).isDetected());
	
	while(blips.size() > 4) {
	    blips.remove(0);
	}
	if(blips.size() < blipRandomizer && Math.random() * 100 < 10) {
	    int numOfBlips = (int) (Math.random() * (blipRandomizer));
	    for(int i = 0; i < numOfBlips; i++) {
		blips.add(new Blip());
	    }
	}
	
	ArrayList<Blip> toRemove = new ArrayList<Blip>();
	
	for(Blip blip : blips) {
	    blip.update(delta);
	    if(blip.getTime() > blip.getLifeTime()) 
		toRemove.add(blip);
	}
	
	for(Blip blip : toRemove) {
	    blips.remove(blip);
	    randomizeBlipRandomizer();
	}
	
	if(blipRandomizer <= 1 && blips.size() == 0)
	    blipRandomizer = 2;
	
	
	
	for(Blip blip : blips) {
	    if(scanner.getHitBox().contains(blip.getX(), blip.getY())) {
		blip.setDetected(true);
	    }
	}
    }
    
    private void randomizeBlipRandomizer() {
	double num = Math.random() * 100 + 1;
	if(num > 23) {
	    if(num > 46) {
		if(num > 69) {
		    if(num > 89) {
			blipRandomizer = 4;
		    } else {
			blipRandomizer = 3;
		    }
		} else {
		    blipRandomizer = 2;
		}
	    } else {
		blipRandomizer = 1;
	    }
	} else {
	    blipRandomizer = 0;
	}
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
    
    public ArrayList<Blip> getBlips() {
	return blips;
    }
    
    //GameStates e.g.- isReady
    
    public void setRenderer(GameRenderer renderer) {
	this.renderer = renderer;
    }
    
    public GameRenderer getRenderer() {
	return renderer;
    }
}
