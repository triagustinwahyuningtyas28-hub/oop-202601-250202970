package id.ac.upb.asetpinjam.denda;

import id.ac.upb.asetpinjam.kontrak.AturanDenda;
import id.ac.upb.asetpinjam.model.Aset;

/**
 * Minggu 5: denda yang makin berat bila makin lama terlambat.
 *
 * <p>Rumus (lihat docs/asumsi.md A-01): denda flat ditambah 5% dari denda flat
 * untuk setiap hari terlambat, yaitu {@code flat + (flat x hari) / 20}.
 * Syarat minimal yang diuji: 0 hari = 0; tidak pernah lebih murah dari DendaFlat;
 * tidak pernah turun saat hari bertambah; hari ke-30 lebih mahal dari DendaFlat.
 */
public class DendaProgresif implements AturanDenda {

    @Override
    public long hitung(Aset aset, int hariTerlambat) {
        long dendaFlat = aset.hitungDenda(hariTerlambat);
        long tambahan = (dendaFlat * hariTerlambat) / 20;
        return dendaFlat + tambahan;
    }
}
