public class TSP {
    static float bestTourDst;
    static int[] bestTourIndices;
    static float[][] costMatrix;
    static int[] indices;

    // Counters for the number of evaluations
    static long unoptimizedCount;
    static long optimizedCount;

    // Common swap helper
    static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    // Generates permutations using Heap's Algorithm
    static void generateSolutionsUnoptimized(int[] array, int n) {
        if (n == 0) {
            evaluateSolutionUnoptimized(array);
        } else {
            for (int i = 0; i <= n; i++) {
                generateSolutionsUnoptimized(array, n - 1);
                int j = (n % 2 == 0) ? i : 0;
                swap(array, j, n);
            }
        }
    }

    static void generateSolutionsOptimized(int[] array, int n) {
        if (n == 0) {
            evaluateSolutionOptimized(array);
        } else {
            for (int i = 0; i <= n; i++) {
                generateSolutionsOptimized(array, n - 1);
                int j = (n % 2 == 0) ? i : 0;
                swap(array, j, n);
            }
        }
    }

    // Unoptimized: evaluates all permutations
    static void evaluateSolutionUnoptimized(int[] array) {
        unoptimizedCount++;  // Increment counter

        float tourDst = 0;
        for (int i = 0; i < array.length; i++) {
            int nextIndex = (i + 1) % array.length;
            tourDst += costMatrix[array[i]][array[nextIndex]];
        }

        if (tourDst < bestTourDst) {
            bestTourDst = tourDst;
            System.arraycopy(array, 0, bestTourIndices, 0, array.length);

            System.out.print("[Unoptimized] New best tour found: ");
            for (int v : array) System.out.print(v + " ");
            System.out.println(" with distance " + tourDst);
        }
    }

    // Optimized: skips mirrored routes
    static void evaluateSolutionOptimized(int[] array) {
        if (array[0] > array[array.length - 2]) return;  // skip mirrored routes

        optimizedCount++;  // Increment counter

        float tourDst = 0;
        for (int i = 0; i < array.length; i++) {
            int nextIndex = (i + 1) % array.length;
            // FIX: Correctly index costMatrix with array[nextIndex]
            tourDst += costMatrix[array[i]][array[nextIndex]];
        }

        if (tourDst < bestTourDst) {
            bestTourDst = tourDst;
            System.arraycopy(array, 0, bestTourIndices, 0, array.length);

            System.out.print("[Optimized] New best tour found: ");
            for (int v : array) System.out.print(v + " ");
            System.out.println(" with distance " + tourDst);
        }
    }

    // Wrapper to run unoptimized TSP
    public static void solveUnoptimized(float[][] matrix) {
        costMatrix = matrix;
        int size = matrix.length;
        indices = new int[size];
        bestTourIndices = new int[size];
        bestTourDst = Float.MAX_VALUE;
        unoptimizedCount = 0;  // Reset counter

        for (int i = 0; i < size; i++) indices[i] = i;

        generateSolutionsUnoptimized(indices, size - 1);

        System.out.println("Unoptimized best distance: " + bestTourDst);
        System.out.print("Unoptimized best tour: ");
        for (int i : bestTourIndices) System.out.print(i + " ");
        System.out.println();
        System.out.println("Unoptimized evaluations: " + unoptimizedCount);
    }

    // Wrapper to run optimized TSP
    public static void solveOptimized(float[][] matrix) {
        costMatrix = matrix;
        int size = matrix.length;
        indices = new int[size];
        bestTourIndices = new int[size];
        bestTourDst = Float.MAX_VALUE;
        optimizedCount = 0;  // Reset counter

        for (int i = 0; i < size; i++) indices[i] = i;

        generateSolutionsOptimized(indices, size - 1);

        System.out.println("Optimized best distance: " + bestTourDst);
        System.out.print("Optimized best tour: ");
        for (int i : bestTourIndices) System.out.print(i + " ");
        System.out.println();
        System.out.println("Optimized evaluations: " + optimizedCount);
    }

    // Test example
    public static void main(String[] args) {
        float[][] matrix = {
            {0, 10, 15, 20, 22, 17, 19},
            {10, 0, 35, 25, 18, 24, 30},
            {15, 35, 0, 30, 28, 21, 16},
            {20, 25, 30, 0, 14, 27, 23},
            {22, 18, 28, 14, 0, 26, 20},
            {17, 24, 21, 27, 26, 0, 32},
            {19, 30, 16, 23, 20, 32, 0}
        };

        long startTime = System.nanoTime();
        solveUnoptimized(matrix);
        long endTime = System.nanoTime();
        System.out.println("Unoptimized runtime (ms): " + (endTime - startTime) / 1_000_000.0);

        startTime = System.nanoTime();
        solveOptimized(matrix);
        endTime = System.nanoTime();
        System.out.println("Optimized runtime (ms): " + (endTime - startTime) / 1_000_000.0);
    }
}
