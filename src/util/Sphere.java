package util;

import javafx.scene.paint.Color;

public class Sphere 
{
    private Color color;
    private Vector3 position;
    private double radius;
    
    public Sphere(Color color, Vector3 position, double radius)
    {
        this.color = color;
        this.position = position;
        this.radius = radius;
    }
    
    public double Intersect(Ray ray)
    {
        Vector3 objectDirection = position.subtract(ray.origin);

        double t = 0d;
        double b = objectDirection.dot(ray.direction);
        double det = (b * b) - objectDirection.dot(objectDirection) + (radius * radius);
        
        if (det < 0d)
            return 0d;
        else
            det = Math.sqrt(det);
        
        return (t = b - det) > Double.MIN_VALUE ? t : ((t = b + det) > Double.MIN_VALUE ? t : 0d);
    }

    public Color getColor() {
        return color;
    }

    public Vector3 getPosition() {
        return position;
    }
}
