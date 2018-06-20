## Saka JavaWeb 事件总线框架

+ Saka是一个简单的基于事件的框架总库,支持一对一发送、一对多发送，以及多对多发送消息机制，
目前项目处于1.0版本，正在处于开发中,稍后更详细的使用日志会同步更新。

## 使用方法

#### 1、添加依赖
**请等待上传到仓库**

```xml
暂未上传，敬请期待
```

#### 2、使用注解标注对象和执行器
**(注意：目前仅支持最多无参接收或者一参接收，暂不支持多个参数接收消息)**

+ 使用```@SakaSService``` 既可以将此对象注入至Spring容器


+ 使用```@SakaSubscribe```即可声明某方法为接收方法

|参数名称|说明|默认值|备注|
|-----|----|-----|-----|
|debug|是否为调试模式|false|调试模式在使用中会打印日志|

如下定义了三种基本的接收器

```java
/**
 * 实现一个接收消息
 *
 * <p>使用@SakaService注入到Spring容器
 *
 * <p>使用@SakaSubscribe标注这是一个接收器
 *
 * @author tao
 */
@SakaService
public class BaseService {

  /** 自动注入对象测试 */
  @Autowired SpeakService speakService;

  /** 不接受参数 */
  @SakaSubscribe
  public void sendEmpty() {
    speakService.speak();
    System.out.println("exec sendEmpty ");
  }

  /** 接受一个参数 */
  @SakaSubscribe
  public void sendString(String message) {
    speakService.speak();
    System.out.println("exec  sendString = " + message);
  }
  
  /** 接受一个参数自定义的参数类型 */
  @SakaSubscribe
  public void sendClassObject(Message message) {
    speakService.speak();
    System.out.println("exec  sendClassObject = " + message.getName());
  }  
}

```

#### 2、注入SakaSendClient
Saka在应用启动的时候向Spring的Context中注入ISakaClient,在SpringBoot项目中可使用以下代码自动的注入Bean对象。

```java
@RestController
public class TestController {

  /** 自动注入SakaClient */
  @Autowired
  SakaSendClient sakaSendClient;

}  
```


#### 3、使用Client发送消息
在需要发送消息的地方，注入SakaClient对象后，使用send()或者send(Object)方法发送消息.

```java
@RestController
public class TestController {

  /** 自动注入SakaClient */
  @Autowired SakaSendClient sakaSendClient;

  /**
   * 尝试发送一个字符串消息
   *
   * @param name
   * @return
   * @throws Exception
   */
  @GetMapping("/string/{name}")
  public String sendString(@PathVariable("name") String name) throws Exception {
    sakaSendClient.send(name);
    return name;
  }

  /**
   * 尝试发送自定义类型消息
   *
   * @param message
   * @return
   * @throws Exception
   */
  @GetMapping("/message/{message}")
  public String sendMessage(@PathVariable("message") String message) throws Exception {
    Message messageObject = new Message().setName(message).setAge(12);
    sakaSendClient.send(messageObject);
    return messageObject.getName();
  }

  /**
   * 尝试发送空消息
   *
   * @return
   * @throws Exception
   */
  @GetMapping("/empty")
  public String sendEmpty() throws Exception {
    sakaSendClient.send();
    return "success";
  }
} 

```

#### 4、控制台观察消息发送日志
可以使用注解@SakaSubscribe注解参数debug配置是否输入打印日志，默认是不打印日志。

```text
//Saka注册接收器日志                                                    
 Saka ------> Add a methods execCommand(1) to Saka
 Saka ------> Add a methods sendClassObject(1) to Saka
 Saka ------> Add a methods printMessage(0) to Saka
 Saka ------> Add a methods speak(0) to Saka                
                                                                  
//Saka发送消息  
 Saka ------> Send data to testMethodes1 successfully             
 Saka ------>  Saka has successfully sent 1 times data.           
 Saka ------>  Saka has successfully sent 1 times data.           
 Saka ------>  Saka has successfully sent 1 times data.           
```