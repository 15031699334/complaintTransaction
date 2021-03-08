package org.jeecg.modules.extbpm.process.common.expression;

import org.jeecg.common.system.api.ISysBaseAPI;
import org.jeecg.common.system.vo.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("flowNodeExpression")
public class FlowNodeExpression {
    @Autowired
    private ISysBaseAPI sysbase;

    /**
     * 查找部门负责人
     * ${flowNodeExpression.getDepartLeaders(applyUserId)}
     * @param applyUserId
     * @return
     */
    public List<String> getDepartLeaders(String applyUserId){
        LoginUser user = sysbase.getUserByName(applyUserId) ;
        String depId = sysbase.getDepartIdsByOrgCode(user.getOrgCode());
        List<String> users = sysbase.getDeptHeadByDepId(depId);
        return users;
    }
}
