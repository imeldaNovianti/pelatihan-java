import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

interface BisaBertarung {
    void serang(Monster target);
    void serang(Monster target, String jurus);
}

interface BisaMenyembuhkan {
    void sembuhkan();
}

abstract class Monster {
    protected String nama;
    protected int kesehatan;
    protected int kekuatan;

    public Monster(String nama, int kesehatan, int kekuatan) {
        this.nama = nama;
        this.kesehatan = kesehatan;
        this.kekuatan = kekuatan;
    }

    public boolean masihHidup() {
        return kesehatan > 0;
    }

    public void terimaSerangan(int damage) {
        kesehatan -= damage;
        if (kesehatan < 0) {
            kesehatan = 0;
        }
        System.out.println("⚔️  " + nama + " menerima serangan sebesar " + damage + "!");
    }

    public abstract void tampilkanInfo();
}

class MonsterApi extends Monster implements BisaBertarung {
    public MonsterApi(String nama) {
        super(nama, 100, 20);
    }

    public void serang(Monster target) {
        System.out.println("🔥 " + nama + " menyerang " + target.nama + " dengan api!");
        target.terimaSerangan(kekuatan);
    }

    public void serang(Monster target, String jurus) {
        int damage = kekuatan + 15;
        System.out.println("🔥 " + nama + " menggunakan jurus *" + jurus + "*!");
        target.terimaSerangan(damage);
    }

    public void tampilkanInfo() {
        System.out.printf("[API] %-12s | HP: %-3d | Status: %s\n", nama, kesehatan, masihHidup() ? "HIDUP" : "KALAH");
    }
}

class MonsterAir extends Monster implements BisaBertarung, BisaMenyembuhkan {
    public MonsterAir(String nama) {
        super(nama, 110, 15);
    }

    public void serang(Monster target) {
        System.out.println("💧 " + nama + " menyerang " + target.nama + " dengan air!");
        target.terimaSerangan(kekuatan);
    }

    public void serang(Monster target, String jurus) {
        int damage = kekuatan + 10;
        System.out.println("💧 " + nama + " menggunakan jurus air *" + jurus + "*!");
        target.terimaSerangan(damage);
    }

    public void sembuhkan() {
        int heal = 20;
        kesehatan += heal;
        System.out.println("💚 " + nama + " menyembuhkan diri sebanyak " + heal + " HP!");
    }

    public void tampilkanInfo() {
        System.out.printf("[AIR] %-12s | HP: %-3d | Status: %s\n", nama, kesehatan, masihHidup() ? "HIDUP" : "KALAH");
    }
}

class MonsterListrik extends Monster implements BisaBertarung {
    public MonsterListrik(String nama) {
        super(nama, 90, 25);
    }

    public void serang(Monster target) {
        System.out.println("⚡ " + nama + " menyerang " + target.nama + " dengan listrik!");
        target.terimaSerangan(kekuatan);
    }

    public void serang(Monster target, String jurus) {
        int damage = kekuatan + 20;
        System.out.println("⚡ " + nama + " menggunakan jurus listrik *" + jurus + "*!");
        target.terimaSerangan(damage);
    }

    public void tampilkanInfo() {
        System.out.printf("[LISTRIK] %-10s | HP: %-3d | Status: %s\n", nama, kesehatan, masihHidup() ? "HIDUP" : "KALAH");
    }
}

public class ArenaPertarungan {
    static Scanner input = new Scanner(System.in);
    static Random random = new Random();
    static ArrayList<Monster> arena = new ArrayList<>();

    public static void main(String[] args) {
        arena.add(new MonsterApi("ApiRaksasa"));
        arena.add(new MonsterAir("AirSakti"));
        arena.add(new MonsterListrik("PetirCepat"));

        int pilihan;
        do {
            System.out.println("\n═════════════════════════════════");
            System.out.println("         MENU ARENA MONSTER      ");
            System.out.println("═════════════════════════════════");
            System.out.println("1. Tampilkan Semua Monster");
            System.out.println("2. Pertarungan Manual");
            System.out.println("3. Pertarungan Acak Otomatis");
            System.out.println("4. Keluar");
            System.out.print("Pilih menu: ");
            pilihan = input.nextInt();
            input.nextLine();

            switch (pilihan) {
                case 1:
                    tampilkanSemua();
                    break;
                case 2:
                    pertarunganManual();
                    break;
                case 3:
                    pertarunganAcak();
                    break;
                case 4:
                    System.out.println("👋 Keluar dari arena...");
                    break;
                default:
                    System.out.println("❌ Pilihan tidak tersedia.");
            }
        } while (pilihan != 4);
    }

    static void tampilkanSemua() {
        System.out.println("\n🧟‍♂️ DAFTAR MONSTER DI ARENA:");
        for (int i = 0; i < arena.size(); i++) {
            System.out.print(i + ". ");
            arena.get(i).tampilkanInfo();
        }
    }

    static void pertarunganManual() {
        tampilkanSemua();

        System.out.print("Pilih nomor monster penyerang: ");
        int penyerangIndex = input.nextInt();
        Monster penyerang = arena.get(penyerangIndex);
        input.nextLine();

        System.out.print("Pilih nomor monster target: ");
        int targetIndex = input.nextInt();
        Monster target = arena.get(targetIndex);
        input.nextLine();

        if (!penyerang.masihHidup() || !target.masihHidup()) {
            System.out.println("⚠️ Salah satu monster sudah kalah!");
            return;
        }

        if (penyerang instanceof BisaMenyembuhkan) {
            System.out.print("Aksi: 1) Serang 2) Serang pakai jurus 3) Sembuhkan: ");
        } else {
            System.out.print("Aksi: 1) Serang 2) Serang pakai jurus: ");
        }

        int aksi = input.nextInt();
        input.nextLine();

        switch (aksi) {
            case 1:
                ((BisaBertarung) penyerang).serang(target);
                break;
            case 2:
                System.out.print("Masukkan nama jurus: ");
                String jurus = input.nextLine();
                ((BisaBertarung) penyerang).serang(target, jurus);
                break;
            case 3:
                if (penyerang instanceof BisaMenyembuhkan) {
                    ((BisaMenyembuhkan) penyerang).sembuhkan();
                } else {
                    System.out.println("Monster ini tidak bisa menyembuhkan diri!");
                }
                break;
            default:
                System.out.println("❌ Aksi tidak dikenali.");
        }
    }

    static void pertarunganAcak() {
        ArrayList<Monster> hidup = new ArrayList<>();
        for (Monster m : arena) {
            if (m.masihHidup()) hidup.add(m);
        }

        if (hidup.size() < 2) {
            System.out.println("⚠️ Tidak cukup monster hidup untuk bertarung.");
            return;
        }

        Monster attacker = hidup.get(random.nextInt(hidup.size()));
        Monster target;
        do {
            target = hidup.get(random.nextInt(hidup.size()));
        } while (target == attacker);

        int aksi = random.nextInt((attacker instanceof BisaMenyembuhkan) ? 3 : 2);

        if (aksi == 0) {
            ((BisaBertarung) attacker).serang(target);
        } else if (aksi == 1) {
            ((BisaBertarung) attacker).serang(target, "Jurus Acak");
        } else if (aksi == 2 && attacker instanceof BisaMenyembuhkan) {
            ((BisaMenyembuhkan) attacker).sembuhkan();
        }
    }
}
