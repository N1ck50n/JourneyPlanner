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

                if (next == -1 || timeToSource[next] == Double.MAX_VALUE) //if time is infinite, break from the loop.
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
                            double newTime = timeToSource[next] + graph[next][n].time;
                            if (newTime < timeToSource[n]) 
                            {
                                timeToSource[n] = newTime;
                                tripLineColor[n] = graph[next][n].color;
                                previousLocation[n] = next;
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
                            int numChanges;
                            
                            // If the next trips color is different the current trip add 1.
                            if (tripLineColor[next] != null && !tripLineColor[next].equals(graph[next][n].color))
                            {
                                numChanges = numofLineChanges[next] + 1;
                            }
                            else
                            {
                                numChanges = numofLineChanges[next];
                            }
                            
                            if (numChanges < numofLineChanges[n]) // Check if new path would have lower changes.
                            {
                                numofLineChanges[n] = numChanges;
                                timeToSource[n] = timeToSource[next] + graph[next][n].time;
                                tripLineColor[n] = graph[next][n].color;
                                previousLocation[n] = next;
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
