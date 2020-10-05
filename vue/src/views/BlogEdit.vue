<template>
  <div>
    <Header></Header>
    <div class="x-content">
      <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
        <el-form-item label="标题" prop="title">
          <el-input  v-model="ruleForm.title" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="摘要" prop="description">
          <el-input  v-model="ruleForm.description" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="内容" prop="content">
          <mavon-editor v-model="ruleForm.content"></mavon-editor>
        </el-form-item>
        <el-form-item class="x-item">
          <el-button type="primary" @click="submitForm('ruleForm')">提交</el-button>
          <el-button @click="resetForm('ruleForm')">重置</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
    import Header from "../components/Header";
    export default {
      name: "BlogEdit",
      components: {Header},
      data() {
        var checkContent = (rule, value, callback) => {
            if(value == ''){
              callback(new Error('请输入内容'));
            }
            callback()
        };
        var checkTitle = (rule, value, callback) => {
            if(value.length<3 || value.length>25){
              callback(new Error('标题长度错误'));
            }
            callback()
        };
        var checkDescription = (rule, value, callback) => {
            if(value == ''){
              callback(new Error('请输入摘要'));
            }
            callback()
        };
        return {
          ruleForm: {
            id: '',
            title: '',
            description: '',
            content: ''
          },
          rules: {
            title: [
              { validator: checkTitle, trigger: 'blur' }
            ],
            description: [
              { validator: checkDescription, trigger: 'blur' }
            ],
            content: [
              { validator: checkContent, trigger: 'blur' }
            ]
          }
        };
      },
      methods: {
        submitForm(formName) {
          this.$refs[formName].validate((valid) => {
            if (valid) {
              const _this = this
              _this.$axios.post('/blog/edit',_this.ruleForm,{
                headers: {
                  "Authorization": localStorage.getItem('token')
                }
              }).then(res => {
                console.log(res)

                _this.$alert('操作成功', '提示', {
                  confirmButtonText: '确定',
                  callback: action => {
                    this.$message({
                      type: 'info',
                      message: `action: ${ action }`
                    });
                  }
                });

                _this.$router.push('/blogs')

              })

            } else {
              console.log('格式错误');
              return false;
            }
          });
        },
        resetForm(formName) {
          this.$refs[formName].resetFields();
        }
      },
      created() {

        const blogId = this.$route.params.blogId
        console.log(blogId)
        const _this = this
        if(blogId){
            _this.$axios.get('/blog/'+blogId).then(res => {

              const blog = res.data.data
              _this.ruleForm.id = blogId
              _this.ruleForm.title = blog.title
              _this.ruleForm.description = blog.description
              _this.ruleForm.content = blog.content


            })
        }


      }
    }
</script>

<style scoped>
      .x-content{
        text-align: center;
        margin: 0 auto;
      }
      /*.x-item{*/
      /*  text-align: center;*/
      /*  margin: 0 auto;*/
      /*}*/
</style>
