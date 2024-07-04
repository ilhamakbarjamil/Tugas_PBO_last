package Utama;

import java.util.ArrayList;
import java.util.Scanner;

import Books.TextBook;
import Data.Admin;
import Data.Student;
import Exception.IllegalAdminAccess;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application{
    public static ArrayList<Student> userlist = new ArrayList<>();
    Admin admin = new Admin();
    public static boolean checkNim = true;
    Main login;
    public static void main(String[] args) {
        launch(args);
        //objek
        Scanner scan = new Scanner(System.in);
        Admin admin = new Admin();
        Main main = new Main();

        // Student student = new Student();
        userlist.add(new Student("Farhan Fauzi", "202310370311100", "Teknik", "Informatika", "farhan.uzie77@gmail.com"));
        userlist.add(new Student("Ilham Akbar Jamil", "202310370311085", "Teknik", "Informatika", "ilhamakbarjamil8@gmail.com"));

        // admin.inputBook(new TextBook("babi", "akbar", "1718381338657-88", "Text", 10));

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
                        // admin.login();
                        admin.menu();
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


    public void inputNim(Scanner scan){
        checkNim = true;
        while (checkNim){
            try {
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
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
    
    

    @Override
    public void start(Stage arg0) throws Exception {
        userlist.add(new Student("Farhan Fauzi", "202310370311100", "Teknik", "Informatika", "farhan.uzie77@gmail.com"));
        userlist.add(new Student("Ilham Akbar Jamil", "202310370311085", "Teknik", "Informatika", "ilhamakbarjamil8@gmail.com"));
        admin.inputBook(new TextBook("futsal", "akbar", "1718381338657-88", "Text", 10));

       Mainmenu(arg0);
    }

    public void Mainmenu(Stage stage){
        Label label = new Label("Library Menu");
       
        
        Button adminBtn = new Button("Login as Admin");
        Button studentBtn = new Button("Login as Student");
        Button exitBtn = new Button("Exit");

        double setbuttonwidth = 150;
        adminBtn.setMinWidth(setbuttonwidth);
        studentBtn.setMinWidth(setbuttonwidth);
        exitBtn.setMinWidth(setbuttonwidth);

        adminBtn.setOnAction(e -> loginAdmin(stage));
        studentBtn.setOnAction(e -> checkNim(stage));
        exitBtn.setOnAction(e -> System.exit(0));

        Label output = new Label();
        VBox vbox = new VBox(label,adminBtn, studentBtn, exitBtn, output);
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(15);
        vbox.setAlignment(Pos.CENTER);

        Scene scene = new Scene(vbox, 300, 300);

        Image icon = new Image(getClass().getResourceAsStream("/Logo/Logo2.png"), 50,50,true,true);
        stage.getIcons().add(icon);
        stage.setTitle("Perpustakaan");
        stage.setScene(scene);
        stage.show();
    }

    public void loginAdmin(Stage stage){
        Label username = new Label("Username");
        TextField usernameField = new TextField();
        usernameField.setPromptText("masukkan username");

        Label password = new Label("Password");
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("masukkan password");

        Button submitBtn = new Button("Submit");
        double setwitdh = 100;
        submitBtn.setMinWidth(setwitdh);

        submitBtn.setOnAction(event ->{
            String user = usernameField.getText();
            String pass = passwordField.getText();
            if(user.equalsIgnoreCase("1") && pass.equalsIgnoreCase("1")){
                admin.adminMenu(stage);
            }else{
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Tidak Sesuai");
                alert.setHeaderText("username and password invalid");
                alert.setContentText("silahkan coba lagi");
                alert.showAndWait();
            }
        });

        VBox submit = new VBox(submitBtn);
        submit.setAlignment(Pos.CENTER);
        VBox vbox = new VBox(10, username,usernameField,password,passwordField,submit);
        vbox.setPadding(new Insets(15));
        Scene scene = new Scene(vbox, 400, 400);
        stage.setTitle("Login Admin");
        stage.setScene(scene);
        stage.show();
    }

    private void checkNim(Stage stage){
        Label label = new Label("Masukkan Nim : ");
        TextField nimField = new TextField();
        nimField.setPromptText("Masukkan nim");

        Button submitBtn = new Button("Submit");
        Button backBtn = new Button("Back");

        double setminwidth = 70;
        submitBtn.setMinWidth(setminwidth);
        backBtn.setMinWidth(setminwidth);
        
        submitBtn.setOnAction(event->{
            String nim = nimField.getText();
            boolean found = true;
            for(Student cek : userlist){
                
                if(cek.getNim().equals(nim)){
                    found = true;
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Login berhasil");
                    alert.setHeaderText("Welcome "+cek.getNama());
                    // alert.setContentText(cek.getNama());
                    alert.showAndWait();
                    cek.menuStudent(stage);
                }
            }
            if(!found){
                found = false;
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Login gagal");
                alert.setHeaderText("Nim tidak ditemukan");
                alert.setContentText("silahkan coba lagi");
                alert.showAndWait();
            }
            nimField.clear();
            
        });

        backBtn.setOnAction(event->{
            Mainmenu(stage);
        });

        HBox hbox = new HBox(5,submitBtn,backBtn);
        hbox.setAlignment(Pos.CENTER);
        VBox vbox = new VBox(15,label,nimField,hbox);
        vbox.setPadding(new Insets(15));
        Scene scene = new Scene(vbox, 400, 400);
        stage.setTitle("Login student");
        stage.setScene(scene);
        stage.show();
    }
}