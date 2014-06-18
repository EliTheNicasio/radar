package com.eNic.GameObjects;

import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class Scanner {

    private Vector2 position;
    
    private boolean isOn;
    
    private int width, height;
    private float rotation, speed;
    
    private Polygon hitBox;
    
    public Scanner(int x, int y) {
	width = 4;
	height = 460;
	position = new Vector2(x, y);
	rotation = 0;
	speed = 100;
	isOn = false;
	float[] vertices = { 230, 400, 230, 150, 250, 150, 250, 400};
	hitBox = new Polygon(vertices);
	hitBox.setOrigin(240, 400);
    }
    
    public void update(float delta) {
	if(isOn) {
	    rotation+= speed * delta;
	    hitBox.rotate(speed * delta);
	}
    }
    
    public void onClick() {
	if(isOn)
	    stop();
	else if(!isOn())
	    start();
    }
    
    public void stop() {
	isOn = false;
    }
    
    public void start() {
	isOn = true;
    }
    
    public float getX() {
	return position.x;
    }
    
    public float getY() {
	return position.y;
    }
    
    public float getRotation() {
	return rotation;
    }
    
    public Polygon getHitBox() {
	return hitBox;
    }
    
    public boolean isOn() {
	return isOn;
    }
}
