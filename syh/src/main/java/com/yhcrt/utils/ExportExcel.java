package com.yhcrt.utils;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.hssf.usermodel.DVConstraint;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFComment;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFDataValidation;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddressList;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Comment;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;

import com.yhcrt.entity.queryStatistical.ScoresBase;

/**
 * 导出excel的工具类
 * @author kejunzhong
 * 2017年6月1日
 * 版权所有：武汉炎黄创新服务有限公司
 */
public class ExportExcel<T> {
	/**
	 *  导出为Excel工作簿
	 * @param title
	 * @param headers
	 * @param tableHead
	 * @param dataset
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public byte[] exportExcel(String title, String[] headers,String tableHead, Collection<T> dataset) {
		ByteArrayOutputStream outputStream=null;
		// 声明一个工作薄
		HSSFWorkbook workbook = new HSSFWorkbook();
		// 生成一个表格
		HSSFSheet sheet = workbook.createSheet(title);
		// 设置表格默认列宽度为15个字节
		sheet.autoSizeColumn((short) 15);
		// 生成一个样式
		HSSFCellStyle style = setHeadStyle(workbook);
		// 生成并设置另一个样式
		HSSFCellStyle style2 = setBodyStyle(workbook);
		
		// 声明一个画图的顶级管理器
		HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
		// 定义注释的大小和位置,详见文档
		HSSFComment comment = patriarch.createComment(new HSSFClientAnchor(0, 0, 0, 0, (short) 4, 2, (short) 6, 5));
		// 设置注释内容
		comment.setString(new HSSFRichTextString(tableHead));//批注
		// 设置注释作者，当鼠标移动到单元格上是可以在状态栏中看到该内容.
		comment.setAuthor("leno");
		
		HSSFRow row = sheet.createRow(0);  //第一行：抬头
        //四个参数分别是：起始行，起始列，结束行，结束列
		sheet.addMergedRegion(new CellRangeAddress(0,(short)(0),0,(short)(headers.length-1))); //设置抬头跨列数
		HSSFCell cell2= row.createCell(0);
		cell2.setCellStyle(style);
		cell2.setCellValue(tableHead);  //设置抬头
	  
		row = sheet.createRow(1);    //设置标题
		for (short i = 0; i < headers.length; i++) {
			HSSFCell cell = row.createCell(i);
			cell.setCellStyle(style);
			HSSFRichTextString text = new HSSFRichTextString(headers[i]);
			cell.setCellValue(text);
		}

		// 遍历集合数据，产生数据行
		Iterator<T> it = dataset.iterator();
		int index = 1;
		while (it.hasNext()) {
			index++;
			row = sheet.createRow(index);
			T t = (T) it.next();
			// 利用反射，根据javabean属性的先后顺序，动态调用getXxx()方法得到属性值
			Field[] fields = t.getClass().getDeclaredFields();
			for (short i = 0; i < fields.length; i++) {
				HSSFCell cell = row.createCell(i);
				cell.setCellStyle(style2);
				Field field = fields[i];
				String fieldName = field.getName();
				String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
				try {
					Class tCls = t.getClass();
					Method getMethod = tCls.getMethod(getMethodName, new Class[] {});
					Object value = getMethod.invoke(t, new Object[] {});
					// 判断值的类型后进行强制类型转换
					String textValue = null;
					if (value instanceof Boolean) {
						boolean bValue = (Boolean) value;
						textValue = "男";
						if (!bValue) {
							textValue = "女";
						}
					} else if (value instanceof byte[]) {
						// 有图片时，设置行高为60px;
						row.setHeightInPoints(60);
						// 设置图片所在列宽度为80px,注意这里单位的一个换算
						sheet.setColumnWidth(i, (short) (35.7 * 80));
						// sheet.autoSizeColumn(i);
						byte[] bsValue = (byte[]) value;
						HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0, 1023, 255, (short) 6, index, (short) 6,
								index);
						anchor.setAnchorType(2);
						patriarch.createPicture(anchor, workbook.addPicture(bsValue, HSSFWorkbook.PICTURE_TYPE_JPEG));
					} else {
						// 其它数据类型都当作字符串简单处理
						if(value!=null){
							textValue = value.toString();
						}else{
							textValue = "";
						}
					}
					// 如果不是图片数据，就利用正则表达式判断textValue是否全部由数字组成
					if (textValue != null) {
						Pattern p = Pattern.compile("^//d+(//.//d+)?$");
						Matcher matcher = p.matcher(textValue);
						if (matcher.matches()) {
							// 是数字当作double处理
							cell.setCellValue(Double.parseDouble(textValue));
						} else {
							HSSFRichTextString richString = new HSSFRichTextString(textValue);
							HSSFFont font3 = workbook.createFont();
							font3.setColor(HSSFColor.BLACK.index);
							richString.applyFont(font3);
							cell.setCellValue(richString);
						}
					}
				} catch (SecurityException e) {
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				} finally {
					// 清理资源
				}
			}
		}
		try {
			outputStream=new ByteArrayOutputStream();
			workbook.write(outputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return outputStream.toByteArray();
	}
	/**
	 *  导出为Excel工作簿
	 * @param title
	 * @param headers
	 * @param tableHead
	 * @param dataset
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public byte[] exportExcel1(String title,String[] headers1, String[] headers2,String[][] datas) {
		ByteArrayOutputStream outputStream=null;
		// 声明一个工作薄
		HSSFWorkbook workbook = new HSSFWorkbook();
		// 生成一个表格
		HSSFSheet sheet = workbook.createSheet(title);
		// 设置表格默认列宽度为15个字节
		sheet.autoSizeColumn((short) 15);
		// 生成一个样式
		HSSFCellStyle style = setHeadStyle(workbook);
		// 生成并设置另一个样式
		HSSFCellStyle style2 =setBodyStyle(workbook);
		
		HSSFRow row1 = sheet.createRow(0);    //设置一级标题
		//四个参数分别是：起始行号，终止行号， 起始列号，终止列号
		sheet.addMergedRegion(new CellRangeAddress(0,1,0,0)); //设置抬头跨列数
		HSSFCell cell1 = row1.createCell(0);
		cell1.setCellStyle(style);
		cell1.setCellValue(headers1[0]);
		
		for (int i = 1; i < headers1.length; i++) {
			sheet.addMergedRegion(new CellRangeAddress(0,0,5*i-4,5*i)); //设置抬头跨列数
			HSSFCell cell = row1.createCell(5*i-4);
			cell.setCellStyle(style);
			HSSFRichTextString text = new HSSFRichTextString(headers1[i]);
			cell.setCellValue(text);
		}
		
		HSSFRow row2 = sheet.createRow(1);    //设置二级标题
		for (short i = 0; i < headers2.length; i++) {
			HSSFCell cell = row2.createCell(i+1);
			cell.setCellStyle(style);
			HSSFRichTextString text = new HSSFRichTextString(headers2[i]);
			cell.setCellValue(text);
		}
		for (int i = 0; i < datas.length; i++) { //设置正文数据
			HSSFRow rowi = sheet.createRow(2+i);
			for (int j = 0; j < datas[i].length; j++) {
				HSSFCell cell = rowi.createCell(j);
				cell.setCellStyle(style2);
				HSSFRichTextString text = new HSSFRichTextString(datas[i][j]);
				cell.setCellValue(text);
			}
		}
			
		try {
			outputStream=new ByteArrayOutputStream();
			workbook.write(outputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return outputStream.toByteArray();
	}
	
	 /**
     * 创建Workbook
     * 
     * @param in
     * @return
     * @throws Exception
     */
    public static Workbook createWorkBook(InputStream in) throws Exception {
        try {
            return new HSSFWorkbook(in);
        } finally {
            if (in != null) {
                in.close();
            }
        }
    }

