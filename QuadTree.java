//My Implementation of Quad Trees
//By: Jean Luka Molina
//18/03/2022

/**
 * Quad Tree Class
 */
public class QuadTree 
{
    private Rectangle data;
    private String type;
    private int level;
    private QuadTree topLeftRec;
    private QuadTree topRightRec;
    private QuadTree bottomLeftRec;
    private QuadTree bottomRightRec;

    /**
     * Creates Node of the Quad Tree
     * @param data The Rectangle belonging to the Node
     * @param name The Name of the Level of the Node
     * @param level The Level of the current node
     */
    public QuadTree(Rectangle data, String name, int level)
    {
        this.data = data;
        this.type = name;
        this.level = level;
        this.topLeftRec = null;
        this.topRightRec = null;
        this.bottomLeftRec = null;
        this.bottomRightRec = null;
    }

    /**
     * @return The current node
     */
    public QuadTree GetNode()
    {
        return(this);
    }

    /**
     * Returns the data
     * @return The Data
     */
    public Rectangle getData()
    {
        return(this.data);
    }

    /**
     * Return the current level
     * @return The level
     */
    public int GetLevel()
    {
        return(this.level);
    }
    
    /**
     * Gets the Midpoint of the current node
     * @return The Midpoint
     */
    public XYCoordinates MidPoint()
    {
        Rectangle myRec = data.GetRectangle();
        XYCoordinates midPoint = new XYCoordinates();
        double x1 = myRec.GetSWCoordinate().GetX();
        double y1 = myRec.GetSWCoordinate().GetY();
        double x2 = myRec.GetNECoordinate().GetX();
        double y2 = myRec.GetNECoordinate().GetY();

        return(midPoint.setCoordinates((x1 + x2) / 2, (y1 + y2) / 2));
    }


    /**
     * Helper method to allocate the appropriate points into each new rectangle
     * @param oldArray The array that will be changed
     * @param newSize The new size of the array
     * @param test The Rectangle in question
     * @return oldArray with its new size
     */
    private XYCoordinates [] PointsInsideHelper(XYCoordinates [] oldArray, int newSize, Rectangle test)
    {
        XYCoordinates [] temp = new XYCoordinates[newSize];
        int j = 0;

        for(int i = 0; i < oldArray.length; i++)
        {
            if(test.IsPointInsideRec(oldArray[i]))
            {
                temp[j] = oldArray[i];
                j++;
            }
        }
        oldArray = temp;
        temp = null;

        return(oldArray);
    }
    /**
     * Determines how many points are inside the rectangle that we have divided 
     * @param testCoordinates Pass the current rectangle's points to see which are valid
     * @param test  The Rectangle in question
     * @return the number of points that are inside the rectangle
     */
    public XYCoordinates [] PointsInside(XYCoordinates [] testCoordinates, Rectangle test)
    {
        int count = 0;
        XYCoordinates [] testPoints = this.data.GetPoints();

        for(int i = 0; i < testPoints.length; i++)
        {
            if(test.IsPointInsideRec(testPoints[i]))
            {
                count++;
            }
        }

        testPoints = PointsInsideHelper(testPoints, count, test);

        return(testPoints);
    }
    
    /**
     * Determines if we should divide the rectangle up to 4 new nodes
     * @param node The node that will be checked if it needs to be divided
     * @return True or False
     */
    public boolean ShouldDivide(QuadTree node)
    {
        if(node.getData().GetPoints().length > 1)
        {
            return(true);
        }

        return(false);
    }

    /**
     * Divide and create 4 new Rectangles
     * @param newLevel The New Level of the next 4 nodes
     */
    public void Divide4(int newLevel)
    {
        XYCoordinates center = MidPoint();
        Rectangle topLeft = new Rectangle(data.GetSWCoordinate().GetX(), center.GetY(),
                                          center.GetX(), center.GetY(),
                                          data.GetNWCoordinate().GetX(), data.GetNWCoordinate().GetY(),
                                          center.GetX(), data.GetNECoordinate().GetY());
        //System.out.println("Reached this point");
        //topLeft.PrintRectangle();
        topLeft.AddPoints(PointsInside(data.GetPoints(), topLeft));

        Rectangle topRight = new Rectangle(center.GetX(), center.GetY(),
                                          data.GetSECoordinate().GetX(), center.GetY(),
                                          center.GetX(), data.GetNWCoordinate().GetY(),
                                          data.GetNECoordinate().GetX(), data.GetNECoordinate().GetY());
        topRight.AddPoints(PointsInside(data.GetPoints(), topRight));

        Rectangle bottomLeft = new Rectangle(data.GetSWCoordinate().GetX(), data.GetSWCoordinate().GetY(),
                                          center.GetX(), data.GetSECoordinate().GetY(),
                                          data.GetNWCoordinate().GetX(), center.GetY(),
                                          center.GetX(), center.GetY());
        bottomLeft.AddPoints(PointsInside(data.GetPoints(), bottomLeft));

        Rectangle bottomRight = new Rectangle(center.GetX(), data.GetSWCoordinate().GetY(),
                                          data.GetSECoordinate().GetX(), data.GetSECoordinate().GetY(),
                                          center.GetX(), center.GetY(),
                                          data.GetNECoordinate().GetX(), center.GetY());
        bottomRight.AddPoints(PointsInside(data.GetPoints(), bottomRight));
        
        this.topLeftRec = new QuadTree(topLeft, "Top Left", newLevel + 1);
        this.topRightRec = new QuadTree(topRight, "Top Right", newLevel + 1);
        this.bottomLeftRec = new QuadTree(bottomLeft, "Bottom Left", newLevel + 1);
        this.bottomRightRec = new QuadTree(bottomRight, "Bottom Right", newLevel + 1);
    }

    /**
     * Traverses the tree recursively from whatever node it is given
     * @param node Current node 
     * @param newLevel The Level that is going to be added if it needs division 
     */
    public void CreateTree(QuadTree node, int newLevel)
    {
        if(node.topLeftRec == null && node.topRightRec == null &&
           node.bottomLeftRec == null && node.bottomRightRec == null)
        {
            if(ShouldDivide(node))
            {
                node.Divide4(newLevel);
            }
            else
            {
                return;
            }
            
        }

        //Travel all nodes
        CreateTree(node.topLeftRec, newLevel + 1);
        CreateTree(node.topRightRec, newLevel + 1);
        CreateTree(node.bottomLeftRec, newLevel + 1);
        CreateTree(node.bottomRightRec, newLevel + 1);

        return;
    }

    /**
     * Prints the entire tree
     * @param node Current node in question
     */
    public void PrintEntireTree(QuadTree node)
    {
        if(node == null)
        {
            return;
        }

        System.out.println(node.type + " Level: " + node.level + "\n----------------------------------");
        node.getData().PrintRectangle();
        System.out.println("----------------------------------");

        PrintEntireTree(node.topLeftRec);
        PrintEntireTree(node.topRightRec);
        PrintEntireTree(node.bottomLeftRec);
        PrintEntireTree(node.bottomRightRec);

        return;
    }
}
