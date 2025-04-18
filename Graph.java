    import java.util.*;  

    public class Graph extends FileRead
    {
        locationData[][] graph;

        
        public void graph()
        {
            graph = new locationData[locationIndexes.size()][locationIndexes.size()];

            for (int i = 0; i < startingLocations.size(); i++)
            {
                int startingLocationIndex = locationIndexes.get(startingLocations.get(i)); 
                int endingLocationIndex = locationIndexes.get(endingLocations.get(i)); 
                int timeTaken = Integer.parseInt(time.get(i));
                String lineColor = lineType.get(i);

                graph[startingLocationIndex][endingLocationIndex].time = timeTaken;
                graph[startingLocationIndex][endingLocationIndex].color = lineColor;
                graph[endingLocationIndex][startingLocationIndex].time = timeTaken;
                graph[endingLocationIndex][startingLocationIndex].color = lineColor;
            }
            
        }
    }