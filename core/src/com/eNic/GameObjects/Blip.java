package com.eNic.GameObjects;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenEquations;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.eNic.TweenAccessors.Value;
import com.eNic.TweenAccessors.ValueAccessor;

public class Blip {

    private Vector2 position;
    private float time, lifeTime;
    private boolean detected;
    private TweenManager managerUndetectIn, managerUndetectOut, managerDetectIn, managerDetectOut;
    private Value alphaUndetectIn = new Value(), alphaUndetectOut = new Value(),
	    	  alphaDetectIn = new Value(), alphaDetectOut = new Value();
    
    public Blip() {
	Circle boundingCircle = new Circle(240, 500, 230);
	position = new Vector2(0, 0);
	time = 0;
	lifeTime = (float) (Math.random() * 7 + 4);
	
	while(!(boundingCircle.contains(position))) {
	    position.x = (float) (Math.random() * 460 + 11);
	    position.y = (float) (Math.random() * 460 + 271);
	}
	
	detected = false;

	alphaUndetectIn.setValue(0);
	Tween.registerAccessor(Value.class, new ValueAccessor());
	managerUndetectIn = new TweenManager();
	Tween.to(alphaUndetectIn, -1, 1f).target(1)
		.ease(TweenEquations.easeOutQuad).start(managerUndetectIn);
	
	alphaUndetectOut.setValue(1);
	Tween.registerAccessor(Value.class, new ValueAccessor());
	managerUndetectOut = new TweenManager();
	Tween.to(alphaUndetectOut, -1, 1f).target(0)
		.ease(TweenEquations.easeOutQuad).start(managerUndetectOut);
	
	prepareDetectedIn();
	prepareDetectedOut();
	alphaDetectOut.setValue(0);
    }

    public void prepareDetectedIn() {
	alphaDetectIn.setValue(0);
	Tween.registerAccessor(Value.class, new ValueAccessor());
	managerDetectIn = new TweenManager();
	Tween.to(alphaDetectIn, -1, 1f).target(1)
		.ease(TweenEquations.easeOutQuad).start(managerDetectIn);
    }
    
    public void prepareDetectedOut() {
	alphaDetectOut.setValue(1);
	Tween.registerAccessor(Value.class, new ValueAccessor());
	managerDetectOut = new TweenManager();
	Tween.to(alphaDetectOut, -1, 1f).target(0)
		.ease(TweenEquations.easeOutQuad).start(managerDetectOut);
    }
    
    public void update(float delta) {
	time+= delta;
    }
    
    public void setDetected(boolean isDetected) {
	detected = isDetected;
    }
    
    public void extendLifeTime(float delta) {
	lifeTime += delta;
    }
    
    public void setX(float x) {
	position.x = x;
    }
    // Remove these ^ and bottom
    public void setY(float y) {
	position.y = y;
    }
    
    // Getters
    public float getX() {
	return position.x;
    }
    
    public float getY() {
	return position.y;
    }
    
    public float getTime() {
	return time;
    }
    
    public float getLifeTime() {
	return lifeTime;
    }
    
    public boolean isDetected() {
	return detected;
    }
    
    public Value getAlphaUndetectIn() {
	return alphaUndetectIn;
    }
    
    public Value getAlphaUndetectOut() {
	return alphaUndetectOut;
    }
    
    public Value getAlphaDetectIn() {
	return alphaDetectIn;
    }
    
    public Value getAlphaDetectOut() {
	return alphaDetectOut;
    }
    
    public TweenManager getManagerUndetectIn() {
	return managerUndetectIn;
    }
    
    public TweenManager getManagerUndetectOut() {
	return managerUndetectOut;
    }
    
    public TweenManager getManagerDetectIn() {
	return managerDetectIn;
    }
    
    public TweenManager getManagerDetectOut() {
	return managerDetectOut;
    }
}
