package mac.yorum.android.app.models;

import java.util.ArrayList;

public class Result {

    public String Id;
    public String refId;
    public String refKullaniciGrup;
    public String ReferansKodu ;
    public String KullaniciGrupAd ;
    public String Token ;
    public String AdSoyad;
    public String Eposta ;
    public String Parola ;
    public String Aktif ;
    public String RecordTime;
    public String KullaniciAdi;
    public ArrayList<Yetki> Yetkiler;




    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getRefId() {
        return refId;
    }

    public void setRefId(String refId) {
        this.refId = refId;
    }

    public String getRefKullaniciGrup() {
        return refKullaniciGrup;
    }

    public void setRefKullaniciGrup(String refKullaniciGrup) {
        this.refKullaniciGrup = refKullaniciGrup;
    }

    public String getReferansKodu() {
        return ReferansKodu;
    }

    public void setReferansKodu(String referansKodu) {
        ReferansKodu = referansKodu;
    }

    public String getKullaniciGrupAd() {
        return KullaniciGrupAd;
    }

    public void setKullaniciGrupAd(String kullaniciGrupAd) {
        KullaniciGrupAd = kullaniciGrupAd;
    }

    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        Token = token;
    }

    public String getAdSoyad() {
        return AdSoyad;
    }

    public void setAdSoyad(String adSoyad) {
        AdSoyad = adSoyad;
    }

    public String getEposta() {
        return Eposta;
    }

    public void setEposta(String eposta) {
        Eposta = eposta;
    }

    public String getParola() {
        return Parola;
    }

    public void setParola(String parola) {
        Parola = parola;
    }

    public String getAktif() {
        return Aktif;
    }

    public void setAktif(String aktif) {
        Aktif = aktif;
    }

    public String getRecordTime() {
        return RecordTime;
    }

    public void setRecordTime(String recordTime) {
        RecordTime = recordTime;
    }

    public String getKullaniciAdi() {
        return KullaniciAdi;
    }

    public void setKullaniciAdi(String kullaniciAdi) {
        KullaniciAdi = kullaniciAdi;
    }

    public ArrayList<Yetki> getYetkiler() {
        return Yetkiler;
    }

    public void setYetkiler(ArrayList<Yetki> yetkiler) {
        Yetkiler = yetkiler;
    }
}
