import java.util.Random;
import java.util.Scanner;

public class TebakAngkaAjaib {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Random rand = new Random();

        int angkaAjaib = rand.nextInt(100) + 1; // angka antara 1-100
        int percobaan = 5;
        boolean menang = false;

        System.out.println("🎲 Selamat datang di Permainan Tebak Angka Ajaib!");
        System.out.println("Saya telah memilih angka antara 1 sampai 100.");
        System.out.println("Kamu punya " + percobaan + " kali percobaan untuk menebaknya.");

        for (int i = 1; i <= percobaan; i++) {
            System.out.print("\nTebakan ke-" + i + ": ");
            int tebakan = input.nextInt();

            if (tebakan == angkaAjaib) {
                System.out.println("🎉 Selamat! Kamu berhasil menebak angka ajaib!");
                menang = true;
                break;
            } else if (tebakan < angkaAjaib) {
                System.out.println("Terlalu kecil!");
            } else {
                System.out.println("Terlalu besar!");
            }
        }

        if (!menang) {
            System.out.println("\n😢 Kamu kalah. Angka ajaibnya adalah: " + angkaAjaib);
        }

        input.close();
    }
}


// Mulai
//  |
//  |-- Generate angka ajaib secara acak (1–100)
//  |
//  |-- Set jumlah percobaan ke 5
//  |
//  |-- Tampilkan sambutan & instruksi
//  |
//  |-- Ulangi selama percobaan masih tersedia:
//  |     |
//  |     |-- Minta pengguna menebak angka
//  |     |
//  |     |-- Jika tebakan == angka ajaib → menang → tampilkan pesan
//  |     |-- Jika tebakan < angka ajaib → tampilkan "terlalu kecil"
//  |     |-- Jika tebakan > angka ajaib → tampilkan "terlalu besar"
//  |
//  |-- Jika tidak menang setelah 5 percobaan → tampilkan angka sebenarnya
//  |
// Selesai
