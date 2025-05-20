public class SelectionSort{

    private static int[] arr = Utility.randomizeArray("assets\\random_numbers.txt");
	static void selectionSortUnoptimized(int array[]) {
		int size = array.length; //get the size of the array

        //outer loop controls the boundary between sorted and unsorted parts
		for(int i=0; i<size-1; i++) {
            //assume the current index is the minIndex
		    int minIndex = i;
		
            //inner loop searches for the actual smallest element in the unsorted part
            //this is the brute force part: it checks every remaining element
            for(int j = i + 1; j < size; j++) {
                if(array[j] < array[minIndex]) {
                    minIndex = j; //update the smallest element found
                }
            }
		
            //swap the smallest found element with the first unsorted element
            int temp = array[minIndex];
            array[minIndex] = array[i];
            array[i] = temp;
        }
	}
    static void selectionSortOptimized(int array[]) {
        int size = array.length;
        for (int i = 0, j = size - 1; i < j; i++, j--)
        {
            int min = arr[i];
            int max = arr[i];
            
            int id_min = i;
            int id_max = i;
            
            for (int k = i + 1; k <= j; k++)
            {
                if (arr[k] > max)
                {
                    max = arr[k];
                    id_max = k;
                }
                else if (arr[k] < min)
                {
                    min = arr[k];
                    id_min = k;
                }
            }

             if (id_min != i) {
            Utility.swap(arr, i, id_min);
            // if the max was sitting at i, its index just moved to idMin
            if (id_max == i) {
                id_max = id_min;
            }
        }

            // 2) swap the max into position j, but only if needed
            if (id_max != j) {
                Utility.swap(arr, j, id_max);
            }
        }
    }

	
	public static void runSelectionSort(int choice) {
        //example array
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


