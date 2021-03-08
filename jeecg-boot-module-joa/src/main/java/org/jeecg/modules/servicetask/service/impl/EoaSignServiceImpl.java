package org.jeecg.modules.servicetask.service.impl;

import org.jeecg.modules.servicetask.mapper.EoaSignMapper;
import org.jeecg.modules.servicetask.service.IEoaSignService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Description: OA考勤业务
 * @author: lvdandan
 * @date: 2020年04月03日 15:04
 */
@Service
public class EoaSignServiceImpl implements IEoaSignService {
    @Resource
    private EoaSignMapper eoaSignMapper;

    @Override
    public int updateSignPatchBizStatus(String bizStatus, String id) {
        return eoaSignMapper.updateSignPatchBizStatus(bizStatus,id);
    }
}
