import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class Client {

    public static void main(String[] args) {

        /**
         * Digunakan untuk parameter pengecekan apakah input yang
         * dimasukkan berupa angka atau bukan
         */
        boolean inputFlag = false;

        // Inisialisasi untuk jumlahh kursi yang mau diambil
        int jumlah = 0;

        try {
            // IP Address dari server yang dituju
            String ipServer = "192.168.43.129";
            // Port dari RMI
            int port = 1099;
            // Digunakan untuk reference dari remote object pada ip dan port tertentu
            Registry registry = LocateRegistry.getRegistry(ipServer, port);

            /**
             * Inisiasi koneksi dengan remote virtual machine
             * Berfungsi untuk transmit (marshals) parameter yang ingin di gunakan
             */
            IBioskop stub = (IBioskop) registry.lookup("Ticketing");

            String namaPengunjung = JOptionPane.showInputDialog(null,
                    "Hallo!\n"
                            +"Selamat datang di E-ticketing.\n"
                            +"Silahkan masukkan nama Anda");

            while (true) {
                while (true) {
                    String jumlahKursi = JOptionPane.showInputDialog(null,
                            "Halo "+namaPengunjung+" mau pesan berapa tiket? ");
                    try {
                        jumlah = Integer.parseInt(jumlahKursi);
                        inputFlag = true;
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null,
                                "Jumlah tiket tidak valid!\n");
                        inputFlag = false;
                    }
                    if (inputFlag) {
                        break;
                    }
                }
                if (jumlah <= 25 && jumlah > 0) {
                    break;
                } else {
                    JOptionPane.showMessageDialog(null,
                            "\nMaaf total kursi hanya berjumlah 25");
                }
            }

            String isJombloNgenes = stub.isSingle(jumlah);
            System.out.println(isJombloNgenes);
            for (int i = 1; i < jumlah + 1; i++) {
                String seat = stub.printSeat();
                JOptionPane.showMessageDialog(null, new JTextArea("Untuk kursi yang ke-" + i +"\n"+seat));

                while (true) {
                    String input;
                    input = JOptionPane.showInputDialog(null,
                            "Masukkan kursi yang ingin dipesan! ");
                    int col = 0;
                    int row = 0;
                    if(input.length()==2){
                        col = stub.convertCol(input.charAt(1));
                        row = stub.convertRow(input.charAt(0));
                    }else{
                        System.out.println("error 1");
                        input = JOptionPane.showInputDialog(null,
                                "Input tidak valid"+"\n"
                                        +"Silahkan masukkan Baris dan nomor kursi!");
                    }


                    while (col > 5  || col <= 0 || row > 5 || row <= 0) {
                        if(input.length()==2){
                            col = stub.convertCol(input.charAt(1));
                            row = stub.convertRow(input.charAt(0));
                        }else{
                            System.out.println("error 2");
                            input = JOptionPane.showInputDialog(null,
                                    "Input tidak valid"+"\n"
                                            +"Silahkan masukkan Baris dan nomor kursi!");
                        }

                    }
                    System.out.println("lanjut");
                    boolean b = stub.isEmpty(row, col);
                    String isSeatEmpty = stub.emptySeat(row,col);
                    JOptionPane.showMessageDialog(null,isSeatEmpty);
                    /**
                     * mengecek apabila kursi kosong maka while (true) akan di break
                     * sedangkan apabila kursi sudah terisi maka while akan tetap berjalan
                     */
                    if (b) {
                        break;
                    }
                }
            }
            JOptionPane.showMessageDialog(null,"Transaksi selesai.\n"+jumlah+" tiket berhasil dipesan dengan total Rp. "+jumlah*35+".000"+
                    "\nTerimakasih "+namaPengunjung+" telah menggunakan E-Ticketing");
        } catch (RemoteException e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        } catch (NotBoundException e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}