// import java.util.ArrayList;
// import java.util.Scanner;

// class Mahasiswa {
//     private String namaLengkap;
//     private String nim;
//     private double nilaiTugas;
//     private double nilaiUts;
//     private double nilaiUas;
//     private double nilaiAkhir;
//     private char hurufMutu;

//     public Mahasiswa(String namaLengkap, String nim, double nilaiTugas, double nilaiUts, double nilaiUas) {
//         this.namaLengkap = namaLengkap;
//         this.nim = nim;
//         this.nilaiTugas = nilaiTugas;
//         this.nilaiUts = nilaiUts;
//         this.nilaiUas = nilaiUas;
//         this.nilaiAkhir = hitungNilaiAkhir(nilaiTugas, nilaiUts, nilaiUas);
//         this.hurufMutu = tentukanHurufMutu(this.nilaiAkhir);
//     }

//     public static double hitungNilaiAkhir(double tugas, double uts, double uas) {
//         return (tugas * 0.3) + (uts * 0.3) + (uas * 0.4);
//     }

//     public static char tentukanHurufMutu(double nilai) {
//         if (nilai >= 85) return 'A';
//         else if (nilai >= 70) return 'B';
//         else if (nilai >= 60) return 'C';
//         else if (nilai >= 50) return 'D';
//         else return 'E';
//     }

//     public String getNamaLengkap() {
//         return namaLengkap;
//     }

//     public String getNim() {
//         return nim;
//     }

//     public double getNilaiTugas() {
//         return nilaiTugas;
//     }

//     public double getNilaiUts() {
//         return nilaiUts;
//     }

//     public double getNilaiUas() {
//         return nilaiUas;
//     }

//     public double getNilaiAkhir() {
//         return nilaiAkhir;
//     }

//     public char getHurufMutu() {
//         return hurufMutu;
//     }
// }

// public class AplikasiNilaiMahasiswa {
//     private static final Scanner inputScanner = new Scanner(System.in);
//     private static final ArrayList<Mahasiswa> daftarMahasiswa = new ArrayList<>();

//     public static void main(String[] args) {
//         int pilihanMenu;
//         do {
//             tampilkanMenuUtama();
//             System.out.print("Masukkan pilihan Anda: ");
//             pilihanMenu = inputScanner.nextInt();
//             inputScanner.nextLine(); 
//             switch (pilihanMenu) {
//                 case 1 -> tambahDataMahasiswa();
//                 case 2 -> tampilkanSemuaMahasiswa();
//                 case 3 -> cariMahasiswaByNim();
//                 case 4 -> hapusMahasiswaByNim();
//                 case 0 -> System.out.println("Keluar dari program. Sampai jumpa!");
//                 default -> System.out.println("Pilihan tidak tersedia.");
//             }
//         } while (pilihanMenu != 0);
//     }

//     private static void tampilkanMenuUtama() {
//         System.out.println("\n===== MENU UTAMA =====");
//         System.out.println("1. Input Data Mahasiswa");
//         System.out.println("2. Lihat Semua Mahasiswa");
//         System.out.println("3. Cari Mahasiswa (NIM)");
//         System.out.println("4. Hapus Mahasiswa (NIM)");
//         System.out.println("0. Keluar");
//     }

//     private static void tambahDataMahasiswa() {
//         System.out.print("Nama Lengkap : ");
//         String nama = inputScanner.nextLine();

//         System.out.print("NIM          : ");
//         String nim = inputScanner.nextLine();

//         double tugas = inputNilaiDenganValidasi("Nilai Tugas : ");
//         double uts = inputNilaiDenganValidasi("Nilai UTS   : ");
//         double uas = inputNilaiDenganValidasi("Nilai UAS   : ");

//         Mahasiswa mhs = new Mahasiswa(nama, nim, tugas, uts, uas);
//         daftarMahasiswa.add(mhs);
//         System.out.println(" Data mahasiswa berhasil ditambahkan.");
//     }

//     private static double inputNilaiDenganValidasi(String teks) {
//         double nilai;
//         while (true) {
//             System.out.print(teks);
//             nilai = inputScanner.nextDouble();
//             if (nilai >= 0 && nilai <= 100) {
//                 return nilai;
//             } else {
//                 System.out.println(" Nilai harus antara 0 - 100.");
//             }
//         }
//     }

//     private static void tampilkanSemuaMahasiswa() {
//         System.out.println("\n===== DAFTAR MAHASISWA =====");
//         if (daftarMahasiswa.isEmpty()) {
//             System.out.println("Belum ada data mahasiswa.");
//             return;
//         }

//         System.out.printf("%-20s %-10s %-8s %-8s %-8s %-8s %-5s\n",
//                 "Nama", "NIM", "Tugas", "UTS", "UAS", "Akhir", "Grade");

//         for (Mahasiswa mhs : daftarMahasiswa) {
//             System.out.printf("%-20s %-10s %-8.1f %-8.1f %-8.1f %-8.1f %-5c\n",
//                     mhs.getNamaLengkap(),
//                     mhs.getNim(),
//                     mhs.getNilaiTugas(),
//                     mhs.getNilaiUts(),
//                     mhs.getNilaiUas(),
//                     mhs.getNilaiAkhir(),
//                     mhs.getHurufMutu());
//         }
//     }

//     private static void cariMahasiswaByNim() {
//         System.out.print("Masukkan NIM yang dicari: ");
//         String cariNim = inputScanner.nextLine();
//         boolean ketemu = false;

//         for (Mahasiswa mhs : daftarMahasiswa) {
//             if (mhs.getNim().equalsIgnoreCase(cariNim)) {
//                 System.out.println("\n Mahasiswa Ditemukan:");
//                 System.out.println("Nama        : " + mhs.getNamaLengkap());
//                 System.out.println("NIM         : " + mhs.getNim());
//                 System.out.println("Nilai Akhir : " + mhs.getNilaiAkhir());
//                 System.out.println("Huruf Mutu  : " + mhs.getHurufMutu());
//                 ketemu = true;
//                 break;
//             }
//         }

//         if (!ketemu) {
//             System.out.println(" Mahasiswa dengan NIM " + cariNim + " tidak ditemukan.");
//         }
//     }

//     private static void hapusMahasiswaByNim() {
//         System.out.print("Masukkan NIM mahasiswa yang ingin dihapus: ");
//         String nimHapus = inputScanner.nextLine();

//         boolean berhasil = daftarMahasiswa.removeIf(m -> m.getNim().equalsIgnoreCase(nimHapus));

//         if (berhasil) {
//             System.out.println(" Data mahasiswa berhasil dihapus.");
//         } else {
//             System.out.println(" Tidak ditemukan mahasiswa dengan NIM tersebut.");
//         }
//     }
// }
