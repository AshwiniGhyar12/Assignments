package SeleniumAutomation;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class StockPrices {
    WebDriver driver;

    public StockPrices(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    public void navigateToReddiffPortal() {
        driver.get(" https://money.rediff.com/losers/bse/daily/groupall");
    }

    public void writeDataToExcel() throws IOException {
        XSSFWorkbook workbook=new XSSFWorkbook();
        XSSFSheet sheet=workbook.createSheet("sheet1");

        // Creating HashMap and entering data
        HashMap<String,Double> map=new HashMap<String,Double>();
        int size = dataTable.size();
        System.out.println("Size:" + size);
        for (int i = 2; i < 10; i++) {
            String data = driver.findElement(By.xpath("//table[contains(@class,'dataTable')]//tr[" + i + "]/td[1]")).getText();
            String str = driver.findElement(By.xpath("//table[contains(@class,'dataTable')]//tr[" + i + "]/td[4]")).getText();
            double val = Double.parseDouble(str.replaceAll(",", ""));
            map.put(data, val);
        }
        int rowno=0;

        for(HashMap.Entry entry:map.entrySet()) {
            XSSFRow row=sheet.createRow(rowno++);
            row.createCell(0).setCellValue((String)entry.getKey());
            row.createCell(1).setCellValue((Double) entry.getValue());
        }

        FileOutputStream file = new FileOutputStream("C://Users//ashwi//Desktop//Demo.xlsx");
        workbook.write(file);
        file.close();
        System.out.println("Data Copied to Excel");

    }

    public HashMap<String, Double> readXLSDatatoHashmap() throws IOException {
        FileInputStream file = new FileInputStream("C://Users//ashwi//Desktop//Demo.xlsx");
        XSSFWorkbook wb = new XSSFWorkbook(file);
        XSSFSheet sh = wb.getSheetAt(0);

        HashMap<String, Double> map = new HashMap<String, Double>();

        for (int r = 0; r <= sh.getLastRowNum(); r++) {
            String key = sh.getRow(r)
                    .getCell(0)
                    .getStringCellValue();
            Double value = (Double) sh.getRow(r)
                    .getCell(1)
                    .getNumericCellValue();
            map.put(key, value);
        }
        wb.close();
        file.close();
        System.out.println(map);
        return map;
    }

    @FindBy(xpath = "//table[contains(@class,'dataTable')]//tr")
    List<WebElement> dataTable;

    public HashMap<String, Double> readDataUsingSelenium() throws IOException {

        HashMap<String, Double> map = new HashMap<>();
        int size = dataTable.size();
        System.out.println("Size:" + size);
        for (int i = 2; i < 10; i++) {
            String data = driver.findElement(By.xpath("//table[contains(@class,'dataTable')]//tr[" + i + "]/td[1]")).getText();
            String str = driver.findElement(By.xpath("//table[contains(@class,'dataTable')]//tr[" + i + "]/td[4]")).getText();
            double val = Double.parseDouble(str.replaceAll(",", ""));
            map.put(data, val);
        }

        System.out.println(map);
        return map;
    }

}

