First add this to the top of your ".jsp" file:

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
It will still give syntax error but you can fix that by adding "javax.servlet:jstl:1.2" (without quotes) as a module dependency. To do that, follow these steps:

Click your project name and press "F4" to bring up the module settings dialog.
Then go to the "dependencies" tab in the "modules" section.
Click the green "+" icon --> library --> From Maven.
Search for javax.servlet:jstl:1.2 in the search bar and press OK and it will download and add the above mentioned library as a module.
Now you should not have any kind of syntax error.