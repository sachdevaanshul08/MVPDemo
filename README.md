# About The Project

This Android App makes use of [NewYork Times-api] to fetch the most popular articles from last 7 days and displays it on the screen by leverging the power of `MVP Architecture pattern`.

This project desmonstrate the MVP pattern in an easiest way possible and includes the static code analyser tool to make sure that the code adhere to the industry standards.
Unit test and UI test cases are also covered in this project.
Jacoco library along with Sonarqube has been used for continuous inspection of code quality to perform automatic reviews with static analysis of code to detect bugs, code smells, and security vulnerabilities.

How To Run This Program :-

1. Download this projec on your system in zip format.
2. Unzip it.
3. [Download Android Studio] and install it on your system.
4. Import the app/project you have downloaed in earlier step.
4. Run it either on Emulator or Mobile device.(Refer to [How to run Android App])
5. Congrats! You have successfully executed this app on your machine.

### Just So You Know 
We have added three scripts (under config folder) in this project which cater to different needs :-
1. `analyser.gradle` - This contains lint configuration as of now and if you want, you can add CheckStyle, PMD, findbugs configuration here as well. (Oh by the way if there will be even single error from lint tool, our program won't compile, we have restricted this in our file, to know more open analyser.gradle)
2. `jacoco.gradle` - This contains the jacoco configuration to tests coverage calculation.
3. `sonarqube.gradle`- As the name signifies, it contains sonarqube related configuraiton.

### How to run these :
1. Open terminal/CMD from your system.
2. Move to root folder of this project from console.
3. Make your emulator or device is connected to system with "usb debugging on" (Refer to [How to run Android App])
4. Type `./gradlew clean connectedAndroidTest test jacocoTestReport`

    
     What are these commands :-
     
    `clean` - build directories.
    
    `connectedAndroidTest`- start UI tests on connected device.
    
    `test`- start Unit tests.
    
    `jacocoTestReport` - transform code coverage report to jacoco format - this command internal depends on 'createDebugCoverageReport' which generates the coverate report.


Now, once you run this you will be able to see multiple folders under `/app/build/` there you will find your lint report and conde coverage report(s) in HTML and XML format.
To see lint report open "app/build/reports/lint/lint-result.html"
To see Jacoco coverage report open - "app/build/reports/jacoco/jacocoTestReport/jacocoTestReport.xml" 

Now if you want to see it on BEAUTIFUL interface, along with many more customizations and utilities than run sonarqube.

### prerequisites to run sonarqube 
You can find sonarqube [here]
Download and follow the steps to configure the same on your local machine. It won't take more than 2 minutes ofcourse if everything goes well.
Somehow if you find some difficulty then you can search it on Google as there are lot of information avaiable on Google about this.

next step, open http://localhost:9000
If it's opening, then you have successfully installed sonarqube on your machine.

### Next step(s) :
Make your emulator or device is connected to system with "usb debugging on" (Refer to [How to run Android App])

Now, recall the `how to run these` section of this file.
We will execute the following command from the console by moving to the root of your folder :-
`./gradlew clean connectedAndroidTest test  jacocoTestReport sonarqube`

`sonarqube` - It will analys the matrix reported by jacoco from the previous command and present to you on http://localhost:9000/. Just refresh this page to see the matrix with beautiful interface.

That's not it, you can do much more with sonarqube by adding more plugins.


You can read more about it [Official Sonarqube Website]
About Jacoco as well [Jacoco official website]




[NewYork Times-api]:<https://developer.nytimes.com/apis>
[Download Android Studio]:<https://developer.android.com/studio>
[How to run Android App]:<https://developer.android.com/training/basics/firstapp/running-app>
[here]:<https://docs.sonarqube.org/latest/setup/get-started-2-minutes/>
[Official Sonarqube Website]:<https://docs.sonarqube.org/latest/>
[Jacoco official website]:<https://www.eclemma.org/jacoco/>