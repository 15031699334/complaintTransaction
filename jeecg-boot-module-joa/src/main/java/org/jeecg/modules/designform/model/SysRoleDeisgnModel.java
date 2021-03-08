package org.jeecg.modules.designform.model;

import lombok.Data;

/**
 * 角色表单授权model
 */
@Data
public class SysRoleDeisgnModel {
    /**主键*/
    private String id;
    /**变单设计器code*/
    private String desformCode;
    /**变单设计器名称*/
    private String desformName;
    /**变单设计器图标  PC端*/
    private String desformIcon;
    /**变单设计器图标 移动app端*/
    private String appIcon;
    /**流程类型*/
    private String procType;
    /**流程名称*/
    private String procName;
    /**标题表达式*/
    private String titleExp;
}
