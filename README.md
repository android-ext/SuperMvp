#  SuperMvp
## **MVP + RxJava + Retrofit + Glide + Material Design**

##**简介**
一款遵循**Material Design**风格的新闻，美图，天气查询应用
- Android一些次新的技术的一个合集示例
- API来自网络免费API（感谢提供api的服务商，良心啊~，不过*最新版本美图大全已经改为收费的API*）
- MVP模式（使用ViewDelegate解耦，非常感谢**kymjs**提供的mvp思想，在他的基础上我有一些改动，虽然有些地方我有点不太理解，但是决定在这个应用上做个最佳实践）@kymjs(https://github.com/kymjs/TheMVP)
- leakcanary引入(这个还在摸索到底怎么用)
- Retrofit（+RxJava）网络请求
- Glide加载缓存图片
- 使用RecyclerView展示新闻列表

##**版本**
####V0.6
- 解决美图api请求失败问题
- ╮(╯▽╰)╭续费“美图大全”，收费支持更多并发，嘿嘿，急需star来弥补金钱上的损失

####V0.5
- 美图支持类型切换，“清纯”，“气质”，“萌女”，“校花”

####V0.4
- 修改第二个tab为**美图大全**，瀑布流显示，“美图”看个够。。。
- 支持图片点击放大，双指缩放，旋转
- 抽取出一个公共的recyclerview代理类（grid，list合二为一），并支持下拉刷新

####V0.3
- 天气预报查询，弹窗提醒
- 修改新闻列表为cardview展示
- 支持新闻查看详情，CollapsingToolbarLayout +　NestedScrollView
- 支持右滑返回
- 增加*离线缓存*机制(使用Retrofit和Okhttp实现网络缓存)
- 崩溃捕捉并写入本地文件(有存储卡写到存储卡，没有保存到内存),目录：**/data/data/com.ly.supermvp/cache//Log/log.txt**，基于此功能可以后期做一个友好的应用crash提示

####V0.2
- 整体框架搭建完毕，新闻列表功能已经实现，各功能正常
- 下拉刷新与加载更多的解耦


##**截图**
####美图

![](./picture1.gif) ![](./picture2.gif)
####新闻

![](http://7xrdzx.com1.z0.glb.clouddn.com/mvp_news.jpg)
####天气预报

![](http://7xrdzx.com1.z0.glb.clouddn.com/mvp_weather.gif)

## 深受以下文章影响，感谢大神们的无私讲解
* [给Android开发者的RxJava详解](http://gank.io/post/560e15be2dca930e00da1083)
* [深入浅出RxJava](http://blog.csdn.net/lzyzsd/article/details/41833541)
* [用MVP架构开发Android应用](http://kymjs.com/code/2015/11/09/01)
* [对MVC、MVP、MVVM的理解](http://blog.csdn.net/napolunyishi/article/details/22722345)

##**开源项目**
#####RengwuxianRxjava
扔物线《给Android开发者的RxJava详解》文章中的例子  
Github地址：https://github.com/androidmalin/RengwuxianRxjava

#####SimpleNews
基于Material Design和MVP的新闻客户端    
Github地址：https://github.com/liuling07/SimpleNews

##**About me**
* 如果不能运行或者有问题请Email: LYYX@outlook.com
* WeiBo:http://weibo.com/liuyang6

##**引入的第三方库**
####squarup出品，必属精品, 不用过多介绍
* [Retrofit](https://github.com/square/retrofit)
* [LeakCanary](https://github.com/square/leakcanary)

####Rx系列
* [RxJava](https://github.com/ReactiveX/RxJava)
* [RxAndroid](https://github.com/ReactiveX/RxAndroid)
* [RxBinding](https://github.com/JakeWharton/RxBinding) (使用时根据需要使用的控件导入相应的包)

####其他热门
* [Glide](https://github.com/bumptech/glide)(Google官方推荐图片加载库)
* [Butter Knife](https://github.com/JakeWharton/butterknife)(专注于控件的注解，Dagger太全面)
* [Logger](https://github.com/orhanobut/logger)(打印log现在可以好看多了，使用so easy)
* [Material](https://github.com/rey5137/material)(一系列meterial的控件，不过用起来有点差强人意，wiki写得不是很全面)
* [SwipeBackLayout](https://github.com/ikew0ng/SwipeBackLayout)(从未想过右滑返回可以做得这么简单，非常强大！)
* [PhotoView](https://github.com/bm-x/PhotoView)(支持旋转的photoview，不过没发现有设置单击图片的回调，直接设置OnclickListener不行，**chrisbanes/PhotoView**提供onPhotoTapListener是可以的)

>嘿~走过路过不要错过~ 最后无耻的求一下**STAR**
# SuperMvp
