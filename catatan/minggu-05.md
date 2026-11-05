# Catatan Minggu 05: Abstraction

## Yang saya kerjakan

Saya mengisi `bisaDipinjam()` pada `Aset` agar hanya bernilai true ketika status aset `TERSEDIA` dan
kondisinya bukan `RUSAK_BERAT`. Setelah itu `Aset` saya ubah menjadi `abstract` dan `hitungDenda(int)`
dijadikan metode abstrak, sehingga setiap subclass wajib menyediakan perhitungan dendanya sendiri.
Saya juga mengimplementasikan `DendaFlat` dan `DendaProgresif` sebagai kelas yang memenuhi kontrak
`AturanDenda`. Sebagai bukti rumus progresif yang saya pilih, saya menambahkan tes sendiri di folder
`mahasiswa`, lalu menjalankan tes minggu 1 sampai 5 dan seluruh 37 tes lulus.

## Keputusan desain

- **`Aset` memakai abstract class, sedangkan `DapatDipinjam` tetap interface.** `Aset` menyimpan
  state (`kode`, `nama`, `status`, `kondisi`) dan punya konstruktor yang menjalankan validasi, jadi
  tidak bisa diganti dengan interface. Sebaliknya `DapatDipinjam` hanya berisi satu perilaku
  (`bisaDipinjam()`) yang bisa dimiliki kelas mana pun, sehingga cocok menjadi interface.
- **`bisaDipinjam()` diletakkan di `Aset`.** Aturannya berlaku sama untuk semua jenis aset dan hanya
  membutuhkan field private yang sudah ada di kelas itu, sehingga subclass tidak perlu menuliskannya
  lagi.
- **`hitungDenda(int)` dibuat abstrak.** Kelas induk tidak mengetahui multiplier jenis aset, jadi
  lebih tepat memaksa setiap subclass mengimplementasikannya. Metode bantu
  `hitungDendaDenganPengali` tetap konkret karena validasi hari negatif dan pembacaan tarif varian
  sama untuk semua subclass.
- **`DendaFlat` hanya meneruskan ke `aset.hitungDenda(hari)`.** Dengan cara ini aturan denda tetap
  mengikuti multiplier masing-masing jenis aset dan tidak ada rumus yang diduplikasi.
- **Rumus `DendaProgresif` adalah `flat + (flat x hari) / 20`.** Artinya denda flat ditambah 5% dari
  denda flat untuk tiap hari keterlambatan (lihat `docs/asumsi.md` A-01). Rumus ini dipilih karena
  sederhana, tidak butuh parameter baru, tidak pernah lebih murah dari denda flat, tidak pernah turun
  saat hari bertambah, dan pada hari ke-30 menjadi 2,5 kali denda flat.
- **Pembagian memakai bilangan bulat.** Hasil pembagian dibulatkan ke bawah, tetapi karena tarif
  harian selalu ribuan rupiah, tambahan 5% tetap lebih dari nol dan urutan denda tetap naik.

## Kendala

Kendala pertama adalah membedakan kapan memakai abstract class dan kapan memakai interface. Saya
memilih abstract class untuk `Aset` karena ada state dan konstruktor yang harus diwarisi, sedangkan
interface untuk `DapatDipinjam` karena hanya mendefinisikan kemampuan. Saya juga memastikan semua
subclass tetap mengompilasi setelah `hitungDenda` menjadi abstrak; hal ini terbantu oleh anotasi
`@Override` yang sudah ada.

Kendala kedua adalah merancang rumus progresif yang memenuhi empat syarat sekaligus: nol hari sama
dengan nol, tidak lebih murah dari flat, tidak pernah turun, dan hari ke-30 lebih mahal dari flat.
Saya menguji rumus dengan perulangan hari 0 sampai 30 pada `Ruang` dan `Laptop` agar kesalahan
pembulatan atau urutan nilai langsung terlihat. Karena `minggu-aktif.txt` masih berisi minggu 1, saya
menjalankan `.\scripts\uji.ps1 -Minggu 5` supaya tes minggu 2 sampai 5 ikut dijalankan.

## Penggunaan AI

- Alat yang dipakai: Java, Maven, chatgpt, dan Visual Studio Code.
- Bagian yang dikerjakan:
  1. Mengisi `bisaDipinjam()` pada `Aset`.
  2. Mengubah `Aset` menjadi `abstract` dan membuat `hitungDenda(int)` abstrak.
  3. Mengimplementasikan `DendaFlat.hitung(...)`.
  4. Mengimplementasikan `DendaProgresif.hitung(...)` beserta rumusnya.
  5. Menambahkan `DendaProgresifTest` dan menjalankan tes minggu 1 sampai 5.
- Satu hal yang saya pahami lebih baik sekarang: abstract class cocok dipakai ketika kelas turunan
  perlu mewarisi state dan perilaku yang sudah jadi, sementara interface cocok untuk kontrak perilaku
  yang bisa dipakai lintas kelas. Metode abstrak memaksa subclass menyediakan implementasi, tetapi
  metode konkret di kelas abstrak tetap bisa dipakai bersama.
