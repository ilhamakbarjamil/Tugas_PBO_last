package Books;

import java.util.Date;

public class Book {
    String judul, BookId, author, category, stockSting;
    int stock;
    int durasi;
    private Date tanggalPinjam;
    private int nomerUrut;

    public Book(String judul,  String author, String bookId, String category, int stock){
        this.judul = judul;
        this.BookId = bookId;
        this.author = author;
        this.category = category;
        this.stock = stock;
    }

    public Book(String judul,  String author, String bookId, String category, String stock){
        this.judul = judul;
        this.BookId = bookId;
        this.author = author;
        this.category = category;
        this.stockSting = stock;
    }

    public Book(Book book) {
        this.judul = book.judul;
        this.author = book.author;
        this.BookId = book.BookId;
        this.category = book.category;
        this.stock = book.stock;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getBookId() {
        return BookId;
    }

    public void setBookId(String bookId) {
        BookId = bookId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getDurasi() {
        return durasi;
    }

    public void setDurasi(int durasi) {
        this.durasi = durasi;
    }

    public void kurangStock(int jumlah){
        if(stock > 0){
            stock -= jumlah;
        }
    }

    public void tambahStock(int jumlah){
        if(jumlah <= stock){
            stock += jumlah;
        }
    }

    public Date getTanggalPinjam() {
        return tanggalPinjam;
    }

    public void setTanggalPinjam(Date tanggalPinjam) {
        this.tanggalPinjam = tanggalPinjam;
    }

    public int getNomerUrut() {
        return nomerUrut;
    }

    public void setNomerUrut(int nomerUrut) {
        this.nomerUrut = nomerUrut;
    }
}