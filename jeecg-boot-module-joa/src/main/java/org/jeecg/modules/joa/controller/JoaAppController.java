package org.jeecg.modules.joa.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.vo.LoginUser;
import org.jeecg.modules.joa.model.DraftModel;
import org.jeecg.modules.joa.service.IJoaAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/app/joa")
@Slf4j
public class JoaAppController {

    @Autowired
    private IJoaAppService joaAppService;


    /**
     * 查询草稿箱
     * /app/joa/draftList
     * @param model
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @GetMapping(value = "/draftList")
    public Result<List<DraftModel>> queryDraftList(DraftModel model,
            @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
            @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
            HttpServletRequest req) {
        Result<List<DraftModel>> result = new Result<List<DraftModel>>();
        //设置查询条件 创建人为当前登陆人
        LoginUser user = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        model.setCreateBy(user.getUsername());
        Page<DraftModel> pageList = joaAppService.queryDraftList(model,pageNo,pageSize);
        result.setResult(pageList.getRecords());
        result.setSuccess(true);
        return result;
    }
}
