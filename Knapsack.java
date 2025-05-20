import java.util.Scanner; //Import scanner for user input

public class Knapsack {

    static int calls = 0; // Global counter to track the number of recursive calls

    // Recursive function to solve the 0/1 Knapsack problem
    public static int knapsackBF(int[] weights, int[] values, int capacity, int index) {
        calls++; // Increment the call counter every time the function is called

        // Base case: no more items left or knapsack is full
        if (index == weights.length || capacity == 0) {
            return 0;
        }

        // If the current item's weight exceeds the remaining capacity, skip it
        if (weights[index] > capacity) {
            return knapsackBF(weights, values, capacity, index + 1);
        } else {
            // Option 1: Include the current item
            int include = values[index] + knapsackBF(weights, values, capacity - weights[index], index + 1);

            // Option 2: Exclude the current item
            int exclude = knapsackBF(weights, values, capacity, index + 1);

            // Return the better value between including and excluding the item
            return Math.max(include, exclude);
        }
    }
    
    // Function to solve the 0/1 Knapsack problem using Dynamic Programming
    public static int knapsackDP(int[] weights, int[] values, int capacity) {
        int n = weights.length; // Number of items
        int[] dp = new int[capacity + 1]; // dp[w] will store max value for capacity w
        int iterations = 0; // To count the number of inner loop iterations

        // Loop through each item
        for (int i = 0; i < n; i++) {
            // Traverse capacities backwards to avoid overwriting needed values
            for (int w = capacity; w >= weights[i]; w--) {
                // Choose the maximum between not taking or taking the item
                dp[w] = Math.max(dp[w], dp[w - weights[i]] + values[i]);
                iterations++; // Count how many iterations occur (for analysis)
            }
        }

        // Print the number of passes/iterations of the DP loop
        System.out.println("Number of iterations (DP): " + iterations);

        // Return the maximum value that fits in the knapsack
        return dp[capacity];
    }
	
	public static int solveKnapsackDP(int[] weights, int[] values, int capacity){
		calls = 0;
		int output = knapsackDP(weights, values, capacity);
		return output;
	}

    public static void runKnapsack(int choice)
    {
        System.out.println("Please enter the capacity that the knapsack can contain: ");



        if(!(choice == 1 || choice ==2)){
            System.out.println("Invalid option. Try again."); 
            return;
        }

        Scanner scanner = new Scanner(System.in);
            int capacity = Integer.parseInt(scanner.nextLine());

            int [] weights = new int[capacity];
            int [] values = new int[capacity];

            System.out.println("Please enter the weights: ");
            for (int i = 0; i < capacity; i++)
            {
                weights[i] = scanner.nextInt();
            }

            System.out.println("Please enter the values: ");
            for (int i = 0; i < capacity; i++)
            {
                values[i] = scanner.nextInt();
            }

        long startTime = System.nanoTime(); // start timer
        
        if(choice == 1)
        {
            // Call the recursive brute force knapsack function starting at index 0
            int maxValue = knapsackBF(weights, values, capacity, 0);
            
            // Print the result and the total number of recursive calls made
            System.out.println("Number of recursive calls (Brute Force): " + calls);
            System.out.println("Maximum value in knapsack (Brute Force): " + maxValue);  
        } 
        else if(choice == 2)
        {
            int maxValue = knapsackDP(weights, values, capacity);

            // Print the final result (maximum value achievable)
            System.out.println("Maximum value in knapsack (DP): " + maxValue);    
        }

        long endTime = System.nanoTime(); // end timer
        long duration = endTime - startTime; // calculate elapsed time
        System.out.println("Execution time: " + duration + " nanoseconds");
        System.out.println("Execution time: " + (duration / 1_000_000.0) + " milliseconds");
    }
}
