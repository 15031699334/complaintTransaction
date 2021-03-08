package org.jeecg.modules.designform.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 系统角色与OA表单授权
 * @Author: jeecg-boot
 * @Date:   2020-03-25
 * @Version: V1.0
 */
@Data
@TableName("sys_role_design")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="sys_role_design对象", description="系统角色与OA表单授权")
public class SysRoleDesignForm {
    
	/**主键*/
	@TableId(type = IdType.ID_WORKER_STR)
    @ApiModelProperty(value = "主键")
	private java.lang.String id;
	/**角色id*/
	@Excel(name = "角色id", width = 15)
    @ApiModelProperty(value = "角色id")
	private java.lang.String roleId;
	/**表单设计器id*/
	@Excel(name = "表单设计器id", width = 15)
    @ApiModelProperty(value = "表单设计器id")
	private java.lang.String designId;

	public SysRoleDesignForm(String roleId, String designId) {
		this.roleId = roleId;
		this.designId = designId;
	}

	public SysRoleDesignForm() {

	}
}
