# DESAIN PROGRAM

Program ini adalah Aplikasi Manajemen Data Mahasiswa berbasis Java Swing yang memungkinkan pengguna untuk menambahkan, memperbarui, dan menghapus data mahasiswa yang tersimpan dalam database MySQL. Aplikasi ini memiliki antarmuka grafis sederhana dengan komponen utama sebagai berikut:


## 1. Kelas Menu (GUI Utama)

A. Menggunakan JFrame sebagai window utama.  
B. Menggunakan JTable untuk menampilkan daftar mahasiswa.  
C. Memiliki beberapa input field:    
- JTextField untuk NIM dan Nama.  
- JComboBox untuk memilih Jenis Kelamin.  
- JRadioButton untuk memilih Tingkat Pendidikan (S1, S2, D4).
   
### Dilengkapi tombol-tombol berikut:  
- Add/Update: Untuk menambah atau memperbarui data mahasiswa.
- Delete: Untuk menghapus data mahasiswa yang dipilih.  
- Cancel: Untuk mengosongkan input field dan mereset form.  
  
## 2. Kelas Mahasiswa (Model)  
A. Berfungsi sebagai representasi dari objek mahasiswa.  
B. Menyimpan informasi mahasiswa seperti NIM, Nama, Jenis Kelamin, dan Tingkat Pendidikan.  
C. Memiliki getter dan setter untuk mengakses serta mengubah data mahasiswa.  
  
## 3. Kelas Database (Penghubung ke Database MySQL)  
A. Menjalin koneksi ke database MySQL (db_mahasiswa).  
B. Menyediakan metode selectQuery() untuk mengambil data.  
C. Menyediakan metode insertUpdateDeleteQuery() untuk operasi tambah, ubah, dan hapus data.  
  
# ALUR PROGRAM  
  
## a. Saat Program Dijalankan  
- Program membuka window utama (Menu).  
- Data mahasiswa dari database diambil dan ditampilkan dalam JTable menggunakan populateList().  
- Tombol Delete disembunyikan hingga ada data yang dipilih.  
  
## b. Menambah Data Mahasiswa
- Pengguna mengisi form dengan NIM, Nama, Jenis Kelamin, dan Tingkat Pendidikan.
- Klik tombol Add.
- Sistem memeriksa apakah NIM sudah ada di database.
- Jika ada, muncul notifikasi bahwa NIM sudah terdaftar.
- Jika tidak ada, data disimpan ke dalam database menggunakan INSERT INTO mahasiswa.
- Tabel diperbarui untuk menampilkan data baru.
- Formulir dikosongkan (clearForm()).
- Muncul notifikasi sukses.

c. Memilih Data di Tabel

Pengguna mengklik baris data dalam JTable.

Data dari tabel dimasukkan ke dalam input field.

Tombol Add berubah menjadi Update.

Tombol Delete ditampilkan.

d. Memperbarui Data Mahasiswa

Pengguna mengubah data mahasiswa yang telah dipilih.

Klik tombol Update.

Jika NIM diubah, sistem memeriksa apakah NIM baru sudah ada di database.

Jika ada, muncul notifikasi bahwa NIM sudah digunakan.

Jika tidak, data diperbarui di database menggunakan UPDATE mahasiswa SET ... WHERE nim=....

Tabel diperbarui.

Formulir dikosongkan.

Notifikasi sukses muncul.

e. Menghapus Data Mahasiswa

Pengguna memilih data di tabel.

Klik tombol Delete.

Konfirmasi penghapusan muncul.

Jika memilih Yes, data dihapus dari database menggunakan DELETE FROM mahasiswa WHERE nim=....

Tabel diperbarui.

Formulir dikosongkan.

Notifikasi sukses muncul.
