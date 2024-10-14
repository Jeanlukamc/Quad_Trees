//My Implementation of Quad Trees
//By: Jean Luka Molina
//18/03/2022

public class XYCoordinates
{
    private double x;
    private double y;

    /**
     * Construct the default XY Coordinates
     */
    public XYCoordinates()
    {
        this.x = 0.0;
        this.y = 0.0;
    }

    /**
     * Set the coordinates of XY manually
     * @param xPoint The X-Axis Point
     * @param yPoint The Y-Axis Point
     * @return The Coordinates
     */
    public XYCoordinates setCoordinates(double xPoint, double yPoint)
    {
        this.x = xPoint;
        this.y = yPoint;

        return(this);
    }

    /**
     * @return the X Point
     */
    public double GetX()
    {
        return(this.x);
    }

    /**
     * @return the Y Point
     */
    public double GetY()
    {
        return(this.y);
    }

    /**
     * Convert the Coordinates into a string
     */
    public String toString()
    {
        return("(" + this.x + ", " + this.y + ")");
    }
}    
