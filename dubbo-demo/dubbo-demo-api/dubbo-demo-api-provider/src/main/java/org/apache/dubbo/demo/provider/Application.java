/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.dubbo.demo.provider;

import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.ServiceConfig;
import org.apache.dubbo.demo.DemoService;

public class Application {
    public static void main(String[] args) throws Exception {
        /**
         * new ServiceConfig<>()
         * ----private static final Protocol protocol = ExtensionLoader.getExtensionLoader(Protocol.class).getAdaptiveExtension()
         * ----private static final ProxyFactory PROXY_FACTORY = ExtensionLoader.getExtensionLoader(ProxyFactory.class).getAdaptiveExtension();
         * ----private static final ScheduledExecutorService DELAY_EXPORT_EXECUTOR = Executors.newSingleThreadScheduledExecutor(new NamedThreadFactory("DubboServiceDelayExporter", true));
         */
        ServiceConfig<DemoServiceImpl> service = new ServiceConfig<>();
        /**
         * new ApplicationConfig("dubbo-demo-api-provider")
         * ----this.name = dubbo-demo-api-provider;
         * ----this.id = dubbo-demo-api-provider;
         * service.setApplication
         * ----this.application = application;
         * ----ConfigManager:this.application = application; 配置管理器
         *
         */
        service.setApplication(new ApplicationConfig("dubbo-demo-api-provider"));
        /**
         * new RegistryConfig
         * ----this.address = address;
         * ----org.apache.dubbo.config.AbstractConfig#id=zookeeper
         * ----org.apache.dubbo.config.RegistryConfig#protocol=zookeeper
         * ----org.apache.dubbo.config.RegistryConfig#port=2181
         * service.setRegistry
         * ----List<RegistryConfig> registries.add(registry)
         * ----org.apache.dubbo.config.context.ConfigManager#registries.put(registry.id, registry)
         */
        service.setRegistry(new RegistryConfig("zookeeper://192.168.145.100:2181"));
        /**
         *  this.interfaceClass = interfaceClass;
         *  this.interfaceName = interfaceClass.getName();
         *  org.apache.dubbo.config.AbstractConfig#id = interfaceClass.getName();
         */
        service.setInterface(DemoService.class);
        /**
         * org.apache.dubbo.config.ServiceConfig#ref=new DemoServiceImpl()
         */
        service.setRef(new DemoServiceImpl());
        /**
         *
         */
        service.export();
        System.in.read();
    }
}
