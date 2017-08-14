# Super_EditText
>[中文文档](https://github.com/Carson-Ho/SuperEditText/blob/master/README.md)
- Author：Carson_Ho
- Summary




![示意图](http://upload-images.jianshu.io/upload_images/944365-022edc10b8a9c22b.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)



## 1. Introduction
a simple & useful  Android DIY View  for EditText
>[Carson_Ho的Github地址：Super_EditText](https://github.com/Carson-Ho/SuperEditText)

![效果图](http://upload-images.jianshu.io/upload_images/944365-10b970da9048907f.gif?imageMogr2/auto-orient/strip)


## 2. Application Scenarios
 Batch delete & High Diy style



## 3. Feature
- Fresh & concise style
- Easy to use
- Secondary Programming costs are low

## 4. Usage

##### Step 1：Import Library
There are two ways to  import Library：

- 1. For Gradle
*build.Gradle*

```
dependencies {
    compile 'com.carson_ho:Super_EditText:1.0.0'
}
```

- 2. For Maven
*pom.xml*
```
<dependency>
  <groupId>com.carson_ho</groupId>
  <artifactId>Super_EditText</artifactId>
  <version>1.0.0</version>
  <type>pom</type>
</dependency>
```


##### Step 2：Set Animation Attributes
- Attributes Description：


![示意图](http://upload-images.jianshu.io/upload_images/944365-baa414ba395a85f6.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

![示意图](http://upload-images.jianshu.io/upload_images/944365-622a93d4c1ffcebd.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


- Use examples

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

## 5. Complete Demo
[Carson_Ho的Github地址：Super_EditText](https://github.com/Carson-Ho/SuperEditText)


![效果图](http://upload-images.jianshu.io/upload_images/944365-10b970da9048907f.gif?imageMogr2/auto-orient/strip)



## 6.  Source code analysis
[Android Diy View：SuperEditText Source code analysis ](http://www.jianshu.com/p/99d9dc5bcffc)



## 7.  LICENSE
Super_EditText is available under the MIT license.



## 8. Contribute
Before you open an issue or create a pull request, please read [Contributing Guide](https://github.com/Carson-Ho/SuperEditText/blob/master/CONTRIBUTING.md) first.



## 9. Release
2017-08-14 v1.0.0 ：finish basic function



# About the author
- ID：Carson_Ho
- 简介：CSDN签约作者、简书推荐作者、稀土掘金专栏作者
- E - mail：[carson.ho@foxmail.com](mailto:carson.ho@foxmail.com)
- Github：[https://github.com/Carson-Ho](https://github.com/Carson-Ho)
- CSDN：[http://blog.csdn.net/carson_ho](http://blog.csdn.net/carson_ho)
- 简书：[http://www.jianshu.com/u/383970bef0a0](http://www.jianshu.com/u/383970bef0a0)
- 稀土掘金：[https://juejin.im/user/58d4d9781b69e6006ba65edc](https://juejin.im/user/58d4d9781b69e6006ba65edc)
