package org.jeecg.modules.joa.controller;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.system.vo.LoginUser;
import org.jeecg.modules.extbpm.process.common.CodeUtil;
import org.jeecg.modules.extbpm.process.common.WorkFlowGlobals;
import org.jeecg.modules.joa.entity.JoaEmployeeLeave;
import org.jeecg.modules.joa.model.JoaBizLeaveModel;
import org.jeecg.modules.joa.service.IJoaEmployeeLeaveService;
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
 * @Description: 请假申请表
 * @author： jeecg-boot
 * @date：   2019-04-08
 * @version： V1.0
 */
@RestController
@RequestMapping("/joa/joaEmployeeLeave")
@Slf4j
public class JoaEmployeeLeaveController {
	@Autowired
	private IJoaEmployeeLeaveService joaEmployeeLeaveService;
	
	/**
	  * 分页列表查询
	 * @param joaEmployeeLeave
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@GetMapping(value = "/list")
	public Result<IPage<JoaEmployeeLeave>> queryPageList(JoaEmployeeLeave joaEmployeeLeave,
									  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									  HttpServletRequest req) {
		Result<IPage<JoaEmployeeLeave>> result = new Result<IPage<JoaEmployeeLeave>>();
		QueryWrapper<JoaEmployeeLeave> queryWrapper = QueryGenerator.initQueryWrapper(joaEmployeeLeave, req.getParameterMap());
		Page<JoaEmployeeLeave> page = new Page<JoaEmployeeLeave>(pageNo, pageSize);
		IPage<JoaEmployeeLeave> pageList = joaEmployeeLeaveService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}
	
	/**
	  *   添加
	 * @param joaEmployeeLeave
	 * @return
	 */
	@PostMapping(value = "/add")
	public Result<JoaEmployeeLeave> add(@RequestBody JoaEmployeeLeave joaEmployeeLeave) {
		Result<JoaEmployeeLeave> result = new Result<JoaEmployeeLeave>();
		try {
			joaEmployeeLeave.setApplyNo(CodeUtil.getUUIDNum());
			joaEmployeeLeave.setBpmStatus(WorkFlowGlobals.BPM_BUS_STATUS_1);
			joaEmployeeLeaveService.save(joaEmployeeLeave);
			result.setResult(joaEmployeeLeave);
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
		}
		return result;
	}
	
	/**
	  *  编辑
	 * @param joaEmployeeLeave
	 * @return
	 */
	@PutMapping(value = "/edit")
	public Result<JoaEmployeeLeave> edit(@RequestBody JoaEmployeeLeave joaEmployeeLeave) {
		Result<JoaEmployeeLeave> result = new Result<JoaEmployeeLeave>();
		JoaEmployeeLeave joaEmployeeLeaveEntity = joaEmployeeLeaveService.getById(joaEmployeeLeave.getId());
		if(joaEmployeeLeaveEntity==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = joaEmployeeLeaveService.updateById(joaEmployeeLeave);
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
	public Result<JoaEmployeeLeave> delete(@RequestParam(name="id",required=true) String id) {
		Result<JoaEmployeeLeave> result = new Result<JoaEmployeeLeave>();
		JoaEmployeeLeave joaEmployeeLeave = joaEmployeeLeaveService.getById(id);
		if(joaEmployeeLeave==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = joaEmployeeLeaveService.removeById(id);
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
	public Result<JoaEmployeeLeave> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<JoaEmployeeLeave> result = new Result<JoaEmployeeLeave>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.joaEmployeeLeaveService.removeByIds(Arrays.asList(ids.split(",")));
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
	public Result<JoaEmployeeLeave> queryById(@RequestParam(name="id",required=true) String id) {
		Result<JoaEmployeeLeave> result = new Result<JoaEmployeeLeave>();
		JoaEmployeeLeave joaEmployeeLeave = joaEmployeeLeaveService.getById(id);
		if(joaEmployeeLeave==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(joaEmployeeLeave);
			result.setSuccess(true);
		}
		return result;
	}


	 /**
	  * 【工作流】基于业务的流程操作模式
	  * @param joaBizLeave
	  * @param pageNo
	  * @param pageSize
	  * @param req
	  * @return
	  */
	 @GetMapping(value = "/taskList")
	 public Result<Page<JoaBizLeaveModel>> taskList(JoaBizLeaveModel joaBizLeave,
													 @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
													 @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
													 HttpServletRequest req) {
		 Result<Page<JoaBizLeaveModel>> result = new Result<Page<JoaBizLeaveModel>>();
		 LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		 joaBizLeave.setAssignee(sysUser.getUsername());
		 Page<JoaBizLeaveModel> pageList = joaEmployeeLeaveService.queryTaskList(joaBizLeave,pageNo,pageSize);
		 result.setSuccess(true);
		 result.setResult(pageList);
		 return result;
	 }

}
