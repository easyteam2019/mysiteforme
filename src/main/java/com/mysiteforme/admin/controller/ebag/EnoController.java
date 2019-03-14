package com.mysiteforme.admin.controller.ebag;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.google.common.collect.Maps;
import com.mysiteforme.admin.annotation.SysLog;
import com.mysiteforme.admin.base.BaseController;
import com.mysiteforme.admin.entity.BlogArticle;
import com.mysiteforme.admin.entity.BlogChannel;
import com.mysiteforme.admin.entity.Eno;
import com.mysiteforme.admin.entity.VO.ZtreeVO;
import com.mysiteforme.admin.service.EnoService;
import com.mysiteforme.admin.util.LayerData;
import com.mysiteforme.admin.util.RestResponse;
import com.xiaoleilu.hutool.date.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.WebUtils;

import javax.servlet.ServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 博客内容  前端控制器
 * </p>
 *
 * @author wangl
 * @since 2018-01-19
 */
@Controller
@RequestMapping("/admin/ebag/eno")
public class EnoController extends BaseController{
    private static final Logger LOGGER = LoggerFactory.getLogger(EnoController.class);




    @Autowired
    protected EnoService enoService;

    @GetMapping("list")
    @SysLog("跳转快递单号管理列表")
    public String list(){
        return "/admin/ebag/eno/list";
    }

    @RequiresPermissions("ebag:eno:list")
    @PostMapping("list")
    @ResponseBody
    public LayerData<BlogArticle> list(@RequestParam(value = "page",defaultValue = "1")Integer page,
                                      @RequestParam(value = "limit",defaultValue = "10")Integer limit,
                                      ServletRequest request){
        Map<String,Object> map = WebUtils.getParametersStartingWith(request, "s_");
        LayerData<BlogArticle> layerData = new LayerData<>();
        EntityWrapper<BlogArticle> wrapper = new EntityWrapper<>();
        wrapper.eq("del_flag",false);
        if(!map.isEmpty()){
            String title = (String) map.get("title");
            if(StringUtils.isBlank(title)) {
                map.remove("title");
            }
            String category = (String) map.get("category");
            if(StringUtils.isBlank(category)) {
                map.remove("category");
            }
            String beginPublistTime = (String) map.get("beginPublistTime");
            String endPublistTime = (String) map.get("endPublistTime");
            if(StringUtils.isNotBlank(beginPublistTime)) {
                Date begin = DateUtil.parse(beginPublistTime);
                map.put("publist_time",begin);
            }else{
                map.remove("beginPublistTime");
            }
            if(StringUtils.isNotBlank(endPublistTime)) {
                Date end = DateUtil.parse(endPublistTime);
                map.put("publist_time",end);
            }else{
                map.remove("endPublistTime");
            }
            String content = (String) map.get("content");
            if(StringUtils.isBlank(content)) {
                map.remove("content");
            }
            String channelId = (String) map.get("channelId");
            if(StringUtils.isBlank(channelId)){
                map.remove("channelId");
            }

        }
        Page<BlogArticle> pageData = blogArticleService.selectDetailArticle(map,new Page<>(page,limit));
        layerData.setData(pageData.getRecords());
        layerData.setCount(pageData.getTotal());
        return layerData;
    }

    @GetMapping("add")
    public String add(){
        return "/admin/ebag/eno/add";
    }


    @RequiresPermissions("ebag:eno:add")
    @PostMapping("add")
    @SysLog("保存快递单号数据")
    @ResponseBody
    public RestResponse add(Eno eno){

        enoService.saveEno(eno);
        return RestResponse.success();
    }

    @GetMapping("edit")
    public String edit(Long id,Model model){

        return "/admin/ebag/eno/edit";
    }

    @RequiresPermissions("ebag:eno:edit")
    @PostMapping("edit")
    @ResponseBody
    @SysLog("保存编辑博客内容数据")
    public RestResponse edit(@RequestBody BlogArticle blogArticle){
        if(null == blogArticle.getId() || 0 == blogArticle.getId()){
            return RestResponse.failure("ID不能为空");
        }
        if(StringUtils.isBlank(blogArticle.getTitle())){
            return RestResponse.failure("标题不能为空");
        }
        if(StringUtils.isBlank(blogArticle.getContent())){
            return RestResponse.failure("内容不能为空");
        }
        if(blogArticle.getSort() == null){
            return RestResponse.failure("排序值不能为空");
        }
        blogArticleService.saveOrUpdateArticle(blogArticle);
        blogArticleService.removeArticleTags(blogArticle.getId());
        if(blogArticle.getBlogTags() != null && blogArticle.getBlogTags().size()>0){
            Map<String,Object> map = Maps.newHashMap();
            map.put("articleId",blogArticle.getId());
            map.put("tags",blogArticle.getBlogTags());
            blogArticleService.saveArticleTags(map);
        }
        return RestResponse.success();
    }

    @RequiresPermissions("ebag:eno:delete")
    @PostMapping("delete")
    @ResponseBody
    @SysLog("删除博客内容数据")
    public RestResponse delete(@RequestParam(value = "id",required = false)Long id){
        if(null == id || 0 == id){
            return RestResponse.failure("ID不能为空");
        }
        BlogArticle blogArticle = blogArticleService.selectById(id);
        blogArticle.setDelFlag(true);
        blogArticleService.saveOrUpdateArticle(blogArticle);
        return RestResponse.success();
    }

    @GetMapping("createIndex")
    @ResponseBody
    public RestResponse createIndex() {
        blogArticleService.createArticlIndex();
        return RestResponse.success();
    }
}