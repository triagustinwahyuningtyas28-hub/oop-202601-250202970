# Catatan Minggu 06: UML dan SOLID

## Yang saya kerjakan

Saya membaca kode modul `core` lalu membuat dua sumber diagram PlantUML:
`docs/uml/class.puml` dan `docs/uml/sequence.puml`. Class diagram memuat paket `model`, `kontrak`,
`jadwal`, `katalog`, `denda`, `factory`, `config`, `exception`, dan `dao`, lengkap dengan tipe,
visibility, serta multiplicity. Sequence diagram menggambarkan pengajuan peminjaman dengan
fragmen `alt` untuk skenario sukses dan skenario bentrok. Saya juga menulis `docs/uml/solid.md`
dan melakukan refactor kecil pada `LayananDenda`: aturan denda kini benar-benar disimpan sebagai
field `final` dan disuntikkan lewat konstruktor, sehingga DIP terlihat di kode, bukan hanya di
komentar. Terakhir saya menjalankan `.\scripts\uji.ps1` untuk minggu 1 sampai 6.

## Keputusan desain

- **Prinsip yang ditunjukkan: OCP dan DIP.** OCP terlihat pada `AturanDenda` dengan `DendaFlat`
  dan `DendaProgresif`, serta pada `Aset` dengan `hitungDenda(int)` yang dioverride tiap subclass.
  DIP terlihat pada `LayananDenda` yang bergantung pada antarmuka `AturanDenda`, dan pada
  `JdbcPeminjamanDAO` yang bergantung pada antarmuka `AsetDAO`. SRP saya sertakan sebagai tambahan.
- **Refactor dibatasi pada `LayananDenda`.** Konstruktor dan field-nya masih TODO minggu 10, tetapi
  untuk minggu 6 saya mengisinya supaya contoh DIP benar-benar bisa dijalankan. Saya tidak
  menyentuh `AsetFactory`, DAO, `bentrok()`, atau `ajukan()` karena itu pekerjaan minggu berikutnya.
- **Diagram mengikuti kode apa adanya.** `bentrok()` dan `ajukan()` memang sudah dideklarasikan di
  `JadwalPeminjaman`, tetapi badannya masih TODO minggu 7/9. Saya memakai signature yang ada dan
  menuliskan catatan TODO di dalam diagram, daripada mengarang method baru atau berpura-pura
  bahwa exception sudah dilempar. `PeminjamanBentrokException` juga sudah ada sebagai kelas;
  pesan jelasnya yang masih TODO minggu 9.
- **Cakupan class diagram adalah modul `core`.** Paket `api/` (minggu 12 ke atas) sengaja tidak
  dimasukkan agar diagram minggu 6 tetap sesuai dengan kode yang relevan saat ini. Saya memilih
  PlantUML karena sumber teksnya mudah dicek terhadap kode; tidak ada berkas `.png`/`.svg` yang
  dihasilkan karena alat render tidak dipasang di mesin ini.
- **Agregasi, bukan komposisi.** `JadwalPeminjaman` dan `KatalogAset` memakai agregasi (`o--`)
  terhadap objek yang mereka simpan, karena `hapus()` melepaskan objek tanpa menghancurkannya.

## Kendala

Kendala utama adalah memisahkan *kontrak* dari *implementasi*. Sequence diagram diminta menampilkan
skenario bentrok, sementara logika bentrok baru akan dikerjakan minggu 7 dan `ajukan()` minggu 9.
Saya mengatasinya dengan memakai nama method dan kelas yang sudah ada serta menandai status TODO
langsung pada diagram; dengan demikian gambaran tetap konsisten dengan kode. Kendala kedua adalah
menentukan batas refactor: saya hanya mengubah `LayananDenda` karena perubahannya kecil dan
langsung dibutuhkan untuk menunjukkan DIP. Sesuai permintaan, semua perubahan saya biarkan belum
di-commit.

## Penggunaan AI

- Alat yang dipakai: chat gpt, Visual Studio Code, Java 21,
  Maven, dan PlantUML.
- Bagian yang dibantu AI: bagian  `class.puml` dan `sequence.puml`, merangkai daftar
  atribut/method dari file Java, serta menulis draf `docs/uml/solid.md` dan catatan ini. AI juga
  menunjukkan bahwa `LayananDenda` masih memiliki TODO yang cocok dijadikan contoh DIP.
- Prompt utama:
  1. analisa UML dan SOLID. Buat class diagram, sequence diagram dengan
     alt sukses/bentrok, dan solid.md yang konsisten dengan kode."
  2. "Periksa signature di core/src/main/java lalu buat diagram PlantUML; tandai method yang masih
     TODO minggu 7/9 supaya tidak dianggap sudah terimplementasi."
  3. "Tunjukkan minimal dua prinsip SOLID pada kelas nyata dan jelaskan alasannya."
- Yang saya ubah atau tolak dari saran AI, dan alasannya: saya menolak menyajikan `ajukan()` sebagai
  method yang sudah berjalan dan menolak menambah method fiktif seperti `validasiBentrok()` pada
  draf awal. Saya juga membatalkan klaim bahwa `Peminjaman --> Peminjam` adalah komposisi, karena
  `Peminjam` dibuat di luar `Peminjaman` dan bisa tetap dipakai; di diagram akhir saya pakai
  asosiasi biasa. Cakupan diagram saya batasi pada modul `core`.
- Satu hal yang saya pahami lebih baik sekarang: OCP dan DIP saling melengkapi. OCP memberi tempat
  perluasan berupa antarmuka, sedangkan DIP memastikan kelas lain bergantung pada antarmuka itu
  sehingga implementasinya bisa diganti, termasuk dengan objek palsu di tes.
