package org.jeecg.modules.joa.controller;

import java.util.Arrays;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.constant.FillRuleConstant;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.FillRuleUtil;
import org.jeecg.modules.extbpm.process.common.WorkFlowGlobals;
import org.jeecg.modules.joa.entity.JoaDocSending;
import org.jeecg.modules.joa.service.IJoaDocSendingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import lombok.extern.slf4j.Slf4j;

 /**
 * @Title: Controller
 * @Description: 出差表
 * @author： jeecg-boot
 * @date：   2019-04-10
 * @version： V1.0
 */
@RestController
@RequestMapping("/joa/joaDocSending")
@Slf4j
public class JoaDocSendingController {
	@Autowired
	private IJoaDocSendingService joaDocSendingService;
	
	/**
	  * 分页列表查询
	 * @param joaDocSending
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@GetMapping(value = "/list")
	public Result<IPage<JoaDocSending>> queryPageList(JoaDocSending joaDocSending,
									  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									  HttpServletRequest req) {
		Result<IPage<JoaDocSending>> result = new Result<IPage<JoaDocSending>>();
		QueryWrapper<JoaDocSending> queryWrapper = QueryGenerator.initQueryWrapper(joaDocSending, req.getParameterMap());
		Page<JoaDocSending> page = new Page<JoaDocSending>(pageNo, pageSize);
		IPage<JoaDocSending> pageList = joaDocSendingService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}
	
	/**
	  *   添加
	 * @param joaDocSending
	 * @return
	 */
	@PostMapping(value = "/add")
	public Result<JoaDocSending> add(@RequestBody JoaDocSending joaDocSending) {
		Result<JoaDocSending> result = new Result<JoaDocSending>();
		try {
			joaDocSending.setBpmStatus(WorkFlowGlobals.BPM_BUS_STATUS_1);
			JSONObject formData = (JSONObject) JSONObject.toJSON(joaDocSending);
			String docCode = (String)FillRuleUtil.executeRule(FillRuleConstant.DOC_SEND,formData);
			joaDocSending.setDocCode(docCode);
			joaDocSending.setBookDate(new Date());
			joaDocSendingService.save(joaDocSending);
			result.setResult(joaDocSending);
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
		}
		return result;
	}
	
	/**
	  *  编辑
	 * @param joaDocSending
	 * @return
	 */
	@PutMapping(value = "/edit")
	public Result<JoaDocSending> edit(@RequestBody JoaDocSending joaDocSending) {
		Result<JoaDocSending> result = new Result<JoaDocSending>();
		JoaDocSending joaDocSendingEntity = joaDocSendingService.getById(joaDocSending.getId());
		if(joaDocSendingEntity==null) {
			result.error500("未找到对应实体");
		}else {
			JSONObject formData = (JSONObject) JSONObject.toJSON(joaDocSending);
			String docCode = (String)FillRuleUtil.executeRule(FillRuleConstant.DOC_SEND,formData);
			joaDocSending.setDocCode(docCode);
			boolean ok = joaDocSendingService.updateById(joaDocSending);
			//TODO 返回false说明什么？
			if(ok) {
				result.success("修改成功!");
			}
		}
		
		return result;
	}
	
	/**
	  *   通过id删除
	 * @param id
	 * @return
	 */
	@DeleteMapping(value = "/delete")
	public Result<JoaDocSending> delete(@RequestParam(name="id",required=true) String id) {
		Result<JoaDocSending> result = new Result<JoaDocSending>();
		JoaDocSending joaDocSending = joaDocSendingService.getById(id);
		if(joaDocSending==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = joaDocSendingService.removeById(id);
			if(ok) {
				result.success("删除成功!");
			}
		}
		
		return result;
	}
	
	/**
	  *  批量删除
	 * @param ids
	 * @return
	 */
	@DeleteMapping(value = "/deleteBatch")
	public Result<JoaDocSending> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<JoaDocSending> result = new Result<JoaDocSending>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.joaDocSendingService.removeByIds(Arrays.asList(ids.split(",")));
			result.success("删除成功!");
		}
		return result;
	}
	
	/**
	  * 通过id查询
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/queryById")
	public Result<JoaDocSending> queryById(@RequestParam(name="id",required=true) String id) {
		Result<JoaDocSending> result = new Result<JoaDocSending>();
		JoaDocSending joaDocSending = joaDocSendingService.getById(id);
		if(joaDocSending==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(joaDocSending);
			result.setSuccess(true);
		}
		return result;
	}

}
