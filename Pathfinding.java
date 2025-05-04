import java.util.LinkedList;

public class Pathfinding extends Input 
{
    boolean[] visited;
    LinkedList<Integer> tripData;
    Double[] timeToSource;
    int[] previousLocation;
    String[] tripLineColor;
    int startingIndex = 0;
    int endingIndex = 0;

    public Pathfinding() 
    {
        super();
        visited = new boolean[indexNum];
        tripData = new LinkedList<>();
        timeToSource = new Double[indexNum];
        previousLocation = new int[indexNum];
        tripLineColor = new String[indexNum];

        startingIndex = locationIndexes.get(startLocation);
        endingIndex = locationIndexes.get(endLocation);

        for (int i = 0; i < indexNum; i++) 
        {
            visited[i] = false;
            timeToSource[i] = Double.MAX_VALUE;
            previousLocation[i] = -1;
        }

        timeToSource[startingIndex] = 0.0;

        for (int j = 0; j < indexNum; j++) 
        {
            int next = -1;
            for (int k = 0; k < indexNum; k++) 
            {
                if (visited[k] == false)
                {
                    if ((next == -1 || timeToSource[k] < timeToSource[next]))
                    {
                        next = k;
                    }  
                }
            }

            if (next == -1 || timeToSource[next] == Double.MAX_VALUE) 
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
                        double newTime = timeToSource[next] + graph[next][n].time;
                        if (newTime < timeToSource[n]) 
                        {
                            timeToSource[n] = newTime;
                            previousLocation[n] = next;
                            tripLineColor[n] = graph[next][n].color;
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
