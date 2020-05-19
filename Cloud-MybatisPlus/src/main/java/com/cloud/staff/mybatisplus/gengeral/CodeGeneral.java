package com.cloud.staff.mybatisplus.gengeral;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


public class CodeGeneral {
	//jdbc
	private static final String Driver_Name="com.mysql.jdbc.Driver";
	private static final String Url="jdbc:mysql://localhost:3306/finance?useUnicode=true&characterEncoding=utf-8&useSSL=false&useLegacyDatetimeCode=false&serverTimezone=Asia/Shanghai";
	private static final String User_Name="root";
	private static final String Pass_Word="zhao4443";
	//输出路径
	private static final String Output_Dir="F:\\SelfWorkpace\\Hospital\\InternetHospital\\InternetHospital-dockhospital\\src\\main\\java";
	private static final String Entity="com.ih.entity";
	private static final String Dao="com.ih.dao";
	private static final String service="com.ih.service";
	private static final String serviceImpl="com.ih.service.impl";
	private static final String controller="com.ih.controller";
	//private static final String Xml="";
	private static String packageName="F:\\SelfWorkpace\\Hospital\\InternetHospital\\InternetHospital-dockhospital";//xml文件路径
	private static File file = new File(packageName);
	private static String path = file.getAbsolutePath();
	//个人信息
	private static final String Author="赵参谋";
	//需要生成的表

	private static String[] tables={"finance_park_income"}; //table名字
	

	public static void main(String[] args) {
			AutoGenerator mpg = new AutoGenerator();

			//全局配置
			GlobalConfig gc = new GlobalConfig();
			gc.setOutputDir(Output_Dir);
			gc.setAuthor(Author);
			gc.setOpen(true);//是否打开输出目录
			gc.setFileOverride(true);//覆盖输出
			gc.setEntityName("%sEntity");//Entity名称
			gc.setMapperName("%sDao");//Dao名称
			gc.setXmlName("%sMapper");//Mapper名称
			gc.setEnableCache(false);//XML 二级缓存
			gc.setBaseResultMap(true);// XML ResultMap
			gc.setBaseColumnList(true);//XML columList
			mpg.setGlobalConfig(gc);

			// 数据源配置
			DataSourceConfig dsc = new DataSourceConfig();
			dsc.setUrl(Url);
			dsc.setDriverName(Driver_Name);
			dsc.setUsername(User_Name);
			dsc.setPassword(Pass_Word);
			mpg.setDataSource(dsc);

			// 包配置
			PackageConfig pc = new PackageConfig();
			pc.setController(controller);
			pc.setService(service);
			pc.setServiceImpl(serviceImpl);
			pc.setEntity(Entity);
			pc.setMapper(Dao);
			mpg.setPackageInfo(pc);

			// 策略配置
			StrategyConfig strategy = new StrategyConfig();
			strategy.setNaming(NamingStrategy.underline_to_camel);
			strategy.setColumnNaming(NamingStrategy.underline_to_camel);
			strategy.setEntityLombokModel(true);
			strategy.setRestControllerStyle(true);
			strategy.setInclude(tables);
			strategy.setControllerMappingHyphenStyle(true);
			strategy.setTablePrefix(pc.getModuleName() + "_");
			mpg.setStrategy(strategy);
			mpg.execute();
		}
}
