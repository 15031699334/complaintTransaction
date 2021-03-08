SET FOREIGN_KEY_CHECKS = 0;

-- 清理流程数据
delete from act_evt_log; 
delete from act_hi_actinst;
delete from act_hi_attachment;
delete from act_hi_comment;
delete from act_hi_detail;
delete from act_hi_identitylink;
delete from act_hi_procinst;
delete from act_hi_taskinst;
delete from act_hi_varinst;
delete from act_id_membership;
delete from act_id_group;
delete from act_id_info;
delete from act_id_user;
delete from act_procdef_info;
delete from act_re_model;
delete from act_ru_identitylink;
delete from act_ru_task;
delete from act_ru_variable;
delete from act_ru_event_subscr;
delete from act_ru_execution WHERE SUPER_EXEC_ is not null;
delete from act_ru_execution WHERE PARENT_ID_ is not null;
delete from act_ru_execution;
delete from act_re_procdef;
delete from act_ru_job;
delete from act_ge_bytearray;
delete from act_re_deployment;

-- 清理流程单据测试数据
delete from ext_biz_leave;
delete from joa_busines_strip;
delete from joa_doc_sending;
delete from joa_employee_leave;
delete from joa_loan;


delete from ext_act_flow_data;
delete from ext_act_process_node_deploy;
delete from ext_act_task_cc;
delete from ext_act_bpm_log;
delete from ext_act_bpm_file;
delete from ext_act_mutil_flow_data;
delete from ext_act_task_notification;

update ext_act_process set process_status = 0

-- 清理表单设计器
delete from ext_act_design_flow_data;
delete from  design_form_data;
