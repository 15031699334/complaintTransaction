package org.jeecg.modules.joa.controller;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.system.util.JwtUtil;
import org.jeecg.modules.extbpm.process.common.CodeUtil;
import org.jeecg.modules.extbpm.process.common.WorkFlowGlobals;
import org.jeecg.modules.joa.entity.JoaBusinesStrip;
import org.jeecg.modules.joa.service.IJoaBusinesStripService;
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
 * @date：   2019-04-09
 * @version： V1.0
 */
@RestController
@RequestMapping("/joa/joaBusinesStrip")
@Slf4j
public class JoaBusinesStripController {
	@Autowired
	private IJoaBusinesStripService joaBusinesStripService;
	
	/**
	  * 分页列表查询
	 * @param joaBusinesStrip
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@GetMapping(value = "/list")
	public Result<IPage<JoaBusinesStrip>> queryPageList(JoaBusinesStrip joaBusinesStrip,
									  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									  HttpServletRequest req) {
		Result<IPage<JoaBusinesStrip>> result = new Result<IPage<JoaBusinesStrip>>();
		QueryWrapper<JoaBusinesStrip> queryWrapper = QueryGenerator.initQueryWrapper(joaBusinesStrip, req.getParameterMap());
		Page<JoaBusinesStrip> page = new Page<JoaBusinesStrip>(pageNo, pageSize);
		IPage<JoaBusinesStrip> pageList = joaBusinesStripService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}
	
	/**
	  *   添加
	 * @param joaBusinesStrip
	 * @return
	 */
	@PostMapping(value = "/add")
	public Result<JoaBusinesStrip> add(@RequestBody JoaBusinesStrip joaBusinesStrip,HttpServletRequest request) {
		Result<JoaBusinesStrip> result = new Result<JoaBusinesStrip>();
		try {
			joaBusinesStrip.setApplyNo(CodeUtil.getUUIDNum());
			joaBusinesStrip.setBpmStatus(WorkFlowGlobals.BPM_BUS_STATUS_1);
			String username = JwtUtil.getUserNameByToken(request);
			joaBusinesStrip.setApplyUserName(username);
			joaBusinesStripService.save(joaBusinesStrip);
			result.setResult(joaBusinesStrip);
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
		}
		return result;
	}
	
	/**
	  *  编辑
	 * @param joaBusinesStrip
	 * @return
	 */
	@PutMapping(value = "/edit")
	public Result<JoaBusinesStrip> edit(@RequestBody JoaBusinesStrip joaBusinesStrip) {
		Result<JoaBusinesStrip> result = new Result<JoaBusinesStrip>();
		JoaBusinesStrip joaBusinesStripEntity = joaBusinesStripService.getById(joaBusinesStrip.getId());
		if(joaBusinesStripEntity==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = joaBusinesStripService.updateById(joaBusinesStrip);
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
	public Result<JoaBusinesStrip> delete(@RequestParam(name="id",required=true) String id) {
		Result<JoaBusinesStrip> result = new Result<JoaBusinesStrip>();
		JoaBusinesStrip joaBusinesStrip = joaBusinesStripService.getById(id);
		if(joaBusinesStrip==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = joaBusinesStripService.removeById(id);
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
	public Result<JoaBusinesStrip> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<JoaBusinesStrip> result = new Result<JoaBusinesStrip>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.joaBusinesStripService.removeByIds(Arrays.asList(ids.split(",")));
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
	public Result<JoaBusinesStrip> queryById(@RequestParam(name="id",required=true) String id) {
		Result<JoaBusinesStrip> result = new Result<JoaBusinesStrip>();
		JoaBusinesStrip joaBusinesStrip = joaBusinesStripService.getById(id);
		if(joaBusinesStrip==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(joaBusinesStrip);
			result.setSuccess(true);
		}
		return result;
	}
	
	/**
	  * 通过tripApplyNo查询出差单
	 * @param tripApplyNo
	 * @return
	 */
	@GetMapping(value = "/queryByTripApplyNo")
	public Result<JoaBusinesStrip> queryByTripApplyNo(@RequestParam(name="tripApplyNo",required=true) String tripApplyNo) {
		Result<JoaBusinesStrip> result = new Result<JoaBusinesStrip>();
		QueryWrapper<JoaBusinesStrip> queryWrapper = new QueryWrapper<JoaBusinesStrip>();
		queryWrapper.lambda().eq(JoaBusinesStrip::getApplyNo, tripApplyNo);
		JoaBusinesStrip joaBusinesStrip = joaBusinesStripService.getOne(queryWrapper);
		if(joaBusinesStrip==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(joaBusinesStrip);
			result.setSuccess(true);
		}
		return result;
	}

}
