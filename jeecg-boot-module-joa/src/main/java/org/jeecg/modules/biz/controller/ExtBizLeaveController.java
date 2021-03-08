package org.jeecg.modules.biz.controller;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.util.JwtUtil;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.biz.entity.ExtBizLeave;
import org.jeecg.modules.biz.service.IExtBizLeaveService;
import org.jeecg.modules.bpm.util.JoaUtil;
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
 * @Description: 请假单表
 * @author： jeecg-boot
 * @date：   2019-03-26
 * @version： V1.0
 */
@RestController
@RequestMapping("/biz/extBizLeave")
@Slf4j
public class ExtBizLeaveController {
	@Autowired
	private IExtBizLeaveService extBizLeaveService;
	
	/**
	  * 分页列表查询
	 * @param extBizLeave
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@GetMapping(value = "/list")
	public Result<IPage<ExtBizLeave>> queryPageList(ExtBizLeave extBizLeave,
									  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									  HttpServletRequest req) {
		Result<IPage<ExtBizLeave>> result = new Result<IPage<ExtBizLeave>>();
		QueryWrapper<ExtBizLeave> queryWrapper = new QueryWrapper<ExtBizLeave>(extBizLeave);
		Page<ExtBizLeave> page = new Page<ExtBizLeave>(pageNo,pageSize);
		//排序逻辑 处理
		String column = req.getParameter("column");
		String order = req.getParameter("order");
		if(oConvertUtils.isNotEmpty(column) && oConvertUtils.isNotEmpty(order)) {
			if("asc".equals(order)) {
				queryWrapper.orderByAsc(oConvertUtils.camelToUnderline(column));
			}else {
				queryWrapper.orderByDesc(oConvertUtils.camelToUnderline(column));
			}
		}
		//--------------------------我的审批任务过滤---开始-------------------------------
		String userid = JwtUtil.getUserNameByToken(req); // 获取当前登录用户
		String bizTaskType = req.getParameter("bizTaskType");//业务类型1待我审批2我发起的申请
		JoaUtil.filterRunningTask(bizTaskType,queryWrapper,userid, "TEST001");
		//--------------------------我的审批任务过滤---结束-------------------------------
		
		IPage<ExtBizLeave> pageList = extBizLeaveService.page(page, queryWrapper);
		//log.debug("查询当前页："+pageList.getCurrent());
		//log.debug("查询当前页数量："+pageList.getSize());
		//log.debug("查询结果数量："+pageList.getRecords().size());
		//log.debug("数据总数："+pageList.getTotal());
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}
	
	/**
	  *   添加
	 * @param extBizLeave
	 * @return
	 */
	@PostMapping(value = "/add")
	public Result<ExtBizLeave> add(@RequestBody ExtBizLeave extBizLeave) {
		Result<ExtBizLeave> result = new Result<ExtBizLeave>();
		try {
			extBizLeave.setBpmStatus("1");
			extBizLeaveService.save(extBizLeave);
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
		}
		return result;
	}
	
	/**
	  *  编辑
	 * @param extBizLeave
	 * @return
	 */
	@PutMapping(value = "/edit")
	public Result<ExtBizLeave> edit(@RequestBody ExtBizLeave extBizLeave) {
		Result<ExtBizLeave> result = new Result<ExtBizLeave>();
		ExtBizLeave extBizLeaveEntity = extBizLeaveService.getById(extBizLeave.getId());
		if(extBizLeaveEntity==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = extBizLeaveService.updateById(extBizLeave);
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
	public Result<ExtBizLeave> delete(@RequestParam(name="id",required=true) String id) {
		Result<ExtBizLeave> result = new Result<ExtBizLeave>();
		ExtBizLeave extBizLeave = extBizLeaveService.getById(id);
		if(extBizLeave==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = extBizLeaveService.removeById(id);
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
	public Result<ExtBizLeave> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<ExtBizLeave> result = new Result<ExtBizLeave>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.extBizLeaveService.removeByIds(Arrays.asList(ids.split(",")));
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
	public Result<ExtBizLeave> queryById(@RequestParam(name="id",required=true) String id) {
		Result<ExtBizLeave> result = new Result<ExtBizLeave>();
		ExtBizLeave extBizLeave = extBizLeaveService.getById(id);
		if(extBizLeave==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(extBizLeave);
			result.setSuccess(true);
		}
		return result;
	}

}
