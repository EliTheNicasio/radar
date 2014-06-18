package com.eNic.Helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetLoader {
    
    public static Texture blackTexture, radarTexture, scannerTexture,
    			  blipUTexture, blipDTexture;
    public static TextureRegion black, radar, scanner, undetectedBlip,
    				detectedBlip;
    
    public static void load() {
	blackTexture = new Texture(Gdx.files.internal("black.jpg"));
	blackTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
	
	black = new TextureRegion(blackTexture, 0, 0, 480, 800);
	
	radarTexture = new Texture(Gdx.files.internal("radar.png"));
	radarTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
	
	radar = new TextureRegion(radarTexture, 0, 0, 460, 460);
	
	scannerTexture = new Texture(Gdx.files.internal("scanner.png"));
	scannerTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
	
	scanner = new TextureRegion(scannerTexture, 0, 0, 460, 460);
	scanner.flip(false, true);
	
	blipUTexture = new Texture(Gdx.files.internal("undetectedBlip.png"));
	blipUTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
	
	undetectedBlip = new TextureRegion(blipUTexture, 0, 0, 10, 10);
	
	blipDTexture = new Texture(Gdx.files.internal("detectedBlip.png"));
	blipDTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
	
	detectedBlip = new TextureRegion(blipDTexture, 0, 0, 10, 10);
    }
    
    public static void dispose() {
	blackTexture.dispose();
	radarTexture.dispose();
	scannerTexture.dispose();
    }
}
