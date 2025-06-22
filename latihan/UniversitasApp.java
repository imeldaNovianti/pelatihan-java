import java.util.Scanner;

interface Aktivitas {
    void kuliah();
}

abstract class Mahasiswa implements Aktivitas {
    protected String nama;
    protected int angkatan;

    public Mahasiswa(String nama, int angkatan) {
        this.nama = nama;
        this.angkatan = angkatan;
    }

    public void infoMahasiswa() {
        System.out.println("Nama: " + nama);
        System.out.println("Angkatan: " + angkatan);
    }

    public void infoMahasiswa(String jurusan) {
        System.out.println("Nama: " + nama);
        System.out.println("Angkatan: " + angkatan);
        System.out.println("Jurusan: " + jurusan);
    }
}

class MahasiswaS1 extends Mahasiswa {
    private String jurusan;

    public MahasiswaS1(String nama, int angkatan, String jurusan) {
        super(nama, angkatan);
        this.jurusan = jurusan;
    }

    @Override
    public void kuliah() {
        System.out.println(nama + " sedang mengikuti kuliah di jurusan " + jurusan + " program S1.");
    }

    @Override
    public void infoMahasiswa() {
        super.infoMahasiswa(jurusan);
    }
}

class MahasiswaS2 extends Mahasiswa {
    private String programStudi;

    public MahasiswaS2(String nama, int angkatan, String programStudi) {
        super(nama, angkatan);
        this.programStudi = programStudi;
    }

    @Override
    public void kuliah() {
        System.out.println(nama + " sedang mengikuti kuliah di program magister " + programStudi + ".");
    }

    @Override
    public void infoMahasiswa() {
        super.infoMahasiswa(programStudi);
        System.out.println("Program: S2 (Magister)");
    }
}

public class UniversitasApp {

    // Method static untuk input data Mahasiswa S1
    public static MahasiswaS1 inputDataMahasiswaS1() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Masukkan nama mahasiswa S1: ");
        String nama = scanner.nextLine();
        System.out.print("Masukkan angkatan mahasiswa S1: ");
        int angkatan = scanner.nextInt();
        scanner.nextLine(); // menangkap enter

        System.out.print("Masukkan jurusan mahasiswa S1: ");
        String jurusan = scanner.nextLine();

        return new MahasiswaS1(nama, angkatan, jurusan);
    }

    // Method static untuk input data Mahasiswa S2
    public static MahasiswaS2 inputDataMahasiswaS2() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Masukkan nama mahasiswa S2: ");
        String nama = scanner.nextLine();
        System.out.print("Masukkan angkatan mahasiswa S2: ");
        int angkatan = scanner.nextInt();
        scanner.nextLine(); // menangkap enter

        System.out.print("Masukkan program studi mahasiswa S2: ");
        String programStudi = scanner.nextLine();

        return new MahasiswaS2(nama, angkatan, programStudi);
    }

    public static void main(String[] args) {
        System.out.println("Input data mahasiswa S1:");
        MahasiswaS1 mhs1 = inputDataMahasiswaS1();

        System.out.println("\nInput data mahasiswa S2:");
        MahasiswaS2 mhs2 = inputDataMahasiswaS2();

        System.out.println("\nInformasi Mahasiswa S1:");
        mhs1.infoMahasiswa();
        mhs1.kuliah();

        System.out.println("\nInformasi Mahasiswa S2:");
        mhs2.infoMahasiswa();
        mhs2.kuliah();

        System.out.println("\nDemo overloading info mahasiswa S1:");
        mhs1.infoMahasiswa("Teknik Informatika - Fakultas Teknik");
    }
}