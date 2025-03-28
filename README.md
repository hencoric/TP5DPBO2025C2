# JANJI

Saya Marco Henrik Abineno dengan NIM 2301093 berjanji mengerjakan TP5 DPBO dengan keberkahan-Nya, maka saya tidak akan melakukan kecurangan sesuai yang telah di spesifikasikan, Aamiin  
  
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

## c. Memilih Data di Tabel
- Pengguna mengklik baris data dalam JTable.
- Data dari tabel dimasukkan ke dalam input field.
- Tombol Add berubah menjadi Update.
- Tombol Delete ditampilkan.

## d. Memperbarui Data Mahasiswa
- Pengguna mengubah data mahasiswa yang telah dipilih.
- Klik tombol Update.
- Jika NIM diubah, sistem memeriksa apakah NIM baru sudah ada di database.
- Jika ada, muncul notifikasi bahwa NIM sudah digunakan.
- Jika tidak, data diperbarui di database menggunakan UPDATE mahasiswa SET ... WHERE nim=....
- Tabel diperbarui.
- Formulir dikosongkan.
- Notifikasi sukses muncul.

## e. Menghapus Data Mahasiswa
- Pengguna memilih data di tabel.
- Klik tombol Delete.
- Konfirmasi penghapusan muncul.
- Jika memilih Yes, data dihapus dari database menggunakan DELETE FROM mahasiswa WHERE nim=....
- Tabel diperbarui.
- Formulir dikosongkan.
- Notifikasi sukses muncul.

# DOKUMENTASI

### ADD

1. Input Data  
   ![image](https://github.com/user-attachments/assets/2abad730-0678-49dc-b906-43226a7233ff)

2. Tekan Tombol Add dan akan muncul pemberitahuan sukses dan formulir dikosongkan  
   ![image](https://github.com/user-attachments/assets/e07cd6a0-b31c-4628-8362-52cdaefbb794)

3. ERROR Jika NIM sudah ada
   ![image](https://github.com/user-attachments/assets/11883aec-1de9-467d-bbb8-4bffcdafb0dc)
     
4. Dalam Database  
   ![image](https://github.com/user-attachments/assets/07931f73-0f25-419e-accc-30f5a06b77ef)


### UPDATE 

1. Pilih data yang ingin di update dan ganti data yang sudah ada  
   ![image](https://github.com/user-attachments/assets/2396eb60-4273-48cf-ba09-ebe45d33409f)

2. Tekan tombol update akan muncul pemberitahuan sukses dan formulir dikosongkan  
   ![image](https://github.com/user-attachments/assets/93e8851d-ebf1-4460-88b4-585a79ba3416)

3. ERROR Jika NIM sudah ada  
   ![image](https://github.com/user-attachments/assets/c5744585-7396-4274-919b-0ed0ff9d9399)

5. Dalam Database  
   ![image](https://github.com/user-attachments/assets/112eebb8-39fc-438b-8a5b-c68874c03b08)

### DELETE

1. Pilih data yang ingin di hapus dan tekan delete  
   ![image](https://github.com/user-attachments/assets/29399ebd-299e-48bc-9525-e2f1939003ad)
 
2. Tekan Yes  
   ![image](https://github.com/user-attachments/assets/d291171d-1b72-4a74-a8f2-9e2f145052f0)

3. Akan Ada pemberitahuan sukses  
   ![image](https://github.com/user-attachments/assets/d469cf35-1fde-4f41-a7fa-947b994d483a)
  
4. Dalam Database
   ![image](https://github.com/user-attachments/assets/040a9807-f1a9-46ab-ae15-4449696e5462)

