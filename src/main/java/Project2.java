
public class Project2 {
    
    public class User 
{
    private String userId;
    
    private String UserPIN;
    
    public User()
    {
        this.userId="";
        
        this.UserPIN="";
    }
    public User(String id , String pin)
    {
        this.userId=id;
        
        this.UserPIN=pin;
    }
    public void setUserId(String id )
    {
        this.userId=id;
    }
    public void setUserPIN(String pin)
    {
      this.UserPIN=pin;
    }
    public String getUserId()
    {
        return this.userId;
    }
    public String getUserPIN()
    {
      return this.UserPIN;
    }
    public String toString()
    {
        String str="\n";
               str+="\nID : "+this.getUserId();
               str+="\nPIN : "+this.getUserPIN();
               
        return str;
    }
}



















package atmsystem;

public class Account extends User 
{
    private String AccountNumber ;
    
    private String FullName ;
    
    private double CurrentBalance;
    
    public Account()
    {
        super("","");
        
        this.FullName="";
        
        this.CurrentBalance=0.0;
        
        this.AccountNumber="";
    }
    public Account(String id , String pin,String AccNumber , String name , double Bal)
    {
        super(id,pin);
        
        this.FullName=name;
        
        this.CurrentBalance=Bal;
        
         this.AccountNumber=AccNumber;
    }
    public String getAccountNumber()
    {
        return this.AccountNumber;
    }
    public double getCurrentBalance()
    {
        return this.CurrentBalance;
    }
    public String getFullName()
    {
        return this.FullName;
    }
    public void Withdraw(double amount)
    {
        this.CurrentBalance-=amount;
    }
    public void Deposit(double amount)
    {
        this.CurrentBalance+=amount;
    }
    public void Transfer(Account acc , double amount )
    {
        acc.Deposit(amount);
        
        this.Withdraw(amount);        
        
        System.out.println("\n*****Transfer "+amount +" SAR to "+acc.getAccountNumber()+ " from "+this.getAccountNumber()+" Done Succesfully .");
    }
    public String toString()
    {
        String str="\nAccount Information "+super.toString();
               str+="\nAccount Number : "+this.getAccountNumber();
               str+="\nAccount Full Name : "+this.getFullName();
               str+="\nAccountCurrent Balance : "+this.getCurrentBalance();               
        return str;
    }
}

package atmsystem;
import java.util.*;
public class ATMSystem 
{
    public static void MainMenu()
    {
        System.out.println("----------------------------------");
        System.out.println("        Welcome in ATM System     ");
        System.out.println("----------------------------------");
        System.out.println("You are ? ");
        System.out.println("    1- Admin  ");
        System.out.println("    2- User . ");
        System.out.println("    3- Quit . ");
        System.out.print("Enter Your Selection : > ");
    }
    public static void AdminMenu()
    {
        System.out.println("----------------------------------");
        System.out.println("        Welcome Admin in ATM System     ");
        System.out.println("----------------------------------");
        System.out.println("    1- Add New User .  ");
        System.out.println("    2- View All Users . ");
        System.out.println("    3- Back . ");
        System.out.print("Enter Your Selection : > ");
    }
    public static void displayAllUsers(ArrayList<User> UserList)
    {
      for(User temp : UserList)
      {                                          
        if(!temp.getUserId().equals(""))
        {
          System.out.println(temp.toString());
        }                           
      }
    }
    public static boolean SearchUsers(ArrayList<User> UserList , String id  ,String pin)
    {
      boolean  found=false;
      
      for(User temp : UserList)
      {                                          
        if(temp.getUserId().equals(id) && temp.getUserPIN().equals(pin))
        {
          found=true;
          break;
        }  
        else
        {
           found=false;
        }
      }
      
      return found;
    }
    public static boolean SearchAccounts(ArrayList<Account> AccountList , String CardNo)
    {
      boolean  found=false;
      
      for(Account temp : AccountList)
      {                                          
        if(temp.getAccountNumber().equals(CardNo))
        {
          found=true;
          
          break;
        }  
        else
        {
           found=false;
        }
      }
      
      return found;
    }
    
