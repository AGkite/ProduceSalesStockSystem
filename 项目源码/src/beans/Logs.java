package beans;

public class Logs {
    private String ShopName;
    private String ShopId;
    private String State;
    private String count;
    private String times;
    private String type;

    public String getShopName() {
        return ShopName;
    }

    public void setShopName(String shopName) {
        ShopName = shopName;
    }

    public String getShopId() {
        return ShopId;
    }

    public void setShopId(String shopId) {
        ShopId = shopId;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getTimes() {
        return times;
    }

    public void setTimes(String times) {
        this.times = times;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    public Logs(){
        super();
    }
    public Logs( String ShopName, String ShopId, String State, String count, String times, String type){
        super();
        this.ShopName=ShopName;
        this.ShopId=ShopId;
        this.State=State;
        this.count=count;
        this.times=times;
        this.type=type;
    }
}
