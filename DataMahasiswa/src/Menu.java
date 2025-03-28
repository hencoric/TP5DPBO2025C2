import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Menu extends JFrame{
    public static void main(String[] args) {
        // buat object window
        Menu window = new Menu();

        // atur ukuran window
        window.setSize(480, 560);

        // letakkan window di tengah layar
        window.setLocationRelativeTo(null);

        // isi window
        window.setContentPane(window.mainPanel);

        // ubah warna background
        window.getContentPane().setBackground(Color.white);

        // tampilkan window
        window.setVisible(true);

        // agar program ikut berhenti saat window diclose
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    // index baris yang diklik
    private int selectedIndex = -1;
    // list untuk menampung semua mahasiswa
    private ArrayList<Mahasiswa> listMahasiswa;

    private Database database;

    private JPanel mainPanel;
    private JTextField nimField;
    private JTextField namaField;
    private JTable mahasiswaTable;
    private JButton addUpdateButton;
    private JButton cancelButton;
    private JComboBox jenisKelaminComboBox;
    private JButton deleteButton;
    private JLabel titleLabel;
    private JLabel nimLabel;
    private JLabel namaLabel;
    private JLabel jenisKelaminLabel;
    private JTextField programField;
    private JLabel tingkatLabel;
    private JRadioButton s1RadioButton;
    private JRadioButton s2RadioButton;
    private JRadioButton d4RadioButton;
    private ButtonGroup tingkatGroup;

    public Menu() {
        listMahasiswa = new ArrayList<>();
        database = new Database();
        populateList();
        mahasiswaTable.setModel(setTable());
        String[] jenisKelaminData = {"", "Laki-laki", "Perempuan"};
        jenisKelaminComboBox.setModel(new DefaultComboBoxModel(jenisKelaminData));
        tingkatGroup = new ButtonGroup();
        tingkatGroup.add(s1RadioButton);
        tingkatGroup.add(s2RadioButton);
        tingkatGroup.add(d4RadioButton);
        deleteButton.setVisible(false);

        addUpdateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedIndex == -1) {
                    insertData();
                } else {
                    updateData();
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedIndex >= 0) {
                    deleteData();
                }
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearForm();
            }
        });

        mahasiswaTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                selectedIndex = mahasiswaTable.getSelectedRow();
                nimField.setText(mahasiswaTable.getValueAt(selectedIndex, 1).toString());
                namaField.setText(mahasiswaTable.getValueAt(selectedIndex, 2).toString());
                jenisKelaminComboBox.setSelectedItem(mahasiswaTable.getValueAt(selectedIndex, 3).toString());
                selectRadioButton(mahasiswaTable.getValueAt(selectedIndex, 4).toString());
                addUpdateButton.setText("Update");
                deleteButton.setVisible(true);
            }
        });
    }

    public DefaultTableModel setTable() {
        Object[] column = {"No", "NIM", "Nama", "Jenis Kelamin", "Tingkat Pendidikan"};
        DefaultTableModel temp = new DefaultTableModel(null, column);
        try {
            ResultSet resultSet = database.selectQuery("SELECT * FROM mahasiswa");
            int i = 0;
            while (resultSet.next()){
                temp.addRow(new Object[]{i + 1, resultSet.getString("nim"), resultSet.getString("nama"),
                        resultSet.getString("jenis_kelamin"), resultSet.getString("jenjang")});
                i++;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Gagal mengambil data dari database: " + e.getMessage());
        }
        return temp;
    }

    public void insertData() {
        String nim = nimField.getText().trim();
        String nama = namaField.getText().trim();
        String jenisKelamin = jenisKelaminComboBox.getSelectedItem().toString();
        String tingkat = getSelectedTingkat();

        if (nim.isEmpty() || nama.isEmpty() || jenisKelamin.isEmpty() || tingkat.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Harap isi semua field!");
            return;
        }

        if (isNimExists(nim)) {
            JOptionPane.showMessageDialog(null, "NIM sudah terdaftar!");
            return;
        }

        String sql = "INSERT INTO mahasiswa (nim, nama, jenis_kelamin, jenjang) VALUES ('" + nim + "', '" + nama + "', '" + jenisKelamin + "', '" + tingkat + "');";
        database.insertUpdateDeleteQuery(sql);
        mahasiswaTable.setModel(setTable());
        clearForm();
        JOptionPane.showMessageDialog(null, "Data berhasil ditambahkan");
    }

    public void updateData() {
        String oldNim = mahasiswaTable.getValueAt(selectedIndex, 1).toString(); // Ambil NIM lama
        String nim = nimField.getText().trim();
        String nama = namaField.getText().trim();
        String jenisKelamin = jenisKelaminComboBox.getSelectedItem().toString();
        String tingkat = getSelectedTingkat();

        if (nim.isEmpty() || nama.isEmpty() || jenisKelamin.isEmpty() || tingkat.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Harap isi semua field!");
            return;
        }

        // Jika NIM diubah, pastikan NIM baru belum ada di database
        if (!nim.equals(oldNim) && isNimExists(nim)) {
            JOptionPane.showMessageDialog(null, "NIM sudah terdaftar! Gunakan NIM lain.");
            return;
        }

        // Lakukan update
        String sql = "UPDATE mahasiswa SET nim='" + nim + "', nama='" + nama + "', jenis_kelamin='" + jenisKelamin + "', jenjang='" + tingkat + "' WHERE nim='" + oldNim + "';";
        database.insertUpdateDeleteQuery(sql);
        mahasiswaTable.setModel(setTable());
        clearForm();
        JOptionPane.showMessageDialog(null, "Data berhasil diubah!");
    }


    public void deleteData() {
        int confirm = JOptionPane.showConfirmDialog(null, "Apakah Anda yakin ingin menghapus data ini?", "Konfirmasi Hapus", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            String nim = nimField.getText();
            String sql = "DELETE FROM mahasiswa WHERE nim='" + nim + "';";
            database.insertUpdateDeleteQuery(sql);
            mahasiswaTable.setModel(setTable());
            clearForm();
            JOptionPane.showMessageDialog(null, "Data berhasil dihapus!");
        }
    }

    public boolean isNimExists(String nim) {
        try {
            ResultSet rs = database.selectQuery("SELECT nim FROM mahasiswa WHERE nim='" + nim + "'");
            return rs.next();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Gagal mengecek NIM: " + e.getMessage());
        }
        return false;
    }

    public void clearForm() {
        nimField.setText("");
        namaField.setText("");
        jenisKelaminComboBox.setSelectedItem("");
        tingkatGroup.clearSelection();
        addUpdateButton.setText("Add");
        deleteButton.setVisible(false);
        selectedIndex = -1;
    }

    private void selectRadioButton(String tingkat) {
        if (tingkat.equals("S1")) {
            s1RadioButton.setSelected(true);
        } else if (tingkat.equals("S2")) {
            s2RadioButton.setSelected(true);
        } else if (tingkat.equals("D4")) {
            d4RadioButton.setSelected(true);
        }
    }

    private String getSelectedTingkat() {
        if (s1RadioButton.isSelected()) return "S1";
        if (s2RadioButton.isSelected()) return "S2";
        if (d4RadioButton.isSelected()) return "D4";
        return "";
    }

    private void populateList() {
        listMahasiswa.add(new Mahasiswa("2203999", "Amelia Zalfa Julianti", "Perempuan", "S1"));
        listMahasiswa.add(new Mahasiswa("2202292", "Muhammad Iqbal Fadhilah", "Laki-laki", "S1"));
        listMahasiswa.add(new Mahasiswa("2202346", "Muhammad Rifky Afandi", "Laki-laki", "S2"));
        listMahasiswa.add(new Mahasiswa("2210239", "Muhammad Hanif Abdillah", "Laki-laki", "D4"));
        listMahasiswa.add(new Mahasiswa("2202046", "Nurainun", "Perempuan", "S1"));
        listMahasiswa.add(new Mahasiswa("2205101", "Kelvin Julian Putra", "Laki-laki", "S2"));
        listMahasiswa.add(new Mahasiswa("2200163", "Rifanny Lysara Annastasya", "Perempuan", "D4"));
        listMahasiswa.add(new Mahasiswa("2202869", "Revana Faliha Salma", "Perempuan", "S1"));
        listMahasiswa.add(new Mahasiswa("2209489", "Rakha Dhifiargo Hariadi", "Laki-laki", "S2"));
        listMahasiswa.add(new Mahasiswa("2203142", "Roshan Syalwan Nurilham", "Laki-laki", "D4"));
        listMahasiswa.add(new Mahasiswa("2200311", "Raden Rahman Ismail", "Laki-laki", "S1"));
        listMahasiswa.add(new Mahasiswa("2200978", "Ratu Syahirah Khairunnisa", "Perempuan", "S2"));
        listMahasiswa.add(new Mahasiswa("2204509", "Muhammad Fahreza Fauzan", "Laki-laki", "D4"));
        listMahasiswa.add(new Mahasiswa("2205027", "Muhammad Rizki Revandi", "Laki-laki", "S1"));
        listMahasiswa.add(new Mahasiswa("2203484", "Arya Aydin Margono", "Laki-laki", "S2"));
        listMahasiswa.add(new Mahasiswa("2200481", "Marvel Ravindra Dioputra", "Laki-laki", "D4"));
        listMahasiswa.add(new Mahasiswa("2209889", "Muhammad Fadlul Hafiizh", "Laki-laki", "S1"));
        listMahasiswa.add(new Mahasiswa("2206697", "Rifa Sania", "Perempuan", "S2"));
        listMahasiswa.add(new Mahasiswa("2207260", "Imam Chalish Rafidhul Haque", "Laki-laki", "D4"));
        listMahasiswa.add(new Mahasiswa("2204343", "Meiva Labibah Putri", "Perempuan", "S1"));
    }
}
