package org.jeecg.modules.designform.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * OA表单model
 */
@Data
public class DesignFormModel {
    private String id;
    private String desformCode;
    private String desformName;
    private String desformIcon;
    private String desformDesignJson;
    private String cgformCode;
    private String parentId;
    private String parentCode;
    private Integer desformType;
    private Boolean izOaShow;
    private String createBy;
    /**流程类型*/
    private String procType;
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    private String updateBy;
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
}
