package com.idofast.admin.controller;

import com.idofast.admin.annotation.AuthRole;
import com.idofast.admin.config.context.RequestContext;
import com.idofast.admin.controller.vo.request.NoticeAddVo;
import com.idofast.admin.controller.vo.request.NoticeModifyVo;
import com.idofast.admin.controller.vo.response.NoticeAdminResponseVo;
import com.idofast.admin.controller.vo.response.NoticeUserResponseVo;
import com.idofast.admin.domain.Notice;
import com.idofast.admin.domain.User;
import com.idofast.admin.exception.BusinessErrorEnum;
import com.idofast.admin.service.NoticeService;
import com.idofast.admin.service.UserService;
import com.idofast.common.enums.NoticeStatusEnum;
import com.idofast.common.enums.NoticeTypeEnum;
import com.idofast.common.enums.NoticeVisibilityEnum;
import com.idofast.common.enums.RoleEnum;
import com.idofast.common.response.ServerResponse;
import com.idofast.common.response.error.BusinessException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/notice")
@Api(tags = "公告相关的api")
@CrossOrigin
public class NoticeController
{
    @Autowired
    private NoticeService noticeService;

    @Autowired
    private UserService userService;


    @PostMapping("/add")
    @ApiOperation("添加公告")
    @AuthRole(RoleEnum.ADMIN)
    public ServerResponse<String> addNotice(@Validated NoticeAddVo noticeAddVo)
    {
        Notice notice = NoticeAddVo.convertToNotice(noticeAddVo);
        notice.setPublisherId(RequestContext.getCurrentUser().getId());
        noticeService.addNotice(notice);
        return ServerResponse.success();
    }

    @PostMapping("/modify")
    @ApiOperation("添加公告")
    @AuthRole(RoleEnum.ADMIN)
    public ServerResponse<String> modifyNotice(@Validated NoticeModifyVo noticeModifyVo) throws BusinessException
    {
        Notice notice = NoticeModifyVo.convertToNotice(noticeModifyVo);
        noticeService.modifyNoticeContent(notice);
        return ServerResponse.success();
    }


    @GetMapping("/detail/{id}")
    @ApiOperation("根据id获取文章内容")
    public ServerResponse<NoticeUserResponseVo> getNoticeById(@PathVariable("id") Long id) throws BusinessException
    {
        Notice noticeById = noticeService.getNoticeById(id);
        if(noticeById.getStatus() == NoticeStatusEnum.PUBLISHED)
        {
            //所有人可见，直接返回
            if(noticeById.getVisibility() == NoticeVisibilityEnum.ALL)
            {
                return ServerResponse.success(NoticeUserResponseVo.convertFrom(noticeById));
            }
        }


        String token = RequestContext.getToken();
        //需要登陆，进行校验
        User userByToken = null;
        try
        {
            userByToken = userService.getUserByToken(token);
        } catch (BusinessException e)
        {
            throw new BusinessException(BusinessErrorEnum.NEED_LOGIN);
        }

        if(noticeById.getVisibility() == NoticeVisibilityEnum.USER && noticeById.getStatus() == NoticeStatusEnum.PUBLISHED)
        {
            return ServerResponse.success(NoticeUserResponseVo.convertFrom(noticeById));
        }

        //此处权限只能是管理员权限
        if(userByToken.getRole() != RoleEnum.ADMIN)
        {
            throw new BusinessException(BusinessErrorEnum.OUT_OF_AUTORITY);
        }
        return ServerResponse.success(NoticeUserResponseVo.convertFrom(noticeById));
    }

    @GetMapping("/list/user/notification")
    @ApiOperation("获取所有公告列表")
    public ServerResponse<List<NoticeUserResponseVo>> getNotificationList()
    {
        List<Notice> noticeList = noticeService.getAllNoticeForSimpleUser();
        List<NoticeUserResponseVo>  noticeListVo= noticeList.stream().map(NoticeUserResponseVo::convertFrom)
                .collect(Collectors.toList());
        return ServerResponse.success(noticeListVo);
    }

