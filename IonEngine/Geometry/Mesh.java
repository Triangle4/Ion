package IonEngine.Geometry;

import IonEngine.Math.Vector3;
import IonEngine.Debugging.*;

public class Mesh 
{
    public Triangle [] triangles;
    public Mesh(String meshType)
    {
        if(meshType == "CUBE")
        {
            triangles = new Triangle [12];
            triangles[0] = new Triangle(new Vector3(0,0,0), new Vector3(0,1,0), new Vector3(1,1,0));
            triangles[1] = new Triangle(new Vector3(0,0,0), new Vector3(1,1,0), new Vector3(1,0,0));
            
            triangles[2] = new Triangle(new Vector3(1,0,0), new Vector3(1,1,0), new Vector3(1,1,1));
            triangles[3] = new Triangle(new Vector3(1,0,0), new Vector3(1,1,1), new Vector3(1,0,1));
        
            triangles[4] = new Triangle(new Vector3(1,0,1), new Vector3(1,1,1), new Vector3(0,1,1));
            triangles[5] = new Triangle(new Vector3(1,0,1), new Vector3(0,1,1), new Vector3(0,0,1));

            triangles[6] = new Triangle(new Vector3(0,0,1), new Vector3(0,1,1), new Vector3(0,1,0));
            triangles[7] = new Triangle(new Vector3(0,0,1), new Vector3(0,1,0), new Vector3(0,0,0));

            triangles[8] = new Triangle(new Vector3(0,1,0), new Vector3(0,1,1), new Vector3(1,1,1));
            triangles[9] = new Triangle(new Vector3(0,1,0), new Vector3(1,1,1), new Vector3(1,1,0));
            
            triangles[10] = new Triangle(new Vector3(1,0,1), new Vector3(0,0,1), new Vector3(0,0,0));
            triangles[11] = new Triangle(new Vector3(1,0,1), new Vector3(0,0,0), new Vector3(1,0,0));
            Debug.Log(triangles.length);
        }
    }
}
