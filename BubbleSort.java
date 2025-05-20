public class BubbleSort {

    private static int[] arr = Utility.randomizeArray("assets\\random_numbers.txt");

    public static void bubbleSort(int[] arr) {
        int size = arr.length, i;

        for (i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - i - 1; j++) {
                Utility.swap(arr, j, j+1);
            }
        }

        System.out.println("Number of Passes : " + i);
    }

    public static void optimizedBubbleSort(int[] arr) {
        int size = arr.length;
        int i = size - 1;
        int pass = 1;

        while (i > 0) {
            boolean swapped = false;
            int lastIndex = 0;

            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j + 1]) {
                    
                    // Swap elements
                    Utility.swap(arr, j, j + 1);
                    swapped = true;
                    lastIndex = j;
                }
            }

            if (!swapped) {
                System.out.println("No swaps performed. The array is sorted.");
                break;
            }

            i = lastIndex;
            pass++;
        }

        System.out.println("Number of Passes : " + (pass-1));
    }

    public static void runBubbleSort(int choice) {
        if(!(choice == 1 || choice ==2)){
            System.out.println("Invalid option. Try again."); 
            return;
        }
        
        System.out.println("Original array:");
        Utility.printArray(arr);

        if(choice == 1)
        {
            long startTime = System.nanoTime(); // start timer
            bubbleSort(arr);
            long endTime = System.nanoTime(); // end timer
            long duration = endTime - startTime; // calculate elapsed time
            System.out.println("Execution time: " + duration + " nanoseconds");
            System.out.println("Execution time: " + (duration / 1_000_000.0) + " milliseconds");
        } 
        else if(choice == 2)
        {
            long startTime = System.nanoTime(); // start timer
            optimizedBubbleSort(arr);
            long endTime = System.nanoTime(); // end timer
            long duration = endTime - startTime; // calculate elapsed time
            System.out.println("Execution time: " + duration + " nanoseconds");
            System.out.println("Execution time: " + (duration / 1_000_000.0) + " milliseconds");    
        }

        // Print the sorted array
        System.out.println("Final sorted array:");
        Utility.printArray(arr);
    }
}
