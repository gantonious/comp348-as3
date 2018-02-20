TASK_PACKAGE = src/part2/task
TASK_JAR = task.jar
SERVER_PACKAGE = src/part2/server
CLIENT_PACKAGE = src/part2/client

all: server client

server: task
	javac -cp $(TASK_JAR) $(SERVER_PACKAGE)/*.java

client: task
	javac -cp $(TASK_JAR) $(CLIENT_PACKAGE)/*.java

task:
	javac $(TASK_PACKAGE)/*.java
	jar cvf $(TASK_JAR) $(TASK_PACKAGE)/*.class

clean:
	rm -f *.jar
	find . -name "*.class" -type f -delete
