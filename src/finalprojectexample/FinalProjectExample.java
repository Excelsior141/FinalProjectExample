package finalprojectexample;

import java.util.Random;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.image.PixelWriter;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import util.*;

public class FinalProjectExample extends Application 
{
    final int ScreenWidth = 640;
    final int ScreenHeight = 480;
    
    Sphere[] spheres = new Sphere[]
    {
        new Sphere(Color.color(0.8d, 0.2d, 0.2d), new Vector3(1d, 2d, 10d), 1d),
        new Sphere(Color.color(0.2d, 0.2d, 0.8d), new Vector3(-1d, 2d, 10d), 1d),
        new Sphere(Color.color(0.5d, 0.5d, 0.5d), new Vector3(0d, -5000d, 0d), 5000d),
    };
    
    Light[] lights = new Light[]
    {
        new Light(new Vector3(0d, 5d, 5d), Color.color(0.5d, 0.5d, 0.2d)),
        //new Light(new Vector3(-5d, 1d, 5d), Color.color(0.5d, 0d, 0d)),
    };
    
    Camera camera = new Camera(new Vector3(0d, 1d, 0d), ScreenWidth, ScreenHeight, 1000d);
    
    @Override
    public void start(Stage primaryStage) {
        StackPane root = new StackPane();
        Scene scene = new Scene(root, ScreenWidth, ScreenHeight);
        Canvas canvas = new Canvas(ScreenWidth, ScreenHeight);
        root.getChildren().add(canvas);
        
        PixelWriter pixelWriter = canvas.getGraphicsContext2D().getPixelWriter();

        for (int y = 0; y < ScreenHeight; ++y)
        {
            for (int x = 0; x < ScreenWidth; ++x)
            {
                pixelWriter.setColor(x, y, render(x, y));
            }
        }
        
        primaryStage.setTitle("Raytracer example");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    private Color render(int x, int y)
    {
        Ray ray = camera.ScreenToRay(x, y);
        Sphere sphere = null;
        
        double distance = Double.POSITIVE_INFINITY;
        double minDistance = Double.POSITIVE_INFINITY;
        
        for (int i = 0; i < spheres.length; ++i)
        {
            if ((distance = spheres[i].Intersect(ray)) > 0d)
            {
                if (distance < minDistance)
                {
                    minDistance = distance;
                    sphere = spheres[i];
                }
            }
        }
        
        if (sphere != null)
        {
            InternalColor color = new InternalColor(sphere.getColor());
            Vector3 spherePoint = ray.direction.multiply(minDistance);
            Vector3 sphereNormal = spherePoint.subtract(sphere.getPosition()).normalize();

            for (int l = 0; l < lights.length; ++l)
            {
                Vector3 lightDirection = lights[l].getPosition().subtract(spherePoint).normalize();
                
                Ray shadowRay = new Ray(spherePoint.add(sphereNormal.multiply(1.1d)), lightDirection);
                boolean inShadow = false;
                
                for(int s = 0; s < spheres.length; ++s)
                {
                    if (spheres[s].Intersect(shadowRay) > 0d)
                    {
                        inShadow = true;
                        break;
                    }
                }
                
                if (!inShadow)
                {
                    double strength = sphereNormal.dot(lightDirection);
                    color = color.add(new InternalColor(lights[l].getColor()).multiply(strength));
                }
                else
                {
                    color = color.multiply(0.5d);
                }
            }
            
            return color.getColor();
        }
        
        return Color.SKYBLUE;
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
