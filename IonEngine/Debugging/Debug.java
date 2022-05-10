package IonEngine.Debugging;
public class Debug 
{
    public static void Log(Object object)
    {
        System.out.println(object);
    }
    public static void Print(Object object)
    {
        debugString = object.toString();
    }
    public static String debugString  = "";
}
