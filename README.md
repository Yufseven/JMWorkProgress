

# 一、使用（添加依赖）
##  **maven { url 'https://jitpack.io' }**
```bash
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```
##  implementation 'com.github.Yufseven:JMWorkProgress:v1.0'

```bash
dependencies {
    ...
    implementation 'com.github.Yufseven:JMWorkProgress:v1.0'
}
```



# 二、常见使用步骤
## XML中调用
### 1.在不加其他属性的样式下
```xml
<com.shenzhen.jimeng.jm.JMWorkProgress
    android:layout_width="200dp"
    android:layout_height="200dp"
    />
```
**效果图：**

![请添加图片描述](https://img-blog.csdnimg.cn/b696a35baa234e18a78e7b91da810f6f.gif)
### 2.改变线条的颜色
- **oval_big_color：最外层的线条（最大的）**
- **oval_second_color：第二层**
- **oval_thirdly_color：第三层**
- **oval_min_color：第四层**

**为了效果都改为一个颜色**

```xml
<com.shenzhen.jimeng.jm.JMWorkProgress
    android:layout_width="200dp"
    android:layout_height="200dp"
    app:oval_big_color="@color/colorAccent"
    app:oval_second_color="@color/colorPrimary"
    app:oval_thirdly_color="@color/colorPrimary"
    app:oval_min_color="@color/colorAccent"
    />
```
**效果图：**

![请添加图片描述](https://img-blog.csdnimg.cn/b0dba78fd6f34e259d030517a689ec0a.gif)
### 3.改变线条宽度
 **关键调用：app:ovalStrokeWidth**
 

```xml
<com.shenzhen.jimeng.jm.JMWorkProgress
    android:layout_width="200dp"
    android:layout_height="200dp"
    app:oval_big_color="@color/colorAccent"
    app:oval_second_color="@color/colorPrimary"
    app:oval_thirdly_color="@color/colorPrimary"
    app:oval_min_color="@color/colorAccent"
    app:ovalStrokeWidth="20"
    />
```
**效果图：**

![请添加图片描述](https://img-blog.csdnimg.cn/ba24a9c9f0ae433b820bfe1387e8238b.gif)
### 4.添加文字
- **app:text :添加的文字**
-  **app:textSize：文字大小（没有单位，数字即可）**
-  **app:textStyle：文字的样式（三个选择，分别为STROKE，FILL，FILL_AND_STROKE）**

**注意：字体颜色与左边对应线条颜色一致**

```xml
<com.shenzhen.jimeng.jm.JMWorkProgress
    android:layout_width="200dp"
    android:layout_height="200dp"
    android:layout_centerInParent="true"
    app:oval_big_color="@color/colorAccent"
    app:oval_second_color="@color/colorPrimary"
    app:oval_thirdly_color="@color/colorPrimary"
    app:oval_min_color="@color/colorAccent"
    app:text="加载中"
    app:textSize="40"
    app:textStyle="FILL"
    />
```
**！！！文字长度得在1-7的范围内**
**效果图：**

![请添加图片描述](https://img-blog.csdnimg.cn/a3c9ffde5bf5425884930554905f93ea.gif)
### XML可调用属性如以下表格
| 属性 |作用 |
|--|--|
| oval_big_color |设置最外层线条颜色 |
|oval_second_color |设置第二层线条颜色  |
| oval_thirdly_color | 设置第三层线条颜色 |
| oval_min_color |设置最内层线条颜色  |
|ovalStrokeWidth  |  设置线条宽度|
|text |设置文字内容，文字长度得在1-7的范围内 ，控件内已设计文字位置排放 |
|textStyle | 设置文字样式 |
|ovalStyle| 设置线条样式 |
|textSize | 设置字体大小 |
|ovalStrokeWidth |设置线条宽度  |

## Java中调用方法
| 主要方法 |作用 |
|--|--|
|getmBigColor |得到最外层线条颜色 |
|setmBigColor |设置最外层线条颜色 |
|getmSecondColor |得到第二层线条颜色  |
|setmSecondColor |设置第二层线条颜色  |
| getmThirdlyColor | 得到第三层线条颜色 |
| setmThirdlyColor | 设置第三层线条颜色 |
| getmMinColor |得到最内层线条颜色  |
| setmMinColor |设置最内层线条颜色  |
|ovalStrokeWidth  |  设置线条宽度|
|getmText |得到文字内容 |
|setmText |设置文字，文字长度得在1-7的范围内 ，控件内已设计文字位置排放 |
|setmTextStyle | 设置文字样式 |
|setmOvalStyle| 设置线条样式 |
|setmTextSize | 设置字体大小 |
|setmOvalStrokeWidth |设置线条宽度  |
|setTextIsShow |设置文字是否显示  |
|animationIsShow|设置动画是否显示  |
