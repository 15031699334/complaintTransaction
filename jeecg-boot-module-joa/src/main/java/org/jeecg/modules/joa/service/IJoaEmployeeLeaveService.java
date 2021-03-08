package org.jeecg.modules.joa.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.joa.entity.JoaEmployeeLeave;
import org.jeecg.modules.joa.model.JoaBizLeaveModel;

/**
 * @Description: 请假申请表
 * @author： jeecg-boot
 * @date：   2019-04-08
 * @version： V1.0
 */
public interface IJoaEmployeeLeaveService extends IService<JoaEmployeeLeave> {

    Page<JoaBizLeaveModel> queryTaskList(JoaBizLeaveModel query, int pageNo, int pageSize);
}
