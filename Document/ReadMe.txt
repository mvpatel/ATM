Follow following Steps to run the Application

# In the Terminal Go to the Folder: cd ATM/target/
# Run Command: mvn clean install
# In the Terminal Go to the Folder: cd ATM/target/
# Run jar File by Command: JAVA -jar atm-0.0.1-SNAPSHOT.jar

# Test: for Wrong Account Number or Pin should get ACCOUNT_Err
# Input:
    123456789
    1234
# Output:
    ACCOUNT_Err: User not found by given username and Pin please try again
# Also try with different random account number and Pin like (12345678, 121) or (123432323, 12989) all time ACCOUNT_Err will be given

# Test: Correct Account information
# Input:
    1234567890
    1230
# Output:
    Customer Account Number and Pin will be displayed in the first line, Then 3 actions will be listed in each line with the heading
    "Choose your next Action from Below"

# Test: Balance Check
# Input:
    B
# Output:
    First Line Account Number with Pin
    Customer Balance: 1000
    Customer Overdraft: 100

# Test: Cash Withdrawals more than ATM Balance Should Return ATM_Err
# Input:
    W 15000

# Output:
    ATM_Err: ATM not have sufficient fund, please try again later

# Test: Cash Withdrawals Should Return FUND_ERR
# Input:
    W 5000

# Output:
    FUND_ERR: Your Balance is not sufficient to withdraw requested amount

# Test: Cash Withdrawals
# Input:
    W 500

# Output:
    request Amount will be subtracted from the Customer Balance
    Customer Balance: 500
    Customer Overdraft: 100

# Test: Cash Withdrawals for full amount
# Input:
    W 600

# Output:
    request Amount will be subtracted from the Customer Balance and Overdraft as well, both balance will be 0
    Customer Balance: 0
    Customer Overdraft: 0

# Test: Exit from the Application
# Input:
    Exit

# Output:
    Bye, Have a great Day See you again!

# Following Todo Work required
        @InjectMock works
        @MockBean Work
        @Verify should work
        Comments should be added in details.
        More tests should be added for ATM Balance.
        Validation should be added for DTOs
        SonarQube should be added in the pom file
        Jacoco test Coverage plugin required to be added in the pom file
        Minimum 85% Test Coverage required should be added under Jacoco
        Test Coverage should be added for ATMDTO

# Git Repository: https://github.com/mvpatel/ATM
# Sequence Diagrams are added in the folder: ATM/Document/SequenceDiagrams
# Test Coverage is added in the folder: ATM/Document/TestCoverage
