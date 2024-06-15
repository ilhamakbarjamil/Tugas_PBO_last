package Utama;

import java.util.ArrayList;
import java.util.Scanner;
import Books.TextBook;
import Data.Admin;
import Data.Student;

public class Main {
    public static ArrayList<Student> userlist = new ArrayList<>();
    Main login;
    public static void main(String[] args) {

        //objek
        Scanner scan = new Scanner(System.in);
        Admin admin = new Admin();
        Main main = new Main();
        // Student student = new Student();

        userlist.add(new Student("Ilham Akbar Jamil", "202310370311085", "Teknik", "Informatika"));
        userlist.add(new Student("Farhan Fauzi", "202310370311100", "Teknik", "Informatika"));
        userlist.add(new Student("Royhan Azizi Roji", "202310370311067", "Teknik", "Informatika"));

        admin.inputBook(new TextBook("babi", "akbar", "1718381338657-88", "Text", 10));

        boolean running = true;
        while (running) {
            System.out.println("==== Library System");
            System.out.println("1. Login as Admin");
            System.out.println("2. Login as Student");
            System.out.println("3. Exit");
            System.out.print("choose option : ");
            int choose = scan.nextInt();
            scan.nextLine();
            switch (choose) {
                case 1:
                    admin.login();
                break;
                case 2:
                    main.inputNim(scan);
                break;
                case 3:
                    System.out.println("Terima kasih");
                    running = false;
                break;
                default:
                    System.out.println("choose 1-4 !!");
                break;
            }
        }

        scan.close();
    }

    public void inputNim(Scanner scan){
        boolean checkNim = true;
        while (checkNim) {
            System.out.print("Masukkan Nim(99 = back) : ");
            String nim = scan.nextLine();
            if(nim.equalsIgnoreCase("99")){
                checkNim = false;
            }
            for(Student cek : userlist){
                if(cek.getNim().equals(nim)){
                    System.out.println("Welcome "+cek.getNama());
                    cek.menu();
                }
            }
        }
    }
}