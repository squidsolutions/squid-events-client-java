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
// create the config
Config config = new Config();
config.setAppKey("application-key");
config.setSecretKey("secret-key");

// initialize the tracker
EventTracker.initialize(config);
```
You can use the default configuration for all technical settings. It is production ready.
You must specify your application key and secret key. If not provided the initialize() method wil throws an IllegalStateException.


Tracking events
---------------

1. creating the EventModel

This is an exemple of a retrieval event, providing extensive article definition:

```
ArticleModel article = new ArticleModel()
.withContentType("article")
.withDiscipline("science")
.withJournal("nature")
// more properties here...
;
EventModel event = new RetrievalEvent()
.withContentOwnerID("myUniversity")
.withContentType("article")
.withDisplayFormat("PDF")
.withEntitlement("myUnivertsity")
.withContentReferenceArticle(article)// reference the article
.withClientIP("127.0.0.1")
.withSessionID("1234")
.withUserID("sergio")
.withPageViewURL("http://myapp/contentPage")
.withServerIP("127.0.0.1");
```

2. sending event

You can easily send the previous event.

```
// send the event and return
EventTracker.send(event,10);
```

Note that the EventTracker will not make a internal copy of the event to avoid unnecessary memory allocation.
So you must not re-use the same EventModel object because this will lead to inconsistency in the flushing queue.

Flushing the queue
------------------

The queue will be automatically flushed when you terminate the client.
This is ideally done in the ServletContextListener.contextDestroyed() method, where you can simply call the EventTracker shutdown method:

```
public class ServletContextListenerImpl implements ServletContextListener {

    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
    	EventTracker.shutdown();
    }

}
```


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

2. it is possible to adjust the send() method timeout setting. By default the timeout is 10ms. 
By setting the timeout to 0ms, the send() will fail straight if the queue is full.

3. Consider adding more flushers; you can have multiple background threads flushing the queue in parallel.
Each flusher will contact the Tracker Server independently, thus adding more network load from your application.
A single flusher can sustain about 500 events/s.
