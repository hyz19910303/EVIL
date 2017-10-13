package com.hyz.pojo;
/**
*Create at 2017年9月19日 上午10:18:05
*
*@autuor EVIL
*
*@version 1.0
*
*ProjectName evil-manager-pojo
*
*Description: 文章对应的实体类
*        
*/
public class Content {
	
	private String content_id;
	private String category_id;
	private String title;
	private String content;
	private String flag;
	private String createTime;
	private String updateTime;
	public String getContent_id() {
		return content_id;
	}
	public void setContent_id(String content_id) {
		this.content_id = content_id;
	}
	
	
	public String getCategory_id() {
		return category_id;
	}
	public void setCategory_id(String category_id) {
		this.category_id = category_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	
	
	
}
