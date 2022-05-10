package IonEngine.Math;

import java.util.Random;

public class GeneralMath 
{
    private static Random rand = new Random();
    public static int GetRandomInt(int min, int max)
    {
        return rand.nextInt((max - min) + 1) + min;
    }
}
