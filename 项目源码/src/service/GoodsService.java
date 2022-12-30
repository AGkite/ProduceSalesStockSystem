package service;

import beans.Goods;
import dao.impl.GoodsDaoimpl;

public class GoodsService {
 public  void update (int no ,String name,int count,double price,String type) {
		Goods g = new Goods();
		GoodsDaoimpl udi = new GoodsDaoimpl();
		udi.createNew();
		int id=udi.getid();
		udi.addgoods(name,id);
		g.setNo(no);g.setGoodsName(name);g.setCount(count);g.setPrice(price);g.setType(type);
		udi.updatagoods(g);
	   g.toString();
	}
 public void sale(int no,int count) {	 
	 Goods g=new GoodsDaoimpl().findgoodsbyId(no);
	 int a=g.getCount()-count;
	 g.setCount(a);
	 new GoodsDaoimpl().updatagoods(g);
 }
}
	 
	 
	 
	 
 

