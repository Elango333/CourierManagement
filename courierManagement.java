import java.nio.file.*;
import java.io.*;
import java.util.*;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


class courierManagement {
  public static void main(String[] args) {
    System.out.println();
    String companyName =

      "                                      ░▀▀▀▀█ ░█▀▀▀█ ░█─░█ ░█▀▀▀█ 　 ░█▀▀█ ░█▀▀▀█ ░█─░█ ░█▀▀█ ▀█▀ ░█▀▀▀ ░█▀▀█ ░█▀▀▀█\n" +
      "                                      ─▄▄▄▀▀ ░█──░█ ░█▀▀█ ░█──░█ 　 ░█─── ░█──░█ ░█─░█ ░█▄▄▀ ░█─ ░█▀▀▀ ░█▄▄▀ ─▀▀▀▄▄\n" +
      "                                      ░█▄▄▄▄ ░█▄▄▄█ ░█─░█ ░█▄▄▄█ 　 ░█▄▄█ ░█▄▄▄█ ─▀▄▄▀ ░█─░█ ▄█▄ ░█▄▄▄ ░█─░█ ░█▄▄▄█";
    System.out.println(companyName);
    courierManagement askUserDetails = new courierManagement();
    askUserDetails.homePageOptions();
    System.out.println();
  }
   String registerName;
   String senderAddress;
   String senderNumber;
   String fileName;
   String password;
   int countLogin;
   static String senderName;
   String passwordFile = "UserIdPassword.txt";
   Scanner getDetailsforLogin = new Scanner(System.in);


   // Home page Options
   void homePageOptions() {
    System.out.println();
    System.out.println("╔════════════════════════════╗");
    System.out.println("║                            ║");
    System.out.println("║          WELCOME!!!        ║");
    System.out.println("║                            ║");
    System.out.println("╠════════════════════════════╣");
    System.out.println("║                            ║");
    System.out.println("║ Admin login    - Press (1) ║");
    System.out.println("║ Customer login - Press (2) ║");
    System.out.println("║ Exit           - Press (3) ║");
    System.out.println("║                            ║");
    System.out.println("╚════════════════════════════╝");
    countLogin = 0;
    String managerCustomerLogin = getDetailsforLogin.nextLine();
    // Admin login
    if (managerCustomerLogin.equals("1")) {
      getUserIDPassforManager();
    }
    // Customer login
     else if (managerCustomerLogin.equals("2")) {
      customerLoginOptions();
    }
    else{
      System.out.println("Thank you..Bye Bye👋");
      System.exit(0);
    }
  }

  void getUserIDPassforManager(){
        Scanner getDataforManagerLogin = new Scanner(System.in);
     countLogin++;
             if(countLogin == 10){
          System.out.println("Try again after few minutes!!!" + "\n");
          System.out.println("Enter 1 to go back to home page:");
          int backtoHomepage = getDataforManagerLogin.nextInt();
          if(backtoHomepage == 1){
            homePageOptions();
          }
        }

        System.out.println();
        System.out.println("Enter the username:");
        String usernameforManagerLogin = getDataforManagerLogin.nextLine();

        if (usernameforManagerLogin.equals("zohocouriers")) {
            System.out.println("Enter the password:");
            String passforManagerLogin = getDataforManagerLogin.nextLine();
            if (passforManagerLogin.equals("manager@zohocouriers")) {
              Manager manager = new Manager();
              manager.showManagerDetails();
            } else {
              System.out.println("Incorrect Password!!!");
              System.out.println();
              System.out.println("-------------------------------");
              getUserIDPassforManager();
            }
        }
        else if(usernameforManagerLogin.equals("1")){
          homePageOptions();
        }
         else {
          System.out.println("Incorrect username!!!");
          System.out.println();
          System.out.println("-------------------------------");
          getUserIDPassforManager();
        }
  }


