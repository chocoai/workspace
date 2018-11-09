package com.whty.common.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFComment;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * class:PoiUtil,实现poi导excel工具类
 * 
 * @author 黄凯
 * 
 */
public abstract class PoiUtil {

	/**
	 * excel版本值
	 */
	private static final int VERSION_2003 = 2003;
	private static final int VERSION_2007 = 2007;

	/**
	 * 定义默认版本为2003
	 */
	private static int version = VERSION_2003;

	/**
	 * method:exportFile 导出excel
	 * 
	 * @param file
	 *            :excel名称
	 * @param tableHeader
	 *            :excel表头数组
	 * @param reportList
	 *            :数据源
	 * @throws Exception
	 */
	public static void exportFile(HttpServletResponse response, String fileName, List<Object> reportList,
			String tableHeader[]) throws Exception {
		OutputStream os = null;
		response.setContentType("application/vnd.ms-excel");
		response.addHeader("Content-disposition", "attachment" + ";filename=" + fileName + ".xls");
		os = response.getOutputStream();
		HSSFWorkbook wb = new HSSFWorkbook();
		try {
			createExcelSheet(wb, tableHeader, reportList);
			wb.write(os);
			os.flush();
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		} finally {
			os.close();
		}
	}

	/**
	 * method:exportFile 导出excel
	 * 
	 * @param file
	 *            :excel名称
	 * @param tableHeader
	 *            :excel表头数组
	 * @param reportList
	 *            :数据源
	 * @throws Exception
	 */
	public static void exportZipFile(HttpServletResponse response, String fileName, String filePath) throws Exception {
		OutputStream os = null;
		// FileOutputStream os =new FileOutputStream(new File(outPath));
		FileInputStream in = new FileInputStream(new File(filePath));
		response.setContentType("application/octet-stream");
		response.addHeader("Content-disposition", "attachment" + ";filename=" + fileName + ".zip");
		byte[] buffer = null;
		int tempbyte = 0;
		os = response.getOutputStream();
		// HSSFWorkbook wb = new HSSFWorkbook();
		try {
			// createExcelSheet(wb, tableHeader, reportList);
			// wb.write(os);
			buffer = new byte[1024];
			while ((tempbyte = in.read(buffer)) != -1) {
				for (int k = 0; k < tempbyte; k++)
					os.write(buffer[k]);
			}

			in.close();
			os.flush();
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		} finally {
			os.close();
		}
	}

	/**
	 * method:exportFile 导出excel
	 * 
	 * @param file
	 *            :excel名称
	 * @param tableHeader
	 *            :excel表头数组
	 * @param reportList
	 *            :数据源
	 * @throws Exception
	 */
	public static void writeExcelFile(String filePath, List<Object> reportList, String tableHeader[]) throws Exception {
		FileOutputStream os = null;
		os = new FileOutputStream(new File(filePath));
		// HttpServletResponse response = ContextUtil.getResponse();
		// response.setContentType("application/vnd.ms-excel");
		// response.addHeader("Content-disposition", "attachment" + ";filename="
		// + fileName + ".xls");
		// os = response.getOutputStream();
		HSSFWorkbook wb = new HSSFWorkbook();
		try {
			createExcelSheet(wb, tableHeader, reportList);
			wb.write(os);
			os.flush();
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		} finally {
			os.close();
		}
	}

	/**
	 * method:importFile 导入文件
	 * 
	 * @param file
	 *            获取excel的文件对象
	 * @param startLine
	 *            从第几行开始读
	 */
	public static List<Object> importFile(FileVo file, int startLine) {
		List<Object> list = null;
		try {
			list = readerExcelSheet(file, startLine);
		} catch (Exception e) {
		}
		return list;
	}

