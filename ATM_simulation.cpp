#include <iostream>
#include <string>
#include <vector>
#include <cstdio>

using namespace std;

class Account {

    private:
    string name;
    string pin;
    double balance;

    public:
    Account(string name, string pin, double balance) {
        this->name = name;
        this->pin = pin;
        this->balance = balance;
    }

    string getName() {
        return name;
    }

    string getPin() {
        return pin;
    }

    double getBalance() {
        return balance;
    }

    void deposit(double amount) {
        balance += amount;
    }

    void withdraw(double amount) {
        balance -= amount;
    }

    void setPin(string newPin) {
        pin = newPin;
    }


};

class ATM {
    public:
    ATM() {
        accounts.push_back(Account("Raj", "1234", 1000.0));
        accounts.push_back(Account("Chandan", "5678", 2000.0));
        accounts.push_back(Account("Amit", "9012", 3000.0));
    }

    void run() 
    {
        //while (true) {
            cout << "Welcome to the ATM."<<"\n\n"<<"Please enter your PIN: ";
            string pin;
            cin >> pin;
            Account *account = findAccount(pin);
            if (account == nullptr) {
                cout << "Invalid PIN. Please try again." << endl;
            } else {
                cout << "\nWelcome, " << account->getName() << "." << endl;
            }  
                while (true) 
                {
                    cout << "Please select an option:" << endl;
                    cout << "1. Check balance" << endl;
                    cout << "2. Deposit" << endl;
                    cout << "3. Withdraw" << endl;
                    cout << "4. Change Pin" << endl;
                    cout << "5. Exit" << endl;
                    
                    int opt;
                    cin >> opt;
                switch(opt)
                {
                    
                    case 1:
                        cout << "\nYour balance is $" << account->getBalance() << "." << endl;
                        break;
                    
                    case 2:
                        cout << "\nEnter deposit amount: ";
                        double amount;
                        cin >> amount;
                        account->deposit(amount);
                        cout << "\nDeposit successful. Your new balance is $" << account->getBalance() << ".\n" << endl;
                        break;
                    
                    case 3:
                        cout << "\nEnter withdrawal amount: ";
                        //double amount;
                        cin >> amount;
                        if (amount > account->getBalance()) {
                            cout << "Insufficient funds. Please try again." << endl;
                        } else {
                            account->withdraw(amount);
                            cout << "Withdrawal successful. Your new balance is $" << account->getBalance() << "." << endl;
                        }
                        break;
                    
                    case 4: {
                            cout << "Enter old PIN: ";
                            string oldPIN;
                            cin >> oldPIN;
                            if (oldPIN != account->getPin()) {
                                cout << "Invalid PIN. Please try again." << endl;
                            } else {
                                cout << "Enter new PIN: ";
                                string newPIN;
                                cin >> newPIN;
                                account->setPin(newPIN);
                                cout << "PIN changed successfully." << endl;
                            }
                            break;
                    }
                    case 5:
                        cout << "Thank you for using the ATM. Goodbye!" << endl;
                        exit(0);
                        break;
                    
                    default:
                        cout << "\nInvalid option. Please try again." << endl;
                        break;
                }
                
                }
            
       // }
    }

private:
    vector<Account> accounts;

    Account *findAccount(string pin) {
        for (int i = 0; i < accounts.size(); i++) {
            if (accounts[i].getPin() == pin) {
                return &accounts[i];
            }
        }
        return nullptr;
    }
};

int main() {
    ATM atm;
    atm.run();
    return 0;
}
