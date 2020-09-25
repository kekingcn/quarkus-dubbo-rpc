package org.github.keking.dubbo;

import io.quarkus.arc.Arc;
import io.quarkus.arc.InstanceHandle;
import io.quarkus.arc.ResourceReferenceProvider;
import org.apache.dubbo.common.utils.StringUtils;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.annotation.DubboReference;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Set;

/**
 * @author kl : http://kailing.pub
 * @version 1.0
 * @date 2020/9/24
 */
public class DubboReferenceProvider implements ResourceReferenceProvider {

    @Override
    public InstanceHandle<Object> get(Type type, Set<Annotation> annotations) {
        DubboReference dubboReference = getAnnotation(annotations, DubboReference.class);
        if(dubboReference != null){
            DubboConfig dubboConfig = Arc.container().instance(DubboConfig.class).get();
            ApplicationConfig applicationConfig = DubboConfigUtils.applicationConfig(dubboConfig);
            RegistryConfig registryConfig = DubboConfigUtils.registryConfig(dubboConfig);

            ReferenceConfig<Object> reference = new ReferenceConfig<>();
            reference.setApplication(applicationConfig);
            reference.setRegistry(registryConfig);

            this.bindParams(reference,dubboReference);
            reference.setInterface((Class)type);
            return () -> reference.get();
        }
        return null;
    }

    private void bindParams(ReferenceConfig<Object> target,DubboReference orign){
        target.setCheck(orign.check());
        target.setRetries(orign.retries());
        target.setGroup(orign.group());
        if(orign.timeout() != 0){
            target.setTimeout(orign.timeout());
        }
        if(StringUtils.isNotEmpty(orign.onconnect())){
            target.setOnconnect(orign.onconnect());
        }
        if(StringUtils.isNotEmpty(orign.group())){
            target.setGroup(orign.group());
        }
    }
}
