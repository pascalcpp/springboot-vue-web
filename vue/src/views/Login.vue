<template>
  <div>
    <el-container>
      <el-header>
        <img class="x-img" src="../assets/github.jpg" alt="">
      </el-header>

      <el-main>
        <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
          <el-form-item label="用户名" prop="username">
            <el-input  v-model="ruleForm.username" autocomplete="off"></el-input>
          </el-form-item>

          <el-form-item label="密码" prop="password">
            <el-input type="password" v-model="ruleForm.password" autocomplete="off"></el-input>
          </el-form-item>

          <el-form-item>
            <el-button type="primary" @click="submitForm('ruleForm')">登陆</el-button>
          </el-form-item>
        </el-form>
      </el-main>
    </el-container>
  </div>

</template>

<script>
    export default {
      name: "Login",
      data() {
        var validateUsername = (rule, value, callback) => {
          var usernamePattern = /^[a-zA-Z0-9_-]{4,16}$/;
          if(!usernamePattern.test(value)){
            callback(new Error('用户名4到16位（字母，数字，下划线，减号）'))
          }
          callback();
        };
        var validatePassword = (rule, value, callback) => {
          var passwordPattern = /^[a-zA-Z0-9_-]{4,16}$/;
          if(!passwordPattern.test(value)){
            callback(new Error('密码4到16位（字母，数字，下划线，减号）'))
          }
          callback();
        };
        return {
          ruleForm: {
            username: '',
            password: ''
          },
          rules: {
            username: [
              { validator: validateUsername, trigger: 'blur' }
            ],
            password: [
              { validator: validatePassword, trigger: 'blur' }
            ]
          }
        };
      },
      methods: {
        submitForm(formName) {
          this.$refs[formName].validate((valid) => {
            if (valid) {
              alert('submit!');
              const _this = this
              this.$axios.post('/login',this.ruleForm).then(res=>{
                // console.log(res.headers)
                // console.log(res)
                //注意小写
                const jwt = res.headers['authorization']
                const userInfo = res.data.data

                _this.$store.commit('SET_TOKEN',jwt)
                _this.$store.commit('SET_USERINFO',userInfo)

                // console.log(_this.$store.getters.getUser)
                console.log(res)
                console.log(res.data)
                console.log(res.data.data)

                _this.$router.push('/blogs')
              })


            } else {
              alert('输入错误')
              return false;
            }
          });
        },
        resetForm(formName) {
          this.$refs[formName].resetFields();
        }
      }
    }
</script>

<style scoped>
  .el-header, .el-footer {
    background-color: #B3C0D1;
    color: #333;
    text-align: center;
    line-height: 60px;
  }

  .el-aside {
    background-color: #D3DCE6;
    color: #333;
    text-align: center;
    line-height: 200px;
  }

  .el-main {
    /*background-color: #E9EEF3;*/
    color: #333;
    text-align: center;
    line-height: 160px;
  }

  body > .el-container {
    margin-bottom: 40px;
  }

  .el-container:nth-child(5) .el-aside,
  .el-container:nth-child(6) .el-aside {
    line-height: 260px;
  }

  .el-container:nth-child(7) .el-aside {
    line-height: 320px;
  }

  .x-img{
    /*height: 50px;*/
    /*width: 50px;*/
    height: 60%;
    margin-top: 5px;
  }
  .demo-ruleForm{
    max-width: 500px;
    margin: 0 auto;
  }
</style>
