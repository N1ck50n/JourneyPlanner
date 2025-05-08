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

        // Form the path using previousLocation array by backtracking from the final destination.
        for (int i = endingIndex; i != -1; i = previousLocation[i])
        {
            tripData.addFirst(i);
        }

        // Check if final destination is reachable and if a shortest time has been set.
        if (timeToSource[endingIndex] == Double.MAX_VALUE) 
        {
            System.out.println("It is not possible to go from  " + startLocation + " to " + endLocation);
            return;
        }

        System.out.println("*** Minimal Time ***");
        // Print the first location and the trip color should be the line color for the second location.
        System.out.println(locationName.get(startingIndex) + " on " + tripLineColor[tripData.get(1)]);

        for (int j = 0; j < tripData.size() - 1; j++) 
        {
            currentLocationIndex = tripData.get(j);
            nextLocationIndex = tripData.get(j + 1);

            currentColor = tripLineColor[nextLocationIndex];

            double bestTime = -1; 
            // use bestTime to compare times for the same route between two locations.
            for (locationData bestWay : graph[currentLocationIndex][nextLocationIndex])
            {
                if (bestWay.color.equals(currentColor))
                {
                    bestTime = bestWay.time;
                    break;
                }
            }

            if (previousColor != null && !currentColor.equals(previousColor)) // If next color is different then current color.
            {
                System.out.println("** Change Line to " + currentColor + " **");
                bestTime = bestTime + 2;
                numOfChanges++;
            }

            System.out.println(locationName.get(nextLocationIndex) + " on " + currentColor);
            
            totalTime = totalTime + bestTime;
            // save the current color as previous for comparing with the next tram line color.
            previousColor = currentColor;
        }

        System.out.println();
        System.out.println("Overall Journey Time (mins) = " + totalTime);
        System.out.println("Number of changes = " + numOfChanges);
    }
}
