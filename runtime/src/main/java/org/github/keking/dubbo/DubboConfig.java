package org.github.keking.dubbo;

import io.quarkus.runtime.annotations.ConfigGroup;
import io.quarkus.runtime.annotations.ConfigItem;
import io.quarkus.runtime.annotations.ConfigPhase;
import io.quarkus.runtime.annotations.ConfigRoot;

import java.util.Optional;
import java.util.OptionalInt;

@ConfigRoot(name = "dubbo", phase = ConfigPhase.BUILD_AND_RUN_TIME_FIXED)
public class DubboConfig {
    /**
     * 是否开启dubbo
     */
    @ConfigItem(name = ConfigItem.PARENT, defaultValue = "false")
    boolean enabled;

    /**
     * app name
     */
    @ConfigItem
    public Optional<String> name;

    /**
     * 注册中心地址
     */
    @ConfigItem
    public Optional<String> registrAddr;

    public Protocol protocol;

    @ConfigGroup
    public static class Protocol {

        /**
         * 端口
         */
        @ConfigItem
        public OptionalInt port;

        /**
         * 协议头
         */
        @ConfigItem(defaultValue = "dubbo")
        public Optional<String> name;

    }
}