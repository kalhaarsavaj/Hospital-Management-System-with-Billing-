/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared;
import static hospitalmanagementsystem.HospitalManagementSystem.ANSI_GREEN;
import static hospitalmanagementsystem.HospitalManagementSystem.ANSI_RED;
import static hospitalmanagementsystem.HospitalManagementSystem.ANSI_RESET;
import hospitalmanagementsystem.models.UserRecordModel;
import java.io.File;
import java.util.ArrayList;
import jxl.Cell;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

/**
 *
 * @author NIAR Tech
 */
public class WriteToExcel {
    
    public void writeData(String fileName, String[] labels,String[] data, String fromClass) throws Exception{
       boolean flag = false;
        if(fromClass == "signup")
            flag = checkIfUserExists(fromClass,fileName,data[2]);
        
        if(!flag){
        String path = CommonVariables.directoryPath+fileName;
        int noOfFields = labels.length;
        int rowCount = 0;
        File file = new File(path);
        WritableWorkbook wb;
        WritableSheet sht;
        if(file.exists()){
            Workbook existingWb = Workbook.getWorkbook(new File(file.getAbsolutePath()));
            wb = Workbook.createWorkbook(new File(path), existingWb);
        }else {
          wb = Workbook.createWorkbook(file);
          sht = wb.createSheet("data", 0);
          for (int i = 0; i<noOfFields; i++){
              sht.addCell(new Label (i, 0, labels[i]));
          }
        }
        sht = wb.getSheet(0);
        rowCount = sht.getRows();
        
        for (int colNum = 0; colNum <noOfFields; colNum++){
        sht.addCell(new Label (colNum,rowCount,data[colNum]));
        }
        wb.write();
        System.out.println(ANSI_GREEN+"Sucessfully Signed Up.!"+ANSI_RESET);
        wb.close();
//        System.out.println("Workbook is created");
        }else{
        System.out.println(ANSI_RED+"User already exists"+ANSI_RESET);
        }
    }
    
    private boolean checkIfUserExists(String fromClass,String fileName, String username){
        boolean flag = false;
        ArrayList<UserRecordModel> readUserRecord = new ArrayList<UserRecordModel>();
        ReadFromExcel readClass = new ReadFromExcel();
        readUserRecord = readClass.readUserRecord(fromClass, fileName);
        for(UserRecordModel record : readUserRecord){
            if(record.getEmail().equals(username)){
               flag = true;
                break;
            }
        }
        return flag;
    }
}
