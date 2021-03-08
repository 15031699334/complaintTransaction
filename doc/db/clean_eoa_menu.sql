-- 删除OA组件模块
delete from sys_permission where id in
 ('1174628684611563521',
'1176103166991773698',
'1194585809408344066',
'1234735670388441089',
'1235483978132119553',
'1244279838241562625',
'1244281754195435522',
'1247704541530546178',
'1247705246731460610',
'1194586555705049089',
'1194587923132039170',
'1194588524918194178',
'1194944096041451521',
'1197125991571480578',
'1241962019083567106',
'1242274973658173441',
'1242640213038112769',
'1244162795109400578',
'1174628954015903745',
'1174629137172770818',
'1231868225743073281',
'1235167949069754369',
'1235766252878712834',
'1174291880345526274',
'1176852999002075137',
'1176031222112026625',
'11760312221121525513');

drop table eoa_cms_article;
drop table eoa_cms_article_read;
drop table eoa_cms_menu;
drop table eoa_cms_site;
drop table eoa_file;
drop table eoa_file_log;
drop table eoa_mailbox_attach;
drop table eoa_mailbox_category;
drop table eoa_mailbox_info;
drop table eoa_mailbox_receiver;
drop table eoa_plan;
drop table eoa_char_msg;
drop table eoa_char_user_grouping;
drop table eoa_chat_group;
drop table eoa_chat_grouping;
drop table eoa_chat_historical;
drop table eoa_chat_off_message;
drop table eoa_chat_real_time;
drop table eoa_chat_user;
drop table eoa_chat_user_group;
drop table eoa_metting;
drop table eoa_metting_device;
drop table eoa_metting_room;
drop table eoa_metting_sign;
