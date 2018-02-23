package com.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.JavaFormatter;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.ShellCallback;
import org.mybatis.generator.api.dom.java.CompilationUnit;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.Document;
import org.mybatis.generator.api.dom.xml.Element;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.codegen.mybatis3.ListUtilities;
import org.mybatis.generator.codegen.mybatis3.MyBatis3FormattingUtilities;
import org.mybatis.generator.exception.ShellException;
import org.mybatis.generator.internal.DefaultShellCallback;
import static org.mybatis.generator.internal.util.StringUtility.stringHasValue;


/**
 * MBG 插件
 * 
 * @author Belong.
 */
public class MapperPlugin extends PluginAdapter {	
 
    private static final String DEFAULT_DAO_SUPER_CLASS = "com.gnt.mapper.BaseMapper";
    private static final String DEFAULT_EXPAND_DAO_SUPER_CLASS = "com.dfz.base.BaseExpandMapper";
    private String daoTargetDir;
    private String daoTargetPackage;

    private String daoSuperClass;

    // 扩展
    private String expandDaoTargetPackage;
    private String expandDaoSuperClass;

    private ShellCallback shellCallback = null;

    public MapperPlugin() {
        shellCallback = new DefaultShellCallback(false);
    }

    /** 
     * 生成dao 
     */  
    @Override  
    public boolean clientGenerated(Interface interfaze, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {  
    	
        FullyQualifiedJavaType fqjt = new FullyQualifiedJavaType("BaseMapper<" + introspectedTable.getBaseRecordType() + "Key, " + introspectedTable.getBaseRecordType()  + ">");  
//        FullyQualifiedJavaType imp = new FullyQualifiedJavaType("com.gnt.mapper.BaseMapper");  
        interfaze.addSuperInterface(fqjt);// 添加 extends BaseDao<User>  
//        interfaze.addImportedType(imp);// 添加import common.BaseDao;  
        interfaze.getMethods().clear();  
        return true;  
    }  
    
    /**
     * 验证参数是否有效
     * @param warnings
     * @return
     */
    public boolean validate(List<String> warnings) {
        /*daoTargetDir = properties.getProperty("targetProject");
        boolean valid = stringHasValue(daoTargetDir);
        daoTargetPackage = properties.getProperty("targetPackage");
        boolean valid2 = stringHasValue(daoTargetPackage);
        daoSuperClass = properties.getProperty("daoSuperClass");
        if (!stringHasValue(daoSuperClass)) {
            daoSuperClass = DEFAULT_DAO_SUPER_CLASS;
        }
        expandDaoTargetPackage = properties.getProperty("expandTargetPackage");
        expandDaoSuperClass = properties.getProperty("expandDaoSuperClass");
        if (!stringHasValue(expandDaoSuperClass)) {
            expandDaoSuperClass = DEFAULT_EXPAND_DAO_SUPER_CLASS;
        }
        return valid && valid2;*/
        return true;
    }
    
    /**
     * create selectAll method in mapper xml 
     * 
     * @param document
     * @param introspectedTable
     * @return 
     */
    @Override
    public boolean sqlMapDocumentGenerated(Document document, IntrospectedTable introspectedTable) {
        XmlElement select = new XmlElement("select");
        select.addAttribute(new Attribute("id", "selectAll"));
        select.addAttribute(new Attribute("resultMap", "BaseResultMap"));
//        select.addAttribute(new Attribute("parameterType", introspectedTable.getBaseRecordType()));
        select.addElement(new TextElement(" select * from "+ introspectedTable.getFullyQualifiedTableNameAtRuntime()));

        XmlElement parentElement = document.getRootElement();
        parentElement.addElement(select);
        return super.sqlMapDocumentGenerated(document, introspectedTable);
    }
    
    //sqlMapSelectAllElementGenerated 不是simple 未被调用
    /*@Override
    public boolean sqlMapSelectAllElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
    	XmlElement select = new XmlElement("select");
        select.addAttribute(new Attribute("id", "selectAll"));
        select.addAttribute(new Attribute("resultMap", "BaseResultMap"));
        select.addElement(new TextElement(" select * from "+ introspectedTable.getFullyQualifiedTableNameAtRuntime()));
        
        element.addElement(select);
        return super.sqlMapSelectAllElementGenerated(element, introspectedTable);
    }*/
    
    //revise update SQL statement
    /*
    @Override
    public boolean sqlMapUpdateByPrimaryKeySelectiveElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
    	
//    	start
        
        XmlElement answer = new XmlElement("update"); //$NON-NLS-1$

        answer.addAttribute(new Attribute(
                "id", introspectedTable.getUpdateByPrimaryKeySelectiveStatementId())); //$NON-NLS-1$

        String parameterType;

        if (introspectedTable.getRules().generateRecordWithBLOBsClass()) {
            parameterType = introspectedTable.getRecordWithBLOBsType();
        } else {
            parameterType = introspectedTable.getBaseRecordType();
        }

        answer.addAttribute(new Attribute("parameterType", //$NON-NLS-1$
                parameterType));

        context.getCommentGenerator().addComment(answer);

        StringBuilder sb = new StringBuilder();

        sb.append("update "); //$NON-NLS-1$
        sb.append(introspectedTable.getFullyQualifiedTableNameAtRuntime());
        answer.addElement(new TextElement(sb.toString()));

        XmlElement dynamicElement = new XmlElement("set"); //$NON-NLS-1$
        answer.addElement(dynamicElement);

        for (IntrospectedColumn introspectedColumn : ListUtilities.removeGeneratedAlwaysColumns(introspectedTable
                .getNonPrimaryKeyColumns())) {
            sb.setLength(0);
            sb.append(introspectedColumn.getJavaProperty());
            sb.append(" != null"); //$NON-NLS-1$
            XmlElement isNotNullElement = new XmlElement("if"); //$NON-NLS-1$
            isNotNullElement.addAttribute(new Attribute("test", sb.toString())); //$NON-NLS-1$
            dynamicElement.addElement(isNotNullElement);

            sb.setLength(0);
            sb.append(MyBatis3FormattingUtilities
                    .getEscapedColumnName(introspectedColumn));
            sb.append(" = "); //$NON-NLS-1$
            sb.append(MyBatis3FormattingUtilities
                    .getParameterClause(introspectedColumn));
            sb.append(',');

            isNotNullElement.addElement(new TextElement(sb.toString()));
        }

        boolean and = false;
        for (IntrospectedColumn introspectedColumn : introspectedTable
                .getPrimaryKeyColumns()) {
            sb.setLength(0);
            if (and) {
                sb.append(" and "); //$NON-NLS-1$
            } else {
                sb.append("type = type + 1 where "); //$NON-NLS-1$
                and = true;
            }

            sb.append(MyBatis3FormattingUtilities
                    .getEscapedColumnName(introspectedColumn));
            sb.append(" = "); //$NON-NLS-1$
            sb.append(MyBatis3FormattingUtilities
                    .getParameterClause(introspectedColumn));
            answer.addElement(new TextElement(sb.toString()));
        }

//        if (context.getPlugins()
//                .sqlMapUpdateByPrimaryKeySelectiveElementGenerated(answer,
//                        introspectedTable)) {
//            parentElement.addElement(answer);
//        }
        //	end
        
    	element.addElement(answer);
//        return super.sqlMapDocumentGenerated(document, introspectedTable);
		return true;
    }*/

    
    /**  
     * Create separate Mapper interface , default path 
     * is DEFAULT_EXPAND_DAO_SUPER_CLASS, change in config file. 
     * 
     * @param introspectedTable
     * @return 
     */
    public List<GeneratedJavaFile> contextGenerateAdditionalJavaFiles(IntrospectedTable introspectedTable) {
        JavaFormatter javaFormatter = context.getJavaFormatter();
        List<GeneratedJavaFile> mapperJavaFiles = new ArrayList<GeneratedJavaFile>();
        for (GeneratedJavaFile javaFile : introspectedTable.getGeneratedJavaFiles()) {
            CompilationUnit unit = javaFile.getCompilationUnit();
            FullyQualifiedJavaType baseModelJavaType = unit.getType();

            String shortName = baseModelJavaType.getShortName();

            GeneratedJavaFile mapperJavafile = null;

            if (shortName.endsWith("Mapper")) { // 扩展Mapper
//                if (stringHasValue(expandDaoTargetPackage)) {
//                    Interface mapperInterface = new Interface(
//                            expandDaoTargetPackage + "." + shortName.replace("Mapper", "ExpandMapper"));
//                    mapperInterface.setVisibility(JavaVisibility.PUBLIC);
//                    mapperInterface.addJavaDocLine("/**");
//                    mapperInterface.addJavaDocLine(" * " + shortName + "扩展");
//                    mapperInterface.addJavaDocLine(" */");
//                    
//                    //插件单独创建的 mapper interface
//                    FullyQualifiedJavaType daoSuperType = new FullyQualifiedJavaType(expandDaoSuperClass);
//                    mapperInterface.addImportedType(daoSuperType);
//                    mapperInterface.addSuperInterface(daoSuperType);

//                    mapperJavafile = new GeneratedJavaFile(mapperInterface, daoTargetDir, javaFormatter);
//                    try {
//                        File mapperDir = shellCallback.getDirectory(daoTargetDir, daoTargetPackage);
//                        File mapperFile = new File(mapperDir, mapperJavafile.getFileName());
//                        // 文件不存在
//                        if (!mapperFile.exists()) {
//                            mapperJavaFiles.add(mapperJavafile);
//                        }
//                    } catch (ShellException e) {
//                        e.printStackTrace();
//                    }
//                }
            } else if (!shortName.endsWith("Example")) { // CRUD Mapper
//                Interface mapperInterface = new Interface(daoTargetPackage + "." + shortName + "Mapper");
//
//                mapperInterface.setVisibility(JavaVisibility.PUBLIC);
//                mapperInterface.addJavaDocLine("/**");
//                mapperInterface.addJavaDocLine(" * MyBatis Generator");
//                mapperInterface.addJavaDocLine(" */");
//
//                FullyQualifiedJavaType daoSuperType = new FullyQualifiedJavaType(daoSuperClass);
//                // 添加泛型支持
//                daoSuperType.addTypeArgument(baseModelJavaType);
//                mapperInterface.addImportedType(baseModelJavaType);
//                mapperInterface.addImportedType(daoSuperType);
//                mapperInterface.addSuperInterface(daoSuperType);
//
//                mapperJavafile = new GeneratedJavaFile(mapperInterface, daoTargetDir, javaFormatter);
//                mapperJavaFiles.add(mapperJavafile);

            } else if (!shortName.endsWith("Key")) { // CRUD Mapper
//                Interface mapperInterface = new Interface(daoTargetPackage + "." + shortName + "Mapper");
//
//                mapperInterface.setVisibility(JavaVisibility.PUBLIC);
//                mapperInterface.addJavaDocLine("/**");
//                mapperInterface.addJavaDocLine(" * 由MyBatis Generator工具自动生成，请不要手动修改");
//                mapperInterface.addJavaDocLine(" */");
//
//                FullyQualifiedJavaType daoSuperType = new FullyQualifiedJavaType(daoSuperClass);
//                // 添加泛型支持
//                daoSuperType.addTypeArgument(baseModelJavaType);
//                mapperInterface.addImportedType(baseModelJavaType);
//                mapperInterface.addImportedType(daoSuperType);
//                mapperInterface.addSuperInterface(daoSuperType);
//
//                mapperJavafile = new GeneratedJavaFile(mapperInterface, daoTargetDir, javaFormatter);
//                mapperJavaFiles.add(mapperJavafile);
            } 
        }
        return mapperJavaFiles;
    }
}