This file was created to be easy for anyone to setup the project and test it properly.
For further testing on the application use the port 8085 and the context /waes

Setup:

1) Download the project which includes src folder and pom.xml file
2) On Eclipse import the project as maven project
3) Run maven install on the project to download all required libraries
4) To make sure that everything is properly set, run the project as Java Application

Testing:
While implementing and testing I have used Postman extension for Chrome, once it make easy to test and see the results

To add a value to left use:
http://localhost:8085/waes/v1/diff/{id}/left?value=<value>
Example:
http://localhost:8085/waes/v1/diff/1/left?value=RnJ1aXRzOg0KDQpiYW5hbmENCmFwcGxlDQpwaW5lYXBwbGUNCm9yYW5nZQ0KZ3JhcGVmcnVpdA==

To add a value to right use:
http://localhost:8085/waes/v1/diff/{id}/right?value=<value>
Example:
http://localhost:8085/waes/v1/diff/1/right?value=RnJ1aXRzOg0KDQpiYW5hYWFuYQ0KYXBwZQ0KcGluZWFwcGxlDQpvcm9uZ2UNCmdyYXBlZnJ1aXQ=

To check which value has been set use:
http://localhost:8085/waes/v1/diff/{id}/get/{left/right}
Example:
http://localhost:8085/waes/v1/diff/1/get/left

To retrieve the difference between the files use:
http://localhost:8085/waes/v1/diff/{id}
Example:
http://localhost:8085/waes/v1/diff/1