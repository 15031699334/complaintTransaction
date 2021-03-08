package org.jeecg.modules.joa.util;

import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.math.RandomUtils;
import org.jeecg.common.handler.IFillRuleHandler;
import org.jeecg.common.util.oConvertUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
/**
* @Description: 公文发文 文号生成规则
* @author: scott
* @date: 2020/3/19 19:39
*/
public class DocCodeRule implements IFillRuleHandler {
    public static final String pre = "京计算中心报";


    @Override
    public Object execute(JSONObject params, JSONObject formData) {
        String prefix = pre;
        String orderNo = null;
        if (formData != null) {
            Object obj = formData.get("officeCode");
            if (obj != null) {
                prefix = obj.toString();
            }

            Object no = formData.get("orderNo");
            if (no != null) {
                orderNo = no.toString();
            }
        }
        if(orderNo==null){
            orderNo = String.valueOf(RandomUtil.randomInt(9999));
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        return prefix +"【" + format.format(new Date()) + "】" +orderNo+"号";
    }
}