  void customerLoginOptions(){
      System.out.println();
      System.out.println("╔═══════════════════════════════════╗");
      System.out.println("║                                   ║");
      System.out.println("║    Register - Press (1)           ║");
      System.out.println("║    Login    - Press (2)           ║");
      System.out.println("║    Back to home page - Press (3)  ║");
      System.out.println("║                                   ║");
      System.out.println("╚═══════════════════════════════════╝");
      System.out.println();
      int signinOption = getDetailsforLogin.nextInt();
      getDetailsforLogin.nextLine();
      // New Register
      if (signinOption==1) {
        System.out.println();
        System.out.println("Enter your Name: ");
        registerName = getDetailsforLogin.nextLine();

        try {
          ArrayList < String > fileContentforPasswordFile = new ArrayList < > (Files.readAllLines(Paths.get(passwordFile)));
          FileWriter passFile = new FileWriter(passwordFile, true);
          for (int i = 0; i < fileContentforPasswordFile.size(); i++) {
            if (fileContentforPasswordFile.get(i).contains(registerName)) {
              System.out.println();
              System.out.println("╔════════════════════════════╗");
              System.out.println("║   User already exists!     ║");
              System.out.println("║ Try a different name or    ║");
              System.out.println("║   log in to your account.  ║");
              System.out.println("╚════════════════════════════╝");
              System.out.println();
              customerLoginOptions();
            }
          }
          passFile.write(registerName + "\n");
          System.out.println("Enter the password: ");
          password = getDetailsforLogin.nextLine();
          passFile.write(password + "\n");
          passFile.close();
          System.out.println("Enter the Address: ");
          senderAddress = getDetailsforLogin.nextLine();
          System.out.println("Enter the Contact number: ");
          senderNumber = getDetailsforLogin.nextLine();
          System.out.println();
        } catch (IOException e) {
           System.out.println("Id password does not exist!!");
        }


      // Increase the count of customers in manager profile
        try {
          ArrayList < String > fileContentformanagerProfile2 = new ArrayList <> (Files.readAllLines(Paths.get("ManagerDetails.txt")));
          for (int i = 0; i < fileContentformanagerProfile2.size(); i++) {
            if (fileContentformanagerProfile2.get(i).contains("Number of Customers:")) {
              String separateNumberforManager = fileContentformanagerProfile2.get(i).substring(fileContentformanagerProfile2.get(i).indexOf("'") + 1, fileContentformanagerProfile2.get(i).lastIndexOf("'"));
              int convertedNumber = Integer.parseInt(separateNumberforManager) + 1;
              String updatedLine = "Number of Customers: '" + convertedNumber + "'";
              fileContentformanagerProfile2.set(i, updatedLine);
            }
          }
          Files.write(Paths.get("ManagerDetails.txt"), fileContentformanagerProfile2);
        } catch (IOException e) {
          System.out.println("Manager details does not exist!!");
        }


          // Add customer details to managerfile
        try {
          FileWriter customerwriter = new FileWriter("customer.txt", true);
          customerwriter.write("Customer details:" + "\n" + "\n" + "Name           : " + registerName + "\n" + "Address        : " + senderAddress + "\n" + "Contact Number : " + senderNumber + "\n" + "\n" + "-------------------------------" + "\n");
          customerwriter.close();

        } catch (IOException e) {
          System.out.println("File does not exist!!");
        }


        // Create customer file with their name and store data
        try {
          fileName = registerName + ".txt";
          File userFile = new File(fileName);
            FileWriter userFileInput = new FileWriter(fileName);
            userFileInput.write("\n" + "Personal details:" + "\n" + "Name           : " + registerName + "\n" + "Address        : " + senderAddress + "\n" + "Contact Number : " + senderNumber + "\n" + "\n" + "-------------------------------" + "\n" + "\n" + "Orders that you have placed: '0'" + "\n" + "\n");
            userFileInput.close();
            System.out.println("Successfully Registered!");
            System.out.println();
            System.out.println("-------------------------------");
            login();
        } catch (IOException e) {
         System.out.println("Unable to create file for customer!!");
        }

      } else if(signinOption==2){
        login();
      }
      else if(signinOption==3){
        homePageOptions();
      }
      else{
        System.out.println("Enter the correct value!!");
        customerLoginOptions();
      }
  }



