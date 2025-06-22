// import java.util.ArrayList;
// import java.util.HashMap;
// import java.util.Scanner;

// // Kelas Induk
// class Person {
//     private String nama;
//     private String id;

//     public Person(String nama, String id) {
//         this.nama = nama;
//         this.id = id;
//     }

//     public String getNama() {
//         return nama;
//     }

//     public String getId() {
//         return id;
//     }

//     public void display() {
//         System.out.println("Nama: " + nama + " | ID: " + id);
//     }
// }

// // Kelas Mahasiswa
// class Mahasiswa extends Person {
//     private String jurusan;

//     public Mahasiswa(String nama, String id, String jurusan) {
//         super(nama, id);
//         this.jurusan = jurusan;
//     }

//     @Override
//     public void display() {
//         System.out.println("  Nama: " + getNama());
//         System.out.println("  NIM : " + getId());
//         System.out.println("  Jurusan: " + jurusan);
//         System.out.println("----------------------------------");
//     }
// }

// // Kelas Dosen
// class Dosen extends Person {
//     private String bidang;

//     public Dosen(String nama, String id, String bidang) {
//         super(nama, id);
//         this.bidang = bidang;
//     }

//     @Override
//     public void display() {
//         System.out.println("  Nama  : " + getNama());
//         System.out.println("  NIP   : " + getId());
//         System.out.println("  Bidang: " + bidang);
//         System.out.println("----------------------------------");
//     }
// }

// public class Mainn2 {
//     public static void main(String[] args) {
//         Scanner scanner = new Scanner(System.in);
//         ArrayList<Mahasiswa> daftarMahasiswa = new ArrayList<>();
//         HashMap<String, Dosen> daftarDosen = new HashMap<>();

//         // Input jumlah mahasiswa
//         System.out.print("\nMasukkan jumlah mahasiswa: ");
//         int jumlahMahasiswa = scanner.nextInt();
//         scanner.nextLine(); // Buang newline

//         // Input data mahasiswa
//         for (int i = 0; i < jumlahMahasiswa; i++) {
//             System.out.println("\nMasukkan data Mahasiswa ke-" + (i + 1) + ":");
//             System.out.print("  Nama: ");
//             String nama = scanner.nextLine();
//             System.out.print("  NIM: ");
//             String nim = scanner.nextLine();
//             System.out.print("  Jurusan: ");
//             String jurusan = scanner.nextLine();
//             daftarMahasiswa.add(new Mahasiswa(nama, nim, jurusan));
//         }

//         // Input jumlah dosen
//         System.out.print("\nMasukkan jumlah dosen: ");
//         int jumlahDosen = scanner.nextInt();
//         scanner.nextLine(); // Buang newline

//         // Input data dosen
//         for (int i = 0; i < jumlahDosen; i++) {
//             System.out.println("\nMasukkan data Dosen ke-" + (i + 1) + ":");
//             System.out.print("  Nama: ");
//             String nama = scanner.nextLine();
//             System.out.print("  NIP: ");
//             String nip = scanner.nextLine();
//             System.out.print("  Bidang: ");
//             String bidang = scanner.nextLine();
//             daftarDosen.put(nip, new Dosen(nama, nip, bidang));





            
//         }

//         // Menampilkan data mahasiswa
//         System.out.println("\n=== Daftar Mahasiswa ===");
//         int noMhs = 1;
//         for (Mahasiswa mhs : daftarMahasiswa) {
//             System.out.println("\nMahasiswa ke-" + noMhs + ":");
//             mhs.display();
//             noMhs++;
//         }

//         // Menampilkan data dosen
//         System.out.println("\n=== Daftar Dosen ===");
//         int noDsn = 1;
//         for (Dosen dsn : daftarDosen.values()) {
//             System.out.println("\nDosen ke-" + noDsn + ":");
//             dsn.display();
//             noDsn++;
//         }

//         scanner.close();
//     }
// }