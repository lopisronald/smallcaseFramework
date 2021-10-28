set "var=%CD%"
xcopy /f /y %CD%\AllureConfigurationFiles\* %var%\allure-results
cd "%CD%\AllureCommandLine\allure-2.13.6\bin"
allure serve "%var%\allure-results"