    // Login details
    void login() {
    Scanner getDataforUserLogin = new Scanner(System.in);
    System.out.println();
    System.out.println("Enter your name to login:");
    senderName = getDataforUserLogin.nextLine();

    try {
      FileWriter passFile = new FileWriter(passwordFile, true);
      ArrayList < String > fileContentforPasswordFile = new ArrayList < > (Files.readAllLines(Paths.get(passwordFile)));
      boolean userExists = false;

      for (int i = 0; i < fileContentforPasswordFile.size(); i++) {
        if (fileContentforPasswordFile.get(i).contains(senderName)) {
          userExists = true;
          System.out.println("Enter the password");
          String passwordcheck = getDataforUserLogin.nextLine();
          if (fileContentforPasswordFile.get(i + 1).contains(passwordcheck)) {
            System.out.println();
            System.out.println("╔═══════════════════════════╗");
            System.out.println("║  Logged in successfully!  ║");
            System.out.println("╚═══════════════════════════╝");
            System.out.println();
            customerOptions();
            break;
          } else {
            System.out.println();
            System.out.println("╔══════════════════════╗");
            System.out.println("║  Incorrect Password! ║");
            System.out.println("╚══════════════════════╝");
            System.out.println();
            login();
            break;
          }
        }
      }

      if (!userExists) {
        System.out.println();
        System.out.println("╔══════════════════════════╗");
        System.out.println("║ User does not exist!     ║");
        System.out.println("║ Please Register!         ║");
        System.out.println("╚══════════════════════════╝");
        System.out.println();
        customerLoginOptions();
      }

      passFile.close();
    } catch (IOException e) {
      System.out.println("User details does not exist!!");
    }
  }

      // Show customer options
    void customerOptions() {
      System.out.println();
      System.out.println("╔══════════════════════════════════╗");
      System.out.println("║                                  ║");
      System.out.println("║ To view your profile - Press (1) ║");
      System.out.println("║                                  ║");
      System.out.println("║ To send your courier - Press (2) ║");
      System.out.println("║                                  ║");
      System.out.println("║ To track the order   - Press (3) ║");
      System.out.println("║                                  ║");
      System.out.println("║ To logout            - Press (4) ║");
      System.out.println("║                                  ║");
      System.out.println("╚══════════════════════════════════╝");
      System.out.println();
      Scanner getDataforCustomerDetails = new Scanner(System.in);
      int sendCourier = getDataforCustomerDetails.nextInt();


      // View profile
      if (sendCourier == 1) {
        try {
          String filePathforProfile = senderName + ".txt";
          ArrayList < String > fileContentforProfile = new ArrayList < > (Files.readAllLines(Paths.get(filePathforProfile)));
          for (int i = 0; i < fileContentforProfile.size(); i++) {
            if (fileContentforProfile.get(i).contains("Delivery Time")) {
              String separateDateforProfile = fileContentforProfile.get(i).substring(fileContentforProfile.get(i).indexOf("\'") + 1, fileContentforProfile.get(i).lastIndexOf("\'"));
              LocalDateTime currentTime = LocalDateTime.now();
              DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm a");
              String formattedNewTime = currentTime.format(formatter);
              LocalDateTime parsedCurrentTime = LocalDateTime.parse(formattedNewTime, formatter);
              LocalDateTime parsedOldTime = LocalDateTime.parse(separateDateforProfile, formatter);

              if (parsedCurrentTime.isBefore(parsedOldTime)) {
                fileContentforProfile.set(i + 1, "Delivery Status            : Shipped");
              } else if (parsedCurrentTime.isAfter(parsedOldTime)) {
                fileContentforProfile.set(i + 1, "Delivery Status            : Delivered");
              } else {
                fileContentforProfile.set(i + 1, "Delivery Status            : Out for delivery");
              }
            }
            System.out.println(fileContentforProfile.get(i));
          }
          Files.write(Paths.get(filePathforProfile), fileContentforProfile);
        }
        catch (IOException e) {
           System.out.println("Unable to show the customer profile!!");
        }
        customerOptions();
      }


      // Send courier
       else if (sendCourier == 2) {
        receiverDetails receiverdetails = new receiverDetails();
        receiverdetails.askReceiverDetails(senderName);
        packageDetails packagedetails = new packageDetails();
        packagedetails.askPackageDetails();
        Preference preference = new Preference();
        preference.askPreference(packagedetails);
        Customer customer = new Customer();
        customer.createFile(receiverdetails, packagedetails, preference);
        System.out.println();
        trackDetails();
        customerOptions();
      }


      // Track the order
       else if (sendCourier == 3) {
        trackDetails();
        customerOptions();
      }


      // logout
       else if (sendCourier == 4) {
        homePageOptions();
      }
  }


    // Track order
    void trackDetails() {
    Scanner getDataforTrackingDetails = new Scanner(System.in);
    System.out.println("Enter your Consignment code to track your order:");
    int consignmentcode = getDataforTrackingDetails.nextInt();
    Tracking tracking = new Tracking(consignmentcode);
    tracking.printTrackingDetails();
    System.out.println();
  }
    
}


