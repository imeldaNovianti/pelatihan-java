// package kuis;



// import java.util.Scanner;

// public class KalkulatorSederhana {
//     public static void main(String[] args) {
//         Scanner scanner = new Scanner(System.in);
//         int pilihan;
//         double angkaPertama, angkaKedua, hasil = 0;

//         do {
//             tampilkanMenu();
//             System.out.print("Pilih operasi (1-5): ");
//             pilihan = scanner.nextInt();

//             if (pilihan >= 1 && pilihan <= 4) {
//                 System.out.print("Masukkan angka pertama : ");
//                 angkaPertama = scanner.nextDouble();
//                 System.out.print("Masukkan angka kedua   : ");
//                 angkaKedua = scanner.nextDouble();

//                 switch (pilihan) {
//                     case 1:
//                         hasil = angkaPertama + angkaKedua;
//                         System.out.println("Hasil dari " + angkaPertama + " + " + angkaKedua + " adalah " + hasil);
//                         break;
//                     case 2:
//                         hasil = angkaPertama - angkaKedua;
//                         System.out.println("Hasil dari " + angkaPertama + " - " + angkaKedua + " adalah " + hasil);
//                         break;
//                     case 3:
//                         hasil = angkaPertama * angkaKedua;
//                         System.out.println("Hasil dari " + angkaPertama + " x " + angkaKedua + " adalah " + hasil);
//                         break;
//                     case 4:
//                         if (angkaKedua != 0) {
//                             hasil = angkaPertama / angkaKedua;
//                             System.out.println("Hasil dari " + angkaPertama + " ÷ " + angkaKedua + " adalah " + hasil);
//                         } else {
//                             System.out.println("Tidak bisa dibagi dengan nol.");
//                         }
//                         break;
//                 }
//             } else if (pilihan == 5) {
//                 System.out.println("Keluar dari program. Terima kasih!");
//             } else {
//                 System.out.println("Pilihan tidak valid. Silakan pilih 1 - 5.");
//             }

//             System.out.println(); 
//         } while (pilihan != 5);

//         scanner.close(); 
//     }

//     public static void tampilkanMenu() {
//         System.out.println("=== Kalkulator Sederhana ===");
//         System.out.println("1. Tambah");
//         System.out.println("2. Kurang");
//         System.out.println("3. Kali");
//         System.out.println("4. Bagi");
//         System.out.println("5. Keluar");
//     }
// }
