package IonEngine;

import java.awt.Color;

import IonEngine.Geometry.Mesh;
import IonEngine.Geometry.Triangle;
import IonEngine.Math.*;
import IonEngine.Base.Input;
import IonEngine.Debugging.*;

public class Camera 
{
    public double fieldOfView = 90f;
    public double nearPlane = 0.1f;
    public double farPlane = 1000f;

    public Matrix4x4 matProj = new Matrix4x4();
    double theta;
    //Debug Section
    Mesh mesh = new Mesh("CUBE");
    Vector3 position = new Vector3();

    public void Init()
    {
        SetMatrix4x4();
    }
    void SetMatrix4x4()
    {
        matProj = new Matrix4x4();
        
        double fovRad = 1.0f / Math.tan((fieldOfView * 0.5f / 180f) * 3.14159f);
        matProj.matrix[0][0] = (double)Game.HEIGHT / (double)Game.WIDTH * fovRad;
        matProj.matrix[1][1] = fovRad;
        matProj.matrix[2][2] = farPlane / (farPlane - nearPlane);
        matProj.matrix[3][2] = (-farPlane * nearPlane) / (farPlane - nearPlane);
        matProj.matrix[2][3] = 1.0f;
        matProj.matrix[3][3] = 0.0f;
    }
    public void ClearScreen() // Draw Blue on the top half of the screen
    {
        for(int i = 0; i < Game.pixels.length; i++) 
        {
			Game.pixels[i] = Game.background.getRGB();
		}
    }

    public void MultiplyMatrixVector(Vector3 i, Vector3 o, Matrix4x4 m)
    {
        o.x = i.x * m.matrix[0][0] + i.y * m.matrix[1][0] + i.z * m.matrix[2][0] * m.matrix[3][0];
		o.y = i.x * m.matrix[0][1] + i.y * m.matrix[1][1] + i.z * m.matrix[2][1] * m.matrix[3][1];
		o.z = i.x * m.matrix[0][2] + i.y * m.matrix[1][2] + i.z * m.matrix[2][2] * m.matrix[3][2];
		double w = i.x * m.matrix[0][3] + i.y * m.matrix[1][3] + i.z * m.matrix[2][3] * m.matrix[3][3];

		if (w != 0.0)
		{
			o.x /= w;
            o.y /= w;
            o.z /= w;
		}
    }
    void Move()
    {
        if(Input.GetKey("W"))
            position.z += 1;
        else if(Input.GetKey("S"))
            position.z-= 1;
        if(Input.GetKey("X"))
            position.y+= 0.001;
        else if(Input.GetKey("Z"))
            position.y-= 0.001;
        if(Input.GetKey("A"))
            theta += 1.0f * Time.deltaTime * 0.001f;
        else if(Input.GetKey("D"))
            theta -= 1.0f * Time.deltaTime * 0.001f;
        
    }
    
