import java.util.Queue;
import java.util.LinkedList;
import java.util.Scanner;

class Pelanggan {
    public String nama;
    public String noTelepon;
    public Pelanggan(String nama, String noTelepon) {
        this.nama = nama;
        this.noTelepon = noTelepon;
    }
    public void displayInfo() {
        System.out.println("Nama Pelanggan: " + nama);
        System.out.println("Nomor Telepon: " + noTelepon);
    }
}

class PelangganWarungTelepon extends Pelanggan {
    public PelangganWarungTelepon(String nama, String noTelepon) {
        super(nama, noTelepon);
    }
}

interface Antrian {
    void tambahAntrian(Pelanggan pelanggan);
    Pelanggan panggilPelanggan();
    void lihatAntrian();
}

class AntrianWarungTelepon implements Antrian {
    // Implementasi penampungan sementara untuk antrian
    private Queue<Pelanggan> antrianPelanggan = new LinkedList<>();

    @Override
    public void tambahAntrian(Pelanggan pelanggan) {
        antrianPelanggan.add(pelanggan);
        System.out.println("Pelanggan " + pelanggan.nama + " telah ditambahkan ke antrian.");
    }

    @Override
    public Pelanggan panggilPelanggan() {
        if (!antrianPelanggan.isEmpty()) {
            Pelanggan pelanggan = antrianPelanggan.poll();
            System.out.println("Pelanggan " + pelanggan.nama + " dipanggil.");
            return pelanggan;
        } else {
            System.out.println("Antrian kosong.");
            return null;
        }
    }

    @Override
    public void lihatAntrian() {
        if (!antrianPelanggan.isEmpty()) {
            System.out.println("Antrian saat ini:");
            for (Pelanggan pelanggan : antrianPelanggan) {
                System.out.println("- " + pelanggan.nama);
            }
        } else {
            System.out.println("Antrian kosong.");
        }
    }
}

public class WarungTelepon {
    public Antrian antrian;

    public WarungTelepon(Antrian antrian) {
        this.antrian = antrian;
    }

    public void mulai() {
        Scanner scanner = new Scanner(System.in);
        int pilihan;

        do {
            System.out.println("\nMenu:");
            System.out.println("1. Tambah Pelanggan ke Antrian");
            System.out.println("2. Panggil Pelanggan");
            System.out.println("3. Lihat Antrian");
            System.out.println("4. Keluar");
            System.out.print("Pilihan Anda: ");
            pilihan = scanner.nextInt();

            switch (pilihan) {
                case 1:
                    scanner.nextLine(); // Membersihkan buffer
                    System.out.print("Masukkan Nama Pelanggan: ");
                    String nama = scanner.nextLine();
                    System.out.print("Masukkan Nomor Telepon: ");
                    String noTelp = scanner.nextLine();
                    antrian.tambahAntrian(new PelangganWarungTelepon(nama, noTelp));
                    break;
                case 2:
                    Pelanggan pelanggan = antrian.panggilPelanggan();
                    if (pelanggan != null) {
                        pelanggan.displayInfo();
                    }
                    break;
                case 3:
                    antrian.lihatAntrian();
                    break;
                case 4:
                    System.out.println("Program selesai.");
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        } while (pilihan != 4);
    }

    public static void main(String[] args) {
        Antrian antrian = new AntrianWarungTelepon();
        WarungTelepon warungTelepon = new WarungTelepon(antrian);
        warungTelepon.mulai();
    }
}
