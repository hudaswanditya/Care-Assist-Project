# Care Assist 2.0

A student project from HTW Berlin (SoSe 17 – Mobile Computing in der ambulanten Pflege) in cooperation with David Koschnick. This system is designed to support ambulant care services through a distributed multi-app architecture.

## Architecture Overview
Care Assist 2.0 consists of three main components:

1. **Admin Dashboard (Web Application)** – A management interface for caregivers.
2. **Mobile App (Android)** – Used by caregivers to access patient information and update records.
3. **Couchbase Mobile Sync** – Ensures seamless data synchronization across devices and the cloud.

Each component interacts through a centralized Couchbase database, with Sync Gateway enabling real-time updates.

---

## Running the System

### 1. Running the Backend (Care Assist API)
Ensure the API is running before starting other components:
```sh
cd care_assist_api
node app.js
```

### 2. Running the Admin Dashboard
```sh
cd care_assist_admin_client
ng serve
```
The dashboard runs at `http://localhost:4200`.

**Dependencies:**
- Node.js
- Angular CLI
- Couchbase Server

### 3. Running the Mobile App
An Android app that connects to the Couchbase database via Sync Gateway. The database is initialized in `de.careassist.app.DataManager`.

### 4. Running Couchbase Sync-Gateway
Sync Gateway ensures secure synchronization between the mobile app and the backend.

#### Steps:
1. Start Couchbase Server and ensure a bucket named `care_assist` exists.
2. Start Sync Gateway:
   ```sh
   cd Server/couchbase-sync-gateway-macOSX/bin
   ./sync_gateway ../careassist-couchbase.json
   ```
3. Stop Sync Gateway anytime with `CTRL + C`.

#### Sync Gateway URLs:
- `10.0.2.2:4984/` for Android emulators
- `10.0.3.2:4984/` for Genymotion
- `http://94.177.230.91:4984/` for remote server

---

## Couchbase Mobile Sync & Database Access

Couchbase Lite (embedded NoSQL database) syncs with Couchbase Server via Sync Gateway. This enables:
- Offline-first capabilities
- Automatic conflict resolution
- Secure data access

To debug or access the database:
```sh
adb forward tcp:5984 tcp:5984
http://localhost:5984/care_assist/
```

### Admin Console:
- URL: [Couchbase Server](http://94.177.230.91:8091/)
- Credentials: `Administrator / CareAssist`

---

## Summary
Care Assist 2.0 is a distributed system with real-time data synchronization, ensuring caregivers have up-to-date information across web and mobile platforms. The system leverages Couchbase Mobile for efficient offline-first data handling and Sync Gateway for secure synchronization.

This setup allows seamless communication between caregivers, patients, and administrators in an ambulant care setting.