class receiverDetails {
  String receiverName;
  String receiverAddress;
  String receiverNumber;
  String senderFilename;

  void askReceiverDetails(String sendername) {
    senderFilename = sendername;
    Scanner getDataforReceiverDetails = new Scanner(System.in);
    System.out.println();
    System.out.println("Enter the Receiver details,");
    System.out.println();
    System.out.println("Enter the name: ");
    receiverName = getDataforReceiverDetails.nextLine();
    System.out.println("Enter the Address: ");
    receiverAddress = getDataforReceiverDetails.nextLine();
    System.out.println("Enter the Contact number: ");
    receiverNumber = getDataforReceiverDetails.nextLine();
    System.out.println();
    System.out.println("-------------------------------");
    System.out.println();
   
  }

  String getReceiverName() {
    return receiverName;
  }

  String getReceiverAddress() {
    return receiverAddress;
  }

  String getReceiverNumber() {
    return receiverNumber;
  }
}


class packageDetails {
  String description;
  double weight;
  int noOfPieces;

  void askPackageDetails() {
    Scanner getDataforPackageDetails = new Scanner(System.in);
    System.out.println("Package details:");
    System.out.println();
    System.out.println("Enter the description of the content:");
    description = getDataforPackageDetails.nextLine();
    System.out.println("Enter the weight in gram:");
    weight = getDataforPackageDetails.nextDouble();
    System.out.println("Enter the number of packages:");
    noOfPieces = getDataforPackageDetails.nextInt();
    System.out.println();
    System.out.println("-------------------------------");
    System.out.println();
 
  }

  String getDescription() {
    return description;
  }

  double getWeight() {
    return weight;
  }

  int getPieces() {
    return noOfPieces;
  }
  
}


class Preference {
  int specificCode = 332003 + (int)(Math.random() * ((10000000 - 332003) + 1));;
  int typeOfCourier;
  double amount;
  LocalDateTime deliveryDate;
  LocalDateTime deliveryDateUpdate;
  DateTimeFormatter formatter;
  String formattedCurrentTime;
  packageDetails pack;
  void askPreference(packageDetails packdetails) {
    pack = packdetails;
    Scanner getDataforPreference = new Scanner(System.in);
    System.out.println("Which courier do you prefer (Enter 1 for normal courier or 2 for speed courier):");
    typeOfCourier = getDataforPreference.nextInt();
    System.out.println();
    setDeliveryDate();
  
  }

  void setDeliveryDate() {
    LocalDateTime currentDate = LocalDateTime.now();
    deliveryDate = LocalDateTime.now();
    if (typeOfCourier == 1) {
      amount = Math.round((pack.getWeight() / 10));
      deliveryDateUpdate = deliveryDate.plusMinutes(6);
      formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm a");
      formattedCurrentTime = deliveryDateUpdate.format(formatter);
    } else {
      amount = Math.round((pack.getWeight() / 5));
      deliveryDateUpdate = deliveryDate.plusMinutes(3);
      formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm a");
      formattedCurrentTime = deliveryDateUpdate.format(formatter);
    }
  }

  String printPackageDetails() {
    return formattedCurrentTime;
  }

  double getAmount() {
    return amount;
  }

}


class Customer {
  int convertedNumber;
  String deliveryStatus;

