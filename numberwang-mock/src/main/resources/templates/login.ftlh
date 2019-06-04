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
    <el-tabs id="formContainer" v-bind:class="{ styleForReg: activeFlag }" v-model="activeName" @tab-click="changeTab">
        <el-tab-pane label="登录" name="login">
            <el-form id="loginForm" ref="loginForm" :model="loginForm" action="/login" method="post">
                <el-form-item class="inputItem">
                    <el-input v-model="loginForm.name" placeholder="请输入用户名" name="username" clearable></el-input>
                </el-form-item>
                <el-form-item class="inputItem">
                    <el-input v-model="loginForm.pswd" placeholder="请输入密码" name="password" show-password></el-input>
                </el-form-item>
                <el-form-item class="inputItem">
                <el-button id="loginBtn" type="primary" native-type="submit">登录</el-button>
                </el-form-item>
            </el-form>
        </el-tab-pane>
        <el-tab-pane label="注册" name="register">
            <el-form  ref="registerForm" :rules="RegisterRules" :model="registerForm" @submit.native.prevent>
                <el-form-item class="inputItem" prop="username">
                    <el-input v-model="registerForm.username"
                              placeholder="请输入用户名"
                              name="username"
                              clearable
                    ></el-input>
                </el-form-item>
                <el-form-item class="inputItem" prop="password">
                    <el-input v-model="registerForm.password"
                              placeholder="请输入密码"
                              name="password"
                              show-password
                    ></el-input>
                </el-form-item>
                <el-form-item class="inputItem" prop="email">
                    <el-input v-model="registerForm.email"
                              placeholder="请输入邮箱地址"
                              name="mail"
                              clearable
                    ></el-input>
                </el-form-item>
                <el-form-item class="inputItem">
                <el-button id="registerBtn" type="primary" native-type="submit" @click="onRegister('registerForm')">注册</el-button>
                </el-form-item>
            </el-form>
        </el-tab-pane>
    </el-tabs>

   <#--<div id="pics">-->
        <#--<el-carousel :height="screenH+'px'">-->
            <#--<el-carousel-item><img :height="screenH+'px'" :width="screenW+'px'" src="/test1.JPG" /></el-carousel-item>-->
            <#--<el-carousel-item><img :height="screenH+'px'" :width="screenW+'px'" src="/test1.JPG" /></el-carousel-item>-->
        <#--</el-carousel>-->
    <#--</div>-->
</div>
</body>
<script>
    var vue = new Vue({
        el:'#app',
        data:{
            loginForm:{
                name:'',
                pswd:'',
            },
            registerForm:{
                username:'',
                password:'',
                email:''
            },
            screenW:document.documentElement.clientWidth,
            screenH:document.documentElement.clientHeight,
            activeName:'login',
            activeFlag:false,
            RegisterRules:{
                username:[{required: true, message: '用户名不能为空', trigger: 'blur'}],
                password:[{required: true, message: '密码不能为空', trigger: 'blur'}],
                email:[{ required: true, message: '请输入邮箱地址', trigger: 'blur' },
                    { type: 'email', message: '请输入正确的邮箱地址', trigger: ['blur', 'change'] }]
            }
        },

        methods:{
            changeTab:function (tab) {
                if(tab.index > 0) this.activeFlag = true;
                else this.activeFlag = false;
                console.log(this.activeFlag)
            },
            onRegister:function(formName){
                var that = this
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        $.ajax({
                            url: '/register',
                            method: 'post',
                            data: {
                                username: that.registerForm.username,
                                password: that.registerForm.password,
                                mail: that.registerForm.email
                            },
                            success(res) {
                                console.log(res);
                                if(res.code === 0) {
                                    that.$message({
                                        message: '注册成功！',
                                        type: 'success'
                                    });
                                    that.activeName = 'login';
                                    that.activeFlag = false;
                                }
                                else{
                                    that.$message.error('注册失败！');
                                }
                            },
                            error(res) {
                                that.$message.error('注册失败！');
                            }
                        })
                    } else {
                        return false;
                    }
                });
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
        },
        mounted() {
            setTimeout(function () {
                window.addEventListener("resize",function () {
                    vue.screenW=document.documentElement.clientWidth;
                    vue.screenH=document.documentElement.clientHeight;
                })
            },0);
            <#if loginError??>
                    console.log(this)
                    this.$message.error('用户名或密码错误!');
            </#if>
        }
    })
</script>
</html>