[toc]

# 图书管理系统

推荐视频：[JAVA+MySql 图书管理系统，技术:Swing+jdbc+mysql](https://www.bilibili.com/video/BV1it41157ph?p=1)

详细设计步骤，可参考[我的博客]()

## 仓库介绍

`jdbc文件夹`：包含连接MySQL8.0的驱动

`doc文件夹`：本项目生成的[Java文档](https://eternidad33.gitee.io/bookmanager/overview-summary.html)

`src文件夹`：本项目的源码

`.idea文件夹`：本项目在idea中的配置

`.gitignore`：设置仓库提交时忽略的文件夹或某类型文件

`db_book.sql`：生成数据库的文件

`BookManager.iml`：idea中的项目配置文件

## 实验环境

Java版本：<font color=#ff0000> JDK 9 </font>

MySQL版本：<font color=#ff0000>8.0.16</font>

开发工具：Idea

Idea插件:`JFormDesigner`和`EasyCode`

相关技术：用MySQL作为数据库，Java的swing设计界面，通过JDBC驱动使数据库和界面进行连接

## 界面设计及效果

1. 测试数据库连接

   连接数据库失败直接弹出窗口，退出程序

   ![数据库连接](https://gitee.com/eternidad33/picbed/raw/master/img/BookManager数据库连接.gif)

2. 登录界面

   ![BookManager登录](https://gitee.com/eternidad33/picbed/raw/master/img/BookManager登录.gif)

3. 图书管理主界面

   ![主界面](https://gitee.com/eternidad33/picbed/raw/master/img/BookManager主界面.gif)

4. 添加图书界面

   ![图书添加](https://gitee.com/eternidad33/picbed/raw/master/img/BookManager图书添加.gif)

5. 图书类别管理界面

   ![图书类别](https://gitee.com/eternidad33/picbed/raw/master/img/BookManager图书类别.gif)

6. 添加图书类别界面

   ![类别添加](https://gitee.com/eternidad33/picbed/raw/master/img/BookManager类别添加.gif)

## 代码行数统计

![代码行数统计](https://cdn.jsdelivr.net/gh/eternidad33/picbed@master/img/BookManager代码行数统计.png)

虽然表面看上去是2000多行Java代码，实际上80%以上都是idea插件自己生成的。

> 使用前要将`DbUtil.java`文件中的数据库URL，用户名，密码，设为自己的数据库URL，用户名，密码