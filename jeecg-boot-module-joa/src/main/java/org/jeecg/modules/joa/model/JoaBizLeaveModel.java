package org.jeecg.modules.joa.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description: 请假申请表
 * @author： jeecg-boot
 * @date：   2019-04-08
 * @version： V1.0
 */
@Data
public class JoaBizLeaveModel implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键*/
	@TableId(type = IdType.UUID)
	private String id;
	/**名称*/
	private String name;
	/**申请编号*/
	private String applyNo;
	/**申请日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private java.util.Date applyDate;
	/**职务*/
	private String duty;
	/**请假类别*/
	private String leaveCategory;
	/**请假原因*/
	private String leaveReason;
	/**请假开始时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private java.util.Date leaveStartDate;
	/**请假结束时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private java.util.Date leaveEndDate;
	/**共计*/
	private Double total;
	/**紧急联系方式*/
	private String contactWay;
	/**批定职务代理人*/
	private String dutyDeputy;
	/**直接主管审批*/
	private String leaderApproval;
	/**部门负责人审批*/
	private String deptPrincipalApproval;
	/**HR负责人审批*/
	private String hrPrincipalApproval;
	/**行政部备案*/
	private String hrRecords;
	/**部门*/
	private String department;
	/**流转状态*/
	private String bpmStatus;
	/**创建人*/
	private String createName;
	/**创建人id*/
	private String createBy;
	/**创建时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private java.util.Date createTime;
	/**修改人*/
	private String updateName;
	/**修改人id*/
	private String updateBy;
	/**修改时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private java.util.Date updateTime;

	/**
	 * 任务节点ID
	 */
	private String taskId;
	private String taskName;
	private String taskKey;

	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date taskDate;

	/**
	 * 任务节点处理人
	 */
	private String assignee;

	/**
	 * 流程实例ID
	 */
	private String processInstId;
}
