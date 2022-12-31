import java.util.InputMismatchException;
// scanner
import java.util.Scanner;
// bersihkan layar
import java.io.IOException;
// sql
import java.sql.*;

public class Program {

    // SQL

    // objek yang diperlukan untuk mengelola database
    static Connection connSQL;
    static Statement statementSQL;
    static ResultSet resultSetSQL;
    
    // paramater JDBC untuk koneksi ke database
    static String url = "jdbc:mysql://localhost:3306/db_perpustakaan";
    static String driver = "com.mysql.cj.jdbc.Driver";
    static String user = "root";
    static String pass = ""; 

    public static void main(String[] args) throws Exception {

        // bersihkan layar
        cls();

        // input dari user
        Scanner userInput = new Scanner(System.in);

        String pilihanUser;
        boolean lanjutkan = true;

        // objek baru-> buku
        Buku buku = new Buku();
        
    // Main

    // Exception -> try dan catch
    
    // menangani eror di Java
    try{

        // interface -> penghubung antar objek
        

        // register driver yang akan dipakai
        Class.forName(driver);
        // koneksi ke database
        connSQL = DriverManager.getConnection(url, user, pass);
        System.out.println("\n-----------------------------");
        System.out.println("| Class Driver ditemukan    |\n| Silakan lanjutkan program |");
        System.out.println("-----------------------------");

        // objek buku
        
        buku.tampil();
        

        // pengulangan while do
        while(lanjutkan){

        System.out.println("\n====Aktivitas Kunjungan di Perpustakaan====\n");
        System.out.println("1. Lihat data kunjungan");
        System.out.println("2. Cari data kunjungan");
        System.out.println("3. Tambah data kunjungan");
        System.out.println("4. Hapus data kunjungan");
        System.out.println("5. Ubah data kunjungan");
        System.out.println("6. Peminjaman buku");
        System.out.println("0. Exit");

        System.out.print("\nMasukkan Pilihan Anda (1/2/3/4/5/6/0): ");
        pilihanUser = userInput.next();
        System.out.println("\n");

        // percabangan
        switch(pilihanUser){

            case "1":
                // tampilkan data
                System.out.println("========================================");
                System.out.println("|   LIST DATA KUNJUNGAN PERPUSTAKAAN   |");
                System.out.println("========================================");
                LihatData();
                break;
            case "2":
                System.out.println("========================================");
                System.out.println("|   CARI DATA KUNJUNGAN PERPUSTAKAAN   |");
                System.out.println("========================================");
                CariData();
                break;
            case "3":
                System.out.println("==========================================");
                System.out.println("|   TAMBAH DATA KUNJUNGAN PERPUSTAKAAN   |");
                System.out.println("==========================================");
                TambahData();
                break;
            case "4":
                System.out.println("=========================================");
                System.out.println("|   HAPUS DATA KUNJUNGAN PERPUSTAKAAN   |");
                System.out.println("=========================================");
                HapusData();
                break;
            case "5":
                System.out.println("========================================");
                System.out.println("|   UBAH DATA KUNJUNGAN PERPUSTAKAAN   |");
                System.out.println("========================================");
                UbahData();
                break;
            case "6":
                System.out.println("=======================");
                System.out.println("|   PEMINJAMAN BUKU   |");
                System.out.println("=======================");
                peminjamanBuku();
                break;
            case "0":
                System.out.println("\n=====Terimakasih Sudah Berkunjung=====");
                System.exit(0);
                break;
            default:
                System.err.println("\nInput tidak ditemukan, pilih(0-6)");

        }

         // lanjutkan: yes atau no
         System.out.println("\nApakah anda ingin melanjutkan [y/n]? ");
         pilihanUser = userInput.next();
         lanjutkan = pilihanUser.equalsIgnoreCase("Y");
 
         // lanjutkan terus jika input tidak y atau n 
         while(!pilihanUser.equalsIgnoreCase("y") && !pilihanUser.equalsIgnoreCase("n")){

             System.err.print("\nPilihan anda bukan y atau n\nSilakan pilih y atau n!");
             System.out.print("\nApakah anda ingin melanjutkan [y/n]? ");
             pilihanUser = userInput.next();
             lanjutkan = pilihanUser.equalsIgnoreCase("Y");
             System.out.println("\n");

        }
        
        String text = "\n===Terimakasih Sudah Berkunjung===\n";
        System.out.println(text.toUpperCase());

        } 
        
    } catch(ClassNotFoundException ex){

        System.err.println("Driver eror");
        System.exit(0);

    }

    }

