package com.missionbit.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.missionbit.game.FlappyDemo;
import com.missionbit.game.sprites.Bird;
import com.missionbit.game.sprites.Tube;

/**
 * Created by missionbit on 10/19/17.
 */

public class PlayState extends State{

    private Bird bird;
    private Texture bg;
    private static final int TUBE_SPACING = 125; //space between tubes
    private static final int TUBE_COUNT = 4; //how many tubes total at any given time
    private Array<Tube> tubes;

    public PlayState(GameStateManager theGSM) {
        super(theGSM);
        bg = new Texture("bg.png");
        bird = new Bird(50,100);
        cam.setToOrtho(false, FlappyDemo.WIDTH/2, FlappyDemo.HEIGHT/2);

        tubes = new Array<Tube>();
        for(int i = 0; i < TUBE_COUNT; i++){
            tubes.add(new Tube(i * (TUBE_SPACING + Tube.TUBE_WIDTH)));
        }
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched()){
            bird.jump();
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        bird.update(dt);
        //camera view in front of bird
        cam.position.x = bird.getPosition().x + 80;

        //how to reposition tube when off camera
        for(Tube tube : tubes){
            //if tube is on the left of the screen
            if(cam.position.x - (cam.viewportWidth / 2) > tube.getPosTopTube().x + tube.getTopTube().getWidth()){
                tube.reposition(tube.getPosTopTube().x +((Tube.TUBE_WIDTH + TUBE_SPACING) * TUBE_COUNT));
            }
        }

        //tell libgdx that we have repositioned
        cam.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(bg, (cam.position.x - (cam.viewportWidth / 2)), 0);
        sb.draw(bird.getTexture(), bird.getPosition().x, bird.getPosition().y);
        for (Tube tube : tubes) {
            sb.draw(tube.getTopTube(), tube.getPosTopTube().x, tube.getPosTopTube().y);
            sb.draw(tube.getBottomTube(), tube.getPosBottomTube().x, tube.getPosBottomTube().y);
        }
        sb.end();
    }

    @Override
    public void dispose() {

    }
}
