# SOLID pada kode Aset-Pinjam (minggu 6)

Dua prinsip yang sengaja ditunjukkan di sini adalah **OCP** dan **DIP**. Keduanya dipilih karena
hubungannya langsung dengan `AturanDenda`, dan keduanya bisa dicek dari kode yang ada sekarang.

## 1. Open/Closed Principle (OCP)

**Kelas terkait:** `id.ac.upb.asetpinjam.kontrak.AturanDenda`,
`id.ac.upb.asetpinjam.denda.DendaFlat`, `id.ac.upb.asetpinjam.denda.DendaProgresif`,
`id.ac.upb.asetpinjam.model.Aset` beserta `Ruang`, `Laptop`, dan `AlatLab`.

`AturanDenda` adalah kontrak `long hitung(Aset aset, int hariTerlambat)`. `DendaFlat` dan
`DendaProgresif` masing-masing menerapkan kontrak itu tanpa saling mengenal. Menambah aturan baru
(misalnya denda tetap per minggu) cukup membuat kelas baru yang mengimplementasikan `AturanDenda`;
`LayananDenda` dan kedua kelas lama tidak perlu diubah. Hal yang sama berlaku untuk hierarki aset:
multiplier denda ditempatkan di `hitungDenda(int)` yang dioverride tiap subclass, sedangkan
`Aset.hitungDendaDenganPengali(int, int)` tetap menjadi perilaku bersama. Menambah jenis aset baru
tidak mengharuskan percabangan `if`/`switch` di kelas induk. Jadi kode terbuka untuk perluasan,
tertutup untuk modifikasi.

## 2. Dependency Inversion Principle (DIP)

**Kelas terkait:** `id.ac.upb.asetpinjam.denda.LayananDenda`,
`id.ac.upb.asetpinjam.kontrak.AturanDenda`, `id.ac.upb.asetpinjam.model.Aset`,
serta `id.ac.upb.asetpinjam.dao.AsetDAO` / `JdbcAsetDAO`.

`LayananDenda` tidak pernah menyebut `DendaFlat` atau `DendaProgresif`. Dependensinya hanya
berupa antarmuka `AturanDenda`, diterima lewat konstruktor dan disimpan dalam field `final`.
Refactor kecil yang dilakukan pada minggu 6 adalah mengisi badan konstruktor dan field tersebut
(sebelumnya masih TODO) serta meneruskan panggilan ke `aturan.hitung(aset, hariTerlambat)`.
Dengan demikian perilakunya dapat diuji memakai implementasi sederhana/lambda, tanpa perlu
membuat objek aset nyata atau mengubah kelas denda. Pola yang sama terlihat pada `AsetDAO`:
`JdbcPeminjamanDAO` bergantung pada antarmuka `AsetDAO`, bukan pada `JdbcAsetDAO` konkret.
Modul tingkat tinggi bergantung pada abstraksi, dan detail bergantung pada abstraksi yang sama.

## 3. Single Responsibility Principle (SRP) — tambahan

`KatalogAset` hanya menyimpan dan mencari aset; `JadwalPeminjaman` hanya mengelola jadwal;
`Peminjaman` hanya menyimpan data satu pengajuan beserta statusnya; `Varian` hanya membaca
parameter aturan dari `mahasiswa.properties`. Perubahan pada format pencarian tidak perlu
menyentuh logika jadwal, dan sebaliknya.

## Catatan istilah (bahan kuis)

- **Aggregation vs composition:** `JadwalPeminjaman o-- "0..*" Peminjaman` adalah agregasi karena
  `hapus(id)` dapat melepaskan peminjaman tanpa menghancurkannya; objek `Peminjaman` memiliki
  siklus hidup sendiri. `Peminjaman --> Peminjam` digambarkan sebagai asosiasi biasa karena
  `Peminjam` dibuat di luar `Peminjaman` dan tetap bisa dipakai setelah pengajuan dihapus.
  Komposisi akan dipakai bila objek induk benar-benar membuat dan menguasai siklus hidup objek
  bagiannya; di kode ini tidak ada hubungan seperti itu, jadi tidak saya klaim sebagai komposisi.
- **Mengapa OCP memudahkan pengembangan:** fitur baru ditambahkan sebagai kelas baru, sehingga
  kode lama yang sudah diuji tidak perlu diubah dan risiko regresi lebih kecil.
- **Mengapa DIP meningkatkan testability:** dependensi berupa antarmuka dapat diganti dengan
  *stub*, *fake*, atau lambda di tes, sehingga tes tidak bergantung pada database, koneksi, atau
  implementasi konkret.
