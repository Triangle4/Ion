package IonEngine.UI;

import IonEngine.*;

public class FPS 
{
    private static float lastTime;
    private static int frames;
    private static int fps = 0;
    private static int fpsRefreshRate = 6;

    public static int Get()
    {
        frames++;
        if(Time.time() - lastTime >= 1f / (float)fpsRefreshRate)
        {
            lastTime = Time.time();
            fps = frames * fpsRefreshRate;
            frames = 0;
        }
        return fps;
    }
}