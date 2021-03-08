package org.jeecg.modules.designform.service;

import org.jeecg.modules.designform.entity.DesignFormCommuse;
import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.designform.model.SysRoleDeisgnModel;

import java.util.List;

/**
 * @Description: 常用流程
 * @Author: jeecg-boot
 * @Date:   2020-03-25
 * @Version: V1.0
 */
public interface IDesignFormCommuseService extends IService<DesignFormCommuse> {
    /**
     * 根据用户id查询常用流程
     * @param userId
     * @return
     */
    List<SysRoleDeisgnModel> getCommuseByUserId(String userId);

    /**
     * 设置常用流程
     * @param userId
     * @param newDesignId
     * @param oldDessignId
     */
    void commUseDesignAdd(String userId, String newDesignId, String oldDessignId);
}
