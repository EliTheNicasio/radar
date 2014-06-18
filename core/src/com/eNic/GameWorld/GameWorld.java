package com.eNic.GameWorld;

import java.util.ArrayList;

import com.eNic.GameObjects.Blip;
import com.eNic.GameObjects.Scanner;

public class GameWorld {

    private Scanner scanner;
    private ArrayList<Blip> blips;
    private GameRenderer renderer;
    private int blipRandomizer;

    public GameWorld() {
	scanner = new Scanner();
	blips = new ArrayList<Blip>();
	blipRandomizer = 2;
    }

    public void update(float delta) {
	updateRunning(delta);
    }

    public void updateRunning(float delta) {
	if (delta > .15f) {
	    delta = .15f;
	}
	scanner.update(delta);
	updateBlips(delta);
    }

    private void updateBlips(float delta) {
	while (blips.size() > 4) {
	    blips.remove(0);
	}
	if (blips.size() < blipRandomizer && Math.random() * 100 < 10) {
	    int numOfBlips = (int) (Math.random() * (blipRandomizer));
	    for (int i = 0; i < numOfBlips; i++) {
		blips.add(new Blip());
	    }
	}

	ArrayList<Blip> toRemove = new ArrayList<Blip>();

	for (Blip blip : blips) {
	    blip.update(delta);
	    if (blip.getTime() > blip.getLifeTime())
		toRemove.add(blip);
	}

	for (Blip blip : toRemove) {
	    blips.remove(blip);
	    randomizeBlipRandomizer();
	}

	if (blipRandomizer <= 1 && blips.size() == 0)
	    blipRandomizer = 2;

	for (Blip blip : blips) {
	    if (scanner.getHitBox().contains(blip.getX(), blip.getY())) {
		blip.setDetected(true);
	    }
	}
    }

    private void randomizeBlipRandomizer() {
	double num = Math.random() * 100 + 1;
	if (num > 23) {
	    if (num > 46) {
		if (num > 69) {
		    if (num > 89) {
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

    // Getters

    public Scanner getScanner() {
	return scanner;
    }

    public ArrayList<Blip> getBlips() {
	return blips;
    }

    public void setRenderer(GameRenderer renderer) {
	this.renderer = renderer;
    }

    public GameRenderer getRenderer() {
	return renderer;
    }
}
