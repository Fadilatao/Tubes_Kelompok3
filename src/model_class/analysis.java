package model_class;

public class analysis {

    // membuat atribute untuk objek analyisis
    private double Sum;
    private double pieSum;

    // membuat konstruktor untuk membuat objek analysis nantinya 
    public analysis(double Sum, double pieSum) {
        this.Sum = Sum;
        this.pieSum = pieSum;
    }

    // membuat method setter dan gettter 
    public double getPieSum() {
        return pieSum;
    }

    public double getSum() {
        return Sum;
    }

    // method ini merupakan method yang akan digunakan nantinya
    public static String SumPercentage(double Sum, double pieSum) {
        // method ini akan melakukan perhitungan untuk menentukan jumlah presentase sebuah objek,
        // jika dibandingkan jumlah total objek tersebut di dalam piechart
        double sum = (pieSum / Sum) * 100;      
        // membuat string format untuk menampilkan nya sebagai string                         
        String sumPercentage = String.format("%.2f%%", sum);
        return sumPercentage;
    }

}
