
![](http://i.imgur.com/Rx8wdOv.png)


----------------------------
View: 应用的界面。这里创建响应用户操作的action。

----------------------------

Dispatcher: 中心枢纽，传递所有的action，负责把它们运达每个Store。

可以使用EventBus Or Otto

----------------------------

Store: 维护一个特定application domain的状态。它们根据当前状态响应action，执行业务逻辑，同时在完成的时候发出一个change事件。这个事件用于view更新其界面。

这三个部分都是通过Action来通信的：一个简单的基本对象，以类型来区分，包含了和操作相关的数据。

----------------------------

Actions也不复杂。它们的实现和POJO一样简单，有两个主要属性

Type: 一个String，定义了事件的类型。

Data: 一个map，装载了本次操作。
