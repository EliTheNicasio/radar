package com.eNic.GameWorld;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenEquations;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.eNic.GameObjects.Scanner;
import com.eNic.Helpers.AssetLoader;
import com.eNic.TweenAccessors.Value;
import com.eNic.TweenAccessors.ValueAccessor;

public class GameRenderer {

    private GameWorld myWorld;
    private OrthographicCamera cam;
    private ShapeRenderer shapeRenderer;

    private SpriteBatch batcher;

    private int gameWidth, gameHeight;
    
    //Game Objects
    Scanner scanner;
    
    //Game Assets
    private Sprite radar, scannerLine;
    
    //Tween
    private TweenManager managerIn, managerOut;
    private Value alphaIn = new Value(), alphaOut = new Value();
    
    public GameRenderer(GameWorld world, int gameWidth, int gameHeight) {
	
	myWorld = world;
	this.gameWidth = gameWidth;
	this.gameHeight = gameHeight;
	cam = new OrthographicCamera();
	cam.setToOrtho(true, gameWidth, gameHeight);
	
	batcher = new SpriteBatch();
	batcher.setProjectionMatrix(cam.combined);
	shapeRenderer = new ShapeRenderer();
	shapeRenderer.setProjectionMatrix(cam.combined);

	initGameObjects();
	initAssets();
    }
    
    private void initGameObjects() {
	scanner = myWorld.getScanner();
    }
    
    private void initAssets() {
	radar = new Sprite(AssetLoader.radarTexture);
	radar.setCenter(240, 400);
	scannerLine = new Sprite(AssetLoader.scanner);
	scannerLine.setCenter(240, 400);
	prepareFades();
	alphaOut.setValue(0);
    }
    
    //Draw methods
    private void drawRadar(float delta) {
	radar.draw(batcher);
	updateScanner(delta);
	
    }
    
    public void updateScanner(float delta) {
	scannerLine.setRotation(scanner.getRotation());
	if(scanner.isOn()) {
	    if(alphaIn.getValue() < 1) {
		managerIn.update(delta);
		scannerLine.setColor(1f, 1f, 1f, alphaIn.getValue());
		scannerLine.draw(batcher);
	    } else {
		scannerLine.setColor(1f, 1f, 1f, alphaIn.getValue());
		scannerLine.draw(batcher);
	    }
	} else {
	    if(alphaOut.getValue() > 0) {
		managerOut.update(delta);
		scannerLine.setColor(1f, 1f, 1f, alphaOut.getValue());
		scannerLine.draw(batcher);
	    } else {
		scannerLine.setColor(1f, 1f, 1f, alphaOut.getValue());
		scannerLine.draw(batcher);
	    }
	}
    }
    
    //Prepare for tweeners
    public void prepareFades() {
	prepareFadeIn();
	prepareFadeOut();
    }
    
    public void prepareFadeIn() {
	alphaIn.setValue(0);
	Tween.registerAccessor(Value.class, new ValueAccessor());
	managerIn = new TweenManager();
	Tween.to(alphaIn, -1, 1f).target(1)
		.ease(TweenEquations.easeOutQuad).start(managerIn);
    }
    
    public void prepareFadeOut() {
	alphaOut.setValue(1);
	Tween.registerAccessor(Value.class, new ValueAccessor());
	managerOut = new TweenManager();
	Tween.to(alphaOut, -1, 1f).target(0)
		.ease(TweenEquations.easeOutQuad).start(managerOut);
    }
    
    public void testPolygon() {
	shapeRenderer.begin(ShapeType.Line);
	shapeRenderer.setColor(255 / 255.0f, 255 / 255.0f, 255 / 255.0f, 1);
	shapeRenderer.polygon(scanner.getHitBox().getTransformedVertices());
	shapeRenderer.end();
    }
    
    public void render(float delta, float runTime) {
	
	shapeRenderer.begin(ShapeType.Filled);
	// Draw Background color
	shapeRenderer.setColor(0 / 255.0f, 0 / 255.0f, 0 / 255.0f, 1);
	shapeRenderer.rect(0, 0, gameWidth, gameHeight);
	
	shapeRenderer.end();
	//Then do gamestates
	batcher.begin();
//	batcher.disableBlending();
	
	drawRadar(delta);
	batcher.end();
	testPolygon();
    }
}
