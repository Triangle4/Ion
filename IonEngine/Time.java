package IonEngine;

public class Time 
{
    static long startTime;
    public static void Init()
    {
        startTime = System.currentTimeMillis();
    }
    public static float time()
    {
        float t = System.currentTimeMillis() - startTime;
        return t / 1000f;
    }
    public static float lastDeltaTime;
    public static float deltaTime;
}
