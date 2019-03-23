package com.graphics;

import javax.swing.JFrame;
import java.awt.DisplayMode;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Window;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class ScreenManager {

	private GraphicsDevice graphicsDevice;

	public ScreenManager() {
		GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
		graphicsDevice = graphicsEnvironment.getDefaultScreenDevice();
	}

	public DisplayMode[] getCompatibleDisplayModes() {
		return graphicsDevice.getDisplayModes();
	}

	public DisplayMode findFirstCompatibleMode(DisplayMode[] modes) {

		DisplayMode goodModes[] = graphicsDevice.getDisplayModes();
        for (DisplayMode mode : modes) {
            for (DisplayMode goodMode : goodModes) {
                if (displayModesMatch(mode, goodMode)) {
                    return mode;
                }
            }
        }

		return null;
	}

	public DisplayMode getCurrentDisplayMode() {
		return graphicsDevice.getDisplayMode();
	}

	public boolean displayModesMatch(DisplayMode m1, DisplayMode m2) {
		if (m1.getWidth() != m2.getWidth() || m1.getHeight() != m2.getHeight()) {
			return false;
		}
		if (m1.getBitDepth() != DisplayMode.BIT_DEPTH_MULTI && m2.getBitDepth() != DisplayMode.BIT_DEPTH_MULTI && m1.getBitDepth() != m2.getBitDepth()) {
			return false;
		}
		if (m1.getRefreshRate() != DisplayMode.REFRESH_RATE_UNKNOWN && m2.getRefreshRate() != DisplayMode.REFRESH_RATE_UNKNOWN && m1.getRefreshRate() != m2.getRefreshRate()) {
			return false;
		}

		return true;
	}

	public void setFullScreen(DisplayMode displayMode) {
		JFrame jFrame = new JFrame();
		jFrame.setUndecorated(true);
		jFrame.setIgnoreRepaint(true);
		jFrame.setResizable(false);
		graphicsDevice.setFullScreenWindow(jFrame);

		if (displayMode != null && graphicsDevice.isDisplayChangeSupported()) {
			try {
				graphicsDevice.setDisplayMode(displayMode);
			} catch (Exception ex) {
			    //throw an exception or log an error
			}

			jFrame.createBufferStrategy(2);
		}
	}

	public Graphics2D getGraphics() {
		Window window = graphicsDevice.getFullScreenWindow();
		if (window != null) {
			BufferStrategy bufferStrategy = window.getBufferStrategy();
			return (Graphics2D) bufferStrategy.getDrawGraphics();
		} else {
			return null;
		}
	}

	public void update() {
		Window window = graphicsDevice.getFullScreenWindow();
		if (window != null) {
			BufferStrategy bufferStrategy = window.getBufferStrategy();
			if (!bufferStrategy.contentsLost()) {
				bufferStrategy.show();
			}
		}
	}

	public Window getFullScreenWindow() {
		return graphicsDevice.getFullScreenWindow();
	}

	public int getWidth() {
		Window window = graphicsDevice.getFullScreenWindow();
		if (window != null) {
			return window.getWidth();
		} else {
			return 0;
		}
	}

	public int getHeight() {
		Window window = graphicsDevice.getFullScreenWindow();
		if (window != null) {
			return window.getHeight();
		} else {
			return 0;
		}
	}

	public void restoreScreen() {
		Window window = graphicsDevice.getFullScreenWindow();
		if (window != null) {
			window.dispose();
		}
		graphicsDevice.setFullScreenWindow(null);
	}

	public BufferedImage createCompatibleImage(int width, int height, int transparency) {
		Window window = graphicsDevice.getFullScreenWindow();
		if (window != null) {
			GraphicsConfiguration graphicsConfiguration = window.getGraphicsConfiguration();
			return graphicsConfiguration.createCompatibleImage(width, height, transparency);
		} else {
			return null;
		}
	}
}
