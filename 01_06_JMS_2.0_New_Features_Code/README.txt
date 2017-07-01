To compile and run the coding examples in this chapter, please setup the following:

1. The samples assume OpenMQ 5.0 is running on mq://localhost:7676. If you are running OpenMQ 
   on a different host and/or port, you will need to add the following line of code after 
   the connection factory is created and replace <hostname> and <port>:
   
   cf.setProperty(ConnectionConfiguration.imqAddressList, "mq://<hostname>:<port>");
   
   example:
       cf.setProperty(ConnectionConfiguration.imqAddressList, "mq://saturn.dev:1234");
   
2. You will need to copy the following jar files from the IMQ_HOME/lib directory into 
   your project lib directory and reference those jar files in your build path:

   imq.jar   
   jms.jar
   
3. All classes are run as a simple main() method, so you can create scripts or simply launch each
   one as Run As...| Java Application.
   
      