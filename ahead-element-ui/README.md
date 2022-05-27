## 说明
本框架基于[vue-element-admin](https://panjiachen.github.io/vue-element-admin)框架进行封装和开发。

## Features

```
- 登陆 / 退出

- Permission Authentication
  - Page permission
  - Directive permission
  - Permission configuration page
  - Two-step login

- Multi-environment build
  - Develop (dev)
  - sit
  - Stage Test (stage)
  - Production (prod)

- Global Features
  - I18n
  - Multiple dynamic themes
  - Dynamic sidebar (supports multi-level routing)
  - Dynamic breadcrumb
  - Tags-view (Tab page Support right-click operation)
  - Svg Sprite
  - Mock data
  - Screenfull
  - Responsive Sidebar

- Editor
  - Rich Text Editor
  - Markdown Editor
  - JSON Editor

- Excel
  - Export Excel
  - Upload Excel
  - Visualization Excel
  - Export zip

- Table
  - Dynamic Table
  - Drag And Drop Table
  - Inline Edit Table

- Error Page
  - 401
  - 404

- Components
  - Avatar Upload
  - Back To Top
  - Drag Dialog
  - Drag Select
  - Drag Kanban
  - Drag List
  - SplitPane
  - Dropzone
  - Sticky
  - CountTo

- Advanced Example
- Error Log
- Dashboard
- Guide Page
- ECharts
- Clipboard
- Markdown to html
```

## 快速开始

```bash
# 克隆项目
git clone https://github.com/PanJiaChen/vue-element-admin.git

# avoid git error
git config --global url.https://.insteadOf git://

# 特别说明: 注释掉packageage.json中的"tui-editor": "1.3.3".最后单独执行npm install --save tui-editor
# 也可参考https://blog.csdn.net/weixin_44179923/article/details/123851598处理
# install dependency
# npm config set registry https://registry.npm.taobao.org --global
# npm install --registry=https://registry.npm.taobao.org
npm install

# develop
npm run dev
```

This will automatically open http://localhost:9527

## Build

```bash
# build for test environment
npm run build:stage

# build for production environment
npm run build:prod
```

## Advanced

```bash
# preview the release environment effect
npm run preview

# preview the release environment effect + static resource analysis
npm run preview -- --report

# code format check
npm run lint

# code format check and auto fix
npm run lint -- --fix
```
## 使用心得
### 使用cnpm提速
``` bash
 # 初始使用项目npm install较慢时,可以考虑使用cnpm
 # 安装cnpm
 npm install -g cnpm --registry=https://registry.npm.taobao.org
 # cnpm的命令和npm一致
 cnpm install

 # 如报错cnpm : 无法加载文件 npm\cnpm.ps1。未对文件 npm\cnpm.ps1 进行数字签名。无法在当前系统上运行该脚本。
 # 管理员打开powershell,执行以下命令。然后再次执行install即可
 set-ExecutionPolicy RemoteSigned

```
## Browsers support

Modern browsers and Internet Explorer 10+.

| IE / Edge | Firefox | Chrome | Safari |
| --------- | --------- | --------- | --------- |
| IE10, IE11, Edge | last 2 versions | last 2 versions | last 2 versions |

## License

MIT

Copyright (c) 2022-present young
