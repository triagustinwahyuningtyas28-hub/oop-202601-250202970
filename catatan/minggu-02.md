# Catatan Minggu 02: Class, Object, dan Encapsulation

## Yang saya kerjakan

Saya melengkapi class `Aset` dengan empat field private: `kode` (final), `nama`, `status`, dan
`kondisi`. Konstruktor memvalidasi `kode` dan `nama` agar tidak null atau kosong, lalu memberi nilai
awal `status = TERSEDIA` dan `kondisi = BAIK`. Saya menambahkan getter untuk semua field dan setter
hanya untuk `nama`, `status`, serta `kondisi`; `kode` sengaja tidak memiliki setter. Setelah itu saya
menjalankan tes minggu 2 dan keenam tes lulus.

## Keputusan desain

- **Kode tidak dinormalisasi.** Nilai `kode` disimpan apa adanya setelah divalidasi, tanpa `trim`
  atau ubah ke huruf besar. Alasannya, kode inventaris bisa saja mengandung huruf kecil atau spasi
  internal yang bermakna, sehingga mengubahnya diam-diam berpotensi membuat kode tidak cocok dengan
  data lain. Yang penting, `null`, string kosong, dan string berisi spasi saja tetap ditolak.
- **`kode` dibuat `final`.** Kode adalah identitas aset, jadi tidak boleh berubah setelah objek
  dibuat. Ini juga sebabnya tidak ada `setKode`.
- **Validasi dilakukan di konstruktor dan setter.** Dengan begitu objek tidak pernah berada dalam
  keadaan tidak valid, termasuk saat `setNama`, `setStatus`, atau `setKondisi` dipanggil.
- **`status` dan `kondisi` memakai enum.** Tipe enum membatasi nilai yang sah, sedangkan setter
  tetap memeriksa `null` agar nilai tidak pernah kosong.
- **Getter mengembalikan nilai apa adanya.** `String` dan enum bersifat immutable, jadi tidak perlu
  salinan tambahan.

## Kendala

Kendala utama adalah membedakan "kosong" dengan "hanya berisi spasi". Saya memakai `isBlank()` agar
nilai seperti `"   "` ikut ditolak, bukan hanya `""`. Saya juga memastikan lewat tes refleksi bahwa
semua field benar-benar private dan tidak ada metode `setKode`. Kendala kecil lainnya adalah
`minggu-aktif.txt` masih berisi minggu 1, sehingga saya menjalankan tes dengan
`.\scripts\uji.ps1 -Minggu 2` agar tes minggu 2 ikut dijalankan.

## Penggunaan AI

- **Alat yang dipakai:** OpenCode dengan model Big Pickle.
- **Bagian yang dibantu AI:** implementasi field, konstruktor, getter, setter, dan validasi pada
  `Aset.java`, serta penyusunan draf catatan ini.
- **Prompt utama:**
  1. "kerjakan tanpa commit @core/src/main/java/id/ac/upb/asetpinjam/model/Aset.java field,
     konstruktor, getter, setter dengan validasi."
  2. "oke bagaimana saya run nya"
- **Yang saya ubah atau tolak dari saran AI, dan alasannya:** saya mempertimbangkan normalisasi kode
  dengan `trim` dan huruf besar, tetapi memilih tidak melakukannya agar kode aset tidak berubah tanpa
  sepengetahuan pemanggil. Saya juga memastikan metode minggu 3–5 (`getJenis`, `hitungDenda`,
  `bisaDipinjam`) tetap dibiarkan sebagai TODO karena belum menjadi lingkup minggu 2.
- **Satu hal yang saya pahami lebih baik sekarang:** encapsulation bukan sekadar membuat field
  private, tetapi juga menjaga agar objek tidak bisa masuk ke keadaan tidak valid. Validasi harus ada
  di semua jalur masuk data, yaitu konstruktor dan setter, bukan hanya di salah satunya.
