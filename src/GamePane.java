import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.util.ArrayList;

import javax.swing.Timer;

import acm.graphics.*;

public class GamePane extends GraphicsPane implements ActionListener, KeyListener {
	private static final int CURSOR_DIST = 50;
	private static final int CURSOR_SIZE = 10;
	private MainApplication program; //you will use program to get access to all of the GraphicsProgram calls
	private GameConsole console; // Not a new one; just uses the one from MainApplication
	private GImage player_img;
	private PlayerShip player;
	private Timer auto_fire;
	private ArrayList <GOval> cursor_dots;
	private int track_amount = 0;
	//private Vector2 combat_offset = new Vector2(0,0); Unused for now; planned for centering player post combat smoothly
	
	// Nevermind, scratch the idea of game loops we cannot use it here
	// if we do everything becomes really buggy so timers are okay
	// but if you still want to study game loops feel free to
	// just lets get this game working with what we got w/ ACM
	public GamePane(MainApplication app) {
		this.program = app;
		cursor_dots = new ArrayList <GOval>();
		console = program.getGameConsole();
		player = console.getPlayer();
		player_img = new GImage("PlayerShip_Placeholder.png", 0, 0);
		auto_fire = new Timer(250, this);
		if (console.getPlayer() != null && player != null) {
			System.out.println("GamePane successfully accessed GameConsole's Player ship");
		}
		centerPlayer();
	}
	
	public void centerPlayer() {
		player_img.setLocation((MainApplication.WINDOW_WIDTH / 2) - (player_img.getWidth() / 2), (MainApplication.WINDOW_HEIGHT / 2) - (player_img.getHeight() /2));
	}
	
	@Override
	public void showContents() {
		program.add(player_img);
	}

	@Override
	public void hideContents() {
		auto_fire.stop();	
		program.remove(player_img);
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		// Timer should start here
		auto_fire.setInitialDelay(0);
		auto_fire.start();
		GObject obj = program.getElementAt(e.getX(), e.getY());
		if(obj == player_img) {
			program.switchToMenu();
		}
		else {
			System.out.println("Clicked empty space");
		}
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		auto_fire.stop();
		System.out.println("Stopped shooting");
	}

	@Override
	public void actionPerformed(ActionEvent e) { // Player shoot every tick
		System.out.println("Fired");
		
	}
	
	// Might be a very taxing method. We can change to having a simple cursor at the mouse pointer. Luckily, won't draw more than 5 dots
	public void alignReticle(double x, double y) {
		//Vector2 root = player.getPhysObj().getPosition();
		Vector2 visual_root = new Vector2((float)(player_img.getX() + (player_img.getWidth()/2)), (float)(player_img.getY() + (player_img.getHeight()/2)));
		int distance = (int)Math.floor(PhysXLibrary.distance(visual_root, new Vector2((float)x, (float)y)));
		int dots = (distance / CURSOR_DIST) + 1;
		if (cursor_dots.size() < dots) {
			for (int i = 0; i < dots - cursor_dots.size(); i++) {
				GOval dot = new GOval(10, 10, CURSOR_SIZE, CURSOR_SIZE);
				dot.setColor(Color.WHITE);
				cursor_dots.add(dot);
				program.add(dot);
			}
		}
		if (cursor_dots.size() > dots) {
			for (int i = 0; i < cursor_dots.size() - dots; i++) {
				program.remove(cursor_dots.get(0));
				cursor_dots.remove(0);
			}
		}
		
		// Align them properly
		double off_x = (x - visual_root.getX());
		double off_y = (y - visual_root.getY());
		double theta_rad = Math.atan2(off_y, off_x);
		double unit_x = (Math.cos(theta_rad) * CURSOR_DIST);
		double unit_y = (Math.sin(theta_rad) * CURSOR_DIST);
		cursor_dots.get(0).setLocation(x - (CURSOR_SIZE / 2), y - (CURSOR_SIZE / 2));
		for (int i = 1; i < cursor_dots.size(); i++) {
			cursor_dots.get(i).setLocation(visual_root.getX() - (CURSOR_SIZE / 2) + (unit_x * i), visual_root.getY() - (CURSOR_SIZE / 2) + (unit_y * i));
		}
		//System.out.println("Distance: " + distance + ", Drawn: " + dots);
	}
	

	@Override
	public void mouseMoved(MouseEvent e) {
		alignReticle(e.getX(), e.getY());
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		alignReticle(e.getX(), e.getY());
	}
	
	// Key Presses work; the println statements were removed to prevent clutter in the console as I test
	@Override
    public void keyPressed(KeyEvent e) {
    	
        int key = e.getKeyCode();
        
       //if (key == KeyEvent.VK_ESCAPE) 
        //if (key == KeyEvent.VK_ENTER)  
        
        if (key == KeyEvent.VK_A) {
            player.setDx(-1);
            player_img.move(-1, 0);
        }
        if (key == KeyEvent.VK_D) {
        	player.setDx(1);
        	player_img.move(1, 0);
        }
        if (key == KeyEvent.VK_W) {
        	player.setDy(-1);
        	player_img.move(0, -1);
        }
        if (key == KeyEvent.VK_S) {
        	player.setDy(1);
        	player_img.move(0, 1);
        }
    }

	@Override
    public void keyReleased(KeyEvent e) {
        
        int key = e.getKeyCode();
        //if (key == KeyEvent.VK_ESCAPE) 
        //if (key == KeyEvent.VK_ENTER) 
        if (key == KeyEvent.VK_A) {
        	player.setDx(0);
        }
        if (key == KeyEvent.VK_D) {
        	player.setDx(0);
        }
        if (key == KeyEvent.VK_W) {
        	player.setDy(0);
        }
        if (key == KeyEvent.VK_S) {
        	player.setDy(0);
        }
    }

}