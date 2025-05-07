import java.util.Scanner;

public class Input extends FileRead
{
    boolean firstLoc = false;
    boolean secondLoc = false;
    String startLocation;
    String endLocation;
    public static int pathfindingType = 0;

    public Input() 
    {
        super();
        
        Scanner scanner = new Scanner(System.in);

        while (firstLoc == false && secondLoc == false)
        {
            System.out.print("Enter the starting location: ");
            startLocation = scanner.nextLine();

            System.out.print("Enter the ending location: ");
            endLocation = scanner.nextLine();

            
            // System.out.println(startLocation);
            // System.out.println(endLocation);

            if (startingLocations.contains(startLocation)) //Checks if userInput matches any locations from the file.
            {
                firstLoc = true;
            }
            if (endingLocations.contains(endLocation)) //Checks if userInput matches any locations from the file.
            {
                secondLoc = true;   
            }
            if (firstLoc && secondLoc)
            {
                System.out.println("Valid.\n");
                System.out.println("Would you like to choose the shortest path or the path with the fewest changes?");
                System.out.println("(Type 1 for shortest path and 2 for fewest changes) ");
                pathfindingType = Integer.parseInt(scanner.nextLine());

                break;
            }
            if (firstLoc == false || secondLoc == false)
            {
                System.out.println("Invalid input.\n");
            }
        }
    
        scanner.close();
        
    }
    
}
