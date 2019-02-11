package com.zh.service.flood;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zh.dto.basic.UserDTO;
import com.zh.dto.flood.FloodPreventionOwnerDTO;
import com.zh.entity.flood.FloodPreventionOwner;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


/**
 * <p>
 * 防汛责任人表 服务类
 * </p>
 *
 * @author 赵梦霞
 * @since 2019-01-04
 */
public interface FloodPreventionOwnerService extends IService<FloodPreventionOwner> {

    IPage<FloodPreventionOwnerDTO> selectByTypeIdPage(Page<FloodPreventionOwnerDTO> page, Long typeId, String keywords);

    void exportExcel(HttpServletResponse response, Long typeId, String keywords) throws IOException;

    List<UserDTO> selectAllUserByTypeId(Long typeId);

    boolean checkDutyUser(FloodPreventionOwner floodPreventionOwner);
}
