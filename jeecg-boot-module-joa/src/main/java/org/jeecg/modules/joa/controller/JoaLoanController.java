package org.jeecg.modules.joa.controller;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.modules.extbpm.process.common.CodeUtil;
import org.jeecg.modules.extbpm.process.common.WorkFlowGlobals;
import org.jeecg.modules.joa.entity.JoaLoan;
import org.jeecg.modules.joa.service.IJoaLoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import lombok.extern.slf4j.Slf4j;

 /**
 * @Title: Controller
 * @Description: 借款表
 * @author： jeecg-boot
 * @date：   2019-04-08
 * @version： V1.0
 */
@RestController
@RequestMapping("/joa/joaLoan")
@Slf4j
public class JoaLoanController {
	@Autowired
	private IJoaLoanService joaLoanService;
	
	/**
	  * 分页列表查询
	 * @param joaLoan
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@GetMapping(value = "/list")
	public Result<IPage<JoaLoan>> queryPageList(JoaLoan joaLoan,
									  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									  HttpServletRequest req) {
		Result<IPage<JoaLoan>> result = new Result<IPage<JoaLoan>>();
		QueryWrapper<JoaLoan> queryWrapper = QueryGenerator.initQueryWrapper(joaLoan, req.getParameterMap());
		Page<JoaLoan> page = new Page<JoaLoan>(pageNo, pageSize);
		IPage<JoaLoan> pageList = joaLoanService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}
	
	/**
	  *   添加
	 * @param joaLoan
	 * @return
	 */
	@PostMapping(value = "/add")
	public Result<JoaLoan> add(@RequestBody JoaLoan joaLoan) {
		Result<JoaLoan> result = new Result<JoaLoan>();
		try {
			joaLoan.setApplyNo(CodeUtil.getUUIDNum());
			joaLoanService.save(joaLoan);
			result.setResult(joaLoan);
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
		}
		return result;
	}
	
	/**
	  *   子流程添加
	 * @param joaLoan
	 * @return
	 */
	@PostMapping(value = "/addForSub")
	public Result<JoaLoan> addForSub(@RequestBody JoaLoan joaLoan) {
		Result<JoaLoan> result = new Result<JoaLoan>();
		try {
			joaLoan.setApplyNo(CodeUtil.getUUIDNum());
			joaLoan.setBpmStatus(WorkFlowGlobals.BPM_BUS_STATUS_2);
			joaLoanService.save(joaLoan);
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
		}
		return result;
	}
	
	/**
	  *  编辑
	 * @param joaLoan
	 * @return
	 */
	@PutMapping(value = "/edit")
	public Result<JoaLoan> edit(@RequestBody JoaLoan joaLoan) {
		Result<JoaLoan> result = new Result<JoaLoan>();
		JoaLoan joaLoanEntity = joaLoanService.getById(joaLoan.getId());
		if(joaLoanEntity==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = joaLoanService.updateById(joaLoan);
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
	public Result<JoaLoan> delete(@RequestParam(name="id",required=true) String id) {
		Result<JoaLoan> result = new Result<JoaLoan>();
		JoaLoan joaLoan = joaLoanService.getById(id);
		if(joaLoan==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = joaLoanService.removeById(id);
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
	public Result<JoaLoan> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<JoaLoan> result = new Result<JoaLoan>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.joaLoanService.removeByIds(Arrays.asList(ids.split(",")));
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
	public Result<JoaLoan> queryById(@RequestParam(name="id",required=true) String id) {
		Result<JoaLoan> result = new Result<JoaLoan>();
		JoaLoan joaLoan = joaLoanService.getById(id);
		if(joaLoan==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(joaLoan);
			result.setSuccess(true);
		}
		return result;
	}
	
	
	/**
	  * 通过tripApplyNo查询借款单
	 * @param tripApplyNo
	 * @return
	 */
	@GetMapping(value = "/queryByTripApplyNo")
	public Result<JoaLoan> queryByTripApplyNo(@RequestParam(name="id",required=true) String tripApplyNo) {
		Result<JoaLoan> result = new Result<JoaLoan>();
		LambdaQueryWrapper<JoaLoan> queryWrapper = new LambdaQueryWrapper<JoaLoan>();
		queryWrapper.eq(JoaLoan::getTripApplyNo, tripApplyNo);
		JoaLoan joaLoan = joaLoanService.getOne(queryWrapper);
		if(joaLoan==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(joaLoan);
			result.setSuccess(true);
		}
		return result;
	}

}
