package dao;

import java.util.List;

public interface ThreadDao {
	
	//今日から一か月分の投稿を全部表示
	List<domain.Thread> showAll(String start, String end,int limit, int offset);
	
	//投稿
	boolean insertThread(String loginId, String post);
	
	//ページネーション実装
	List<domain.Thread> getThread(int limit, int offset);
	
	int getTotalThreadCount(String start, String end);
	
	//削除
	boolean deletePost(int id);
	

}
