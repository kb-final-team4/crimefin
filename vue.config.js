const { defineConfig } = require("@vue/cli-service");
module.exports = defineConfig({
  transpileDependencies: ["vuetify"],
  lintOnSave: false,
  // npm run build 타겟 디렉토리
  outputDir: "dist",

  // npm run serve 개발 진행시에 포트가 다르기때문에 프록시 설정
  // npm run serve 개발 진행시에 포트가 다르기때문에 프록시 설정
  devServer: {
    proxy: "http://13.125.225.44:9999",
    port: 3000,
  },
});
