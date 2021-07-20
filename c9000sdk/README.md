#### 红外、一键报警、声光报警Jna封装案例

##### 概述
```text

```


#### 事件总线
```java
 RxSubscriptions.remove(mRxSub);
 mRxSub = RxBus.getDefault().toObservable(Event.class).map(event -> event)
         .subscribe(new RxBusSubscriber<Event>() {
             @Override
             protected void onEvent(Event event) {
                 System.out.println(event.getEvent().toString());
             }
         });
 RxSubscriptions.add(mRxSub);
 ConnectNotify connectNotify = new ConnectNotify();
 connectNotify.setInfo("1");
 RxBus.getDefault().post(new Event(connectNotify));
 connectNotify.setInfo("2");
 RxBus.getDefault().post(new Event(connectNotify));
 connectNotify.setInfo("3");
 RxBus.getDefault().post(new Event(connectNotify));
```
