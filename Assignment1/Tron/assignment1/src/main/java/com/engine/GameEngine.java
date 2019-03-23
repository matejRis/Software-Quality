package com.engine;

import com.graphics.Presentation;
import com.graphics.ScreenManager;

public abstract class GameEngine {

	protected boolean running = true;
	protected Presentation presentation;
	protected ScreenManager screenManager;

	protected GameEngine(Presentation presentation, ScreenManager screenManager) {
		this.presentation = presentation;
		this.screenManager = screenManager;
	}

	protected void run(int refreshRate) {
		try {
			init();
			gameLoop(refreshRate);
		} finally {
			screenManager.restoreScreen();
		}
	}

	public void run() {
		run(20);
	}

	protected void stop() {
		running = false;
	}

	private void gameLoop(int refreshRate) {
		while (running) {
			update();

			try {
				Thread.sleep(refreshRate);
			} catch (Exception ex) {
			}
		}
	}

	protected abstract void update();

	protected abstract void init();
}
