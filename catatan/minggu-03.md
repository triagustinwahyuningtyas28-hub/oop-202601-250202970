# Catatan Minggu 03: Inheritance

## Yang saya kerjakan

Saya melengkapi tiga subclass dari `Aset`, yaitu `Ruang`, `Laptop`, dan `AlatLab`. Masing-masing
memanggil konstruktor induk melalui `super(kode, nama)` agar validasi kode dan nama tetap berjalan,
lalu menyimpan atribut khususnya sendiri. `Ruang` menyimpan `kapasitas`, `Laptop` menyimpan
`spesifikasi` dan `nomorInventaris`, sedangkan `AlatLab` menyimpan `laboratorium` dan
`tingkatRisiko`. Saya menambahkan getter untuk atribut baru dan meng-override `getJenis()` supaya
tiap objek mengembalikan `RUANG`, `LAPTOP`, atau `ALAT_LAB` sesuai kelasnya. Setelah itu saya
menjalankan tes minggu 1 sampai 3 dan seluruh 19 tes lulus.

## Keputusan desain

- **Field di induk:** `kode`, `nama`, `status`, dan `kondisi` tetap di `Aset` karena keempatnya
  dimiliki semua jenis aset dan aturan validasinya sama. Dengan begitu subclass tidak perlu
  menulis ulang validasi kode/nama atau mengatur nilai awal status dan kondisi.
- **Field di subclass:** `kapasitas` hanya masuk ke `Ruang`, `spesifikasi` dan `nomorInventaris`
  hanya ke `Laptop`, serta `laboratorium` dan `tingkatRisiko` hanya ke `AlatLab`. Field yang hanya
  bermakna untuk satu jenis tidak saya taruh di induk agar `Aset` tidak menyimpan data yang selalu
  kosong untuk jenis lain.
- **Field subclass dibuat `final`.** Kapasitas, spesifikasi, nomor inventaris, laboratorium, dan
  tingkat risiko tidak perlu berubah setelah objek dibuat, sehingga tidak ada setter dan objek
  selalu berada dalam keadaan valid.
- **Validasi dilakukan sebelum menyimpan nilai.** Kapasitas harus `> 0`; spesifikasi, nomor
  inventaris, dan laboratorium ditolak jika `null` atau kosong (termasuk hanya spasi); tingkat
  risiko ditolak jika `null`.
- **`getJenis()` di-override, bukan dicek dengan `instanceof`.** Setiap subclass tahu jenisnya
  sendiri, sehingga pemanggil cukup memakai `Aset::getJenis` tanpa mengetahui kelas konkretnya.

## Keterbatasan inheritance dan contoh yang lebih cocok dengan komposisi

Inheritance mengikat subclass ke induknya. Perubahan perilaku di `Aset` otomatis memengaruhi semua
subclass, dan Java hanya mengizinkan satu kelas induk, sehingga subclass tidak bisa mewarisi
perilaku lain yang juga dibutuhkannya. Sebagai contoh, bila aturan denda dibuat dengan inheritance
(misalnya `LaptopDendaProgresif extends Laptop`), setiap kombinasi jenis aset dan aturan denda
memerlukan kelas baru. Di sistem ini komposisi lebih tepat: objek aset menyimpan/memakai sebuah
`AturanDenda` (seperti `DendaFlat` atau `DendaProgresif`), sehingga aturan denda bisa diganti tanpa
mengubah hierarki aset dan tanpa membuat kelas untuk setiap kombinasi.

## Kendala

Tes memakai nilai batas yang mudah terlewat, yaitu kapasitas `0` dan `-5`, string kosong, string
berisi spasi saja, serta tingkat risiko `null`. Saya menggunakan `isBlank()` dan memeriksa `null`
lebih dulu supaya semua kasus itu ditolak. Kendala lain, `minggu-aktif.txt` masih berisi minggu 1,
jadi saya menjalankan `.\scripts\uji.ps1 -Minggu 3` agar tes minggu 2 dan 3 ikut dijalankan. Saya
juga sempat menjalankan `mvn clean test` untuk memastikan kelas benar-benar dikompilasi ulang, bukan
memakai hasil kompilasi lama.

## Penggunaan AI

Pada pengerjaan minggu ini, saya membuat hierarki class dengan menerapkan konsep inheritance (pewarisan) pada class `Ruang`, `Laptop`, dan `AlatLab` yang merupakan turunan dari class `Aset`.

Saya menambahkan field khusus pada setiap subclass, membuat konstruktor, getter, validasi data, serta melakukan override method `getJenis()`. Konstruktor pada subclass menggunakan `super(...)` untuk memanfaatkan atribut dan validasi umum yang sudah tersedia pada class induk `Aset`.

Field khusus pada setiap subclass dibuat menggunakan `final` tanpa setter agar nilainya tidak berubah setelah objek dibuat. Saya juga menggunakan `isBlank()` untuk memastikan input berupa teks kosong atau hanya berisi spasi tidak diterima.

**Hal yang saya pelajari:**

Saya memahami bahwa inheritance memungkinkan subclass menggunakan kembali atribut, konstruktor, dan perilaku dari class induk. Subclass hanya perlu menambahkan data dan perilaku yang bersifat khusus. Saya juga memahami bahwa inheritance lebih tepat digunakan untuk hubungan "adalah sebuah" (*is-a*), sedangkan composition lebih fleksibel untuk menggabungkan beberapa perilaku yang berbeda.