<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>快递单号管理--${site.name}</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <meta name="description" content="${site.description}"/>
    <meta name="keywords" content="${site.keywords}"/>
    <meta name="author" content="${site.author}"/>
    <link rel="icon" href="${site.logo}">
    <link rel="stylesheet" href="${base}/static/layui/css/layui.css" media="all" />
    <link rel="stylesheet" href="//at.alicdn.com/t/font_tnyc012u2rlwstt9.css" media="all" />
    <link rel="stylesheet" href="${base}/static/css/user.css" media="all" />
</head>
<body class="childrenBody">
<fieldset class="layui-elem-field">
  <legend>单号检索</legend>
  <div class="layui-field-box">
    <form class="layui-form" id="searchForm">
        <div class="layui-inline" style="margin-left: 10px">


            <div class="layui-form-item">
                <label class="layui-form-label">寄件地址</label>
                <div class="layui-input-inline" style="width: 12%">
                    <select name="s_sprovince" id="s_sprovince" lay-filter="s_sprovince">
                        <option value="">请选择省</option>
                    </select>
                </div>
                <div class="layui-input-inline" style="width: 12%">
                    <select name="s_scity" id="s_scity" lay-filter="s_scity" style="width: 15%">
                        <option value="">请选择市</option>
                    </select>
                </div>
                <div class="layui-input-inline" style="width: 12%">
                    <select name="s_scounty" id="s_scounty" lay-filter="s_scounty" >
                        <option value="">请选择县/区</option>
                    </select>
                </div>

                <label class="layui-form-label">收件地址</label>
                <div class="layui-input-inline" style="width: 12%">
                    <select name="s_rprovince" id="s_rprovince" lay-filter="s_rprovince" style="width: 15%">
                        <option value="">请选择省</option>
                    </select>
                </div>
                <div class="layui-input-inline" style="width: 12%">
                    <select name="s_rcity" id="s_rcity" lay-filter="s_rcity">
                        <option value="">请选择市</option>
                    </select>
                </div>
                <div class="layui-input-inline" style="width: 12%">
                    <select name="s_rcounty" id="s_rcounty" lay-filter="s_rcounty">
                        <option value="">请选择县/区</option>
                    </select>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">快递单号</label>
                <div class="layui-input-inline" style="width: 37.5%">
                    <input  type="text"  class="layui-input search_input" name="s_eno" value="" placeholder="请输入快递单号">
                </div>
                <div class="layui-inline"  style="margin-left: 110px">
                    <a class="layui-btn" lay-submit="" lay-filter="searchForm">查询</a>
                </div>
                <div class="layui-inline" >
                    <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                </div>
                <div class="layui-inline">
                    <a class="layui-btn layui-btn-normal" data-type="addEno">添加快递单号</a>
                </div>
            </div>

        </div>



    </form>
  </div>
