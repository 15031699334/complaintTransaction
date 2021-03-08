package org.jeecg.modules.servicetask.mapper;

import org.apache.ibatis.annotations.Param;

/**
 * @Description: OA考勤业务
 * @author: lvdandan
 * @date: 2020年04月03日 15:04
 */
public interface EoaSignMapper {
    /**
     * 根据id修改考勤补卡状态
     * @param bizStatus
     * @param id
     */
    int updateSignPatchBizStatus(@Param("bizStatus") String bizStatus, @Param("id") String id);
}
