package org.github.keking.dubbo;

import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ProtocolConfig;
import org.apache.dubbo.config.RegistryConfig;

/**
 * @author kl : http://kailing.pub
 * @version 1.0
 * @date 2020/9/24
 */
public class DubboConfigUtils {

    private DubboConfigUtils() {
    }

    private static ApplicationConfig applicationConfig;

    private static RegistryConfig registryConfig;

    private static ProtocolConfig protocolConfig;


    public static ApplicationConfig applicationConfig(DubboConfig dubboConfig) {
        if (applicationConfig == null) {
            ApplicationConfig config = new ApplicationConfig();
            config.setName(dubboConfig.name.get());
            applicationConfig = config;
            return config;
        }
        return applicationConfig;
    }


    public static RegistryConfig registryConfig(DubboConfig dubboConfig) {
        if (registryConfig == null) {
            RegistryConfig config = new RegistryConfig();
            config.setAddress(dubboConfig.registrAddr.get());
            registryConfig = config;
            return config;
        }
        return registryConfig;
    }

    public static ProtocolConfig protocolConfig(DubboConfig dubboConfig){
        if(protocolConfig ==null) {
            ProtocolConfig protocol = new ProtocolConfig();
            protocol.setName(dubboConfig.protocol.name.get());
            protocol.setPort(dubboConfig.protocol.port.getAsInt());
            protocolConfig = protocol;
            return protocol;
        }
        return protocolConfig;
    }

}
