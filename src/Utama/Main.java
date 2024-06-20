package Utama;

import java.util.ArrayList;
import java.util.Scanner;
import Books.TextBook;
import Data.Admin;
import Data.Student;
import Exception.IllegalAdminAccess;

public class Main {
    public static ArrayList<Student> userlist = new ArrayList<>();
    Main login;
    public static void main(String[] args) {
        
        //objek
        Scanner scan = new Scanner(System.in);
        Admin admin = new Admin();
        Main main = new Main();

        // Student student = new Student();
        userlist.add(new Student("Farhan Fauzi", "202310370311100", "Teknik", "Informatika", "farhan.uzie77@gmail.com"));
        userlist.add(new Student("Ilham Akbar Jamil", "202310370311085", "Teknik", "Informatika", "ilhamakbarjamil8@gmail.com"));

        admin.inputBook(new TextBook("babi", "akbar", "1718381338657-88", "Text", 10));

        boolean running = true;
        while (running){
            try {
                System.out.println("==== Library System");
                System.out.println("1. Login as Admin");
                System.out.println("2. Login as Student");
                System.out.println("3. Exit");
                System.out.print("choose option : ");
                int choose = Integer.parseInt(scan.nextLine());
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
                        throw new IllegalAdminAccess("pilih 1-3");
                }
            } catch (IllegalAdminAccess e) {
                System.out.println(e.getMessage());
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
        }
        scan.close();
    }

    public static boolean checkNim = true;
    public void inputNim(Scanner scan){
        checkNim = true;
        while (checkNim){
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