package IonEngine.Base;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.util.*;

public class Input implements KeyListener
{
	private static Hashtable<String, Boolean> keys = new Hashtable<String, Boolean>();
	private static Hashtable<Integer, String> codes = new Hashtable<Integer, String>()
	{	
		
		{put(10, "RETURN");}
		{put(16, "LSHIFT");}
		{put(107, "+");}
		{put(109, "-");}
		{put(65, "A");}
		{put(66, "B");}
		{put(67, "C");}
		{put(68, "D");}
		{put(69, "E");}
		{put(70, "F");}
		{put(71, "G");}
		{put(72, "H");}
		{put(73, "I");}
		{put(74, "J");}
		{put(75, "K");}
		{put(76, "L");}
		{put(77, "M");}
		{put(78, "N");}
		{put(79, "O");}
		{put(80, "P");}
		{put(81, "Q");}
		{put(82, "R");}
		{put(83, "S");}
		{put(84, "T");}
		{put(85, "U");}
		{put(86, "V");}
		{put(87, "W");}
		{put(88, "X");}
		{put(89, "Y");}
		{put(90, "Z");}
	};
	public static boolean GetKey(String key)
	{
		return keys.containsKey(key) && keys.get(key);
	}
    public void keyPressed(KeyEvent key) 
    {
		int code = key.getExtendedKeyCode();
		if(codes.containsKey(code))
			keys.put(codes.get(code), true);
	}
	public void keyReleased(KeyEvent key) 
	{
		int code = key.getExtendedKeyCode();
		if(codes.containsKey(code))
		{
			String letter = codes.get(code);
			if(keys.containsKey(letter))
				keys.put(letter, false);
		}
	}
	public void keyTyped(KeyEvent arg0){}
}
