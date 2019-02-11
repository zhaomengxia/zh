package com.zh.service.sys;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zh.dto.sys.site.SiteDTO;
import com.zh.entity.sys.Site;

import java.util.List;

/**
 * <p>
 * 自动监测站 服务类
 * </p>
 *
 * @author 赵梦霞
 * @since 2018-12-22
 */
public interface SiteService extends IService<Site> {

     Boolean checkName(Site site);

     Boolean checkCode(Site site);

     boolean saveOrUpdateOthers(SiteDTO siteDTO);

     boolean selectAllBySiteId(Long siteId);

     List<Site> getSiteList();

     IPage<Site> selectBySiteName(Page<Site> page, String keywords);

     IPage<SiteDTO> selectByKeywords(Page<SiteDTO> page, String keywords);

     List<SiteDTO> selectBySiteId(Long siteId);

     Boolean deleteByDeviceIds(List<Long> deviceIds);
}
