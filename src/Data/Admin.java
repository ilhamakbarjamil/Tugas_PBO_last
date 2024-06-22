package Data;

// import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

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
// import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
// import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Admin extends User{
    public static void main(String[] args) {
        launch(args);
    }
    private String USERNAME = "admin";
    private String PASSWORD = "admin123";

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

        VBox vbox = new VBox(label,addStudentBtn,addbookBtn,cekStudentBtn,cekBookBtn);
        Scene scene = new Scene(vbox, 400, 400);
        stage.setTitle("Admin menu");
        stage.setScene(scene);
        stage.show();
    }

    public void addStudent(Stage stage){
        Label label = new Label("Menu Tambah Student");
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

            if(!nama.matches("^[a-zA-Z\\s]+$")){
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Nama invlid");
                alert.setHeaderText("Nama tidak boleh angka");
                alert.show();
            }
            if(nim.length() != 15){
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Nim invalid");
                alert.setHeaderText("Nim tidak 15 karakter");
                alert.show();
            }
            if(!fakultas.matches("^[a-zA-Z\\s]+$")){
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Fakultas invlid");
                alert.setHeaderText("fakultas tidak boleh angka");
                alert.show();
            }
            if(!jurusan.matches("^[a-zA-Z\\s]+$")){
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Jurusan invlid");
                alert.setHeaderText("jurusan tidak boleh angka");
                alert.show();
            }
            if(!email.contains("@gmail.com")){
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Email invalid");
                alert.setHeaderText("email tidak sesuai");
                alert.setContentText("harus ada @gmail.com");
                alert.showAndWait();
            }
            Student student = new Student(nama, nim, fakultas, jurusan, email);
            Main.userlist.add(student);

        namaField.clear();
        nimField.clear();
        fakultasField.clear();
        jurusanField.clear();
        emailField.clear();
        });

        backBtn.setOnAction(event->{
            adminMenu(stage);
        });

        VBox vbox = new VBox(label,namaLabel,namaField,nimLabel,nimField,fakultasLabel,fakultasField,jurusanLabel,jurusanField,emaiLabel,emailField,submitBtn,backBtn);
        Scene scene = new Scene(vbox, 400, 400);
        stage.setTitle("Input data");
        stage.setScene(scene);
        stage.show();
    }

    public void addBook(Stage stage) {
    // Labels and TextFields for book details
    Label titleLbl = new Label("Title");
    TextField titleTxt = new TextField();

    Label authorLbl = new Label("Author");
    TextField authorTxt = new TextField();

    Label stockLbl = new Label("Stock");
    TextField stockTxt = new TextField();

    Label categoryLbl = new Label("Category");
    TextField categoryTxt = new TextField();

    // Button to add book
    Button addBtn = new Button("Add Book");
    addBtn.setOnAction(event -> {
        String title = titleTxt.getText();
        String author = authorTxt.getText();
        String stockStr = stockTxt.getText();
        String category = categoryTxt.getText();
        
        // Validation
        if (title.isEmpty() || author.isEmpty() || stockStr.isEmpty() || category.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Form Error!", "Please enter all details");
            return;
        }
        
        int stock;
        try {
            stock = Integer.parseInt(stockStr);
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Form Error!", "Stock must be a number");
            return;
        }
        
        String bookId = generated();

        // Add book based on category
        switch (category.toLowerCase()) {
            case "story":
                booklist.add(new StoryBook(title, author, bookId, category, stock));
                break;
            case "history":
                booklist.add(new HistoryBook(title, author, bookId, category, stock));
                break;
            case "text":
                booklist.add(new TextBook(title, author, bookId, category, stock));
                break;
            default:
                showAlert(Alert.AlertType.ERROR, "Form Error!", "Invalid category. Use 'story', 'history', or 'text'.");
                return;
        }

        // Clear the form fields
        titleTxt.clear();
        authorTxt.clear();
        stockTxt.clear();
        categoryTxt.clear();

        // Show success message
        showAlert(Alert.AlertType.INFORMATION, "Success", "Book added successfully");
    });

    // Button to go back to the admin menu
    Button backBtn = new Button("Back");
    backBtn.setOnAction(event -> adminMenu(stage));

    // Layout for the form
    VBox vbox = new VBox(10, titleLbl, titleTxt, authorLbl, authorTxt, stockLbl, stockTxt, categoryLbl, categoryTxt, addBtn, backBtn);
    vbox.setPadding(new Insets(10));

    // Set the scene and show the stage
    Scene scene = new Scene(vbox, 300, 400);
    stage.setTitle("Add Book");
    stage.setScene(scene);
    stage.show();
}

