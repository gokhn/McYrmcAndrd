package mac.yorum.android.app.models;

public class LoginResponse {

    public  String Status;

    public mac.yorum.android.app.models.Result getResult() {
        return Result;
    }

    public void setResult(mac.yorum.android.app.models.Result result) {
        Result = result;
    }

    public Result Result;


}
