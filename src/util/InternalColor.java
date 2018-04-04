package util;

import javafx.scene.paint.Color;

public class InternalColor
{
    public double red;
    public double green;
    public double blue;
    
    public InternalColor(double red, double green, double blue)
    {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }
    
    public InternalColor(Color color)
    {
        this.red = color.getRed();
        this.green = color.getGreen();
        this.blue = color.getBlue();
    }
    
    public InternalColor add(InternalColor other)
    {
        return new InternalColor(this.red + other.red, this.green + other.green, this.blue + other.blue);
    }
    
    public InternalColor subtract(InternalColor other)
    {
        return new InternalColor(this.red - other.red, this.green - other.green, this.blue - other.blue);
    }
    
    public InternalColor multiply(InternalColor other)
    {
        return new InternalColor(this.red * other.red, this.green * other.green, this.blue * other.blue);
    }
    
    public InternalColor multiply(double scale)
    {
        return new InternalColor(this.red * scale, this.green * scale, this.blue * scale);
    }
    
    public InternalColor divide(InternalColor other)
    {
        return new InternalColor(this.red / other.red, this.green / other.green, this.blue / other.blue);
    }
    
    public InternalColor divide(double scale)
    {
        return new InternalColor(this.red / scale, this.green / scale, this.blue / scale);
    }
    
    public InternalColor average(InternalColor other)
    {
        return this.add(other).divide(2d);
    }
    
    public Color getColor()
    {
        return Color.color(clamp(red), clamp(green), clamp(blue));
    }
    
    private double clamp(double value)
    {
        double result = value;
        
        if (result > 1.0d)
            result = 1.0d;
        
        if (result < 0.0d)
            result = 0d;

        return result;
    }
}
