import java.util.*;

public class CSCANDiskSchedulingAlgorithm {
    static int disk_size = 200;

    public static void CSCAN(int arr[], int head) {
        int size = arr.length;
        int seek_count = 0;
        int distance, cur_track;

        // Create vectors to store the tracks on the left and right of the head
        Vector<Integer> left = new Vector<Integer>();
        Vector<Integer> right = new Vector<Integer>();
        // Vector to store the sequence in which tracks are accessed
        Vector<Integer> seek_sequence = new Vector<Integer>();

        // Add the boundary tracks to the left and right vectors
        left.add(0);
        right.add(disk_size - 1);

        // Separate the tracks into left and right based on their position relative to
        // the head
        for (int i = 0; i < size; i++) {
            if (arr[i] < head)
                left.add(arr[i]);
            if (arr[i] > head)
                right.add(arr[i]);
        }

        // Sort the left and right vectors
        Collections.sort(left);
        Collections.sort(right);

        // Process the tracks on the right side of the head
        for (int i = 0; i < right.size(); i++) {
            cur_track = right.get(i);
            seek_sequence.add(cur_track);
            distance = Math.abs(cur_track - head);
            seek_count += distance;
            head = cur_track;
        }

        // Reset the head position to the beginning of the disk
        head = 0;

        // Add the seek count for reaching the rightmost track and coming back to the
        // leftmost track
        seek_count += (disk_size - 1);

        // Process the tracks on the left side of the head
        for (int i = 0; i < left.size(); i++) {
            cur_track = left.get(i);
            seek_sequence.add(cur_track);
            distance = Math.abs(cur_track - head);
            seek_count += distance;
            head = cur_track;
        }

        // Print the request sequence and initial head position
        System.out.print("Request Sequence: ");
        for (int i = 0; i < size; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println("\nInitial Head Position: " + head);

        // Print the seek sequence table
        System.out.println("\n----- Seek Sequence -----");
        System.out.println("-------------------------------------------------");
        System.out.println("| Track Accessed | Seek Count  | Running Total  |");
        System.out.println("-------------------------------------------------");
        int running_total = 0;

        for (int i = 0; i < seek_sequence.size(); i++) {
            int track = seek_sequence.get(i);
            distance = Math.abs(track - running_total);
            running_total = track;
            System.out.printf("|%15d |%12d |%15d |\n", track, distance, seek_count);
        }
        System.out.println("-------------------------------------------------");
        System.out.println("\nTotal number of seek operations = " + seek_count + "\n");
    }

    public static void main(String[] args) {
        System.out.println("\n----- Disk Scheduling Algorithm with CSCAN -----");

        Scanner scanner = new Scanner(System.in);

        // Read the number of requests from the user
        System.out.println("Enter the number of requests:");
        while (!scanner.hasNextInt()) {
            System.out.print("Invalid input. Please enter an integer value for the size.");
            scanner.next();
        }
        int size = scanner.nextInt();
        int[] arr = new int[size];

        // Read the request array from the user
        System.out.println("Enter the values for the request array:");
        for (int i = 0; i < size; i++) {
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter an integer value for the sequence values.");
                scanner.next();
            }
            arr[i] = scanner.nextInt();
        }

        // Read the head position from the user
        System.out.println("Enter the value for the head:");
        while (!scanner.hasNextInt()) {
            System.out.print("Invalid input. Please enter an integer value for the head position.");
            scanner.next();
        }
        int head = scanner.nextInt();

        // Perform the disk scheduling with CSCAN algorithm
        System.out.println("\n----- Disk Scheduling Process with CSCAN -----");
        CSCAN(arr, head);

        scanner.close();
    }
}
