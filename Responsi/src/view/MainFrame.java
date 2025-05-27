// File: view/MainFrame.java
package view;

import model.Produk;
import controller.ProdukController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class MainFrame extends JFrame {
    private JTextField tfNama, tfUnit, tfJam, tfTenaga, tfBahan;
    private JTable table;
    private DefaultTableModel tableModel;

    public MainFrame() {
        setTitle("PABRIK BELA NEGARA");
        setSize(800, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel inputPanel = new JPanel(new GridLayout(6, 2));
        tfNama = new JTextField();
        tfUnit = new JTextField();
        tfJam = new JTextField();
        tfTenaga = new JTextField();
        tfBahan = new JTextField();

        inputPanel.add(new JLabel("Nama Produk")); inputPanel.add(tfNama);
        inputPanel.add(new JLabel("Jumlah Unit")); inputPanel.add(tfUnit);
        inputPanel.add(new JLabel("Jam Kerja")); inputPanel.add(tfJam);
        inputPanel.add(new JLabel("Jumlah Tenaga Kerja")); inputPanel.add(tfTenaga);
        inputPanel.add(new JLabel("Biaya Bahan Baku")); inputPanel.add(tfBahan);

        JButton btnCreate = new JButton("Create");
        JButton btnEdit = new JButton("Edit");
        JButton btnDelete = new JButton("Delete");

        btnCreate.addActionListener(e -> tambahData());
        btnEdit.addActionListener(e -> editData());
        btnDelete.addActionListener(e -> hapusData());

        inputPanel.add(btnCreate); inputPanel.add(btnEdit);

        String[] kolom = {"Nama Produk", "Biaya Tenaga Kerja", "Efisiensi Produksi", "Total Biaya Produksi"};
        tableModel = new DefaultTableModel(kolom, 0);
        table = new JTable(tableModel);

        loadData();

        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.add(new JScrollPane(table), BorderLayout.CENTER);
        rightPanel.add(btnDelete, BorderLayout.SOUTH);

        setLayout(new BorderLayout());
        add(inputPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.CENTER);
    }

    private void loadData() {
        tableModel.setRowCount(0);
        List<Produk> list = ProdukController.getAllProduk();
        for (Produk p : list) {
            Object[] row = {p.getNamaProduk(), p.getBiayaTenagaKerja(), p.getEfisiensiProduksi(), p.getTotalBiaya()};
            tableModel.addRow(row);
        }
    }

    private void tambahData() {
        try {
            String nama = tfNama.getText();
            int unit = Integer.parseInt(tfUnit.getText());
            int jam = Integer.parseInt(tfJam.getText());
            int tenaga = Integer.parseInt(tfTenaga.getText());
            int bahan = Integer.parseInt(tfBahan.getText());

            Produk p = new Produk(nama, unit, jam, tenaga, bahan);
            if (ProdukController.tambahProduk(p)) {
                loadData();
                JOptionPane.showMessageDialog(this, "Data berhasil ditambahkan.");
            } else {
                JOptionPane.showMessageDialog(this, "Efisiensi < 20%. Data tidak disimpan.");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Input tidak valid.");
        }
    }

    private void hapusData() {
        int selected = table.getSelectedRow();
        if (selected >= 0) {
            String nama = tableModel.getValueAt(selected, 0).toString();
            if (ProdukController.deleteProduk(nama)) {
                loadData();
                JOptionPane.showMessageDialog(this, "Data berhasil dihapus.");
            } else {
                JOptionPane.showMessageDialog(this, "Gagal menghapus data.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Pilih data terlebih dahulu.");
        }
    }

    private void editData() {
        try {
            String nama = tfNama.getText();
            int unit = Integer.parseInt(tfUnit.getText());
            int jam = Integer.parseInt(tfJam.getText());
            int tenaga = Integer.parseInt(tfTenaga.getText());
            int bahan = Integer.parseInt(tfBahan.getText());

            Produk p = new Produk(nama, unit, jam, tenaga, bahan);
            if (ProdukController.updateProduk(p)) {
                loadData();
                JOptionPane.showMessageDialog(this, "Data berhasil diupdate.");
            } else {
                JOptionPane.showMessageDialog(this, "Efisiensi < 20%. Data tidak diupdate.");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Input tidak valid.");
        }
    }
}