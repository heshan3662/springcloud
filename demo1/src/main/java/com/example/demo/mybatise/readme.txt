参考https://blog.csdn.net/yang1076180972/article/details/83507211


1.XXconfig.java
 bean.setMapperLocations(rsourcePatternResolver.getResources("com/example/demo/mybatise/dao/*.xml"));
 确定xml是否编译到指定目录，idea的maven项目 source目录下不编译xmL ,这个地方有点坑！~~
 pom.xml中build添加resources的目录，将src下所有xml文件作为resources目录进行编译！


 2.此处xml映射和mapper无关，是通过1中去取xml放入到SqlSessionFactory的！
 此处的逻辑就是取配置文件生成多个sqlSessionTemplate
 调用参考test.java 中的getSqlServerData方法