    // bersihkan layar
    public static void cls() {

        System.out.print("\033[H\033[2J");  
        System.out.flush(); 

    }


    // lihat data di perpustakaan
    private static void LihatData() throws SQLException{

    try{
        // SQL database

        // query lihat data
        String sql = "SELECT * FROM aktivitas";
        // koneksi ke database
        connSQL = DriverManager.getConnection(url, user, pass);
        // objek statement
        statementSQL = connSQL.createStatement();
        // eksekusi query dan simpan hasilnya di obj ResultSet
        // Method executeQuery() akan menghasilkan nilai kembalian berupa objek resultSetSQL.
        resultSetSQL = statementSQL.executeQuery(sql);
        
        // nomor
        int nomor = 0;

        System.out.println("\n| No.|\tID   |\tNama        |\tKunjungan        |\tJudul Buku                 |\tPenulis               |\tKategori Buku        |\tPenerbit      ");
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------");
        
        // tampilkan hasil query
        while(resultSetSQL.next()){
            
            nomor++;

            System.out.printf("| %2d ", nomor);
            System.out.printf("|\t%-5d", resultSetSQL.getInt("ID"));
            System.out.printf("|\t%-10s  ", resultSetSQL.getString("Nama"));
            System.out.printf("|\t%-15s  ", resultSetSQL.getString("Kunjungan"));
            System.out.printf("|\t%-25s  ", resultSetSQL.getString("Judul_Buku"));
            System.out.printf("|\t%-20s  ", resultSetSQL.getString("Penulis"));
            System.out.printf("|\t%-20s ", resultSetSQL.getString("Kategori_Buku"));
            System.out.printf("|\t%-10s ", resultSetSQL.getString("Penerbit"));   
            System.out.println("\n");               
        }

        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------");

        } catch(InputMismatchException e){

            System.err.println("Data belum ada");
            TambahData();

        }
    }


    // cari data di perpustakaan
    private static void CariData() throws SQLException{

        try{

            // input dari user
            Scanner userInput = new Scanner(System.in);

            int nomor = 0;
            
            System.out.print("\nMasukkan keyword ID untuk mencari data: ");
            String keyword = userInput.nextLine();

            // SQL database

            // query cari data
            String sql = "SELECT * FROM aktivitas WHERE ID LIKE '%"+keyword+"%'";
            //  koneksi ke database
            connSQL = DriverManager.getConnection(url, user, pass);
            // objek statement
            statementSQL = connSQL.createStatement();
            // eksekusi query dan simpan hasilnya di obj ResultSet
            resultSetSQL = statementSQL.executeQuery(sql);
            
            System.out.println("\n| No.|\tID   |\tNama        |\tKunjungan        |\tJudul Buku                 |\tPenulis               |\tKategori Buku        |\tPenerbit      ");
            System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------");
            
            while(resultSetSQL.next()){

                nomor++;

                System.out.printf("| %2d ", nomor);
                System.out.printf("|\t%-5d", resultSetSQL.getInt("ID"));
                System.out.printf("|\t%-10s  ", resultSetSQL.getString("Nama"));
                System.out.printf("|\t%-15s  ", resultSetSQL.getString("Kunjungan"));
                System.out.printf("|\t%-25s  ", resultSetSQL.getString("Judul_Buku"));
                System.out.printf("|\t%-20s  ", resultSetSQL.getString("Penulis"));
                System.out.printf("|\t%-20s ", resultSetSQL.getString("Kategori_Buku"));
                System.out.printf("|\t%-10s ", resultSetSQL.getString("Penerbit"));   
                System.out.println("\n"); 

            }

            System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------");

        }catch(InputMismatchException e){
            String text = "\n===Data yang anda cari tidak ada===\n";
            System.out.println(text.toUpperCase());
        }

    }
           

