import java.awt.*;

import acm.graphics.*;
import acm.program.*;

public class MainApplication extends GraphicsApplication {
	public static final int WINDOW_WIDTH = 800;
	public static final int WINDOW_HEIGHT = 600;
	public static final Vector2 WINDOW_CENTER = new Vector2(WINDOW_WIDTH / 2, WINDOW_HEIGHT / 2);
	
	private StoryPane story;
	private GamePane game;
	private MenuPane menu;
	private SettingsPane setting;
	private ScoresPane score;
	private ControlsPane control;
	private PausePane pause;
	private GameOverPane gameOver;
	private GameConsole console;
	private GameTimer gameTimer;
	private AudioPlayer audio;
	private boolean musicToggle;
	private boolean sfxToggle;
	private boolean isPaused;
	
	public void init() {
		//console = new GameConsole();
		//gameTimer = console.getTimer();
		setTitle("HTBX");
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		setBackground(Color.white);
	}
	
	public void run() {
		menu = new MenuPane(this);
		setting = new SettingsPane(this);
		control = new ControlsPane(this);
		score = new ScoresPane(this);
		pause = new PausePane(this);
		gameOver = new GameOverPane(this);
		story = new StoryPane(this);
		audio = AudioPlayer.getInstance();
		musicToggle = true;
		sfxToggle = true;
		isPaused = false;
		
		switchToMenu();
	}
	
	public void sfxToggle(boolean toggle) {
		sfxToggle = toggle;
	}
	
	public boolean getSfxToggle() {
		return sfxToggle;
	}
	
	public boolean isPaused() {
		return isPaused;
	}
	
	public void pauseGameTimer() {
		gameTimer.stopTimer();
	}
	
	public void startGameTimer() {
		gameTimer.startTimer();
	}
	
	public void switchToMenu() {
		isPaused = false;
		audio.stopSound("sounds", "Credits.mp3");
		
		if(musicToggle) {
			audio.playSound("sounds", "3A1W - Menu.wav", true);
		}
		
		switchToScreen(menu);
	}
	
	public void switchToStory() {
		switchToScreen(story);
	}
	
	public void switchToGame() {
		if(!isPaused) {
			removeAll();
			console = new GameConsole();
			gameTimer = console.getTimer();
			game = new GamePane(this);
			gameTimer.addListener(game);
			console.addGraphicsSubscriber(game);
		}
		
		if(musicToggle) {
			audio.stopSound("sounds", "3A1W - Menu.wav");
			audio.playSound("sounds", "01 Misconection_1.mp3", true);
		}
		
		gameTimer.startTimer();
		switchToScreen(game);
	}
	
	public void switchToGameOver() {
		isPaused = false;
		audio.stopSound("sounds", "01 Misconection_1.mp3");
		switchToScreen(gameOver);
	}
	
	public void switchToSettings() {
		switchToScreen(setting);
	}
	
	public void switchToScores() {
		switchToScreen(score);
	}
	
	public void switchToControls() {
		switchToScreen(control);
	}
	
	public void switchToPause() {
		isPaused = true;
		
		audio.stopSound("sounds", "01 Misconection_1.mp3");
		audio.stopSound("sounds", "Credits.mp3");
		if(musicToggle) {
			audio.playSound("sounds", "3A1W - Menu.wav", true);
		}
		gameTimer.stopTimer();
		switchToScreen(pause);
	}
	
	public GameConsole getGameConsole() {
		return console;
	}
	
	public void musicToggle(boolean toggle) {
		musicToggle = toggle;
		
		if(musicToggle) {
			audio.playSound("sounds", "3A1W - Menu.wav", true);
		}
		else {
			audio.stopSound("sounds", "3A1W - Menu.wav");
		}
	}
}