	/**
	 * method:exportFileTemplete 导出模板
	 * 
	 * @param fileName
	 *            模板名
	 * @param tableHeader
	 *            模板表头名数组
	 * @throws Exception
	 */
	public static void exportFileTemplete(HttpServletResponse response, String fileName, String tableHeader[])
			throws Exception {
		OutputStream os = null;
		response.setContentType("application/vnd.ms-excel");
		response.addHeader("Content-disposition", "attachment" + ";filename=" + fileName + ".xls");
		os = response.getOutputStream();
		HSSFWorkbook wb = new HSSFWorkbook();
		try {
			createExcelTemplate(wb, tableHeader);
			wb.write(os);
			os.flush();
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		} finally {
			os.close();
		}
	}

	/***************************************************************************
	 * method:importFile导入excel文件
	 * 
	 * @param file
	 *            获取excel的文件对象
	 * @param tableHeader
	 *            表格的标头数组
	 * @param startLine
	 *            开始行
	 */
	public static List<Object> importFile(FileVo file, String[] tableHeader, int startLine) {
		List<Object> list = null;
		try {
			list = readerExcelSheet(file, tableHeader, startLine);
		} catch (Exception e) {
		}
		return list;
	}

	/**
	 * method:readerExcelSheet 读取excel文件得到单个单元格的值
	 * 
	 * @param is
	 *            文件流
	 * @param tableHeader
	 *            excel表头
	 * @param startLine
	 *            开始行
	 * @return resultList<map> 返回结果集
	 * @throws IOException
	 */
	public static List<Object> readerExcelSheet(FileVo file, int startLine) throws IOException {
		InputStream is = new FileInputStream(file.getFile());
		if (file.getFileFileName().endsWith(".xls")) {
			version = VERSION_2003;
		} else {
			version = VERSION_2007;
		}

		Workbook wb = null;
		if (version == VERSION_2003) {
			wb = (Workbook) new HSSFWorkbook(is);
		} else if (version == VERSION_2007) {
			wb = (Workbook) new XSSFWorkbook(is);
		}

		List<Object> resultList = new ArrayList<Object>();
		String[] tableArray = null;
		Sheet sheet = null;
		Row row = null;
		Cell cell = null;
		if (wb != null) {
			for (int numSheet = 0; numSheet < wb.getNumberOfSheets(); numSheet++) {
				sheet = wb.getSheetAt(numSheet);
				if (sheet == null) {
					continue;
				}
				int length = 0;
				if (sheet != null && sheet.getRow(startLine - 1) != null) {
					length = ((Row) sheet.getRow(startLine - 1)).getLastCellNum();
				}
				if (sheet != null) {
					for (int rowNum = startLine; rowNum <= sheet.getLastRowNum(); rowNum++) {
						row = sheet.getRow(rowNum);
						if (row == null) {
							continue;
						}
						tableArray = new String[length];
						for (int cellNum = 0; cellNum < row.getLastCellNum(); cellNum++) {
							cell = row.getCell(cellNum);
							if (cell != null) {
								tableArray[cellNum] = getExcelCellValue(cell);
							} else {
								tableArray[cellNum] = "";
							}
						}
						resultList.add(tableArray);
					}
				}
			}
		}
		return resultList;
	}

	/**
	 * method:readerExcelSheet 读取文件excel对象
	 * 
	 * @param is
	 *            文件流
	 * @param tableHeader
	 *            excel表头
	 * @param startLine
	 *            开始行
	 * @return resultList<array> 返回结果集
	 * @throws IOException
	 */
	public static List<Object> readerExcelSheet(FileVo file, String[] tableHeader, int startLine) throws IOException {
		InputStream is = new FileInputStream(file.getFile());
		if (file.getFileFileName().endsWith(".xls")) {
			version = VERSION_2003;
		} else {
			version = VERSION_2007;
		}

		Workbook wb = null;
		if (version == VERSION_2003) {
			wb = (Workbook) new HSSFWorkbook(is);
		} else if (version == VERSION_2007) {
			wb = (Workbook) new XSSFWorkbook(is);
		}

		List<Object> resultList = new ArrayList<Object>();
		Map<Object, Object> map = null;
		Sheet sheet = null;
		Row row = null;
		Cell cell = null;
		if (wb != null) {
			for (int numSheet = 0; numSheet < wb.getNumberOfSheets(); numSheet++) {
				sheet = wb.getSheetAt(numSheet);
				if (sheet == null) {
					continue;
				}

				for (int rowNum = startLine; rowNum <= sheet.getLastRowNum(); rowNum++) {
					row = sheet.getRow(rowNum);
					if (row == null) {
						continue;
					}
					map = new HashMap<Object, Object>();
					;
					for (int cellNum = 0; cellNum < row.getLastCellNum(); cellNum++) {
						cell = row.getCell(cellNum);
						if (cell != null) {
							map.put(tableHeader[cellNum], getExcelCellValue(cell));
						} else {
							map.put(tableHeader[cellNum], "");
						}
					}
					resultList.add(map);
				}
			}
		}
		return resultList;
	}

