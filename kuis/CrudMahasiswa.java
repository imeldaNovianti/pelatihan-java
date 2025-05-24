package kuis;

import java.util.ArrayList;
import java.util.Scanner;

class Mahasiswa {
    String nama;
    String nim;
    String jurusan;

    Mahasiswa(String nama, String nim, String jurusan) {
        this.nama = nama;
        this.nim = nim;
        this.jurusan = jurusan;
    }
}

public class CrudMahasiswa {
    static ArrayList<Mahasiswa> daftarMahasiswa = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int pilihan = -1;

        do {
            System.out.println("\n=== MENU CRUD MAHASISWA ===");
            System.out.println("1. TAMBAH MAHASISWA");
            System.out.println("2. LIHAT DAFTAR MAHASISWA");
            System.out.println("3. UPDATE MAHASISWA");
            System.out.println("4. HAPUS MAHASISWA");
            System.out.println("0. KELUAR");
            System.out.print("PILIH : ");
            pilihan = inputAngka();

            switch (pilihan) {
                case 1:
                    tambahMahasiswa();
                    break;
                case 2:
                    lihatMahasiswa();
                    break;
                case 3:
                    updateMahasiswa();
                    break;
                case 4:
                    hapusMahasiswa();
                    break;
                case 0:
                    System.out.println("Terima kasih telah menggunakan program.");
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Coba lagi.");
            }
        } while (pilihan != 0);
    }

    static void tambahMahasiswa() {
        System.out.println("\n--- TAMBAH MAHASISWA ---");

        System.out.print("Nama    : ");
        String nama = scanner.nextLine().trim();

        System.out.print("NIM     : ");
        String nim = scanner.nextLine().trim();

        System.out.print("Jurusan : ");
        String jurusan = scanner.nextLine().trim();

        if (nama.isEmpty() || nim.isEmpty() || jurusan.isEmpty()) {
            System.out.println(" Data tidak boleh kosong! Kembali ke menu.");
            return;
        }

        daftarMahasiswa.add(new Mahasiswa(nama, nim, jurusan));
        System.out.println(" Mahasiswa berhasil ditambahkan.");
    }

    static void lihatMahasiswa() {
        System.out.println("\n--- DAFTAR MAHASISWA ---");

        if (daftarMahasiswa.isEmpty()) {
            System.out.println(" Belum ada data mahasiswa.");
            return;
        }

        for (int i = 0; i < daftarMahasiswa.size(); i++) {
            Mahasiswa m = daftarMahasiswa.get(i);
            System.out.println("Data Mahasiswa ke-" + (i + 1));
            System.out.println("Nama    : " + m.nama);
            System.out.println("NIM     : " + m.nim);
            System.out.println("Jurusan : " + m.jurusan);
            System.out.println("-----------------------------");
        }
    }

    static void updateMahasiswa() {
        System.out.println("\n--- UPDATE MAHASISWA ---");

        if (daftarMahasiswa.isEmpty()) {
            System.out.println(" Belum ada data mahasiswa.");
            return;
        }

        lihatMahasiswa();
        System.out.print("Masukkan nomor mahasiswa yang ingin diupdate : ");
        int index = inputAngka() - 1;

        if (index < 0 || index >= daftarMahasiswa.size()) {
            System.out.println(" Nomor tidak valid!");
            return;
        }

        System.out.print("Nama baru    : ");
        String nama = scanner.nextLine().trim();

        System.out.print("NIM baru     : ");
        String nim = scanner.nextLine().trim();

        System.out.print("Jurusan baru : ");
        String jurusan = scanner.nextLine().trim();

        if (nama.isEmpty() || nim.isEmpty() || jurusan.isEmpty()) {
            System.out.println(" Data tidak boleh kosong!");
            return;
        }

        Mahasiswa m = daftarMahasiswa.get(index);
        m.nama = nama;
        m.nim = nim;
        m.jurusan = jurusan;
        System.out.println(" Data mahasiswa berhasil diperbarui.");
    }

    static void hapusMahasiswa() {
        System.out.println("\n--- HAPUS MAHASISWA ---");

        if (daftarMahasiswa.isEmpty()) {
            System.out.println(" Belum ada data mahasiswa.");
            return;
        }

        lihatMahasiswa();
        System.out.print("Masukkan nomor mahasiswa yang ingin dihapus : ");
        int index = inputAngka() - 1;

        if (index < 0 || index >= daftarMahasiswa.size()) {
            System.out.println(" Nomor tidak valid!.");
            return;
        }

        Mahasiswa m = daftarMahasiswa.get(index);
        System.out.println(" Mahasiswa " + m.nama + " berhasil dihapus.");
        daftarMahasiswa.remove(index);
    }

    static int inputAngka() {
        while (true) {
            try {
                String input = scanner.nextLine();
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.print(" Input harus berupa angka. Coba lagi yaa: ");
            }
        }
    }
}
