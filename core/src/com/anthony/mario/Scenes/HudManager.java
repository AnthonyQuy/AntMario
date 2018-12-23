package com.anthony.mario.Scenes;

public abstract class HudManager {

  protected static Hud hud;

  public static void addScore(int x) {
    hud.addScore(x);
  }
}
