package org.jeecg.modules.designform.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.designform.entity.DesignFormCommuse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.jeecg.modules.designform.model.SysRoleDeisgnModel;

/**
 * @Description: 常用流程
 * @Author: jeecg-boot
 * @Date:   2020-03-25
 * @Version: V1.0
 */
public interface DesignFormCommuseMapper extends BaseMapper<DesignFormCommuse> {
    List<SysRoleDeisgnModel> getCommuseByUserId(@Param("userId") String userId);
}
