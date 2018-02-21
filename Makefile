TASK_PACKAGE = src/part2/task
TASK_JAR = task.jar
SERVER_PACKAGE = src/part2/server
CLIENT_PACKAGE = src/part2/client
OUTPUT_DIR = ./out/production/as3

all: server client

server: task output
	javac -cp $(TASK_JAR) -d $(OUTPUT_DIR) $(SERVER_PACKAGE)/RemoteTaskExecutor.java

client: task output
	javac -cp $(TASK_JAR) -d $(OUTPUT_DIR) $(CLIENT_PACKAGE)/PrimeCalculatorTask.java $(CLIENT_PACKAGE)/ComputePrimeNumberClient.java

output:
	mkdir -p $(OUTPUT_DIR)

task:
	javac $(TASK_PACKAGE)/*.java
	cd src && jar cvf $(TASK_JAR) part2/task/*.class
	mv src/$(TASK_JAR) .

runServer:
	java -cp "task.jar:out/production/as3" -Djava.security.policy=security.policy part2.server.RemoteTaskExecutor

runClient:
	java -cp "task.jar:out/production/as3" -Djava.security.policy=security.policy part2.client.ComputePrimeNumberClient localhost

clean:
	rm -f *.jar
	find . -name "*.class" -type f -delete
	rm -rf out
