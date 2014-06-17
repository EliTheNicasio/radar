package com.eNic.Helpers;

import com.badlogic.gdx.InputProcessor;
import com.eNic.GameObjects.Scanner;
import com.eNic.GameWorld.GameWorld;

public class InputHandler implements InputProcessor{
    
    private Scanner scanner;
    
    private GameWorld myWorld;
    
    private float scaleFactorX;
    private float scaleFactorY;
    
    public InputHandler(GameWorld myWorld, float scaleFactorX, 
	    float scaleFactorY) {
	this.myWorld = myWorld;
	scanner = myWorld.getScanner();
	
	this.scaleFactorX = scaleFactorX;
	this.scaleFactorY = scaleFactorY;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
	screenX = scaleX(screenX);
	screenY = scaleY(screenY);
	
	//What happens on touchDown depending on gamestates goes here
	myWorld.getRenderer().prepareFades();
	scanner.onClick();
	
	return true;
    }
    
    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {	
	return false;
    }
    
    @Override
    public boolean keyDown(int keycode) {
	return false;
    }

    @Override
    public boolean keyUp(int keycode) {
	return false;
    }

    @Override
    public boolean keyTyped(char character) {
	return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
	return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
	return false;
    }

    @Override
    public boolean scrolled(int amount) {
	return false;
    }

    private int scaleX(int screenX) {
	return (int) (screenX / scaleFactorX);
    }

    private int scaleY(int screenY) {
	return (int) (screenY / scaleFactorY);
    }
}
