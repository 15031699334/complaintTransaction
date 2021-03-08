package org.jeecg.modules.designform.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.designform.entity.DesignFormCommuse;
import org.jeecg.modules.designform.entity.SysRoleDesignForm;
import org.jeecg.modules.designform.mapper.DesignFormCommuseMapper;
import org.jeecg.modules.designform.mapper.SysRoleDesignFormMapper;
import org.jeecg.modules.designform.model.DesignFormModel;
import org.jeecg.modules.designform.model.SysRoleDeisgnModel;
import org.jeecg.modules.designform.service.ISysRoleDesignFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.*;

/**
 * @Description: 系统角色与OA表单授权
 * @Author: jeecg-boot
 * @Date:   2020-03-25
 * @Version: V1.0
 */
@Service
public class SysRoleDesignFormServiceImpl extends ServiceImpl<SysRoleDesignFormMapper, SysRoleDesignForm> implements ISysRoleDesignFormService {
    @Autowired
    private SysRoleDesignFormMapper sysRoleDesignFormMapper;
    @Autowired
    private DesignFormCommuseMapper commuserMapper;

    @Override
    public void sysRoleDesignAdd(String userId, String roleId, String newDesignId, String oldDessignId) {
        List<String> add = getDiff(oldDessignId,newDesignId);
        if(add!=null && add.size()>0) {
            List<SysRoleDesignForm> list = new ArrayList<SysRoleDesignForm>();
            for (String designId : add) {
                if(oConvertUtils.isNotEmpty(designId)) {
                    SysRoleDesignForm rolepms = new SysRoleDesignForm(roleId, designId);
                    list.add(rolepms);
                }
            }
            this.saveBatch(list);
        }
        List<String> remove = getDiff(newDesignId,oldDessignId);
        if(remove!=null && remove.size()>0) {
            for (String designId : remove) {
                this.remove(new QueryWrapper<SysRoleDesignForm>().lambda().eq(SysRoleDesignForm::getRoleId, roleId).eq(SysRoleDesignForm::getDesignId, designId));
                //删除已设置的常用功能
                commuserMapper.delete(new QueryWrapper<DesignFormCommuse>().lambda().eq(DesignFormCommuse::getUserId, userId).eq(DesignFormCommuse::getDesformId, designId));
            }
        }
    }

    @Override
    public List<DesignFormModel> getRoleDegisnList(String roleId) {
        return sysRoleDesignFormMapper.getRoleDegisnList(roleId);
    }

    @Override
    public List<SysRoleDeisgnModel> roleDegisnList(String username, String procType) {
        return sysRoleDesignFormMapper.roleDegisnList(username,procType);
    }

    @Override
    public List<SysRoleDeisgnModel> queryOnlineFormList(String procType) {
        return sysRoleDesignFormMapper.queryOnlineFormList(procType);
    }

    /**
     * 从diff中找出main中没有的元素
     * @param main
     * @param diff
     * @return
     */
    private List<String> getDiff(String main, String diff){
        if(oConvertUtils.isEmpty(diff)) {
            return null;
        }
        if(oConvertUtils.isEmpty(main)) {
            return Arrays.asList(diff.split(","));
        }

        String[] mainArr = main.split(",");
        String[] diffArr = diff.split(",");
        Map<String, Integer> map = new HashMap<>();
        for (String string : mainArr) {
            map.put(string, 1);
        }
        List<String> res = new ArrayList<String>();
        for (String key : diffArr) {
            if(oConvertUtils.isNotEmpty(key) && !map.containsKey(key)) {
                res.add(key);
            }
        }
        return res;
    }
}