    /**
     * 设置单单元格字符串值
     * 
     * @param cell
     * @return
     */
    public static String setCellStringValue(Cell cell) {
        if (cell == null) {
            return "";
        }

        cell.setCellType(HSSFCell.CELL_TYPE_STRING);
        RichTextString str = cell.getRichStringCellValue();
        return str.getString();
    }

    /**
     * 初始化Excel单元格, 设置单元格值和样式
     * 
     * @param cell
     * @param style
     * @param value
     */
    public static void initCell(Cell cell, HSSFCellStyle style, String value) {
        cell.setCellStyle(style);
        cell.setCellValue(value);
    }

    /**
     * 初始化Excel单元格, 设置单元格值、样式和备注
     * 
     * @param cell
     * @param style
     * @param value
     * @param comment
     */
    public static void initCell(Cell cell, HSSFCellStyle style, String value, Comment comment) {
        cell.setCellStyle(style);
        cell.setCellValue(value);
        cell.setCellComment(comment);
    }

    /**
     * 设置Excel单元格备注
     * 
     * @param drawing
     * @param anchor
     * @param content
     * @return
     */
    public static Comment getCellComment(Drawing drawing, HSSFClientAnchor anchor, String content) {
        Comment comment = drawing.createCellComment(anchor);
        comment.setString(new HSSFRichTextString(content));
        return comment;
    }

    /**
     * 设置Excel标题单元格样式
     * 
     * @param wb
     * @return
     */
    public static HSSFCellStyle setHeadStyle(HSSFWorkbook workbook) {
		// 生成一个样式
		HSSFCellStyle style =  workbook.createCellStyle();
		// 设置背景色  
		style.setFillForegroundColor(HSSFColor.WHITE.index);// 设置背景色  
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		//设置边框
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框    
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框    
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框    
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
		//设置居中
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中
		// 设置字体
		HSSFFont font = workbook.createFont();
		font.setColor(HSSFColor.BLACK.index);
		font.setFontHeightInPoints((short) 12);
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//粗体显示   
		// 把字体应用到当前的样式
		style.setFont(font);

        return style;
    }