</fieldset>
<div class="layui-form users_list">
    <table class="layui-table" id="test" lay-filter="demo"></table>
    <script type="text/html" id="sendstatus">
        {{#  if(d.sendstatus == 1){ }}
        <span class="layui-btn layui-btn-sm layui-btn-primary" lay-event="printEnoInfo">已使用</span>
        {{#  } else { }}
        <span class="layui-btn layui-btn-sm" lay-event="printEno">出单</span>
        {{#  } }}
    </script>

    <script type="text/html" id="barDemo">
        {{#  if(d.sendstatus == 1){ }}
        <a class="layui-btn layui-btn-xs layui-btn-disabled" lay-event="edit">修改</a>
        {{#  } else { }}
        <a class="layui-btn layui-btn-xs" lay-event="edit">修改</a>
        {{#  } }}
        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
    </script>
</div>
<div id="page"></div>
<script type="text/javascript" src="${base}/static/layui/layui.js"></script>
<script type="text/javascript" src="${base}/static/js/tools.js"></script>
<script>
    layui.use(['layer','form','table','laydate'], function() {
        var layer = layui.layer,
                $ = layui.jquery,
                form = layui.form,
                laydate = layui.laydate,
                table = layui.table;


        //监听工具条
        table.on('tool(demo)', function(obj){
            var data = obj.data;
            if(obj.event === 'edit'){
                var editIndex = layer.open({
                    title : "编辑快递单号",
                    type : 2,
                    content : "${base}/admin/ebag/eno/edit?id="+data.id,
                    success : function(layero, index){
                        setTimeout(function(){
                            layer.tips('点击此处返回快递单号列表', '.layui-layer-setwin .layui-layer-close', {
                                tips: 3
                            });
                        },500);
                    }
                });
                //改变窗口大小时，重置弹窗的高度，防止超出可视区域（如F12调出debug的操作）
                $(window).resize(function(){
                    layer.full(editIndex);
                });
                layer.full(editIndex);
            }
            if(obj.event === "del"){
                layer.confirm("你确定要删除该快递单号么？",{btn:['是的,我确定','我再想想']},
                        function(){
                            $.post("${base}/admin/ebag/eno/delete",{"id":data.id},function (res){
                                if(res.success){
                                    layer.msg("删除成功",{time: 1000},function(){
                                        location.reload();
                                    });
                                }else{
                                    layer.msg(res.message);
                                }
                            });
                        }
                )
            }
            //打印单号
            if(obj.event === "printEno"){
                var editIndex = layer.open({
                    title : "寄出单号",
                    type : 2,
                    area:["80%","95%"],
                    content : "${base}/admin/ebag/eno/printEno?id="+data.id,
                    success : function(layero, index){
                        setTimeout(function(){
                            layer.tips('点击关闭', '.layui-layer-setwin .layui-layer-close', {
                                tips: 3
                            });
                        },800);
                    }
                });
            }

            //显示打印详情
            if(obj.event === "printEnoInfo"){
                var editIndex = layer.open({
                    title : "单号详情",
                    type : 2,
                    area:["80%","95%"],
                    content : "${base}/admin/ebag/eno/printEnoInfo?id="+data.id,
                    success : function(layero, index){
                        setTimeout(function(){
                            layer.tips('点击关闭', '.layui-layer-setwin .layui-layer-close', {
                                tips: 3
                            });
                        },800);
                    }
                });
            }
        });

        /** FORM表单定义*/
        var t = {
            elem: '#test',
            url:'${base}/admin/ebag/eno/list',
            method:'post',
            page: { //支持传入 laypage 组件的所有参数（某些参数除外，如：jump/elem） - 详见文档
                layout: ['limit', 'count', 'prev', 'page', 'next', 'skip'], //自定义分页布局
                //,curr: 5 //设定初始在第 5 页
                groups: 2, //只显示 1 个连续页码
                first: "首页", //显示首页
                last: "尾页", //显示尾页
                limits:[3,10, 20, 30]
            },
            cellMinWidth: 80, //全局定义常规单元格的最小宽度，layui 2.2.1 新增
            cols: [[
                {type:  'checkbox'},
                {field: 'eno', title: '单号',width:'15%', align: 'left'},
                {field: 'sprovince', title: '寄件地址',width:'25%',align: 'left',templet:"<div>{{dicAdd(d.sprovince,d.scity,d.scounty)}}</div>"},
                {field: 'scity', title: 'ID2'},
                {field: 'scounty', title: 'ID3'},
                {field: 'rprovince', title: '收件地址地址',width:'25%',align: 'left',templet:"<div>{{dicAdd(d.rprovince,d.rcity,d.rcounty)}}</div>"},
                {field: 'rcity', title: 'ID4'},
                {field: 'rcounty', title: 'ID5'},
                //{field: 'delFlag',    title: '快递单号状态',width:'5%',templet:'#userStatus'},
                {field: 'createDate',  title: '创建时间',width:'12%',templet:'<div>{{ layui.laytpl.toDateString(d.createDate) }}</div>',unresize: true}, //单元格内容水平居中
                {field: 'sendstatus', title: '状态',width:'10%',templet:'#sendstatus'},//sendstatus
                {fixed: 'right', title:'操作',  width: '10%', align: 'center',toolbar: '#barDemo'}
            ]],
            done: function () {
                $("[data-field='scity']").css('display','none');
                $("[data-field='scounty']").css('display','none');
                $("[data-field='rcity']").css('display','none');
                $("[data-field='rcounty']").css('display','none');
            }

    };
        table.render(t);

        var active={
            addEno : function(){
                var addIndex = layer.open({
                    title : "添加快递单号",
                    type : 2,
                    content : "${base}/admin/ebag/eno/add",
                    success : function(layero, addIndex){
                        setTimeout(function(){
                            layer.tips('点击此处返回快递单号列表', '.layui-layer-setwin .layui-layer-close', {
                                tips: 3
                            });
                        },500);
                    }
                });
                //改变窗口大小时，重置弹窗的高度，防止超出可视区域（如F12调出debug的操作）
                $(window).resize(function(){
                    layer.full(addIndex);
                });
                layer.full(addIndex);
            }
        };

        $('.layui-inline .layui-btn-normal').on('click', function(){
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });

        form.on("submit(searchForm)",function(data){

            alert(JSON.stringify(data.field));
            t.where = data.field;
            table.reload('test', t);
            return false;
        });

        var raddress = {
            s1: 's_rprovince',
            s2: 's_rcity',
            s3: 's_rcounty',
            v1: null,
            v2: null,
            v3: null
        };
        treeSelect(raddress);

        var saddress = {
            s1: 's_sprovince',
            s2: 's_scity',
            s3: 's_scounty',
            v1: null,
            v2: null,
            v3: null
        };
        treeSelect(saddress);

    });

    function dicAdd(sprovince,scity,scounty) {
        var adds = dicAddress(sprovince, scity, scounty);
        //直辖市省略重复显示
        if(adds.a1 == adds.a2){
            adds.a2 = "";
        }
        return adds.a1+''+adds.a2+''+adds.a3;
    }
</script>
<script type="text/javascript" src="${base}/static/layui/assets/data.js"></script>
<script type="text/javascript" src="${base}/static/layui/assets/province.js"></script>
</body>
</html>
