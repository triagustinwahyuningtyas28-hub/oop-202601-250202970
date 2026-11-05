# Asumsi dan Keputusan Desain

Isi bagian ini sendiri. Satu entri per keputusan. Dinilai dari kejelasan alasan dan konsistensi dengan kode dan tes Anda,
bukan dari jawaban "benar".

Format tiap entri:

```
## A-NN: judul singkat
- Pertanyaan: ...
- Keputusan: ...
- Alasan: ...
- Dampak ke kode: ... (kelas/metode)
- Tes yang membuktikan: ... (nama tes di folder mahasiswa/)
```

## Aturan ambigu dari docs/aturan-bisnis.md

Salin setiap pertanyaan ambigu (nomor 1 sampai 7) ke sini dan jawab. Contoh:

## A-01: batas waktu bentrok
- Pertanyaan: 09:00-11:00 dan 11:00-13:00 pada aset yang sama bentrok atau tidak?
- Keputusan: (isi)
- Alasan: (isi)
- Dampak ke kode: (isi)
- Tes yang membuktikan: (isi)

## Asumsi lain

Tambahkan di sini keputusan lain yang Anda ambil: rumus denda progresif, perubahan skema database, format error API, dan seterusnya.

## A-01: rumus denda progresif

- Pertanyaan: Bagaimana denda progresif dihitung sehingga selalu lebih berat daripada denda flat?
- Keputusan: `DendaProgresif` memakai rumus `flat + (flat x hariTerlambat) / 20`, yaitu denda flat ditambah 5% dari denda flat untuk setiap hari keterlambatan. Pembagian dilakukan dengan pembulatan ke bawah.
- Alasan: Rumus ini sederhana, tidak memerlukan parameter baru, dan pasti tidak lebih murah dari denda flat. Setiap hari tambahan tidak pernah menurunkan nilai denda, dan pada hari ke-30 totalnya menjadi 2,5 kali denda flat sehingga keterlambatan panjang benar-benar lebih berat.
- Dampak ke kode: `DendaProgresif.hitung(Aset, int)`. `DendaFlat` tetap sama dengan `aset.hitungDenda(hari)`.
- Tes yang membuktikan: `DendaProgresifTest` di `core/src/test/java/id/ac/upb/asetpinjam/mahasiswa/` dan `Minggu05AbstraksiTest.dendaProgresifMemenuhiSyaratMinimal`.
