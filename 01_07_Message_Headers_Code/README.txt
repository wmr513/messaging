To compile and run the coding examples in this chapter, please setup the following:

1. FOR JMS 1.1: The samples assume ActiveMQ is running on tcp://localhost:61616. If you are running ActiveMQ 
   on a different host and/or port, you will need to change the connection string in each class
   file on the connection factory.

2. FOR JMS 2.0: The samples assume OpenMQ 5.0 is running on mq://localhost:7676. If you are running OpenMQ 
   on a different host and/or port, you will need to add the following line of code after 
   the connection factory is created and replace <hostname> and <port>:
   
   cf.setProperty(ConnectionConfiguration.imqAddressList, "mq://<hostname>:<port>");
   
   example:
       cf.setProperty(ConnectionConfiguration.imqAddressList, "mq://saturn.dev:1234");
   
   
3. You will need to copy the following jar files from the ACTIVEMQ_HOME/lib and 
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

4. If you are running the JMS 2.0 code, make sure you have Java 7, and copy the following jar 
   files from the IMQ_HOME/lib directory into your project lib directory and reference those 
   jar files in your build path:

   imq.jar   
   jms.jar (remove jms-1.1.jar and replace with this jar)
   
5. All classes are run as a simple main() method, so you can create scripts or simply launch each
   one as Run As...| Java Application.
   
      