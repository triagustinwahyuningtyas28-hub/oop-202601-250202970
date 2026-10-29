# Catatan Minggu 01: Paradigma Pemrograman dan Setup

## Yang saya kerjakan

Saya mengisi tiga kelas sapaan dengan tiga paradigma berbeda untuk program yang sama:
`HelloProsedural` memakai fungsi statis, `HelloOOP` membungkus data `nama` dan `nim` di field
private, dan `HelloFungsional` mengembalikan lambda `Function<String, String>`. Saya juga mengisi
`mahasiswa.properties` dengan NIM 250202970 dan nama saya. Ketiga gaya saya buat menghasilkan teks
yang identik agar mudah dibandingkan. Setelah itu saya menjalankan `.\scripts\uji.ps1` dan kelima
tes `Minggu01Test` lulus.

## Keputusan desain

- **Bentuk sapaan:** `Halo Aset-Pinjam, saya <nama> (NIM: <nim>)`. Saya memilih bentuk ini karena
  memuat ketiga unsur yang diwajibkan tes ("Aset-Pinjam", nama, NIM) sekaligus tetap terbaca
  sebagai kalimat sapaan, bukan sekadar gabungan kata.
- **Satu sumber teks yang sama di tiga kelas:** isi sapaan sengaja ditulis dengan pola identik
  supaya tes `ketigaGayaMenghasilkanIsiSama` membuktikan bahwa perbedaan ketiga kelas murni ada di
  cara menyusun kode, bukan di hasil.
- **OOP:** `nama` dan `nim` dibuat `private final` dan hanya diisi lewat konstruktor. Ini membuat
  objek tidak bisa berubah setelah dibuat, sesuai semangat data yang dijaga oleh class.
- **Fungsional:** `pembuatSapaan(nim)` menerima NIM lebih dulu dan mengembalikan fungsi yang
  menunggu nama. Dengan begitu NIM "terkunci" di dalam closure dan tidak perlu dioper ulang.

Kelebihan dan keterbatasan tiap gaya untuk sistem peminjaman aset:

| Gaya | Kelebihan | Keterbatasan |
| --- | --- | --- |
| Prosedural | Paling sederhana dan cepat untuk aturan tunggal seperti menghitung denda dari jumlah hari; mudah dilacak dari atas ke bawah. | Data peminjaman dan fungsi perhitungan terpisah, sehingga aturan tersebar dan mudah tidak konsisten ketika fitur bertambah. |
| OOP | Data peminjaman (aset, peminjam, tanggal) dan perilakunya menyatu dalam satu objek, sehingga perubahan aturan cukup diubah di kelasnya dan mudah diuji. | Butuh lebih banyak kelas dan boilerplate; untuk perhitungan kecil sekali pakai, strukturnya terasa berlebihan. |
| Fungsional | Cocok untuk mengolah daftar peminjaman (filter yang terlambat, map ke denda) tanpa mengubah data asli, sehingga mudah dites dan diparalelkan. | Alur eksekusi lebih sulit dibaca pemula, dan state yang memang harus berubah (misalnya status "dipinjam" di database) tetap butuh lapisan lain. |

## Kendala

Kendala utamanya adalah memastikan ketiga kelas benar-benar menghasilkan string yang sama persis,
termasuk tanda baca dan spasi, karena tes membandingkan hasil dengan `isEqualTo`. Saya atasi dengan
menetapkan satu bentuk kalimat lebih dulu, lalu memakai pola penulisan yang sama di ketiga kelas.
Kendala kecil lainnya adalah kerapian format: indentasi `HelloProsedural` sempat tidak konsisten,
jadi saya rapikan dan ganti komentar `TODO` menjadi Javadoc yang menjelaskan hasil metode.

## Penggunaan AI

- **Alat yang digunakan:** Tidak menggunakan AI.
- **Bagian yang dikerjakan:** Merapikan format dan Javadoc pada HelloProsedural, HelloOOP, dan HelloFungsional, menyusun berkas catatan, serta menjalankan scripts/uji.ps1 untuk menguji program.
- **Prompt utama:** Tidak ada, karena seluruh proses dikerjakan secara mandiri.
- **Perubahan atau keputusan yang dilakukan:** Saya menggunakan pilihan kata sapaan sesuai keinginan saya sendiri. Saya juga tidak menambahkan getter pada HelloOOP karena belum diperlukan oleh pengujian maupun bagian program lainnya.
- **Hal yang saya pahami:** Saya memahami bahwa perbedaan paradigma pemrograman tidak hanya terletak pada sintaks, tetapi juga pada cara data dan perilaku diatur. Ketiga program menghasilkan teks yang sama, tetapi memiliki cara pengelolaan data yang berbeda. Pada OOP, data nama dan nim menjadi bagian dari state objek, sedangkan pada pemrograman fungsional, NIM disimpan di dalam closure.