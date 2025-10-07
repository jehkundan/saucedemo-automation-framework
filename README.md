🧪 SauceDemo Automation Framework

This is a Selenium Test Automation Framework built using Java, TestNG, Maven, Allure Reports, Git, and Jenkins.
The framework automates key user flows of SauceDemo
:

🔐 Login

🛒 Add Product to Cart

💳 Checkout

🚪 Logout

It follows the Page Object Model (POM) design for better structure and reusability.

🧰 Tech Stack

Language: Java 17+

Automation: Selenium 4

Test Runner: TestNG

Build Tool: Maven

Reporting: Allure Reports

Driver: WebDriverManager

CI/CD: Jenkins

Version Control: Git

📂 Project Structure
saucedemo-fw
├─ pom.xml
├─ testng.xml
├─ Jenkinsfile
├─ README.md
├─ src
│  └─ test
│     ├─ java
│     │  ├─ base
│     │  ├─ driver
│     │  ├─ listeners
│     │  ├─ pages
│     │  ├─ tests
│     │  └─ utils
│     └─ resources
│        ├─ config.properties
│        └─ allure.properties

🧭 Setup
✅ Prerequisites

Java 17+

Maven

Git

(Optional) Allure CLI for viewing reports

IntelliJ IDEA (Recommended)

📥 Clone & Install
git clone https://github.com/<your-username>/saucedemo-fw.git
cd saucedemo-fw
mvn clean install

▶️ Running Tests
Run all tests (default browser = Chrome)
mvn clean test

Run in headless mode
mvn clean test -Dheadless=true

Run with a different browser
mvn clean test -Dbrowser=firefox

📊 Allure Report

After the test run:

allure serve allure-results


This will open a detailed HTML report with test steps, screenshots, and statuses.

🤖 Jenkins

Project includes a Jenkinsfile for CI/CD.

Configure Maven + Allure plugin in Jenkins.

Create a pipeline job → point to your GitHub repo.

Jenkins will run mvn clean test and publish Allure reports.

🧪 Sample Test
@Test
public void testValidLogin() {
new LoginPage(driver).login("standard_user", "secret_sauce");
Assert.assertTrue(driver.getCurrentUrl().contains("inventory"));
}

🙌 Author

Kundan Kumar
🔗 Linked:(https://www.linkedin.com/in/kundanaryakd/)In: 
| 💻 GitHub:https://github.com/jehkundan