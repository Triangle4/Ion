package IonEngine.Math;
public class Vector3 
{
    public double x;
    public double y;
    public double z;
    public Vector3()
    {
        this.x = 0;
        this.y = 0;
        this.z = 0;
    }
    public Vector3(double x, double y)
    {
        this.x = x;
        this.y = y;
        this.z = 0;
    }
    public Vector3(double x, double y, double z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    public Vector3(Vector3 v)
    {
        this.x = v.x;
        this.y = v.y;
        this.z = v.z;
    }
    public double magnitude()
    {
        return Math.sqrt((x *x + y * y + z * z));
    }
    public static double Magnitude(Vector3 v)
    {
        return Math.sqrt((v.x *v.x + v.y * v.y + v.z * v.z));
    }
    public static Vector3 Subtract(Vector3 a, Vector3 b)
    {
        return new Vector3(a.x - b.x, a.y - b.y, a.z - b.z);
    }
    public static double Distance(Vector3 a, Vector3 b)
    {
        return Subtract(a, b).magnitude();
    }
    public void normalize()
    {
        double magnitude = this.magnitude();
        double x = this.x / magnitude;
        double y = this.y / magnitude;
        double z = this.z / magnitude;
        this.x = x;
        this.y = y;
        this.z = z;
    }
}
