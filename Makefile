TASK_JAR = task.jar
SERVER_PACKAGE = src/part2/server
CLIENT_PACKAGE = src/part2/client
TASK_PACKAGE = src/part2/task
POEM_PACKAGE = src/part1/
OUTPUT_DIR = ./out/production/as3
SRC_DIR = ./src

POEMS_FILE =

all: sources

sources: task output
	@echo "[=====Building Source=====]"
	javac -cp $(OUTPUT_DIR)/$(TASK_JAR) \
		-d $(OUTPUT_DIR) \
		-sourcepath $(SRC_DIR) \
		$(POEM_PACKAGE)/Main.java \
		$(CLIENT_PACKAGE)/ComputePrimeNumberClient.java \
		$(SERVER_PACKAGE)/RemoteTaskExecutor.java

output:
	@echo "[=====Preparing Output Directory=====]"
	mkdir -p $(OUTPUT_DIR)

task: output
	@echo "[=====Packaging Task as JAR=====]"
	javac -d $(OUTPUT_DIR) $(TASK_PACKAGE)/*.java
	cd $(OUTPUT_DIR) && jar cvf $(TASK_JAR) part2/task/*.class

runPodServer: sources
	@echo "[=====Running PoD Server=====]"
	java -cp out/production/as3 part1.Main $(POEMS_FILE)

runRegistry:
	@echo "[=====Running RMI Registry=====]"
	rmiregistry -J-Djava.rmi.server.codebase=file:$(OUTPUT_DIR)/$(TASK_JAR)

runPrimeCalculatorServer: sources
	@echo "[=====Running PrimeCalculatorServer=====]"
	java -cp "task.jar:out/production/as3" \
		-Djava.security.policy=security.policy \
		-Djava.rmi.server.codebase=file:$(OUTPUT_DIR)/$(TASK_JAR) \
		-Djava.rmi.server.hostname=localhost \
		part2.server.RemoteTaskExecutor

runPrimeCalculatorClient: sources
	@echo "[=====Running PrimeCalculatorClient=====]"
	java -cp "task.jar:out/production/as3" \
		-Djava.security.policy=security.policy \
		part2.client.ComputePrimeNumberClient localhost

clean:
	@echo "[=====Cleaning Output=====]"
	find . -name "*.jar" -type f -delete
	find . -name "*.class" -type f -delete
	rm -rf out
