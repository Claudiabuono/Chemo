package connector;

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.nio.Buffer;
import java.nio.file.Paths;

public class CmdExec {
    public static void main(String args[]) {
        try{
            /*
            Viene prelevato il path del sistema che porta al modulo in python e viene mandato in esecuzione
            Per il path non è stata adottata una soluzione propriamente eleganete ma almeno funziona :)
             */
            String path = Paths.get(System.getProperty("user.dir"), "py", "module.py").toString();
            Process process = Runtime.getRuntime().exec(new String[]{"py", path});

            /*
            In questa sezione viene elaborato l'output prodotto dall'esecuzione del modulo
            */
            try(BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()))){
                String line;
                String output = "";

                while((line = input.readLine()) != null){
                    output += line + "\n";
                }

                System.out.println(output);
            }
        }catch(Exception err){
            err.printStackTrace();
        }
    }

}
