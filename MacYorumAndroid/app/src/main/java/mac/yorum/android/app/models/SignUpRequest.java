package mac.yorum.android.app.models;

public class SignUpRequest {

    String AdSoyad;
    String Eposta;
    String Parola;
    String ReferansKodu;
    String KullaniciAdi;
    String Telefon;
    String PlatformToken;
    String DeviceId;

    public String getDeviceId() {
        return DeviceId;
    }

    public void setDeviceId(String deviceId) {
        DeviceId = deviceId;
    }


    public String getPlatformToken() {
        return PlatformToken;
    }

    public void setPlatformToken(String platformToken) {
        PlatformToken = platformToken;
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

    public String getReferansKodu() {
        return ReferansKodu;
    }

    public void setReferansKodu(String referansKodu) {
        ReferansKodu = referansKodu;
    }

    public String getKullaniciAdi() {
        return KullaniciAdi;
    }

    public void setKullaniciAdi(String kullaniciAdi) {
        KullaniciAdi = kullaniciAdi;
    }

    public String getTelefon() {
        return Telefon;
    }

    public void setTelefon(String telefon) {
        Telefon = telefon;
    }
}