    // tambah data di perpustakaan
    private static void TambahData() throws SQLException{

        try{

            // input dari user
            Scanner userInput = new Scanner(System.in);

            // database
            int ID;
            String Kunjungan, Nama, Penulis, Judul_Buku, Kategori_Buku, Penerbit;

            // tambah atau input data-data
            System.out.print("\nMasukkan nomor ID pengunjung  : ");
            ID = userInput.nextInt();
            userInput.nextLine();
            System.out.print("\nNama pengunjung               : ");
            Nama = userInput.nextLine();
            System.out.print("\nMasukkan jenis kunjungan      : ");
            Kunjungan = userInput.nextLine();
            System.out.print("\nJudul buku                    : ");
            Judul_Buku = userInput.nextLine();
            System.out.print("\nMasukkan nama penulis buku    : ");
            Penulis = userInput.nextLine();
            System.out.print("\nMasukkan kategori jenis buku  : ");
            Kategori_Buku = userInput.nextLine();
            System.out.print("\nNama nama penerbit buku       : ");
            Penerbit = userInput.nextLine();

            // SQL database
            // query tambah data
            String sql = "INSERT INTO aktivitas (ID, Nama, Kunjungan,  Judul_Buku, Penulis, Kategori_Buku, Penerbit) VALUES ('"+ID+"','"+Nama+"','"+Kunjungan+"','"+Judul_Buku+"','"+Penulis+"','"+Kategori_Buku+"','"+Penerbit+"')";
            // objek statement
            statementSQL = connSQL.createStatement();
            // eksekusi query dan simpan hasilnya di obj ResultSet
            statementSQL.execute(sql);

            String text = "\n===data berhasil di-input===\n";
            System.out.println(text.toUpperCase());

            // tampil 
            System.out.println("\n==Hasil inputan==");
            System.out.println("ID pengunjung   : " + ID);
            System.out.println("Nama pengunjung : " + Nama);
            System.out.println("Jenis kunjungan : " + Kunjungan);
            System.out.println("Penulis buku    : " + Penulis);
            System.out.println("Judul buku      : " + Judul_Buku);
            System.out.println("Kategori buku   : " + Kategori_Buku);
            System.out.println("Penerbit buku   : "+ Penerbit);
        
            // tampilkan data
            System.out.println("\nList Data Terbaru");
            LihatData();

        } catch(InputMismatchException e){
                System.err.println("Input yang anda masukkan salah");
            }

    }


    // hapus data di perpustakaan
    private static void HapusData() throws SQLException{

        try{
            
            // input dari user
            Scanner userInput = new Scanner(System.in);

            // lihat data sebelum dihapus
            System.out.println("Data yang Tersedia Saat Ini");
            LihatData();


            System.out.print("Ketik ID dari data yang akan dihapus : ");
            int ID = Integer.parseInt(userInput.nextLine());

            // SQL database

            // query hapus data
            String sql = "DELETE FROM aktivitas WHERE ID = " +ID;
            //  koneksi ke database
            connSQL = DriverManager.getConnection(url, user, pass);
            // objek statement
            statementSQL = connSQL.createStatement();

            // percabangan -> eksekusi update data 
            if(statementSQL.executeUpdate(sql) > 0){
                System.out.println("\nBerhasil menghapus data kunjungan dengan No.ID "+ID+" ");
            }

            System.out.println("\nTabel Data Saat Ini");
            LihatData();
            
            } catch(SQLException e){
                String text = "\n===terjadi kesalahan dalam menghapus data===\n";
                System.out.println(text.toUpperCase());
            }

    }


