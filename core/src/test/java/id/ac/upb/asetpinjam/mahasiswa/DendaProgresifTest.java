package id.ac.upb.asetpinjam.mahasiswa;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import id.ac.upb.asetpinjam.config.Varian;
import id.ac.upb.asetpinjam.denda.DendaFlat;
import id.ac.upb.asetpinjam.denda.DendaProgresif;
import id.ac.upb.asetpinjam.model.Aset;
import id.ac.upb.asetpinjam.model.Laptop;
import id.ac.upb.asetpinjam.model.Ruang;
import java.util.List;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/** Membuktikan rumus denda progresif pada docs/asumsi.md A-01. */
@Tag("week5")
class DendaProgresifTest {

    private final long tarif = Varian.aktif().dendaPerHari;
    private final DendaFlat flat = new DendaFlat();
    private final DendaProgresif progresif = new DendaProgresif();
    private final Aset ruang = new Ruang("R-01", "Ruang Rapat", 10);
    private final Aset laptop = new Laptop("L-01", "Laptop Dosen", "i5", "INV-1");

    @Test
    void nolHariBerartiNol() {
        assertThat(progresif.hitung(ruang, 0)).isZero();
    }

    @Test
    void hariPertamaMenambahLimaPersenDariDendaFlat() {
        long dendaFlat = tarif; // Ruang memakai multiplier 1x
        assertThat(progresif.hitung(ruang, 1)).isEqualTo(dendaFlat + dendaFlat / 20);
    }

    @Test
    void hariKeTigaPuluhMenjadiDuaSetengahKaliDendaFlat() {
        long dendaFlat = 30L * tarif;
        assertThat(progresif.hitung(ruang, 30)).isEqualTo(dendaFlat + (dendaFlat * 30) / 20);
        assertThat(progresif.hitung(ruang, 30)).isEqualTo(dendaFlat * 5 / 2);
    }

    @Test
    void tidakPernahLebihMurahDanTidakPernahTurun() {
        for (Aset aset : List.of(ruang, laptop)) {
            long sebelumnya = 0;
            for (int hari = 0; hari <= 30; hari++) {
                long nilai = progresif.hitung(aset, hari);
                assertThat(nilai).isGreaterThanOrEqualTo(flat.hitung(aset, hari));
                assertThat(nilai).isGreaterThanOrEqualTo(sebelumnya);
                sebelumnya = nilai;
            }
        }
    }

    @Test
    void hariNegatifDitolak() {
        assertThatThrownBy(() -> progresif.hitung(ruang, -1))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
