//My Implementation of Quad Trees
//By: Jean Luka Molina
//18/03/2022

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main 
{
    public static void main(String [] args)
    {
        XYCoordinates [] myPoints = GetPoints();
        Rectangle myRectangle = new Rectangle(6, 6, myPoints);
        QuadTree root = new QuadTree(myRectangle, " Root", 0);
        root.CreateTree(root, root.GetLevel());
        root.PrintEntireTree(root);

    }

    public static XYCoordinates [] GetPoints()
    {
        XYCoordinates [] Points = new XYCoordinates[0];
        int size;

        try
        {
            File input_file_1 = new File("Points.txt");
            
            try(Scanner file_1 = new Scanner(input_file_1))
            {
                
                size = file_1.nextInt();
                Points = new XYCoordinates[size];
                
                for(int i = 0; i < Points.length; i++)
                {
                    XYCoordinates Point = new XYCoordinates();
                    double x = file_1.nextDouble();
                    double y = file_1.nextDouble();
                    Point.setCoordinates(x, y);
                    Points[i] = Point;
                }
            }
        }
        catch (FileNotFoundException fnfe)
        {
            System.out.println("Message " + fnfe.getMessage());
            System.out.println("\ntoString(): " + fnfe + "\n");
            fnfe.printStackTrace();
        }

        return(Points);
    }
}
