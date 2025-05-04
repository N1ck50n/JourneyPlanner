public class Output extends Pathfinding
{
    int currentLocationIndex = 0;
    int nextLocationIndex = 0;
    Double totalTime = 0.0;
    int numOfChanges = 0;
    String currentColor;    
    String previousColor;

    public Output()
    {
        super();

        if (timeToSource[endingIndex] == Double.MAX_VALUE) 
        {
            System.out.println("It is not possible to go from  " + startLocation + " to " + endLocation);
            return;
        }

        for (int m = endingIndex; m != -1; m = previousLocation[m]) 
        {
            tripData.addFirst(m);
        }

        System.out.println("*** Minimal Time Route ***");

        for (int i = 0; i < tripData.size() - 1; i++) 
        {
            currentLocationIndex = tripData.get(i);
            nextLocationIndex = tripData.get(i + 1);
            currentColor = graph[currentLocationIndex][nextLocationIndex].color;

            totalTime = totalTime + graph[currentLocationIndex][nextLocationIndex].time + 2;


            if (previousColor != null && currentColor.equals(previousColor) == false)
            {
                System.out.println("** Change Line to " + currentColor + " **");
                numOfChanges++;
            }

            System.out.println(locationName.get(nextLocationIndex) + " on " + graph[currentLocationIndex][nextLocationIndex].color);
            
            previousColor = graph[currentLocationIndex][nextLocationIndex].color;
        }

        System.out.println();
        System.out.println("Overall Journey Time (mins) = " + totalTime);
        System.out.println("Number of changes = " + numOfChanges);
    }
}
