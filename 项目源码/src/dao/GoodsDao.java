package dao;

import beans.Goods;

public interface GoodsDao {
	  public Goods findgoodsbyId(int id);
	  public Goods findgoodsbyId2(int id);
	  public void createNew();
	  public void addgoods(String goodsname, int id);
	  public void updatagoods(Goods good);
	  public int getid();
	  public Goods findgoodsbyname(String name);
	  public void Drop(int no);
//	  public void AddDrop(Goods good);
	  public void Dropsoldout(int no);
	  void addtalk(String talk,String no);
}
