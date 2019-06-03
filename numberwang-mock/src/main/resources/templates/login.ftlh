<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdn.bootcss.com/vue/2.6.9/vue.min.js"></script>
    <script src="//vuejs.org/js/vue.min.js"></script>
    <link href="https://cdn.bootcss.com/element-ui/2.6.1/theme-chalk/index.css" rel="stylesheet">
    <script src="https://cdn.bootcss.com/element-ui/2.6.1/index.js"></script>

    <!-- 引入样式 -->
    <link rel="stylesheet" href="https://unpkg.com/element-ui/lib/theme-chalk/index.css">
    <!-- 引入组件库 -->
    <script src="https://unpkg.com/element-ui/lib/index.js"></script>

    <link rel="stylesheet" href="/loginStylesheet.css">
</head>
<body>
<div id="app">
    <el-tabs id="formContainer" v-model="activeName" @tab-click="changeTab">
        <el-tab-pane label="登录" name="login">
            <el-form  ref="form" :model="form" action="/login" method="post">
                <el-form-item class="inputItem">
                    <el-input v-model="form.name" placeholder="请输入用户名" name="username" clearable></el-input>
                </el-form-item>
                <el-form-item class="inputItem">
                    <el-input v-model="form.pswd" placeholder="请输入密码" name="password" show-password></el-input>
                </el-form-item>
                <el-form-item class="inputItem">
                    <el-button type="primary" native-type="submit">登录</el-button>
            <#if loginError??>
            <script>alert('用户名或密码错误，请稍后重试！');</script>
            </#if>
                </el-form-item>
            </el-form>
        </el-tab-pane>
        <el-tab-pane label="注册" name="register">配置管理</el-tab-pane>
    </el-tabs>


    
    <div id="pics">
        <el-carousel :height="screenH+'px'">
            <el-carousel-item><img :height="screenH+'px'" :width="screenW+'px'" src="/test1.JPG" /></el-carousel-item>
            <el-carousel-item><img :height="screenH+'px'" :width="screenW+'px'" src="/test1.JPG" /></el-carousel-item>
        </el-carousel>
    </div>
</div>
</body>
<script>
    var vue = new Vue({
        mounted() {
            setTimeout(function () {
                window.addEventListener("resize",function () {
                    vue.screenW=document.documentElement.clientWidth;
                    vue.screenH=document.documentElement.clientHeight;
                })
            },0)
        },
        el:'#app',
        data:{
            form:{
                name:'',
                pswd:'',
            },
            screenW:document.documentElement.clientWidth,
            screenH:document.documentElement.clientHeight,
            activeName:'login'
        },

        methods:{
            changeTab:function (tab, event) {
                console.log(tab, event);
            }
        },
        watch: {
            screenW :function(val) {
                this.screenW = val;
                console.log(this.screenW);
            },
            screenH:function (val) {
                this.screenH = val;
                console.log(this.screenH);
            }
        }
    })
</script>
</html>