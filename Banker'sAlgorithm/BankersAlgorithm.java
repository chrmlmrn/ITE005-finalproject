import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class BankersAlgorithm {

    public static void main(String[] args) {
        System.out.println("\n----- Banker's Algorithm -----");

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of processes: ");
        int numOfProcesses = scanner.nextInt();

        System.out.print("Enter the number of resources: ");
        int numOfResources = scanner.nextInt();

        int[][] max = new int[numOfProcesses][numOfResources];
        int[][] allocation = new int[numOfProcesses][numOfResources];
        int[] available = new int[numOfResources];
        int[][] need = new int[numOfProcesses][numOfResources];

        // Generate random values for max and allocation matrices
        Random random = new Random();
        for (int i = 0; i < numOfProcesses; i++) {
            for (int j = 0; j < numOfResources; j++) {
                int maxValue = Math.min(10, numOfResources); // Set a maximum value of 10 or the number of resources
                max[i][j] = random.nextInt(maxValue) + 1; // Range: 1-10 or 1 to the number of resources
                allocation[i][j] = random.nextInt(max[i][j] + 1);
                need[i][j] = max[i][j] - allocation[i][j];
            }
        }

        // Input available resources
        System.out.println("Enter the available resources:");
        for (int i = 0; i < numOfResources; i++) {
            System.out.print("Resource " + i + ": ");
            available[i] = scanner.nextInt();
        }

        System.out.println("\n----- Banker's Algorithm Process -----");
        System.out.print("Available Resources: ");
        for (int i = 0; i < numOfResources; i++) {
            System.out.print(available[i] + " ");
        }
        System.out.println();

        // Display initial matrices
        System.out.println("\nMax:");
        displayMatrix(max);
        System.out.println("\nAllocation:");
        displayMatrix(allocation);
        System.out.println("\nAvailable:");
        displayArray(available);
        System.out.println("\nNeed:");
        displayMatrix(need);

        // Banker's Algorithm
        boolean[] isSafe = new boolean[numOfProcesses];
        Arrays.fill(isSafe, false);
        int[] work = Arrays.copyOf(available, numOfResources);
        boolean[] isAllocated = new boolean[numOfProcesses];

        int safeCount = 0;
        while (safeCount < numOfProcesses) {
            boolean canAllocate = false;
            for (int i = 0; i < numOfProcesses; i++) {
                if (!isAllocated[i] && checkNeedLessOrEqual(need[i], work)) {
                    work = addArrays(work, allocation[i]);
                    isAllocated[i] = true;
                    isSafe[i] = true;
                    safeCount++;
                    canAllocate = true;
                }
            }

            if (!canAllocate) {
                break;
            }
        }

        // Display the final results
        System.out.println("\nSafety Sequence:");
        for (int i = 0; i < numOfProcesses; i++) {
            if (isSafe[i]) {
                System.out.print("P" + i);
                if (i != numOfProcesses - 1) {
                    System.out.print(" -> ");
                }
            }
        }

        if (safeCount == numOfProcesses) {
            System.out.println("\n\nThe system is in a safe state." + "\n");
        } else {
            System.out.println("\n\nThe system is not in a safe state." + "\n");
        }

        scanner.close();
    }

    public static void displayMatrix(int[][] matrix) {
        int numRows = matrix.length;
        int numCols = matrix[0].length;

        System.out.println("+-------------------------+");
        System.out.print("|     ");
        for (int j = 0; j < numCols; j++) {
            System.out.print("| R" + j + " ");
        }
        System.out.println("|");
        System.out.println("+-------------------------+");

        for (int i = 0; i < numRows; i++) {
            System.out.print("| P" + i + "  ");
            for (int j = 0; j < numCols; j++) {
                System.out.printf("| %2d ", matrix[i][j]);
            }
            System.out.println("|");
        }

        System.out.println("+-------------------------+");
    }

    public static void displayArray(int[] array) {
        int length = array.length;
        System.out.println("+-------------------------+");
        System.out.print("|     ");
        for (int i = 0; i < length; i++) {
            System.out.print("| R" + i + " ");
        }
        System.out.println("|");
        System.out.print("|     ");
        for (int i = 0; i < length; i++) {
            System.out.printf("| %2d ", array[i]);
        }
        System.out.println("|");
        System.out.println("+-------------------------+");
    }

    public static boolean checkNeedLessOrEqual(int[] need, int[] work) {
        for (int i = 0; i < need.length; i++) {
            if (need[i] > work[i]) {
                return false;
            }
        }
        return true;
    }

    public static int[] addArrays(int[] arr1, int[] arr2) {
        int[] result = new int[arr1.length];
        for (int i = 0; i < arr1.length; i++) {
            result[i] = arr1[i] + arr2[i];
        }
        return result;
    }
}