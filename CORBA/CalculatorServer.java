//CalculatorServer.java
import CalculatorApp.*;
import org.omg.CORBA.*;
import org.omg.CosNaming.*;
import org.omg.PortableServer.*;
import org.omg.PortableServer.POA;

public class CalculatorServer {
    public static void main(String[] args) {
        try {
            ORB orb = ORB.init(args, null);
            POA rootPoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
            rootPoa.the_POAManager().activate();

            CalculatorImpl calculatorImpl = new CalculatorImpl();
            org.omg.CORBA.Object ref = rootPoa.servant_to_reference(calculatorImpl);
            Calculator calculatorRef = CalculatorHelper.narrow(ref);

            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

            ncRef.rebind(ncRef.to_name("Calculator"), calculatorRef);
            System.out.println("Calculator Server is running...");
            orb.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
