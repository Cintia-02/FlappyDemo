package com.missionbit.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

/**
 * Created by missionbit on 11/5/17.
 */

public class Tube {
    public static final int TUBE_WIDTH = 52;
    private Texture topTube;
    private Texture bottomTube;

    private static final int TUBE_GAP = 100; //opening between tubes, where flappy can fly through
    private static final int LOWEST_OPENING = 120; //lowest position the top of the bottom tube can be, must be above 90 to be above ground level
    private static final int FLUCTUATION = 130; //fluctuates tube height randomly
    private Vector2 posTopTube, posBottomTube; //position of top tube and bottom tube
    private Random rand;

    public Tube(float x){
        topTube = new Texture("toptube.png");
        bottomTube = new Texture("bottomtube.png");
        rand = new Random();

        posTopTube = new Vector2(x, rand.nextInt(FLUCTUATION) + TUBE_GAP + LOWEST_OPENING);
        posBottomTube = new Vector2(x, posTopTube.y - TUBE_GAP - bottomTube.getHeight());

    }


    public Texture getTopTube() {
        return topTube;
    }

    public Texture getBottomTube() {
        return bottomTube;
    }

    public Vector2 getPosTopTube() {
        return posTopTube;
    }

    public Vector2 getPosBottomTube() {
        return posBottomTube;
    }


    //to move tube from left of camera view to being in front
    public void reposition(float x){
        posTopTube.set(x, rand.nextInt(FLUCTUATION) + TUBE_GAP + LOWEST_OPENING);
        posBottomTube.set(x, posTopTube.y - TUBE_GAP - bottomTube.getHeight());

    }
}
