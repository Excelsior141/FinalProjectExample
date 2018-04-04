package util;

public class Vector3
{
    public double x;
    public double y;
    public double z;

    public Vector3(double x, double y, double z) 
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    public boolean isEqual(Vector3 other)
    {
        return (this.x == other.x && this.y == other.y && this.z == other.z);
    }
    
    public static boolean IsEqual(Vector3 a, Vector3 b)
    {
        return a.x == b.x && a.y == b.y && a.z == b.z;
    }
    
    public Vector3 multiply(Vector3 other)
    {
        return new Vector3(this.x * other.x, this.y * other.y, this.z * other.z);
    }
    
    public Vector3 multiply(double scale)
    {
        return new Vector3(this.x * scale, this.y * scale, this.z * scale);
    }
    
    public static Vector3 Multiply(Vector3 a, Vector3 b)
    {
        return new Vector3(a.x * b.x, a.y * b.y, a.z * b.z);
    }
    
    public static Vector3 Multiply(Vector3 vector, double scale)
    {
        return new Vector3(vector.x * scale, vector.y * scale, vector.z * scale);
    }
    
    public Vector3 add(Vector3 other)
    {
        return new Vector3(this.x + other.x, this.y + other.y, this.z + other.z);
    }
    
    public static Vector3 Add(Vector3 a, Vector3 b)
    {
        return new Vector3(a.x + b.x, a.y + b.y, a.z + b.z);
    }
    
    public Vector3 subtract(Vector3 other)
    {
        return new Vector3(this.x - other.x, this.y - other.y, this.z - other.z);
    }
    
    public static Vector3 Subtract(Vector3 a, Vector3 b)
    {
        return new Vector3(a.x - b.x, a.y - b.y, a.z - b.z);
    }
    
    public double dot(Vector3 other)
    {
        return ((this.x * other.x) + (this.y * other.y) + (this.z * other.z));
    }
    
    public static double Dot(Vector3 a, Vector3 b)
    {
        return ((a.x * b.x) + (a.y * b.y) + (a.z * b.z));
    }
    
    public Vector3 cross(Vector3 other)
    {
        return new Vector3(((this.y * other.z) - (this.z * other.y)), ((this.z * other.x) - (this.x * other.z)), ((this.x * other.y) - (this.y * other.x)));
    }
    
    public Vector3 Cross(Vector3 a, Vector3 b)
    {
        return new Vector3(((a.y * b.z) - (a.z * b.y)), ((a.z * b.x) - (a.x * b.z)), ((a.x * b.y) - (a.y * b.x)));
    }
    
    public double magnitude()
    {
        return Math.sqrt(dot(this));
    }
    
    public static double Magnitude(Vector3 vector)
    {
        return Math.sqrt(Dot(vector, vector));
    }
    
    public Vector3 normalize()
    {
        double magnitude = this.magnitude();
        double scale = magnitude == 0 ? Double.POSITIVE_INFINITY : 1d / magnitude;
        
        return this.multiply(scale);
    }
    
    public static Vector3 Normalize(Vector3 vector)
    {
        double magnitude = vector.magnitude();
        double scale = magnitude == 0 ? Double.POSITIVE_INFINITY : 1d / magnitude;
        
        return vector.multiply(scale);
    }
}
