import java.util.*;

public class RingAlgo {

    static int numProcesses;
    static int[] isActive;
    static int[] electionArr;
    static int oldLeader;
    static int newLeader;
    static int initiator;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the number of processes: ");
        numProcesses = sc.nextInt();
        isActive = new int[numProcesses + 1];

        // Initially all processes are active
        for (int i = 1; i <= numProcesses; i++) {
            isActive[i] = 1;
        }

        oldLeader = numProcesses;
        isActive[oldLeader] = 0;
        System.out.println("The process that is failed is " + oldLeader);

        System.out.print("Enter the process that initiates the process: ");
        initiator = sc.nextInt();

        System.out.print("Enter the process that fails (other than leader, 0 if none): ");
        int failedProcess = sc.nextInt();
        if (failedProcess != oldLeader && failedProcess > 0 && failedProcess <= numProcesses) {
            isActive[failedProcess] = 0;
        }

        newLeader = runElection(initiator);

        System.out.println("\nFinally the process " + newLeader + " is selected as new lead..");

        for (int i = 1; i <= numProcesses; i++) {
            if (isActive[i] == 1 && i != newLeader) {
                System.out.println("Process " + newLeader + " passes the coordinator (" + newLeader + ") to process " + i);
            }
        }

        sc.close();
    }

    // Election logic
    public static int runElection(int initiator) {
        System.out.println("The election process is started by " + initiator);

        electionArr = new int[numProcesses + 1];
        int index = 0;
        int current = initiator;
        int receiver = (current % numProcesses) + 1;

        while (index < numProcesses) {
            if (isActive[current] == 1 && current != receiver) {
                while (isActive[receiver] == 0) {
                    receiver = (receiver % numProcesses) + 1;
                }
                System.out.println("Process " + current + " sends the Election message to process " + receiver);
                electionArr[index] = current;
                printArray(electionArr, index + 1);
            }

            current = (current % numProcesses) + 1;
            receiver = (current % numProcesses) + 1;
            index++;
        }
        int selectedLeader = 0;
        for (int i = 0; i <= numProcesses; i++) {
            if (electionArr[i] > selectedLeader) {
                selectedLeader = electionArr[i];
            }
        }
        return selectedLeader;
    }

    public static void printArray(int[] arr, int size) {
        System.out.print("[");
        for (int i = 0; i < size; i++) {
            if (arr[i] == 0) continue;
            System.out.print(arr[i] + " ");
        }
        System.out.println("]");
    }
}






