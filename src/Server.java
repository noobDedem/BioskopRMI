import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server {

    public static void main (String args[]) {
        /**
         * Menginisiasi objek bioskop
         */
        int[][] availableSeat = new int[6][6];
        BioskopImplementasi bioskopImplementasi = new BioskopImplementasi(availableSeat);
        bioskopImplementasi.initSeat(availableSeat);

        try {
            IBioskop stub = (IBioskop) UnicastRemoteObject.exportObject(bioskopImplementasi,0);

            //Bind the remote object's stub in the registry
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.bind("Ticketing", stub);

            System.err.println("Server ready");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}