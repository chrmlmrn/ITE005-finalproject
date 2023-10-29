import java.util.*;

public class FCFSCPUSchedulingAlgorithm {
    public static void main(String args[]) {

        System.out.println("\n----- CPU Scheduling Algorithm with First-Come-First-Serve -----");

        // Create a Scanner object for user input
        Scanner sc = new Scanner(System.in);

        // Prompt the user to enter the number of processes
        System.out.print("Enter the number of processes: ");
        int n = sc.nextInt();

        // Create arrays to store process details
        int pid[] = new int[n]; // process IDs
        int at[] = new int[n]; // arrival time
        int bt[] = new int[n]; // burst time
        int ct[] = new int[n]; // completion time
        int ta[] = new int[n]; // turnaround time
        int wt[] = new int[n]; // waiting time
        int temp;
        float avgwt = 0, avgta = 0;

        // Prompt the user to enter details for each process
        for (int i = 0; i < n; i++) {
            System.out.println("Enter details for P" + (i + 1) + ":");
            System.out.print("Enter P" + (i + 1) + "'s arrival time: ");
            at[i] = sc.nextInt();
            System.out.print("Enter P" + (i + 1) + "'s burst time: ");
            bt[i] = sc.nextInt();
            pid[i] = i + 1;
        }

        // Arrange the processes according to arrival times using Bubble Sort
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - (i + 1); j++) {
                if (at[j] > at[j + 1]) {
                    // Swap arrival time
                    temp = at[j];
                    at[j] = at[j + 1];
                    at[j + 1] = temp;

                    // Swap burst time
                    temp = bt[j];
                    bt[j] = bt[j + 1];
                    bt[j + 1] = temp;

                    // Swap process IDs
                    temp = pid[j];
                    pid[j] = pid[j + 1];
                    pid[j + 1] = temp;
                }
            }
        }

        // Calculate the completion time, turnaround time, and waiting time for each
        // process
        for (int i = 0; i < n; i++) {
            if (i == 0) {
                ct[i] = at[i] + bt[i];
            } else {
                if (at[i] > ct[i - 1]) {
                    ct[i] = at[i] + bt[i];
                } else
                    ct[i] = ct[i - 1] + bt[i];
            }

            // Calculate turnaround time: completion time - arrival time
            ta[i] = ct[i] - at[i];

            // Calculate waiting time: turnaround time - burst time
            wt[i] = ta[i] - bt[i];

            // Calculate total waiting time and total turnaround time for average
            // calculation
            avgwt += wt[i];
            avgta += ta[i];
        }

        // Print the table of process details
        System.out.println("\n----- CPU Scheduling Process with First-Come-First-Serve -----");
        System.out.print("---------------------------------------------------------------");
        System.out.println("\n|  ID   | Arrival | Burst | Complete | Turnaround |  Waiting  |");
        System.out.println("---------------------------------------------------------------");
        for (int i = 0; i < n; i++) {
            System.out.printf("|  %2d   |   %3d   |  %3d  |   %3d    |    %3d     |   %3d     |\n", pid[i], at[i],
                    bt[i], ct[i], ta[i], wt[i]);
        }
        sc.close();
        System.out.println("---------------------------------------------------------------\n");

        // Print the average waiting time and average turnaround time
        System.out.printf("The average waiting time: %.2f\n", (avgwt / n));
        System.out.printf("The average turnaround time: %.2f\n", (avgta / n));
        System.out.println();
    }
}
