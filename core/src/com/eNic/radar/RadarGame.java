package com.eNic.radar;

import com.badlogic.gdx.Game;
import com.eNic.Helpers.AssetLoader;
import com.eNic.Screens.GameScreen;

public class RadarGame extends Game {

    @Override
    public void create() {
	AssetLoader.load();
	setScreen(new GameScreen());
    }

    @Override
    public void dispose() {
	super.dispose();
	AssetLoader.dispose();
    }

}
