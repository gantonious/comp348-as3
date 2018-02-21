# COMP 366 Assignment 3

I declare that this assignment is my own work and that all material previously written or published in any source by any other person has been duly acknowledged in the assignment. I have not submitted this work, or a significant part thereof, previously as part of any academic program. In submitting this assignment I give permission to copy it for assessment purposes only.

Submitted by: George Antonious (3364768)

## Building Instructions

Assuming JDK 1.8 is installed on the host machine, both parts can be built by doing:

```bash
make
```

## Part 1: PoemOfTheDay Server

### Usage

```bash
make runPodServer
```

### Design

I seperated the design for this server into 2 main parts. The protocol and the business logic on top of it (i.e. the PoD server). The protocol is specified and discussed in pod_spec.pdf. The state machine handling client interactions is also specified in that document.

At a high level though, the PoD server is essentially just sending text based messages back and forth between the client. I've opted for a simple protocol where messages are formated as so (the newline characters are shown for visualization purposes):

```
Body of the message.\n
\n
.\n
```

Whenever an empty line is followed by a period and another newline character then the message is considered terminated and everything before the empty line is considered the message body. This protocol is encapuslated in the `MessageService` class where it provides the following methods to send and receive messages:

```java
public Message readNextMessage();
public void writeMessage(Message message);
```

For the usecase of this asssignment `telnet` is utilized as the client. However, using the `MessageService` class another client could be implemented in java without worrying about the underlying protocol.

## Part 2: RmiPrimeCalculator Server

### Usage

Before the server or client are started the RMI Registry should be launched this can be done by doing:

```bash
make runRegistry
```

The server can be then started by doing:

```bash
make runPrimeCalculatorServer
```

The client can be run by doing:

```bash
make runPrimeCalculatorClient
```

The client will prompt the user for a lower and upper bound then return the highest prime number in between.

### Design