TASK_PACKAGE = src/part2/task
TASK_JAR = task.jar
SERVER_PACKAGE = src/part2/server
CLIENT_PACKAGE = src/part2/client

all: server client

server: task
	javac -cp $(TASK_JAR) $(SERVER_PACKAGE)/RemoteTaskExecutor.java

client: task
	javac -cp $(TASK_JAR) -d . $(CLIENT_PACKAGE)/PrimeCalculatorTask.java $(CLIENT_PACKAGE)/ComputePrimeNumberClient.java

task:
	javac $(TASK_PACKAGE)/*.java
	cd src && jar cvf $(TASK_JAR) part2/task/*.class
	mv src/$(TASK_JAR) .

clean:
	rm -f *.jar
	find . -name "*.class" -type f -delete
