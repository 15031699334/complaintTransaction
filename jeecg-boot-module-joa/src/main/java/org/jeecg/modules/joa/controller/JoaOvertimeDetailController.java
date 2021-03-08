package org.jeecg.modules.joa.controller;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.modules.joa.entity.JoaOvertimeDetail;
import org.jeecg.modules.joa.service.IJoaOvertimeDetailService;
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
 * @Description: 调休明细表
 * @author： jeecg-boot
 * @date：   2019-04-03
 * @version： V1.0
 */
@RestController
@RequestMapping("/joa/joaOvertimeDetail")
@Slf4j
public class JoaOvertimeDetailController {
	@Autowired
	private IJoaOvertimeDetailService joaOvertimeDetailService;
	
	/**
	  * 分页列表查询
	 * @param joaOvertimeDetail
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@GetMapping(value = "/list")
	public Result<IPage<JoaOvertimeDetail>> queryPageList(JoaOvertimeDetail joaOvertimeDetail,
									  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									  HttpServletRequest req) {
		Result<IPage<JoaOvertimeDetail>> result = new Result<IPage<JoaOvertimeDetail>>();
		QueryWrapper<JoaOvertimeDetail> queryWrapper = QueryGenerator.initQueryWrapper(joaOvertimeDetail, req.getParameterMap());
		Page<JoaOvertimeDetail> page = new Page<JoaOvertimeDetail>(pageNo, pageSize);
		IPage<JoaOvertimeDetail> pageList = joaOvertimeDetailService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}
	
	/**
	  *   添加
	 * @param joaOvertimeDetail
	 * @return
	 */
	@PostMapping(value = "/add")
	public Result<JoaOvertimeDetail> add(@RequestBody JoaOvertimeDetail joaOvertimeDetail) {
		Result<JoaOvertimeDetail> result = new Result<JoaOvertimeDetail>();
		try {
			joaOvertimeDetailService.save(joaOvertimeDetail);
			result.setResult(joaOvertimeDetail);
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
		}
		return result;
	}
	
	/**
	  *  编辑
	 * @param joaOvertimeDetail
	 * @return
	 */
	@PutMapping(value = "/edit")
	public Result<JoaOvertimeDetail> edit(@RequestBody JoaOvertimeDetail joaOvertimeDetail) {
		Result<JoaOvertimeDetail> result = new Result<JoaOvertimeDetail>();
		JoaOvertimeDetail joaOvertimeDetailEntity = joaOvertimeDetailService.getById(joaOvertimeDetail.getId());
		if(joaOvertimeDetailEntity==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = joaOvertimeDetailService.updateById(joaOvertimeDetail);
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
	public Result<JoaOvertimeDetail> delete(@RequestParam(name="id",required=true) String id) {
		Result<JoaOvertimeDetail> result = new Result<JoaOvertimeDetail>();
		JoaOvertimeDetail joaOvertimeDetail = joaOvertimeDetailService.getById(id);
		if(joaOvertimeDetail==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = joaOvertimeDetailService.removeById(id);
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
	public Result<JoaOvertimeDetail> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<JoaOvertimeDetail> result = new Result<JoaOvertimeDetail>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.joaOvertimeDetailService.removeByIds(Arrays.asList(ids.split(",")));
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
	public Result<JoaOvertimeDetail> queryById(@RequestParam(name="id",required=true) String id) {
		Result<JoaOvertimeDetail> result = new Result<JoaOvertimeDetail>();
		JoaOvertimeDetail joaOvertimeDetail = joaOvertimeDetailService.getById(id);
		if(joaOvertimeDetail==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(joaOvertimeDetail);
			result.setSuccess(true);
		}
		return result;
	}

}
