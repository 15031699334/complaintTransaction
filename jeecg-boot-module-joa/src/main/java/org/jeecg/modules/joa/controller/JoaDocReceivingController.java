package org.jeecg.modules.joa.controller;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.modules.joa.entity.JoaDocReceiving;
import org.jeecg.modules.joa.service.IJoaDocReceivingService;
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
 * @Description: 收文表
 * @author： jeecg-boot
 * @date：   2019-04-15
 * @version： V1.0
 */
@RestController
@RequestMapping("/joa/joaDocReceiving")
@Slf4j
public class JoaDocReceivingController {
	@Autowired
	private IJoaDocReceivingService joaDocReceivingService;
	
	/**
	  * 分页列表查询
	 * @param joaDocReceiving
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@GetMapping(value = "/list")
	public Result<IPage<JoaDocReceiving>> queryPageList(JoaDocReceiving joaDocReceiving,
									  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									  HttpServletRequest req) {
		Result<IPage<JoaDocReceiving>> result = new Result<IPage<JoaDocReceiving>>();
		QueryWrapper<JoaDocReceiving> queryWrapper = QueryGenerator.initQueryWrapper(joaDocReceiving, req.getParameterMap());
		Page<JoaDocReceiving> page = new Page<JoaDocReceiving>(pageNo, pageSize);
		IPage<JoaDocReceiving> pageList = joaDocReceivingService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}
	
	/**
	  *   添加
	 * @param joaDocReceiving
	 * @return
	 */
	@PostMapping(value = "/add")
	public Result<JoaDocReceiving> add(@RequestBody JoaDocReceiving joaDocReceiving) {
		Result<JoaDocReceiving> result = new Result<JoaDocReceiving>();
		try {
			joaDocReceivingService.save(joaDocReceiving);
			result.setResult(joaDocReceiving);
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
		}
		return result;
	}
	
	/**
	  *  编辑
	 * @param joaDocReceiving
	 * @return
	 */
	@PutMapping(value = "/edit")
	public Result<JoaDocReceiving> edit(@RequestBody JoaDocReceiving joaDocReceiving) {
		Result<JoaDocReceiving> result = new Result<JoaDocReceiving>();
		JoaDocReceiving joaDocReceivingEntity = joaDocReceivingService.getById(joaDocReceiving.getId());
		if(joaDocReceivingEntity==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = joaDocReceivingService.updateById(joaDocReceiving);
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
	public Result<JoaDocReceiving> delete(@RequestParam(name="id",required=true) String id) {
		Result<JoaDocReceiving> result = new Result<JoaDocReceiving>();
		JoaDocReceiving joaDocReceiving = joaDocReceivingService.getById(id);
		if(joaDocReceiving==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = joaDocReceivingService.removeById(id);
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
	public Result<JoaDocReceiving> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<JoaDocReceiving> result = new Result<JoaDocReceiving>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.joaDocReceivingService.removeByIds(Arrays.asList(ids.split(",")));
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
	public Result<JoaDocReceiving> queryById(@RequestParam(name="id",required=true) String id) {
		Result<JoaDocReceiving> result = new Result<JoaDocReceiving>();
		JoaDocReceiving joaDocReceiving = joaDocReceivingService.getById(id);
		if(joaDocReceiving==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(joaDocReceiving);
			result.setSuccess(true);
		}
		return result;
	}

}
