package Data;

import Books.Book;
import Email.sendEmailKembali;
import Email.sendEmailPinjam;
import Exception.IllegalAdminAccess;
import Utama.Main;
// import WhatsApp_API.sendWhatsapp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.*;

public class Student extends User{

    //objek
    private ArrayList<Book> bukuBorrowed = new ArrayList<>();

    public Student(String nama, String nim, String fakultas, String jurusan, String email){
        super(nama, nim, fakultas, jurusan, email);
    }

    public Student(){}


    public void menuStudent(Stage stage) {
        Label menuLabel = new Label("Menu Student");
        Button pinjamButton = new Button("Pinjam Buku");
        Button returButton = new Button("Kembalikan Buku");
        Button tampilkanButton = new Button("Tampilkan buku");
        Button logoutButton = new Button("Logout");

        pinjamButton.setOnAction(event->{
            pinjamBuku(stage);
        });

        returButton.setOnAction(event->{
            kembalikanBuku(stage);
        });

        tampilkanButton.setOnAction(event->{
            displayBorrowedBooksController(stage);
        });

        logoutButton.setOnAction(event->{
            Main mein = new Main();
            mein.Mainmenu(stage);
        });

        double setminwidth = 150;
        pinjamButton.setMinWidth(setminwidth);
        returButton.setMinWidth(setminwidth);
        tampilkanButton.setMinWidth(setminwidth);
        logoutButton.setMinWidth(setminwidth);

        VBox vBox = new VBox(menuLabel,pinjamButton,returButton,tampilkanButton,logoutButton);
        vBox.setSpacing(10);
        vBox.setAlignment(Pos.CENTER);
        Scene scene = new Scene(vBox, 400, 400);
        stage.setTitle("Menu Student");
        stage.setScene(scene);
        stage.show();
    }

    public void displayBorrowedBooksController(Stage stage) {
        TableView<Book> table = new TableView<>();
    
        TableColumn<Book, String> judulColumn = new TableColumn<>("Judul");
        judulColumn.setCellValueFactory(new PropertyValueFactory<>("judul"));
    
        TableColumn<Book, String> penulisColumn = new TableColumn<>("Penulis");
        penulisColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
    
        TableColumn<Book, String> bookIdColumn = new TableColumn<>("BookId");
        bookIdColumn.setCellValueFactory(new PropertyValueFactory<>("bookId"));

        TableColumn<Book, String> kategotiColumn = new TableColumn<>("Kategori");
        kategotiColumn.setCellValueFactory(new PropertyValueFactory<>("category"));

        TableColumn<Book, String> jumlahColumn = new TableColumn<>("Jumlah");
        jumlahColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
    
        TableColumn<Book, Integer> durasiColumn = new TableColumn<>("Durasi (hari)");
        durasiColumn.setCellValueFactory(new PropertyValueFactory<>("durasi"));

        Button backButton = new Button("Back");

        backButton.setOnAction(event->{
            menuStudent(stage);
        });
    
        List<TableColumn<Book, ?>> columns = new ArrayList<>();
        columns.add(judulColumn);
        columns.add(penulisColumn);
        columns.add(bookIdColumn);
        columns.add(kategotiColumn); 
        columns.add(jumlahColumn); 
        columns.add(durasiColumn);

        table.getColumns().addAll(columns);
        
        ObservableList<Book> borrowedBooks = FXCollections.observableArrayList(bukuBorrowed);
        table.setItems(borrowedBooks);
    
        VBox vbox = new VBox(table, backButton);
        Scene scene = new Scene(vbox, 600, 400);
        stage.setTitle("Buku yang Sedang Dipinjam");
        stage.setScene(scene);
        stage.show();
    }

