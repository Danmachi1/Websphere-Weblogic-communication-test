# WebLogic / WebSphere Communication 

## Overview

This project simulates a ** FileNet system simulation "not the full system** where:
- **WebLogic** runs a client application that interacts with FileNet.
- **WebSphere** hosts the **FileNet Simulation**, including:
  - A REST API mimicking FileNet Content Engine (CE).
  - An EJB service representing FileNet workflow operations.

This setup allows **testing real-world communication** between WebLogic and WebSphere in a FileNet-based architecture.

## Project Structure

```
WebLogic-WebSphere-FileNet-Simulation/
│── WebLogic/                 # WebLogic Client Application
│   │── src/
│   │   ├── WebLogicToFileNetClient.java  (Main client-side application)
│   │   ├── WebLogicToWebSphereTest.java  (Connection test script)
│   │── README.md              (Setup guide for WebLogic)
│
│── WebSphere/                # WebSphere Hosting FileNet Simulation
│   │── src/
│   │   ├── FileNetServiceEJB.java  (EJB simulating FileNet workflow handling)
│   │   ├── WebSphereFileNetAPI.java  (REST API simulating FileNet CE)
│   │── README.md              (Setup guide for WebSphere)
│
│── FileNetSimulation/         # Complete FileNet Simulation from Provided Code
│   │── ... (All files from uploaded FileNet Simulation)
│
│── README.md                 # Main Project Documentation
│── .gitignore
│── push_project.sh            # Automated Git Push Script
```

## Setup & Usage

 WebLogic Setup

1. Install **WebLogic Server**.
2. Deploy the **WebLogic Client Application (`WebLogicToFileNetClient.java`)**.
3. Ensure WebLogic can communicate with WebSphere over **required ports**.
4. Run the client application to interact with the FileNet simulation.

WebSphere Setup

1. Install **WebSphere Application Server**.
2. Deploy the **FileNet Simulation**:
   - `FileNetServiceEJB.java` (Simulates FileNet Workflow Engine)
   - `WebSphereFileNetAPI.java` (Simulates FileNet Content Engine)
3. Ensure WebSphere allows external access to the REST API and EJB services.

###  Running the Full Test

- **To test WebLogic → FileNet (via WebSphere):**  
  ```bash
  java WebLogicToFileNetClient
  ```

- **To run WebLogic → WebSphere connection test:**  
  ```bash
  java WebLogicToWebSphereTest
  ```

- **To push everything to GitHub:**  
  ```bash
  bash push_project.sh
  ```

## Troubleshooting

- **Connection Issues?** Check network, ports, and security settings.
- **EJB Lookup Fails?** Ensure WebSphere’s `iiop` service is running.
- **FileNet API Unreachable?** Ensure WebSphere has deployed the FileNet API.

---
This project provides a **complete FileNet simulation** for testing WebLogic-WebSphere integration.
