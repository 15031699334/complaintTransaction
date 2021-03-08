package org.jeecg.modules.joa.entity;

import java.io.Serializable;

import org.springframework.format.annotation.DateTimeFormat;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

/**
 * @Description: 请假申请表
 * @author： jeecg-boot
 * @date：   2019-04-08
 * @version： V1.0
 */
@Data
@TableName("joa_employee_leave")
public class JoaEmployeeLeave implements Serializable {
    private static final long serialVersionUID = 1L;
    
	/**主键*/
	@TableId(type = IdType.UUID)
	private java.lang.String id;
	/**名称*/
	private java.lang.String name;
	/**申请编号*/
	private java.lang.String applyNo;
	/**申请日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private java.util.Date applyDate;
	/**职务*/
	private java.lang.String duty;
	/**请假类别*/
	private java.lang.String leaveCategory;
	/**请假原因*/
	private java.lang.String leaveReason;
	/**请假开始时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private java.util.Date leaveStartDate;
	/**请假结束时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private java.util.Date leaveEndDate;
	/**共计*/
	private java.lang.Double total;
	/**紧急联系方式*/
	private java.lang.String contactWay;
	/**批定职务代理人*/
	private java.lang.String dutyDeputy;
	/**直接主管审批*/
	private java.lang.String leaderApproval;
	/**部门负责人审批*/
	private java.lang.String deptPrincipalApproval;
	/**HR负责人审批*/
	private java.lang.String hrPrincipalApproval;
	/**行政部备案*/
	private java.lang.String hrRecords;
	/**部门*/
	private java.lang.String department;
	/**流转状态*/
	private java.lang.String bpmStatus;
	/**创建人*/
	private java.lang.String createName;
	/**创建人id*/
	private java.lang.String createBy;
	/**创建时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private java.util.Date createTime;
	/**修改人*/
	private java.lang.String updateName;
	/**修改人id*/
	private java.lang.String updateBy;
	/**修改时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private java.util.Date updateTime;
}
