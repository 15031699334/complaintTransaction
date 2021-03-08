package org.jeecg.modules.joa.service.impl;

import java.util.UUID;

import org.jeecg.modules.joa.entity.JoaOvertime;
import org.jeecg.modules.joa.entity.JoaOvertimeDetail;
import org.jeecg.modules.joa.entity.JoaOvertimeLeave;
import org.jeecg.modules.joa.mapper.JoaOvertimeDetailMapper;
import org.jeecg.modules.joa.mapper.JoaOvertimeLeaveMapper;
import org.jeecg.modules.joa.mapper.JoaOvertimeMapper;
import org.jeecg.modules.joa.service.IJoaOvertimeLeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 调休申请表
 * @author： jeecg-boot
 * @date：   2019-04-08
 * @version： V1.0
 */
@Service
public class JoaOvertimeLeaveServiceImpl extends ServiceImpl<JoaOvertimeLeaveMapper, JoaOvertimeLeave> implements IJoaOvertimeLeaveService {

	@Autowired
	private JoaOvertimeMapper joaOvertimeMapper;
	
	@Autowired
	private JoaOvertimeLeaveMapper joaOvertimeLeaveMapper;
	
	@Autowired
	private JoaOvertimeDetailMapper joaOvertimeDetailMapper;
	
	@Override
	@Transactional
	public boolean joaOvertimeLeaveAdd(JoaOvertimeLeave joaOvertimeLeave) {
		try {
			if(joaOvertimeLeave!=null) {
				UUID uuid = UUID.randomUUID();
				String str=uuid.toString();
				// 去掉"-"符号
				String temp = str.substring(0, 8) + str.substring(9, 13) + str.substring(14, 18) + str.substring(19, 23) + str.substring(24);
				joaOvertimeLeave.setId(temp);
				for(int i=0;i<joaOvertimeLeave.getDataSource().size();i++) {
					String id=joaOvertimeLeave.getDataSource().get(i).getId();
					Integer daysPaid=joaOvertimeLeave.getDataSource().get(i).getDay();//此次补偿的天数
					Integer hourPaid=joaOvertimeLeave.getDataSource().get(i).getHour();//此次补偿的小时
					Integer daysUnPaid=joaOvertimeLeave.getDataSource().get(i).getDaysUnpaid();//未补偿天数
					Integer hourUnPaid=joaOvertimeLeave.getDataSource().get(i).getHourUnpaid();//未补偿小时
					//这个判断是前台如果没有编辑单元格的话是Null，是null就把他变成防止报错
					if(daysPaid==null) {
						daysPaid=0;
					}
					if(hourPaid==null) {
						hourPaid=0;
					}
					JoaOvertime joaOvertime=new JoaOvertime();
					joaOvertime.setDaysUnpaid(daysUnPaid-daysPaid);
					joaOvertime.setHourUnpaid(hourUnPaid-hourPaid);
					joaOvertime.setId(id);
					if(daysPaid==0&&hourPaid==0) {
						continue;
					}else {
						joaOvertimeMapper.updateById(joaOvertime);
						JoaOvertimeDetail joaOvertimeDetail=new JoaOvertimeDetail();
						joaOvertimeDetail.setOvertimeLeaveId(joaOvertimeLeave.getId());
						joaOvertimeDetail.setOvertimeId(id);
						joaOvertimeDetail.setApplyDay(daysPaid);
						joaOvertimeDetail.setApplyHour(hourPaid);
						joaOvertimeDetailMapper.insert(joaOvertimeDetail);
					}
					
				}
				joaOvertimeLeaveMapper.insert(joaOvertimeLeave);
				return true;
			}else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}

}
