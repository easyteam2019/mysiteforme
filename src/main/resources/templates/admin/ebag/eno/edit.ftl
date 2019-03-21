<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>快递单号编辑--${site.name}</title>
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
    <style type="text/css">
        .layui-form-item .layui-inline{ width:33.333%; float:left; margin-right:0; }
        @media(max-width:1240px){
            .layui-form-item .layui-inline{ width:100%; float:none; }
        }
        .layui-form-item .role-box {
            position: relative;
        }
        .layui-form-item .role-box .jq-role-inline {
            height: 100%;
            overflow: auto;
        }

    </style>
</head>
<body class="childrenBody">
<form class="layui-form" style="width:80%;">
    <input value="${eno.id}" name="id" type="hidden">
    <div class="layui-form-item">
        <label class="layui-form-label">快递单号</label>
        <div class="layui-input-block">
            <input  type="text"  class="layui-input" name="eno" value="${eno.eno}" placeholder="请输入快递单号">
        </div>
    </div>

      <div class="layui-form-item">
        <div class="layui-row">
            <div class="layui-col-md12">
                <!-- 寄件地址国家默认中国 -->
                <input value="CHN" name="snation" type="hidden">
                <label class="layui-form-label">寄件地址</label>
                <div class="layui-input-inline">
                    <select name="sprovince" id="sprovince" lay-filter="sprovince">
                        <option value="">请选择省</option>
                    </select>
                </div>
                <div class="layui-input-inline">
                    <select name="scity" id="scity" lay-filter="scity">
                        <option value="">请选择市</option>
                    </select>
                </div>
                <div class="layui-input-inline">
                    <select name="scounty" id="scounty"  lay-filter="scounty">
                        <option value="">请选择县/区</option>
                    </select>
                </div>
            </div>
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-row">
            <div class="layui-col-md12">
                <div class="layui-form-item">
                    <label class="layui-form-label"></label>
                    <div class="layui-input-block">
                        <input  type="text"  class="layui-input"  value="${eno.saddress1}" name="saddress1"  placeholder="请填写详细寄件地址">
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-row">
            <div class="layui-col-md12">
                <!-- 收件地址国家默认中国 -->
                <input value="CHN" name="rnation" type="hidden">
                <label class="layui-form-label">收件地址</label>
                <div class="layui-input-inline">
                    <select name="rprovince" id="rprovince" lay-filter="rprovince">
                        <option value="">请选择省</option>
                    </select>
                </div>
                <div class="layui-input-inline">
                    <select name="rcity" id="rcity" lay-filter="rcity">
                        <option value="">请选择市</option>
                    </select>
                </div>
                <div class="layui-input-inline">
                    <select name="rcounty" id="rcounty" lay-filter="rcounty">
                        <option value="">请选择县/区</option>
                    </select>
                </div>
            </div>
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-row">
            <div class="layui-col-md12">
                <div class="layui-form-item">
                    <label class="layui-form-label"></label>
                    <div class="layui-input-block">
                        <input  type="text"  class="layui-input" value="${eno.saddress1}" name="raddress1"  placeholder="请填写详细收件地址">
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit="" lay-filter="addEno">更新</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>
<script type="text/javascript" src="${base}/static/layui/layui.js"></script>
<script>
    layui.use(['form','jquery','layer'],function(){
        var form      = layui.form,
                $     = layui.jquery,
                layer = layui.layer;


        form.on("submit(addEno)",function(data){
            var loadIndex = layer.load(2, {
                shade: [0.3, '#333']
            });
            //给角色赋值
            $.post("${base}/admin/ebag/eno/edit",data.field,function(res){
                layer.close(loadIndex);
                if(res.success){
                    parent.layer.msg("快递单号编辑成功！",{time:1000},function(){
                        parent.layer.close(parent.editIndex);
                        //刷新父页面
                        parent.location.reload();
                    });
                }else{
                    layer.msg(res.message);
                }
            });
            return false;
        });



        var saddress = {
            s1: 'sprovince',
            s2: 'scity',
            s3: 'scounty',
            v1: ${eno.sprovince},
            v2: ${eno.scity},
            v3: ${eno.scounty}
        };
        treeSelect(saddress);

        var raddress = {
            s1: 'rprovince',
            s2: 'rcity',
            s3: 'rcounty',
            v1: ${eno.rprovince},
            v2: ${eno.rcity},
            v3: ${eno.rcounty}
        };
        treeSelect(raddress);

    });
</script>
<script type="text/javascript" src="${base}/static/layui/assets/data.js"></script>
<script type="text/javascript" src="${base}/static/layui/assets/province.js"></script>
</body>
</html>
