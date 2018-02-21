TASK_PACKAGE = src/part2/task
TASK_JAR = task.jar
SERVER_PACKAGE = src/part2/server
CLIENT_PACKAGE = src/part2/client
OUTPUT_DIR = ./out/production/as3

all: server client

server: task.jar output
	@echo "[=====Building Server=====]"
	javac -cp $(TASK_JAR) -d $(OUTPUT_DIR) $(SERVER_PACKAGE)/RemoteTaskExecutor.java

client: task.jar output
	@echo "[=====Building Client=====]"
	javac -cp $(TASK_JAR) -d $(OUTPUT_DIR) $(CLIENT_PACKAGE)/PrimeCalculatorTask.java $(CLIENT_PACKAGE)/ComputePrimeNumberClient.java

output:
	mkdir -p $(OUTPUT_DIR)

task.jar:
	@echo "[=====Packaging Task as JAR=====]"
	javac $(TASK_PACKAGE)/*.java
	cd src && jar cvf $(TASK_JAR) part2/task/*.class
	mv src/$(TASK_JAR) .


runRegistry:
	@echo "[=====Running RMI Registry=====]"
	rmiregistry -J-Djava.rmi.server.codebase=file:./task.jar

runServer: server
	@echo "[=====Running Server=====]"
	java -cp "task.jar:out/production/as3" \
		-Djava.security.policy=security.policy \
		-Djava.rmi.server.codebase=file:./task.jar \
		-Djava.rmi.server.hostname=localhost \
		part2.server.RemoteTaskExecutor

runClient: client
	@echo "[=====Running Client=====]"
	java -cp "task.jar:out/production/as3" -Djava.security.policy=security.policy part2.client.ComputePrimeNumberClient localhost

clean:
	rm -f *.jar
	find . -name "*.class" -type f -delete
	rm -rf out
