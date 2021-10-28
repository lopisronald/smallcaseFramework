set "var=%CD%"
cd %var%
call TestExecution.bat
cd %var%
call GenerateReport.bat