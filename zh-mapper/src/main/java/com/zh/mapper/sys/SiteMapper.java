package com.zh.mapper.sys;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zh.dto.sys.site.SiteDTO;
import com.zh.entity.sys.Site;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 自动监测站 Mapper 接口
 * </p>
 *
 * @author 赵梦霞
 * @since 2018-12-22
 */
public interface SiteMapper extends BaseMapper<Site> {
    IPage<Site> selectBySiteName(IPage<Site> iPage, @Param("keywords") String keywords);

    /**
     * 根据 站点id 获得站点信息
     * @param siteId
     * @return
     */
    List<SiteDTO> selectBySiteId(@Param("siteId") Long siteId);

    /**
     * 根据设备id
     * @param deviceIds
     * @return
     */
    Boolean deleteByDeviceIds(@Param("deviceId") List<Long> deviceIds);

    /**
     * 获取监测点列表
     * @param iPage
     * @param keywords
     * @return
     */
    IPage<SiteDTO> selectByKeywords(Page<SiteDTO> iPage, @Param("keywords")String keywords);
}
