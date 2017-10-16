package com.hyz.dao.Mybatis.ContentDao;

import java.util.List;

import com.hyz.pojo.Category;

/**
*Create at 2017年10月14日 下午4:22:06
*
*@autuor EVIL
*
*@version 1.0
*
*ProjectName evil-manager-dao
*
*Description: 
*        
*/
public interface ContentDao {
	/**
	 * 
	 * <p>MethodName: getCategoryList</p>
	 * <p>Description:根据用户id获取所有的分类 </p>
	 * @param userId
	 * @return
	 * 
	 * @author EVIL
	 * @date 2017年10月14日
	 * @version 1.0
	 * Create At 2017年10月14日 下午4:24:00
	 */
	List<Category> getCategoryList(String userId);
}
