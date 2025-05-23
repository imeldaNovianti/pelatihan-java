import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

class Person {
    protected String nama;
    protected String id;

    public Person(String nama, String id) {
        this.nama = nama;
        this.id = id;
    }

    public void tampilkanData() {
        System.out.println("Nama: " + nama);
        System.out.println("ID: " + id);
    }
}

class Mahasiswa extends Person {
    private String jurusan;

    public Mahasiswa(String nama, String nim, String jurusan) {
        super(nama, nim);
        this.jurusan = jurusan;
    }

    @Override
    public void tampilkanData() {
        System.out.println("Nama: " + nama);
        System.out.println("NIM: " + id);
        System.out.println("Jurusan: " + jurusan);
    }
}

class Dosen extends Person {
    private String bidang;

    public Dosen(String nama, String nip, String bidang) {
        super(nama, nip);
        this.bidang = bidang;
    }

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
        ArrayList<Mahasiswa> daftarMahasiswa = new ArrayList<>();
        HashMap<String, Dosen> daftarDosen = new HashMap<>();

        System.out.print("Masukkan jumlah mahasiswa: ");
        int jumlahMahasiswa = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < jumlahMahasiswa; i++) {
            System.out.println("Masukkan data mahasiswa ke-" + (i + 1));
            System.out.print("Nama: ");
            String nama = scanner.nextLine();
            System.out.print("NIM: ");
            String nim = scanner.nextLine();
            System.out.print("Jurusan: ");
            String jurusan = scanner.nextLine();

            Mahasiswa mahasiswa = new Mahasiswa(nama, nim, jurusan); 
            daftarMahasiswa.add(mahasiswa);
        }

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
            daftarDosen.put(nip, dosen);
        }

        System.out.println("\n=== DATA MAHASISWA ===");
        for (Mahasiswa mahasiswa : daftarMahasiswa) {
            mahasiswa.tampilkanData();
            System.out.println();
        }

        System.out.println("=== DATA DOSEN ===");
        for (Dosen dosen : daftarDosen.values()) {
            dosen.tampilkanData();
            System.out.println();
        }

        scanner.close();
    }
}
