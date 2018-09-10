# Android快捷开发常用工具库

## 描述

* UIUtils

  主要封装和ui操作相关的方法

* ToastUtils

  toast 工具类 防止吐司队列过多时 不停的弹出

* ThreadUtils

  简单的线程池以, 获取主线Handler

* StringUtils

  字符串工具类

* SPUtils

  SP工具

* SaveObjectUtils

  序列化对象的工具

* MeasureUtils

  测量工具

* LogUtils

  日志工具，每行日志都可以在控制台直接跳到代码输出的位置

* GzipUtils

  Gzip压缩和解压工具类

* FileUtils

  File操作工具

* BitmapUtils

  图片处理与转化的工具

* ServiceUtils

  服务工具，提供查看当前服务是否在运行等函数

* Ipv4Utils

  网络ip地址工具，获取校验本机ipv4地址

## 用法

* 引入JitPack仓库

   ```
   allprojects {
   		repositories {
   			...
   			maven { url 'https://jitpack.io' }
   		}
   	}
   ```

* 添加依赖项

  ```
  	dependencies {
  	         implementation 'com.github.CNHubin:Utils:1.0.3'
  	}
  ```

* 在application中初始化

   ```
   Utils.init(this,BuildConfig.DEBUG);
   ```

* 打印log

   ```
   LogUtils.d("onCreate  D : hello!");
   LogUtils.w("onCreate  W : hello!");
   LogUtils.e("onCreate E : hello!");
   LogUtils.i("onCreate I : hello!");
   LogUtils.v("onCreate V : hello!");
   ```

   ![](icon_log.png)

    自动打印代码所在行，点击即可跳转到源码位置！

