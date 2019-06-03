<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>人力资源管理系统</title>
    <script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>
    <!-- Vue -->
    <#--<link rel="stylesheet" href="//unpkg.com/iview/dist/styles/iview.css">-->
    <#--<script src="//unpkg.com/iview/dist/iview.min.js"></script>-->
    <!--elementUI-->
    <script src="//vuejs.org/js/vue.min.js"></script>
    <link rel="stylesheet" href="https://unpkg.com/element-ui/lib/theme-chalk/index.css">
    <script src="https://unpkg.com/element-ui/lib/index.js"></script>
    <link rel="stylesheet" href="/css/index.css">
</head>
<body>
<div id="app">
    <div id="pics">
        <el-carousel :height="screenH+'px'">
            <el-carousel-item><img :height="screenH+'px'" :width="screenW+'px'" src="/image/index1.jpg"/> </el-carousel-item>
            <el-carousel-item><img :height="screenH+'px'" :width="screenW+'px'" src="/image/index2.jpg"/> </el-carousel-item>
            <el-carousel-item><img :height="screenH+'px'" :width="screenW+'px'" src="/image/index3.jpg"/> </el-carousel-item>
            <el-carousel-item><img :height="screenH+'px'" :width="screenW+'px'" src="/image/index4.jpg"/> </el-carousel-item>
        </el-carousel>
    </div>
    <el-form id="login" ref="form" :model="form" label-width="80px" action="/login" method="post">
        <div class="formtitle">人力资源管理系统</div>
        <hr/>
        <el-form-item class="formitem" label="用户名">
            <el-input v-model="form.name" placeholder="请输入用户名" name="username" clearable></el-input>
        </el-form-item>
        <el-form-item class="formitem" label="密码">
            <el-input v-model="form.pswd" placeholder="请输入密码" name="password" show-password></el-input>
        </el-form-item>
        <el-form-item class="formitem">
            <el-button type="primary" native-type="submit">登录</el-button>
            <#if loginError??>
            <script>alert('用户名或密码错误，请稍后重试！');</script>
                <#--<el-popover-->
                    <#--placement="top"-->
                    <#--width="160"-->
                    <#--v-model="visible">-->
                    <#--<p>用户名或密码错误，请稍后重试！</p>-->
                    <#--<div style="text-align: right; margin: 0">-->
                        <#--<el-button size="mini" type="text" @click="visible = false">取消</el-button>-->
                        <#--<el-button type="primary" size="mini" @click="visible = false">确定</el-button>-->
                    <#--</div>-->
                    <#--<el-button type="primary" native-type="submit">登录</el-button>-->
                <#--</el-popover>-->
            </#if>
        </el-form-item>
    </el-form>

    <!--<el-row>-->
    <!--<el-col :span="10" offset="14"><el-card id="login">-->
    <!--<div slot="header" class="clearfix">-->
    <!--<span>人力资源管理系统</span>-->
    <!--</div>-->
    <!--<div class="login-content">-->
    <!--<el-row class="text-item">-->
    <!--<el-col :span="3" offset="2">用户名</el-col>-->
    <!--<el-col :span="17" offset="1"><el-input style="float: right" placeholder="请输入用户名" v-model="inputName" clearable></el-input></el-col>-->
    <!--</el-row>-->
    <!--<el-row class="text-item" glutter="30">-->
    <!--<el-col :span="3" offset="2">密码</el-col>-->
    <!--<el-col :span="17" offset="1"><el-input placeholder="请输入密码" v-model="inputPswd" show-password></el-input></el-col>-->
    <!--</el-row>-->
    <!--</div>-->
    <!--<hr/>-->
    <!--<el-row>-->
    <!--<el-col :span="4" offset="9"><el-button @click="login"  type="primary" plain>登录</el-button></el-col>-->
    <!--</el-row>-->
    <!--</el-card></el-col>-->
    <!--</el-row>-->
</div>
</body>
<script>
    var vue = new Vue({
        mounted() {
            // this.setSize();
            window.onresize = function () { // 定义窗口大小变更通知事件
                this.screenW = document.documentElement.clientWidth;
                this.screenH = document.documentElement.clientHeight;
            }
            console.log(this.screenW, this.screenH);
        },

        watch: {
            screenW (val) {
                this.screenW = val;
                console.log(this.screenW);
            },
            screenH (val) {
                this.screenH = val;
            },
        },
        el:'#app',
        data:{
            form:{
                name:'',
                pswd:'',
            },
            screenW:document.documentElement.clientWidth,
            screenH:document.documentElement.clientHeight
        },
        methods:{
            // login() {
            //     console.log(this.form);
            //
            //     $.ajax({
            //         url:'/login',
            //         common:{
            //             form:this.form,
            //         },
            //         method:'post',
            //         success(res){
            //             console.log(res);
            //             if(res.code=='200') {
            //                 alert('登录成功');
            //                 window.location.href='../static/html/pointRecord.html';
            //             }
            //         },
            //         error(res){
            //             console.log(res);
            //             alert('登录失败');
            //         }
            //     })
            // },
        },
    })
</script>
</html>