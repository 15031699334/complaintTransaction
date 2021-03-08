package org.jeecg.modules.extbpm.process.common;

/**  
* 项目名称：jeecg
* 类名称：Globals   
* 类描述：  全局变量定义
* 创建人：zhoujf      
* @version    
*
 */
public final class WorkFlowGlobals {
	


	 /**
	  * 流程发布状态
	  */
	public static Integer Process_Deploy_NO=0;//未发布
	public static Integer Process_Deploy_YES=1;//已发布
	
	/**
	 * BPM_BUS_STATUS[流程业务单据状态位]
	 * 待提交:1/处理中:2/处理完毕:3
	 */
	public static String BPM_BUS_STATUS_1 = "1";//待提交
	public static String BPM_BUS_STATUS_2 = "2";//处理中
	public static String BPM_BUS_STATUS_3 = "3";//处理完毕
	public static String BPM_BUS_STATUS_4 = "4";//流程作废
	public static String BPM_BUS_STATUS_5 = "5";//流程挂起
	
	
	/**
	 * BPM 流程对应的表单KEY
	 */
	public static String BPM_FORM_KEY = "BPM_FORM_KEY";
	/**
	 * BPM 流程流程开始节点名字
	 */
	public static String BPM_NODE_START = "start";

	/**
	 * BPM 流程对应的流程实例ID KEY[当前流程]
	 */
	public static String JG_LOCAL_PROCESS_ID = "JG_LOCAL_PROCESS_ID";
	/**
	 * BPM 流程对应的流程实例ID KEY[主流程]
	 */
	public static String JG_SUB_MAIN_PROCESS_ID = "JG_SUB_MAIN_PROCESS_ID";
	/**
	 * 业务数据对应ID
	 */
	public static String BPM_DATA_ID = "BPM_DATA_ID";
	/**
	 * 自定义表单编码
	 */
	public static String BPM_DES_FORM_CODE = "BPM_DES_FORM_CODE";
	/**
	 * 自定义表单数据ID
	 */
	public static String BPM_DES_DATA_ID = "BPM_DES_DATA_ID";
	
	/**
	 * BPM 节点对应的表单URL(全局)
	 */
	public static String BPM_FORM_CONTENT_URL = "BPM_FORM_CONTENT_URL";
	/**
	 * BPM 节点对应的表单URL(全局) - 移动端
	 */
	public static String BPM_FORM_CONTENT_URL_MOBILE = "BPM_FORM_CONTENT_URL_MOBILE";
	
	/**
	 * BPM 节点对应的表单URL(全局)
	 */
	public static String BPM_STATUS = "bpm_status";
	/**
	 * BPM 业务标题表达式(全局)
	 */
	public static String BPM_BIZ_TITLE = "bpm_biz_title";
	/**
	 * BPM 流程办理风格(全局)
	 */
	public static String BPM_PROC_DEAL_STYLE = "bpm_proc_deal_style";
	/**
	 * BPM 业务表单类型流程变量
	 */
	public static String BPM_FORM_TYPE = "BPM_FORM_TYPE";
	/**
	 * BPM 业务表单类型 表单类型：1:Online表单,2:自定义表单,3:自定义开发
	 */
	public static String BPM_FORM_TYPE_1 = "1";
	public static String BPM_FORM_TYPE_2 = "2";
	public static String BPM_FORM_TYPE_3 = "3";
	
	/**
	 * BPM 流程对应的流程业务KEY
	 */
	public static String BPM_FORM_BUSINESSKEY = "BPM_FORM_BUSINESSKEY";
	/**
	 * 会签【用户组常量】
	 */
	public static String ASSIGNEE_USER_ID_LIST = "assigneeUserIdList";
	/**
	 * 流程追回状态
	 */
	public static String PROCESS_CALLBACKPROCESS_STATUS = "callBackProcess";
	/**
	 * 流程作废状态
	 */
	public static String PROCESS_INVALIDPROCESS_STATUS = "invalidProcess";
	/**
	 * 流程驳回状态
	 */
	public static String PROCESS_REJECTPROCESS_STATUS = "rejectProcess";
	
	/**
	 * BPM 节点对应的表单URL类型--pc电脑端 mobile移动端
	 */
	public static String SUFFIX_BPM_FORM_URL = "pc";
	public static String SUFFIX_BPM_FORM_URL_MOBILE = "mobile";
	
	
	/**
	 * BPM_OP_TYPE[流程日志操作类型]
	 * 普通任务:1/会签任务:2/驳回:3/作废:4/取回:5
	 */
	public static String BPM_OP_TYPE_1 = "1";//普通任务
	public static String BPM_OP_TYPE_2 = "2";//会签任务
	public static String BPM_OP_TYPE_3 = "3";//驳回
	public static String BPM_OP_TYPE_4 = "4";//作废
	public static String BPM_OP_TYPE_5 = "5";//取回


	/**
	 * 定时任务，自动委派用户ID
	 */
	public static final String SYS_AGENT_JOB_OPUSER_ID = "System Job";

	/**
	 * 表单设计器，流程中间草稿表
	 */
	public static final String DESIGN_FORM_DRAFT_TABLE = "EXT_ACT_DESIGN_FLOW_DATA";

}
