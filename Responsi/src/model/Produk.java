// File: model/Produk.java
package model;

public class Produk {
    private int id;
    private String namaProduk;
    private int jumlahUnit;
    private int jamKerja;
    private int jumlahTenagaKerja;
    private int biayaBahanBaku;
    private int biayaTenagaKerja;
    private double efisiensiProduksi;
    private int totalBiaya;

    public Produk(int id, String namaProduk, int jumlahUnit, int jamKerja, int jumlahTenagaKerja, int biayaBahanBaku) {
        this(namaProduk, jumlahUnit, jamKerja, jumlahTenagaKerja, biayaBahanBaku);
        this.id = id;
    }

    public Produk(String namaProduk, int jumlahUnit, int jamKerja, int jumlahTenagaKerja, int biayaBahanBaku) {
        this.namaProduk = namaProduk;
        this.jumlahUnit = jumlahUnit;
        this.jamKerja = jamKerja;
        this.jumlahTenagaKerja = jumlahTenagaKerja;
        this.biayaBahanBaku = biayaBahanBaku;
        this.biayaTenagaKerja = jamKerja * jumlahTenagaKerja * 15000;
        this.efisiensiProduksi = (jumlahUnit / (double)(jamKerja * jumlahTenagaKerja)) * 100;
        this.totalBiaya = biayaBahanBaku + biayaTenagaKerja;
    }

    public int getId() { return id; }
    public String getNamaProduk() { return namaProduk; }
    public int getBiayaTenagaKerja() { return biayaTenagaKerja; }
    public double getEfisiensiProduksi() { return efisiensiProduksi; }
    public int getTotalBiaya() { return totalBiaya; }
    public int getJumlahUnit() { return jumlahUnit; }
    public int getJamKerja() { return jamKerja; }
    public int getJumlahTenagaKerja() { return jumlahTenagaKerja; }
    public int getBiayaBahanBaku() { return biayaBahanBaku; }
}
