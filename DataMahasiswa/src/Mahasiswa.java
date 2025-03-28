public class Mahasiswa {
    private String nim;
    private String nama;
    private String jenisKelamin;
    private String tingkatPendidikan;

    public Mahasiswa(String nim, String nama, String jenisKelamin, String tingkatPendidikan) {
        this.nim = nim;
        this.nama = nama;
        this.jenisKelamin = jenisKelamin;
        this.tingkatPendidikan = tingkatPendidikan;
    }

    // Getter dan Setter untuk semua atribut
    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public String getTingkatPendidikan() {
        return tingkatPendidikan;
    }

    public void setTingkatPendidikan(String tingkatPendidikan) {
        this.tingkatPendidikan = tingkatPendidikan;
    }
}