package model_class;
import java.sql.Date;
import java.sql.ResultSet;

abstract public class Transaction {         //class ini merupakan abstract class
    private Date Timestamp;
    private String Category;                //Membuat attribute
    private Double Total;
    private String Notes;
    
    public Transaction(Date TimeStamp,String Category,Double Total,String Notes){
        this.Timestamp = TimeStamp;         
        this.Category = Category;           //membuat konstruktor 
        this.Total = Total;
        this.Notes = Notes;
    }

    // ================================membuat method setter dan getter====================
    public Date getTimestamp(){
        return this.Timestamp;
    }
    public String getCategory() {
        return Category;
    }
    public Double getTotal() {
        return Total;
    }
    public String getNotes() {
        return Notes;
    }
    public void setTimestamp(Date timestamp) {
        Timestamp = timestamp;
    }
    public void setCategory(String category) {
        Category = category;
    }
    public void setTotal(Double total) {
        Total = total;
    }
    public void setNotes(String notes) {
        Notes = notes;
    }

    // Merupakan method yang akan diimplementasikan oleh child class dan method disini hanyalah sebuah definisi
    public static ResultSet getData(){
        return null;
    }
}
