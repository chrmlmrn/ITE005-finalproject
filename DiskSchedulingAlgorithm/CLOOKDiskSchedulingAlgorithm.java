import java.util.*;

public class CLOOKDiskSchedulingAlgorithm {
    static int disk_size = 200;

    public static void CLOOK(int[] arr, int head) {
        int size = arr.length;
        int seek_count = 0;
        int distance, cur_track;

        List<Integer> left = new ArrayList<Integer>();
        List<Integer> right = new ArrayList<Integer>();
        List<Integer> seek_sequence = new ArrayList<Integer>();

        // Separate the tracks into left and right based on their position relative to
        // the head
        for (int i = 0; i < size; i++) {
            if (arr[i] < head)
                left.add(arr[i]);
            if (arr[i] > head)
                right.add(arr[i]);
        }

        // Sort the left and right tracks
        Collections.sort(left);
        Collections.sort(right);

        // Process the right side tracks
        for (int i = 0; i < right.size(); i++) {
            cur_track = right.get(i);
            seek_sequence.add(cur_track);
            distance = Math.abs(cur_track - head);
            seek_count += distance;
            head = cur_track;
        }

        // If there are tracks on the left side, process them as well
        if (!left.isEmpty()) {
            seek_count += Math.abs(head - left.get(0));
            head = left.get(0);

            for (int i = 0; i < left.size(); i++) {
                cur_track = left.get(i);
                seek_sequence.add(cur_track);
                distance = Math.abs(cur_track - head);
                seek_count += distance;
                head = cur_track;
            }
        }

        // Display the request sequence and initial head position
        System.out.print("Request Sequence: ");
        for (int i = 0; i < size; i++) {
            System.out.print(arr[i] + " ");
        }

        System.out.println();
        // Print the seek sequence table
        System.out.println("\n----- Seek Sequence -----");
        System.out.println("-------------------------------------------------");
        System.out.println("| Track Accessed | Seek Count  | Running Total  |");
        System.out.println("-------------------------------------------------");
        int running_total = 0;

        for (int i = 0; i < seek_sequence.size(); i++) {
            int track = seek_sequence.get(i);
            int seek = Math.abs(track - head);
            running_total += seek;
            System.out.printf("|%15d |%12d |%15d |\n", track, seek, running_total);
            head = track;
        }

        System.out.println("-------------------------------------------------");
        System.out.println("\nTotal number of seek operations = " + seek_count + "\n");
    }

    public static void main(String[] args) {
        System.out.println("\n----- Disk Scheduling Algorithm with CLOOK -----");
        Scanner scanner = new Scanner(System.in);

        // Get the number of requests
        System.out.print("Enter the number of requests: ");
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter an integer value for the size.");
            scanner.next();
        }
        int size = scanner.nextInt();
        int[] arr = new int[size];

        // Get the request array
        System.out.println("Enter the values for the request array:");
        for (int i = 0; i < size; i++) {
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter an integer value for the sequence values.");
                scanner.next();
            }
            arr[i] = scanner.nextInt();
        }

        // Get the initial head position
        System.out.print("Enter the value for the head: ");
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter an integer value for the head position.");
            scanner.next();
        }
        int head = scanner.nextInt();

        System.out.println("\n----- Disk Scheduling Process with CLOOK -----");
        // Perform CLOOK disk scheduling algorithm
        CLOOK(arr, head);

        scanner.close();
    }
}
