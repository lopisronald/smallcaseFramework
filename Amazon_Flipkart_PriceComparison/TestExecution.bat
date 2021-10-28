set "var=%CD%"
rmdir /Q /S "%var%\allure-results"
cd "%CD%\MavenOS\apache-maven-3.6.3\bin"
mvn -f %var%\pom.xml clean test
exit 0