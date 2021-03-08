package org.jeecg.modules.joa.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.jeecg.modules.joa.model.DraftModel;

/**
 * app专用Service
 */
public interface IJoaAppService{

    /**
     * 分页查询草稿箱
     * @param query
     * @param pageNo
     * @param pageSize
     * @return
     */
    public Page<DraftModel> queryDraftList(DraftModel query,int pageNo,int pageSize);

}