private void showAlert(Alert.AlertType alertType, String title, String message) {
    Alert alert = new Alert(alertType);
    alert.setTitle(title);
    alert.setContentText(message);
    alert.showAndWait();
}


    //     public void displayBook(Stage stage){
    //     // Admin admin = new Admin();
    //     TableView<Book> table = new TableView<>();

    //     TableColumn<Book, String> judColumn = new TableColumn<>("Judul");
    //     judColumn.setCellValueFactory(new PropertyValueFactory<>("judul"));

    //     TableColumn<Book, String> penulisColumn = new TableColumn<>("Author");
    //     penulisColumn.setCellValueFactory(new PropertyValueFactory<>("author"));

    //     TableColumn<Book, String> BookIdColumn = new TableColumn<>("BookId");
    //     BookIdColumn.setCellValueFactory(new PropertyValueFactory<>("bookId"));

    //     TableColumn<Book, String> jumlahColumn = new TableColumn<>("Stock");
    //     jumlahColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));

    //     TableColumn<Book, String> kategoriColumn = new TableColumn<>("Kategori");
    //     kategoriColumn.setCellValueFactory(new PropertyValueFactory<>("kategori"));

    //     Button backBtn = new Button("back");
    //     backBtn.setOnAction(event ->{
    //         adminMenu(stage);
    //     });

    //     table.getColumns().add(judColumn);
    //     table.getColumns().add(penulisColumn);
    //     table.getColumns().add(BookIdColumn);
    //     table.getColumns().add(jumlahColumn);
    //     table.getColumns().add(kategoriColumn);

    //     ObservableList<Book> Booklist = FXCollections.observableArrayList(booklist);
    //     table.setItems(Booklist);

    //     VBox vbox = new VBox(table,backBtn);
    //     Scene scene = new Scene(vbox, 600, 400);
    //     stage.setTitle("Daftar Buku");
    //     stage.setScene(scene);
    //     stage.show();
    // }

    public void displaystudent(Stage stage){
        TableView<Student> table = new TableView<>();

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

        backBtn.setOnAction(event->{
            adminMenu(stage);
        });

        table.getColumns().add(namaColumn);
        table.getColumns().add(nimColumn);
        table.getColumns().add(fakultasColumn);
        table.getColumns().add(jurusanColumn);
        table.getColumns().add(emailColumn);

        ObservableList<Student> userlist = FXCollections.observableArrayList(Main.userlist);
        table.setItems(userlist);

        VBox vBox = new VBox(table, backBtn);
        Scene scene = new Scene(vBox, 600, 400);
        stage.setTitle("Daftar Student");
        stage.setScene(scene);
        stage.show();
    }

    public void displayBooks(Stage stage) {
        TableView<Book> table = new TableView<>();
    
        TableColumn<Book, String> judulColumn = new TableColumn<>("Judul");
        judulColumn.setCellValueFactory(new PropertyValueFactory<>("judul"));
    
        TableColumn<Book, String> penulisColumn = new TableColumn<>("Author");
        penulisColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
    
        TableColumn<Book, String> bookIdColumn = new TableColumn<>("BookId");
        bookIdColumn.setCellValueFactory(new PropertyValueFactory<>("bookId"));
    
        TableColumn<Book, Integer> stockColumn = new TableColumn<>("Stock");
        stockColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
    
        TableColumn<Book, String> categoryColumn = new TableColumn<>("Kategori");
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category")); // Ensure this matches the getter method name
    
        Button backBtn = new Button("Back");
        backBtn.setOnAction(event -> {
            adminMenu(stage);
        });
    
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