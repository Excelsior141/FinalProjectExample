package util;

public class Camera 
{
    private Ray[] rays;
    private Vector3 position;
    private int width;
    private int height;
    private double distance;
    
    public Camera(Vector3 position, int width, int height, double distance)
    {
        this.position = position;
        this.width = width;
        this.height = height;
        this.distance = distance;
        
        rays = new Ray[width * height];
        
        for (int y = 0; y < height; ++y)
        {
            for (int x = 0; x < width; ++x)
            {
                Vector3 direction = new Vector3(x - (width / 2), y - (height / 2), distance).add(position);
                rays[x + y * width] = new Ray(position, direction.normalize());
            }
        }
    }
    
    public Ray ScreenToRay(int x, int y)
    {
        return rays[x + ((height - 1) - y) * width];
    }
}
