###How to run application:

1. Java version: OpenJDK 64-Bit Server VM (build 14.0.2+12-46, mixed mode, sharing)
2. Run db: (In 'wisercat' directory write to terminal 'docker-compose up -d postgres')
3. Start web server: ./mvnw spring-boot:run. Port: 8090.
4. Start frontend application
    1. cd frontend
    2. Install: npm install
    3. Run: npm run serve.
