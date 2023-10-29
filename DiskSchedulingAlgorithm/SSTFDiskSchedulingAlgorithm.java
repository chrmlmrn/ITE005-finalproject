import java.util.Scanner;

class Node {
    int distance = 0;
    boolean accessed = false;
}

public class SSTFDiskSchedulingAlgorithm {
    public static void calculateDifference(int[] queue, int head, Node[] diff) {
        // Calculate the absolute distance between each queue element and the head
        for (int i = 0; i < diff.length; i++)
            diff[i].distance = Math.abs(queue[i] - head);
    }

    public static int findMin(Node[] diff) {
        int index = -1, minimum = Integer.MAX_VALUE;

        // Find the minimum distance that has not been accessed
        for (int i = 0; i < diff.length; i++) {
            if (!diff[i].accessed && minimum > diff[i].distance) {
                minimum = diff[i].distance;
                index = i;
            }
        }
        return index;
    }

    public static void SSTF(int[] request, int head) {
        if (request.length == 0)
            return;

        Node[] diff = new Node[request.length];

        // Initialize the array of Node objects
        for (int i = 0; i < diff.length; i++)
            diff[i] = new Node();

        int seek_count = 0;
        int[] seek_sequence = new int[request.length + 1];

        System.out.print("Request Sequence: ");
        for (int i = 0; i < request.length; i++) {
            System.out.print(request[i] + " ");
        }
        System.out.println("\nInitial Head Position: " + head);

        System.out.println("\n----- Seek Sequence -----");

        System.out.println("-------------------------------------------------");
        System.out.println("| Track Accessed | Seek Count  | Running Total  |");
        System.out.println("-------------------------------------------------");
        for (int i = 0; i < request.length; i++) {
            seek_sequence[i] = head;

            // Calculate the difference between each request and the head
            calculateDifference(request, head, diff);

            // Find the index of the minimum distance
            int index = findMin(diff);

            // Mark the node as accessed and update the seek count and head position
            diff[index].accessed = true;
            seek_count += diff[index].distance;
            head = request[index];

            // Print the details of the current seek operation
            System.out.printf("|%15d |%12d |%15d |\n", request[index], diff[index].distance, seek_count);
        }

        seek_sequence[seek_sequence.length - 1] = head;
        System.out.println("-------------------------------------------------");

        System.out.println("\nTotal number of seek operations = " + seek_count + "\n");
    }

    public static void main(String[] args) {
        System.out.println("\n----- Disk Scheduling Algorithm with Shortest Seek Time First (SSTF) -----");
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of requests:");
        while (!scanner.hasNextInt()) {
            System.out.print("Invalid input. Please enter an integer value for the size.");
            scanner.next();
        }
        int size = scanner.nextInt();
        int[] request = new int[size];

        System.out.println("Enter the values for the request array:");

        // Read the request array elements from the user
        for (int i = 0; i < size; i++) {
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter an integer value for the sequence values.");
                scanner.next();
            }
            request[i] = scanner.nextInt();
        }

        System.out.println("Enter the value for the head:");
        while (!scanner.hasNextInt()) {
            System.out.print("Invalid input. Please enter an integer value for the head position.");
            scanner.next();
        }
        int head = scanner.nextInt();

        System.out.println("\n----- Disk Scheduling Process with Shortest Seek Time First (SSTF) -----");

        // Call the SSTF algorithm function
        SSTF(request, head);

        scanner.close();
    }
}
