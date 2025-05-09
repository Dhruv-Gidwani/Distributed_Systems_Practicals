import java.util.*;

public class BullyAlgo {
    static int numProcesses;
    static int[] isActive;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the number of processes: ");
        numProcesses = sc.nextInt();
     
        isActive = new int[numProcesses + 1];

        for (int i = 1; i <=numProcesses; i++) {
            isActive[i] = 1;
        }

        int oldLeader = numProcesses;
        isActive[oldLeader] = 0;
        System.out.println("The process that failed is: " + oldLeader);

        System.out.print("Enter the process that initiates the election: ");
        int initiator = sc.nextInt();

        System.out.print("Enter another process to fail (0 if none): ");
        int failedProcess = sc.nextInt();
        if (failedProcess != oldLeader && failedProcess > 0 && failedProcess <=numProcesses) {
            isActive[failedProcess] = 0;
        }

        int newLeader = runElection(initiator);

        System.out.println("\nProcess " + newLeader + " is elected as the new leader.");

        for (int i = 1; i <= numProcesses; i++) {
            if (isActive[i] == 1 && i != newLeader) {
                System.out.println("Leader " + newLeader + " sends COORDINATOR message to process " + i);
            }
        }
        sc.close();
    }

    // Election logic
    public static int runElection(int initiator) {
        int selectedLeader = initiator;

        for (int i = initiator; i <= numProcesses; i++) {
            if (isActive[i] == 1) {
                // Send election message to higher processes
                for (int j = i + 1; j <= numProcesses; j++) {
                    if (isActive[j] == 1) {
                        System.out.println("Process " + i + " sends ELECTION(" + i + ") to process " + j);
                    }
                }

                boolean gotOk = false;
                // Receive OK messages from higher processes
                for (int j = i + 1; j <= numProcesses; j++) {
                    if (isActive[j] == 1) {
                        System.out.println("Process " + j + " replies OK(" + j + ") to process " + i);
                        gotOk = true;
                    }
                }
                
                if (!gotOk) {
                    // No higher active process found, elect this as leader
                    selectedLeader = i;
                    break;
                }
            }
        }

        return selectedLeader;
    }
}


/*
import java.util.*;

public class BullyAlgo{

	static int numProcess;
	static int[] Active;
	
	public static void main(String args[]){
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the number of processes");
		numProcess=sc.nextInt();
		
		Active = new int[numProcess+1];
		
		for(int i=1; i<=numProcess; i++){
			Active[i]=1;
		}
		
		int oldLeader=numProcess;
		Active[oldLeader]=0;
		System.out.println("The oldLeader is "+oldLeader);
		
		System.out.println("Enter the process that fails (0 for none)");
		int failProcess=sc.nextInt();
		if(failProcess != oldLeader && failProcess>0 && failProcess<=numProcess){
			Active[failProcess]=0;
		}
		
		System.out.println("Enter the initiator of the election");
		int initiator = sc.nextInt();
		
		int newLeader = runElection(initiator);
		
		System.out.println("The new Leader is "+newLeader);
		
		for(int i=1;i<=numProcess; i++){
			if(Active[i]==1 && i!=newLeader){
				System.out.println("Process "+newLeader+" sends coordination message to "+ i);
			}
		}
		sc.close();
	}
	
	public static int runElection(int initiator){
		int selectedLeader = initiator;
		
		for(int i=initiator;i<=numProcess; i++){
			if(Active[i]==1){
				for(int j=i+1; j<=numProcess; j++){
					if(Active[j]==1){
						System.out.println("Election message sent from "+ i + "to "+j);
					}
				}
				
				boolean gotOK=false;
				for(int j=i+1; j<=numProcess; j++){
					if(Active[j]==1){
						System.out.println("OK message sent from "+ j + "to "+i);
						gotOK=true;
					}
				}
				
				if(!gotOK){
					selectedLeader=i;
					break;
				}
			}
		}
		return selectedLeader;
	}
}
*/
