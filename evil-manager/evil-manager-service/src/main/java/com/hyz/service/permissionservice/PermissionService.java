package com.hyz.service.permissionservice;

import java.util.List;

import com.hyz.pojo.PermissionDO;

public interface PermissionService {
	
	/**
	 * 根据用户id获取他所拥有的权限
	 * @param userId 用户id
	 * @return
	 */
	List<PermissionDO> getPermissionListByUserId(String userId);

}
