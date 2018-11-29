package com.anthony.mario.Until;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Assets {

  public static AssetManager assetManager = new AssetManager();
  private static final AssetDescriptor<Texture> characters = new AssetDescriptor<Texture>(
      "characters.png", Texture.class);

  public static void load() {
    assetManager.load(characters);
  }

  public static Texture getCharacters() {
    return assetManager.get(characters);
  }

  public static TextureRegion getMarioRegion() {
    return new TextureRegion(getCharacters(), 255, 0, 20, 35);
  }

  public static void dispose() {
    assetManager.dispose();
  }

}