	/**
	 * method:createExcelSheet 创建sheet对象
	 * 
	 * @param wb
	 *            excel工作空间对象
	 * @param tableHeader
	 *            表头数组
	 * @param reportList
	 *            结果集
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static void createExcelSheet(HSSFWorkbook wb, String[] tableHeader, List<Object> reportList)
			throws Exception {
		HSSFSheet sheet = wb.createSheet(ConstantParam.EXCEL_DEFAULT_SHEET_NAME);
		HSSFRow headerRow = sheet.createRow((short) 0);
		int cellNumber = tableHeader.length;
		Map<String, String> fileTitleMap = getFileTitleMap(tableHeader);
		String fileTitleArray[] = new String[cellNumber];
		Iterator<String> it = fileTitleMap.keySet().iterator();
		int index = 0;
		while (it.hasNext()) {
			fileTitleArray[index++] = it.next();
		}
		HSSFCellStyle style = getExcelStyle(wb);

		for (int i = 0; i < cellNumber; i++) {
			HSSFCell headerCell = headerRow.createCell(i);
			headerCell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			headerCell.setCellValue(String.valueOf(fileTitleMap.get(tableHeader[i].split("\\|")[1])));
			headerCell.setCellStyle(style);
			headerCell.setCellComment(getComment(sheet, "请设置所有单元格为为文本类型。设置单元格格式-->文本-->确定"));
			sheet.setColumnWidth(i, 6800);
		}

		HSSFCellStyle contentStyle = getExcelContentStyle(wb);
		for (int j = 0; j < reportList.size(); j++) {
			HSSFRow dataRow = sheet.createRow(j + 1);
			Map<String, String> map = (Map<String, String>) reportList.get(j);
			HSSFCell contentCell = null;
			for (int t = 0; t < cellNumber; t++) {
				contentCell = dataRow.createCell(t);
				contentCell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				contentCell.setCellValue((String.valueOf(getString(map.get(tableHeader[t].split("\\|")[1])))));

				// added by fanzhen 2011-8-2设置单元格类型为文本。方便用户输入
				contentCell.setCellStyle(contentStyle);
			}
		}
	}

	/**
	 * @param wb
	 *            excel工作空间
	 * @param tableHeader
	 *            表格头部数组
	 * @throws Exception
	 */
	public static void createExcelTemplate(HSSFWorkbook wb, String[] tableHeader) throws Exception {
		HSSFSheet sheet = wb.createSheet(ConstantParam.EXCEL_DEFAULT_SHEET_NAME);
		HSSFRow headerRow = sheet.createRow((short) 0);
		int cellNumber = tableHeader.length;
		HSSFCellStyle style = getExcelStyle(wb);

		for (int i = 0; i < cellNumber; i++) {
			HSSFCell headerCell = headerRow.createCell(i);
			headerCell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			headerCell.setCellValue(tableHeader[i]);

			// added by fanzhen 2011-8-2设置单元格类型为文本。方便用户输入
			headerCell.setCellStyle(style);
			headerCell.setCellComment(getComment(sheet, "请设置所有单元格为为文本类型。设置单元格格式-->文本-->确定"));
			sheet.setColumnWidth(i, 6800);
		}
	}