  void createFile(receiverDetails receivedetails, packageDetails packagedetails, Preference preference) {
    String rewriteFile = (receivedetails.senderFilename + ".txt");

    // store data to files
    try {
      File myfile = new File(preference.specificCode + ".txt");
      FileWriter consignmentcodeFile = new FileWriter(preference.specificCode + ".txt");
      FileWriter customerFile = new FileWriter(rewriteFile, true);
      FileWriter managerFile = new FileWriter("Manager.txt", true);
      consignmentcodeFile.write("\n" + "Receiver Information:" + "\n" + "Consignment code           : " + preference.specificCode + "\n" + "Receiver Address: " + "\n" + "Name                       : " + receivedetails.getReceiverName() + "\n" + "Address                    : " + receivedetails.getReceiverAddress() + "\n" + "Contact Number             : " + receivedetails.getReceiverNumber() + "\n" + "\n" + "Package details:" + "\n" + "Description of the content : " + packagedetails.getDescription() + "\n" + "Weight                     : " + packagedetails.getWeight() + "g" + "\n" + "Number of pieces           : " + packagedetails.getPieces() + "\n" + "Amount                     : " + preference.getAmount() + "\n" + "Delivery Time              : " + "'" + preference.printPackageDetails() + "'" + "\n" + "Delivery Status            : Shipped" + "\n" +  "\n" + "-------------------------------" + "\n" );
      consignmentcodeFile.close();
      customerFile.write("\n" + "Receiver Address: " + "\n" + "Name                       : " + receivedetails.getReceiverName() + "\n" + "Address                    : " + receivedetails.getReceiverAddress() + "\n" + "Contact Number             : " + receivedetails.getReceiverNumber() + "\n" + "Consignment code           : " + preference.specificCode + "\n" + "\n" + "Package details:" + "\n" + "Description of the content : " + packagedetails.getDescription() + "\n" + "Weight                     : " + packagedetails.getWeight() + "g" + "\n" + "Number of pieces           : " + packagedetails.getPieces() + "\n" + "Amount                     : " + preference.getAmount() + "\n" + "Delivery Time              : " + "'" + preference.printPackageDetails() + "'" + "\n" + "Delivery Status            : Shipped" + "\n" + "\n" + "-------------------------------" + "\n" );
      customerFile.close();
      managerFile.write("Receiver Address: " + "\n" + "Name                       : " + receivedetails.getReceiverName() + "\n" + "Address                    : " + receivedetails.getReceiverAddress() + "\n" + "Contact Number             : " + receivedetails.getReceiverNumber() + "\n" + "Consignment code           : " + preference.specificCode + "\n" + "\n" + "Package details:" + "\n" + "Description of the content : " + packagedetails.getDescription() + "\n" + "Weight                     : " + packagedetails.getWeight() + "g" + "\n" + "Number of pieces           : " + packagedetails.getPieces() + "\n" + "Amount                     : " + preference.getAmount() + "\n" + "Delivery Time              : " + "'" + preference.printPackageDetails() + "'" + "\n" + "Delivery Status            : Shipped" + "\n" + "\n" + "-------------------------------" + "\n");
      managerFile.close();
    }
     catch (IOException e) {
      System.out.println("Unable to store customer data!!");
    }


    // Edit number of orders in managerfile
    try {
      ArrayList < String > fileContentformanagerProfile2 = new ArrayList < > (Files.readAllLines(Paths.get("ManagerDetails.txt")));
      for (int i = 0; i < fileContentformanagerProfile2.size(); i++) {
        if (fileContentformanagerProfile2.get(i).contains("Number of orders received:")) {
          String separateNumberforManager = fileContentformanagerProfile2.get(i).substring(fileContentformanagerProfile2.get(i).indexOf("'") + 1, fileContentformanagerProfile2.get(i).lastIndexOf("'"));
          int convertedNumber = Integer.parseInt(separateNumberforManager) + 1;
          String updatedLine = "Number of orders received: '" + convertedNumber + "'";
          fileContentformanagerProfile2.set(i, updatedLine);
        }
      }
      Files.write(Paths.get("ManagerDetails.txt"), fileContentformanagerProfile2);
    } catch (IOException e) {
      System.out.println("Unable to edit number of orders in manager profile!!");
    }


    // Edit orders that customers placed in customer file
    try {
      ArrayList < String > fileforCustomer = new ArrayList < > (Files.readAllLines(Paths.get(rewriteFile)));
      for (int i = 0; i < fileforCustomer.size(); i++) {
        if (fileforCustomer.get(i).contains("Orders that you have placed:")) {
          String separateNumberforCustomer = fileforCustomer.get(i).substring(fileforCustomer.get(i).indexOf("'") + 1, fileforCustomer.get(i).lastIndexOf("'"));
          int convertedNumberforCustomer = Integer.parseInt(separateNumberforCustomer) + 1;
          String updatedLineforCustomer = "Orders that you have placed: '" + convertedNumberforCustomer + "'";
          fileforCustomer.set(i, updatedLineforCustomer);
        }
      }
      Files.write(Paths.get(rewriteFile), fileforCustomer);
    }
     catch (IOException e) {
        System.out.println("Unable to edit number of orders in customer profile!!");
    }
    System.out.println();
    System.out.println("╔═══════════════════════════════════╗");
    System.out.println("║  Successfully placed your order!  ║");
    System.out.println("╚═══════════════════════════════════╝");
    System.out.println();
    System.out.println("Your Consignment code      : " + preference.specificCode);
  }
}


