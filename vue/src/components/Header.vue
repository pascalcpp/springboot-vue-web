<template>
  <div class="x-content">
    <h3>Blogs</h3>
    <div class="block">
        <el-avatar :size="50" :src="user.avatar" alt=""></el-avatar>
        <div>{{user.username}}</div>
    </div>

    <div class="x-action">
      <span><el-link type="info" @click="index">主页</el-link></span>

      <div v-show="hasLogin" class="x-block">
      <el-divider direction="vertical"></el-divider>
      <span> <el-link type="success" @click="publish">发表博客</el-link></span>
    </div>

    <div v-show="!hasLogin" class="x-block">
        <el-divider direction="vertical"></el-divider>
        <span> <el-link type="primary" @click="login">登陆</el-link></span>
      </div>

      <div v-show="hasLogin" class="x-block">
        <el-divider direction="vertical"></el-divider>
        <span><el-link type="danger" @click="logout">退出</el-link></span>
      </div>




    </div>
  </div>

</template>

<script>

// <!-- 图片路径 使用require OR 绝对路径url  http://xxxxxxxxxx
// // -->
    export default {
        name: "Header",
        data() {
          return {
            user: {
              username: '请登录',
              avatar: require('../assets/github.jpg')
            },
            hasLogin: false
          }
        },
        methods: {
          logout() {
            const _this = this

            _this.$axios.get('/logout', {
              headers: {
                "Authorization": localStorage.getItem('token')
              }
            }).then(res => {
              _this.$store.commit('REMOVE_INFO')
              _this.$router.push('/login')
            })

          },
          login() {
            this.$router.push('/login')
          },
          publish() {
            this.$router.push('/blog/add')
          },
          index() {
            this.$router.push('/blogs')
          }
        },
      created() {
        if(this.$store.getters.getUser.username){
          this.user.username = this.$store.getters.getUser.username
          this.hasLogin = true
        }
        if(this.$store.getters.getUser.avatar){
          this.user.avatar = require(''+this.$store.getters.getUser.avatar)
        }
      }
    }
</script>

<style scoped>

  .x-content{
    max-width: 960px;
    margin: 0 auto;
    text-align: center;
  }
  .x-action{
    margin: 10px auto;
  }
  .x-block{
    display: inline-block;
  }

</style>
