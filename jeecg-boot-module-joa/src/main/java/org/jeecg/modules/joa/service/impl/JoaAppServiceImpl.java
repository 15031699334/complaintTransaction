package org.jeecg.modules.joa.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.jeecg.modules.joa.mapper.JoaAppMapper;
import org.jeecg.modules.joa.model.DraftModel;
import org.jeecg.modules.joa.service.IJoaAppService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class JoaAppServiceImpl implements IJoaAppService {

    @Resource
    private JoaAppMapper joaAppMapper;

    @Override
    public Page<DraftModel> queryDraftList(DraftModel query, int pageNo, int pageSize) {
        Page<DraftModel> page = new Page<DraftModel>(pageNo, pageSize, false);
        return joaAppMapper.queryDraftList(page,query);
    }
}
