package mac.yorum.android.app.retrofit;

import mac.yorum.android.app.models.LoginRequest;
import mac.yorum.android.app.models.LoginResponse;
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

    @GET("/Mobil/KuponListe")
    Call<LoginResponse> KuponListe(
            @Header("TokenLar") String token,
            @Header("KullaniciToken") String kullaniciToken,
            @Header("Content-Type") String contenttype,
            @Query("KuponTipi") String kuponTipi);

    @GET("/Mobil/KuponListeDetay")
    Call<LoginResponse> KuponListeListeDetay(
            @Header("TokenLar") String token,
            @Header("KullaniciToken") String kullaniciToken,
            @Header("Content-Type") String contenttype,
            @Query("id") String kuponTipi);



}
