package mac.yorum.android.app.models.mainmodels;

public class Coupon {


    public String Id;
    public String CouponName;
    public String CouponType;
    public String CouponExpiredDate;
    public String CouponDescription;
    public String TotalMatch;
    public String TotalRate;
    public String TotalPrice;
    public String TotalEarn;

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

    public String getCouponType() {
        return CouponType;
    }

    public void setCouponType(String couponType) {
        CouponType = couponType;
    }

    public String getCouponExpiredDate() {
        return CouponExpiredDate;
    }

    public void setCouponExpiredDate(String couponExpiredDate) {
        CouponExpiredDate = couponExpiredDate;
    }

    public String getCouponDescription() {
        return CouponDescription;
    }

    public void setCouponDescription(String couponDescription) {
        CouponDescription = couponDescription;
    }

    public String getTotalMatch() {
        return TotalMatch;
    }

    public void setTotalMatch(String totalMatch) {
        TotalMatch = totalMatch;
    }

    public String getTotalRate() {
        return TotalRate;
    }

    public void setTotalRate(String totalRate) {
        TotalRate = totalRate;
    }

    public String getTotalPrice() {
        return TotalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        TotalPrice = totalPrice;
    }

    public String getTotalEarn() {
        return TotalEarn;
    }

    public void setTotalEarn(String totalEarn) {
        TotalEarn = totalEarn;
    }
}
