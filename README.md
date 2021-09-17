# Fast Auth Test Automation

## Requirements
  - Eclipse (https://www.eclipse.org/)
  - Appium (http://appium.io/)
  - Java (https://www.java.com/)
  - Android SDK (https://developer.android.com/studio#command-tools)

## Preparing the tests with Eclipse
  - Open the project with Eclipse. It's a maven project. (You can checkout the project using the comand line and then import the project as maven)
  - Add JUNIT 4 as a library to the project (Project configuration -> Java build path -> Libraries -> Add library -> select junit)
  - Configure your own settings to run the tests (See files under resources folder)
  
## Runing the test  
  - Launch Appium 
  - Configure JAVA_HOME and ANDROID_HOME by clicking on "Edit configuration" button
  - Start Appium on 127.0.0.1 : 4723 (Default port)
  - On Eclipse, right click on the project -> Run as -> Run configurations
  - Define -Ddevice / Dapi / Dusers as VM Arguments. Example:  -Ddevice=santi -Dapi=santi -Dusers=santi
  - Apply and run the tests
