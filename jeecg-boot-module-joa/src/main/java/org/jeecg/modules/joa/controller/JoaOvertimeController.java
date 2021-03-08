package org.jeecg.modules.joa.controller;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.modules.joa.entity.JoaOvertime;
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
 * @Description: 加班申请单
 * @author： jeecg-boot
 * @date：   2019-04-01
 * @version： V1.0
 */
@RestController
@RequestMapping("/joa/joaOvertime")
@Slf4j
public class JoaOvertimeController {
	@Autowired
	private IJoaOvertimeService joaOvertimeService;
	
	/**
	  * 分页列表查询
	 * @param joaOvertime
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@GetMapping(value = "/list")
	public Result<IPage<JoaOvertime>> queryPageList(JoaOvertime joaOvertime,
									  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									  HttpServletRequest req) {
		Result<IPage<JoaOvertime>> result = new Result<IPage<JoaOvertime>>();
		QueryWrapper<JoaOvertime> queryWrapper = QueryGenerator.initQueryWrapper(joaOvertime, req.getParameterMap());
		Page<JoaOvertime> page = new Page<JoaOvertime>(pageNo, pageSize);
		IPage<JoaOvertime> pageList = joaOvertimeService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}
	
	/**
	  *   添加
	 * @param joaOvertime
	 * @return
	 */
	@PostMapping(value = "/add")
	public Result<JoaOvertime> add(@RequestBody JoaOvertime joaOvertime) {
		Result<JoaOvertime> result = new Result<JoaOvertime>();
		try {
			joaOvertimeService.save(joaOvertime);
			result.setResult(joaOvertime);
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
		}
		return result;
	}
	
	/**
	  *  编辑
	 * @param joaOvertime
	 * @return
	 */
	@PutMapping(value = "/edit")
	public Result<JoaOvertime> edit(@RequestBody JoaOvertime joaOvertime) {
		Result<JoaOvertime> result = new Result<JoaOvertime>();
		JoaOvertime joaOvertimeEntity = joaOvertimeService.getById(joaOvertime.getId());
		if(joaOvertimeEntity==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = joaOvertimeService.updateById(joaOvertime);
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
	public Result<JoaOvertime> delete(@RequestParam(name="id",required=true) String id) {
		Result<JoaOvertime> result = new Result<JoaOvertime>();
		JoaOvertime joaOvertime = joaOvertimeService.getById(id);
		if(joaOvertime==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = joaOvertimeService.removeById(id);
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
	public Result<JoaOvertime> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<JoaOvertime> result = new Result<JoaOvertime>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.joaOvertimeService.removeByIds(Arrays.asList(ids.split(",")));
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
	public Result<JoaOvertime> queryById(@RequestParam(name="id",required=true) String id) {
		Result<JoaOvertime> result = new Result<JoaOvertime>();
		JoaOvertime joaOvertime = joaOvertimeService.getById(id);
		if(joaOvertime==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(joaOvertime);
			result.setSuccess(true);
		}
		return result;
	}

}
