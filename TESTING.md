# Maven Testing Configuration

## Local Testing Commands

### Quick Test
```bash
cd ChatSystem
mvn test
```

### Full Validation
```bash
cd ChatSystem
mvn clean test -B
```

### Generate Reports
```bash
cd ChatSystem
mvn surefire-report:report
```

### Specific Test Class
```bash
cd ChatSystem
mvn test -Dtest=ChatSystemTest
mvn test -Dtest=SendingMessagesTest
```

### Specific Test Method
```bash
cd ChatSystem
mvn test -Dtest=ChatSystemTest#testRegisterUserSuccess
```

## Using Test Scripts

### Linux/Mac
```bash
chmod +x run-tests.sh
./run-tests.sh
```

### Windows
```bash
run-tests.bat
```

## Test Results Location

After running tests, find reports in:
- **XML Reports**: `ChatSystem/target/surefire-reports/TEST-*.xml`
- **HTML Report**: `ChatSystem/target/site/surefire-report.html`

## CI/CD via GitHub Actions

Tests automatically run on:
- Push to `main` or `develop` branch
- Pull requests
- Manual trigger via Actions tab

View results in GitHub Actions tab.

## Troubleshooting

| Problem | Solution |
|---------|----------|
| Maven not found | Install Maven or add to PATH |
| Java version error | Ensure Java 17+ installed |
| Tests fail | Check console output for specific failures |
| No reports generated | Run `mvn clean` first, then test |

## Test Coverage

- **Total Tests**: 52+
- **ChatSystem**: 25+ tests
- **SendingMessages**: 27+ tests
- **Categories**: Validation, Business Logic, Integration

## Performance

- Typical test execution: < 5 seconds
- Parallel execution available with `-DparallelTestClasses=true`
- Reports generation: < 10 seconds
