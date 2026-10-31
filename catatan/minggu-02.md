# Catatan Minggu 02: Class, Object, dan Encapsulation

## Yang saya kerjakan

Saya melengkapi class `Aset` dengan empat field private, yaitu `kode`, `nama`, `status`, dan
`kondisi`. Field `kode` saya buat `final` karena kode adalah identitas aset yang tidak boleh berubah.
Konstruktor memeriksa agar `kode` dan `nama` tidak null atau kosong, kemudian mengisi nilai awal
`status = TERSEDIA` dan `kondisi = BAIK`. Saya juga menambahkan getter untuk semua field dan setter
hanya untuk `nama`, `status`, serta `kondisi`. Setelah itu saya menjalankan tes minggu 2 dan keenam
tes lulus.

## Keputusan desain

- **Kode tidak dinormalisasi.** Nilai `kode` disimpan apa adanya, tanpa `trim` atau diubah menjadi
  huruf besar. Saya memilih cara ini karena kode inventaris bisa saja memuat huruf kecil atau spasi
  yang memang bermakna, sehingga mengubahnya diam-diam bisa membuat kode tidak cocok dengan data
  lain. Walaupun begitu, `null`, string kosong, dan string yang hanya berisi spasi tetap ditolak.
- **`kode` dibuat `final` dan tanpa setter.** Kode tidak boleh diubah setelah objek dibuat, jadi
  tidak ada metode `setKode`.
- **Validasi ada di konstruktor dan setter.** Tujuannya agar objek tidak pernah berada dalam keadaan
  tidak valid, baik saat pertama kali dibuat maupun saat nilainya diubah lewat setter.
- **`status` dan `kondisi` memakai enum.** Enum membatasi nilai yang boleh dipakai, sedangkan setter
  tetap menolak `null` agar field tidak pernah kosong.
- **Getter mengembalikan nilai apa adanya.** `String` dan enum tidak bisa diubah isinya, jadi tidak
  perlu membuat salinan tambahan.

## Kendala

Kendala pertama adalah membedakan string kosong dengan string yang hanya berisi spasi. Saya
menggunakan `isBlank()` supaya nilai seperti `"   "` juga ditolak, bukan hanya `""`. Saya juga
memastikan melalui tes refleksi bahwa semua field benar-benar private dan tidak ada metode
`setKode`. Kendala berikutnya, `minggu-aktif.txt` masih berisi minggu 1, jadi saya menjalankan tes
dengan `.\scripts\uji.ps1 -Minggu 2` agar tes minggu 2 ikut dijalankan.


## Penggunaan dan Pemahaman Materi

- Alat yang dipakai: Java dan Visual Studio Code.
- Bagian yang dikerjakan: Membuat class `Aset` dengan empat atribut private, yaitu `kode`, `nama`, `status`, dan `kondisi`. Menambahkan konstruktor, getter, setter, serta validasi data.
- Langkah pengerjaan:
  1. Membuat atribut menggunakan access modifier `private`.
  2. Menetapkan `kode` sebagai `final` agar tidak dapat diubah.
  3. Membuat konstruktor untuk memberikan nilai awal pada objek.
  4. Menambahkan getter dan setter sesuai kebutuhan.
  5. Menambahkan validasi agar data tidak kosong atau bernilai `null`.
  6. Menjalankan pengujian Minggu 02 dan memastikan keenam tes berhasil.
- Keputusan desain: Tidak membuat setter untuk `kode` karena kode merupakan identitas tetap aset. Menggunakan enum untuk membatasi pilihan `status` dan `kondisi`.
- Satu hal yang saya pahami lebih baik sekarang: Saya memahami bahwa encapsulation digunakan untuk melindungi data dalam sebuah class. Dengan menggunakan `private`, getter, setter, dan validasi, data objek dapat dikontrol sehingga tidak mudah diubah sembarangan dan tetap sesuai aturan.