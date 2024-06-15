package Data;

import java.util.ArrayList;

import Books.Book;

public class Student extends User{

    //objek
    ArrayList<Book> bukuBorrowed = new ArrayList<>();

    // protected String nama;
    // protected String nim;
    // protected String fakultas;
    // protected String jurusan;

    public Student(String nama, String nim, String fakultas, String jurusan){
        super(nama, nim, fakultas, jurusan);
    }

    public Student(){}

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
                case 5:
                    menu = false;
                break;
            
                default:
                    break;
            }
        }
    }


}
