package org.jeecg.modules.designform.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.designform.entity.DesignFormCommuse;
import org.jeecg.modules.designform.mapper.DesignFormCommuseMapper;
import org.jeecg.modules.designform.model.SysRoleDeisgnModel;
import org.jeecg.modules.designform.service.IDesignFormCommuseService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import javax.annotation.Resource;
import java.util.*;

/**
 * @Description: 常用流程
 * @Author: jeecg-boot
 * @Date:   2020-03-25
 * @Version: V1.0
 */
@Service
public class DesignFormCommuseServiceImpl extends ServiceImpl<DesignFormCommuseMapper, DesignFormCommuse> implements IDesignFormCommuseService {
    @Resource
    private DesignFormCommuseMapper commuserMapper;

    @Override
    public List<SysRoleDeisgnModel> getCommuseByUserId(String userId) {
        return commuserMapper.getCommuseByUserId(userId);
    }

    @Override
    public void commUseDesignAdd(String userId, String newDesignId, String oldDessignId) {
        List<String> add = getDiff(oldDessignId,newDesignId);
        if(add!=null && add.size()>0) {
            List<DesignFormCommuse> list = new ArrayList<DesignFormCommuse>();
            for (String designId : add) {
                if(oConvertUtils.isNotEmpty(designId)) {
                    DesignFormCommuse rolepms = new DesignFormCommuse(userId, designId);
                    list.add(rolepms);
                }
            }
            this.saveBatch(list);
        }
        List<String> remove = getDiff(newDesignId,oldDessignId);
        if(remove!=null && remove.size()>0) {
            for (String designId : remove) {
                this.remove(new QueryWrapper<DesignFormCommuse>().lambda().eq(DesignFormCommuse::getUserId, userId).eq(DesignFormCommuse::getDesformId, designId));
            }
        }
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
