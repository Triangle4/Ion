package IonEngine.Math;
public class Vector3Int 
{
    public int x;
    public int y;
    public int z;
    public Vector3Int()
    {
        this.x = 0;
        this.y = 0;
        this.z = 0;
    }
    public Vector3Int(int x, int y, int z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    public Vector3Int(Vector3Int v)
    {
        this.x = v.x;
        this.y = v.y;
        this.z = v.z;
    }
    public int magnitude()
    {
        return (int)Math.sqrt((x * x + y * y + z * z));
    }
    public static int Magnitude(Vector3Int v)
    {
        return (int)Math.sqrt((v.x *v.x + v.y * v.y + v.z * v.z));
    }
    public static Vector3Int Subtract(Vector3Int a, Vector3Int b)
    {
        return new Vector3Int(a.x - b.x, a.y - b.y, a.z - b.z);
    }
    public static int Distance(Vector3Int a, Vector3Int b)
    {
        return Subtract(a, b).magnitude();
    }
    public void normalize()
    {
        int magnitude = this.magnitude();
        int x = this.x / magnitude;
        int y = this.y / magnitude;
        int z = this.z / magnitude;
        this.x = x;
        this.y = y;
        this.z = z;
    }
}
