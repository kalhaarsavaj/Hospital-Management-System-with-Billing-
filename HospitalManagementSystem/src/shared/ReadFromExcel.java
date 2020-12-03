/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared;


import hospitalmanagementsystem.models.*;

import hospitalmanagementsystem.models.UserRecordModel;

import jxl.Sheet;
import jxl.Workbook;

import java.io.File;
import java.util.ArrayList;

import static hospitalmanagementsystem.HospitalManagementSystem.*;
import java.io.IOException;
import jxl.read.biff.BiffException;
/**
 *
 * @author NIAR Tech
 */
public class ReadFromExcel {
    Workbook workbook = null;

    ArrayList<String> dataObj = new ArrayList<>();
    ArrayList<UserRecordModel> userRecorddataList = new ArrayList<>();
    ArrayList<ScheduleModel> ScheduleList = new ArrayList<>();
    ArrayList<PatientRecordModel> patientRecordList = new ArrayList<>();

    private void readData(String fromClass, String fileName){
        String path = CommonVariables.directoryPath+fileName;
    try {

            workbook = Workbook.getWorkbook(new File(path));
//            System.out.println(workbook+"------abcd");
            Sheet sheet = workbook.getSheet(0);
//            System.out.println(sheet.getRows()+"---------------123232-----------");
            for (int rowCount=1 ; rowCount < sheet.getRows() ; rowCount++){
                for(int col = 0; col < sheet.getColumns(); col++){
//                    System.out.println(rowCount);
                    dataObj.add(sheet.getCell(col,rowCount).getContents());
                }
                switch(fromClass){
                    case "signup" :
                        UserRecordModel usermodel = new UserRecordModel(dataObj);
                        userRecorddataList.add(usermodel);
                        dataObj.clear();
                        break;
                    case "schedule":
                        ScheduleModel scheduleModel = new ScheduleModel(dataObj);
                        ScheduleList.add(scheduleModel);
                        dataObj.clear();
                        break;
                    case "history":
                        PatientRecordModel patientRecordModel = new PatientRecordModel(dataObj);
                        patientRecordList.add(patientRecordModel);
                        dataObj.clear();
                        break;
                }
//                System.out.println(dataObj.get(0));
            }
           

        } catch (IOException | BiffException | IndexOutOfBoundsException e) {
           System.out.println(ANSI_RED+"No record found."+ANSI_RESET+"\n");
        } 
    }
    
    public ArrayList<UserRecordModel> readUserRecord(String fromClass, String fileName){
        readData(fromClass,fileName);
        return userRecorddataList;
    }
    public ArrayList<ScheduleModel> readScheduleRecords(String fromClass, String fileName){
        readData(fromClass,fileName);
        return ScheduleList;
    }
    
     public ArrayList<PatientRecordModel> readPatientRecords(String fromClass, String fileName){
        readData(fromClass,fileName);
        return patientRecordList;
    }
    
    
}