    public void tampilkanbukuborrowed(Stage stage) {
        TableView<Book> table = new TableView<>();

        TableColumn<Book, String> judulColumn = new TableColumn<>("Judul");
        judulColumn.setCellValueFactory(new PropertyValueFactory<>("judul"));
    
        TableColumn<Book, String> penulisColumn = new TableColumn<>("Penulis");
        penulisColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
    
        TableColumn<Book, String> bookIdColumn = new TableColumn<>("BookId");
        bookIdColumn.setCellValueFactory(new PropertyValueFactory<>("bookId"));

        TableColumn<Book, String> kategotiColumn = new TableColumn<>("Kategori");
        kategotiColumn.setCellValueFactory(new PropertyValueFactory<>("category"));

        TableColumn<Book, String> jumlahColumn = new TableColumn<>("Jumlah");
        jumlahColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
    
        TableColumn<Book, Integer> durasiColumn = new TableColumn<>("Durasi (hari)");
        durasiColumn.setCellValueFactory(new PropertyValueFactory<>("durasi"));

        Button backButton = new Button("Back");

        backButton.setOnAction(event->{
            kembalikanBuku(stage);
        });
    
        table.getColumns().add(judulColumn);
        table.getColumns().add(penulisColumn);
        table.getColumns().add(bookIdColumn);
        table.getColumns().add(jumlahColumn);
        table.getColumns().add(durasiColumn);
    
        ObservableList<Book> bookList = FXCollections.observableArrayList(bukuBorrowed);
        table.setItems(bookList);
        
        ObservableList<Book> borrowedBooks = FXCollections.observableArrayList(bukuBorrowed);
        table.setItems(borrowedBooks);
    
        VBox vbox = new VBox(table, backButton);
        Scene scene = new Scene(vbox, 600, 400);
        stage.setTitle("Buku yang Sedang Dipinjam");
        stage.setScene(scene);
        stage.show();
    }

    //menampilkan buku yang tersedia
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
    
        for (int i = 0; i < booklist.size(); i++) {
            booklist.get(i).setNomerUrut(i + 1);
        }

        Button backBtn = new Button("Back");
        backBtn.setOnAction(event -> {
           pinjamBuku(stage);
        });
    
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

