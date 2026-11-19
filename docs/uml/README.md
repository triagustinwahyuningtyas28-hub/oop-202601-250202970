# Diagram UML (minggu 6, diperbarui minggu 14)

## Isi folder ini (hasil minggu 6)

- [`class.puml`](class.puml) — Class Diagram modul `core`, lengkap dengan paket, atribut/method, tipe, visibility, dan multiplicity.
- [`sequence.puml`](sequence.puml) — Sequence Diagram pengajuan peminjaman dengan fragmen `alt` sukses/bentrok.
- [`solid.md`](solid.md) — OCP dan DIP pada kelas nyata, tambahan SRP, dan catatan istilah untuk kuis.

Keduanya ditulis dalam PlantUML. Method yang pada minggu 6 masih bertanda TODO minggu 7/9/10/11
dicantumkan apa adanya bersama catatan status, supaya diagram tetap sesuai dengan kode.

Letakkan berkas Anda di folder ini. Alat bebas (draw.io, PlantUML, Mermaid, StarUML). Sertakan **sumber** (`.drawio`, `.puml`, `.mmd`)
dan gambar (`.png` atau `.svg`) bila alat Anda menghasilkannya.

Nama berkas wajib diawali:

| Awalan nama | Isi |
|---|---|
| `class` | Class Diagram: atribut dan method dengan tipe dan visibility, relasi dengan multiplicity, nama paket |
| `sequence` | Sequence Diagram pengajuan peminjaman: skenario sukses dan skenario bentrok, memakai fragmen `alt` |
| `solid.md` | Penjelasan minimal dua prinsip SOLID yang terlihat di kode Anda (nama kelas dan alasan), minimal 300 karakter |

Tes minggu 6 hanya memeriksa keberadaan berkas. Kualitas dinilai dengan rubrik di `docs/minggu-06.md`.
Hapus berkas `README.md` ini bila mau; tes tidak membutuhkannya.
