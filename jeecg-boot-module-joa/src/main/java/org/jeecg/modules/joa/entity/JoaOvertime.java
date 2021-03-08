package org.jeecg.modules.joa.entity;

import java.io.Serializable;

import org.springframework.format.annotation.DateTimeFormat;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

/**
 * @Description: 加班申请单
 * @author： jeecg-boot
 * @date：   2019-04-01
 * @version： V1.0
 */
@Data
@TableName("joa_overtime")
public class JoaOvertime implements Serializable {
    private static final long serialVersionUID = 1L;
    
	/**id*/
	@TableId(type = IdType.UUID)
	private java.lang.String id;
	/**申请人*/
	private java.lang.String applyUser;
	/**部门*/
	private java.lang.String department;
	/**申请时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private java.util.Date applyTime;
	/**加班时段*/
	private java.lang.String overtimeType;
	/**加班开始时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private java.util.Date beginTime;
	/**加班结束时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private java.util.Date endTime;
	/**加班原由*/
	private java.lang.String reason;
	/**总加班时间 天*/
	private java.lang.String totalDay;
	/**总加班时间 小时*/
	private java.lang.String totalHour;
	/**直接领导审批*/
	private java.lang.String leaderRemark;
	/**部门领导审批*/
	private java.lang.String deptLeaderRemark;
	/**调休申请（天）*/
	private java.lang.Integer applyDay;
	/**调休申请（小时）*/
	private java.lang.Integer applyHour;
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
	private java.util.Date updateDate;
	/**所属部门*/
	private java.lang.String sysOrgCode;
	/**所属公司*/
	private java.lang.String sysCompanyCode;
	/**流程状态*/
	private java.lang.String bpmStatus;
	/**逻辑删除标识0未删除1删除*/
	private java.lang.Integer delFlag;
	/**未补偿天数 */
	private java.lang.Integer daysUnpaid;
	/**未补偿小时 */
	private java.lang.Integer hourUnpaid;
	@TableField(exist = false)
	/**补偿天数 */
	private java.lang.Integer day;
	@TableField(exist = false)
	/**补偿小时 */
	private java.lang.Integer hour;
	@TableField(exist = false)
	/** key*/
	private java.lang.Integer key;
}
