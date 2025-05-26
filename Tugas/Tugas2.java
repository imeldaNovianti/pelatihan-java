// File: tugas2.java
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

// Superclass: Person
class Person {
    // Enkapsulasi: atribut dibuat private dan diakses melalui getter
    private String nama;
    private String id;

    // Constructor
    public Person(String nama, String id) {
        this.nama = nama;
        this.id = id;
    }

    // Getter dan setter (Enkapsulasi)
    public String getNama() {
        return this.nama;
    }

    public String getId() {
        return this.id;
    }

    // Method display() akan dioverride
    public void display() {
        System.out.println("Nama: " + nama);
        System.out.println("ID: " + id);
    }
}

// Subclass: Mahasiswa mewarisi Person (Inheritance)
class Mahasiswa extends Person {
    private String jurusan; // tambahan atribut (Enkapsulasi)

    // Constructor menggunakan keyword this
    public Mahasiswa(String nama, String nim, String jurusan) {
        super(nama, nim); // memanggil konstruktor superclass
        this.jurusan = jurusan;
    }

    // Overriding method display (Polimorfisme)
    @Override
    public void display() {
        System.out.println("=== Mahasiswa ===");
        System.out.println("Nama: " + getNama());
        System.out.println("NIM: " + getId());
        System.out.println("Jurusan: " + jurusan);
    }
}

// Subclass: Dosen mewarisi Person (Inheritance)
class Dosen extends Person {
    protected String bidang; // Contoh access modifier: protected

    public Dosen(String nama, String nip, String bidang) {
        super(nama, nip);
        this.bidang = bidang;
    }

    // Overriding method display (Polimorfisme)
    @Override
    public void display() {
        System.out.println("=== Dosen ===");
        System.out.println("Nama: " + getNama());
        System.out.println("NIP: " + getId());
        System.out.println("Bidang: " + bidang);
    }
}

public class Tugas2 {
    // Collection: ArrayList untuk mahasiswa
    static ArrayList<Mahasiswa> daftarMahasiswa = new ArrayList<>();
    
    // Collection: HashMap untuk dosen (NIP sebagai key)
    static HashMap<String, Dosen> daftarDosen = new HashMap<>();
    
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.print("\n Masukkan jumlah mahasiswa: ");
        int jumlahMhs = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < jumlahMhs; i++) {
            System.out.println("\nInput data mahasiswa ke-" + (i + 1));
            System.out.print("Nama: ");
            String nama = scanner.nextLine();
            System.out.print("NIM: ");
            String nim = scanner.nextLine();
            System.out.print("Jurusan: ");
            String jurusan = scanner.nextLine();
            Mahasiswa mhs = new Mahasiswa(nama, nim, jurusan);
            daftarMahasiswa.add(mhs); // Menyimpan ke ArrayList
        }

        System.out.print("\nMasukkan jumlah dosen: ");
        int jumlahDosen = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < jumlahDosen; i++) {
            System.out.println("\nInput data dosen ke-" + (i + 1));
            System.out.print("Nama: ");
            String nama = scanner.nextLine();
            System.out.print("NIP: ");
            String nip = scanner.nextLine();
            System.out.print("Bidang: ");
            String bidang = scanner.nextLine();
            Dosen dsn = new Dosen(nama, nip, bidang);
            daftarDosen.put(nip, dsn); // Menyimpan ke HashMap
        }

        // Output Mahasiswa
        System.out.println("\n===== DAFTAR MAHASISWA =====");
        for (Mahasiswa mhs : daftarMahasiswa) {
            mhs.display();
            System.out.println("-----------------------------");
        }

        // Output Dosen
        System.out.println("\n===== DAFTAR DOSEN =====");
        for (String nip : daftarDosen.keySet()) {
            Dosen dsn = daftarDosen.get(nip);
            dsn.display();
            System.out.println("-----------------------------");
        }
    }
}
