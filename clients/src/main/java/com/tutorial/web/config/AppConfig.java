package com.tutorial.web.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.corda.client.jackson.JacksonSupport;
import net.corda.client.rpc.CordaRPCClient;
import net.corda.core.messaging.CordaRPCOps;
import net.corda.core.utilities.NetworkHostAndPort;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AppConfig implements WebMvcConfigurer {

    @Value("${Lokesh.host}")
    private String lokeshHostAndPort;

    @Value("${Varatharaj.host}")
    private String varatharajHostAndPort;

    @Value("${CordaExaminar.host}")
    private String cordaExaminarHostAndPort;

    @Bean(destroyMethod = "")  // Avoids node shutdown on rpc disconnect
    public CordaRPCOps lokeshProxy(){
        CordaRPCClient lokeshClient = new CordaRPCClient(NetworkHostAndPort.parse(lokeshHostAndPort));
        return lokeshClient.start("user1", "test").getProxy();
    }

    @Bean(destroyMethod = "")
    public CordaRPCOps varatharajProxy(){
        CordaRPCClient varatharajClient = new CordaRPCClient(NetworkHostAndPort.parse(varatharajHostAndPort));
        return varatharajClient.start("user1", "test").getProxy();
    }

    @Bean(destroyMethod = "")
    public CordaRPCOps cordaExaminarProxy(){
        CordaRPCClient cordaExaminarClient = new CordaRPCClient(NetworkHostAndPort.parse(cordaExaminarHostAndPort));
        return cordaExaminarClient.start("user1", "test").getProxy();
    }

    /**
     * Corda Jackson Support, to convert corda objects to json
     */
    @Bean
    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter(){
        ObjectMapper mapper =  JacksonSupport.createDefaultMapper(cordaExaminarProxy());
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setObjectMapper(mapper);
        return converter;
    }
}
