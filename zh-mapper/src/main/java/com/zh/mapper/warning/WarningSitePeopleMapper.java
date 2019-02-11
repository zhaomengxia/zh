package com.zh.mapper.warning;

import com.zh.dto.basic.KeyValueDTO;
import com.zh.entity.warning.WarningSitePeople;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 赵梦霞
 * @since 2019-01-11
 */
public interface WarningSitePeopleMapper extends BaseMapper<WarningSitePeople> {

    List<WarningSitePeople> getSitePeople(@Param("siteId")Long siteId);

    /**
     * 根据防汛指挥部id查询.
     * @param ids
     * @return
     */
    List<KeyValueDTO> getHeadQuarters(@Param("ids")String ids);

    /**
     * 根据防汛责任人类型查询.
     * @param ids
     * @return
     */
    List<KeyValueDTO> getChargeMan(@Param("ids")String ids);
}
