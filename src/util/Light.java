package util;

import javafx.scene.paint.Color;

public class Light
{
    private Vector3 position;
    private Color color;
    
    public Light(Vector3 position, Color color)
    {
        this.position = position;
        this.color = color;
    }
    
    public Vector3 getPosition() {
        return position;
    }

    public Color getColor() {
        return color;
    }
}
