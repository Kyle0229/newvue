package com.qf.domain;

import lombok.Data;

@Data
public class SysPermission {
    private Integer permission_id;
    private String per_name;
    private String menu_name;
    private String if_vilid;

}
