package com.yhcrt.healthcloud.util;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
* @Description: Excel文件导入工具类
* @version	1.0		2017年5月5日
* @author jimmy
 */
public class importUtil {
    
    public List<Map<Integer,Object>> read(File dest,Integer cellLength) throws Exception{
        Workbook wookbook = null;
        FileInputStream fis = null;
        int cellType = 1;
        try {
            fis = new FileInputStream(dest);
            //2003版本的excel，用.xls结尾
            wookbook = new HSSFWorkbook(fis);//得到工作簿
            cellType = HSSFCell.CELL_TYPE_STRING;
        } catch (Exception e) {
            //2007版本的excel，用.xlsx结尾
            try {
                fis = new FileInputStream(dest);//这里不创建输入流就会报错stream close
                wookbook = new XSSFWorkbook(fis);
                cellType = XSSFCell.CELL_TYPE_STRING;
            } catch (Exception e1) {
                System.out.println("文件转换异常");
                return null;
            }//得到工作簿
        } finally {
            fis.close();
        }
        //得到一个工作表
        Sheet sheet = wookbook.getSheetAt(0);
        //获得数据的总行数
        int totalRowNum = sheet.getLastRowNum();
        //要获得属性
        List<Map<Integer,Object>> list = new ArrayList<Map<Integer,Object>>();
        Map<Integer,Object> map = null;
         //获得所有数据
        for(int x = 2 ; x <= totalRowNum ; x++){
            map = new HashMap<Integer,Object>();
            //获得第i行对象
            Row row = sheet.getRow(x);
            for(int y=0;y<cellLength;y++){
                Cell cell = row.getCell(y);
                cell.setCellType(cellType);
                map.put(y, cell.getStringCellValue().toString());
            }
            int a = 0;
            for(int i=0;i<map.size();i++){
                if(map.get(i)==null||"".equals(map.get(i))){
                    a++;
                }
            }
            if(a!=cellLength){
                list.add(map);
            }
        }
        return list;
    }
    
}
