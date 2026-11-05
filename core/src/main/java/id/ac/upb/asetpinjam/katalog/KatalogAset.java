package id.ac.upb.asetpinjam.katalog;

import id.ac.upb.asetpinjam.model.Aset;
import id.ac.upb.asetpinjam.model.JenisAset;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/** Katalog aset di memori. tambah() dan semua() DISEDIAKAN; Anda mengerjakan dua overload cari() (minggu 4). */
public class KatalogAset {

    private final List<Aset> daftar = new ArrayList<>();

    public void tambah(Aset aset) {
        daftar.add(aset);
    }

    public List<Aset> semua() {
        return List.copyOf(daftar);
    }

    /** Aset yang namanya memuat kata (tanpa membedakan huruf besar/kecil). */
    public List<Aset> cari(String kata) {
        if (kata == null) {
            throw new IllegalArgumentException("Kata pencarian tidak boleh null");
        }
        String kunci = kata.toLowerCase(Locale.ROOT);
        List<Aset> hasil = new ArrayList<>();
        for (Aset aset : daftar) {
            if (aset.getNama().toLowerCase(Locale.ROOT).contains(kunci)) {
                hasil.add(aset);
            }
        }
        return hasil;
    }

    /** Seperti cari(kata) tetapi hanya untuk jenis yang diminta (overloading). */
    public List<Aset> cari(String kata, JenisAset jenis) {
        if (jenis == null) {
            throw new IllegalArgumentException("Jenis aset tidak boleh null");
        }
        List<Aset> hasil = new ArrayList<>();
        for (Aset aset : cari(kata)) {
            if (aset.getJenis() == jenis) {
                hasil.add(aset);
            }
        }
        return hasil;
    }
}
