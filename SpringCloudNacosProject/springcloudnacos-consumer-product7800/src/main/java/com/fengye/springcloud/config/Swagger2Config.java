package com.fengye.springcloud.config;

import org.springframework.core.env.Environment;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.service.Contact;

/**
 * @Description:
 * @Author: huang
 * @Date: 2021/5/28 15:02
 */
//将此类交给Spring管理，表示一个配置类
@Configuration
//开启Swagger2
@EnableSwagger2
public class Swagger2Config {
    /**
     * 创建API应用
     * apiInfo() 增加API相关信息
     * 通过select()函数返回一个ApiSelectorBuilder实例,用来控制哪些接口暴露给Swagger来展现，
     * 本例采用指定扫描的包路径来定义指定要建立API的目录
     *
     * @return 返回Swagger的Docket配置Bean实例
     */
    @Bean
    public Docket createRestApi(Environment environment) {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .enable(true)  //enable是否启动swagger，如果为False，则swagger不能在浏览器中访问
                .select()
                //指定API对象扫描哪个包下面的controller
                //参数any()：扫描全部； none()：都不扫描
                //withClassAnnotation：扫描类上的注解，参数是一个注解的反射对象
                //withMethodAnnotation：扫描方法上的注解
                .apis(RequestHandlerSelectors.basePackage("com.fengye.springcloud"))
                //过滤什么路径
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * 创建该API的基本信息（这些基本信息会展现在文档页面中）
     * 访问地址：http://项目实际地址/swagger-ui.html
     * @return  返回API基本信息
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                //Swagger2展示界面的标题（重要）
                .title("抽奖接口API文档")
                //描述信息（主要）
                .description("抽奖接口API文档")
                .version("1.0")
                //.termsOfServiceUrl("https://swagger.io/docs")
                //.license("Apache 2.0")
                //.licenseUrl("http://www.apache.org/licenses/LICENSE-2.0")
                //作者信息
                .contact(new Contact("fengye", "https://www.cnblogs.com/yif0118/",
                        "hyfmailsave@163.com"))
                .build();
    }
}
