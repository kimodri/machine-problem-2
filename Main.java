public class Main {
    public static void main(String[] args) {
        // First matrix
        float[][] matrix = {
            {0, 10, 15, 20},
            {10, 0, 35, 25},
            {15, 35, 0, 30},
            {20, 25, 30, 0}
        };

        // Call TSPFunctions.main(), which solves both unoptimized and optimized for hardcoded matrix
        String[] emptyArgs = {};
        TSP.main(emptyArgs);

        System.out.println("\n\nNow solving with a DIFFERENT matrix:\n");

        // Slightly changed matrix (to test isolation of state)
        float[][] matrix2 = {
            {0, 12, 14, 18, 25, 19, 22},
            {12, 0, 33, 27, 20, 23, 29},
            {14, 33, 0, 24, 30, 22, 18},
            {18, 27, 24, 0, 15, 29, 21},
            {25, 20, 30, 15, 0, 28, 24},
            {19, 23, 22, 29, 28, 0, 31},
            {22, 29, 18, 21, 24, 31, 0}
        };
		
		
		
		
        long startTime = System.nanoTime();
        TSP.solveUnoptimized(matrix2);
        long endTime = System.nanoTime();
        System.out.println("Unoptimized runtime (ms): " + (endTime - startTime) / 1_000_000.0);


		float[][] matrix3 = {
            {0, 8, 12, 19, 21, 18, 20},
            {8, 0, 28, 24, 17, 25, 27},
            {12, 28, 0, 21, 22, 20, 19},
            {19, 24, 21, 0, 16, 26, 23},
            {21, 17, 22, 16, 0, 27, 25},
            {18, 25, 20, 26, 27, 0, 30},
            {20, 27, 19, 23, 25, 30, 0}
        };

		
        startTime = System.nanoTime();
        TSP.solveOptimized(matrix3);
        endTime = System.nanoTime();
        System.out.println("Optimized runtime (ms): " + (endTime - startTime) / 1_000_000.0);
    }
}
