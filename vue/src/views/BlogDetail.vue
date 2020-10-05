<template>
  <div>
    <Header></Header>
    <div class="x-blog">
      <h2 class="x-title">{{blog.title}}</h2>
      <router-link :to="{name: 'BlogEdit' , params: {blogId: blog.id}}">
        <el-link icon="el-icon-edit" v-if="ownBlog">编辑</el-link>
      </router-link>

      <el-divider></el-divider>
      <div class="markdown-body" v-html="blog.content"></div>
    </div>

  </div>
</template>

<script>
    import Header from "../components/Header";
    import '_github-markdown-css@4.0.0@github-markdown-css'

    export default {
        name: "BlogDetail",
        components: {Header},
        data() {
          return {
            blog: {
              id: '',
              title: '',
              content: ''
            },
            ownBlog: false
          }
        },
        created() {
          const blogId = this.$route.params.blogId
          console.log(blogId)
          const _this = this
          if(blogId){
            _this.$axios.get('/blog/'+blogId).then(res => {

              const blog = res.data.data
              _this.blog.id = blogId
              _this.blog.title = blog.title

              let MarkdownIt = require('markdown-it')
              let md = new MarkdownIt()
              let result = md.render(blog.content)
              console.log(result)
              _this.blog.content = result

              _this.ownBlog = (blog.userId === _this.$store.getters.getUser.id)
          })
          }
        }
    }

</script>

<style scoped>
  .x-blog{
    min-height: 700px;
    width: 100%;
    box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
    padding: 20px 15px;
  }

  .x-title{
    text-align: center;
  }


</style>
