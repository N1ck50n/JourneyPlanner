import java.util.LinkedList;

public class Pathfinding extends Input 
{
    boolean[] visited; //Stores nodes that are already visited and their shortest distance has been found.
    LinkedList<Integer> tripData; //Stores the locations in order to reach the final destination.
    Double[] timeToSource; //Time taken to reach the initial source location.
    String[] tripLineColor; //Color of the line.
    int[] numofLineChanges; //number of tram changes made.
    int[] previousLocation; //Stores the previous location of the index's location.
    int startingIndex = 0; //Index of initial location.
    int endingIndex = 0; //Index of final destination.
    Double newTime = 0.0; //Temporary variable used to check for possible new times.
    int minimumChanges = Integer.MAX_VALUE; //temporary variable that will store the min changes to compare in the loop.

    public Pathfinding() 
    {
        super();
        visited = new boolean[indexNum];
        tripData = new LinkedList<>();
        timeToSource = new Double[indexNum];
        tripLineColor = new String[indexNum];
        numofLineChanges = new int[indexNum];
        previousLocation = new int[indexNum];

        startingIndex = locationIndexes.get(startLocation);
        endingIndex = locationIndexes.get(endLocation);

        //Initialise the variables used to sort paths.
        for (int i = 0; i < indexNum; i++) 
        {
            visited[i] = false;
            timeToSource[i] = Double.MAX_VALUE;
            numofLineChanges[i] = Integer.MAX_VALUE;
            previousLocation[i] = -1;
        }

        timeToSource[startingIndex] = 0.0;
        numofLineChanges[startingIndex] = 0;
        

        if (pathfindingType == 1) //Shortest path option.
        {
            while (true) 
            {
                // next is a temporary variable used to store the next station you will visit.
                int next = -1;
                for (int k = 0; k < indexNum; k++) 
                {
                    if (visited[k] == false)
                    {
                        //if shorter time is found then assign k to next.
                        if ((next == -1 || timeToSource[k] < timeToSource[next]))
                        {
                            next = k;
                        }  
                    }
                }
                // If time is still infinite break from the loop, the node is unreachable.
                if (next == -1 || timeToSource[next] == Double.MAX_VALUE)
                {
                    break;
                }

                visited[next] = true; //set the node as visited.

                
                //Check neighbors of the selected node and if the path is shorter through it.
                for (int n = 0; n < indexNum; n++) 
                {
                    if (visited[n] == false) 
                    {
                        if (graph[next][n] != null) //Check if you can go from next to n.
                        {
                            // Go over all the different types of ways to go to the same location and choose the shortest time.
                            for (locationData shortestWay : graph[next][n])
                            {
                                newTime = timeToSource[next] + shortestWay.time;
                            
                                if (tripLineColor[next] != null && !tripLineColor[next].equals(shortestWay.color)) // Add 2 mins if you have to change trams.
                                {
                                    newTime = newTime + 2.0;
                                }

                                if (newTime < timeToSource[n]) 
                                {
                                    timeToSource[n] = newTime;
                                    tripLineColor[n] = shortestWay.color;
                                    previousLocation[n] = next;
                                }
                            }
                        }
                    }
                }
            }
        
        }
        if (pathfindingType == 2) // Fewest number of changes option.
        {
            while (true) 
            {
                // next is a temporary variable used to store the next station you will visit.
                int next = -1;
                for (int k = 0; k < indexNum; k++) 
                {
                    if (visited[k] == false) 
                    {
                        if (next == -1 || numofLineChanges[k] < numofLineChanges[next])
                        {
                            next = k;
                        }
                    }
                }

                if (next == -1 || numofLineChanges[next] == Integer.MAX_VALUE) 
                {
                    break;
                }

                visited[next] = true;

                for (int n = 0; n < indexNum; n++) 
                {
                    if (visited[n] == false)
                    {
                        if (graph[next][n] != null)
                        {
                            // Go over all the different types of ways to go to the same location and choose the best route.
                            for (locationData bestWay : graph[next][n])
                            {
                                newTime = timeToSource[next] + bestWay.time;
                                int numChanges = numofLineChanges[next];                                
                                
                                if (tripLineColor[next] != null && !tripLineColor[next].equals(bestWay.color))
                                {
                                    numChanges++;
                                    newTime = newTime + 2;
                                }

                                if (numChanges < numofLineChanges[n]) // Check if the new path has fewer changes.
                                {
                                    numofLineChanges[n] = numChanges;
                                    timeToSource[n] = newTime;
                                    tripLineColor[n] = bestWay.color;
                                    previousLocation[n] = next;
                                }

                            }
                        }
                    }  
                }
            }
        }

        // for (int i = 0; i < indexNum; i++) 
        // {
        //     System.out.println(timeToSource[i]);
        // }
    }
}
