package dictionary;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Dictionary implements Serializable
{
    public static void main(String[] args) 
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Select the Operation you want to perform\n 1)Give Input here \t \t 2)Insert Data into Dictionary \t \t 3)Show Data of Dictionary");
        byte n=sc.nextByte();
        HashSet<String> hst=new HashSet<String>();
        HashSet<String> hs=new HashSet<String>();
        switch(n)
        {
        case 2:
            try
            {
                  
                FileInputStream fin=new FileInputStream("D:/Serialization/datas.txt");
                ObjectInputStream ois=new ObjectInputStream(fin);
                hst=(HashSet<String>)ois.readObject();
                ois.close();
                fin.close();
                //System.out.println("Deserialization completed "+als);
                    do
                    {
                    System.out.println("Insert into Dictionary: or press f for Finish!!");
                    hs.add(sc.next().toLowerCase());
                    }
                    while(!hs.contains("f"));
                    hs.remove("f");
                    hst.addAll(hs);
                    hs.clear();
                    FileOutputStream fout=new FileOutputStream("D:/Serialization/datas.txt");
                    ObjectOutputStream oos=new ObjectOutputStream(fout);
                    oos.writeObject(hst);
                    oos.flush();
                    oos.close();
                    fout.close();
                    System.out.println("Data Inserted...");

            }
            catch(IOException | ClassNotFoundException e)
            {
                try
                {
                FileOutputStream fout=new FileOutputStream("D:/Serialization/datas.txt");
                ObjectOutputStream oos;/*=new ObjectOutputStream(fout);
                    oos.writeObject(al);
                    oos.flush();
                    oos.close();
                fout.close();*/
                    do
                    {
                    System.out.println("Insert into Dictionary: or press f for Finish!!");
                    hst.add(sc.next().toLowerCase());
                    }
                    while(!hst.contains("f"));
                    hst.remove("f");
                    fout=new FileOutputStream("D:/Serialization/datas.txt");
                    oos=new ObjectOutputStream(fout);
                    oos.writeObject(hst);
                    oos.flush();
                    oos.close();
                    fout.close();
                    System.out.println("Data Inserted...");

                }
                catch(Exception ex)
                {
                    System.out.println("Data Insertion failed!!");
                    System.out.println(e);
                    System.exit(0);
                }
            }
            
            break;
        case 3:
            try
            {
                FileInputStream fin=new FileInputStream("D:/Serialization/datas.txt");
                ObjectInputStream ois=new ObjectInputStream(fin);
                hst=(HashSet<String>)ois.readObject();
                ois.close();
                fin.close();
                System.out.println(hst);
            }
            catch(IOException | ClassNotFoundException e)
            {
                System.out.println("Deserialization Failed!!");
                System.out.println(e);
                System.exit(0);
            }
            break;
            
        case 1:
            try
            {
                FileInputStream fin=new FileInputStream("D:/Serialization/datas.txt");
                ObjectInputStream ois=new ObjectInputStream(fin);
                hst=(HashSet<String>)ois.readObject();
                ois.close();
                fin.close();
            }
            catch(IOException | ClassNotFoundException e)
            {
                System.out.println("Deserialization Failed!!");
                System.out.println(e);
                System.exit(0);
            }
            System.out.println("Enter the Input: ");
            String str=sc.next();
            str=str.toLowerCase();//search the string by having lower case letters
            spellChecker(str,hst);
            break;
            
        default:
            System.out.println("Invalid Choice!!");
        }
        
        
    }
        static void spellChecker(String input,HashSet<String> h)
        {
            HashSet<String> hst=new HashSet<String>(h);
            String []setArray=new String[hst.size()];
                    hst.toArray(setArray);                //All the elements of hst gets stored in the setArray in the form of array
           // String []str=input.toCharArray();
            
           Set<String> temp = new TreeSet<String>();
            if(hst.contains(input))
            {
                System.out.println(input);
            }
            else
            {  
                for(int i=input.length();i>=0;i--)
                {
                    boolean count=false;
                    for(int j=0;j<setArray.length;j++)
                    {
                        if(setArray[j].indexOf(input.substring(0,i ))==0)
                        {
                             
                        temp.add(setArray[j]);
                        
                        //((String)it.next()).indexOf(((String)input.subSequence(0, (str.length-j-1))))==0
                        }
                        if((!temp.isEmpty()) && ((setArray.length-1)==j))
                        {
                            count=true;
                        }
                    }
                if(count)
                 {
                  break;
                 } 
                } 
                System.out.println("content found "+temp);
            /*    Iterator i=temp.iterator();           //used to get data one by one
                while(i.hasNext())
                {
                    System.out.println(i.next()+" ");       
                }   */
            }   
            //new generated string
            
        }
}
