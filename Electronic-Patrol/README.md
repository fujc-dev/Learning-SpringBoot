###  Excel 所需jar包
```text
    <!--poi-->
    <dependency>
      <groupId>org.apache.poi</groupId>
      <artifactId>poi</artifactId>
      <version>3.10-FINAL</version>
    </dependency>
    <dependency>
      <groupId>org.apache.poi</groupId>
      <artifactId>poi-ooxml</artifactId>
      <version>3.10-FINAL</version>
    </dependency>
    <!--poi-end-->
```
### 格式分析

```text
    1、有效数据范围 A - L（M-N）
    2、人员卡号  A，人员卡名 B-C ，巡检时间 D，地址卡号 E-G，地址卡名 H，事件号 I，事件名J，巡检器号k-L
    3、第一次读取从第四行开始
    4、第二个分页，查找方式未，由结束“第1页 共13页	”结束，由人员卡号中间的数据需要被过滤掉
    5、当监测到人员卡号时，自动跳到下一行数据进行读取，后续以此类推，最终完成整个Excel文件的读取

```
