package org.jeecg.modules.designform.controller;

import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.common.system.vo.LoginUser;
import org.jeecg.modules.designform.entity.DesignFormCommuse;
import org.jeecg.modules.designform.model.DesignFormModel;
import org.jeecg.modules.designform.model.SysRoleDeisgnModel;
import org.jeecg.modules.designform.service.IDesignFormCommuseService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.system.base.controller.JeecgController;

import org.jeecg.modules.designform.service.ISysRoleDesignFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 常用流程
 * @Author: jeecg-boot
 * @Date:   2020-03-25
 * @Version: V1.0
 */
@Slf4j
@Api(tags="常用流程")
@RestController
@RequestMapping("/designform/designFormCommuse")
public class DesignFormCommuseController extends JeecgController<DesignFormCommuse, IDesignFormCommuseService> {
	@Autowired
	private IDesignFormCommuseService designFormCommuseService;
	@Autowired
	private ISysRoleDesignFormService roleDesignFormService;

	 /**
	  * OA菜单权限-查看
	  */
	 @RequestMapping(value = "/getRoleDegisnList", method = RequestMethod.GET)
	 public Result<List<DesignFormModel>> getRoleDegisnList(@RequestParam(value = "roleId") String roleId){
		 Result<List<DesignFormModel>> result = new Result<>();
		 List<DesignFormModel> roleDegisnList = roleDesignFormService.getRoleDegisnList(roleId);
		 result.setResult(roleDegisnList);
		 return result;
	 }

	 /**
	  * OA菜单权限-添加
	  */
	 @RequestMapping(value = "/sysRoleDesignAdd", method = RequestMethod.POST)
	 public Result<?> sysRoleDesignAdd(@RequestBody JSONObject json) {
		 LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		 String roleId = json.getString("roleId");
		 String newDesignId = json.getString("newDesignId");
		 String oldDessignId = json.getString("oldDessignId");
		 roleDesignFormService.sysRoleDesignAdd(loginUser.getId(),roleId,newDesignId,oldDessignId);
		 return Result.ok("添加成功！");
	 }

	 /**
	  * 根据当前登录人查看OA申请单权限
	  */
	 @RequestMapping(value = "/roleDegisnList", method = RequestMethod.GET)
	 public Result<List<SysRoleDeisgnModel>> roleDegisnList(@RequestParam(name = "procType", required = false) String procType){
		 Result<List<SysRoleDeisgnModel>> result = new Result<>();
		 LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		 String username=loginUser.getUsername();
		 List<SysRoleDeisgnModel> designForms = roleDesignFormService.roleDegisnList(username,procType);
		 result.setSuccess(true);
		 result.setResult(designForms);
		 return result;
	 }

	 /**
	  * 查询online表单
	  * @param procType
	  * @return
	  */
	 @RequestMapping(value = "/queryOnlineFormList", method = RequestMethod.GET)
	 public Result<List<SysRoleDeisgnModel>> queryOnlineFormList(@RequestParam(name = "procType", required = false) String procType){
		 Result<List<SysRoleDeisgnModel>> result = new Result<>();
		 List<SysRoleDeisgnModel> designForms = roleDesignFormService.queryOnlineFormList(procType);
		 result.setSuccess(true);
		 result.setResult(designForms);
		 return result;
	 }

	 /**
	  * 根据用户id获取常用流程
	  * @return
	  */
	 @RequestMapping(value = "/getCommuseByUserId", method = RequestMethod.GET)
	 public Result<List<SysRoleDeisgnModel>> getCommuseByUserId(){
		 LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		 Result<List<SysRoleDeisgnModel>> result = new Result<>();
		 List<SysRoleDeisgnModel> roleDegisnList = designFormCommuseService.getCommuseByUserId(sysUser.getId());
		 result.setSuccess(true);
		 result.setResult(roleDegisnList);
		 return result;
	 }

	 /**
	  * 设置常用流程
	  * @param json
	  * @return
	  */
	 @RequestMapping(value = "/commUseDesignAdd", method = RequestMethod.POST)
	 public Result<?> commUseDesignAdd(@RequestBody JSONObject json) {
		 LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		 String newDesignId = json.getString("newDesignId");
		 String oldDessignId = json.getString("oldDessignId");
		 designFormCommuseService.commUseDesignAdd(sysUser.getId(),newDesignId,oldDessignId);
		 return Result.ok("添加成功！");
	 }

	/**
	 * 分页列表查询
	 *
	 * @param designFormCommuse
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@ApiOperation(value="常用流程-分页列表查询", notes="常用流程-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(DesignFormCommuse designFormCommuse,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<DesignFormCommuse> queryWrapper = QueryGenerator.initQueryWrapper(designFormCommuse, req.getParameterMap());
		Page<DesignFormCommuse> page = new Page<DesignFormCommuse>(pageNo, pageSize);
		IPage<DesignFormCommuse> pageList = designFormCommuseService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param designFormCommuse
	 * @return
	 */
	@ApiOperation(value="常用流程-添加", notes="常用流程-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody DesignFormCommuse designFormCommuse) {
		designFormCommuseService.save(designFormCommuse);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param designFormCommuse
	 * @return
	 */
	@AutoLog(value = "常用流程-编辑")
	@ApiOperation(value="常用流程-编辑", notes="常用流程-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody DesignFormCommuse designFormCommuse) {
		designFormCommuseService.updateById(designFormCommuse);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@ApiOperation(value="常用流程-通过id删除", notes="常用流程-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		designFormCommuseService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@ApiOperation(value="常用流程-批量删除", notes="常用流程-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.designFormCommuseService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@ApiOperation(value="常用流程-通过id查询", notes="常用流程-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		DesignFormCommuse designFormCommuse = designFormCommuseService.getById(id);
		return Result.ok(designFormCommuse);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param designFormCommuse
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, DesignFormCommuse designFormCommuse) {
      return super.exportXls(request, designFormCommuse, DesignFormCommuse.class, "常用流程");
  }

  /**
   * 通过excel导入数据
   *
   * @param request
   * @param response
   * @return
   */
  @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
  public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
      return super.importExcel(request, response, DesignFormCommuse.class);
  }

}
