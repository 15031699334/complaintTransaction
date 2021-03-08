package org.jeecg.modules.joa.controller;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.modules.joa.entity.JoaOvertime;
import org.jeecg.modules.joa.entity.JoaOvertimeLeave;
import org.jeecg.modules.joa.service.IJoaOvertimeLeaveService;
import org.jeecg.modules.joa.service.IJoaOvertimeService;
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
 * @Description: 调休申请表
 * @author： jeecg-boot
 * @date：   2019-04-08
 * @version： V1.0
 */
@RestController
@RequestMapping("/joa/joaOvertimeLeave")
@Slf4j
public class JoaOvertimeLeaveController {
	@Autowired
	private IJoaOvertimeLeaveService joaOvertimeLeaveService;
	
	@Autowired
	private IJoaOvertimeService joaOvertimeService;
	
	
	/**
	  * 分页列表查询
	 * @param joaOvertimeLeave
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@GetMapping(value = "/list")
	public Result<IPage<JoaOvertimeLeave>> queryPageList(JoaOvertimeLeave joaOvertimeLeave,
									  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									  HttpServletRequest req) {
		Result<IPage<JoaOvertimeLeave>> result = new Result<IPage<JoaOvertimeLeave>>();
		QueryWrapper<JoaOvertimeLeave> queryWrapper = QueryGenerator.initQueryWrapper(joaOvertimeLeave, req.getParameterMap());
		Page<JoaOvertimeLeave> page = new Page<JoaOvertimeLeave>(pageNo, pageSize);
		IPage<JoaOvertimeLeave> pageList = joaOvertimeLeaveService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}
	
	/**
	  *   添加
	 * @param joaOvertimeLeave
	 * @return
	 */
	@PostMapping(value = "/add")
	public Result<JoaOvertimeLeave> add(@RequestBody JoaOvertimeLeave joaOvertimeLeave) {
		Result<JoaOvertimeLeave> result = new Result<JoaOvertimeLeave>();
		try {
					boolean istrue=joaOvertimeLeaveService.joaOvertimeLeaveAdd(joaOvertimeLeave);
					if(istrue) {
						result.setResult(joaOvertimeLeave);
						result.success("添加成功！");
					}else {
						result.success("添加失败！");
					}
					
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
		}
		return result;
	}
	
	/**
	  *  编辑
	 * @param joaOvertimeLeave
	 * @return
	 */
	@PutMapping(value = "/edit")
	public Result<JoaOvertimeLeave> edit(@RequestBody JoaOvertimeLeave joaOvertimeLeave) {
		Result<JoaOvertimeLeave> result = new Result<JoaOvertimeLeave>();
		JoaOvertimeLeave joaOvertimeLeaveEntity = joaOvertimeLeaveService.getById(joaOvertimeLeave.getId());
		if(joaOvertimeLeaveEntity==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = joaOvertimeLeaveService.updateById(joaOvertimeLeave);
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
	public Result<JoaOvertimeLeave> delete(@RequestParam(name="id",required=true) String id) {
		Result<JoaOvertimeLeave> result = new Result<JoaOvertimeLeave>();
		JoaOvertimeLeave joaOvertimeLeave = joaOvertimeLeaveService.getById(id);
		if(joaOvertimeLeave==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = joaOvertimeLeaveService.removeById(id);
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
	public Result<JoaOvertimeLeave> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<JoaOvertimeLeave> result = new Result<JoaOvertimeLeave>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.joaOvertimeLeaveService.removeByIds(Arrays.asList(ids.split(",")));
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
	public Result<JoaOvertimeLeave> queryById(@RequestParam(name="id",required=true) String id) {
		Result<JoaOvertimeLeave> result = new Result<JoaOvertimeLeave>();
		JoaOvertimeLeave joaOvertimeLeave = joaOvertimeLeaveService.getById(id);
		if(joaOvertimeLeave==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(joaOvertimeLeave);
			result.setSuccess(true);
		}
		return result;
	}
	
	/**
	 * 根据部门和申请人查询加班表信息
	 */
	@SuppressWarnings("unused")
	@GetMapping(value = "/queryByAppUser")
	public Result<List<JoaOvertime>> queryByAppUser(@RequestParam(name="applyUser",required=true) String applyUser,
											  @RequestParam(name="department",required=true)String department) {
		Result<List<JoaOvertime>> result = new Result<List<JoaOvertime>>();
		QueryWrapper<JoaOvertime> queryWrapper =new QueryWrapper<JoaOvertime>();
		queryWrapper.eq("apply_user", applyUser);
		queryWrapper.eq("department", department);
		queryWrapper.notInSql("id", "select id from joa_overtime where days_unpaid=0 and hour_unpaid=0");
		List<JoaOvertime> list=joaOvertimeService.list(queryWrapper);
		if(list==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(list);
			result.setSuccess(true);
			
		}
		return result;
		
	}
	
	
	

}
