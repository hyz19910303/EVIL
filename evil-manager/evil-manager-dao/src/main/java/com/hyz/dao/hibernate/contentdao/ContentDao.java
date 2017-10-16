package com.hyz.dao.hibernate.contentdao;

import com.hyz.pojo.Category;
import com.hyz.pojo.Content;

/**
*Create at 2017年9月19日 下午12:27:33
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
	 * <p>MethodName: saveContent</p>
	 * <p>Description: </p>
	 * @param content 保存日志
	 * @return
	 * 
	 * @author EVIL
	 * @date 2017年9月19日
	 * @version 1.0
	 * Create At 2017年9月19日 下午12:28:46
	 */
	int saveContent(Content content);
	/**
	 * 
	 * <p>MethodName: saveCategory</p>
	 * <p>Description:保存分类 </p>
	 * @param category
	 * @return
	 * 
	 * @author EVIL
	 * @date 2017年10月13日
	 * @version 1.0
	 * Create At 2017年10月13日 上午9:55:19
	 */
	int saveCategory(Category category);
	/**
	 * 
	 * <p>MethodName: editCategory</p>
	 * <p>Description: 修改分类</p>
	 * @param category
	 * @return
	 * 
	 * @author EVIL
	 * @date 2017年10月13日
	 * @version 1.0
	 * Create At 2017年10月13日 下午10:48:02
	 */
	int editCategory(Category category);
	/**
	 * 
	 * <p>MethodName: deleteCategory</p>
	 * <p>Description: 删除分类</p>
	 * @param category
	 * @return
	 * 
	 * @author EVIL
	 * @date 2017年10月13日
	 * @version 1.0
	 * Create At 2017年10月13日 下午10:47:38
	 */
	int deleteCategory(Category category);
	
}
