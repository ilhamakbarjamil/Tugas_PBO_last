package Data;

import java.util.ArrayList;
import java.util.Scanner;

import Books.Book;
// import Utama.Main;
// import Utama.Main;
import Util.iMenu;
import javafx.application.Application;
// import javafx.application.Application;
// import javafx.collections.FXCollections;
// import javafx.collections.ObservableList;
// import javafx.scene.Scene;
// import javafx.scene.control.Alert;
// import javafx.scene.control.Button;
// import javafx.scene.control.TableColumn;
// import javafx.scene.control.TableView;
// import javafx.scene.control.Alert.AlertType;
// import javafx.scene.control.cell.PropertyValueFactory;
// import javafx.scene.layout.VBox;
// import javafx.collections.FXCollections;
// import javafx.collections.ObservableList;
// import javafx.scene.Scene;
// import javafx.scene.control.Button;
// import javafx.scene.control.TableColumn;
// import javafx.scene.control.TableView;
// import javafx.scene.control.cell.PropertyValueFactory;
// import javafx.scene.layout.VBox;
// import javafx.collections.FXCollections;
// import javafx.collections.ObservableList;
// import javafx.scene.Scene;
// import javafx.scene.control.Button;
// import javafx.scene.control.TableColumn;
// import javafx.scene.control.TableView;
// import javafx.scene.control.cell.PropertyValueFactory;
// import javafx.scene.layout.VBox;
// import javafx.scene.control.Button;
// import javafx.scene.control.Label;
//ini farhan yang edit
import javafx.stage.Stage;
public class User extends Application implements iMenu{

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage arg0) throws Exception {
        // TODO Auto-generated method stub
        
    }
    
    //objek
    public static ArrayList <Book> booklist = new ArrayList<>();
    Scanner scan = new Scanner(System.in);

    
    public static ArrayList<Book> getBooklist() {
        return booklist;
    }


    protected String nama;
    protected String nim;
    protected String fakultas;
    protected String jurusan;
    protected String email;

    public User(String nama, String nim, String fakultas, String jurusan, String email){
        this.nama = nama;
        this.nim = nim;
        this.fakultas = fakultas;
        this.jurusan = jurusan;
        this.email = email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
            System.out.println("Email\t: "+email);
            System.out.println("----------------------------------");
        }
    }


    @Override
    public void menu() {
       
    }



    // public void displayUserInformation(Stage stage) {
    //     Alert alert = new Alert(AlertType.INFORMATION);
    //     alert.setTitle("data Information");
    //     alert.setHeaderText("Information");
    //     alert.setContentText(nama);
    //     alert.setContentText(nim);
    //     alert.setContentText(fakultas);
    //     alert.setContentText(jurusan);
    //     alert.setContentText(email);
    //     alert.showAndWait();
    // }
}

    // protected void displayInformation(Stage stage){
    //     TableView<Student> table = new TableView<>();

    //     TableColumn<Student, String > namaColumn = new TableColumn<>("Nama");
    //     namaColumn.setCellValueFactory(new PropertyValueFactory<>("nama"));

    //     TableColumn<Student, String> nimColumn = new TableColumn<>("Nim");
    //     nimColumn.setCellValueFactory(new PropertyValueFactory<>("nim"));

    //     TableColumn<Student, String> fakultasColumn = new TableColumn<>("Fakultas");
    //     fakultasColumn.setCellValueFactory(new PropertyValueFactory<>("fakultas"));

    //     TableColumn<Student, String> jurusanColumn = new TableColumn<>("Jurusan");
    //     jurusanColumn.setCellValueFactory(new PropertyValueFactory<>("jurusan"));

    //     TableColumn<Student, String> emailColumn = new TableColumn<>("Email");
    //     emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

    //     Button backBtn = new Button("back");

    //     backBtn.setOnAction(event->{
            
    //     });

    //     table.getColumns().add(namaColumn);
    //     table.getColumns().add(nimColumn);
    //     table.getColumns().add(fakultasColumn);
    //     table.getColumns().add(jurusanColumn);
    //     table.getColumns().add(emailColumn);

    //     ObservableList<Student> userlist = FXCollections.observableArrayList(Main.userlist);
    //     table.setItems(userlist);

    //     VBox vBox = new VBox(table, backBtn);
    //     Scene scene = new Scene(vBox, 600, 400);
    //     stage.setTitle("Daftar Student");
    //     stage.setScene(scene);
    //     stage.show();
    // }

