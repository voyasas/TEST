@echo on
call setenv.bat
echo trying to call ant
call C:\eclipse\plugins\org.apache.ant_1.7.0.v200803061910\bin\ant.bat -logfile build.log -verbose  %1 -buildfile Build.xml
