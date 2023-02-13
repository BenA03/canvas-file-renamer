import java.io.File;
import java.util.Scanner;

public class FolderScript
{ 
	public static void main(String [] args)
	{
        Scanner scan = new Scanner(System.in);
        String pathname = "/";
        System.out.print("Provide path to homework directory (leave blank if in current directory): ");
        pathname = scan.next();
        if(pathname.endsWith("/"))
        {
            pathname.replaceAll(".$", "");
        }

        File folder = new File(pathname);
        File[] listOfFiles = folder.listFiles();
        
        int readFiles = 0;
        int numFiles = 0;
        int errors = 0;
        final int warningSize = 20;
        String permission = "no";

        System.out.println("About to rename the following files:");
        for (File file : listOfFiles) 
        {
            if (file.isFile()) 
            {
                System.out.println(file.getName());
                readFiles++;
            }
        }

        System.out.println();
        System.out.print("Approve these changes? (Y/N): ");
        permission = scan.next();

        if (readFiles >= warningSize && (permission.toLowerCase().equals("y") || permission.toLowerCase().equals("yes")))
        {
            System.out.println();
            System.out.println("WARNING! This operation will alter "+readFiles+" files.");
            System.out.print("Are you still sure you want to approve these changes? (Y/N): ");
            permission = scan.next();
        }

        if (permission.toLowerCase().equals("y") || permission.toLowerCase().equals("yes"))
        {
            System.out.println();
            for (File file : listOfFiles) 
            {
                if (file.isFile()) 
                {
                    String filename = file.getName();
                    String newFileName = filename.replaceAll("^.+?_.+?_.+?_","");
                    File newFile = new File (pathname+"/"+newFileName);
                    if (file.renameTo(newFile))
                    {
                        numFiles++;
                        if (!newFileName.matches("[A-Za-z0-9._-]+") || Character.isUpperCase(newFileName.charAt(0)))
                        {
                            System.out.println("Warning! "+newFileName+" contains filename errors.");
                            errors++;
                        }
                    }
                }
            }
    }
        System.out.println();
        System.out.println("Summary: Renamed "+ numFiles +" files successfully. Encountered "+errors+" filename error(s).");

        scan.close();
    }
}