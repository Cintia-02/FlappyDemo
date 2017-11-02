package com.missionbit.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.missionbit.game.FlappyDemo;
import com.missionbit.game.sprites.Bird;

/**
 * Created by missionbit on 10/24/17.
 */

public class PlayState extends State {

    private Bird bird;
    private Texture bg;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        cam.setToOrtho(false, FlappyDemo.WIDTH / 2,
                FlappyDemo.HEIGHT / 2);
        bird = new Bird(50, 100);
        bg = new Texture("bg.png");
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
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(bg,(cam.position.x - cam.viewportWidth / 2), 0);
        sb.draw(bird.getTexture(), bird.getPosition().x, bird.getPosition().y);
        sb.end();
    }

    @Override
    public void dispose() {
        bg.dispose();
    }
}
