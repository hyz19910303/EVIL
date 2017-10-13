package com.hyz.service.content;

import com.hyz.pojo.Category;
import com.hyz.pojo.Content;

/**
*Create at 2017年9月19日 下午12:32:31
*
*@autuor EVIL
*
*@version 1.0
*
*ProjectName evil-manager-service
*
*Description: 
*        
*/
public interface ContentService {
	/**
	 * 
	 * <p>MethodName: saveContent</p>
	 * <p>Description:保存日志 </p>
	 * @param content
	 * @return
	 * 
	 * @author EVIL
	 * @date 2017年9月19日
	 * @version 1.0
	 * Create At 2017年9月19日 下午12:33:26
	 */
	String saveContent(Category category,Content content);
	/**
	 * 保存分类
	 * <p>MethodName: saveCategory</p>
	 * <p>Description: </p>
	 * @param category
	 * @return
	 * 
	 * @author EVIL
	 * @date 2017年10月13日
	 * @version 1.0
	 * Create At 2017年10月13日 上午11:03:12
	 */
	String saveCategory(Category category);
}
