import java.util.Scanner;

class FCFSDiskSchedulingAlgorithm {
    // First-Come-First-Serve (FCFS) Disk Scheduling Algorithm
    static void FCFS(int arr[], int head) {
        int size = arr.length;
        int seek_count = 0;
        int distance, cur_track;

        // Display the request sequence
        System.out.print("Request Sequence: ");
        for (int i = 0; i < size; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println("\nInitial Head Position: " + head);

        System.out.println("\n----- Seek Sequence -----");
        System.out.println("-------------------------------------------------");
        System.out.println("| Track Accessed | Seek Count  | Running Total  |");
        System.out.println("-------------------------------------------------");

        // Iterate through the request sequence
        for (int i = 0; i < size; i++) {
            cur_track = arr[i];

            // Calculate the absolute distance between current track and head position
            distance = Math.abs(cur_track - head);

            // Increase the total seek count
            seek_count += distance;

            // Update the head position to the current track
            head = cur_track;

            // Display the track accessed, seek distance, and running total seek count
            System.out.printf("|%15d |%12d |%15d |\n", cur_track, distance, seek_count);
        }

        System.out.println("-------------------------------------------------");
        System.out.println("\nTotal number of seek operations = " + seek_count + "\n");
    }

    public static void main(String[] args) {
        System.out.println("\n----- Disk Scheduling Algorithm with First-Come-First-Serve (FCFS) -----");
        Scanner scanner = new Scanner(System.in);

        // Read the size of the request sequence from the user
        System.out.println("Enter the number of requests:");
        while (!scanner.hasNextInt()) {
            System.out.print("Invalid input. Please enter an integer value for the size.");
            scanner.next();
        }
        int size = scanner.nextInt();
        int[] arr = new int[size];

        // Read the values for the request sequence from the user
        System.out.println("Enter the values for the request array:");
        for (int i = 0; i < size; i++) {
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter an integer value for the sequence values.");
                scanner.next();
            }
            arr[i] = scanner.nextInt();
        }

        // Read the initial head position from the user
        System.out.println("Enter the value for the head:");
        while (!scanner.hasNextInt()) {
            System.out.print("Invalid input. Please enter an integer value for the head position.");
            scanner.next();
        }
        int head = scanner.nextInt();

        System.out.println("\n----- Disk Scheduling Process with First-Come-First-Serve (FCFS) -----");
        // Call the FCFS algorithm with the provided request sequence and head position
        FCFS(arr, head);

        scanner.close();
    }
}
