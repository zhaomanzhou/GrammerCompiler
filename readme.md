## 环境准备
- jdk8或以上
- maven
- rocketmq
- redis
- mysql

## 运行项目
在根目录运行
```
mvn spring-boot:run
```
## 一些配置项

#### 验证码发送邮箱
```
spring:
    mail:
```

#### Mysql
```
spring:
    datasource:
```

#### redis

```
spring:
    redis:
```
### rocketmq
```
mq:
    
```

### 用户提交代码以及结构声明代码存放文件夹

```
compiler:
    userFolder:
    problemFolder:
```

