import java.util.Scanner; //Import scanner for user input

public class SequentialSearch {
    private static int count;//Variable for counting.
    private static int[] arr = Utility.randomizeArray("assets\\unique_random_numbers.txt");
    private static int size = arr.length;


    //Method to call to search
    public static int searchTraditional(int arr[], int search, int size){
        count = 0;
        for(int i = 0; i < size; i++) {//Iterate all elements in the array using loops.
            count++;
            if(arr[i] == search) {//Checks if the element is equal to the target.
                return i; //Returns the index if element is found
            }
        }
        return -1;//Return -1 if the target element is not in the array.
    }

    //Method to call to improved search algorithm
    public static int searchOptimized(int arr[], int search, int size){
        count = 0;
        int first = 0, last = arr.length - 1; //Initialized variable of both ends for iterations
        while(first <= last){ //Loop will stop until it reaches the middle of the array.
            count++;//Increments to know how many iterations needed to find the target.
            if(search == arr[first] || search == arr[last]){//Checks if search targets matches the first or last index.
                if(search == arr[first]){   
                    return first; //Return first variable if found at this index
                }else{
                    return last; //Return last variable if found at this index
                }
            }
            first++; //Moves the first to the next element for the next iteration
            last--; //Moves the last to the previous element for the next iteration
        }
        return -1; //If not found returns -1
    }

    public static void runSequentialSearch(int choice) {
        Scanner scanner = new Scanner(System.in);

        //If invalid choice
        if(!(choice == 1 || choice ==2)){
            System.out.println("Invalid option. Try again."); 
            return;
        }

        System.out.print("Enter number to search (0 - 9999): ");
        int searchItem = Integer.parseInt(scanner.nextLine());

        int searchIndex;
        if(choice == 1){
            long startTime = System.nanoTime(); // start timer
            searchIndex = searchTraditional(arr, searchItem, size);
            long endTime = System.nanoTime(); // end timer
            long duration = endTime - startTime; // calculate elapsed time
            System.out.println("Execution time: " + duration + " nanoseconds");
            System.out.println("Execution time: " + (duration / 1_000_000.0) + " milliseconds");
            
        }else{
            long startTime = System.nanoTime(); // start timer
            searchIndex = searchOptimized(arr, searchItem, size);
            long endTime = System.nanoTime(); // end timer
            long duration = endTime - startTime; // calculate elapsed time
            System.out.println("Execution time: " + duration + " nanoseconds");
            System.out.println("Execution time: " + (duration / 1_000_000.0) + " milliseconds");
            
        }

        if(searchIndex != -1){//Outputs the target/search index if found
            System.out.println(searchItem + " is found at index " + searchIndex);
            System.out.println("The element is found after " + count + " iteration/s"); //Outputs iterations needed to search.
        }else{
            System.out.println(searchItem + " is not found");
        }
    }
}
