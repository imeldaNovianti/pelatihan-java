import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

// Kelas induk Person menerapkan enkapsulasi dan inheritance
class Person {
    // Access modifier protected: atribut bisa diakses oleh kelas turunan
    protected String nama;
    protected String id;

    // Konstruktor public untuk inisialisasi objek Person
    public Person(String nama, String id) {
        this.nama = nama;
        this.id = id;
    }

    // Method public yang dapat dioverride oleh subclass (polimorfisme)
    public void tampilkanData() {
        System.out.println("Nama: " + nama);
        System.out.println("ID: " + id);
    }
}

// Mahasiswa mewarisi (inheritance) dari Person
class Mahasiswa extends Person {
    // Enkapsulasi dengan private: atribut jurusan hanya bisa diakses dalam kelas ini
    private String jurusan;

    // Konstruktor memanggil konstruktor superclass dengan super
    public Mahasiswa(String nama, String nim, String jurusan) {
        super(nama, nim); // pemanggilan konstruktor kelas induk Person
        this.jurusan = jurusan;
    }

    // Polimorfisme: method tampilkanData dioverride sesuai data Mahasiswa
    @Override
    public void tampilkanData() {
        System.out.println("Nama: " + nama);
        System.out.println("NIM: " + id);
        System.out.println("Jurusan: " + jurusan);
    }
}

// Dosen juga mewarisi dari Person
class Dosen extends Person {
    // Enkapsulasi dengan private
    private String bidang;

    // Konstruktor memanggil konstruktor kelas induk
    public Dosen(String nama, String nip, String bidang) {
        super(nama, nip);
        this.bidang = bidang;
    }

    // Polimorfisme: override method tampilkanData
    @Override
    public void tampilkanData() {
        System.out.println("Nama: " + nama);
        System.out.println("NIP: " + id);
        System.out.println("Bidang: " + bidang);
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Menggunakan Collection ArrayList buat daftar Mahasiswa-nya
        ArrayList<Mahasiswa> daftarMahasiswa = new ArrayList<>();

        // Menggunakan Collection HashMap untuk daftar Dosen dengan key NIP
        // key nya jangan saama nantikepanggilnya satu
        HashMap<String, Dosen> daftarDosen = new HashMap<>();

        // Input data mahasiswa-nyaa
        System.out.print("Masukkan jumlah mahasiswa: ");
        int jumlahMahasiswa = scanner.nextInt();
        scanner.nextLine(); // agar dapat membersihkan newline

        for (int i = 0; i < jumlahMahasiswa; i++) {
            System.out.println("Masukkan data mahasiswa ke-" + (i + 1));
            System.out.print("Nama: ");
            String nama = scanner.nextLine();
            System.out.print("NIM: ");
            String nim = scanner.nextLine();
            System.out.print("Jurusan: ");
            String jurusan = scanner.nextLine();

            Mahasiswa mahasiswa = new Mahasiswa(nama, nim, jurusan); 
            daftarMahasiswa.add(mahasiswa);  // Menambahkan ke koleksi ArrayList nyaa
        }

        // untuk meng-Inputkan data dosen
        System.out.print("Masukkan jumlah dosen: ");
        int jumlahDosen = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < jumlahDosen; i++) {
            System.out.println("Masukkan data dosen ke-" + (i + 1));
            System.out.print("Nama: ");
            String nama = scanner.nextLine();
            System.out.print("NIP: ");
            String nip = scanner.nextLine();
            System.out.print("Bidang: ");
            String bidang = scanner.nextLine();

            Dosen dosen = new Dosen(nama, nip, bidang);
            daftarDosen.put(nip, dosen);  // Menyimpan di koleksi HashMap dengan key NIP
        }

        // yg ini nampilin data mahasiswa (polimorfisme: memanggil method override)
        System.out.println("\n=== DATA MAHASISWA NYA ===");
        for (Mahasiswa mahasiswa : daftarMahasiswa) {
            mahasiswa.tampilkanData();
            System.out.println();
        }

        // buat nampilin data dosen
        System.out.println("=== DATA DOSEN NYA ===");
        for (Dosen dosen : daftarDosen.values()) {
            dosen.tampilkanData();
            System.out.println();
        }

        scanner.close();
    }
}
