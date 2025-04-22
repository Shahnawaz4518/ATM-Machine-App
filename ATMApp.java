import java.util.*;

interface AtmOperationInterf {
    void viewBalance();
    void withdrawAmount(double withdrawAmount);
    void depositAmount(double depositAmount);
    void viewMiniStatement();
}

class ATM {
    private double balance;
    private double depositAmount;
    private double withdrawAmount;

    public ATM() {
        this.balance = 10000.0; // Initial balance for testing
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getDepositAmount() {
        return depositAmount;
    }

    public void setDepositAmount(double depositAmount) {
        this.depositAmount = depositAmount;
    }

    public double getWithdrawAmount() {
        return withdrawAmount;
    }

    public void setWithdrawAmount(double withdrawAmount) {
        this.withdrawAmount = withdrawAmount;
    }
}

class AtmOperationImpl implements AtmOperationInterf {
    ATM atm = new ATM();
    Map<Double, String> ministmt = new LinkedHashMap<>(); // Maintains order of transactions

    @Override
    public void viewBalance() {
        System.out.println("Available Balance is : ₹" + atm.getBalance());
    }

    @Override
    public void withdrawAmount(double withdrawAmount) {
        if (withdrawAmount % 500 == 0) {
            if (withdrawAmount <= atm.getBalance()) {
                ministmt.put(withdrawAmount, "Withdrawn");
                System.out.println("Collect the Cash: ₹" + withdrawAmount);
                atm.setBalance(atm.getBalance() - withdrawAmount);
                viewBalance();
            } else {
                System.out.println("Insufficient Balance!!");
            }
        } else {
            System.out.println("Please enter the amount in multiples of 500");
        }
    }

    @Override
    public void depositAmount(double depositAmount) {
        ministmt.put(depositAmount, "Deposited");
        System.out.println("₹" + depositAmount + " Deposited Successfully!!");
        atm.setBalance(atm.getBalance() + depositAmount);
        viewBalance();
    }

    @Override
    public void viewMiniStatement() {
        if (ministmt.isEmpty()) {
            System.out.println("No transactions yet.");
        } else {
            System.out.println("Mini Statement:");
            for (Map.Entry<Double, String> entry : ministmt.entrySet()) {
                System.out.println("₹" + entry.getKey() + " - " + entry.getValue());
            }
        }
    }
}

public class ATMApp {
    public static void main(String[] args) {
        AtmOperationInterf op = new AtmOperationImpl();
        int atmNumber = 12345;
        int pin = 123;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to ATM Machine!!!");
        System.out.print("Enter ATM Number: ");
        int userAtmNumber = scanner.nextInt();

        System.out.print("Enter PIN: ");
        int userPin = scanner.nextInt();

        if (atmNumber == userAtmNumber && pin == userPin) {
            while (true) {
                System.out.println("\n========= ATM Menu =========");
                System.out.println("1. View Available Balance");
                System.out.println("2. Withdraw Amount");
                System.out.println("3. Deposit Amount");
                System.out.println("4. View Mini Statement");
                System.out.println("5. Exit");
                System.out.print("Enter Choice: ");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        op.viewBalance();
                        break;
                    case 2:
                        System.out.print("Enter amount to withdraw: ");
                        double withdrawAmount = scanner.nextDouble();
                        op.withdrawAmount(withdrawAmount);
                        break;
                    case 3:
                        System.out.print("Enter amount to deposit: ");
                        double depositAmount = scanner.nextDouble();
                        op.depositAmount(depositAmount);
                        break;
                    case 4:
                        op.viewMiniStatement();
                        break;
                    case 5:
                        System.out.println("Collect your ATM Card");
                        System.out.println("Thank you for using the ATM Machine!!");
                        scanner.close();
                        System.exit(0);
                    default:
                        System.out.println("Please enter a valid choice (1-5).");
                }
            }
        } else {
            System.out.println("Incorrect ATM Number or PIN");
        }
    }
}