class Manager {
   void showManagerDetails() {
    System.out.println();
    System.out.println("╔═══════════════════════════════════════════╗");
    System.out.println("║                                           ║");
    System.out.println("║  To view the company details - Press (1)  ║");
    System.out.println("║                                           ║");
    System.out.println("║  To view Customer details    - Press (2)  ║");
    System.out.println("║                                           ║");
    System.out.println("║  To view the orders list     - Press (3)  ║");
    System.out.println("║                                           ║");
    System.out.println("║  To go to the home page      - Press (4)  ║");
    System.out.println("║                                           ║");
    System.out.println("╚═══════════════════════════════════════════╝");
    System.out.println();
    Scanner getDataforManagerDetails = new Scanner(System.in);
    int comdetails = getDataforManagerDetails.nextInt();
    if (comdetails == 1) {
      printCompanyDetails();
    }
     else if (comdetails == 2) {
      try {
        ArrayList < String > fileContentforCustomer = new ArrayList < > (Files.readAllLines(Paths.get("customer.txt")));
        for (int i = 0; i < fileContentforCustomer.size(); i++) {
          System.out.println(fileContentforCustomer.get(i));
        }
      } catch (IOException e) {
         System.err.println("File does not exist!!");
      }
      showManagerDetails();
    }
     else if (comdetails == 3) {
      orderList();
    }
     else if (comdetails == 4) {
      courierManagement courierMag = new courierManagement();
      courierMag.homePageOptions();
    }
  }


   void printCompanyDetails() {
    try {
      String filePathforCompanyDetails = "ManagerDetails.txt";
      ArrayList < String > fileContentforCompanyDetails = new ArrayList < > (Files.readAllLines(Paths.get(filePathforCompanyDetails)));
      for (int i = 0; i < fileContentforCompanyDetails.size(); i++) {
        System.out.println(fileContentforCompanyDetails.get(i));
      }
    }
     catch (IOException e) {
      System.err.println("Unable to print company details!!");
    }
    showManagerDetails();
  }


   void orderList() {
    System.out.println();
    try {
      ArrayList < String > fileContentformanagerProfile = new ArrayList < > (Files.readAllLines(Paths.get("Manager.txt")));
      for (int i = 0; i < fileContentformanagerProfile.size(); i++) {
        if (fileContentformanagerProfile.get(i).contains("Delivery Time")) {
          String separateDateforManagerProfile = fileContentformanagerProfile.get(i).substring(fileContentformanagerProfile.get(i).indexOf("\'") + 1, fileContentformanagerProfile.get(i).lastIndexOf("\'"));
          LocalDateTime currentTime = LocalDateTime.now();
          DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm a");
          String formattedNewTime = currentTime.format(formatter);
          LocalDateTime parsedCurrentTime = LocalDateTime.parse(formattedNewTime, formatter);
          LocalDateTime parsedOldTime = LocalDateTime.parse(separateDateforManagerProfile, formatter);

          if (parsedCurrentTime.isBefore(parsedOldTime)) {
            fileContentformanagerProfile.set(i + 1, "Delivery Status            : Shipped");
          }
           else if (parsedCurrentTime.isAfter(parsedOldTime)) {
            fileContentformanagerProfile.set(i + 1, "Delivery Status            : Delivered");
          }
           else {
            fileContentformanagerProfile.set(i + 1, "Delivery Status            : Out for delivery");
          }
        }
      }
      Files.write(Paths.get("Manager.txt"), fileContentformanagerProfile);
    }
     catch (IOException e) {
       System.err.println("Unable to update the manager details!!");
    }
    viewShippedAndDeliveredOptions();
  }


