package au.com.demo.customer.data;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class CsvReader

{
	private static final String[] FILE_HEADER_MAPPING = { "FirstName", "LastName", "PostCode", "UniqueId" };
	private static final String FIRST_NAME = "FirstName";
	private static final String LAST_NAME = "LastName";
	private static final String POSTCODE = "PostCode";

	public List<CsvData> readCsvFile(String filepath) {

		File file = new File(filepath);
		FileReader inputFileReader = null;
		CSVParser csvFileParser = null;
		List<CsvData> csvEntry = new ArrayList<CsvData>();
		CSVFormat csvFileFormat = CSVFormat.DEFAULT.withHeader(FILE_HEADER_MAPPING);

		try {
			inputFileReader = new FileReader(file);
			csvFileParser = new CSVParser(inputFileReader, csvFileFormat);
			List<CSVRecord> inputCsvList = csvFileParser.getRecords();
			
			for (int rawIndex = 1; rawIndex < inputCsvList.size(); rawIndex++) {
				CSVRecord record = inputCsvList.get(rawIndex);
				CsvData csvData = new CsvData(record.get(FIRST_NAME), record.get(LAST_NAME), record.get(POSTCODE),
						rawIndex);
				csvEntry.add(csvData);
			}
		} catch (Exception e) {
			System.out.println("Error in CsvFileReader !!!");
			e.printStackTrace();
		}
        // usual boilerplate code
		finally {
			try
			{
				inputFileReader.close();
				csvFileParser.close();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		return csvEntry;
	}
}