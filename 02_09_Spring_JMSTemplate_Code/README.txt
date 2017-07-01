To compile and run the coding examples in this chapter, please setup the following:

1. The samples assume ActiveMQ is running on tcp://localhost:61616. If you are running ActiveMQ 
   on a different host and/or port, you will need to change the connection string in each class
   file on the connection factory.
   
2. You will need to copy the following jar files from the ACTIVEMQ_HOME/lib and 
   ACTIVEMQ_HOME/lib/optional directories into your project lib directory and 
   reference those jar files in your build path:
   
   activemq-client-5.9.1.jar
   commons-logging.jar
   geronimo-j2ee-management_1.1_spec-1.0.1.jar
   hawtbuf-1.9.jar
   jms-1.1.jar
   log4j-1.2.17.jar
   slf4j-api-1.7.5.jar
   slf4j-log4j12-1.7.5.jar
   
3. You will need to copy the following jar files from your Spring Framework lib directory and 
   reference those jar files in your build path (any spring framework version will work):
   
   spring-beans-4.0.6.RELEASE.jar
   spring-context-4.0.6.RELEASE.jar
   spring-context-support-4.0.6.RELEASE.jar
   spring-core-4.0.6.RELEASE.jar
   spring-expression-4.0.6.RELEASE.jar
   spring-jms-4.0.6.RELEASE.jar
   spring-tx-4.0.6.RELEASE.jar
   
4. All classes are run as a simple main() method, so you can create scripts or simply launch each
   one as Run As...| Java Application.
   
      