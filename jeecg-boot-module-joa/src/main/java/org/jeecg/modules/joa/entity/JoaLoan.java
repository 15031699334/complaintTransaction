package org.jeecg.modules.joa.entity;

import java.io.Serializable;

import org.springframework.format.annotation.DateTimeFormat;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

/**
 * @Description: 借款表
 * @author： jeecg-boot
 * @date：   2019-04-08
 * @version： V1.0
 */
@Data
@TableName("joa_loan")
public class JoaLoan implements Serializable {
    private static final long serialVersionUID = 1L;
    
	/**主键*/
	@TableId(type = IdType.UUID)
	private java.lang.String id;
	/**申请编号*/
	private java.lang.String applyNo;
	/**出差申请编号*/
	private java.lang.String tripApplyNo;
	/**借款人id*/
	private java.lang.String loanUserId;
	/**借款人*/
	private java.lang.String loanUserName;
	/**部门id*/
	private java.lang.String departId;
	/**部门名称*/
	private java.lang.String departName;
	/**借款时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private java.util.Date loanTime;
	/**借款金额*/
	private java.math.BigDecimal loanAmount;
	/**借款用途*/
	private java.lang.String loanUsage;
	/**备注*/
	private java.lang.String remarks;
	/**部门领导id*/
	private java.lang.String departLeaderId;
	/**部门领导审核*/
	private java.lang.String departLeaderAudit;
	/**财务用户id*/
	private java.lang.String financeUserId;
	/**财务审核*/
	private java.lang.String financeAudit;
	/**出纳员id*/
	private java.lang.String cashierId;
	/**出纳放款金额*/
	private java.math.BigDecimal cashierLoanAmount;
	/**出纳放款时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private java.util.Date cashierLoanTime;
	/**总经理id*/
	private java.lang.String managerId;
	/**总经理审核*/
	private java.lang.String managerAudit;
	/**流转状态*/
	private java.lang.String bpmStatus;
	/**创建人登录名称*/
	private java.lang.String createBy;
	/**创建日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private java.util.Date createTime;
	/**更新人登录名称*/
	private java.lang.String updateBy;
	/**更新日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private java.util.Date updateTime;
}
