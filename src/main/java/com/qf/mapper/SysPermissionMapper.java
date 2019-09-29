package com.qf.mapper;

import com.qf.domain.SysPermission;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface SysPermissionMapper {
    List<SysPermission> selectPermissionByUserName(String username);
}
