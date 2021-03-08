package org.jeecg.modules.designform.service;

import org.jeecg.modules.designform.entity.SysRoleDesignForm;
import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.designform.model.DesignFormModel;
import org.jeecg.modules.designform.model.SysRoleDeisgnModel;

import java.util.List;

/**
 * @Description: 系统角色与OA表单授权
 * @Author: jeecg-boot
 * @Date:   2020-03-25
 * @Version: V1.0
 */
public interface ISysRoleDesignFormService extends IService<SysRoleDesignForm> {
    /**
     * 添加授权表单
     * @param userId
     * @param roleId
     * @param newDesignId
     * @param oldDessignId
     */
    void sysRoleDesignAdd(String userId,String roleId, String newDesignId, String oldDessignId);

    /**
     * 根据系统角色获取已授权表单
     * @param roleId
     * @return
     */
    List<DesignFormModel> getRoleDegisnList(String roleId);

    /**
     * 根据用户获取关联表单
     * @param username
     * @param procType
     * @return
     */
    List<SysRoleDeisgnModel> roleDegisnList(String username, String procType);

    /**
     * 查询online表单
     * @param procType
     * @return
     */
    List<SysRoleDeisgnModel> queryOnlineFormList(String procType);
}
