package com.recruit.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.nio.charset.Charset;
import java.util.List;

/**
 * 作者：qiwj
 * 时间：2020/3/19
 */
@Configuration
public class MyWebMvcConfig extends WebMvcConfigurationSupport {
    /**
     * 解决springboot2.0静态资源无法直接访问问题
     *
     * @param registry
     */
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
        String os = System.getProperty("os.name");
        System.out.println(os);
        if (os.toLowerCase().startsWith("win")) {
            //windows下保存路径
            //项目图片访问路径
            //将所有d:/pictureUpload/images/upload/ 访问都映射到/images/upload/**路径下
            registry.addResourceHandler("/images/upload/**")
                    .addResourceLocations("file:D:/pictureUpload/images/upload/");
        } else {
            //linux下保存路径
            //项目图片访问路径
            //将所有/usr/pictureUpload/images/upload/访问都映射到/images/upload/**路径下
            registry.addResourceHandler("/images/upload/**")
                    .addResourceLocations("file:/usr/pictureUpload/images/upload/");
        }
        super.addResourceHandlers(registry);
    }

    /*
    * 防止中文乱码
    * */
    @Override
    protected void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        super.configureMessageConverters(converters);
        converters.add(responseBodyConverter());
    }

    @Bean
    public HttpMessageConverter responseBodyConverter() {
        StringHttpMessageConverter converter = new StringHttpMessageConverter(Charset.forName("UTF-8"));
        return converter;
    }


}//*/
