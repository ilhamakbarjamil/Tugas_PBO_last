package Data;

// import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
// import java.util.ResourceBundle.Control;

import Books.Book;
// import Books.Book;
import Books.HistoryBook;
import Books.StoryBook;
import Books.TextBook;
import Exception.IllegalAdminAccess;
import Utama.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
// import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
// import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
// import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Admin extends User{
    public static void main(String[] args) {
        launch(args);
    }
    private String USERNAME = "admin";
    private String PASSWORD = "admin";

    public Admin(String name, String nim, String faculty, String program, String email){
        super(name, nim, faculty, program, email);
    }

    public Admin(){}
    
    Scanner scan = new Scanner(System.in);

    //login
    boolean login = true;
    public void login(){
        login = true;
        while (login) {
            try {
                System.out.print("Username : ");
                String username = scan.nextLine();
                System.out.print("Password : ");
                String password = scan.nextLine();
                if(isAdmin(username, password)){
                    menu();
                }else{
                    System.out.println("username dan password invalid");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    //menu
    @Override
    public void menu() {
        boolean running = true;
        while (running) {
            try {
                System.out.println("==== Admin Menu");
                System.out.println("1. Tambah Student");
                System.out.println("2. Tambah Buku");
                System.out.println("3. Tampilkan Student");
                System.out.println("4. Tampilkan Buku");
                System.out.println("5. Logout");
                System.out.print("choose Option : ");
                int choose = Integer.parseInt(scan.nextLine());
                // scan.nextLine();
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
                        throw new IllegalAdminAccess("pilih 1-5");
                }
            } catch (IllegalAdminAccess e) {
               System.out.println(e.getMessage());
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
        }
    }

    //1. tambah student
    public void tambahStudent(){
        boolean tambah = true;
        while (tambah) {
            try {
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

            //email
            boolean input = true;
            while(input){
                System.out.print("Input Email anda : ");
                email = scan.nextLine();
                if(email.contains("@gmail.com")){
                    input = false;
                }else{
                    System.out.println("harus mengandung '@gmail.com'");
                }
            }

            
            Student student = new Student(nama, nim, fakultas, jurusan, email);
            Main.userlist.add(student);
            break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
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
            System.out.println("Email\t: "+display.getEmail());
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
            try {
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
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println("berhasil ditambahkan");
    }

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

    @Override
    public void start(Stage arg0) throws Exception {
        adminMenu(arg0);
    }

    public void adminMenu(Stage stage){
        Label label = new Label("Admin Menu");
        Button addStudentBtn = new Button("add Student");
        Button addbookBtn = new Button("add Book");
        Button cekStudentBtn = new Button("cek daftar Student");
        Button cekBookBtn = new Button("cek daftar Buku");
        Button backButton = new Button("back");

        addStudentBtn.setOnAction(event->{
            addStudent(stage);
        });

        addbookBtn.setOnAction(event->{
            addBook(stage);
        });

        cekStudentBtn.setOnAction(event->{
            displaystudent(stage);
        });

        cekBookBtn.setOnAction(event->{
            displayBooks(stage);
        });

        backButton.setOnAction(event->{
            Main main = new Main();
            main.Mainmenu(stage);
        });

        double setwidth = 150;
        addStudentBtn.setMinWidth(setwidth);
        addbookBtn.setMinWidth(setwidth);
        cekStudentBtn.setMinWidth(setwidth);
        cekBookBtn.setMinWidth(setwidth);
        backButton.setMinWidth(setwidth);

        VBox vbox = new VBox(10,label,addStudentBtn,addbookBtn,cekStudentBtn,cekBookBtn, backButton);
        vbox.setAlignment(Pos.CENTER);
        Scene scene = new Scene(vbox, 400, 400);
        stage.setTitle("Admin menu");
        stage.setScene(scene);
        stage.show();
    }

    public void addStudent(Stage stage){
        // Label label = new Label("Menu Tambah Student");
        Label namaLabel = new Label("Masukkan Nama");
        TextField namaField = new TextField();
        namaField.setPromptText("masukkan nama mahasiswa");

        Label nimLabel = new Label("Masukkan Nim");
        TextField nimField = new TextField();
        nimField.setPromptText("masukkan nim mahasiswa");

        Label fakultasLabel = new Label("Masukkan fakultas");
        TextField fakultasField = new TextField();
        fakultasField.setPromptText("masukkan fakultas mahasiswa");

        Label jurusanLabel = new Label("Masukkan Jurusan");
        TextField jurusanField = new TextField();
        jurusanField.setPromptText("masukkan jurusan mahasiswa");

        Label emaiLabel = new Label("Masukkan email");
        TextField emailField = new TextField();
        emailField.setPromptText("masukkan email mahasiswa");

        Button submitBtn = new Button("Submit");
        Button backBtn = new Button("back");

        submitBtn.setOnAction(event ->{
            String nama = namaField.getText();
            String nim = nimField.getText();
            String fakultas = fakultasField.getText();
            String jurusan = jurusanField.getText();
            String email = emailField.getText();

            boolean isValid = true;

            if(!nama.matches("^[a-zA-Z\\s]+$")){
                isValid = false;
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Nama invlid");
                alert.setHeaderText("Nama tidak boleh angka");
                alert.show();
            }
            if(nim.length() != 15){
                isValid = false;
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Nim invalid");
                alert.setHeaderText("Nim tidak 15 karakter");
                alert.show();
            }
            for(Student cek : Main.userlist){
                if(cek.getNim().equals(nim)){
                    isValid = false;
                    Alert alertnim = new Alert(AlertType.WARNING);
                    alertnim.setHeaderText("Nim sudah ada");
                    alertnim.show();
                }
            }
            if(!fakultas.matches("^[a-zA-Z\\s]+$")){
                isValid = false;
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Fakultas invlid");
                alert.setHeaderText("fakultas tidak boleh angka");
                alert.show();
            }
            if(!jurusan.matches("^[a-zA-Z\\s]+$")){
                isValid = false;
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Jurusan invlid");
                alert.setHeaderText("jurusan tidak boleh angka");
                alert.show();
            }
            if(!email.contains("@gmail.com")){
                isValid = false;
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Email invalid");
                alert.setHeaderText("email tidak sesuai");
                alert.setContentText("harus ada @gmail.com");
                alert.showAndWait();
            }
            if(isValid){
                Student student = new Student(nama, nim, fakultas, jurusan, email);
                Main.userlist.add(student);
    
                namaField.clear();
                nimField.clear();
                fakultasField.clear();
                jurusanField.clear();
                emailField.clear();
            }
        });

        backBtn.setOnAction(event->{
            adminMenu(stage);
        });

        HBox hbox = new HBox(5,submitBtn,backBtn);

        VBox vbox = new VBox(10,namaLabel,namaField,nimLabel,nimField,fakultasLabel,fakultasField,jurusanLabel,jurusanField,emaiLabel,emailField, hbox);
        vbox.setPadding(new Insets(15));
        Scene scene = new Scene(vbox, 350, 400);
        stage.setTitle("Input data");
        stage.setScene(scene);
        stage.show();
    }

    public void addBook(Stage stage) {
        Label kategoriLabel = new Label("Kategori (story/history/text) :");
        Button storyButton = new Button("story");
        Button historyButton = new Button("history");
        Button textButton = new Button("text");

        Button backButton = new Button("back");

        double setminwidth = 100;
        storyButton.setMinWidth(setminwidth);
        historyButton.setMinWidth(setminwidth);
        textButton.setMinWidth(setminwidth);
        backButton.setMinWidth(setminwidth);


        storyButton.setOnAction(event->{
            addBookStory(stage);
        });

        historyButton.setOnAction(event->{
            addBookHistory(stage);
        });

        textButton.setOnAction(event->{
            addBookText(stage);
        });

        backButton.setOnAction(event->{
            adminMenu(stage);
        });

        HBox hBoxButton = new HBox(8,storyButton,historyButton,textButton);
        hBoxButton.setAlignment(Pos.CENTER);
        HBox hbox = new HBox(backButton);
        hbox.setAlignment(Pos.CENTER);
        VBox vbox = new VBox(10,kategoriLabel,hBoxButton,hbox);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(15));
        Scene scene = new Scene(vbox,400,400);
        stage.setTitle("Pilih kategori");
        stage.setScene(scene);
        stage.show();
    }

    public void addBookStory(Stage stage){
        try {
            Label judulLabel = new Label("Judul :");
            TextField judulField = new TextField();
            Label penulisLabel = new Label("Penulis :");
            TextField penulisField = new TextField();
            Label bookIdLabel = new Label("BookId");
            TextField bookIdField = new TextField();
            bookIdField.setEditable(false);
            Label kategoriLabel = new Label("Kategori");
            TextField kategoriField = new TextField();
            kategoriField.setEditable(false);
            Label jumlahLabel = new Label("Jumlah :");
            TextField jumlahField = new TextField();
    
            bookIdField.setText(generated());
            kategoriField.setText("Story");
    
            Button submitButton = new Button("submit");
            Button exitButton = new Button("exit");
    
            submitButton.setOnAction(event->{
                String judul = judulField.getText();
                String penulis = penulisField.getText();
                String bookId = bookIdField.getText();
                String kategoriStory = kategoriField.getText();
                int jumlah = Integer.parseInt(jumlahField.getText());
    
                boolean isvalid = true;
                if(judul.isEmpty()){
                    isvalid = false;
                    Alert judulAlert = new Alert(AlertType.WARNING);
                    judulAlert.setHeaderText("Judul tidak boleh kosong");
                    judulAlert.showAndWait();
                }
                if(penulis.isEmpty()){
                    isvalid = false;
                    Alert alert = new Alert(AlertType.WARNING);
                    alert.setHeaderText("Penulis tidak boleh kosong");
                    alert.showAndWait();
                }
                if(jumlah <= 0){
                    isvalid = false;
                    Alert alert = new Alert(AlertType.WARNING);
                    alert.setHeaderText("Jumlah tidak boleh kosong <= 0");
                    alert.showAndWait();
                }
    
                if(isvalid){
                    Book bookstory = new Book(judul, penulis, bookId, kategoriStory, jumlah);
                    booklist.add(bookstory);
                    judulField.clear();
                    penulisField.clear();
                    bookIdField.setText(generated());
                    jumlahField.clear();
                }
            });
    
            exitButton.setOnAction(event->{
                addBook(stage);
            });
            
            HBox hBox = new HBox(8,submitButton,exitButton);
            VBox vBox = new VBox(10,judulLabel,judulField,penulisLabel,penulisField,bookIdLabel,bookIdField,kategoriLabel,kategoriField,jumlahLabel,jumlahField,hBox);
            vBox.setPadding(new Insets(15));
            Scene scene = new Scene(vBox,300,400);
            stage.setTitle("Tambah Buku Story");
            stage.setScene(scene);
            stage.show();
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Masukkan angka pada jumlah");
            alert.showAndWait();
        } catch(Exception error){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Sepertinya ada yang salah");
            alert.showAndWait();
        }
    }


    public void addBookHistory(Stage stage){
        Label judulLabel = new Label("Judul :");
        TextField judulField = new TextField();
        Label penulisLabel = new Label("Penulis :");
        TextField penulisField = new TextField();
        Label bookIdLabel = new Label("BookId");
        TextField bookIdField = new TextField();
        bookIdField.setEditable(false);
        Label kategoriLabel = new Label("Kategori");
        TextField kategoriField = new TextField();
        kategoriField.setEditable(false);
        Label jumlahLabel = new Label("Jumlah :");
        TextField jumlahField = new TextField();

        bookIdField.setText(generated());
        kategoriField.setText("History");

        Button submitButton = new Button("submit");
        Button exitButton = new Button("exit");

        submitButton.setOnAction(event->{
            String judul = judulField.getText();
            String penulis = penulisField.getText();
            String bookId = bookIdField.getText();
            String kategoriStory = kategoriField.getText();
            int jumlah = Integer.parseInt(jumlahField.getText());

            boolean isvalid = true;
            if(judul.isEmpty()){
                isvalid = false;
                Alert judulAlert = new Alert(AlertType.WARNING);
                judulAlert.setHeaderText("Judul tidak boleh kosong");
                judulAlert.showAndWait();
            }
            if(penulis.isEmpty()){
                isvalid = false;
                Alert alert = new Alert(AlertType.WARNING);
                alert.setHeaderText("Penulis tidak boleh kosong");
                alert.showAndWait();
            }
            if(jumlah <= 0){
                isvalid = false;
                Alert alert = new Alert(AlertType.WARNING);
                alert.setHeaderText("Jumlah tidak boleh kosong <= 0");
                alert.showAndWait();
            }

            if(isvalid){
                Book bookstory = new Book(judul, penulis, bookId, kategoriStory, jumlah);
                booklist.add(bookstory);
                judulField.clear();
                penulisField.clear();
                bookIdField.setText(generated());
                jumlahField.clear();
            }
        });

        exitButton.setOnAction(event->{
            addBook(stage);
        });
        
        HBox hBox = new HBox(8,submitButton,exitButton);
        VBox vBox = new VBox(10,judulLabel,judulField,penulisLabel,penulisField,bookIdLabel,bookIdField,kategoriLabel,kategoriField,jumlahLabel,jumlahField,hBox);
        vBox.setPadding(new Insets(15));
        Scene scene = new Scene(vBox,300,400);
        stage.setTitle("Tambah Buku History");
        stage.setScene(scene);
        stage.show();
    }

    public void addBookText(Stage stage){
        Label judulLabel = new Label("Judul :");
        TextField judulField = new TextField();
        Label penulisLabel = new Label("Penulis :");
        TextField penulisField = new TextField();
        Label bookIdLabel = new Label("BookId");
        TextField bookIdField = new TextField();
        bookIdField.setEditable(false);
        Label kategoriLabel = new Label("Kategori");
        TextField kategoriField = new TextField();
        kategoriField.setEditable(false);
        Label jumlahLabel = new Label("Jumlah :");
        TextField jumlahField = new TextField();

        bookIdField.setText(generated());
        kategoriField.setText("Text");

        Button submitButton = new Button("submit");
        Button exitButton = new Button("exit");

        submitButton.setOnAction(event->{
            String judul = judulField.getText();
            String penulis = penulisField.getText();
            String bookId = bookIdField.getText();
            String kategoriStory = kategoriField.getText();
            int jumlah = Integer.parseInt(jumlahField.getText());

            boolean isvalid = true;
            if(judul.isEmpty()){
                isvalid = false;
                Alert judulAlert = new Alert(AlertType.WARNING);
                judulAlert.setHeaderText("Judul tidak boleh kosong");
                judulAlert.showAndWait();
            }
            if(penulis.isEmpty()){
                isvalid = false;
                Alert alert = new Alert(AlertType.WARNING);
                alert.setHeaderText("Penulis tidak boleh kosong");
                alert.showAndWait();
            }
            if(jumlah <= 0){
                isvalid = false;
                Alert alert = new Alert(AlertType.WARNING);
                alert.setHeaderText("Jumlah tidak boleh kosong <= 0");
                alert.showAndWait();
            }

            if(isvalid){
                Book bookstory = new Book(judul, penulis, bookId, kategoriStory, jumlah);
                booklist.add(bookstory);
                judulField.clear();
                penulisField.clear();
                bookIdField.setText(generated());
                jumlahField.clear();
            }
        });

        exitButton.setOnAction(event->{
            addBook(stage);
        });
        
        HBox hBox = new HBox(8,submitButton,exitButton);
        VBox vBox = new VBox(10,judulLabel,judulField,penulisLabel,penulisField,bookIdLabel,bookIdField,kategoriLabel,kategoriField,jumlahLabel,jumlahField,hBox);
        vBox.setPadding(new Insets(15));
        Scene scene = new Scene(vBox,300,400);
        stage.setTitle("Tambah Buku text");
        stage.setScene(scene);
        stage.show();
    }

    public void displaystudent(Stage stage){
        TableView<Student> table = new TableView<>();

        // TableColumn<Student, Number> nomerColumn = new TableColumn<>("No");
        // nomerColumn.setCellValueFactory(new PropertyValueFactory<>("nomerUrut"));

        TableColumn<Student, String > namaColumn = new TableColumn<>("Nama");
        namaColumn.setCellValueFactory(new PropertyValueFactory<>("nama"));

        TableColumn<Student, String> nimColumn = new TableColumn<>("Nim");
        nimColumn.setCellValueFactory(new PropertyValueFactory<>("nim"));

        TableColumn<Student, String> fakultasColumn = new TableColumn<>("Fakultas");
        fakultasColumn.setCellValueFactory(new PropertyValueFactory<>("fakultas"));

        TableColumn<Student, String> jurusanColumn = new TableColumn<>("Jurusan");
        jurusanColumn.setCellValueFactory(new PropertyValueFactory<>("jurusan"));

        TableColumn<Student, String> emailColumn = new TableColumn<>("Email");
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

        Button backBtn = new Button("back");
        double setminwidth = 100;
        backBtn.setMinWidth(setminwidth);

        backBtn.setOnAction(event->{
            adminMenu(stage);
        });


        // table.getColumns().add(nomerColumn);
        table.getColumns().add(namaColumn);
        table.getColumns().add(nimColumn);
        table.getColumns().add(fakultasColumn);
        table.getColumns().add(jurusanColumn);
        table.getColumns().add(emailColumn);

        ObservableList<Student> userlist = FXCollections.observableArrayList(Main.userlist);
        table.setItems(userlist);

        VBox vBox = new VBox(table, backBtn);
        // vBox.setAlignment(Pos.CENTER);
        Scene scene = new Scene(vBox, 600, 400);
        stage.setTitle("Daftar Student");
        stage.setScene(scene);
        stage.show();
    }

    public void displayBooks(Stage stage) {
        TableView<Book> table = new TableView<>();

        TableColumn<Book, Number> nomerColumn = new TableColumn<>("No");
        nomerColumn.setCellValueFactory(new PropertyValueFactory<>("nomerUrut"));
    
        TableColumn<Book, String> judulColumn = new TableColumn<>("Judul");
        judulColumn.setCellValueFactory(new PropertyValueFactory<>("judul"));
    
        TableColumn<Book, String> penulisColumn = new TableColumn<>("Author");
        penulisColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
    
        TableColumn<Book, String> bookIdColumn = new TableColumn<>("BookId");
        bookIdColumn.setCellValueFactory(new PropertyValueFactory<>("bookId"));
    
        TableColumn<Book, Integer> stockColumn = new TableColumn<>("Stock");
        stockColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
    
        TableColumn<Book, String> categoryColumn = new TableColumn<>("Kategori");
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category")); 
    
        Button backBtn = new Button("Back");
        double setminwidth = 100;
        backBtn.setMinWidth(setminwidth);
        
        backBtn.setOnAction(event -> {
            adminMenu(stage);
        });

        for (int i = 0; i < booklist.size(); i++) {
            booklist.get(i).setNomerUrut(i + 1);
        }
        
        table.getColumns().add(nomerColumn);
        table.getColumns().add(judulColumn);
        table.getColumns().add(penulisColumn);
        table.getColumns().add(bookIdColumn);
        table.getColumns().add(stockColumn);
        table.getColumns().add(categoryColumn);
    
        ObservableList<Book> bookList = FXCollections.observableArrayList(booklist);
        table.setItems(bookList);
    
        VBox vbox = new VBox(table, backBtn);
        Scene scene = new Scene(vbox, 600, 400);
        stage.setTitle("Daftar Buku");
        stage.setScene(scene);
        stage.show();
    }

    public void displayBooksController(Stage stage) {
        TableView<Book> table = new TableView<>();

        TableColumn<Book, Number> nomerColumn = new TableColumn<>("No");
        nomerColumn.setCellValueFactory(new PropertyValueFactory<>("nomerUrut"));
    
        TableColumn<Book, String> judulColumn = new TableColumn<>("Judul");
        judulColumn.setCellValueFactory(new PropertyValueFactory<>("judul"));
    
        TableColumn<Book, String> penulisColumn = new TableColumn<>("Author");
        penulisColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
    
        TableColumn<Book, String> bookIdColumn = new TableColumn<>("BookId");
        bookIdColumn.setCellValueFactory(new PropertyValueFactory<>("bookId"));
    
        TableColumn<Book, Integer> stockColumn = new TableColumn<>("Stock");
        stockColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
    
        TableColumn<Book, String> categoryColumn = new TableColumn<>("Kategori");
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category")); 
    
        Button backBtn = new Button("Back");
        backBtn.setOnAction(event -> {
            Student student = new Student();
            student.pinjamBuku(stage);
        });

        for (int i = 0; i < booklist.size(); i++) {
            booklist.get(i).setNomerUrut(i + 1);
        }
    
        table.getColumns().add(nomerColumn);
        table.getColumns().add(judulColumn);
        table.getColumns().add(penulisColumn);
        table.getColumns().add(bookIdColumn);
        table.getColumns().add(stockColumn);
        table.getColumns().add(categoryColumn);
    
        ObservableList<Book> bookList = FXCollections.observableArrayList(booklist);
        table.setItems(bookList);
    
        VBox vbox = new VBox(table, backBtn);
        Scene scene = new Scene(vbox, 600, 400);
        stage.setTitle("Daftar Buku");
        stage.setScene(scene);
        stage.show();
    }
    

}