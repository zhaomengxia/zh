package com.zh.mapper.test2;

import com.zh.entity.test2.ZUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author  hahaha 
 * @since 2019-02-21
 */
public interface ZUserMapper extends BaseMapper<ZUser> {

    ZUser findByUserName(@Param("username") String username);

    ZUser findByMobile(@Param("mobile") String mobile);
}
