import java.util.*;

public class TokenRing {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the message:");
        String message = sc.nextLine();

        System.out.println("Enter the number of processes:");
        int n = sc.nextInt();

        System.out.println("The processes are:");
        for (int i = 0; i < n; i++) {
            System.out.print(i + " ");
        }

        System.out.println("\n");

        System.out.println("Enter the sender:");
        int sender = sc.nextInt();

        System.out.println("Enter the receiver:");
        int receiver = sc.nextInt();

        //Input : Which processes want to enter Critical Section
        boolean[] wantsCS = new boolean[n];
        Arrays.fill(wantsCS, false);
        System.out.println("Enter process IDs (0 to " + (n - 1) + ") that want to enter Critical Section (-1 to EXIT):");
        while (true) {
            int id = sc.nextInt();
            if (id == -1) break;
            if (id >= 0 && id < n) {
                wantsCS[id] = true;
            } else {
                System.out.println("Invalid process ID. Try again.");
            }
        }
        
	System.out.println("\n");
        System.out.println("Sender process -> " + sender);
        System.out.println("The token is received by the sender");
        System.out.println("The message to be sent: " + message);
	System.out.println("\n");
	
        int current = sender;

        while (true) {
            current = (current + 1) % n;  // Circular movement in token ring
            System.out.println("Token passed to process " + current);
	    System.out.println("\n");
            // Mutual exclusion part
            if (wantsCS[current]) {
		    System.out.println("Process " + current + " enters Critical Section.");
		    System.out.println("Enter the message:");
		    sc.nextLine();
		    message = sc.nextLine();
		    System.out.println("Shared message in CS: " + message);
		    System.out.println("Message updated by process " + current);
		    try { Thread.sleep(500); } catch (InterruptedException e) {}
		    System.out.println("Process " + current + " exits Critical Section.");
		    System.out.println("\n");
		    wantsCS[current] = false;
		}

            if (current == receiver) {
                System.out.println("\nReceiver process -> " + receiver);
                System.out.println("The message received: " + message);
                System.out.println("\n");
                break;
            }
        }
        sc.close();
    }
}
