package Data;

import Books.Book;
import Exception.IllegalAdminAccess;
import Utama.Main;
import anjay.sendEmailKembali;
import anjay.sendEmailPinjam;

import java.util.*;

public class Student extends User{

    //objek
    private ArrayList<Book> bukuBorrowed = new ArrayList<>();

    
    // protected String nama;
    // protected String nim;
    // protected String fakultas;
    // protected String jurusan;

    public Student(String nama, String nim, String fakultas, String jurusan, String email){
        super(nama, nim, fakultas, jurusan, email);
    }

    public Student(){}

    //pinjam buku
    public void pinjamBuku(){
            boolean running = true;
            while (running) {
                try {
                    displayBook();
                    System.out.println("Menu Pinjam Buku");
                    System.out.print("Masukkan BookId : ");
                    String BookId = scan.nextLine();
                    for(Book cek : booklist){
                        if(cek.getBookId().equals(BookId)){
                            System.out.print("input jumlah buku yang akan dipinjam : ");
                            int jumlahPinjamBuku = scan.nextInt();
                            scan.nextLine();
                            if(jumlahPinjamBuku > cek.getStock()){
                                System.out.println("melebihi stock yang ada");
                                return;
                            }else{
                                int durasiPinjam;
                                do {
                                    System.out.print("durasi pinjaman (max 14 hari) : ");
                                    durasiPinjam = Integer.parseInt(scan.nextLine());
                                    // scan.nextLine();
                                    if(durasiPinjam > 14){
                                        System.out.println("melebihi batas maksimal");
                                    }else{
                                        cek.kurangStock(jumlahPinjamBuku);
                                        Book bukuBorowed = new Book(cek);
                                        bukuBorowed.setStock(jumlahPinjamBuku);
                                        bukuBorowed.setDurasi(durasiPinjam);
                                        bukuBorrowed.add(bukuBorowed);
                                        bukuBorowed.setTanggalPinjam(new Date());
                                        System.out.println("buku dengan Id "+BookId+" berhasil dipinjam");
                                        sendEmailPinjam.kirimEmail(this);
                                        return;
                                    }
                                } while (durasiPinjam > 14);
                            }
                        }
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }

    }    

    public static int jumlahkembali;
    //kembalikan buku
    public void kembalikanBuku(){
        boolean kembali = true;
        while (kembali) {
            try {
                bukuBorrowed();
                System.out.print("Masukkan Id buku : ");
                String Id = scan.nextLine();
                
                for(Book back : bukuBorrowed){
                    if(back.getBookId().equals(Id)){
                        System.out.print("inputkan jumlah buku yang dikembalikan : ");
                        jumlahkembali = scan.nextInt();
                        scan.nextLine();
    
                        if(jumlahkembali <= back.getStock()){
                            for(Book tambah : booklist){
                                back.setStock(back.getStock() - jumlahkembali);
                                // tambah.tambahStock(jumlahkembali);
                                tambah.setStock(jumlahkembali + tambah.getStock());
                                System.out.println("buku berhasil dikembalikan");
                                sendEmailKembali.kirimEmail(this);
    
                                if(back.getStock() == 0){
                                    bukuBorrowed.remove(back);
                                }

                                Date tanggalKembali = new Date();
                                long selisih = selisihHari(back.getTanggalPinjam(), tanggalKembali);
                                if(selisih > back.getDurasi()){
                                    long keterlambatan = selisih - back.getDurasi();
                                    System.out.println("anda terlambat mengembalikan selama "+keterlambatan+" hari");
                                    beriSanksi(keterlambatan);
                                }
                                return;
                            }
                        } 
                        else{
                            System.out.println("melebihi batas stok yang dipinjam");
                        }
                    }else{
                        System.out.println("BookId '"+Id+"' tidak ditemukan");
                    }
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }                                               
        }
    }

    private long selisihHari(Date tanggalPinjam, Date tanggalKemabali){
        long selisih = tanggalKemabali.getTime() - tanggalPinjam.getTime();
        return selisih / (1000*60*60*24);
    }

    private void beriSanksi(long keterlambatan){
        long denda = 1000;
        long totalDenda = keterlambatan * denda;
        System.out.println("total denda anda : "+totalDenda);
    }
    

    //tampilkan buku yang sedang dipinjam
    public void bukuBorrowed(){
        if(bukuBorrowed.isEmpty()){
            System.out.println("anda belum pinjam buku");
        }else{
            System.out.println("Daftar buku yang sedang dipinjam");
            System.out.println("-----------------------------------------------------------------------------------------------------------------");
            System.out.println("\tJudul\t|\tPenulis\t|\t\tBookId\t\t|\tkategori|\tstock\t|\tdursi\t|");
            System.out.println("-----------------------------------------------------------------------------------------------------------------");
            for(Book display : bukuBorrowed){
                System.out.println("\t"+display.getJudul()+"\t|\t"+display.getAuthor()+"\t|\t"+display.getBookId()+"\t|\t"+display.getCategory()+"\t|\t"+display.getStock()+"\t|\t"+display.getDurasi()+" hari |");
            }
            System.out.println("-----------------------------------------------------------------------------------------------------------------");
        }
    }

    public ArrayList<Book> getBukuBorrowed() {
        return bukuBorrowed;
    }

    @Override
    public void menu() {
        boolean menu = true;
        while (menu) {
            try {
                System.out.println("=== Menu Student");
                System.out.println("1. Tampilkan informasi");
                System.out.println("2. Tampilkan buku yang dipinjam");
                System.out.println("3. Pinjam buku");
                System.out.println("4. Kembalikan buku");
                System.out.println("5. Logout");
                System.out.print("choose option : ");
                int choose = Integer.parseInt(scan.nextLine());
                // scan.nextLine();
                switch (choose) {
                    case 1:
                        displayInformation();
                    break;
                    case 2:
                        bukuBorrowed();
                    break;
                    case 3:
                        pinjamBuku();
                    break;
                    case 4:
                        kembalikanBuku();
                    break;
                    case 5:
                        menu = false;
                        Main.checkNim = false;
                    break;
                
                    default:
                        // System.out.println("pilih yang sesuai");
                        throw new IllegalAdminAccess("pilih yang sesuai");
                }
            } catch (IllegalAdminAccess e) {
                System.out.println(e.getMessage());
            } catch(Exception e){
                System.out.println(e.getMessage());
            }
        }
    }
}