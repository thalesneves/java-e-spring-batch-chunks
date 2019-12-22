package br.com.thales.springbatch.utils;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Logger;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import br.com.springbatch.model.Line;

/**
 * classe responsável por fornecer métodos para ler e escrever linhas CSV.
 */
public class FileUtils {

    private static final Logger logger = Logger.getLogger(FileUtils.class.getName());

    private String fileName;
    private CSVReader CSVReader;
    private CSVWriter CSVWriter;
    private FileReader fileReader;
    private FileWriter fileWriter;
    private File file;

    public FileUtils(String fileName) {
        this.fileName = fileName;
    }

    public Line readLine() {
        
    	try {
            
        	if (CSVReader == null) {
            	initReader();
            }
            
            String[] line = CSVReader.readNext()[0].split(";");
        
            if (line == null) {
            	return null;
            }
            
            Line lineReturn = new Line(line[0], LocalDate.parse(line[1], DateTimeFormatter.ofPattern("MM/dd/yyyy")));
            
			return lineReturn;
        } catch (Exception e) {
            logger.severe("Error while reading line in file: " + this.fileName);
            return null;
        }
    }

    public void writeLine(Line line) {
       
    	try {
    		
            if (CSVWriter == null) { 
            	initWriter();
            }
            
            String[] lineStr = new String[2];
            lineStr[0] = line.getName();
            lineStr[1] = line.getAge().toString();
            CSVWriter.writeNext(lineStr);
        } catch (Exception e) {
            logger.severe("Error while writing line in file: " + this.fileName);
        }
    }

    private void initReader() throws Exception {
        
    	ClassLoader classLoader = this.getClass().getClassLoader();
        
        if (file == null) {
        	file = new File(classLoader.getResource(fileName).getFile());
        }
        
        if (fileReader == null) {
        	fileReader = new FileReader(file);
        }
        
        if (CSVReader == null) {
        	CSVReader = new CSVReader(fileReader);
        }
    }

    private void initWriter() throws Exception {
    	
    	if (file == null) {
            file = new File(fileName);
        }

    	if (fileWriter == null) {
        	fileWriter = new FileWriter(file, true);
        }
        
        if (CSVWriter == null) {
        	CSVWriter = new CSVWriter(fileWriter);
        }
    }

    public void closeWriter() {
       
    	try {
            CSVWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            logger.severe("Error while closing writer.");
        }
    }

    public void closeReader() {
        
    	try {
            CSVReader.close();
            fileReader.close();
        } catch (IOException e) {
            logger.severe("Error while closing reader.");
        }
    }

}