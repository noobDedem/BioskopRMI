import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IBioskop extends Remote {

    String emptySeat(int row, int col) throws RemoteException;

    String printSeat() throws RemoteException;

    String isSingle (int jumlah) throws RemoteException;

    boolean isEmpty(int row, int col) throws RemoteException;

    int convertRow(char in)throws RemoteException;

    int convertCol(char in)throws RemoteException;
}

