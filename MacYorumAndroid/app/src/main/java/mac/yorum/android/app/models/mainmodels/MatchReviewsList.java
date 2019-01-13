package mac.yorum.android.app.models.mainmodels;

public class MatchReviewsList {

    public  String Id;
    public String MatchDate;
    public String Home;
    public String Away;
    public String LeagueName;
    public  String Summary;
    public  String BeddingCode;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
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

    public String getSummary() {
        return Summary;
    }

    public void setSummary(String summary) {
        Summary = summary;
    }

    public String getBeddingCode() {
        return BeddingCode;
    }

    public void setBeddingCode(String beddingCode) {
        BeddingCode = beddingCode;
    }

}
