# COMP 348 Assignment 3

I declare that this assignment is my own work and that all material previously written or published in any source by any other person has been duly acknowledged in the assignment. I have not submitted this work, or a significant part thereof, previously as part of any academic program. In submitting this assignment I give permission to copy it for assessment purposes only.

Submitted by: George Antonious (3364768)

## Building Instructions

Assuming JDK 1.8 is installed on the host machine, both parts can be built by doing:

```bash
make
```

## Part 1: PoemOfTheDay Server

### Usage

To launch the server do:

```bash
make runPodServer POEMS_FILE=[path_to_poems]
```

The poems file should contain all the poems seperated by a `===` sequence. Within a poem the title and content should be sperated using a `---` sequence. The following is an example of a properly constructed poems file.

```
Title of the first Poem
---
Content
of the first Poem.
===
Title of the second Poem
---
Content of the second Poem.
===
```

To start a session with the server do:

```bash
telnet localhost 1822
```

The message format is discussed in the design section below but to mark the end of a message add an empty line followed by a line with a period on it followed by a newline character. This is an example of a `telnet` sessions with the PoD server.

```
telnet: Trying ::1...
telnet: Connected to localhost.
telnet: Escape character is '^]'.
server: Welcome to the Poem of The Day Server
server: Select one of the following poems:
server: 1. The Programmers Love by The Internet
server: 2. Christmas Eve by Ella Higginson
server: 3. Paris by Willa Cather
server:
server: .
client: 1
client:
client: .
server: Title: The Programmers Love by The Internet
server: ----------------
server: Roses are Red,
server: Violets are Blue.
server: Unexpected '}',
server: On line 32.
server: ----------------
server: Thanks for reading, visit again soon!
server:
server: .
telnet: Connection closed by foreign host.
```

### Design

I seperated the design for this server into 2 main parts. The protocol and the business logic on top of it (i.e. the PoD server). The protocol is specified and discussed in `pod_spec.pdf` found in the `docs\` directory. The state machine handling client interactions is also specified in that document.

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

When ever a new client connection is established the client statemachine is activated for that client on a seperate thread. The state machine uses the read and write methods discussed above to communicate with the client. The state machine is discussed in greater detail in `pod_spec.pdf`.

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

The structure of this assignment followed a similar structure to the PI sample provided by oracle (https://docs.oracle.com/javase/tutorial/rmi/overview.html). There is a seperate `Task` package that provides a `TaskExecutor` interface and a `Task` interface. The server class implements the `TaskExecutor` and accepts `Task` implementations from remote clients.

For this part a `Task` implementation was created that computes the largest prime number within a given range and throws and exception if one cannot be found. The algorithm itself is not too complex. It iterates through all the numbers within the range in order and updates the output when a new prime is found.

To determine if a number is prime we first check if it's `2` if so then we just return true. After we check if the number is even, if so we return false right away. Then we iterate through all the odd numbers (except for one) until we reach half of the number we're checking, if the number is not divisible by any of those numbers then we can say it's even.