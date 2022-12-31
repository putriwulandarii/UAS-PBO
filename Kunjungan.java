import java.util.Scanner;
import java.util.*;
import java.text.*;
import java.util.Date;

public class Kunjungan extends Program implements Pendataan{

    int nomor;
    int ID_Petugas;
    String namaPetugas = "";
    int jumlahBuku;
    int pengunjung;
    int lamaHari;
    int denda;
    int hari;

    // method construktor -> method yang memiliki nama yang sama dengan nama class itu sendiri
    Kunjungan(){

        System.out.println("Nama  : Putri Wulan Dari");
        System.out.println("NIM   : 2011521027");
        System.out.println("Jurusan : Sistem Informasi");

    }

    // welcome
    // method string -> toUpperCase()
    public void welcome(){

        String text = "\n===selamat datang pengunjung perpustakaan===\n";
        System.out.println(text.toUpperCase());

    }

    @Override
    public void ID() {
        
    }

    @Override
    public void nama() {
        
    }

    @Override
    public void kunjungan() {
        
    }

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

    public void petugas(){

        Scanner userInput = new Scanner(System.in);

        System.out.print("\nMasukkan nama petugas\t: " );
        namaPetugas = userInput.nextLine();
        System.out.print("\nMasukkan no ID petugas\t: ");
        ID_Petugas = userInput.nextInt();
        
    }

    
    // tanggal dan waktu
    Date Now = new Date(); 
    SimpleDateFormat tgl =  new SimpleDateFormat ("EEEE, dd-MM-yyy");
    SimpleDateFormat wk = new SimpleDateFormat ("hh:mm:ss a zzz");

 
}
