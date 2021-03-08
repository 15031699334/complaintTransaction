package org.jeecg.modules.joa.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.joa.entity.JoaEmployeeLeave;
import org.jeecg.modules.joa.model.JoaBizLeaveModel;

/**
 * @Description: 请假申请表
 * @author： jeecg-boot
 * @date：   2019-04-08
 * @version： V1.0
 */
public interface JoaEmployeeLeaveMapper extends BaseMapper<JoaEmployeeLeave> {

    /**
     * 我的任务
     * @param page
     * @param query
     * @return
     */
    Page<JoaBizLeaveModel> queryTaskList(Page<JoaBizLeaveModel> page, @Param("query")JoaBizLeaveModel query);
}
