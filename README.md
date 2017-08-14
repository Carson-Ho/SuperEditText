# SuperEditText
>  Click to see [English Document](https://github.com/Carson-Ho/SuperEditText/blob/master/README-en.md)
- 作者：Carson_Ho
- 概述

![示意图](http://upload-images.jianshu.io/upload_images/944365-ff40bf46370cd596.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


**注：关于该开源项目的意见 & 建议可在Issue上提出。欢迎 Star ！**

## 1. 简介
一款  **简单 & 好用**的 `SuperEditText`控件
>Github地址：[Super_EditText](https://github.com/Carson-Ho/SuperEditText)

![效果图](http://upload-images.jianshu.io/upload_images/944365-10b970da9048907f.gif?imageMogr2/auto-orient/strip)



## 2. 功能介绍
### 2.1 需求场景
对于 `EditText`来说，一般的需求有：
- 方便用户因出现输入错误而进行2次输入
- 标识用户正在填写项
- 根据具体场景增加一定的`UI`元素

### 2.2 功能需求
根据需求场景，得出`EditText`需要具备的功能如下：
- 一键删除
- 丰富的自定义样式：左侧图标、删除功能图标、分割线 & 光标 样式变化。具体如下图：

![示意图](http://upload-images.jianshu.io/upload_images/944365-153064dd032afe4d.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

**注：该样式的设置是系统自带的 `API` 所不具备的**

- 功能列表

![示意图](http://upload-images.jianshu.io/upload_images/944365-399c5869b7adb090.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

### 2.3 功能示意

![效果图](http://upload-images.jianshu.io/upload_images/944365-10b970da9048907f.gif?imageMogr2/auto-orient/strip)


# 3. 特点
对比市面上`EditText`控件，该控件[Super_EditText](https://github.com/Carson-Ho/SuperEditText) 的特点是：

### 3.1 功能实用
- 一键删除功能 在需求中非常常见，现将其封装后更加方便使用
- 可自定义样式程度高（比自带的强大 & 方便），不复杂却能满足一般的`EditText`使用需求
>可自定义样式如下：（注：该样式的设置是系统自带的 `API` 所不具备的）


![示意图](http://upload-images.jianshu.io/upload_images/944365-153064dd032afe4d.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


### 3.2 使用简单
仅需要简单的`xml`属性配置
>下面1节会详细介绍其使用方法

### 3.3 二次开发成本低
- 本项目已在 `Github`上开源：[Super_EditText](https://github.com/Carson-Ho/SuperEditText)
- 具备详细的源码分析文档：[Android自定义View：手把手教你做一款简单实用的SuperEditText(一键删除&自定义样式)](http://www.jianshu.com/p/99d9dc5bcffc)

所以，在其上做二次开发 & 定制化成本非常低。


# 4. 具体使用
该款[Super_EditText](https://github.com/Carson-Ho/SuperEditText)使用非常简单，仅需2步：

### 步骤1：导入控件库
主要有 `Gradle` & `Maven` 2种方式：

- 方式1：`Gradle`引入依赖
*build.Gradle*

```
dependencies {
    compile 'com.carson_ho:Super_EditText:1.0.0'
}
```

- 方式2：`Maven`引入依赖
*pom.xml*
```
<dependency>
  <groupId>com.carson_ho</groupId>
  <artifactId>Super_EditText</artifactId>
  <version>1.0.0</version>
  <type>pom</type>
</dependency>
```


### 步骤2：设置动画属性
- 属性说明


![示意图](http://upload-images.jianshu.io/upload_images/944365-baa414ba395a85f6.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


- 具体属性设置


![示意图](http://upload-images.jianshu.io/upload_images/944365-622a93d4c1ffcebd.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)



- 使用示例
在`XML`文件中进行设置
*activity_main.xml*
```
<scut.carson_ho.diy_view.SuperEditText
        android:layout_width="200dp"
        android:layout_height="wrap_content"
        // 设置左侧图标
        app:ic_left_click="@drawable/ic_left_click"
        app:ic_left_unclick="@drawable/ic_left_unclick"
        app:left_x="0"
        app:left_y="0"
        app:left_width="60"
        app:left_height="60"

        // 设置删除图标
        app:ic_delete="@drawable/delete"
        app:delete_x="0"
        app:delete_y="0"
        app:delete_width="80"
        app:delete_height="80"

        // 设置分割线
        app:lineColor_click="@color/colorfocus"
        app:lineColor_unclick="@color/colorDeafult"
        app:linePosition="1"

        // 设置光标
        app:cursor="@drawable/cursortest"

         />
```


*cursortest.xml*
```
<?xml version="1.0" encoding="utf-8"?>
<shape xmlns:android="http://schemas.android.com/apk/res/android"
    android:shape="rectangle" >

    <solid android:color="@color/colorfocus" />
    <size android:width="1dp" />

</shape>
```




# 5. 完整Demo地址
[Carson_Ho的Github地址：Super_EditText](https://github.com/Carson-Ho/SuperEditText)

![效果图](http://upload-images.jianshu.io/upload_images/944365-10b970da9048907f.gif?imageMogr2/auto-orient/strip)



## 6.  源码解析
具体请看文章[Android自定义View：手把手教你做一款简单实用的SuperEditText(一键删除&自定义样式)](http://www.jianshu.com/p/99d9dc5bcffc)



## 7.  开源协议

`Super_EditText` 遵循 `MIT` 开源协议


## 8. 贡献代码
- 具体请看：[贡献代码说明](https://github.com/Carson-Ho/SuperEditText/blob/master/CONTRIBUTING.md)
- 关于该开源项目的意见 & 建议可在`Issue`上提出。欢迎 Star ！



## 9. 版本说明
2017-08-14 v1.0.0 ：完成一键删除 & 自定义样式



# 关于作者
- ID：Carson_Ho
- 简介：CSDN签约作者、简书推荐作者、稀土掘金专栏作者
- E - mail：[carson.ho@foxmail.com](mailto:carson.ho@foxmail.com)
- Github：[https://github.com/Carson-Ho](https://github.com/Carson-Ho)
- CSDN：[http://blog.csdn.net/carson_ho](http://blog.csdn.net/carson_ho)
- 简书：[http://www.jianshu.com/u/383970bef0a0](http://www.jianshu.com/u/383970bef0a0)
- 稀土掘金：[https://juejin.im/user/58d4d9781b69e6006ba65edc](https://juejin.im/user/58d4d9781b69e6006ba65edc)