   void viewShippedAndDeliveredOptions() {
    Scanner getDataforShippedDetails = new Scanner(System.in);
    System.out.println();
    System.out.println("╔═══════════════════════════════════════════════════╗");
    System.out.println("║                                                   ║");
    System.out.println("║  To view the Shipped orders           - Press (1) ║");
    System.out.println("║                                                   ║");
    System.out.println("║  To view the Out for delivery orders  - Press (2) ║");
    System.out.println("║                                                   ║");
    System.out.println("║  To view the Delivered orders         - Press (3) ║");
    System.out.println("║                                                   ║");
    System.out.println("║  To go back                           - Press (4) ║");
    System.out.println("║                                                   ║");
    System.out.println("╚═══════════════════════════════════════════════════╝");
    System.out.println();
    int chooseDetails = getDataforShippedDetails.nextInt();
    if (chooseDetails == 1) {
      ShippedDetails();
    } else if (chooseDetails == 2) {
      OutforDeliveryDetails();
    } else if (chooseDetails == 3) {
      DeliveredDetails();
    } else if (chooseDetails == 4) {
      showManagerDetails();
    }
    else{
      System.out.println("Enter the correct number!!");
      System.out.println();
      viewShippedAndDeliveredOptions();
    }
  }


   void ShippedDetails() {
    try {
         ArrayList < String > fileContentforManagerDetails = new ArrayList < > (Files.readAllLines(Paths.get("Manager.txt")));
      ArrayList < String > shippedContents = new ArrayList < > ();
      ArrayList < String > shippedContentsTemp = new ArrayList < > ();
      for (int i = 0; i < fileContentforManagerDetails.size(); i++) {
        String currentLine = fileContentforManagerDetails.get(i);

        shippedContentsTemp.add(currentLine);
        if (currentLine.contains("Delivered")) {

          shippedContentsTemp.clear();
        } else if (currentLine.contains("Out for delivery")) {

          shippedContentsTemp.clear();
        } else if (currentLine.contains("Shipped")) {

          shippedContents.addAll(shippedContentsTemp);
          shippedContentsTemp.clear();
        }

      }
      if (shippedContents.isEmpty()) {
        System.out.println("Number of shipped orders: 0");
      } else {
        for (String content: shippedContents) {
          System.out.println(content);
        }
      }

    }
     catch (IOException e) {
        System.out.println("Unable to update shipped orders!!");
    }
    viewShippedAndDeliveredOptions();
  }


   void OutforDeliveryDetails() {
    try {
         ArrayList < String > fileContentforManagerDetails = new ArrayList < > (Files.readAllLines(Paths.get("Manager.txt")));
      ArrayList < String > outforDeliveryContents = new ArrayList < > ();
      ArrayList < String > outforDeliveryContentsTemp = new ArrayList < > ();
      for (int i = 0; i < fileContentforManagerDetails.size(); i++) {
        String currentLine = fileContentforManagerDetails.get(i);

        outforDeliveryContentsTemp.add(currentLine);
        if (currentLine.contains("Shipped")) {

          outforDeliveryContentsTemp.clear();
        } else if (currentLine.contains("Delivered")) {

          outforDeliveryContentsTemp.clear();
        } else if (currentLine.contains("Out for delivery")) {

          outforDeliveryContents.addAll(outforDeliveryContentsTemp);
          outforDeliveryContentsTemp.clear();
        }
      }

      if (outforDeliveryContents.isEmpty()) {
        System.out.println("Number of out for Delivery orders: 0");
      } else {
        for (String content: outforDeliveryContents) {
          System.out.println(content);
        }
      }

    }
     catch (IOException e) {
         System.out.println("Unable to update out for delivery orders!!");
    }
    viewShippedAndDeliveredOptions();
  }


     void DeliveredDetails() {
    try {
         ArrayList < String > fileContentforManagerDetails = new ArrayList < > (Files.readAllLines(Paths.get("Manager.txt")));
      ArrayList < String > deliveredContents = new ArrayList < > ();
      ArrayList < String > deliveredContentsTemp = new ArrayList < > ();
      for (int i = 0; i < fileContentforManagerDetails.size(); i++) {
        String currentLine = fileContentforManagerDetails.get(i);
        deliveredContentsTemp.add(currentLine);
        if (currentLine.contains("Shipped")) {

          deliveredContentsTemp.clear();
        } else if (currentLine.contains("Out for delivery")) {

          deliveredContentsTemp.clear();
        } else if (currentLine.contains("Delivered")) {

          deliveredContents.addAll(deliveredContentsTemp);
          deliveredContentsTemp.clear();
        }

      }
      if (deliveredContents.isEmpty()) {
        System.out.println("Number of Delivered orders: 0");
      }
       else {
        for (String content: deliveredContents) {
          System.out.println(content);
        }
      }

    }
     catch (IOException e) {
       System.out.println("Unable to update delivered orders!!");
        }
    viewShippedAndDeliveredOptions();
  }
}


