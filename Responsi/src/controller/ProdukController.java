// File: controller/ProdukController.java
package controller;

import model.Produk;
import java.sql.*;
import java.util.*;

public class ProdukController {
    public static boolean tambahProduk(Produk p) {
        if (p.getEfisiensiProduksi() < 20) return false;
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "INSERT INTO produksi (nama_produk, biaya_tenaga_kerja, efisiensi_produksi, total_biaya) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, p.getNamaProduk());
            stmt.setInt(2, p.getBiayaTenagaKerja());
            stmt.setDouble(3, p.getEfisiensiProduksi());
            stmt.setInt(4, p.getTotalBiaya());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List<Produk> getAllProduk() {
        List<Produk> list = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "SELECT * FROM produksi";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Produk p = new Produk(
                    rs.getInt("id"),
                    rs.getString("nama_produk"),
                    0, 0, 0, 0
                );
                list.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static boolean deleteProduk(String nama) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "DELETE FROM produksi WHERE nama_produk = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nama);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean updateProduk(Produk p) {
        if (p.getEfisiensiProduksi() < 20) return false;
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "UPDATE produksi SET biaya_tenaga_kerja = ?, efisiensi_produksi = ?, total_biaya = ? WHERE nama_produk = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, p.getBiayaTenagaKerja());
            stmt.setDouble(2, p.getEfisiensiProduksi());
            stmt.setInt(3, p.getTotalBiaya());
            stmt.setString(4, p.getNamaProduk());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}

