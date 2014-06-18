package com.eNic.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.eNic.GameWorld.GameRenderer;
import com.eNic.GameWorld.GameWorld;
import com.eNic.Helpers.InputHandler;

public class GameScreen implements Screen {

    private GameWorld world;
    private GameRenderer renderer;
    private float runTime;

    // This is the constructor, not the class declaration
    public GameScreen() {

	float screenWidth = Gdx.graphics.getWidth();
	float screenHeight = Gdx.graphics.getHeight();
	float gameWidth = 480;
	float gameHeight = screenHeight / (screenWidth / gameWidth);

	world = new GameWorld();
	Gdx.input.setInputProcessor(new InputHandler(world, screenWidth
		/ gameWidth, screenHeight / gameHeight));
	renderer = new GameRenderer(world, (int) gameWidth, (int) gameHeight);
	world.setRenderer(renderer);
    }

    @Override
    public void render(float delta) {
	runTime += delta;
	world.update(delta);
	renderer.render(delta, runTime);
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void show() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
    }

}
