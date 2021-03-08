package org.jeecg.modules.joa.entity;

import java.io.Serializable;

import org.springframework.format.annotation.DateTimeFormat;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

/**
 * @Description: 收文表
 * @author： jeecg-boot
 * @date：   2019-04-15
 * @version： V1.0
 */
@Data
@TableName("joa_doc_receiving")
public class JoaDocReceiving implements Serializable {
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
	/**收文字号*/
	private java.lang.String docCode;
	/**来文标题*/
	private java.lang.String title;
	/**来文部门*/
	private java.lang.String fromDepart;
	/**来文日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private java.util.Date fromDate;
	/**缓急程度*/
	private java.lang.String urgency;
	/**机密度*/
	private java.lang.String confidentiality;
	/**文种*/
	private java.lang.String classification;
	/**公文分类*/
	private java.lang.String docType;
	/**收文份数*/
	private java.lang.Integer receiveNum;
	/**关键词*/
	private java.lang.String keyword;
	/**相关文件*/
	private java.lang.String extFile;
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
}
