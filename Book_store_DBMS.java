// Book store Database management system.

import java.sql.*;
import java.io.*;

class BookStore
{ 
    public String URL,userName, Password;
    public String Query, insertQuery,selectAllQuary, searchQuery, searchAuthar, deleteQuery;
    public Connection connect;
    public Statement statement;
    
    public ResultSet result;

    public BookStore() throws Exception
    {
        // create connection to the sql using jdbc driver
        URL = "jdbc:mysql://localhost:3306/Book";
        userName = "root";
        Password = "";
        connect = DriverManager.getConnection(URL,userName,Password);
        statement = connect.createStatement();
        
    }
    public void addBook(int Book_Code, int Prize, String Book_name,String Authar) throws Exception
    {
        // use Exception handling because user may enter wring inputs
        try
        {
            insertQuery = "insert into books values("+Book_Code+",'"+Book_name+"','"+Authar+"',"+Prize+")";
            statement.execute(insertQuery);
        }
        catch (Exception obj)
        {
            System.out.println("An Error Occured.");
        }

    }

    public void repositary() throws Exception
    {
        // to show repositary next() return boolean value if it is true then it will enter in the loop and if it is false then loop stops
        // no need to use try catch because i have already used when getting inputs 
        selectAllQuary = "select * from books";
        result = statement.executeQuery(selectAllQuary);

        while(result.next())
        {
            System.out.println("-------------------------------");
            System.out.println("book code : "+result.getInt("book_code"));
            System.out.println("Book Name : "+result.getString("Name"));
            System.out.println("Authar : "+result.getString("Authar"));
            System.out.println("Prize : "+result.getInt("prize"));
            System.out.println("-------------------------------");
            System.out.println();
            
        }
        
    }
    public void SearchByName(String Book_name) throws Exception
    {
        // i used like query because sometimes the name of the book or authar is not comppletly known to the customer.
        try
        {
            searchQuery = "select * from books where name like '%"+Book_name+"%'";
            result = statement.executeQuery(searchQuery);
            //result.next() returns boolean value 
            //it Moves the cursor froward one row from its current position.
            // if condition checks that the input given by user is available in our database or not.
            if(result.next())
            {
                //that is why i executeed query again .
                //at first it moved forward so that i have to bring it back. 
                result = statement.executeQuery(searchQuery);
                while(result.next())
                {
                    System.out.println("-------------------------------");
                    System.out.println("book code : "+result.getInt("book_code"));
                    System.out.println("Book Name : "+result.getString("Name"));
                    System.out.println("Authar : "+result.getString("Authar"));
                    System.out.println("Prize : "+result.getInt("prize"));
                    System.out.println("-------------------------------");
                    System.out.println();
                }
            }
            else
            {   
                System.out.println("Sorry that book is not available...");
            }
        }
        catch (Exception obj)
        {
            System.out.println("An Error Occured.");
        }
        
    }

    public void searchByAuthar(String Authar) throws Exception
    {
        // same logic as searchByname() method.
        try
        {
            searchAuthar = "select * from books where Authar like '%"+Authar+"%'";
            result = statement.executeQuery(searchAuthar);
            if(result.next())
            {
                result = statement.executeQuery(searchAuthar);
                while(result.next())
                {
                    System.out.println("-------------------------------");
                    System.out.println("book code : "+result.getInt("book_code"));
                    System.out.println("Book Name : "+result.getString("Name"));
                    System.out.println("Authar : "+result.getString("Authar"));
                    System.out.println("Prize : "+result.getInt("prize"));
                    System.out.println("-------------------------------");
                    System.out.println();
                }
            }
            else
            {   
                System.out.println("Sorry that book is not available...");
            }

        }
        catch (Exception obj)
        {
            System.out.println("An Error Occured.");
        }
    }

    public void deleteItem(int Book_Code) throws Exception
    {
        Query = "select * from books where book_code = "+Book_Code;
        result = statement.executeQuery(Query);
        // if condition checks that the input given by user is available in our database or not.
        if(result.next())
        {
            deleteQuery = "delete from books where book_code = "+Book_Code;
            statement.execute(deleteQuery);
            System.out.println("Book_code : "+Book_Code+" Sucessfully deleted...");
        }
        else
        {
            System.out.println("Book_code : "+Book_Code+" does not exist");
        }
    }
}

class Book_store_DBMS
{
    public static void main(String arg[]) throws Exception
    {
        int iNo = 0;
        int Book_Code = 0, Prize = 0;
        String Book_name,Authar;

        BookStore book = new BookStore();
        System.out.println("-------------------------------");
        System.out.println("Welcome to the Book store.\n");
        System.out.println("-------------------------------");
        System.out.println("1. Add Book in the repositary.\n");
        System.out.println("-------------------------------");
        System.out.println("2. Search Book by name.\n");
        System.out.println("-------------------------------");
        System.out.println("3. Search Book by authar.\n");
        System.out.println("-------------------------------");
        System.out.println("4. Show repositary.\n");
        System.out.println("-------------------------------");
        System.out.println("5. Remove Item.\n");
        System.out.println("-------------------------------");

        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        
        
        // i used loop here because whenever user wants to close the application he can.
        do
        {
            switch(iNo)
            {
                case 1:
                try
                {
                    System.out.print("\nEnter Book Code: ");
                    Book_Code = Integer.parseInt(input.readLine());
                    System.out.print("Name of the book : ");
                    Book_name = input.readLine();
                    System.out.print("Authar of the book : ");
                    Authar = input.readLine();
                    System.out.print("Prize of the book : ");
                    Prize = Integer.parseInt(input.readLine());

                    book.addBook(Book_Code,Prize,Book_name,Authar);
                    
                    System.out.println("Successfully added book in the repositary...");
                }
                catch (Exception obj)
                {
                    System.out.println("An Error Occured.");
                }
                break;

                case 2:
                System.out.println("Enter the book name:");
                Book_name = input.readLine();
                book.SearchByName(Book_name);
                break;

                case 3:
                System.out.println("Enter the Authar name:");
                Authar = input.readLine();
                book.searchByAuthar(Authar);
                break;

                case 4:
                book.repositary();
                break;

                case 5:
                try
                {
                    System.out.println("Enter the Book Code:");
                    Book_Code = Integer.parseInt(input.readLine());
                    book.deleteItem(Book_Code);
                }
                catch(Exception obj)
                {
                    System.out.println("An Error Occured.");
                }

            }

            try
            {
                System.out.print("\nEnter Number: ");
                iNo = Integer.parseInt(input.readLine());
            }
            catch (Exception obj)
            {
                System.out.println("An Error Occured.");
            }
        }while(iNo != -1);
    }
}