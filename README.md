# spring-boot-demo-jms

This example makes use of spring jms powered by spring boot. Sample code for how to send/receive messages from/to topic/queue. 

Step 1: Create a scheduled tasks that will run at scheduled intervals. For e.g., TransactionAlert is created and posted. 

Step 2: Send the Transaction Alerts to Topic

Step 3: Create 2 subscribers that listens the topic and fan out the message to two different queues for email and sms notifications

Step 4: Listen to the Queues

Step 5: Send emails
