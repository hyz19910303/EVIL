package com.hyz.pojo;

import java.io.Serializable;

/**
 * 用户传输对象
 * @author EVIL
 *
 */

public class UserDO implements Serializable{

	private static final long serialVersionUID = -545410529734572294L;

	private String userId;  //主索引
	private String accountNo;//账号
	private String nickName;//昵称
	private String realName;//真实姓名
	private String password;//密码
	private String salt;//盐
	private String age;//年龄
	private String height;//身高
	private String weight;//体重
	private String education;//教育程度
	private String mobilePhono;//移动电话
	private String email;//邮箱
	private String address;//地址
	private String lockStatus;//锁定状态
	private String expireTime;//失效时间
	private String allowIps;//允许访问的我ip
	private String createTime;//创建时间
	private String editTime;//修改时间
	private String gender;//修改时间
	
	public String getUserId() {
		return userId;
	}
	public String getAccountNo() {
		return accountNo;
	}
	public String getNickName() {
		return nickName;
	}
	public String getRealName() {
		return realName;
	}
	public String getPassword() {
		return password;
	}
	public String getSalt() {
		return salt;
	}
	public String getAge() {
		return age;
	}
	public String getWeight() {
		return weight;
	}
	public String getEducation() {
		return education;
	}
	public String getMobilePhono() {
		return mobilePhono;
	}
	public String getEmail() {
		return email;
	}
	public String getAddress() {
		return address;
	}
	public String getLockStatus() {
		return lockStatus;
	}
	public String getExpireTime() {
		return expireTime;
	}
	public String getAllowIps() {
		return allowIps;
	}
	public String getCreateTime() {
		return createTime;
	}
	public String getEditTime() {
		return editTime;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	public void setMobilePhono(String mobilePhono) {
		this.mobilePhono = mobilePhono;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public void setLockStatus(String lockStatus) {
		this.lockStatus = lockStatus;
	}
	public void setExpireTime(String expireTime) {
		this.expireTime = expireTime;
	}
	public void setAllowIps(String allowIps) {
		this.allowIps = allowIps;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public void setEditTime(String editTime) {
		this.editTime = editTime;
	}
	public String getHeight() {
		return height;
	}
	public void setHeight(String height) {
		this.height = height;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}

	

}
