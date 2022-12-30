package beans;

public class Goods {
    private int id;
    private int no;
    private String goodsName;
    private int count;
    private double price;
    private String type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    public Goods(){
        super();
    }
    public Goods( int id,int no,String goodsName,int count,double price,String type){
        super();
        this.id = id;
        this.no = no;
        this.goodsName = goodsName;
        this.count = count;
        this.price = price;
        this.type = type;
    }


}
