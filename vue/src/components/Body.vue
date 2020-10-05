<template>
    <div>
      <div class="block">
        <el-timeline>
          <el-timeline-item :timestamp="blog.created" placement="top" v-for="blog in blogs">
            <el-card>
              <router-link :to="{name: 'BlogDetail' , params: {blogId: blog.id}}">
                <h4>{{blog.title}}</h4>
              </router-link>

              <p>{{blog.description}}</p>
            </el-card>
          </el-timeline-item>
        </el-timeline>
      </div>
      <div class="block">
        <el-pagination
          class="x-page"
          layout="prev, pager, next"
          :current-page="currentPage"
          :page-size="pageSize"
          :total="total"
          @current-change="page">
        </el-pagination>
      </div>

    </div>
</template>

<script>
    export default {
        name: "Body",
        data() {
          return {
            blogs: {},
            currentPage: 1,
            total: 0,
            pageSize: 5
          }
        },
        methods: {
          page(currentPage){
            const _this = this

            _this.$axios.get('/blogs?currentPage='+currentPage).then(res =>{
              // console.log(res)
              _this.blogs = res.data.data.records
              _this.currentPage = res.data.data.current
              _this.total = res.data.data.total
              _this.pageSize = res.data.data.size
            })
          }
          // ,
          //
          // created() {
          //   console.log('asdasd')
          //   this.page(1)
          // }
        },
      created() {
          this.page(1)
      }
    }
</script>

<style scoped>
  .x-page{
    margin: 0 auto;
    text-align: center;
  }

</style>
