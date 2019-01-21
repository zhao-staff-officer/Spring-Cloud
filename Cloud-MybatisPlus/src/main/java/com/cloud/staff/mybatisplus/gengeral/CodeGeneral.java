package com.cloud.staff.mybatisplus.gengeral;

import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.FileOutConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

public class CodeGeneral {
	//jdbc
	private static final String Driver_Name="com.mysql.jdbc.Driver";
	private static final String Url="jdbc:mysql://182.150.59.121:2236/db_sr?useUnicode=true&characterEncoding=utf-8&useSSL=false&useLegacyDatetimeCode=false&serverTimezone=Asia/Shanghai";
	private static final String User_Name="user_istore";
	private static final String Pass_Word="istore";
	//输出路径
	private static final String Output_Dir="E:\\SelfWorkpace\\Hospital\\InternetHospital\\InternetHospital-dockhospital\\src\\main\\java";
	private static final String Entity="com.ih.entity";
	private static final String Dao="com.ih.dao";
	private static final String service="com.ih.service";
	private static final String serviceImpl="com.ih.service.impl";
	private static final String controller="com.ih.controller";
	//private static final String Xml="";
	private static String packageName="E:\\SelfWorkpace\\Hospital\\InternetHospital\\InternetHospital-dockhospital";//xml文件路径
	private static File file = new File(packageName);
	private static String path = file.getAbsolutePath();
	//个人信息
	private static final String Author="赵参谋";
	//需要生成的表
	
	private static String table="frontend_data"; //table名字
	

	public static void main(String[] args) {
        AutoGenerator mpg = new AutoGenerator()
        	//全局配置
        	.setGlobalConfig(new GlobalConfig()
        		    .setOutputDir(Output_Dir)//输出目录
        		    .setFileOverride(true)//是否覆盖文件
        		    .setActiveRecord(false)//是否开启activeRecord模式
        		    .setEnableCache(false)//XML 二级缓存
        		    .setBaseResultMap(true)// XML ResultMap
        		    .setBaseColumnList(true)//XML columList
        		    .setMapperName("%sMapper")
        		    .setXmlName("%sMapper")
        		    .setServiceName("%sService")
        		    .setServiceImplName("%sServiceImpl")
        		    .setControllerName("%sController")
        		    .setAuthor(Author)
            //数据连接配置
        	).setDataSource(new DataSourceConfig()
        			.setDbType(DbType.MYSQL)
        			.setTypeConvert(new MySqlTypeConvert() {
        				@Override
                        public DbColumnType processTypeConvert(String fieldType) {
                            return super.processTypeConvert(fieldType);
        			    }}
        			 )
        			.setDriverName(Driver_Name)
        			.setUsername(User_Name)
        			.setPassword(Pass_Word)
        			.setUrl(Url)
            //策略配置
            ).setStrategy(new StrategyConfig()
                    .setNaming(NamingStrategy.underline_to_camel)//表名生成策略
                    .setInclude(new String[] { table })//需要生成的表
            //包配置
            ).setPackageInfo(new PackageConfig()
            		.setParent(null)
            		.setEntity(Entity)
            		.setMapper(Dao)
            		//.setXml(Xml)
            		.setService(service)
            		.setServiceImpl(serviceImpl)
            		.setController(controller)
            // 注入自定义配置，可以在 VM 中使用 cfg.abc 设置的值
            ).setCfg(new InjectionConfig() {
				@Override
				public void initMap() {
					 Map<String, Object> map = new HashMap<>();
                     map.put("abc", this.getConfig().getGlobalConfig().getAuthor() + "-mp");
                     this.setMap(map);
				 }
			   }.setFileOutConfigList(Collections.<FileOutConfig>singletonList(new FileOutConfig("/templates/mapper.xml.vm"){
				   @Override
                   public String outputFile(TableInfo tableInfo) {
                       return path+"/src/main/resources/mapper/" + tableInfo.getEntityName() + "Mapper.xml";
                   }
			   }))
            // 关闭默认 xml 生成，调整生成 至 根目录
            ).setTemplate( new TemplateConfig().setXml(null));
        	

        // 执行生成
        mpg.execute();

        // 打印注入设置
        System.err.println(mpg.getCfg().getMap().get("abc"));
    }
}
