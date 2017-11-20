package ming.cpsc311.ubc;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

class ParsedMINGToJavaWriter {

    private FileWriter fw;
    private BufferedWriter writer;

    ParsedMINGToJavaWriter(String filepath) throws IOException {
        this.fw = new FileWriter(filepath);
        this.writer = new BufferedWriter(fw);
    }
    void write(ArrayList<String> parsed) throws IOException {
        for (String line: parsed){
            //writing to file
            writer.write(line);
            writer.newLine();
        }
        writer.flush();
        writer.close();
        fw.close();
    }
}
