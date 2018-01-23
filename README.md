CommonBase
---
#### **Java通用类封装**

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/me.codeboy.common/base/badge.svg)](https://maven-badges.herokuapp.com/maven-central/me.codeboy.common/base)


## 引入方式

#### gradle

    compile 'me.codeboy.common:base:1.3.6'
    
#### maven 
        
     <dependency>
         <groupId>me.codeboy.common</groupId>
         <artifactId>base</artifactId>
         <version>1.3.6</version>
     </dependency>
 
##  JavaDoc

[http://doc.codeboy.me/Base/](http://doc.codeboy.me/Base/)

## 网络请求类使用,自动维持session

     try {
            //登录,验证用户名
            CBHttp.getInstance().connect("http://xxxx.com/checkUser")
                    .method(CBMethod.POST)
                    .data("name", "122", "password", "122")
                    .execute();
                    
            //获取后续信息
            String source = CBHttp.getInstance().connect("http://xxxx.com/getUserInfo").execute();
            CBLog.print(page);
        } catch (IOException e) {
            e.printStackTrace();
        }


## 类的说明(后续不断增加)

- CBFileUtil

        文件操作。读取，写入等
        
- CBImage

        图片处理工具

- CBHttps
    
        https请求，get,post,能够维持session

- CBHttp

        http请求，get,post,能够维持session

- CBBase64

        base64 编码解码
- CBMd5Sha

        md5 sha 加密

- CBPrint

        打印

- CBMapSortUtil

        排序map

- CBStringUtil

        字符串工具
        
- CBVersionUtil

        版本比较工具
        
- CBTaskController, CBTask
        
        任务控制,有数量限制的执行一组任务

- CBTimeWait (1.3.0+)

        时间等待,对Thead.sleep进行简单封装

- CBTimer (1.3.0+)
        
        计时器,便捷进行时间统计
        
## 更新历史

- v1.3.6

	1.CBFile支持读取文件获取对应的byte数组；支持获取项目当前目录路径。
	2.CBTaskController加入同步操作。

- v1.3.4

	CBStringUtil支持多个字符串判空。

- v1.3.3

	文件工具类CBFileUtil支持追加写入。

- v1.3.2 

	增加了计时器。
	
	
## License

```
Copyright 2016 Yuedong.li

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```

> 有任何问题,欢迎发送邮件到app@codeboy.me交流.
       