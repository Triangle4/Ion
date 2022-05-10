package IonEngine.Geometry;

import java.util.Vector;

import IonEngine.Math.*;
public class Triangle 
{
    public Vector3 [] p = new Vector3 [3];

    public Triangle()
    {
        p[0] = new Vector3();
        p[1] = new Vector3();
        p[2] = new Vector3();
    }
    public Triangle(Vector3 a, Vector3 b, Vector3 c)
    {
        p[0] = new Vector3(a);
        p[1] = new Vector3(b);
        p[2] = new Vector3(c);
    }
    public Triangle(Triangle t)
    {
        p[0] = new Vector3(t.p[0]);
        p[1] = new Vector3(t.p[1]);
        p[2] = new Vector3(t.p[2]);
    }
}
