package cn.edu.nwafu.admin.controller;

import cn.edu.nwafu.admin.annotation.AuthRole;
import cn.edu.nwafu.admin.config.context.RequestContext;
import cn.edu.nwafu.admin.controller.vo.request.ProblemAddVo;
import cn.edu.nwafu.admin.controller.vo.request.NoticeModifyVo;
import cn.edu.nwafu.admin.controller.vo.response.NoticeAdminResponseVo;
import cn.edu.nwafu.admin.controller.vo.response.NoticeUserResponseVo;
import cn.edu.nwafu.admin.domain.Problem;
import cn.edu.nwafu.admin.domain.User;
import cn.edu.nwafu.admin.exception.BusinessErrorEnum;
import cn.edu.nwafu.admin.service.ProblemService;
import cn.edu.nwafu.admin.service.UserService;
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
public class ProblemController
{
    @Autowired
    private ProblemService problemService;

    @Autowired
    private UserService userService;


    @PostMapping("/add")
    @ApiOperation("添加公告")
    @AuthRole(RoleEnum.ADMIN)
    public ServerResponse<String> addProblem(@Validated ProblemAddVo problemAddVo)
    {
        Problem problem = ProblemAddVo.convertToNotice(problemAddVo);
        problem.setPublisherId(RequestContext.getCurrentUser().getId());
        problemService.addNotice(problem);
        return ServerResponse.success();
    }

    @PostMapping("/modify")
    @ApiOperation("添加公告")
    @AuthRole(RoleEnum.ADMIN)
    public ServerResponse<String> modifyNotice(@Validated NoticeModifyVo noticeModifyVo) throws BusinessException
    {
        Problem problem = NoticeModifyVo.convertToNotice(noticeModifyVo);
        problemService.modifyNoticeContent(problem);
        return ServerResponse.success();
    }


    @GetMapping("/detail/{id}")
    @ApiOperation("根据id获取文章内容")
    public ServerResponse<NoticeUserResponseVo> getNoticeById(@PathVariable("id") Long id) throws BusinessException
    {
        Problem problemById = problemService.getNoticeById(id);
        if(problemById.getStatus() == NoticeStatusEnum.PUBLISHED)
        {
            //所有人可见，直接返回
            if(problemById.getVisibility() == NoticeVisibilityEnum.ALL)
            {
                return ServerResponse.success(NoticeUserResponseVo.convertFrom(problemById));
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

        if(problemById.getVisibility() == NoticeVisibilityEnum.USER && problemById.getStatus() == NoticeStatusEnum.PUBLISHED)
        {
            return ServerResponse.success(NoticeUserResponseVo.convertFrom(problemById));
        }

        //此处权限只能是管理员权限
        if(userByToken.getRole() != RoleEnum.ADMIN)
        {
            throw new BusinessException(BusinessErrorEnum.OUT_OF_AUTORITY);
        }
        return ServerResponse.success(NoticeUserResponseVo.convertFrom(problemById));
    }

    @GetMapping("/list/user/notification")
    @ApiOperation("获取所有公告列表")
    public ServerResponse<List<NoticeUserResponseVo>> getNotificationList()
    {
        List<Problem> problemList = problemService.getAllNoticeForSimpleUser();
        List<NoticeUserResponseVo>  noticeListVo= problemList.stream().map(NoticeUserResponseVo::convertFrom)
                .collect(Collectors.toList());
        return ServerResponse.success(noticeListVo);
    }

    @GetMapping("/list/user/instruction")
    @ApiOperation("获取所有教程列表")
    public ServerResponse<List<NoticeUserResponseVo>> getInstructionList()
    {
        List<Problem> problemList = problemService.getAllInstructionForSimpleUser();
        List<NoticeUserResponseVo>  noticeListVo= problemList.stream().map(NoticeUserResponseVo::convertFrom)
                .collect(Collectors.toList());
        return ServerResponse.success(noticeListVo);
    }

    @GetMapping("/list/user/knowledge")
    @ApiOperation("获取所有科普列表")
    public ServerResponse<List<NoticeUserResponseVo>> getKnowledgeList()
    {
        List<Problem> problemList = problemService.getAllKnowledgeForSimpleUser();
        List<NoticeUserResponseVo>  noticeListVo= problemList.stream().map(NoticeUserResponseVo::convertFrom)
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

        List<Problem> allProblemForAdmin = problemService.getAllNoticeForAdmin(NoticeTypeEnum.ofCode(noticeType));
        List<NoticeAdminResponseVo>  noticeListVo= allProblemForAdmin.stream().map(NoticeAdminResponseVo::convertFrom)
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
        problemService.modifyNoticeStickAndOrderValue(id, stick, orderValue);

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

        problemService.setNoticeStatus(id, NoticeStatusEnum.DOWN);

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

        problemService.setNoticeStatus(id, NoticeStatusEnum.PUBLISHED);
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

        problemService.deleteNotice(id);
        return ServerResponse.success();
    }

}
