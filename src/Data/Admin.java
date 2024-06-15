package Data;

// import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

// import Books.Book;
import Books.HistoryBook;
import Books.StoryBook;
import Books.TextBook;
import Utama.Main;

public class Admin extends User{
    private String USERNAME = "admin";
    private String PASSWORD = "admin123";

    public Admin(String name, String nim, String faculty, String program){
        super(name, nim, faculty, program);
    }

    public Admin(){}
    
    Scanner scan = new Scanner(System.in);
    
    //login
    boolean login = true;
    public void login(){
        login = true;
        while (login) {
            System.out.print("Username : ");
            String username = scan.nextLine();
            System.out.print("Password : ");
            String password = scan.nextLine();
            if(isAdmin(username, password)){
                menu();
            }else{
                System.out.println("username dan password invalid");
            }
        }
    }


    //menu
    @Override
    public void menu() {
        boolean running = true;
        while (running) {
            System.out.println("==== Admin Menu");
            System.out.println("1. Tambah Student");
            System.out.println("2. Tambah Buku");
            System.out.println("3. Tampilkan Student");
            System.out.println("4. Tampilkan Buku");
            System.out.println("5. Logout");
            System.out.print("choose Option : ");
            int choose = scan.nextInt();
            scan.nextLine();
            switch (choose) {
                case 1:
                    tambahStudent();
                break;
                case 2:
                    inputBook();
                break;
                case 3:
                    displayStudent();
                break;
                case 4:
                    displayBook();
                break;                
                case 5:
                    login = false;
                    running = false;
                break;
                default:
                    break;
            }
        }
    }

    //1. tambah student
    public void tambahStudent(){
        boolean tambah = true;
        while (tambah) {
            //nama--
            String nama;
            do{
                System.out.print("Nama : ");
                nama = scan.nextLine();
                if(!nama.matches("^[a-zA-Z\\s]+$")){
                    System.out.println("Nama harus menggunakan 'huruf'");
                }
            }while(!nama.matches("^[a-zA-Z\\s]+$"));

            //nim--
            String nim;
            boolean same;
            do {
                System.out.print("Nim : ");
                nim = scan.nextLine();
                same = false;
                if(!nim.matches("^[0-9]+$")){
                    System.out.println("Nim harus menggunakan 'angka'");
                }else if(nim.length() != 15){
                    System.out.println("Nim harus 15 karakter");
                }else{
                    for(Student cek : Main.userlist){
                        if(cek.getNim().equals(nim)){
                            System.out.println("Nim sudah ada");
                            same = true;
                            break;
                        }
                    }
                }
            } while(!nim.matches("^[0-9]+$") || nim.length() != 15 || same);

            //fakultas--
            String fakultas;
            do {
                System.out.print("Fakultas : ");
                fakultas = scan.nextLine();
                if(!fakultas.matches("^[a-zA-Z\\s]+$")){
                    System.out.println("Harus menggunakan 'huruf'");
                }
            } while(!fakultas.matches("^[a-zA-Z\\s]+$"));
            
            //jurusan--
            String jurusan;
            do {
                System.out.print("Jurusan : ");
                jurusan = scan.nextLine();
                if(!jurusan.matches("^[a-zA-Z\\s]+$")){
                    System.out.println("Harus menggunakan 'huruf'");
                }
            } while(!jurusan.matches("^[a-zA-Z\\s]+$"));
            
            Student student = new Student(nama, nim, fakultas, jurusan);
            Main.userlist.add(student);
            break;
        }
    }

    public void tambahStudent(Student student){
        Main.userlist.add(student);
    }

    //3. tampilkan student
    public void displayStudent(){
        for(Student display : Main.userlist){
            System.out.println("Nama\t: "+display.getNama());
            System.out.println("Nim\t: "+display.getNim());
            System.out.println("Fakultas: "+display.getFakultas());
            System.out.println("Jurusan : "+display.getJurusan());
            System.out.println("");
        }
    }

    //2. input book
    public void inputBook(TextBook book){
        booklist.add(book);
    }

    public void inputBook(StoryBook book){
        booklist.add(book);
    }

    public void inputBook(HistoryBook book){
        booklist.add(book);
    }

    public void inputBook(){
        boolean input = true;
        while (input) {
            System.out.println("choose Kategori");
            System.out.println("1. Story Book");
            System.out.println("2. History Book");
            System.out.println("3. Text Book");
            System.out.print("choose Kategori : ");
            int choose = scan.nextInt();
            scan.nextLine();
            String kategori = "";
            switch (choose) {
                case 1:
                    kategori = "Story";
                break;
                case 2:
                    kategori = "History";
                break;
                case 3:
                    kategori = "Text";
                break;
                default:
                    System.out.println("invalid option");
            }
            System.out.print("Masukkan judul : ");
            String judul = scan.nextLine();
            System.out.print("Masukkan penulis : ");
            String penulis = scan.nextLine();
            System.out.print("Masukkan stock : ");
            int stock = scan.nextInt();
            scan.nextLine();

            String bookId = generated();
            switch (choose) {
                case 1:
                    booklist.add(new StoryBook(judul, penulis, bookId, kategori, stock));
                    input = false;
                break;
                case 2:
                    booklist.add(new HistoryBook(judul, penulis, bookId, kategori, stock));
                    input = false;
                break;
                case 3:
                    booklist.add(new TextBook(judul, penulis, bookId, kategori, stock));
                    input = false;
                break;
                default:
                    System.out.println("invalid option");
                    break;
            }
        }
        System.out.println("berhasil ditambahkan");
    }

    //display book
    // public void displayBook(){
    //     System.out.println("Booklist");
    //     System.out.println("-------------------------------------------------------------------------------------------------");
    //     System.out.println("\tJudul\t|\tPenulis\t|\t\tBookId\t\t|\tkategori|\tstock\t|");
    //     System.out.println("-------------------------------------------------------------------------------------------------");
    //     for(Book display : booklist){
    //         System.out.println("\t"+display.getJudul()+"\t|\t"+display.getAuthor()+"\t|\t"+display.getBookId()+"\t|\t"+display.getCategory()+"\t|\t"+display.getStock()+"\t|");
    //     }
    //     System.out.println("-------------------------------------------------------------------------------------------------");
    // }


    //generated Id
    public static String generated(){
        long waktu = System.currentTimeMillis();
        int random = new Random().nextInt(1000);
        return waktu+"-"+random;
    }

    //isAdmin
    public boolean isAdmin(String username, String password){
        if(username.equals(USERNAME) && password.equals(PASSWORD)){
            return true;
        }else{
            // System.out.println("username dan password invalid");
            return false;
        }
    }

}
