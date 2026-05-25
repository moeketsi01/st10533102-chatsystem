#!/bin/bash

# Maven Automated Testing Script for Chat System
# This script runs all automated tests and generates reports

PROJECT_DIR="ChatSystem"
REPORT_DIR="$PROJECT_DIR/target/site"

echo "========================================"
echo "Maven Automated Testing Script"
echo "========================================"
echo ""

# Check if Maven is installed
if ! command -v mvn &> /dev/null; then
    echo "ERROR: Maven is not installed or not in PATH"
    exit 1
fi

echo "✓ Maven found: $(mvn -v | head -1)"
echo ""

# Navigate to project directory
cd "$PROJECT_DIR" || exit 1

echo "Step 1: Cleaning previous builds..."
mvn clean -q
echo "✓ Clean complete"
echo ""

echo "Step 2: Running automated tests..."
mvn test -B
TEST_RESULT=$?

echo ""
echo "Step 3: Generating test reports..."
mvn surefire-report:report -q
echo "✓ Reports generated"
echo ""

echo "Step 4: Test Statistics..."
if [ -d "target/surefire-reports" ]; then
    TEST_COUNT=$(find target/surefire-reports -name "TEST-*.xml" | wc -l)
    echo "✓ Test report files generated: $TEST_COUNT"
    
    # Count total tests
    TOTAL_TESTS=$(grep -r "tests=" target/surefire-reports/ 2>/dev/null | grep -oP 'tests="\K[0-9]+' | awk '{sum+=$1} END {print sum}')
    echo "✓ Total tests executed: $TOTAL_TESTS"
fi
echo ""

echo "========================================"
if [ $TEST_RESULT -eq 0 ]; then
    echo "✓ ALL TESTS PASSED SUCCESSFULLY!"
else
    echo "✗ SOME TESTS FAILED"
    echo "Check the output above for details"
fi
echo "========================================"
echo ""
echo "Test Reports Location:"
echo "  XML: $PROJECT_DIR/target/surefire-reports/"
echo "  HTML: $PROJECT_DIR/target/site/surefire-report.html"
echo ""

exit $TEST_RESULT
