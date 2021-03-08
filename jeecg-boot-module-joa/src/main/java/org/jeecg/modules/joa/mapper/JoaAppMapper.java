package org.jeecg.modules.joa.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.joa.model.DraftModel;

/**
 * app专用mapper
 */
public interface JoaAppMapper {

    /**
     * 分业查询 online表单的草稿箱
     * @param page
     * @param query
     * @return
     */
    Page<DraftModel> queryDraftList(IPage<DraftModel> page,@Param("query") DraftModel query);
}
