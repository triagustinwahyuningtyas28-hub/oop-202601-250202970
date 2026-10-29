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

- Alat yang dipakai: OpenCode (model Big Pickle).
- Bagian yang dibantu AI: merapikan format dan Javadoc `HelloProsedural`, `HelloOOP`,
  `HelloFungsional`, menyusun berkas catatan ini, dan menjalankan `scripts/uji.ps1`.
- Prompt utama:
  1. "kerjakan catatan @docs/minggu-01.md"
  2. "Jalankan tes minggu 1 dan pastikan kelima tes lulus."
- Yang saya ubah atau tolak dari saran AI, dan alasannya: saya mempertahankan pilihan kata sapaan
  versi saya sendiri dan hanya meminta formatnya dirapikan, karena bentuk kalimat adalah keputusan
  yang sengaja diserahkan kepada mahasiswa. Saya juga menolak menambah getter pada `HelloOOP`
  karena belum dibutuhkan oleh tes maupun oleh pemanggil lain.
- Satu hal yang saya pahami lebih baik sekarang: perbedaan paradigma bukan soal sintaks, melainkan
  soal ke mana data dan perilaku ditempatkan. Ketiga versi menghasilkan teks yang sama, tetapi
  hanya versi OOP yang menjaga `nama` dan `nim` sebagai state milik objek, dan hanya versi
  fungsional yang menyimpan NIM di dalam closure.
