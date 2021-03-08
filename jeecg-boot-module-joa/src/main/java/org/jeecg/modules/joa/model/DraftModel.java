package org.jeecg.modules.joa.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public class DraftModel {

    /**
     * 流程标题
     */
    private String title;

    /**
     * 标题 online表单取 表的描述
     */
    private String tableTxt;

    /**
     * 表名
     */
    private String formTableName;

    /**
     * 表ID
     */
    private String tableCode;

    /**
     * 数据ID
     */
    private String dataId;

    /**
     *  extActDesignFlowData id
     */
    private String designId;


    /**创建时间*/
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private java.util.Date createTime;

    /**
     * 流程状态
     */
    private String bpmStatus;

    /**
     * 创建人
     */
    private String createBy;


    /**
     * 表单类型 1 Online表单,2 自定义表单
     */
    private String formType;


}
