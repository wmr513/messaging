To compile and run the coding examples in this chapter, please setup the following:

1. The samples assume ActiveMQ is running on tcp://localhost:61616. If you are running ActiveMQ 
   on a different host and/or port, you will need to change the connection string in each class
   file on the connection factory.

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

4. Make a backup of your jetty.xml file in your activemq distribution and copy the one from
   this distribution to your activemq_home/conf directory
   
5. Copy the contents of the webapps directory containing the jmsrest directory to the activemq_home/webapps directory    
   
      