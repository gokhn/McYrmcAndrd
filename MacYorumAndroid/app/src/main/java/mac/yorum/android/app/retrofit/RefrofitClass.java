package mac.yorum.android.app.retrofit;

import mac.yorum.android.app.models.LoginRequest;
import mac.yorum.android.app.models.LoginResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by gkhngngr on 23.1.2017.
 */

public interface RefrofitClass {

    @POST("Mobil/Giris")
    Call<LoginResponse>Login(@Header("Authorization-Token") String token,
                             @Header("Content-Type") String contenttype,
                             @Body LoginRequest loginRequest);


}
