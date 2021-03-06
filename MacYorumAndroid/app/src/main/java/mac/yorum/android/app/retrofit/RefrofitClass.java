package mac.yorum.android.app.retrofit;

import mac.yorum.android.app.models.LoginRequest;
import mac.yorum.android.app.models.LoginResponse;
import mac.yorum.android.app.models.SignUpRequest;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by gkhngngr on 23.1.2017.
 */

public interface RefrofitClass {

    @POST("Mobil/Giris")
    Call<LoginResponse>Login(@Header("TokenLar") String token,
                             @Header("KullaniciToken") String kullaniciToken,
                             @Header("Content-Type") String contenttype,
                             @Body LoginRequest loginRequest);


    @POST("Mobil/Kayit")
    Call<LoginResponse>SignUp(@Header("TokenLar") String token,
                             @Header("KullaniciToken") String kullaniciToken,
                             @Header("Content-Type") String contenttype,
                             @Body SignUpRequest loginRequest);

    @GET("/Mobil/KazananKuponListe")
    Call<LoginResponse> KazananKuponListe(
            @Header("TokenLar") String token,
            @Header("KullaniciToken") String kullaniciToken,
            @Header("Content-Type") String contenttype,
            @Query("KuponTipi") String kuponTipi,
            @Query("Tarih") String tarih);

    @GET("/Mobil/KuponListe")
    Call<LoginResponse> KuponListe(
            @Header("TokenLar") String token,
            @Header("KullaniciToken") String kullaniciToken,
            @Header("Content-Type") String contenttype,
            @Query("KuponTipi") String kuponTipi,
            @Query("Tarih") String tarih);

    @GET("/Mobil/KuponListeDetay")
    Call<LoginResponse> KuponListeListeDetay(
            @Header("TokenLar") String token,
            @Header("KullaniciToken") String kullaniciToken,
            @Header("Content-Type") String contenttype,
            @Query("id") String kuponTipi);


    @GET("/Mobil/MacYorumListe")
    Call<LoginResponse> MacYorumListe(
            @Header("TokenLar") String token,
            @Header("KullaniciToken") String kullaniciToken,
            @Header("Content-Type") String contenttype,
            @Query("Tarih") String tarih,
            @Query("AranacakKelime") String search);

    @GET("/Mobil/MacYorumOkuyan")
    Call<LoginResponse> MacYorumOkuyan(
            @Header("TokenLar") String token,
            @Header("KullaniciToken") String kullaniciToken,
            @Header("Content-Type") String contenttype,
            @Query("id") String id);

    @GET("/Mobil/HesapSilme")
    Call<LoginResponse> HesapSilme(
            @Header("TokenLar") String token,
            @Header("KullaniciToken") String kullaniciToken,
            @Header("Content-Type") String contenttype);



    @GET("/Mobil/ParolaGuncelle")
    Call<LoginResponse> ParolaGuncelle(
            @Header("TokenLar") String token,
            @Header("KullaniciToken") String kullaniciToken,
            @Header("Content-Type") String contenttype,
            @Query("Parola") String parola);

    @GET("/Mobil/ParolaSifirla")
    Call<LoginResponse> ParolaSifirla(
            @Header("TokenLar") String token,
            @Header("Content-Type") String contenttype,
            @Query("Eposta") String eposta);


}
