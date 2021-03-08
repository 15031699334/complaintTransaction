package org.jeecg.modules.joa.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jeecg.modules.joa.entity.JoaEmployeeLeave;
import org.jeecg.modules.joa.mapper.JoaEmployeeLeaveMapper;
import org.jeecg.modules.joa.model.JoaBizLeaveModel;
import org.jeecg.modules.joa.service.IJoaEmployeeLeaveService;
import org.springframework.stereotype.Service;

/**
 * @Description: 请假申请表
 * @author： jeecg-boot
 * @date：   2019-04-08
 * @version： V1.0
 */
@Service
public class JoaEmployeeLeaveServiceImpl extends ServiceImpl<JoaEmployeeLeaveMapper, JoaEmployeeLeave> implements IJoaEmployeeLeaveService {

    @Override
    public Page<JoaBizLeaveModel> queryTaskList(JoaBizLeaveModel query, int pageNo, int pageSize) {
        Page<JoaBizLeaveModel> page = new Page<JoaBizLeaveModel>(pageNo,pageSize);
        return baseMapper.queryTaskList(page,query);
    }
}
