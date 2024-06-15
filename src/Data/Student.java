package Data;

import java.util.ArrayList;

import Books.Book;

public class Student extends User{

    //objek
    private ArrayList<Book> bukuBorrowed = new ArrayList<>();

    // protected String nama;
    // protected String nim;
    // protected String fakultas;
    // protected String jurusan;

    public Student(String nama, String nim, String fakultas, String jurusan){
        super(nama, nim, fakultas, jurusan);
    }

    //pinjam buku
    public void pinjamBuku(){
        boolean running = true;
        while (running) {
            displayBook();
            System.out.println("Menu Pinjam Buku");
            System.out.print("Masukkan BookId : ");
            String BookId = scan.nextLine();
            for(Book cek : booklist){
                if(cek.getBookId().equals(BookId)){
                    System.out.print("input jumlah buku yang akan dipinjam : ");
                    int jumlahPinjamBuku = scan.nextInt();
                    scan.nextLine();
                    if(cek.getStock() > 0){
                        int durasiPinjam;
                        do {
                            System.out.print("durasi pinjaman (max 14 hari) : ");
                            durasiPinjam = scan.nextInt();
                            scan.nextLine();
                            if(durasiPinjam > 14){
                                System.out.println("melebihi batas maksimal");
                            }else{
                                cek.kurangStock(jumlahPinjamBuku);
                                Book bukuBorowed = new Book(cek);
                                bukuBorowed.setStock(jumlahPinjamBuku);
                                bukuBorowed.setDurasi(durasiPinjam);
                                bukuBorrowed.add(bukuBorowed);
                                System.out.println("buku dengan Id "+BookId+" berhasil dipinjam");
                                return;
                            }
                        } while (durasiPinjam > 14);
                    }else{
                        System.out.println("Melebihi stock yang ada");
                    }
                }
            }
        }
    }    

    //kembalikan buku
    public void kembalikanBuku(){
        boolean kembali = true;
        while (kembali) {
            bukuBorrowed();
            System.out.print("Masukkan Id buku : ");
            String Id = scan.nextLine();
            
            for(Book back : bukuBorrowed){
                if(back.getBookId().equals(Id)){
                    System.out.print("inputkan jumlah buku yang dikembalikan : ");
                    int jumlahkembali = scan.nextInt();
                    scan.nextLine();
                    if(jumlahkembali <= back.getStock()){
                        for(Book tambah : booklist){
                            tambah.tambahStock(jumlahkembali);
                            bukuBorrowed.remove(back);
                            System.out.println("buku berhasil dikembalikan");
                            return;
                        }
                        
                    }else{
                        System.out.println("melebihi batas stok yang dipinjam");
                    }
                }else{
                    System.out.println("BookId '"+Id+"' tidak ditemukan");
                }
            }
        }
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

    @Override
    public void menu() {
        boolean menu = true;
        while (menu) {
            System.out.println("=== Menu Student");
            System.out.println("1. Tampilkan informasi");
            System.out.println("2. Tampilkan buku yang dipinjam");
            System.out.println("3. Pinjam buku");
            System.out.println("4. Kembalikan buku");
            System.out.println("5. Logout");
            System.out.print("choose option : ");
            int choose = scan.nextInt();
            scan.nextLine();
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
                break;
            
                default:
                    System.out.println("pilih yang sesuai");
                    break;
            }
        }
    }
}