class Tracking {
  int trackingNumber;
  Tracking(int trackingNumber) {
    this.trackingNumber = trackingNumber;
  }

  void printTrackingDetails() {
     courierManagement couriermag = new courierManagement();
    try {
      String filePath = trackingNumber + ".txt";
      ArrayList < String > fileContent = new ArrayList < > (Files.readAllLines(Paths.get(filePath)));
      String tempContent;
      int shippedTheme = 0;
      int outforDeliveryTheme = 0;
      int deliveredTheme = 0;
      for (int i = 0; i < fileContent.size(); i++) {
        if (fileContent.get(i).contains("Delivery Time")) {
          String separateDate = fileContent.get(i).substring(fileContent.get(i).indexOf("\'") + 1, fileContent.get(i).lastIndexOf("\'"));
          LocalDateTime currentTime = LocalDateTime.now();
          DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm a");
          String formattedNewTime = currentTime.format(formatter);
          LocalDateTime parsedCurrentTime = LocalDateTime.parse(formattedNewTime, formatter);
          LocalDateTime parsedOldTime = LocalDateTime.parse(separateDate, formatter);

          if (parsedCurrentTime.isBefore(parsedOldTime)) {
            fileContent.set(i + 1, "Delivery Status            : Shipped");
            shippedTheme = 1;
          } else if (parsedCurrentTime.isAfter(parsedOldTime)) {
            fileContent.set(i + 1, "Delivery Status            : Delivered");
            deliveredTheme = 1;

          } else {
            fileContent.set(i + 1, "Delivery Status            : Out for delivery");
            outforDeliveryTheme = 1;
          }

        }
        System.out.println(fileContent.get(i));
      }

      if (shippedTheme == 1) {
        System.out.println(
          "  ___________________________________________\n" +
          " /                                           \\\n" +
          "|              ZOHO TRACK & TRACE             |\n" +
          "|_____________________________________________|\n" +
          "|                                             |\n" +
          "|       📍                                    |\n" +
          "|       __             __             __      |\n" +
          "|      |  |           |  |           |  |     |\n" +
          "|      |📦|           |🚚|           |✅|     |\n" +
          "|      |__|           |__|           |__|     |\n" +
          "|                                             |\n" +
          "|  [ Shipped ]                                |\n" +
          "|                                             |\n" +
          "|                                             |\n" +
          "|_____________________________________________|\n" +
          " \\___________________________________________/");
        System.out.println();
      }
       else if (outforDeliveryTheme == 1) {

        System.out.println(
          "  ___________________________________________\n" +
          " /                                           \\\n" +
          "|              ZOHO TRACK & TRACE             |\n" +
          "|_____________________________________________|\n" +
          "|                                             |\n" +
          "|                      📍                     |\n" +
          "|       __             __             __      |\n" +
          "|      |  |           |  |           |  |     |\n" +
          "|      |📦|           |🚚|           |✅|     |\n" +
          "|      |__|           |__|           |__|     |\n" +
          "|                                             |\n" +
          "|             [ Out for Delivery ]            |\n" +
          "|                                             |\n" +
          "|                                             |\n" +
          "|_____________________________________________|\n" +
          " \\___________________________________________/");
        System.out.println();
      }
       else if (deliveredTheme == 1) {
        System.out.println(
          "  ___________________________________________\n" +
          " /                                           \\\n" +
          "|              ZOHO TRACK & TRACE             |\n" +
          "|_____________________________________________|\n" +
          "|                                             |\n" +
          "|                                     📍      |\n" +
          "|       __             __             __      |\n" +
          "|      |  |           |  |           |  |     |\n" +
          "|      |📦|           |🚚|           |✅|     |\n" +
          "|      |__|           |__|           |__|     |\n" +
          "|                                             |\n" +
          "|                               [ Delivered ] |\n" +
          "|                                             |\n" +
          "|                                             |\n" +
          "|_____________________________________________|\n" +
          " \\___________________________________________/");
        System.out.println();
      }
      Files.write(Paths.get(filePath), fileContent);
      couriermag.customerOptions();
    }
     catch (IOException e) {
      System.err.println("Enter the correct Consignment code!!!");
    }
    couriermag.customerOptions();
  }
}