    // ubah data di perpustakaan
    private static void UbahData() throws SQLException{
        try{
            
            // input dari user
            Scanner userInput = new Scanner(System.in);

            // lihat data sebelum dihapus
            System.out.println("\nData yang Tersedia");
            LihatData();

            System.out.print("Input ID data yang akan di-update : ");
            int ID = Integer.parseInt(userInput.nextLine());

            // pilih data yang akan di-update
            String sql = "SELECT * FROM aktivitas WHERE ID= " +ID;

             // SQL database
            statementSQL = connSQL.createStatement();
            resultSetSQL = statementSQL.executeQuery(sql);

        
        if(resultSetSQL.next()){

            System.out.print("\nNama pengunjung\t\t\t: ");
            String Nama = userInput.nextLine();
            System.out.print("\nKunjungan\t\t\t: ");
            String Kunjungan = userInput.nextLine();
            System.out.print("\nJudul buku\t\t\t: ");
            String Judul_Buku = userInput.nextLine();
            System.out.print("\nMasukkan nama penulis buku\t: ");
            String Penulis = userInput.nextLine();
            System.out.print("\nMasukkan kategori jenis buku\t: ");
            String Kategori_Buku = userInput.nextLine();
            System.out.print("\nNama nama penerbit buku\t\t: ");
            String Penerbit = userInput.nextLine();
            
            // query ubah data
            sql = "UPDATE aktivitas SET Nama = '"+Nama+"', Kunjungan= '"+Kunjungan+"',  Judul_Buku= '"+Judul_Buku+"', Penulis= '"+Penulis+"', Kategori_Buku= '"+Kategori_Buku+"', Penerbit= '"+Penerbit+"' WHERE ID= '"+ID+"' ";
            //  koneksi ke database
            //connSQL = DriverManager.getConnection(url, user, pass);

            if(statementSQL.executeUpdate(sql) > 0){
                
                String text = "\n===data berhasil diubah===\n";
                System.out.println(text.toUpperCase());

            }
            LihatData();
        }        
        }catch(InputMismatchException e){
            String text = "\n===Terjadi kesalahan! Data tidak dapat diubah===\n";
            System.out.println(text.toUpperCase());
            String text1 = "\n===Data yang akan diubah, tidak ada===\n";
            System.out.println(text1.toUpperCase());
        }

    }


    // pengembalian buku 
    private static void peminjamanBuku(){

        int hari;
        int ID;
        String nama, judulBuku, penulis, penerbit;
        
        int nomor = 0;
        nomor++;

        Scanner userInput = new Scanner(System.in);

        // input
        System.out.print("\nMasukkan nomor ID peminjam       : ");
        ID = userInput.nextInt();
        userInput.nextLine();
        System.out.print("\nMasukkan nama peminjam           : ");
        nama = userInput.nextLine();
        System.out.print("\nMasukkan judul buku              : ");
        judulBuku = userInput.nextLine();
        System.out.print("\nMasukkan penulis buku            : ");
        penulis = userInput.nextLine();
        System.out.print("\nMasukkan penerbit buku           : ");
        penerbit = userInput.nextLine();
        System.out.print("\nMasukkan lama pengembalian buku  : ");
        hari = userInput.nextInt();
        System.out.println("Lama pengembalian buku           : " + hari + " hari");
        


        System.out.println("\n| No.|\tID   |\tNama        |\tJudul Buku               |\tPenulis               |\tPenerbit      |\tPengembalian(hari)     |");
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------");


            // tampilkan
            System.out.printf("| %2d ", nomor);
            System.out.printf("|\t%-4d ", ID);
            System.out.printf("|\t%-10s  ", nama);
            System.out.printf("|\t%-23s  ", judulBuku);
            System.out.printf("|\t%-20s  ", penulis);
            System.out.printf("|\t%-13s ", penerbit);
            System.out.printf("|\t%-22s |", hari );
            System.out.println("\n");
            
            if(hari > 3){
            
                System.out.println("-------Denda-------");
                int denda;
                // operasi aritmetika
                denda = 1000 + ((hari-3) * 500);
                System.out.println(nama + " dikenakan denda sebesar Rp " + denda);
    
            }else{
                System.out.println(nama + "Tidak dikenakan denda");
            }

        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------");
        
    }


}