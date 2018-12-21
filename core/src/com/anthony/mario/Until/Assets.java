package com.anthony.mario.Until;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Assets {

    private static final AssetDescriptor<TextureAtlas> characters_des = new AssetDescriptor<TextureAtlas>(
            "characters/characters.txt", TextureAtlas.class);
    private static final AssetDescriptor<Music> music_des = new AssetDescriptor<Music>(
            "audio/music/mario_music.ogg", Music.class);
    private static final AssetDescriptor<Sound> sound_break_block_des = new AssetDescriptor<Sound>(
            "audio/sounds/breakblock.wav", Sound.class);
    private static final AssetDescriptor<Sound> bump_des = new AssetDescriptor<Sound>(
            "audio/sounds/bump.wav", Sound.class);
    private static final AssetDescriptor<Sound> coin_des = new AssetDescriptor<Sound>(
            "audio/sounds/coin.wav", Sound.class);
    public static AssetManager assetManager = new AssetManager();
    private static TextureAtlas atlas;

    public static void load() {
        assetManager.load(characters_des);
        assetManager.load(music_des);
        assetManager.load(sound_break_block_des);
        assetManager.load(bump_des);
        assetManager.load(coin_des);

        assetManager.finishLoading();
        atlas = assetManager.get(characters_des);
    }


    public static TextureRegion getMarioRegion() {
        return atlas.findRegion("LittleMario");
    }

    public static TextureRegion getGoombaRegion() {
        return atlas.findRegion("Goomba");
    }

    public static Music getMusic() {
        return assetManager.get(music_des);
    }

    public static Sound getBreackBlockSound() {
        return assetManager.get(sound_break_block_des);
    }

    public static Sound getBumpSound() {
        return assetManager.get(bump_des);
    }

    public static Sound getCoinSound() {
        return assetManager.get(coin_des);
    }

    public static void dispose() {
        assetManager.dispose();
    }


}
