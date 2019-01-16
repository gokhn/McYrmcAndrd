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


    //http://185.122.201.49:8080/Mobil/MacYorumListe?Tarih=2019-01-24
    @GET("/Mobil/MacYorumListe")
    Call<LoginResponse> MacYorumListe(
            @Header("TokenLar") String token,
            @Header("KullaniciToken") String kullaniciToken,
            @Header("Content-Type") String contenttype,
            @Query("Tarih") String tarih);



}
