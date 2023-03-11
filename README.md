# Smart Stream Recommendation System

## Explanation of Design Pattern Implementation

### FACTORY

<ul>
  <li>I considered two types of users who use the application, which can be seen as two entities (implementing the Entity interface).</li>
  <li>I used the Factory pattern because it separates the object construction from any potential use of it.</li>
  <li>Factory provides a way to abstract the process of creating entities and is much easier to manipulate and extend in the future, in case I choose to add another type of entity that uses the application.</li>
  <li>Although they have different attributes, I considered that the two essential attributes that identify and characterize them throughout the use of the application are: ID and name.</li>
  <li>I did not use Builder because, when creating them, all attributes must be completed (there is no attribute for the user/streamer that can be omitted).</li>
</ul>

### BUILDER

<ul>
  <li>I chose to use Builder for streams because it separates the construction of a complex object from its representation so that the same constructor can create different representations. By this, I mean that for each type of stream, I could have added new features, for example, for AUDIOBOOK, I could have added the language in which the audiobook is narrated, something that is not necessarily important for other streams, and for SONG, I could have added the album name or whether it's 8D/simple.</li>
</ul>

### COMMAND

<ul>
  <li>I used it to encapsulate actions in objects. Each command is contained in a separate class, and depending on the keyword in the command, the invoker invokes the appropriate command. This makes it easy to add new commands to the application.</li>
</ul>

### FACADE

<ul>
  <li>Facade is a design pattern that offers a way to access complex systems through a simple API. In this case, using a Facade for stream operations can be useful because it can hide the complexity behind stream operations and provide a single point of access for all stream operations. This can make the code more intuitive and easier to maintain since all stream operations are grouped together in one place, rather than being scattered throughout the code.</li>
</ul>

### SINGLETON

<ul>
  <li>I chose to use the Singleton pattern for the class that holds the algorithm for calculating surprise and recommended streams to ensure that there is only one instance of this class throughout the application. This allows better resource management and avoids creating multiple instances of this class, which could lead to excessive memory consumption and other performance issues. The Singleton pattern ensures that there is only one instance of the class, and all calls to this class are made through the same instance, thus ensuring better resource management and avoiding problems associated with creating multiple instances of the same class.</li>
</ul>

## Project Requirements
[Proiect POO - 2023.pdf](https://github.com/AndreeaG22/Smart-Stream-Recommendation-System/files/10948098/Proiect.POO.-.2023.1.pdf)