    public void Render()
    {
        Move();
        Matrix4x4 matRotZ = new Matrix4x4();
        Matrix4x4 matRotX = new Matrix4x4();
        
        matRotZ.matrix[0][0] = Math.cos(theta);
		matRotZ.matrix[0][1] = Math.sin(theta);
		matRotZ.matrix[1][0] = -Math.sin(theta);
		matRotZ.matrix[1][1] = Math.cos(theta);
		matRotZ.matrix[2][2] = 1.0f;
		matRotZ.matrix[3][3] = 1.0f;

        matRotX.matrix[0][0] = 1.0f;
		matRotX.matrix[1][1] = Math.cos(theta * 0.5f);
		matRotX.matrix[1][2] = Math.sin(theta * 0.5f);
		matRotX.matrix[2][1] = -Math.sin(theta * 0.5f);
		matRotX.matrix[2][2] = Math.cos(theta * 0.5f);
		matRotX.matrix[3][3] = 1.0f;
        for(int i = 0; i < mesh.triangles.length; i++)
        {
        Triangle tri = mesh.triangles[i];
        Triangle projected = new Triangle();
        Triangle translated = new Triangle();
        Triangle rotatedZ = new Triangle();
        Triangle rotatedZX = new Triangle();

        //Rotate in Z-Axis
        MultiplyMatrixVector(tri.p[0], rotatedZ.p[0], matRotZ);
        MultiplyMatrixVector(tri.p[1], rotatedZ.p[1], matRotZ);
        MultiplyMatrixVector(tri.p[2], rotatedZ.p[2], matRotZ);

        //Rotate in X-Axis
        MultiplyMatrixVector(rotatedZ.p[0], rotatedZX.p[0], matRotX);
        MultiplyMatrixVector(rotatedZ.p[1], rotatedZX.p[1], matRotX);
        MultiplyMatrixVector(rotatedZ.p[2], rotatedZX.p[2], matRotX);
        
        translated = new Triangle(rotatedZX);

        translated.p[0].z = rotatedZX.p[0].z + 50f;
        translated.p[1].z = rotatedZX.p[1].z + 50f;
        translated.p[2].z = rotatedZX.p[2].z + 50f;

        // Project triangles from 3D --> 2D
        MultiplyMatrixVector(translated.p[0], projected.p[0], matProj);
        MultiplyMatrixVector(translated.p[1], projected.p[1], matProj);
        MultiplyMatrixVector(translated.p[2], projected.p[2], matProj);

        // Scale into view
        projected.p[0].x += 1.0f;
        projected.p[1].x += 1.0f;
        projected.p[2].x += 1.0f;
        projected.p[0].y += 1.0f;
        projected.p[1].y += 1.0f;
        projected.p[2].y += 1.0f;

        projected.p[0].x *= 0.5f * (double)Game.WIDTH;
        projected.p[1].x *= 0.5f * (double)Game.WIDTH;
        projected.p[2].x *= 0.5f * (double)Game.WIDTH;
        projected.p[0].y *= 0.5f * (double)Game.HEIGHT;
        projected.p[1].y *= 0.5f * (double)Game.HEIGHT;
        projected.p[2].y *= 0.5f * (double)Game.HEIGHT;

        DrawTriangle(projected);
        }
    }
    
    void DrawTriangle(Triangle t)
	{
		DrawLine(t.p[0], t.p[1]);
		DrawLine(t.p[1], t.p[2]);
		DrawLine(t.p[2], t.p[0]);
	}
    void DrawLine(Vector3 a, Vector3 b)
	{
		double x;
        double y;
        double xDifference = b.x - a.x; 
        double yDifference = b.y - a.y;
        double xDistance = Math.abs(xDifference); 
        double yDistance = Math.abs(yDifference);
        double px = 2 * yDistance - xDistance;	
        double py = 2 * xDistance - yDistance;
        double xe;
        double ye;

		if (yDistance <= xDistance)
		{
			if (xDifference >= 0)
			{
                x = a.x;
                y = a.y;
                xe = b.x; 
            }
			else
			{
                x = b.x;
                y = b.y;
                xe = a.x;
            }

			SetPixel(x, y);
			
			while(x < xe)
			{
				x = x + 1;
				if (px<0)
					px = px + 2 * yDistance;
				else
				{
					if ((xDifference < 0 && yDifference < 0) || (xDifference > 0 && yDifference > 0))
                        y = y + 1; 
                    else
                        y = y - 1;
					px = px + 2 * (yDistance - xDistance);
				}
				SetPixel(x, y);
			}
		}
		else
		{
			if (yDifference >= 0)
			{
                x = a.x;
                y = a.y;
                ye = b.y;
            }
			else
			{
                x = b.x;
                y = b.y;
                ye = a.y;
            }

			SetPixel(x, y);

			while(y < ye)
			{
				y = y + 1;
				if (py <= 0)
                {
					py = py + 2 * xDistance;
                }
				else
				{
					if ((xDifference < 0 && yDifference < 0) || (xDifference > 0 && yDifference > 0))
                        x = x + 1;
                    else
                        x = x - 1;
					py = py + 2 * (xDistance - yDistance);
				}
				SetPixel(x, y);
			}
		}
	}
    void SetPixel(double x, double y)
	{
        int i = (int)(x + y * Game.WIDTH);
		if (i > 0 && i < Game.WIDTH * Game.HEIGHT)
		    Game.pixels[i] = Color.white.getRGB();
	}
    double Clamp(double value, double min, double max)
	{
		if(value > max) value = max;
		if(value < min) value = min;
		return value;
	}
    double Round(double value)
    {
        if(value % 1 >= 0.5)
            return Math.ceil(value);
        return Math.floor(value);
        
    }
    
    
}
