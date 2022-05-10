package IonEngine;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import javax.swing.JFrame;

import IonEngine.Base.Input;
import IonEngine.Debugging.*;
import IonEngine.UI.*;

import java.awt.Font;

public class Game extends JFrame implements Runnable
{
	Font timesnewroman = new Font ("Times New Roman", 1, 17);
    
	private static final long serialVersionUID = 1L;
	public static int WIDTH = 1280;
	public static int HEIGHT = 720;
	private Thread thread;
	private boolean running;
	private BufferedImage image;

	public static Color background = new Color(0,0,0,1);
	
	public static int[] pixels;
	public Input input = new Input();

    public static void main(String [] args) 
    {
        Time.Init();
		Game game = new Game();
        game.Start();
	}
    
    public void Start()
    {
		thread = new Thread(this);
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData(); 
		addKeyListener(input);
		setSize(WIDTH, HEIGHT);
		setResizable(false);
		setTitle("ION");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBackground(Color.black);
		setLocationRelativeTo(null);
		setVisible(true);
		mainCamera.Init();
		start();
	}
	private synchronized void start() 
    {
		running = true;
		thread.start();
	}
	public synchronized void stop() 
    {
		running = false;
		try 
        {
			thread.join();
		}
        catch(InterruptedException e) 
        {
			e.printStackTrace();
		}
	}
	
	public void run() 
    {
		long lastTime = System.nanoTime();
		final double ns = 1000000000.0 / 60.0;//60 times per second
		double delta = 0;
		requestFocus();
		while(running) 
        {
			long now = System.nanoTime();
			delta = delta + ((now-lastTime) / ns);
			Time.deltaTime = (float)delta;
			lastTime = now;
			if(delta >= 1)
			{
				delta--;
			}
			Blit();
		}
	}
	void Blit()
	{
		BufferStrategy bs = getBufferStrategy();
		if(bs != null) 
        {
			Graphics g = bs.getDrawGraphics();
			g.drawImage(image, 0, 0, image.getWidth(), image.getHeight(), null);
			g.setColor(Color.WHITE);
			Render(g);
			bs.show();
		}
		else
			createBufferStrategy(3);
	}
	Camera mainCamera = new Camera();
	void Render(Graphics g) 
    {
		mainCamera.ClearScreen();
		mainCamera.Render();
		Overlay.Draw(g);
	}
}