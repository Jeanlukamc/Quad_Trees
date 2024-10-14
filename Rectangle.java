//My Implementation of Quad Trees
//By: Jean Luka Molina
//18/03/2022

/**
 * Rectangle Class
 */
public class Rectangle 
{
    private XYCoordinates southWest = new XYCoordinates();
    private XYCoordinates southEast = new XYCoordinates();
    private XYCoordinates northWest = new XYCoordinates();
    private XYCoordinates northEast = new XYCoordinates();
    private XYCoordinates [] pointsInside;

    /**
     * Create the Rectangle based on the Area created by Rows X Columns
     * @param rows The number of rows available
     * @param columns The number of columns available
     * @param pointsWithin The Points that reside inside the rectangle
     */
    public Rectangle(int rows, int columns, XYCoordinates [] pointsWithin)
    {
        this.southWest.setCoordinates(0, 0);
        this.southEast.setCoordinates(columns, 0);
        this.northWest.setCoordinates(0, rows);
        this.northEast.setCoordinates(columns, rows);
        this.pointsInside = pointsWithin;
    }

    /**
     * Manually creates a Rectangle
     * @param x1 X-Value 1
     * @param x2 X-Value 2
     * @param x3 X-Value 3
     * @param x4 X-Value 4
     * @param y1 Y-Value 1
     * @param y2 Y-Value 2
     * @param y3 Y-Value 3
     * @param y4 Y-Value 4
     */
    public Rectangle(double x1, double y1, double x2, double y2, double x3, double y3, double x4, double y4)
    {
        this.southWest.setCoordinates(x1, y1);
        this.southEast.setCoordinates(x2, y2);
        this.northWest.setCoordinates(x3, y3);
        this.northEast.setCoordinates(x4, y4);
        this.pointsInside = null;
    }

    /**
     * @return The Rectagle
     */
    public Rectangle GetRectangle()
    {
        return(this);
    }

    /**
     * @return the South West Point
     */
    public XYCoordinates GetSWCoordinate()
    {
        return(this.southWest);
    }
    
    /**
     * @return the South East Point
     */
    public XYCoordinates GetSECoordinate()
    {
        return(this.southEast);
    }

    /**
     * @return the North West Point
     */
    public XYCoordinates GetNWCoordinate()
    {
        return(this.northWest);
    }

    /**
     * @return the North East Point
     */
    public XYCoordinates GetNECoordinate()
    {
        return(this.northEast);
    }

    /**
     * Get the points inside the rectangle
     * @return The points inside
     */
    public XYCoordinates [] GetPoints()
    {
        return(this.pointsInside);
    }

    /**
     * Add a new set of points that are inside the Rectangle
     * @param newPoints The new points that will be added
     */
    public void AddPoints(XYCoordinates [] newPoints)
    {
        this.pointsInside = newPoints;
    }
    /**
     * Print the Rectangle and the points that are within it
     */
    public void PrintRectangle()
    {
        System.out.println("Lower Left Corner: (" + southWest.GetX() +", " + southWest.GetY() + ")");
        System.out.println("Lower Right Corner: (" + southEast.GetX() +", " + southEast.GetY() + ")");
        System.out.println("Upper Left Corner: (" + northWest.GetX() +", " + northWest.GetY() + ")");
        System.out.println("Upper Right Corner: (" + northEast.GetX() +", " + northEast.GetY() + ")");
        System.out.println("Points Inside: ");
        PrintPoints(pointsInside);
    }

    /**
     * Prints the points inside the rectangle
     * @param points The points that will be printed
     */
    public void PrintPoints(XYCoordinates[] points)
    {
        if (points == null || points.length == 0)
        {
            System.out.println("No Points inside!");
            return;
        }
        for (int i = 0; i < points.length; i++)
        {
            System.out.println(points[i]);
        }
    }

    /**
     * Tells whether or not the point lies within the current rectangle
     * @param point The point that will be tested
     * @return True or False
     */
    public boolean IsPointInsideRec(XYCoordinates point)
    {
        if(point.GetX() > southWest.GetX() && point.GetX() < northEast.GetX()
        && point.GetY() > southWest.GetY() && point.GetY() < northEast.GetY())
        {
            return(true);
        }

        return(false);
    }
}
