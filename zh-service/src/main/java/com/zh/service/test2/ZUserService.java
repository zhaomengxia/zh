package com.zh.service.test2;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zh.api.Result;
import com.zh.dto.user.ChangePasswordDTO;
import com.zh.dto.user.SysUserInertOrUpdateDTO;
import com.zh.dto.user.SysUserQueryDTO;
import com.zh.dto.user.SysUserShowDTO;
import com.zh.entity.test2.ZUser;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author hahaha
 * @since 2019-02-21
 */
public interface ZUserService extends IService<ZUser> {
    List<ZUser> checkUserName(String name);

    boolean checkUserExist(SysUserInertOrUpdateDTO userInertOrUpdateDTO);

    Result<?> saveOrUpdateUser(SysUserInertOrUpdateDTO userInertOrUpdateDTO, MultipartFile multipartFile, HttpServletRequest request) throws IOException;

    String exportOne(OutputStream outputStream);

    IPage<SysUserShowDTO> queryPage(Page<SysUserShowDTO> page, SysUserQueryDTO queryDTO);

    SysUserShowDTO queryUserInfo(Long id);

    boolean changePassword(ChangePasswordDTO changePasswordDTO);

    boolean resetPassword(Long id);

    void exportUser(HttpServletResponse response)throws IOException;
}
