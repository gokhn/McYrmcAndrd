package mac.yorum.android.app.models.mainmodels;

public class CouponMatch {

    public  String Id;
    public String CouponName;
    public String MatchDate;
    public String Home;
    public String Away;
    public String LeagueName;
    public  String Prediction;
    public  String BeddingRate;
    public  String BeddingCode;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getCouponName() {
        return CouponName;
    }

    public void setCouponName(String couponName) {
        CouponName = couponName;
    }

    public String getMatchDate() {
        return MatchDate;
    }

    public void setMatchDate(String matchDate) {
        MatchDate = matchDate;
    }

    public String getHome() {
        return Home;
    }

    public void setHome(String home) {
        Home = home;
    }

    public String getAway() {
        return Away;
    }

    public void setAway(String away) {
        Away = away;
    }

    public String getLeagueName() {
        return LeagueName;
    }

    public void setLeagueName(String leagueName) {
        LeagueName = leagueName;
    }

    public String getBeddingCode() {
        return BeddingCode;
    }

    public void setBeddingCode(String beddingCode) {
        BeddingCode = beddingCode;
    }

    public String getPrediction() {
        return Prediction;
    }

    public void setPrediction(String prediction) {
        Prediction = prediction;
    }

    public String getBeddingRate() {
        return BeddingRate;
    }

    public void setBeddingRate(String beddingRate) {
        BeddingRate = beddingRate;
    }


}
