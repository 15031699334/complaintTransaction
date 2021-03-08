package org.jeecg.modules.joa.entity;

import java.io.Serializable;

import org.springframework.format.annotation.DateTimeFormat;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

/**
 * @Description: 出差表
 * @author： jeecg-boot
 * @date：   2019-04-10
 * @version： V1.0
 */
@Data
@TableName("joa_doc_sending")
public class JoaDocSending implements Serializable {
    private static final long serialVersionUID = 1L;
    
	/**主键*/
	@TableId(type = IdType.UUID)
	private java.lang.String id;
	/**创建人名称*/
	private java.lang.String createName;
	/**创建人登录名称*/
	private java.lang.String createBy;
	/**创建日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private java.util.Date createTime;
	/**更新人名称*/
	private java.lang.String updateName;
	/**更新人登录名称*/
	private java.lang.String updateBy;
	/**更新日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private java.util.Date updateTime;
	/**所属部门*/
	private java.lang.String sysOrgCode;
	/**所属公司*/
	private java.lang.String sysCompanyCode;
	/**流程状态*/
	private java.lang.String bpmStatus;
	/**发文字号*/
	private java.lang.String docCode;
	/**登记人*/
	private java.lang.String booker;
	/**登记时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private java.util.Date bookDate;
	/**收文人*/
	private java.lang.String receiver;
	/**收文人名称*/
	private java.lang.String receiverName;
	/**公文标题*/
	private java.lang.String title;
	/**发文目标*/
	private java.lang.String sendTarget;
	/**主送机关*/
	private java.lang.String sendDepart;
	/**公文分类*/
	private java.lang.String docType;
	/**文种*/
	private java.lang.String classification;
	/**机密度*/
	private java.lang.String confidentiality;
	/**缓急程度*/
	private java.lang.String urgency;
	/**成文日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private java.util.Date writtenDate;
	/**审阅时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private java.util.Date reviewDate;
	/**主题词*/
	private java.lang.String theme;
	/**相关文件*/
	private java.lang.String extFile;
	/**印刷分数*/
	private java.lang.String printScore;

	/**
	 * 机关代字
	 */
	private String officeCode;

	/**
	 * 排序码
	 */
	private Integer orderNo;

}
