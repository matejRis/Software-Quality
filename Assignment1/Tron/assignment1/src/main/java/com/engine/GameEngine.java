package com.engine;

public interface GameEngine {

    void run();

    void stop();

    void init();

    void gameLoop();

    void update(long timePassed);
}
