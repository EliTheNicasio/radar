package com.eNic.GameObjects;

import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;

public class Scanner {

    private Vector2 position;

    private boolean isOn;

    private float rotation, speed;

    private Polygon hitBox;

    public Scanner() {
	position = new Vector2(240, 500);
	rotation = 0;
	speed = 100;
	isOn = false;
	float[] vertices = { 230, 500, 230, 250, 250, 250, 250, 500 };
	hitBox = new Polygon(vertices);
	hitBox.setOrigin(position.x, position.y);
    }

    public void update(float delta) {
	if (isOn) {
	    rotation += speed * delta;
	    hitBox.rotate(speed * delta);
	}
    }

    public void onClick() {
	if (isOn)
	    stop();
	else if (!isOn())
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
