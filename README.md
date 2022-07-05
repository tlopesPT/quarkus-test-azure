## Structure

- MessagePublisher
    - Service class, would contain business logic and forward message to client wrapper.
- MessageClientWrapper
    - Infrastructure class, manages connection with IoT Hub and sends messages.
    - Should not open connection during quarkus tests

- MessagePublisherTest
    - Tests service class business logic, should never interact with Iot Hub
    - Injects mock of MessageClientWrapper, but this does not prevent actual MessageClientWrapper from being instantiated previously, which
      requires IoT Hub credentials and can cause problems if not mocked properly.

Ideally when running the quarkus test, MessageClientWrapper is not instantiated.

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:

```shell script
./mvnw compile quarkus:dev
```
