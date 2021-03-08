package org.jeecg.modules.designform.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.designform.entity.SysRoleDesignForm;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.jeecg.modules.designform.model.DesignFormModel;
import org.jeecg.modules.designform.model.SysRoleDeisgnModel;

/**
 * @Description: 系统角色与OA表单授权
 * @Author: jeecg-boot
 * @Date:   2020-03-25
 * @Version: V1.0
 */
public interface SysRoleDesignFormMapper extends BaseMapper<SysRoleDesignForm> {

    /**
     * 根据角色id获取已授权表单
     * @param roleId
     * @return
     */
    List<DesignFormModel> getRoleDegisnList(@Param("roleId") String roleId);

    /**
     * 根据用户获取关联表单
     * @param username
     * @param procType
     * @return
     */
    List<SysRoleDeisgnModel> roleDegisnList(@Param("username") String username, @Param("procType") String procType);

    /**
     * 查询online表单
     * @param procType
     * @return
     */
    List<SysRoleDeisgnModel> queryOnlineFormList(@Param("procType") String procType);

}
