package mac.yorum.android.app.models;

import java.util.ArrayList;

import mac.yorum.android.app.models.mainmodels.MatchReviewsList;

public class MatchReviewsListResult {

    public  String Status;
    public ArrayList<MatchReviewsList> MatchReviewsList;

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public ArrayList<mac.yorum.android.app.models.mainmodels.MatchReviewsList> getMatchReviewsList() {
        return MatchReviewsList;
    }

    public void setMatchReviewsList(ArrayList<mac.yorum.android.app.models.mainmodels.MatchReviewsList> matchReviewsList) {
        MatchReviewsList = matchReviewsList;
    }


}