	/**
	 * 设置所有单元格为为文本类型
	 * 
	 * @author fanzhen 2011-8-5
	 */
	private static HSSFComment getComment(HSSFSheet sheet, String strContent) throws Exception {
		try {
			HSSFPatriarch patr = sheet.createDrawingPatriarch();
			HSSFClientAnchor clientAnchor = new HSSFClientAnchor(0, 0, 0, 0, (short) 4, 2, (short) 6, 5);
			HSSFComment comment = patr.createComment(clientAnchor);
			HSSFRichTextString str = new HSSFRichTextString(strContent);
			comment.setString(str);
			comment.setAuthor("System check");

			return comment;
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * method:getExcelStyle设置excel单元格样式
	 * 
	 * @param wb
	 *            excel工作空间对象
	 * @return
	 */
	public static HSSFCellStyle getExcelStyle(HSSFWorkbook wb) {
		HSSFCellStyle style = wb.createCellStyle();
		style.setFillForegroundColor((short) 13);
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		HSSFFont font = wb.createFont();
		font.setColor(HSSFColor.VIOLET.index);
		font.setFontHeightInPoints((short) 16);
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		style.setFont(font);
		style.setWrapText(true);

		// added by fanzhen,2011-8-2将excel所有单无格设置成文本内容，方便用户输入内容
		HSSFDataFormat format = wb.createDataFormat();
		style.setDataFormat(format.getFormat("@"));

		return style;
	}

	/**
	 * 设置文本的单元格样式，主是为了设置数据类型为文本型，以方便用户输入。
	 * 
	 * @author fanzhen 2011-8-2
	 * @param excel工作空间对象
	 * @return 单元格样式
	 */
	public static HSSFCellStyle getExcelContentStyle(HSSFWorkbook wb) {
		HSSFCellStyle style = wb.createCellStyle();
		HSSFDataFormat format = wb.createDataFormat();
		style.setDataFormat(format.getFormat("@"));

		return style;
	}

	/**
	 * method:getFileTitleMap得到表格头部装在msp里面
	 * 
	 * @param tableHeader
	 *            表格头部数组
	 * @return map
	 */
	private static Map<String, String> getFileTitleMap(String tableHeader[]) {
		Map<String, String> map = new HashMap<String, String>();
		for (int i = 0; i < tableHeader.length; i++) {
			String str = tableHeader[i];
			String strArray[] = str.split("\\|");
			for (int j = 0; j < strArray.length; j++) {
				map.put(strArray[j + 1], strArray[j]);
				break;
			}
		}
		return map;
	}

	/**
	 * 返回excel单元格值如果为null则返回"";
	 * 
	 * @param titleName
	 * @return titleName
	 */
	private static Object getString(Object titleName) {
		Object newTitleName = titleName;
		if (titleName == null) {
			newTitleName = "";
		}
		return newTitleName;
	}

	/**
	 * method:getExcelCellValue获 得excel 当个表格的返回值,更据下面的传值判断
	 * 
	 * @param cell
	 * @return ret;
	 */
	public static String getExcelCellValue(Cell cell) {
		String ret = "";
		try {
			if (cell == null) {
				ret = "";
			} else if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
				ret = cell.getStringCellValue().trim();
			} else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
				ret = "" + cell.getNumericCellValue();
				String temp = ret.substring(ret.indexOf(".") + 1, ret.length());
				try {
					if (Integer.parseInt(temp) == 0) {
						ret = ret.substring(0, ret.indexOf("."));
					}
				} catch (Exception ex) {
				}
			} else if (cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
				ret = cell.getCellFormula();
			} else if (cell.getCellType() == Cell.CELL_TYPE_ERROR) {
				ret = "" + cell.getErrorCellValue();
			} else if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
				ret = "" + cell.getBooleanCellValue();
			} else if (cell.getCellType() == Cell.CELL_TYPE_BLANK) {
				ret = "";
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			ret = "";
		}
		return ret;
	}

}
