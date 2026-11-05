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


## Penggunaan AI

- **Alat yang dipakai:** ChatGPT untuk membantu memahami konsep class, object, dan encapsulation serta menyusun catatan pengerjaan.
- **Bagian yang dibantu AI:** Pembuatan dan pemeriksaan class `Aset`, penggunaan field `private` dan `final`, konstruktor, getter, setter, serta validasi nilai pada setiap field.
- **Prompt utama:**
  1. "Bantu saya memahami penerapan encapsulation pada class Aset menggunakan private, getter, dan setter."
  2. "Bantu periksa validasi konstruktor dan setter agar kode dan nama tidak boleh null atau kosong."
  3. "Bantu susun catatan pengerjaan Minggu 02 berdasarkan implementasi class Aset."
- **Yang saya ubah atau tolak dari saran AI, dan alasannya:** Saya mempertahankan nilai `kode` tanpa normalisasi menggunakan `trim()` atau mengubah huruf menjadi kapital karena kode inventaris harus disimpan sesuai nilai aslinya. Saya juga tidak menambahkan setter untuk `kode` karena field tersebut bersifat `final`.
- **Satu hal yang saya pahami lebih baik sekarang:** Saya lebih memahami konsep encapsulation, yaitu melindungi data dalam class menggunakan `private` dan mengatur akses maupun perubahan data melalui getter dan setter agar objek tetap dalam keadaan valid.