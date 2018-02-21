TASK_PACKAGE = src/part2/task
TASK_JAR = task.jar
SERVER_PACKAGE = src/part2/server
CLIENT_PACKAGE = src/part2/client
OUTPUT_DIR = ./out/production/as3

all: server client

server: task output
	@echo "[=====Building Server=====]"
	javac -cp $(OUTPUT_DIR)/$(TASK_JAR) -d $(OUTPUT_DIR) $(SERVER_PACKAGE)/RemoteTaskExecutor.java

client: task output
	@echo "[=====Building Client=====]"
	javac -cp $(OUTPUT_DIR)/$(TASK_JAR) -d $(OUTPUT_DIR) $(CLIENT_PACKAGE)/PrimeCalculatorTask.java $(CLIENT_PACKAGE)/ComputePrimeNumberClient.java

output:
	@echo "[=====Preparing Output Directory=====]"
	mkdir -p $(OUTPUT_DIR)

task: output
	@echo "[=====Packaging Task as JAR=====]"
	javac -d $(OUTPUT_DIR) $(TASK_PACKAGE)/*.java
	cd $(OUTPUT_DIR) && jar cvf $(TASK_JAR) part2/task/*.class

runRegistry:
	@echo "[=====Running RMI Registry=====]"
	rmiregistry -J-Djava.rmi.server.codebase=file:$(OUTPUT_DIR)/$(TASK_JAR)

runServer: server
	@echo "[=====Running Server=====]"
	java -cp "task.jar:out/production/as3" \
		-Djava.security.policy=security.policy \
		-Djava.rmi.server.codebase=file:$(OUTPUT_DIR)/$(TASK_JAR) \
		-Djava.rmi.server.hostname=localhost \
		part2.server.RemoteTaskExecutor

runClient: client
	@echo "[=====Running Client=====]"
	java -cp "task.jar:out/production/as3" \
		-Djava.security.policy=security.policy \
		part2.client.ComputePrimeNumberClient localhost

clean:
	@echo "[=====Cleaning Output=====]"
	find . -name "*.jar" -type f -delete
	find . -name "*.class" -type f -delete
	rm -rf out