    /**
     * 设置Excel数据单元格样式
     * 
     * @param wb
     * @return
     */
    public static HSSFCellStyle setBodyStyle(HSSFWorkbook workbook) {
		// 生成并设置另一个样式
		HSSFCellStyle style = workbook.createCellStyle();
		style.setFillForegroundColor(HSSFColor.WHITE.index);
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		// 生成另一个字体
		HSSFFont font = workbook.createFont();
		font.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
		font.setColor(HSSFColor.BLACK.index);
		// 把字体应用到当前的样式
		style.setFont(font);
		// 自动换行  
		style.setWrapText(true);
        return style;
    }

    /**
     * 设置Excel错误单元格样式
     * 
     * @param wb
     * @return
     */
    public static HSSFCellStyle setErrorStyle(HSSFWorkbook workbook) {
    	HSSFCellStyle style = workbook.createCellStyle();

    	// 生成另一个字体
    	HSSFFont font = workbook.createFont();
        font.setColor(HSSFColor.RED.index);

        style.setFont(font);
        return style;
    }
	
    /**
     * @param wb HSSFWorkbook对象
     * @param realSheet 需要操作的sheet对象
     * @param datas 下拉的列表数据
     * @param startRow 开始行
     * @param endRow 结束行
     * @param startCol 开始列
     * @param endCol 结束列
     * @param hiddenSheetName 隐藏的sheet名
     * @param hiddenSheetIndex 隐藏的sheet索引
     * @return
     * @throws Exception
     */
    public static HSSFWorkbook dropDownList2003(HSSFWorkbook workbook, Sheet realSheet, String[] datas, int startRow, int endRow,
                                                int startCol, int endCol, String hiddenSheetName, int hiddenSheetIndex)throws Exception {
        // 创建一个数据源sheet
        HSSFSheet hidden = workbook.createSheet(hiddenSheetName);
        // 数据源sheet页不显示
        workbook.setSheetHidden(hiddenSheetIndex, true);
        // 将下拉列表的数据放在数据源sheet上
        HSSFRow row = null;
        HSSFCell cell = null;
        for (int i = 0, length = datas.length; i < length; i++) {
            row = hidden.createRow(i);
            cell = row.createCell(0);
            cell.setCellValue(datas[i]);
        }
        //2016-12-15更新，遇到问题：生成的excel下拉框还是可以手动编辑，不满足
        //HSSFName namedCell = workbook.createName();
        //namedCell.setNameName(hiddenSheetName);
        // A1 到 Adatas.length 表示第一列的第一行到datas.length行，需要与前一步生成的隐藏的数据源sheet数据位置对应
        //namedCell.setRefersToFormula(hiddenSheetName + "!$A$1:$A" + datas.length);
        // 指定下拉数据时，给定目标数据范围 hiddenSheetName!$A$1:$A5   隐藏sheet的A1到A5格的数据
        DVConstraint constraint = DVConstraint.createFormulaListConstraint(hiddenSheetName + "!$A$1:$A" + datas.length);
        CellRangeAddressList addressList = null;
        HSSFDataValidation validation = null;
        row = null;
        cell = null;
        // 单元格样式
        CellStyle style = workbook.createCellStyle();
        style.setDataFormat(HSSFDataFormat.getBuiltinFormat("0"));
        style.setAlignment(CellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        // 循环指定单元格下拉数据
        for (int i = startRow; i <= endRow; i++) {
            row = (HSSFRow) realSheet.createRow(i);
            cell = row.createCell(startCol);
            cell.setCellStyle(style);
            addressList = new CellRangeAddressList(i, i, startCol, endCol);
            validation = new HSSFDataValidation(addressList, constraint);
            realSheet.addValidationData(validation);
        }
        return workbook;
    }
	
	
	
	public static void main(String[] args) {
		String[] headers = { "名次", "单位", "姓名", "成绩", "得分","备注" };
		String tableHead="测试excel";
		String title="123";
		List<ScoresBase> list2=new ArrayList<ScoresBase>();
		for (int i = 0; i < 3; i++) {
			ScoresBase ScoresBase=new ScoresBase();
			ScoresBase.setRanking(i+1);
			ScoresBase.setUnitName("武汉市"+i);
			ScoresBase.setAthleteName("张三"+i);
			ScoresBase.setScores("70");
			ScoresBase.setIntrgral(9.0-i);
			ScoresBase.setBackUp("备注"+i);
			list2.add(ScoresBase);
		}
		ExportExcel<ScoresBase> ex = new ExportExcel<ScoresBase>();
		byte[] b=ex.exportExcel(title,headers,tableHead ,list2);
		FileOutputStream fout;
		try {
			fout= new FileOutputStream("E:/a.xls");
			fout.write(b);
			fout.flush();
			fout.close();
		} catch (FileNotFoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}  
	}
}
