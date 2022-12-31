import java.util.Scanner;
public class Buku extends Kunjungan{

    Scanner userInput = new Scanner(System.in);

    @Override
    public void penulis() {
    }

    @Override
    public void judulBuku() {
        
    }

    @Override
    public void kategoriBuku() {
        
    }

    @Override
    public void penerbit() {
        
    }

    public void tampil(){

        welcome();
        petugas();
        System.out.println("\n\n");
        System.out.println("===LAPORAN KUNJUNGAN PERPUSTAKAAN===");
        System.out.println("Tanggal kunjungan  : " + tgl.format(Now));
        System.out.println("Waktu kunjungan    : " + wk.format(Now));
        // tampil 
        System.out.println("No.ID petugas\t   : " + ID_Petugas);
        getClass();
        System.out.println("Nama petugas\t   : " + namaPetugas);
        System.out.println("\n");
        getClass();

    }

    
}
