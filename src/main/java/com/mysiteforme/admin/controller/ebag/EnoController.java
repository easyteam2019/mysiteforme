package com.mysiteforme.admin.controller.ebag;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.mysiteforme.admin.annotation.SysLog;
import com.mysiteforme.admin.base.BaseController;
import com.mysiteforme.admin.entity.Eno;
import com.mysiteforme.admin.service.EnoService;
import com.mysiteforme.admin.util.LayerData;
import com.mysiteforme.admin.util.RestResponse;
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
import java.util.Map;

/**
 * <p>
 * 快递单号前端控制器
 * </p>
 *
 * @author liao
 * @since 2018-01-19
 */
@Controller
@RequestMapping("/admin/ebag/eno")
public class EnoController extends BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(EnoController.class);


    @GetMapping("list")
    @SysLog("跳转快递单号管理列表")
    public String list() {
        return "/admin/ebag/eno/list";
    }

    @RequiresPermissions("ebag:eno:list")
    @PostMapping("list")
    @ResponseBody
    public LayerData<Eno> list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                               @RequestParam(value = "limit", defaultValue = "10") Integer limit,
                               ServletRequest request) {
        Map<String, Object> map = WebUtils.getParametersStartingWith(request, "s_");
        LayerData<Eno> layerData = new LayerData<>();
        EntityWrapper<Eno> wrapper = new EntityWrapper<>();
        wrapper.eq("del_flag",false);
        if (!map.isEmpty()) {
            String eno = (String) map.get("eno");
            if (StringUtils.isNotBlank(eno)) {
                wrapper.like("eno", eno);
            } else {
                map.remove("eno");
            }

            String sprovince = (String) map.get("sprovince");
            if (!StringUtils.isBlank(sprovince)) {
                wrapper.eq("sprovince", sprovince);
            } else {
                map.remove("sprovince");
            }

            String scity = (String) map.get("scity");
            if (!StringUtils.isBlank(scity)) {
                wrapper.eq("scity", scity);
            } else {
                map.remove("scity");
            }

            String scounty = (String) map.get("scounty");
            if (!StringUtils.isBlank(scounty)) {
                wrapper.eq("scounty", scounty);
            } else {
                map.remove("scounty");
            }

            String rprovince = (String) map.get("rprovince");
            if (!StringUtils.isBlank(rprovince) ) {
                wrapper.eq("rprovince", rprovince);
            } else {
                map.remove("rprovince");
            }

            String rcity = (String) map.get("rcity");
            if (!StringUtils.isBlank(rcity) ) {
                wrapper.eq("rcity", rcity);
            } else {
                map.remove("rcity");
            }

            String rcounty = (String) map.get("rcounty");
            if (!StringUtils.isBlank(rcounty)) {
                wrapper.eq("rcounty", rcounty);
            } else {
                map.remove("rcounty");
            }
        }
        wrapper.orderBy("createDate",false);
        Page<Eno> pageData = enoService.selectPage(new Page<Eno>(page, limit), wrapper);
        layerData.setData(pageData.getRecords());
        layerData.setCount(pageData.getTotal());
        return layerData;
    }

    @GetMapping("add")
    public String add() {
        return "/admin/ebag/eno/add";
    }


    @RequiresPermissions("ebag:eno:add")
    @PostMapping("add")
    @SysLog("保存快递单号数据")
    @ResponseBody
    public RestResponse add(Eno eno) {

        enoService.saveOrUpdateEno(eno);
        return RestResponse.success();
    }


    @GetMapping("edit")
    public String edit(Long id, Model model) {

        /**
         * 编辑Eno对象之前，查询出来放到页面中显示
         */
        Eno eno = enoService.selectById(id);
        model.addAttribute("eno", eno);
        return "/admin/ebag/eno/edit";
    }

    @RequiresPermissions("ebag:eno:edit")
    @PostMapping("edit")
    @ResponseBody
    @SysLog("保存编辑快递编号数据")
    public RestResponse edit(Eno eno) {
        if (null == eno.getId()) {
            return RestResponse.failure("快递单号不能为空");
        }

        enoService.saveOrUpdateEno(eno);
        return RestResponse.success();
    }



    @GetMapping("printEno")
    public String printEno(Long id, Model model) {

        /**
         * 编辑Eno对象之前，查询出来放到页面中显示
         */
        Eno eno = enoService.selectById(id);
        model.addAttribute("eno", eno);
        return "/admin/ebag/eno/printEno";
    }


    @RequiresPermissions("ebag:eno:printEno")
    @PostMapping("printEno")
    @ResponseBody
    @SysLog("打印出单数据")
    public RestResponse printEno(Eno eno) {
        if (null == eno.getId()) {
            return RestResponse.failure("快递单号不能为空");
        }

        enoService.saveOrUpdateEno(eno);
        return RestResponse.success();
    }

    @GetMapping("printEnoInfo")
    public String printEnoInfo(Long id, Model model) {

        /**
         * 编辑Eno对象之前，查询出来放到页面中显示
         */
        Eno eno = enoService.selectById(id);
        model.addAttribute("eno", eno);
        return "/admin/ebag/eno/printEnoInfo";
    }



    @RequiresPermissions("ebag:eno:delete")
    @PostMapping("delete")
    @ResponseBody
    @SysLog("删除快递单号数据")
    public RestResponse delete(@RequestParam(value = "id", required = false) Long id) {
        if (null == id || 0 == id) {
            return RestResponse.failure("ID不能为空");
        }
        Eno eno = enoService.selectById(id);
        eno.setDelFlag(true);
        enoService.saveOrUpdateEno(eno);
        return RestResponse.success();
    }

}
