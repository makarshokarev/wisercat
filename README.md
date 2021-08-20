Steps to deploy project locally:

0. Java version: OpenJdk 14....
1. Run db: (In 'wisercat' directory write to terminal 'docker-compose up -d postgres')
2. Start web server: ./mvnw spring-boot:run. Port: 8090.
3. Start frontend application (In 'wisercat/frontend' directory write to terminal 'npm install'. Before installing write 'npm run serve')
    3.1. Install: npm install
    3.2. Run: npm run serve.
