package org.jeecg.modules.servicetask.service;

/**
 * @Description: OA考勤业务
 * @author: lvdandan
 * @date: 2020年04月03日 15:04
 */
public interface IEoaSignService {
    /**
     * 根据id修改考勤补卡状态
     * @param bizStatus
     * @param id
     */
    int updateSignPatchBizStatus(String bizStatus, String id);
}
