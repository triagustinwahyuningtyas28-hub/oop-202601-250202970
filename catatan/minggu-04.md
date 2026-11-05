# Catatan Minggu 04: Polymorphism

## Yang saya kerjakan

Saya mengimplementasikan `hitungDenda(int)` pada `Aset` dan meng-override-nya di `Ruang`, `Laptop`,
serta `AlatLab` dengan multiplier 1x, 2x, dan 4x. Perhitungan hari, multiplier, dan tarif dasar
`Varian.aktif().dendaPerHari` saya satukan dalam metode bantu `hitungDendaDenganPengali` agar aturan
validasi tidak ditulis berulang di setiap subclass. Saya juga melengkapi dua overload `cari(...)` di
`KatalogAset`: pencarian berdasarkan nama saja dan pencarian berdasarkan nama sekaligus jenis aset.
Setelah itu saya menjalankan tes minggu 1 sampai 4 dan seluruh 27 tes lulus.

## Keputusan desain

- **Perhitungan dasar ditaruh di induk.** `Aset.hitungDenda` memakai multiplier 1x, sedangkan tiap
  subclass hanya memanggil `hitungDendaDenganPengali` dengan multiplier jenisnya sendiri. Dengan cara
  ini pengecekan hari negatif dan pembacaan tarif varian hanya ada di satu tempat.
- **Multiplier ditentukan oleh subclass, bukan oleh `if`/`switch` di pemanggil.** Setiap subclass
  meng-override `hitungDenda`, sehingga pemanggil cukup memakai referensi `Aset` dan Java memilih
  implementasi yang tepat saat runtime (*dynamic binding*). Ini juga memperlihatkan perbedaan
  overriding (nama dan parameter sama, isi berbeda) dengan overloading (nama sama, parameter beda).
- **Tarif dibaca dari varian, bukan ditulis sebagai angka tetap.** NIM saya berakhiran `0`, jadi
  tarifnya Rp5.000 per hari. Dengan membaca `Varian.aktif().dendaPerHari`, kode tidak perlu diubah
  bila tarif varian berbeda.
- **Hasil perkalian dijadikan `long` sebelum dikalikan.** `hariTerlambat * pengali` bisa melewati
  batas `int` untuk nilai hari yang besar, sehingga salah satu operand saya ubah ke `long` lebih dulu.
- **Pencarian tidak membedakan huruf besar/kecil dengan `Locale.ROOT`.** Saya tidak memakai
  `toLowerCase()` tanpa locale karena pada locale tertentu (misalnya Turki) huruf `I` dan `i` dapat
  berubah berbeda. `null` sebagai kata kunci atau jenis saya tolak dengan `IllegalArgumentException`
  agar kesalahan pemanggilan tidak diam-diam menghasilkan daftar kosong.
- **Overload kedua memakai ulang `cari(kata)`.** Penyaringan jenis cukup dilakukan setelah hasil
  pencarian nama didapat, sehingga aturan pencocokan nama hanya ditulis sekali.
- **Hasil pencarian berupa list baru.** Daftar hasil tidak menunjuk langsung ke field `daftar`, jadi
  pemanggil tidak bisa mengubah isi katalog melalui list yang dikembalikan.

## Kendala

Kendala utama adalah membedakan overloading dan overriding. Keduanya sama-sama memakai nama metode
yang sama, tetapi overloading dipilih saat kompilasi berdasarkan tipe parameter, sedangkan overriding
dipilih saat runtime berdasarkan objek sebenarnya. Saya memastikan `hitungDenda(int)` di subclass
memakai anotasi `@Override` agar kesalahan tanda tangan langsung terdeteksi oleh compiler, sedangkan
dua metode `cari` sengaja memiliki parameter berbeda.

Kendala lain adalah pencocokan nama. Awalnya saya hanya memakai `toLowerCase()`, lalu menggantinya
dengan `toLowerCase(Locale.ROOT)` supaya hasilnya konsisten. Untuk kasus batas, saya memastikan hari
`0` menghasilkan denda `0` dan hari negatif melempar `IllegalArgumentException`. Saya juga
menjalankan `.\scripts\uji.ps1 -Minggu 4` karena `minggu-aktif.txt` masih berisi minggu 1, sehingga
tes minggu 2 dan 3 ikut dijalankan dan memastikan perubahan minggu ini tidak merusak fitur sebelumnya.

## Penggunaan dan Pemahaman Materi

- Alat yang dipakai: Java, Maven, dan Visual Studio Code.
- Bagian yang dikerjakan:
  1. Meng-override hitungDenda(int) pada Ruang, Laptop, dan AlatLab.
  2. Menambahkan metode bantu hitungDendaDenganPengali di Aset untuk validasi dan perhitungan.
  3. Melengkapi cari(String) dan cari(String, JenisAset) di KatalogAset.
  4. Menjalankan tes minggu 1 sampai 4 dan memastikan 27 tes berhasil.
- Satu hal yang saya pahami lebih baik sekarang: overriding membuat objek dari subclass yang berbeda
  dapat dipanggil melalui tipe induk yang sama, tetapi perilakunya tetap sesuai kelas masing-masing.
  Overloading berbeda karena hanya mengganti daftar parameter, bukan isi perilaku turunan.
