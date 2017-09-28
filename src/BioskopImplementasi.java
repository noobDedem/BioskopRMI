import java.rmi.RemoteException;

public class BioskopImplementasi implements IBioskop {

    int[][] availableSeat;

    public BioskopImplementasi(int[][] availableSeat) {
        this.availableSeat = availableSeat;
    }

    public int[][] getAvailableSeat() {
        return availableSeat;
    }

    public void setAvailableSeat(int[][] availableSeat) {
        this.availableSeat = availableSeat;
    }

    /**
     * Menginisiasi array kursi
     * @param seat
     */
    public void initSeat(int[][] seat) {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j <6; j++) {
                seat[i][j] = 0;
            }
        }
    }

    /**
     * Digunakan untuk mencetak kursi yang ada
     * @return kursi yang ada
     * @throws RemoteException
     */
    @Override
    public String printSeat() throws RemoteException {
        String print = "Berikut adalah daftar kursi yang ada\n\n";
        print = print + "X\t1\t2\t3\t4\t5\n";
        int c = 65;
        for (int i=1;i<=5;i++){
            print = print + (char)c + "\t";
            for (int j=1;j<=5;j++){
                String seat = Integer.toString(availableSeat[i][j]) ;
                print = print + seat + "\t" ;
            }
            print = print + "\n";
            c=c+1;
        }
        return print;
    }

    /**
     * Digunakan untuk mengecek jumlah parameter dan bertindak sesuai kondisi
     * @param jumlah : jumlah tiket yang dipesan
     * @return kalimat sesuai parameter jumlah
     * @throws RemoteException
     */
    @Override
    public String isSingle(int jumlah) throws RemoteException {
        if (jumlah == 1){
            return "Cieee yang sendirian ajaaa :v\n";
        }else if(jumlah == 2){
            return "Cieee sama gebetan yaaa\n";
        }else if(jumlah > 25){
            return "Maaf kak, kursinya cuman ada 25 tuh :( mau bikinin gedung baru nggak?\n";
        }
        return "OK\n";
    }

    /**
     * Digunakan untuk mengecek apakah kursi sudah dipesan atau belum
     * @param row baris yang diambil
     * @param col kolom yang diambil
     * @return kosong atau tidak kursi yang ingin dipesan
     * @throws RemoteException
     */
    @Override
    public boolean isEmpty(int row, int col) throws RemoteException {
        if (availableSeat[row][col] == 0) {
            return true;
        } else if (availableSeat[row][col] != 0) {
            return false;
        }
        return false;
    }

    /**
     * Mengeluarkan kalimat tergantung dari kolom dan baris
     * @param row baris kursi yang dipesan
     * @param col kolom yang dipesan
     * @return
     * @throws RemoteException
     */
    @Override
    public String emptySeat(int row, int col) throws RemoteException {
        if (availableSeat[row][col] != 0) {
            return "Maaf, kursi tersebut sudah dipesan, mohon pilih kembali\n";
        }
        availableSeat[row][col]=1;
        return "Kursi berhasil dipesan.\n";
    }

    /**
     * Mengubah kolom dari bentuk char menjadi integer
     * @param in
     * @return
     * @throws RemoteException
     */
    @Override
    public int convertCol(char in) throws RemoteException {
        int a = 0;
        switch(in){
            case '1':a=1;break;
            case '2':a=2;break;
            case '3':a=3;break;
            case '4':a=4;break;
            case '5':a=5;break;
        }
        return a;
    }

    /**
     * Mengubah baris dari bentuk char menjadi integer
     * @param in
     * @return
     * @throws RemoteException
     */
    @Override
    public int convertRow(char in) throws RemoteException {
        int a = 0;
        switch(in){
            case 'A':a=1;break;
            case 'B':a=2;break;
            case 'C':a=3;break;
            case 'D':a=4;break;
            case 'E':a=5;break;
            case 'a':a=1;break;
            case 'b':a=2;break;
            case 'c':a=3;break;
            case 'd':a=4;break;
            case 'e':a=5;break;
        }
        return a;
    }
}
