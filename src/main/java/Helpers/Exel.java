//package Helpers;
//
//import org.apache.poi.ss.usermodel.CellType;
//import org.apache.poi.xssf.usermodel.XSSFCell;
//import org.apache.poi.xssf.usermodel.XSSFRow;
//import org.apache.poi.xssf.usermodel.XSSFSheet;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.testng.annotations.DataProvider;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//
//public class Exel {
//    @DataProvider(name = "supplier")
//    public Object[][] dataSupplier(String exelPath, String exelSheetName) {
//        String excelFilePath = System.getProperty("user.dir") + exelPath;
//        File excelFile = new File(excelFilePath);
//
//        try (FileInputStream fis = new FileInputStream(excelFile);
//             XSSFWorkbook workbook = new XSSFWorkbook(fis)) {
//
//            XSSFSheet sheet = workbook.getSheet(exelSheetName);
//            int rowsCount = sheet.getPhysicalNumberOfRows();
//            int colsCount = sheet.getRow(0).getLastCellNum();
//
//            Object[][] data = new Object[rowsCount - 1][colsCount];
//
//            for (int r = 1; r < rowsCount; r++) { // skip header
//                XSSFRow row = sheet.getRow(r);
//                for (int c = 0; c < colsCount; c++) {
//                    XSSFCell cell = row.getCell(c);
//                    if (cell != null) {
//                        CellType cellType = cell.getCellType();
//                        switch (cellType) {
//                            case STRING:
//                                data[r - 1][c] = cell.getStringCellValue();
//                                break;
//                            case NUMERIC:
//                                // Handle phone number formatting for numeric values
//                                data[r - 1][c] = String.format("%.0f", cell.getNumericCellValue());
//                                break;
//                            case BOOLEAN:
//                                data[r - 1][c] = String.valueOf(cell.getBooleanCellValue());
//                                break;
//                            default:
//                                data[r - 1][c] = "";
//                        }
//                    }
//                }
//            }
//
//            return data;
//
//        } catch (IOException e) {
//            e.printStackTrace();
//            return new Object[0][0];
//        }
//    }
//}
//
