package com.eNic.GameWorld;

import java.util.ArrayList;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenEquations;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.eNic.GameObjects.Blip;
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

    // Game Objects
    Scanner scanner;
    ArrayList<Blip> blips;

    // Game Assets
    private Sprite radar, scannerLine, title;

    // Tween
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
	blips = myWorld.getBlips();
    }

    private void initAssets() {
	radar = new Sprite(AssetLoader.radarTexture);
	radar.setCenter(scanner.getX(), scanner.getY());
	scannerLine = new Sprite(AssetLoader.scanner);
	scannerLine.setCenter(scanner.getX(), scanner.getY());
	title = new Sprite(AssetLoader.title);
	title.setCenter(240, 150);
	prepareFades();
	alphaOut.setValue(0);
    }

    // Draw methods
    private void drawRadar(float delta) {
	radar.draw(batcher);
	drawScanner(delta);
	drawBlips(delta);
    }

    public void drawBlips(float delta) {
	blips = myWorld.getBlips();
	for (Blip blip : blips) {
	    Sprite blipSprite = new Sprite(AssetLoader.undetectedBlip);
	    blipSprite.setCenter(blip.getX(), blip.getY());
	    if (blip.getLifeTime() - blip.getTime() <= 1) {
		drawFadeOut(delta, blipSprite, blip);
	    } else {
		drawFadeIn(delta, blipSprite, blip);
	    }

	    Sprite detectedSprite = new Sprite(AssetLoader.detectedBlip);
	    detectedSprite.setCenter(blip.getX(), blip.getY());
	    if (scanner.isOn() && blip.isDetected()) {
		drawDetectedIn(delta, detectedSprite, blip);
		blip.extendLifeTime(delta);
	    } else {
		drawDetectedOut(delta, detectedSprite, blip);
	    }
	}
    }

    public void drawFadeIn(float delta, Sprite sprite, Blip blip) {
	if (blip.getAlphaUndetectIn().getValue() < 1) {
	    blip.getManagerUndetectIn().update(delta);
	    sprite.setColor(1f, 1f, 1f, blip.getAlphaUndetectIn().getValue());
	    sprite.draw(batcher);
	} else {
	    sprite.setColor(1f, 1f, 1f, blip.getAlphaUndetectIn().getValue());
	    sprite.draw(batcher);
	}
    }

    public void drawFadeOut(float delta, Sprite sprite, Blip blip) {
	if (blip.getAlphaUndetectOut().getValue() > 0) {
	    blip.getManagerUndetectOut().update(delta);
	    sprite.setColor(1f, 1f, 1f, blip.getAlphaUndetectOut().getValue());
	    sprite.draw(batcher);
	} else {
	    sprite.setColor(1f, 1f, 1f, blip.getAlphaUndetectOut().getValue());
	    sprite.draw(batcher);
	}
    }

    public void drawDetectedIn(float delta, Sprite sprite, Blip blip) {
	if (blip.getAlphaDetectIn().getValue() < 1) {
	    blip.getManagerDetectIn().update(delta);
	    sprite.setColor(1f, 1f, 1f, blip.getAlphaDetectIn().getValue());
	    sprite.draw(batcher);
	} else {
	    sprite.setColor(1f, 1f, 1f, blip.getAlphaDetectIn().getValue());
	    sprite.draw(batcher);
	    blip.prepareDetectedIn();
	    blip.prepareDetectedOut();
	    blip.setDetected(false);
	}
    }

    public void drawDetectedOut(float delta, Sprite sprite, Blip blip) {
	if (blip.getAlphaDetectOut().getValue() > 0) {
	    blip.getManagerDetectOut().update(delta);
	    sprite.setColor(1f, 1f, 1f, blip.getAlphaDetectOut().getValue());
	    sprite.draw(batcher);
	} else {
	    sprite.setColor(1f, 1f, 1f, blip.getAlphaDetectOut().getValue());
	    sprite.draw(batcher);
	    blip.getAlphaDetectIn().setValue(0);
	}
    }

    public void drawScanner(float delta) {
	scannerLine.setRotation(scanner.getRotation());
	if (scanner.isOn()) {
	    if (alphaIn.getValue() < 1) {
		managerIn.update(delta);
		scannerLine.setColor(1f, 1f, 1f, alphaIn.getValue());
		scannerLine.draw(batcher);
	    } else {
		scannerLine.setColor(1f, 1f, 1f, alphaIn.getValue());
		scannerLine.draw(batcher);
	    }
	} else {
	    if (alphaOut.getValue() > 0) {
		managerOut.update(delta);
		scannerLine.setColor(1f, 1f, 1f, alphaOut.getValue());
		scannerLine.draw(batcher);
	    } else {
		scannerLine.setColor(1f, 1f, 1f, alphaOut.getValue());
		scannerLine.draw(batcher);
	    }
	}
    }

    // Prepare for tweeners
    public void prepareFades() {
	prepareFadeIn();
	prepareFadeOut();
    }

    public void prepareFadeIn() {
	alphaIn.setValue(0);
	Tween.registerAccessor(Value.class, new ValueAccessor());
	managerIn = new TweenManager();
	Tween.to(alphaIn, -1, 1f).target(1).ease(TweenEquations.easeOutQuad)
		.start(managerIn);
    }

    public void prepareFadeOut() {
	alphaOut.setValue(1);
	Tween.registerAccessor(Value.class, new ValueAccessor());
	managerOut = new TweenManager();
	Tween.to(alphaOut, -1, 1f).target(0).ease(TweenEquations.easeOutQuad)
		.start(managerOut);
    }

    public void testPolygon() {
	shapeRenderer.begin(ShapeType.Line);
	shapeRenderer.setColor(255 / 255.0f, 255 / 255.0f, 255 / 255.0f, 1);
	shapeRenderer.polygon(scanner.getHitBox().getTransformedVertices());
	shapeRenderer.end();
    }

    public void testBlip() {
	blips = myWorld.getBlips();
	shapeRenderer.begin(ShapeType.Line);
	shapeRenderer.setColor(255 / 255.0f, 255 / 255.0f, 255 / 255.0f, 1);
	for (Blip blip : blips) {
	    shapeRenderer.circle(blip.getX(), blip.getY(), 5);
	}
	shapeRenderer.end();
    }

    public void render(float delta, float runTime) {

	shapeRenderer.begin(ShapeType.Filled);
	// Draw Background color
	shapeRenderer.setColor(0 / 255.0f, 0 / 255.0f, 0 / 255.0f, 1);
	shapeRenderer.rect(0, 0, gameWidth, gameHeight);
	shapeRenderer.end();

	batcher.begin();

	drawRadar(delta);
	title.draw(batcher);
	
	batcher.end();
	// testPolygon();
	// testBlip();
    }
}
