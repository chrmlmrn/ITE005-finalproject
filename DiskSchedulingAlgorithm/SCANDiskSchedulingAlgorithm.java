import java.util.*;

public class SCANDiskSchedulingAlgorithm {
    static int disk_size = 200;

    static void SCAN(int arr[], int head, String direction) {
        int size = arr.length;
        int seek_count = 0; // Total seek count
        int distance, cur_track;
        Vector<Integer> left = new Vector<Integer>(); // Tracks to the left of the head
        Vector<Integer> right = new Vector<Integer>(); // Tracks to the right of the head
        Vector<Integer> seek_sequence = new Vector<Integer>(); // Sequence of track accesses

        // Determine the initial direction and add the appropriate boundary track
        if (direction.equals("left"))
            left.add(0);
        else if (direction.equals("right"))
            right.add(disk_size - 1);

        // Split the tracks into left and right based on their position relative to the
        // head
        for (int i = 0; i < size; i++) {
            if (arr[i] < head)
                left.add(arr[i]);
            if (arr[i] > head)
                right.add(arr[i]);
        }

        Collections.sort(left); // Sort the left tracks in ascending order
        Collections.sort(right); // Sort the right tracks in ascending order

        int run = 2; // Perform two iterations: one in each direction
        while (run-- > 0) {
            if (direction.equals("left")) {
                // Visit the tracks in the left vector in reverse order
                for (int i = left.size() - 1; i >= 0; i--) {
                    cur_track = left.get(i);
                    seek_sequence.add(cur_track);
                    distance = Math.abs(cur_track - head);
                    seek_count += distance;
                    head = cur_track;
                }
                direction = "right"; // Change direction to right
            } else if (direction.equals("right")) {
                // Visit the tracks in the right vector in ascending order
                for (int i = 0; i < right.size(); i++) {
                    cur_track = right.get(i);
                    seek_sequence.add(cur_track);
                    distance = Math.abs(cur_track - head);
                    seek_count += distance;
                    head = cur_track;
                }
                direction = "left"; // Change direction to left
            }
        }

        // Display the request sequence
        System.out.print("Request Sequence: ");
        for (int i = 0; i < size; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println("\nInitial Head Position: " + head);

        int running_total = 0;
        System.out.println("\n----- Seek Sequence -----");
        System.out.println("-------------------------------------------------");
        System.out.println("| Track Accessed | Seek Count  | Running Total  |");
        System.out.println("-------------------------------------------------");

        // Display the track accesses, seek count, and running total
        for (int i = 0; i < seek_sequence.size(); i++) {
            cur_track = seek_sequence.get(i);
            distance = Math.abs(cur_track - head);
            seek_count += distance;
            running_total += seek_count;
            System.out.printf("|%15d |%12d |%15d |\n", cur_track, distance, running_total);
            head = cur_track;
        }

        System.out.println("-------------------------------------------------");
        System.out.println("\nTotal number of seek operations = " + seek_count + "\n");
    }

    public static void main(String[] args) {
        System.out.println("\n----- Disk Scheduling Algorithm with SCAN -----");
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of requests:");
        while (!scanner.hasNextInt()) {
            System.out.print("Invalid input. Please enter an integer value for the size.");
            scanner.next();
        }
        int size = scanner.nextInt();
        int[] arr = new int[size];

        System.out.println("Enter the values for the request array:");
        for (int i = 0; i < size; i++) {
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter an integer value for the sequence values.");
                scanner.next();
            }
            arr[i] = scanner.nextInt();
        }

        System.out.println("Enter the value for the head:");
        while (!scanner.hasNextInt()) {
            System.out.print("Invalid input. Please enter an integer value for the head position.");
            scanner.next();
        }
        int head = scanner.nextInt();

        scanner.nextLine(); // Consume the newline character

        System.out.println("Enter the direction (left/right):");
        String direction = scanner.nextLine();
        while (!direction.equals("left") && !direction.equals("right")) {
            System.out.println("Invalid input. Please enter either 'left' or 'right' for the direction.");
            direction = scanner.nextLine();
        }

        System.out.println("\n----- Disk Scheduling Process with SCAN -----");

        SCAN(arr, head, direction);

        scanner.close();
    }
}
