package org.jeecg.modules.servicetask.easyoa;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.jeecg.common.constant.CommonConstant;
import org.jeecg.common.util.SpringContextUtils;
import org.jeecg.modules.servicetask.service.IEoaSignService;

/**
 * @Description: 考勤补卡审批同意服务
 * @author: lvdandan
 * @date: 2020年04月03日 15:07
 */
public class OaSignPatchAgreeService implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        IEoaSignService eoaSignService = (IEoaSignService) SpringContextUtils.getBean("eoaSignServiceImpl");
        String id = execution.getVariable("id").toString();
        int count = eoaSignService.updateSignPatchBizStatus(CommonConstant.SIGN_PATCH_BIZ_STATUS_1,id);
        if(count == 0){
            throw new RuntimeException("未找到审批记录");
        }
    }
}
