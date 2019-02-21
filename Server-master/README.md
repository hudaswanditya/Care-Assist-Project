# Connecting CareAssist with Couchbase Mobile [Lite - SyncGate - Server]

## General Links:
- [Couchbase Mobile Download](https://www.couchbase.com/downloads) including Couchbase Server and Couchbase Sync-Gateway
- [Developer Guides](https://developer.couchbase.com/documentation/mobile/1.4/guides/index.html)

## Setting up Couchbase Lite
Couchbase Lite is an embedded NoSQL database that runs on the device. The local database is initialized in `de.careassist.app.DataManager` Depending if you use an emulator or an android device you must connect to the right URL (see below). For better Understanding see this [Android Couchbase Lite Tutorial](https://www.raywenderlich.com/134752/couchbase-tutorial-android)

### Sync Gateway URL
- 10.0.2.2:4984/ for Android stock emulators
- 10.0.3.2:4984/ for Genymotion emulators
- http://94.177.230.91:4984/ for sync-gateway on our server

## How to run Couchbase Sync-Gateway
An Internet-facing synchronization server that securely syncs data between device and cloud.

Before running Sync Gateway make sure you have installed Couchbase Server. Run Couchbase Server and make sure there is a bucket named ***care_assist***.

To start sync-gateway do the following:

- make sure you download the correct version of sync-gateway for you OS
- open the terminal/command line tool
- with OSX for example navigate to `Server/couchbase-sync-gateway-macOSX/bin`
- run `./sync_gateway ../careassist-couchbase.json`

Sync-Gateway is now synchronizing with the connected Couchbase Lite database from the Android application. Last tested with **version 1.4.1**.

To stop sync-gateway use <kbd>CTRL</kbd> + <kbd>C</kbd> at any time.

Here are a list of example Sync Gateway configurations:

File  | Description
------------- | -------------
basic-walrus-bucket.json  | Start with this example.  It is a minimal config that uses the Walrus in memory bucket as a backing store.
basic-walrus-persisted-bucket.json  | Uses the Walrus in memory bucket, with regular snapshots persisted to disk in the current directory.
basic-couchbase-bucket.json  | Uses a Couchbase Server bucket as a backing store.
basic-sync-function.json  | Uses a custom Sync Function.
users-roles.json  | Statically define users and roles.  (They can also be defined via the REST API)
read-write-timeouts.json  | Demonstrates how to set timeouts on reads/writes.
cors.json  | Enable CORS support.
config-server.json  | Use an external configuration server to support dynamic configuration, such as the ability to add databases on the fly.
democlusterconfig.json | This the configuration used by the demo cluster Sync Gateway instance, which example apps such as TodoLite and GrocerySync connect to by default.

### Disabling logging

By default these examples have all log levels enabled.  The idea behind this is to make debugging easier.

If the logs become too noisy, please replace the log section with:

```
"log": ["HTTP+"]
```

More log levels are documented in the [Sync Gateway documentation](http://developer.couchbase.com/mobile/develop/guides/sync-gateway/)

### How these examples were formatted

```
$ npm install -g underscore-cli
$ cat basic-walrus-bucket.json | underscore pretty --color
```

## Login Admin Console Couchbase Server
- http://94.177.230.91:8091/
- Username: Administrator
- Password: CareAssist

## Database Listener
component that provides an HTTP entry point to the database.This component is optional and is often used for the following reasons:

- **Debugging**: In development, it’s much faster to use the HTTP API to inspect the database than to set breakpoints and examine query results.
- **Peer-to-peer**: Other databases (often running on different devices) can persist data to the listener directly without the need for a server to act as the intermediary. This aspect of Couchbase Mobile isn’t covered in this Couchbase tutorial.
- **Hybrid development**: Used to access the database from JavaScript frameworks within a WebView.

To access the database from a browser or a command-line utility such as curl, you must enable port forwarding for that port using ADB. From the command line, run the following:
```
adb forward tcp:5984 tcp:5984
```
[http://localhost:5984/care_assist/](http://localhost:5984/care_assist/)