    @GetMapping("/list/user/instruction")
    @ApiOperation("获取所有教程列表")
    public ServerResponse<List<NoticeUserResponseVo>> getInstructionList()
    {
        List<Notice> noticeList = noticeService.getAllInstructionForSimpleUser();
        List<NoticeUserResponseVo>  noticeListVo= noticeList.stream().map(NoticeUserResponseVo::convertFrom)
                .collect(Collectors.toList());
        return ServerResponse.success(noticeListVo);
    }

    @GetMapping("/list/user/knowledge")
    @ApiOperation("获取所有科普列表")
    public ServerResponse<List<NoticeUserResponseVo>> getKnowledgeList()
    {
        List<Notice> noticeList = noticeService.getAllKnowledgeForSimpleUser();
        List<NoticeUserResponseVo>  noticeListVo= noticeList.stream().map(NoticeUserResponseVo::convertFrom)
                .collect(Collectors.toList());
        return ServerResponse.success(noticeListVo);
    }
    @GetMapping("/list/admin")
    @ApiOperation("管理员获取所有公告内容")
    @ApiImplicitParams(
            @ApiImplicitParam(paramType = "int", name = "noticeType", value = "公告类型；0-教程；1-公告；2-科普", required = true)
    )
    @AuthRole(RoleEnum.ADMIN)
    public ServerResponse<List<NoticeAdminResponseVo>>  getNoticeList(@NotNull Integer noticeType) throws BusinessException
    {
        if(noticeType < 0 || noticeType > 2)
        {
            throw new BusinessException("非法noticeType");
        }

        List<Notice> allNoticeForAdmin = noticeService.getAllNoticeForAdmin(NoticeTypeEnum.ofCode(noticeType));
        List<NoticeAdminResponseVo>  noticeListVo= allNoticeForAdmin.stream().map(NoticeAdminResponseVo::convertFrom)
                .collect(Collectors.toList());
        return ServerResponse.success(noticeListVo);
    }


    @PostMapping("/modify/stickAndOrderValue")
    @ApiOperation("修改公告置顶和orderValue")
    @ApiImplicitParams(
            @ApiImplicitParam(paramType = "int", name = "noticeType", value = "公告类型；0-教程；1-公告；2-科普", required = true)
    )
    @AuthRole(RoleEnum.ADMIN)
    public ServerResponse<String>  getNoticeList(@NotNull Long id, Boolean stick, Long orderValue) throws BusinessException
    {
        noticeService.modifyNoticeStickAndOrderValue(id, stick, orderValue);

        return ServerResponse.success();
    }


    @PostMapping("/modify/down/{id}")
    @ApiOperation("下架公告")
    @ApiImplicitParams(
            @ApiImplicitParam(paramType = "long", name = "id", value = "公告的id", required = true)
    )
    @AuthRole(RoleEnum.ADMIN)
    public ServerResponse<String>  downNotice(@PathVariable Long id) throws BusinessException
    {

        noticeService.setNoticeStatus(id, NoticeStatusEnum.DOWN);

        return ServerResponse.success();
    }

    @PostMapping("/modify/up/{id}")
    @ApiOperation("上架公告")
    @ApiImplicitParams(
            @ApiImplicitParam(paramType = "long", name = "id", value = "公告的id", required = true)
    )
    @AuthRole(RoleEnum.ADMIN)
    public ServerResponse<String>  putUpNotice(@PathVariable Long id) throws BusinessException
    {

        noticeService.setNoticeStatus(id, NoticeStatusEnum.PUBLISHED);
        return ServerResponse.success();
    }


    @PostMapping("/modify/delete/{id}")
    @ApiOperation("下架公告")
    @ApiImplicitParams(
            @ApiImplicitParam(paramType = "long", name = "id", value = "公告的id", required = true)
    )
    @AuthRole(RoleEnum.ADMIN)
    public ServerResponse<String>  deleteNotice(@PathVariable Long id) throws BusinessException
    {

        noticeService.deleteNotice(id);
        return ServerResponse.success();
    }

}
