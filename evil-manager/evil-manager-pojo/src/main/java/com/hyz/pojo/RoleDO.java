package com.hyz.pojo;

/**
 * 角色对象
 * @author EVIL
 *
 */
public class RoleDO {
//	@Id
//	@GeneratedValue(generator="system-uuid")  
//    @GenericGenerator(name = "system-uuid",strategy="uuid")  
	private String id;
//	@Column(name="code")
	private String code;
	
//	@Column(name="name")
	private String name;
	
//	@Column(name="remark")
	private String remark;
	
//	@Column(name="createTime")
	private String createTime;
	
//	@Column(name="editTime")
	private String editTime;

	public String getId() {
		return id;
	}
	public String getCode() {
		return code;
	}
	public String getName() {
		return name;
	}
	public String getRemark() {
		return remark;
	}
	public String getCreateTime() {
		return createTime;
	}
	public String getEditTime() {
		return editTime;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public void setEditTime(String editTime) {
		this.editTime = editTime;
	}
	

}
