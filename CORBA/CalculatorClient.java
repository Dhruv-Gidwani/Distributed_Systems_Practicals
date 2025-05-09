//CalculatorClient.java
import CalculatorApp.*;
import org.omg.CORBA.*;
import org.omg.CosNaming.*;

public class CalculatorClient {
    public static void main(String[] args) {
        try { 
		    ORB orb = ORB.init(args, null);
		    org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
		    NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

		    Calculator calculator = CalculatorHelper.narrow(ncRef.resolve_str("Calculator"));
			
		    System.out.println("Add: " + calculator.add(10, 5));
		    System.out.println("Subtract: " + calculator.subtract(10, 5));
		    System.out.println("Multiply: " + calculator.multiply(10, 5));
		    System.out.println("Divide: " + calculator.divide(10, 5));
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}

