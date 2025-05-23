// // File: Main.java
// import java.io.BufferedReader;
// import java.io.IOException;
// import java.io.InputStreamReader;
// import java.util.ArrayList;
// import java.util.Scanner;

// public class Main {

//     // Variabel final
//     final int number = 0;

//     public static void main(String[] args) throws IOException {
//         // Output
//         System.out.println("Hello, World!");
//         System.out.print("Hello ");
//         System.out.print("Universe!\n");

//         int age = 20;
//         System.out.printf("Umur saya %d tahun\n", age);

//         // Tipe Data Primitif
//         char golonganDarah = 'A';
//         int umur = 20;
//         double tinggi = 1.74;
//         boolean menikah = true;
//         System.out.println("Golongan darah: " + golonganDarah);
//         System.out.println("Umur: " + umur);
//         System.out.println("Tinggi: " + tinggi);
//         System.out.println("Menikah: " + menikah);

//         // Tipe Data Komposit - String
//         String nama = "Dimas Firmansyah";
//         System.out.println("Nama: " + nama);
//         System.out.println("Huruf pertama: " + nama.charAt(0));
//         System.out.println("Panjang nama: " + nama.length());
//         System.out.println("Nama huruf kecil: " + nama.toLowerCase());
//         System.out.println("Nama huruf besar: " + nama.toUpperCase());
//         System.out.println("Equals ignore case: " + nama.equalsIgnoreCase("dimas firmansyah"));
//         System.out.println("Equals: " + nama.equals("Dimas Firmansyah"));

//         // Array
//         int[] arr = {1, 2, 3, 4, 5};
//         int[] number = new int[5];
//         number[0] = 10;
//         number[1] = 20;
//         number[2] = 30;
//         System.out.println("Isi array index 0: " + number[0]);

//         // ArrayList
//         ArrayList<String> listName = new ArrayList<>();
//         listName.add("Dilan");
//         listName.add("Milea");
//         System.out.println("ArrayList index 0: " + listName.get(0));
//         System.out.println("ArrayList index 1: " + listName.get(1));
//         System.out.println("Ukuran ArrayList: " + listName.size());
//         System.out.println("Apakah ArrayList kosong? " + listName.isEmpty());
//         System.out.println("Apakah ArrayList berisi 'Dilan'? " + listName.contains("Dilan"));

//         // Variabel Lokal
//         int angkaLokal = 10;
//         System.out.println("Variabel lokal: " + angkaLokal);

//         // Variabel final (akses melalui instance)
//         Main example = new Main();
//         System.out.println("Variabel final: " + example.number);

//         // Input menggunakan BufferedReader
//         BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
//         System.out.print("Masukkan nama (BufferedReader): ");
//         String inputNama = input.readLine();
//         System.out.println("Selamat datang " + inputNama);

//         // Parsing dari String ke int
//         System.out.print("Masukkan umur (BufferedReader): ");
//         int umurInput = Integer.parseInt(input.readLine());
//         System.out.println("Umur kamu adalah: " + umurInput);

//         // Input menggunakan Scanner
//         Scanner scanner = new Scanner(System.in);
//         System.out.print("Masukkan kota asal (Scanner): ");
//         String kota = scanner.nextLine();
//         System.out.println("Kamu berasal dari " + kota);

//         System.out.print("Masukkan tinggi badan (Scanner): ");
//         double tinggiBadan = scanner.nextDouble();
//         System.out.println("Tinggi kamu: " + tinggiBadan);
//         // System.out.printf("selamat datang " + inputNama ,"kamu berasal dari "  + kota, "kamu berumur " + umurInput, "berasal dari kota " + kota);

//         scanner.close();
//     }
// }
