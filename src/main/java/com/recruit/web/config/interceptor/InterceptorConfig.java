package com.recruit.web.config.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * 作者：qiwj
 * 时间：2020/8/1
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    private final Logger logger = LoggerFactory.getLogger(InterceptorConfig.class);

    @Bean()
    public UserHandlerInterceptor userInterceptor() {
        return new UserHandlerInterceptor();
    }
    @Autowired
    private UserHandlerInterceptor userHandlerInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        logger.info("配置拦截器的设置");
        String[] addpathpatterns = {"/**"};
        String[] excludePathPatterns = {
                "/","/images/upload/**","/error", "/**/**/*.css",
                "/**/*.css", "/**/*.js","/js/*",
                "/**/*.png ", "/**/*.jpg",
                "/**/*.jpeg", "/**/*.gif",
                "/**/fonts/*", "/**/*.svg", "/**/*.html",
                "/index/**","/center/register",
                "/password/retrievePassword","/favicon.ico","/resume/getcount","/center/**",
        };
        registry.addInterceptor(userHandlerInterceptor)
                .addPathPatterns(addpathpatterns)
                .excludePathPatterns(excludePathPatterns);
        /*


        ;
//*/

    }
    /**
     * 添加静态资源文件，外部可以直接访问地址
     *
     * @param registry
     */

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
        String os = System.getProperty("os.name");
        System.out.println(os);
        String imagePath="file:D:/pictureUpload/images/upload/";
        if (os.toLowerCase().startsWith("win")) {
            //windows下保存路径
            //项目图片访问路径
            //将所有d:/pictureUpload/images/upload/ 访问都映射到/images/upload/**路径下
            registry.addResourceHandler("/images/upload/**")
                    .addResourceLocations(imagePath);
        } else {
            //linux下保存路径
            //项目图片访问路径
            //将所有/usr/pictureUpload/images/upload/访问都映射到/images/upload/**路径下
            registry.addResourceHandler("/images/upload/**")
                    .addResourceLocations("file:/usr/pictureUpload/images/upload/");
        }

    }//*/
}
