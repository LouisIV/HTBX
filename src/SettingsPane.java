import java.util.ArrayList;

import java.awt.Color;
import java.awt.event.MouseEvent;

import acm.graphics.GLabel;
import acm.graphics.GObject;

public class SettingsPane extends GraphicsPane {
	private MainApplication program;
	private ArrayList<GLabel> settings = new ArrayList<GLabel>();
	private GLabel toggle1;
	private GLabel toggle2;
	private GObject obj;
	private int count1;
	private int count2;

	public SettingsPane(MainApplication app) {
		this.program = app;
		count1 = 0;
		count2 = 0;

		toggle1 = new GLabel("ON", 100, 0);
		toggle2 = new GLabel("ON", 0, 50);
		toggle1.setFont(font);
		toggle2.setFont(font);
		toggle1.setColor(Color.black);
		toggle2.setColor(Color.black);

		GLabel musicSetting = new GLabel("MUSIC");
		GLabel sfxSetting = new GLabel("SFX", 0, 50);
		GLabel back = new GLabel("BACK", 0, 205);

		settings.add(musicSetting);
		settings.add(sfxSetting);
		settings.add(back);

		for(GLabel setting:settings) {
			setting.setFont(font);
			setting.setColor(Color.black);
			setting.move(CENTER_WIDTH - (setting.getWidth() / 2), CENTER_HEIGHT - (setting.getHeight() / 2));
			switch(setting.getLabel()) {
			case "MUSIC":
				toggle1.move(CENTER_WIDTH - (setting.getWidth() / 2), CENTER_HEIGHT - (setting.getHeight() / 2));
				break;
			case "SFX":
				setting.move(-12.5, 0);
				toggle2.move(toggle1.getX(), CENTER_HEIGHT - (setting.getHeight() / 2));
				break;
			}
		}
	}

	@Override
	public void showContents() {
		program.add(whiteBG());
		for(GLabel setting:settings) {
			program.add(setting);
		}
		program.add(toggle1);
		program.add(toggle2);
		program.add(selection());
		program.add(title());
	}

	@Override
	public void hideContents() {
		program.remove(whiteBG());
		for(GLabel setting:settings) {
			program.remove(setting);
		}
		program.remove(toggle1);
		program.remove(toggle2);
		program.remove(selection());
		program.remove(title());
	}

	@Override
	public void mousePressed(MouseEvent e) {
		obj = program.getElementAt(e.getX(), e.getY());
		if(obj != null) {
			if(obj == toggle1) {
				toggle1.setColor(Color.gray);
			}
			else if(obj == toggle2) {
				toggle2.setColor(Color.gray);
			}
			else {
				for(GLabel setting:settings) {
					if(obj == setting) {
						setting.setColor(Color.gray);
					}
				}
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		for(GLabel setting:settings) {
			setting.setColor(Color.black);
		}

		toggle1.setColor(Color.black);
		toggle2.setColor(Color.black);

		obj = program.getElementAt(e.getX(), e.getY());
		if(obj != whiteBG() || obj != title()) {
			for(GLabel setting:settings) {
				if(obj == setting) {
					switch(setting.getLabel()) {
					case "MUSIC":
						count1++;
						switch(count1 % 2) {
						case 0:
							toggle1.setLabel("ON");
							program.setMusicToggle(true);
							break;

						case 1:
							toggle1.setLabel("OFF");
							program.setMusicToggle(false);
							break;
						}
						break;

					case "SFX":
						count2++;
						switch(count2 % 2) {
						case 0:
							toggle2.setLabel("ON");
							program.sfxToggle(true);
							break;

						case 1:
							toggle2.setLabel("OFF");
							program.sfxToggle(false);
							break;
						}
						break;

					case "BACK":
						if(!program.isPaused()) {
							program.switchToMenu();
						}
						else {
							program.switchToPause();
						}
						break;

					default:
						break;
					}
				}
			}
			if(obj == toggle1) {
				count1++;
				switch(count1 % 2) {
				case 0:
					toggle1.setLabel("ON");
					program.setMusicToggle(true);
					break;

				case 1:
					toggle1.setLabel("OFF");
					program.setMusicToggle(false);
					break;
				}
			}
			else if(obj == toggle2) {
				count2++;
				switch(count2 % 2) {
				case 0:
					toggle2.setLabel("ON");
					program.sfxToggle(true);
					break;

				case 1:
					toggle2.setLabel("OFF");
					program.sfxToggle(false);
					break;
				}
			}
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		obj = program.getElementAt(e.getX(), e.getY());

		if(obj != whiteBG() && obj != title()) {
			for(GLabel setting:settings) {
				if(obj == setting) {
					selection.setVisible(true);
					selection.setLocation(setting.getX() - 25, setting.getY());
				}
			}
			if(obj == toggle1) {
				selection.setVisible(true);
				selection.setLocation(343.5, toggle1.getY());
			}
			if(obj == toggle2) {
				selection.setVisible(true);
				selection.setLocation(343.5, toggle2.getY());
			}
		}
		else{
			selection.setVisible(false);
		}
	}
}
