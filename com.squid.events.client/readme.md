Events Tracking SDK for JAVA
============================

This is the documentation for using the Events Tracking client Java library. 
The client library provides you an easy way to integrate events tracking in your application.
You just have to focus on the business logic to define and push the events from your application code.
The library will handle all the work to send the events to the Tracker Server in an asynchrone & optimize way.

Getting started
---------------

1. getting the library

2. initialization

```
Config config = new Config();
config.setAppKey("application-key");
config.setSecretKey("secret-key");
//
EventTracker.initialize(config);
``

Tracking events
--------------

1. creating the EventModel

2. sending event



How does it works?
------------------

The client library uses an internal queue in order to make sending events from the application non-blocking and very fast.
You can define a maximum latency for the send() method.

The queue is bound in order to prevent the library to starve memory in case of a communication problem.
If the queue is full, new events won't be accepted - and probably lost forever. 
You can configure the maximun queue size, default is 10000.

The client library sends the event in batches to the Tracker Server. 
This background process is asynchrone, and it will flush the queue every second or when the batch is full.

Performances & Configuration
----------------------------

We tested the client library to support heavy load (1000 events/s) under sustained period of time (10 minutes).

If your application is expected to generate such load, you would need to optimize the library configuration:

1. consider extending the queue size; the queue size is used mostly to absorb pick of usage. 
Remember that once the queue is full, call to send() will either fail (if you enforce a timeout) or block (until there is some room in the queue).

2. Consider adding more flushers; you can have multiple background threads flushing the queue in parallel.
Each flusher will contact the Tracker Server independently, thus adding more network load from your application.
A single flusher can sustain about 500 events/s.
