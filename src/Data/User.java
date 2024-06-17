package Data;

import java.util.ArrayList;
import java.util.Scanner;

import Books.Book;
// import Utama.Main;
import Util.iMenu;

public class User implements iMenu{
    
    //objek
    protected static ArrayList <Book> booklist = new ArrayList<>();
    Scanner scan = new Scanner(System.in);

    protected String nama;
    protected String nim;
    protected String fakultas;
    protected String jurusan;

    public User(String nama, String nim, String fakultas, String jurusan){
        this.nama = nama;
        this.nim = nim;
        this.fakultas = fakultas;
        this.jurusan = jurusan;
    }

    public User(){}

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public void setFakultas(String fakultas) {
        this.fakultas = fakultas;
    }

    public void setJurusan(String jurusan) {
        this.jurusan = jurusan;
    }

    public String getNama() {
        return nama;
    }

    public String getNim() {
        return nim;
    }

    public String getFakultas() {
        return fakultas;
    }

    public String getJurusan() {
        return jurusan;
    }


    //display Book
    public void displayBook(){
        System.out.println("Booklist");
        System.out.println("-------------------------------------------------------------------------------------------------");
        System.out.println("\tJudul\t|\tPenulis\t|\t\tBookId\t\t|\tkategori|\tstock\t|");
        System.out.println("-------------------------------------------------------------------------------------------------");
        for(Book display : booklist){
            System.out.println("\t"+display.getJudul()+"\t|\t"+display.getAuthor()+"\t|\t"+display.getBookId()+"\t|\t"+display.getCategory()+"\t|\t"+display.getStock()+"\t|");
        }
        System.out.println("-------------------------------------------------------------------------------------------------");
    }


    //display information
    public void displayInformation(){
        if(nama == null || nim == null || fakultas == null || jurusan == null){
            System.out.println("daftarkan dulu");
        }else{
            System.out.println("----------------------------------");
            System.out.println("User Yang Login");
            System.out.println("----------------------------------");
            System.out.println("Nama\t: "+nama);
            System.out.println("Nim\t: "+nim);
            System.out.println("Fakultas: "+fakultas);
            System.out.println("Jurusan : "+jurusan);
            System.out.println("----------------------------------");
        }
    }
    
    
    

    @Override
    public void menu() {
        boolean menu = true;
        while (menu) {
            System.out.println("=== Menu User");
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
                case 5:
                    menu = false;
                break;
                default:
                    break;
            }
        }
    }
}