    public static void UserMenu(String id)
    {
        System.out.println("---------------------------------------------------");
        System.out.println("        Welcome User ID "+ id +" in ATM System     ");
        System.out.println("---------------------------------------------------");
        System.out.println("    1- Transactions History .  ");
        System.out.println("    2- Withdraw . ");
        System.out.println("    3- Deposit . ");
        System.out.println("    4- Transfer . ");
        System.out.println("    5- Quit . ");
        System.out.println("    6- Add a new account. ");
        System.out.print("Enter Your Selection : > ");
    }
    public static String generateAccountNumber()
    {
        Random random = new Random();
        
        String AccountNumber = "SA";
        
        for (int i = 0; i < 14; i++)
        {
            int n = random.nextInt(10) + 0;
            
            AccountNumber += Integer.toString(n);
        }
        
        return AccountNumber;
    }
    public static void displayAllTransactionsHistory(ArrayList<String> TransactionsHistory)
    {
      for(String temp : TransactionsHistory)
      {                                          
        if(!temp.equals(""))
        {
          System.out.println(temp.toString());
        }                           
      }
    }
    public static void main(String[] args)
    {
        Scanner scan=new Scanner(System.in);
        ArrayList<User> UserList= new ArrayList<User>();
        int select1=0 , select2=0 , select3=0;
        String id , PIN;
        
        
        ArrayList<Account> AccountList= new ArrayList<Account>();
         
        String AccountNumber  , FullName ;
        double CurrentBalance;
        
        String AccountNumberfrom , AccountNumberto;
        
        ArrayList<String> TransactionsHistory= new ArrayList<String>();
        do
        {
          MainMenu();
          select1=scan.nextInt();
          switch(select1)
          {
            case 1:
                do
                {
                  AdminMenu();
                  select2=scan.nextInt();
                  switch(select2)
                  {
                    case 1:
                        System.out.print("Enter User ID : ");
                        id=scan.next();
                        System.out.print("Enter User PIN  : ");
                        PIN=scan.next();
                        User us=new User(id,PIN);
                        UserList.add(us);
                        System.out.println("\n*****User Information Added Succesfully .");
                        break;
                    case 2:
                         displayAllUsers(UserList);
                         break;
                    case 3:
                          break;
                    default:
                         System.out.println("****Error Input , try again ....");
                         System.exit(0);
                         break;
                  }
                }while(select2!=3);                
                break;
            case 2:
                System.out.print("Enter User ID  to Login : ");
                id=scan.next();
                System.out.print("Enter User PIN to Login : ");
                PIN=scan.next();
                boolean stateUser=SearchUsers(UserList,id,PIN);
                if(stateUser==true)
                {
                  do
                  {
                   UserMenu(id);
                   select3=scan.nextInt();
                   switch(select3)
                   {
                      case 1:
                          System.out.println("        Transactions History     ");
     System.out.println("---------------------------------------------------");
                          displayAllTransactionsHistory(TransactionsHistory);
                        break;
                      case 2:
                        System.out.print("Enter Account Number to Withdraw : ");
                        AccountNumber=scan.next();
                        boolean stateAccount=SearchAccounts(AccountList,AccountNumber);
                        if(stateAccount==true)
                        {
System.out.print("Enter Amount to Withdraw from Account "+ AccountNumber+" : ");
                          double Amount=scan.nextDouble(); 
                          for(Account temp : AccountList)
                           {
                             if (temp.getAccountNumber().equals(AccountNumber)) 
                             {
                                 if(temp.getCurrentBalance()>=Amount)
                                 {
                                   temp.Withdraw(Amount);
System.out.print(Amount+" SAR Withdraw Succesfully from Account "+ AccountNumber+" . ");
System.out.println("Your Current Balance is : "+temp.getCurrentBalance());
                                   break;
                                 }
                                 else
                                 {
    System.out.println("****Your Current Balance is Not Enough to Withdraw .");
                                   System.exit(0);  
                                 }
                             }
                           }
                        }
                        else
                        {
                          System.out.println("****Error Account Information  , try again ....");
                          System.exit(0);  
                        }
                        break;
                      case 3:
                        System.out.print("Enter Account Number to Deposit : ");
                        AccountNumber=scan.next();
                        stateAccount=SearchAccounts(AccountList,AccountNumber);
                        if(stateAccount==true)
                        {
System.out.print("Enter Amount to Deposit to Account "+ AccountNumber+" : ");
                          double Amount=scan.nextDouble(); 
                          for(Account temp : AccountList)
                           {
                             if (temp.getAccountNumber().equals(AccountNumber)) 
                             {
                                 temp.Deposit(Amount);
System.out.print(Amount+" SAR Added Succesfully to Account "+ AccountNumber+" . ");
 System.out.println("Your Current Balance is : "+temp.getCurrentBalance());
                                 break;
                             } 
                           }
                        }
                        else
                        {
                          System.out.println("****Error Account Information  , try again ....");
                          System.exit(0);  
                        }
                        break;
                      case 4:
                        System.out.print("Enter Account Number to Transfer from : ");
                        AccountNumberfrom=scan.next();
                        boolean from=SearchAccounts(AccountList,AccountNumberfrom);                        
                        if(from)
                        {
                          System.out.print("Enter Account Number to Transfer to : ");
                          AccountNumberto=scan.next();
                          boolean to=SearchAccounts(AccountList,AccountNumberto); 
                          if(to)
                          {
   System.out.print("Enter Amount to transfer to Account "+ AccountNumberto+" : ");
                            double Amount=scan.nextDouble(); 
                            Account Accountto=new Account();
                            for(Account temp : AccountList)
                            {
                                if (temp.getAccountNumber().equals(AccountNumberto)) 
                                {
                                    Accountto=temp;
                                    break;
                                }
                            } 
                            for(Account temp : AccountList)
                            {
                              if (temp.getAccountNumber().equals(AccountNumberfrom)) 
                              {
                                  temp.Transfer(Accountto, Amount);                                
                                  Date d=Calendar.getInstance().getTime(); 
String Str="*****Transfer "+Amount +" SAR to "+AccountNumberto+ " from "+AccountNumberfrom+" Done Succesfully at "+d.toString();
                                  TransactionsHistory.add(Str);
                                  break;
                              }
                            }
                          }                                 
                          else
                          {
                       System.out.println("****Error Account Information  , try again ....");
                            System.exit(0);    
                          }
                        }
                        else
                        {
                          System.out.println("****Error Account Information  , try again ....");
                          System.exit(0);    
                        }                        
                        break;
                      case 6:
                        AccountNumber=generateAccountNumber();
                        System.out.print("Enter Account ID   : ");
                        id=scan.next();
                        System.out.print("Enter Account PIN  : ");
                        PIN=scan.next();
                        scan=new Scanner(System.in);
                        System.out.print("Enter Account Full Name  : ");
                        FullName=scan.nextLine();
                        System.out.print("Enter Account Initial Balance  : ");
                        CurrentBalance=scan.nextDouble();
                        Account acc=new Account(id,PIN,AccountNumber,FullName,CurrentBalance);
                        AccountList.add(acc);
               System.out.println("\n*****Account No "+AccountNumber+"  Created Succesfully .");
                        break;
                      case 5:
                         System.out.println("Thank You for Using ATM System .");
                         System.exit(0);
                         break;
                      default:
                         System.out.println("****Error Input , try again ....");
                         System.exit(0);
                         break;                           
                   }
                  }while(select3!=5);
                }
                else
                {
                  System.out.println("****Error User Information  , try again ....");
                  System.exit(0);  
                }
                break;
            case 3:
                 System.out.println("Thank You for Using ATM System .");
                 System.exit(0);
                  break;
            default:
                 System.out.println("****Error Input , try again ....");
                 System.exit(0);
                 break;
          }
        }while(select1!=3);
    }
    
}




}
