import java.util.*;

public class LOOKDiskSchedulingAlgorithm {
    static int disk_size = 200;

    public static void LOOK(int arr[], int head, String direction) {
        int size = arr.length;
        int seek_count = 0;
        int distance, cur_track;
        Vector<Integer> left = new Vector<Integer>();
        Vector<Integer> right = new Vector<Integer>();
        Vector<Integer> seek_sequence = new Vector<Integer>();

        for (int i = 0; i < size; i++) {
            if (arr[i] < head)
                left.add(arr[i]);
            if (arr[i] > head)
                right.add(arr[i]);
        }

        Collections.sort(left);
        Collections.sort(right);

        int run = 2;
        while (run-- > 0) {
            if (direction.equals("left")) {
                // Process the tracks in the left direction
                for (int i = left.size() - 1; i >= 0; i--) {
                    cur_track = left.get(i);
                    seek_sequence.add(cur_track);
                    distance = Math.abs(cur_track - head);
                    seek_count += distance;
                    head = cur_track;
                }
                direction = "right"; // Change direction after processing left tracks
            } else if (direction.equals("right")) {
                // Process the tracks in the right direction
                for (int i = 0; i < right.size(); i++) {
                    cur_track = right.get(i);
                    seek_sequence.add(cur_track);
                    distance = Math.abs(cur_track - head);
                    seek_count += distance;
                    head = cur_track;
                }
                direction = "left"; // Change direction after processing right tracks
            }
        }

        // Print the original request sequence and initial head position
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

        int runningTotal = 0;
        for (int i = 0; i < seek_sequence.size(); i++) {
            int track = seek_sequence.get(i);
            distance = Math.abs(track - head);
            runningTotal += distance;
            System.out.printf("|%15d |%12d |%15d |\n", track, distance, runningTotal);
            head = track;
        }

        System.out.println("-------------------------------------------------");
        System.out.println("\nTotal number of seek operations = " + seek_count + "\n");
    }

    public static void main(String[] args) {
        System.out.println("\n----- Disk Scheduling Algorithm with LOOK -----");
        Scanner scanner = new Scanner(System.in);

        // Read the number of requests from the user
        System.out.println("Enter the number of requests:");
        while (!scanner.hasNextInt()) {
            System.out.print("Invalid input. Please enter an integer value for the size.");
            scanner.next();
        }
        int size = scanner.nextInt();
        int[] arr = new int[size];

        // Read the values for the request array from the user
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

        scanner.nextLine(); // Consume the newline character

        // Read the direction (left or right) from the user
        System.out.println("Enter the direction (left or right):");
        String direction = scanner.nextLine();
        while (!direction.equals("left") && !direction.equals("right")) {
            System.out.println("Invalid input. Please enter either 'left' or 'right' for the direction.");
            direction = scanner.nextLine();
        }

        // Perform the disk scheduling process with LOOK
        System.out.println("\n----- Disk Scheduling Process with LOOK -----");
        LOOK(arr, head, direction);

        scanner.close();
    }
}
