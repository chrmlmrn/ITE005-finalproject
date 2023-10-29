import java.util.*;

public class FIFOPageReplacementAlgorithm {
    public static void main(String[] args) {
        // Print algorithm header
        System.out.println("\n----- Page Replacement Algorithm with First-In-First-Out -----");

        // Prompt for the number of frames
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of frames: ");
        int numFrames = scanner.nextInt();

        // Prompt for the length of the reference string
        System.out.print("Enter the length of the reference string: ");
        int length = scanner.nextInt();

        // Initialize the reference string
        int[] referenceString = new int[length];
        System.out.println("Enter the reference string:");
        for (int i = 0; i < length; i++) {
            referenceString[i] = scanner.nextInt();
        }

        // Initialize the frames
        int[] frames = new int[numFrames];
        Arrays.fill(frames, -1); // Initialize frames with -1 to indicate empty

        int pageFaults = 0;
        int pointer = 0; // Pointer to track the oldest page in the frame

        // Print page replacement process header
        System.out.println("\n----- Page Replacement Process with First-In-First-Out -----");
        System.out.println("Reference String: " + Arrays.toString(referenceString));
        System.out.println("No. Of Page Frame: " + numFrames);

        // Print table header
        System.out.println("\n----------------------------------");
        System.out.println("| Page |   Status   |   Frames   |");
        System.out.println("----------------------------------");

        // Process each page in the reference string
        for (int i = 0; i < length; i++) {
            int currentPage = referenceString[i];

            // Check if the current page is already in a frame
            boolean pageFound = false;
            for (int j = 0; j < numFrames; j++) {
                if (frames[j] == currentPage) {
                    pageFound = true;
                    break;
                }
            }

            if (pageFound) {
                // Page hit
                System.out.format("|  %-3s |    Hit     | %s |%n", currentPage, getFramesTable(frames));
            } else {
                // Page fault
                frames[pointer] = currentPage;
                pointer = (pointer + 1) % numFrames; // Move the pointer to the next frame

                pageFaults++;
                System.out.format("|  %-3s |    Miss    | %s |%n", currentPage, getFramesTable(frames));
            }
        }

        // Print table footer
        System.out.println("----------------------------------");
        System.out.println("\nTotal page faults: " + pageFaults + "\n");
        scanner.close();
    }

    // Helper method to format the frames into a table
    private static String getFramesTable(int[] frames) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < frames.length; i++) {
            if (frames[i] == -1) {
                sb.append("  ");
            } else {
                sb.append(String.format("%2d", frames[i]));
            }

            if (i != frames.length - 1) {
                sb.append("  ");
            }
        }
        return sb.toString();
    }
}
