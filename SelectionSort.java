public class SelectionSort{

	static void selectionSortUnoptimized(int arr[]) {
		int size = arr.length; //get the size of the array

        //outer loop controls the boundary between sorted and unsorted parts
		for(int i=0; i<size-1; i++) {
            //assume the current index is the minIndex
		    int minIndex = i;
		
            //inner loop searches for the actual smallest element in the unsorted part
            //this is the brute force part: it checks every remaining element
            for(int j = i + 1; j < size; j++) {
                if(arr[j] < arr[minIndex]) {
                    minIndex = j; //update the smallest element found
                }
            }
		
            //swap the smallest found element with the first unsorted element
            int temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;
        }
	}
    static void selectionSortOptimized(int arr[]) {
        int size = arr.length;
        
        for (int i = 0, j = size - 1; i < j; i++, j--)
        {
            // Assume that the minimum and maximum are at the current index
            int min = arr[i];
            int max = arr[i];
            
            int id_min = i;
            int id_max = i;
            
            // Inner loop : search the unsorted subarray for min and max
            for (int k = i + 1; k <= j; k++)
            {
                // Update max
                if (arr[k] > max)
                {
                    max = arr[k];
                    id_max = k;
                }

                // ... or min
                else if (arr[k] < min)
                {
                    min = arr[k];
                    id_min = k;
                }
            }
        
            // Swap the minimum element with the element at index i 
            // only if they are not 
            // already in the correct place
            if (id_min != i) {
            Utility.swap(arr, i, id_min);

            // if the max was sitting at i, its index just moved to id_min
            if (id_max == i) {
                id_max = id_min;
            }
        }

            // swap the max into position j, but only if needed
            if (id_max != j) {
                Utility.swap(arr, j, id_max); 
            }
        }
    }

	
	public static void runSelectionSort(int choice) {
        //example array
        int[] arr = Utility.randomizeArray("assets\\random_numbers.txt");
        if(!(choice != 1 || choice != 2)){
            System.out.println("Invalid option. Try again."); 
            return;
        }

        //create object and call the sort method
        if(choice == 1)
        {
            long startTime = System.nanoTime(); // start timer
            selectionSortUnoptimized(arr);  
            long endTime = System.nanoTime(); // end timer
            long duration = endTime - startTime; // calculate elapsed time
            System.out.println("Execution time: " + duration + " nanoseconds");
            System.out.println("Execution time: " + (duration / 1_000_000.0) + " milliseconds");
            
        } 
        else if (choice == 2)
        {
            long startTime = System.nanoTime(); // start timer
            selectionSortOptimized(arr);
            long endTime = System.nanoTime(); // end timer
            long duration = endTime - startTime; // calculate elapsed time
            System.out.println("Execution time: " + duration + " nanoseconds");
            System.out.println("Execution time: " + (duration / 1_000_000.0) + " milliseconds");            
        }

        System.out.println("Final sorted array:");
        Utility.printArray(arr);
	}
}


