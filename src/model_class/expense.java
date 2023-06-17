package model_class;

// mengimport kebutuhan
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

//Menggunakan metode extends untuk mewarisi dari transaction
public class expense extends Transaction {

    // dikarenakna ini merupakan sebuah child kan maka akan menggunakan super
    // Menginisialisasi objek expense dengan mewarisi atribut dan metode dari kelas
    // Transaction.
    public expense(Date TimeStamp, String Category, Double Total, String Notes) {
        super(TimeStamp, Category, Total, Notes);
    }

    // mengimplementasikan bagaimana method getdata() di parent class bekerja
    // method ini berfungsi untuk menghubungkan program ke mysql sehingga data dari
    // mysql tersebut bisa dibuat menjadi
    // sebuah objek income
    public static ResultSet getData() {
        try {

            // programm menggunakan try catch untuk mengetes apa kah koneksi berhasil atau tidak
            Connection connection;
            PreparedStatement preparedStatement;
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost/expensedb", "root", "");
            preparedStatement = connection.prepareStatement("SELECT * FROM `expenseList`");
            System.out.println("Expense Connect success");
            ResultSet rs = preparedStatement.executeQuery();

            // method ini akan mengembalikan / mengeluarkan sebuah nilai bertipe data Resultset
            return rs;
        } catch (Exception e) {
            //jika koneksi gagal program akan mengeluarkan nilai null / tidak ada
            System.out.println("connect failed");
            return null;
        }
    }
}