    //pinjam buku
    public void pinjamBuku(Stage stage) {
        Label juduLabel = new Label("Judul");
        TextField juduField = new TextField();
    
        Label jumlahLabel = new Label("Jumlah");
        TextField jumlahField = new TextField();
    
        Label durasiLabel = new Label("Durasi pinjam (Max 14 hari)");
        TextField durasField = new TextField();

        Button lihatbukuButton = new Button("Lihat Buku");
        Button submiButton = new Button("submit");
        Button exitButton = new Button("exit");
    
        double setminwidth = 70;
        lihatbukuButton.setMinWidth(setminwidth);
        submiButton.setMinWidth(setminwidth);
        exitButton.setMinWidth(setminwidth);
    
        submiButton.setOnAction(event -> {
            String judul = juduField.getText();
            int jumlah = Integer.parseInt(jumlahField.getText());
            int durasi = Integer.parseInt(durasField.getText());
    
            boolean bukuDitemukan = false;
    
            try {
                for (Book cek : booklist) {
                    if (cek.getJudul().equalsIgnoreCase(judul)) {
                        bukuDitemukan = true;
                        if (jumlah > cek.getStock()) {
                            showWarning("WARNING", "Jumlah melebihi stock yang ada", null);
                        } else {
                            if (durasi > 14) {
                                showWarning("WARNING", "Melebihi ketentuan batas pinjam", null);
                            } else {
                                cek.kurangStock(jumlah);
                                Book bukuBorowed = new Book(cek);
                                bukuBorowed.setStock(jumlah);
                                bukuBorowed.setDurasi(durasi);
                                bukuBorrowed.add(bukuBorowed);
                                bukuBorowed.setTanggalPinjam(new Date());
                                showAlert("INFORMATION", "Buku berhasil dipinjam", "Email berhasil terkirim");                        
                                sendEmailPinjam.kirimEmail(this);
                                return;
                            }
                        }
                    }
                }
    
                if (!bukuDitemukan) {
                    showWarning("WARNING", "Buku tidak ditemukan", null);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            juduField.clear();
            jumlahField.clear();
            durasField.clear();
        });
    
        lihatbukuButton.setOnAction(event -> {
            displayBooksController(stage);
        });
    
        exitButton.setOnAction(event -> {
            menuStudent(stage);
        });
    
        juduField.clear();
        jumlahField.clear();
        durasField.clear();
    
        HBox hbox = new HBox(7, submiButton, exitButton);
        hbox.setAlignment(Pos.CENTER);
        VBox vBox = new VBox(10, juduLabel, juduField, jumlahLabel, jumlahField, durasiLabel, durasField, lihatbukuButton,hbox);
        vBox.setPadding(new Insets(15));
        Scene scene = new Scene(vBox, 400, 400);
        stage.setTitle("Pinjam Buku");
        stage.setScene(scene);
        stage.show();
    }
    

    //kembalikan buku
    private void kembalikanBuku(Stage stage) {
        Label judulLabel = new Label("Judul");
        TextField judulField = new TextField();
    
        Label jumlahLabel = new Label("Jumlah kembali");
        TextField jumlahField = new TextField();
    
        Button lihatBukuButton = new Button("Lihat buku yang dipinjam");
        Button submitButton = new Button("submit");
        Button exitButton = new Button("exit");
    
        double setminwidth = 70;
        lihatBukuButton.setMinWidth(setminwidth);
        submitButton.setMinWidth(setminwidth);
        exitButton.setMinWidth(setminwidth);
    
        lihatBukuButton.setOnAction(event -> {
            tampilkanbukuborrowed(stage);
        });
    
        submitButton.setOnAction(event -> {
            String judul = judulField.getText();
            int jumlahreturn = Integer.parseInt(jumlahField.getText());
            boolean bukuDitemukan = false;
            jumlahkembali = jumlahreturn;
            try {
                for (Book back : bukuBorrowed) {
                    if (back.getJudul().equalsIgnoreCase(judul)) {
                        bukuDitemukan = true;
                        if (jumlahreturn <= back.getStock()) {
                            for (Book tambah : booklist) {
                                if (tambah.getJudul().equalsIgnoreCase(judul)) {
                                    back.setStock(back.getStock() - jumlahreturn);
                                    tambah.setStock(jumlahreturn + tambah.getStock());
                                    showAlert("INFORMATION", "Buku berhasil dikembalikan", "Email berhasil terkirim");
                                    sendEmailKembali.kirimEmail(this);
    
                                    if (back.getStock() == 0) {
                                        bukuBorrowed.remove(back);
                                    }
    
                                    Date tanggalKembali = new Date();
                                    long selisih = selisihHari(back.getTanggalPinjam(), tanggalKembali);
                                    if (selisih > back.getDurasi()) {
                                        long keterlambatan = selisih - back.getDurasi();
                                        System.out.println("Anda terlambat mengembalikan selama " + keterlambatan + " hari");
                                        beriSanksi(keterlambatan);
                                    }
                                    return;
                                }
                            }
                        } else {
                            showWarning("WARNING", "Melebihi jumlah yang dipinjam", null);
                        }
                    }
                }
    
                if (!bukuDitemukan) {
                    showWarning("WARNING", "Buku tidak ditemukan", null);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });
    
        exitButton.setOnAction(event -> {
            menuStudent(stage);
        });
    
        judulField.clear();
        jumlahField.clear();
    
        HBox hbox = new HBox(5, submitButton, exitButton);
        hbox.setAlignment(Pos.CENTER);
        VBox vBox = new VBox(10, judulLabel, judulField, jumlahLabel, jumlahField, lihatBukuButton, hbox);
        vBox.setPadding(new Insets(15));
        Scene scene = new Scene(vBox, 400, 400);
        stage.setTitle("Mengembalikan Buku");
        stage.setScene(scene);
        stage.show();
    }
    
    
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

    private void showAlert(String title, String header, String contrnt){
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(contrnt);
        alert.show();
    }

    private void showWarning(String title, String header, String contrnt){
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(contrnt);
        alert.show();
    }

    
}