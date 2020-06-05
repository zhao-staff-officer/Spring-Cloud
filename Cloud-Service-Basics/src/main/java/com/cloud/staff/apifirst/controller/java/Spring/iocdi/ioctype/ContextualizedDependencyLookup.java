package com.cloud.staff.apifirst.controller.java.Spring.iocdi.ioctype;


/**
 * 上下文依赖查找
 * 依赖拉取与上下文依赖查找区别：依赖拉取是从注册表中拉取依赖项，上下文依赖查找是从容器中查找依赖项
 */
public  class ContextualizedDependencyLookup{

    interface Container{
        Object getDependency(String key);
    }

    interface MangedComponet{
        void performLookup(Container container);
    }

    static class ContextDenpendencyLookup implements MangedComponet{

        @Override
        public void performLookup(Container container) {
               container.getDependency("test");
        }
    }




}
