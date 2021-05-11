package nuc.rwenjie.common.config.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Rwenjie
 * @ClassName Swagger2Config
 * @Description TODO Swagger2配置类
 * @Date 2021/3/18 15:51
 **/

@Configuration
@EnableSwagger2
public class Swagger2Config {

    @Bean
    public Docket createAPI() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                //指定哪个包下面生成接口文档
                .apis(RequestHandlerSelectors.basePackage("nuc.rwenjie.modules.sys.controller"))
                .paths(PathSelectors.any())
                .build()
                .securityContexts(securityContexts())
                .securitySchemes(securitySchemes()); // 配置请求头信息
    }

    /**
     * 文档基本信息
     *
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .version("1.0")
                .title("DIY-workshop接口文档")
                .description("DIY-workshop接口文档")
                .contact(new Contact("Rwenjie", "localhost:8081/doc.html", "rwenjie0421@gmail.com"))
                .build();
    }

    // 1. 解决访问接口登录问题
    private List<ApiKey> securitySchemes() {
        // 设置请求头信息
        List<ApiKey> result = new ArrayList<>();
        // 参数：api key 名字 { 准备的 key 名字，value 请求头 }
        result.add(new ApiKey("Authorization", "Authorization", "header"));
        return result;
    }

    // 2. 解决访问接口登录问题
    private List<SecurityContext> securityContexts() {
        // 设置需要登录认证的路径
        List<SecurityContext> result = new ArrayList<>();
        result.add(getContextByPath());
        return result;
    }

    // 3. 解决访问接口登录问题
    private SecurityContext getContextByPath() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.regex("/hello/.*"))
                .forPaths(PathSelectors.regex("/register/.*"))
                .build();
    }

    // 4. 设置默认授权 - 解决访问接口登录问题
    private List<SecurityReference> defaultAuth() {
        List<SecurityReference> result = new ArrayList<>();
        AuthorizationScope authorizationScope = new AuthorizationScope("global","accessEverything");
        AuthorizationScope[] scopes = new AuthorizationScope[1];
        scopes[0] = authorizationScope;
        result.add(new SecurityReference("Authorization", scopes));
        return result;
    }

}
