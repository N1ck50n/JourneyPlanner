public class Output extends Pathfinding
{
    public Output()
    {
        super();

        for (int m = endingIndex; m != -1; m = previousLocation[m]) 
        {
            tripData.addFirst(m);
        }

        if (timeToSource[endingIndex] == Double.MAX_VALUE) 
        {
            System.out.println("It is not possible to go from  " + startLocation + " to " + endLocation);
            return;
        }

        System.out.println("*** Minimal Time Route ***");
        System.out.println("shortest path from " + startLocation + " to " + endLocation + ":");

        for (int i = 0; i < tripData.size() - 1; i++) 
        {
            int currentLocationIndex = tripData.get(i);
            int nextLocationIndex = tripData.get(i + 1);
            System.out.println(locationName.get(currentLocationIndex) + " to " + locationName.get(nextLocationIndex) + " in " + graph[currentLocationIndex][nextLocationIndex].color + " (" + graph[currentLocationIndex][nextLocationIndex].time + " mins)");
        }
    }
}
