package com.hyz.dao.hibernate.contentdao;

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
	
}
