package org.jeecg.modules.joa.datastatistics;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.system.util.JwtUtil;
import org.jeecg.modules.extbpm.process.common.CodeUtil;
import org.jeecg.modules.extbpm.process.common.WorkFlowGlobals;
import org.jeecg.modules.joa.entity.JoaBusinesStrip;
import org.jeecg.modules.joa.service.IJoaBusinesStripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
* @Title: Controller
* @Description: 图表data
* @author： jeecg-boot
* @date：   2019-04-09
* @version： V1.0
*/
@RestController
@RequestMapping("/joa/dataStatistics")
@Slf4j
public class DataStatisticsController {

   /**
     * 获取图表数据
    * @param req
    * @return
    */
   @GetMapping(value = "/getChartDate")
   public String getChartDate(HttpServletRequest req) {
       return readJson("classpath:org/jeecg/modules/joa/datastatistics/mockjson/getChartDateInfo.json");
   }
    /**
     * 获取饼表数据
     * @param req
     * @return
     */
    @GetMapping(value = "/getPieDate")
    public String getPieDate(HttpServletRequest req) {
        return readJson("classpath:org/jeecg/modules/joa/datastatistics/mockjson/getPieDateInfo.json");
    }
    /**
     * 读取json格式文件
     * @param jsonSrc
     * @return
     */
    private String readJson(String jsonSrc) {
        String json = "";
        try {
            //File jsonFile = ResourceUtils.getFile(jsonSrc);
            //json = FileUtils.re.readFileToString(jsonFile);
            //换个写法，解决springboot读取jar包中文件的问题
            InputStream stream = getClass().getClassLoader().getResourceAsStream(jsonSrc.replace("classpath:", ""));
            json = IOUtils.toString(stream,"UTF-8");
        } catch (IOException e) {
            log.error(e.getMessage(),e);
        }
        return json;
    }
}
