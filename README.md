# CommonBase(Java通用类封装)

## 引入方式

#### gradle

    compile 'me.codeboy.common:base:1.2.1'
    
#### maven 
        
     <dependency>
         <groupId>me.codeboy.common</groupId>
         <artifactId>base</artifactId>
         <version>1.2.1</version>
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

- CBTimeLock

        时间锁,超过一定时间自动解锁
       