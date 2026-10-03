# WebLogic / WebSphere communication and FileNet-style examples

A Java integration-learning project containing a connectivity probe and
small in-memory examples of document-system concerns. It is **not a
complete FileNet replacement or a deployable enterprise server**.

## Repository contents

- [WebLogicToWebSphereTest](WebLogic/WebLogicToWebSphereTest.java): HTTP GET
  connectivity check and a WebSphere-specific JNDI lookup example
- [SecurityManager](backend/src/main/java/com/example/filenet/security/SecurityManager.java):
  a small READ/WRITE permission example
- Audit log, event, storage, search and workflow example classes under
  [backend/src/main/java/com/example/filenet](backend/src/main/java/com/example/filenet)

The repository does not include a REST endpoint implementation, deployable
EJB service or complete application-server setup.

## Permission behavior

The example has two fixed principals:

| Principal | READ | WRITE | Other or null action |
| --- | --- | --- | --- |
| admin | allowed | allowed | denied |
| user | allowed | denied | denied |
| unknown, empty or null | denied | denied | denied |

Action names are case-sensitive. Unknown users do not gain read access.
This is an in-memory policy example, **not authentication**: callers can
still supply a string username. Do not expose it as a production security
boundary.

## Run the isolated regression tests

Requirements: a full JDK and Python 3. No server, third-party package,
credentials, database or network connection is needed.

```sh
git clone https://github.com/Danmachi1/Websphere-Weblogic-communication-test.git
cd Websphere-Weblogic-communication-test
python3 scripts/test_security.py
```

On Windows, use `python` instead of `python3`. Put the JDK on PATH or
set `JAVA` to the Java executable. The script compiles only the permission
class and its regression harness in a temporary directory.

Focused validation on OpenJDK 21: **30/30 permission cases passed**.
The original implementation failed **7 cases**, including reads by
unknown users and unsupported actions by the write-capable principal.

## Connectivity probe prerequisites

The probe contains placeholder hostnames. It requires an actual
authorized server endpoint and, for JNDI, the matching WebSphere client
libraries and server configuration. Do not run it against systems you do
not own or have permission to test. Never commit credentials.

Compiling/running the isolated permission tests does not exercise HTTP,
JNDI, WebLogic, WebSphere or FileNet. No application-server integration
test was performed in this focused regression pass.

## Other known example limitations

- DocumentStorage references a FileNetDocument type not supplied here.
- SearchEngine needs Lucene dependencies and currently parses a query;
  it does not return stored search results.
- DatabaseConfig names an in-memory H2 connection; its driver is not
  packaged by this repository.
- The audit and workflow examples use in-memory collections.
- No complete build manifest or production persistence/concurrency
  guarantees are supplied.

These examples are intended for learning and local experimentation.
