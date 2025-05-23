import java.util.Scanner;

public class Coba {

    // Method tanpa parameter
    public static void sapa() {
        System.out.println("Halo, selamat datang di program Java!");
    }

    // Method dengan parameter
    public static void sapaNama(String nama) {
        System.out.println("Halo, " + nama + "!");
    }

    // Method dengan return value
    public static int tambah(int a, int b) {
        return a + b;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Method
        sapa();
        System.out.print("Masukkan nama Anda: ");
        String nama = scanner.nextLine();
        sapaNama(nama);

        // Percabangan if-else-if
        System.out.print("Masukkan nilai Anda: ");
        int nilai = scanner.nextInt();

        if (nilai >= 80) {
            System.out.println("Nilai A");
        } else if (nilai >= 70) {
            System.out.println("Nilai B");
        } else if (nilai >= 60) {
            System.out.println("Nilai C");
        } else {
            System.out.println("Nilai D");
        }

        // Percabangan switch
        System.out.print("Masukkan angka hari (1-7): ");
        int hari = scanner.nextInt();
        switch (hari) {
            case 1: System.out.println("Senin"); break;
            case 2: System.out.println("Selasa"); break;
            case 3: System.out.println("Rabu"); break;
            case 4: System.out.println("Kamis"); break;
            case 5: System.out.println("Jumat"); break;
            case 6: System.out.println("Sabtu"); break;
            case 7: System.out.println("Minggu"); break;
            default: System.out.println("Hari tidak valid!");
        }

        // Perulangan for
        System.out.println("\nPerulangan for:");
        for (int i = 1; i <= 5; i++) {
            System.out.println("Iterasi ke-" + i);
        }

        // Perulangan while
        System.out.println("\nPerulangan while:");
        int i = 1;
        while (i <= 5) {
            System.out.println("Iterasi ke-" + i);
            i++;
        }

        // Perulangan do-while
        System.out.println("\nPerulangan do-while:");
        int j = 1;
        do {
            System.out.println("Langkah ke-" + j);
            j++;
        } while (j <= 5);

        // Method dengan return value
        System.out.print("\nMasukkan dua angka untuk ditambahkan:\nAngka 1: ");
        int a = scanner.nextInt();
        System.out.print("Angka 2: ");
        int b = scanner.nextInt();
        int hasil = tambah(a, b);
        System.out.println("Hasil penjumlahan: " + hasil);

        scanner.close();
    }
}
