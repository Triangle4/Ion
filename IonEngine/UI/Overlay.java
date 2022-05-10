package IonEngine.UI;

import java.awt.Graphics;
import IonEngine.Debugging.*;

public class Overlay 
{
    public static void Draw(Graphics g)
	{
		g.drawString("LEVEL: 1", 10, 50);
        g.drawString("FPS:" + FPS.Get(), 10, 70);
		g.drawString(Debug.debugString, 10, 90);
	}
}
