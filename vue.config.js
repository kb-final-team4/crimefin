const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: [
    'vuetify'
  ],
  lintOnSave:false,
  // npm run build 타겟 디렉토리
  outputDir: '../Crime-Fin/src/main/resources/static',

// npm run serve 개발 진행시에 포트가 다르기때문에 프록시 설정
  devServer: {
    proxy: 'http://localhost:9999'
  }
})
