package DataReader;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

public class ReadDataFromExcel {


    @Test(dataProvider = "readDataFromExcel")
    public void testExcel(String email, String password, String TestCases, String productName, String TestCaseNumber){

        System.out.println(email);
        System.out.println(password);
        System.out.println(TestCases);
        System.out.println(productName);
        System.out.println(TestCaseNumber);

    }


    @Test
    public void read() throws IOException {

        FileInputStream file = new FileInputStream(System.getProperty("user.dir")+ "\\testDataExcel.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(file);
        XSSFSheet sheet = workbook.getSheet("testDataSheet");
        int rowCount = sheet.getPhysicalNumberOfRows();
        XSSFRow row = sheet.getRow(1);
        int colCount = row.getLastCellNum();
        DataFormatter df =  new DataFormatter();
        Object[][] data = new Object[rowCount-1][colCount];
        XSSFRow  firstRow = sheet.getRow(0);
        int column = 0;
        int k=0;
        for(int i =0; i<firstRow.getLastCellNum();i++){
            if(firstRow.getCell(i).getStringCellValue().equals("TestCases")){
                column = k;
            }
            k++;
        }
        System.out.println(column);
        for(int i = 0; i<rowCount-1; i++){
            if(sheet.getRow(i).getCell(column).getStringCellValue() .equals("Purchase Order 2")){
                    XSSFRow  r = sheet.getRow(i);
                    for(int j=0; j<r.getLastCellNum();j++){
                        String cell = df.formatCellValue(r.getCell(j));
                        System.out.println(cell);
                    }
            }
        }


    }

    @DataProvider
    public Object[][] readDataFromExcel() throws IOException {
        FileInputStream file = new FileInputStream(System.getProperty("user.dir")+ "\\testDataExcel.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(file);
        XSSFSheet sheet = null;
        for(int i =0; i<workbook.getNumberOfSheets();i++){
            if(workbook.getSheetName(i).equals("testDataSheet")){
                sheet = workbook.getSheetAt(i);
            }
        }
        int rowCount = sheet.getPhysicalNumberOfRows();
        Iterator<Row> rows = sheet.iterator();
        Row firstRow = rows.next();
        int colCount = firstRow.getLastCellNum();
        Object[][] data = new Object[rowCount-1][colCount];
        Iterator<Cell> cells=  firstRow.iterator();
        int k=0;
        int column = 0;
        while(cells.hasNext()){
            Cell cell = cells.next();
            if(cell.getStringCellValue().equals("TestCases")){
                column = k;
            }
            k++;
        }
        System.out.println(column);
        while(rows.hasNext()){
            Row row = rows.next();
            if(row.getCell(column).equals("Purchase Order 2")){
                for(int i=0;i<rowCount-1;i++){
                    row = sheet.getRow(i+1);
                    for(int j = 0; j<colCount;j++){
                        Cell cell = row.getCell(i);
                        data[i][j] = cell;
                    }
                }
            }
        }
        return data;
    }

    @Test
    public void readFromExcelPractice() throws IOException {

        XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream("C:\\Users\\abhinati\\Documents\\Personal Documents\\AutomationPractice\\SeleniumFrameworkPractice\\SeleniumFrameworkRepo\\testDataExcel.xlsx"));
        XSSFSheet sheet = workbook.getSheet("testDataSheet");
        DataFormatter dataFormatter = new DataFormatter();
        int totalRows = sheet.getPhysicalNumberOfRows();
        System.out.println("Total Number of Rows - "+ totalRows);
        XSSFRow row = sheet.getRow(0);
        int totalColumns =  row.getLastCellNum();
        System.out.println("Total number of Columns - "+ totalColumns);
        /*
        Now we will write code to figure out a column which contain the header as Test Case
         */
        int column=0;
        int k=0;
        for(int i=0; i< sheet.getRow(0).getLastCellNum(); i++){
            if(sheet.getRow(0).getCell(i).getStringCellValue().equals("TestCases")){
                column=k;
            }
            k++;
        }
        System.out.println("Column with Test Cases is - " + column);
        /*
        Now we will write code to figure out a for which test case we need to fetch the data
         */
        for(int i =0; i<=totalRows; i++){
            if(sheet.getRow(i).getCell(11).getStringCellValue().equals("Purchase Order 2")){
                for(int j=0;j<totalColumns; j++){
                    System.out.println(dataFormatter.formatCellValue(sheet.getRow(i).getCell(j)));
                }
                break;
            }
        }

    }

}
