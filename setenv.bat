rem
rem Please set you env for JDK,ANT and ClassPath, if FTPing Netcomponents.jar required in classpath
rem

@echo on
rem path=%PATH%;c:\jdk1.3.1_06\bin
rem set JAVA_HOME=c:\jdk1.3.1_06
rem set ANT_HOME=c:\ant
rem set classpath=.;c:\java\jdk1.3.1_06\lib\tools.jar;c:\ant\lib\ant.jar;c:\ant\lib\optional.jar;C:\Infrastructure\NetComponents.jar;C:\Infrastructure\report11_pro.jar;C:\Infrastructure\report12_pro.jar

set ANT_HOME=C:\eclipse\plugins\org.apache.ant_1.7.0.v200803061910
set JAVA_HOME=C:\jboss_ING\app\jvm\jdk1.6.0_07
set classpath=.;C:\jboss_ING\app\jvm\jdk1.6.0_07\lib\tools.jar;C:\eclipse\plugins\org.apache.ant_1.7.0.v200803061910\lib\ant.jar;C:\eclipse\plugins\org.apache.ant_1.7.0.v200803061910\lib\optional.jar;C:\Infrastructure\NetComponents.jar;C:\Infrastructure\report11_pro.jar;C:\Infrastructure\report12_pro.jar;C:\Infrastructure\infrastructure.xml;
set PATH=%PATH%;%ANT_HOME%/bin;%JAVA_HOME%/bin;
echo Environment has been configured
echo "Configured"
